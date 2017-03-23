package fr.cedricsevestre.entity.engine;

public interface IFile {

	String getPath();

	void setPath(String path);

	String getFileName();

	void setFileName(String fileName);
	
	Long getFileSize();

	void setFileSize(Long fileSize);

}
