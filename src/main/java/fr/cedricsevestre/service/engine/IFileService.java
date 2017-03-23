package fr.cedricsevestre.service.engine;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import fr.cedricsevestre.bean.NFile;
import fr.cedricsevestre.entity.engine.IFile;
import fr.cedricsevestre.exception.ServiceException;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface IFileService<T extends IFile> {

    void init();

    void store(MultipartFile file);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();
    
    List<NFile> list(String path, boolean onlyFolders) throws ServiceException;
	
}
