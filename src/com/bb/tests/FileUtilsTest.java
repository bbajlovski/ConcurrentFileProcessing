/**
 * 
 */
package com.bb.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.bb.file.FileUtils;

/**
 * Test Cases for {@link com.bb.file.FileUtils}
 * 
 * @author bbajlovski
 * @since  2019-06-27
 *
 */
public class FileUtilsTest {

    /**
     * Test method for {@link com.bb.file.FileUtils#getFiles(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testGetFiles() {
	FileUtils fileUtils = new FileUtils();
	
	List<File> actual = null;
	
	try {
	    actual = fileUtils.getFiles("", "");
	} catch (Exception e) {
	    
	}
	assertEquals(null, actual);
	
	try {
	    actual = fileUtils.getFiles("~", "");
	} catch (Exception e) {
	    
	}
	assertEquals(null, actual);
	
	try {
	    actual = fileUtils.getFiles("..", "");
	} catch (Exception e) {
	    
	}
	assertNotEquals(null, actual);
	
	try {
	    actual = fileUtils.getFiles(".", "");
	} catch (Exception e) {
	    
	}
	assertNotEquals(null, actual);
    }

}
