package com.bb;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Summary class is a singleton that collects summary information in
 * concurrent hash maps.
 * The class also consists of tools for thread-safe update of the maps and
 * for reading values.
 * 
 * @author bbajlovski
 * @since  2019-06-27
 *
 */
/**
 * @author bbajlovski
 *
 */
public class Summary {

    /**
     * Static instance of the singleton.
     */
    private static final Summary instance;
    
    /**
     * Map that collects summary grouped per date.
     */
    private static ConcurrentHashMap<String, Integer> perDate;
    
    /**
     * Map that collects summary grouped per user and per location/
     */
    private static ConcurrentHashMap<String, Integer> perUserAndLocation;

    
    /**
     * Default constructor
     */
    private Summary() {
	 perDate = new ConcurrentHashMap<String, Integer>();
	 perUserAndLocation = new ConcurrentHashMap<String, Integer>();
    }

    static {
        try {
            instance = new Summary();
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * @return the instance of the singleton
     */
    public static Summary getInstance() {
        return instance;
    }

    /**
     * Getter for the hash map that collects summary grouped per date.
     * @return ConcurrentHashMap that collects summary grouped per date
     */
    public synchronized ConcurrentHashMap<String, Integer> getAllPerDate() {
        return perDate;
    }
    
    /**
     * Prints the summary grouped per date.
     */
    public synchronized void printPerDate() {
	System.out.println("Upload per Date");
        for (final Map.Entry<String, Integer> key : perDate.entrySet()) {
            System.out.println("-> " + key.getKey() + ", " + key.getValue());
        }
    }
    
    /**
     * Creates key/value pair for new key or increase the value for given key
     * @param key for which the value is added/increased 
     */
    public synchronized void createOrIncreasePerDate(String key) {

        if (!perDate.containsKey(key)) {
            perDate.put(key, 1);
        } else {
            perDate.put(key, perDate.get(key)+1);
        }
    }

    /**
     * Getter for the hash map that collects summary grouped per user and location.
     * @return ConcurrentHashMap that collects summary grouped per user and location
     */
    public synchronized ConcurrentHashMap<String, Integer> getAllPerUserAndLocation() {
        return perUserAndLocation;
    }
    
    /**
     * Creates key/value pair for new key or increase the value for given key
     * @param key for which the value is added/increased 
     */
    public synchronized void createOrIncreasePerUserAndLocation(String key) {

        if (!perUserAndLocation.containsKey(key)) {
            perUserAndLocation.put(key, 1);
        } else {
            perUserAndLocation.put(key, perUserAndLocation.get(key)+1);
        }
    }
    
    /**
     * Prints the summary grouped per user and location.
     */
    public synchronized void printPerUserAndLocation() {
	System.out.println("Upload by User per Location");
        for (final Map.Entry<String, Integer> key : perUserAndLocation.entrySet()) {
            System.out.println("-> " + key.getKey() + ", " + key.getValue());
        }
    }
    
}
