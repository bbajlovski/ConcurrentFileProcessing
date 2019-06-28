package com.bb.tests;

import static org.junit.Assert.*;

import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

import com.bb.Summary;

/**
 * Test Cases for {@link com.bb.Summary}
 * 
 * @author bbajlovski
 * @since  2019-06-27
 * 
 */
public class SummaryTest {

    /**
     * Test  method for {@link com.bb.Summary#getAllPerDate}.
     */
    @Test
    public void testGetAllPerDate() {
	
	Summary summary = Summary.getInstance();
	
	ConcurrentHashMap<String, Integer> actual = summary.getAllPerDate();
	
	assertNotEquals(null, actual);
	assertEquals(true, actual.size()>=0);
    }

    /** 
     * Test  method for {@link com.bb.Summary#getAllPerUserAndLocation}.
     */
    @Test
    public void testGetAllPerUserAndLocation() {

	
	Summary summary = Summary.getInstance();
	
	ConcurrentHashMap<String, Integer> actual = summary.getAllPerUserAndLocation();
	
	assertNotEquals(null, actual);
	assertEquals(true, actual.size()>=0);
    }

}
