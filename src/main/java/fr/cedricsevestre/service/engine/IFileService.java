package fr.cedricsevestre.service.engine;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import fr.cedricsevestre.entity.engine.IFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface IFileService<T extends IFile> {

    void init();

    void store(MultipartFile file);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();
	
}
