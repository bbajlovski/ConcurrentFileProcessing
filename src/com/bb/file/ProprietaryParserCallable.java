package com.bb.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.Callable;

import com.bb.Summary;

/**
 * Sequential parser for file that is callable.
 * File is not loaded in the memory. Instead, scanner is used for reading
 * line by line.
 * 
 * @author bbajlovski
 * @since  2019-06-27
 *
 */
public class ProprietaryParserCallable implements Callable<Void> {

    /**
     * The file object that is parsed.
     */
    private File file;
    
    /**
     * Strictly defined delimiter.
     */
    private final String delimiter = ",";
    
    /**
     * Scanner that reads parts from file (not whole file).
     */
    private Scanner input;

    /**
     * Constructor with a file object.
     * 
     * @param file file object
     * @throws FileNotFoundException if listed file is missing
     */
    public ProprietaryParserCallable(File file) throws FileNotFoundException {
	this.file = file;
	input = new Scanner(file);
    }

    /**
     * Method that starts the parsing. It can be used also for sequential calls
     * and not just by callable.
     */
    public void parse() {

	String line;

	System.out.println("\nParsing file: " + file.getAbsolutePath());

	Summary summary = Summary.getInstance();
	
	/*
	 * Scanning a file portions
	 */
	int rowNumber = 0;
	while (input.hasNext()) {
	    line = input.nextLine();
	    System.out.print(rowNumber + " -> ");
	    
	    /*
	     * If it is a proper line it will process 
	     */
	    if (line != null && line.length() > 1) {
		String[] values = line.split(delimiter);
		
		/*
		 * If the line has at least 4 columns
		 */
		if (values.length >= 4) {

		    Integer imageId;
		    String uploadDate;
		    Integer userId;
		    String city;

		    try {
			
			/*
			 * Validation of values in each of the 4 columns
			 */
			imageId = Integer.valueOf(values[0].trim());
			System.out.print(imageId + "\t");

			if (values[1].trim().length() >= 10) {
			    uploadDate = values[1].trim().substring(0, 10);
			    System.out.print(values[1] + "\t");

			    try {
				userId = Integer.valueOf(values[2].trim());
				System.out.print(values[2] + "\t");

				city = values[3].trim();
				System.out.print(values[3] + "\t");

				if (values.length > 4) {
				    System.out.print("(rest of the line is ignored)");
				}
				
				/*
				 * If validation is successful summary data is updated
				 */
				summary.createOrIncreasePerDate(uploadDate);
				summary.createOrIncreasePerUserAndLocation(userId + ", " + city);

			    } catch (NumberFormatException e) {
				System.out.print("(bad value)\t(rest of the line is ignored)");
			    }

			} else {
			    System.out.print("(bad value)\t(rest of the line is ignored)");
			}

		    } catch (NumberFormatException e) {
			System.out.print("(bad value)\t(rest of the line is ignored)");
		    }
		} else {
		    System.out.print("SKIPPED (not enough columns)");
		}

	    } else {
		System.out.print("SKIPPED (empty line)");
	    }
	    System.out.println();
	    rowNumber++;
	}

	input.close();

    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public Void call() {
	parse();
	return null;
    }
}
