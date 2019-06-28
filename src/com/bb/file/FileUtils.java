package com.bb.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Utils that makes life easier while working with files.
 * 
 * @author bbajlovski
 * @since  2019-06-27
 *
 */
public class FileUtils {

    /**
     * Getting the files for giving folder and recursively searching for files in subfolders. 
     * 
     * @param directoryName directory where searching of file starts
     * @param fileExtension file extension for the files that we are looking
     * @return list of files
     * @throws java.lang.Exception all exception (needs to be replaced)
     */
    public List<File> getFiles(String directoryName, String fileExtension) throws Exception {
	List<File> filteredFiles = new ArrayList<File>();

	File directory = new File(directoryName);

	File[] files = directory.listFiles();

	for (File file : files) {
	    if (file.isFile() && fileExtension.compareTo(getFileExtension(file).toUpperCase()) == 0) {
		System.out.println("-> " + file.getAbsolutePath());
		filteredFiles.add(file);
	    } else if (file.isDirectory()) {
		filteredFiles.addAll(getFiles(file.getAbsolutePath(), fileExtension));
	    }
	}
	return filteredFiles;
    }

    /**
     * Check the extension name of a given file
     * 
     * @param file File object
     * @return extension in a String
     */
    private String getFileExtension(File file) {
	String extension = "";
	String name = file.getName();
	int lastIndexOf = name.lastIndexOf(".");
	if (lastIndexOf > -1) {
	    extension = name.substring(lastIndexOf + 1);
	}
	return extension;
    }
}
