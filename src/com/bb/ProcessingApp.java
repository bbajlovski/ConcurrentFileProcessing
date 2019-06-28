/**
 * Free to use.
 * Future improvements.  
 */
package com.bb;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.bb.file.FileUtils;
import com.bb.file.ProprietaryParserCallable;

import java.io.File;

/**
 * The ProcessingApp starts thread pool with multiple callable tasks that 
 * recursively list all files with given extension from a starting folder .
 * 
 * Each task uses proprietary and strictly pre defined parsing process to 
 * interpret the lines in the files. The parser ignores/filters improper files 
 * and lines within a file. The rest of the lines are used for calculating 
 * statistical Summary.
 * 
 * @author bbajlovski
 * @since  2019-06-27 
 */
public class ProcessingApp {

    /**
     * <b>Usage:</b><br>
     * &nbsp;&nbsp;&nbsp;&nbsp;java ProcessingApp [STARTING_PATH] [FILE_EXTENSION] [THREAD_POOL_SIZE] <br>
     * <b>Examples:</b><br>
     * &nbsp;&nbsp;&nbsp;&nbsp;java ProcessingApp /home CSV 10<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;java ProcessingApp . CSV 8<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;java ProcessingApp ../ TXT 9<br>
     * 
     * @param args are STARTING_PATH, FILE_EXTENSION and THREAD_POOL_SIZE
     */
    public static void main(String[] args) {

	
	if (args != null && args.length > 2) {
	    String startingPath = args[0];
	    System.out.println("Starting Path: " + startingPath);

	    String fileExtension = args[1].toUpperCase();
	    System.out.println("File extension: " + fileExtension);

	    try {
		Integer threadPool = Integer.valueOf(args[2]);
		System.out.println("Thread Pool: " + threadPool);

		/*
		 * First making a list of all files
		 */
		List<File> files;
		FileUtils utils = new FileUtils();
		try {
		    System.out.println("Searching Started");
		    System.out.println(".");

		    files = utils.getFiles(startingPath, fileExtension);

		    System.out.println("Files Found: " + files.size());

		    /*
		     * Creating the thread manager with fixed size thread pool
		     */
		    ExecutorService executor = Executors.newFixedThreadPool(threadPool);

		    /*
		     * Creating list of all callable tasks. They return Void with
		     * value null.
		     */
		    List<Callable<Void>> callableTasks = new ArrayList<Callable<Void>>();
		    for (File file : files) {
			callableTasks.add(new ProprietaryParserCallable(file));
		    }
		    
		    /*
		     * Invoking the tasks and waithing them to finish (without
		     * accepting new tasks.
		     */
		    executor.invokeAll(callableTasks);
		    executor.shutdown();

		    /*
		     * Reading and printing summary results.
		     */
		    Summary summary = Summary.getInstance();

		    System.out.println();
		    summary.printPerDate();

		    System.out.println();
		    summary.printPerUserAndLocation();

		} catch (Exception e) {
		    System.out.println("File Not Found: " + startingPath);
		    e.printStackTrace();
		}
	    } catch (NumberFormatException e) {
		System.out.println("Thread Pool not a number: " + args[1]);
		e.printStackTrace();
	    }
	} else {
	    System.out.println("Usage:");
	    System.out.println("\tjava ProcessingApp <STARTING_PATH> <FILE_EXTENSION> <THREAD_POOL_SIZE>");
	    System.out.println("\nExamples:");
	    System.out.println("\tjava ProcessingApp /home CSV 10");
	    System.out.println("\tjava ProcessingApp . CSV 8");
	    System.out.println("\tjava ProcessingApp ../ TXT 9");
	}
    }

}
