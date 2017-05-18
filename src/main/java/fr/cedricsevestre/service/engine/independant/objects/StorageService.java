package fr.cedricsevestre.service.engine.independant.objects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import fr.cedricsevestre.annotation.BOService;
import fr.cedricsevestre.bean.NFile;
import fr.cedricsevestre.com.utils.CommonUtil;
import fr.cedricsevestre.conf.ApplicationProperties;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.exception.StorageException;
import fr.cedricsevestre.exception.StorageFileNotFoundException;
import fr.cedricsevestre.service.engine.IStorageService;


@Service
@Scope(value = "singleton")
@BOService
public class StorageService implements IStorageService{
	private Logger logger = Logger.getLogger(StorageService.class);
	
	@Autowired
	protected CommonUtil common;
	
	@Autowired
	protected ApplicationProperties applicationProperties;
	
    private Path rootLocation;
    
    @PostConstruct
    private void initialize(){
    	this.rootLocation = Paths.get(common.getWebInfFolder() + applicationProperties.getUploadDir());
    }
    
	public List<NFile> list(String path, boolean onlyFolders) throws ServiceException  {
    	try{
			File dir = new File(rootLocation + path);
			File[] fileList = dir.listFiles();
			List<NFile> nFiles = new ArrayList<>();
			if (fileList != null) {
				for (File f : fileList) {
					if (!f.exists() || (onlyFolders && !f.isDirectory())) {
						continue;
					}
					nFiles.add(mkNFile(f));
				}
			}
			return nFiles;
    	} catch (IOException e) {
			throw new ServiceException("todo", e);
		}
	}

    private NFile mkNFile(java.io.File file) throws IOException{
    	if (!file.exists()) throw new FileNotFoundException();
    	Date d = new Date(file.lastModified());
    	return new NFile(file.getName(), getPermissions(file), d, file.length(), file.isFile() ? "file" : "dir", null);
    }
    private String getPermissions(java.io.File f) throws IOException {
		// http://www.programcreek.com/java-api-examples/index.php?api=java.nio.file.attribute.PosixFileAttributes
		PosixFileAttributeView fileAttributeView = Files.getFileAttributeView(f.toPath(), PosixFileAttributeView.class);
		if (fileAttributeView == null) return null;
		PosixFileAttributes readAttributes = fileAttributeView.readAttributes();
		Set<PosixFilePermission> permissions = readAttributes.permissions();
		return PosixFilePermissions.toString(permissions);
	}


    @Override
    public void store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }


    public Path getPath(String filename) {
        return Paths.get(rootLocation + filename);
    }
    
    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(this.rootLocation + filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }
    
	public void move(String filename, String newFilename) {
		try {
			File afile = new File(rootLocation + filename);
			if (!afile.renameTo(new File(rootLocation + newFilename))){
				throw new StorageException("Failed to move file : " + filename + " to " + newFilename);
			}
		}catch(Exception e){
			throw new StorageException(e.getMessage(), e);
    	}
	}

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
    
    public void delete(String path) {
    	FileSystemUtils.deleteRecursively(Paths.get(rootLocation + path).toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectory(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }


    
}
