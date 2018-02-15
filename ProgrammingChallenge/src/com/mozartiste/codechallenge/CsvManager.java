package com.mozartiste.codechallenge;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
* The class is the contener for all orders
* orders are stored and ordered in a Map<Order, Integer>
* @version 0.0
* @author Mehdi Korti
*/
public class CsvManager {

	
	//Attributs
	private String[] CSVs;
	private String separator;
	Map<Order, Integer> SortedSumProducts;
	int NbRecors;
	
	/**
	* Static attribute
	* This is the header for the final output
	*/
	final static String header = "Product\t\t\tTotal Quantity\t\tCurrency\t\tValue ";
	
	@Override
	/**
     * Compare 2 object CsvManager
     * @param o object to be compared for equality with this object
     * @return <tt>true</tt> if the specified object is equal to this map
     */
    public boolean equals(Object o) {
			
        if (o == this)
            return true;

        if (!(o instanceof CsvManager))
            return false;
        
        Map<Order,Integer> m = ((CsvManager) o).getSortedSumProducts();
        if (m.size() != SortedSumProducts.size())
            return false;

        try {
            Iterator<Entry<Order,Integer>> i = SortedSumProducts.entrySet().iterator();
            while (i.hasNext()) {
                Entry<Order,Integer> e = i.next();
                Order key = e.getKey();
                Integer value = e.getValue();
                if (value == null) {
                    if (!(m.get(key)==null && m.containsKey(key)))
                        return false;
                } else {
                    if (!value.equals(m.get(key)))
                        return false;
                }
            }
        } catch (ClassCastException unused) {
            return false;
        } catch (NullPointerException unused) {
            return false;
        }

        return true;
    }

	/** 
	  * 
	  * @return The number of records stord in the Map<Order, Integer> SortedSumProducts
	  *
	*/
	public int getNbRecors() {
		return NbRecors;
	}

	/** 
	  * 
	  * @return Return the Map<Order, Integer> SortedSumProducts
	  *
	*/
	// Getters and setters
	public Map<Order, Integer> getSortedSumProducts() {
		return SortedSumProducts;
	}

	/** 
	  * 
	  * @param The String separator for the CSVs
	  *
	*/
	public void setSeparator(String separator) {
		this.separator = separator;
	}


	/** 
	  * This is the constructor of the Class
	  * @param A String[] of the CSVs file names and a String as a separator
	  * 
	  *
	*/
	public CsvManager(String[] cSVs, String separator) {
		super();
		CSVs = cSVs;
		this.separator = separator;

	}

	/** 
	  * This is the constructor of the Class
	  * @param A String[] of the CSVs file names
	  * 	@return A List<String[]> of all lines for all CSV Files
	  *
	*/
	public List<String[]> readCSV(String fileCSV) {

		Pattern pattern = Pattern.compile(separator);
		List<String[]> orders = null;
		try (BufferedReader in = new BufferedReader(new FileReader(fileCSV));) {
			orders = in.lines().skip(1).map(line -> {
				String[] arr = pattern.split(line);
				return arr;
			}).collect(Collectors.toList());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return orders;
	}

	/** 
	  * This is sorting function for a Map<K, V> map
	  * @param A Map<K, V> map to sort
	  * @param  boolean desc true for ascending descending and false for ascending sorting
	  * 	@return A List<String[]> of all lines for all CSV Files
	  *
	*/
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map, boolean desc) {
		if (desc) {
			return map.entrySet().stream().sorted(Map.Entry.comparingByValue(Collections.reverseOrder())).collect(
					Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		} else {
			return map.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(
					Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		}
	}

	/** 
	  * This function parse List<String[]> of all CSV lines then sort and set Map<Order, Integer> SortedSumProducts;
	  *
	*/
	public void summarizeAll() {
		List<String[]> totalFiles = new ArrayList<String[]>();

		// Read all the CSV file and add lines in List<String[]> orders
		for (String csvfile : CSVs) {
			List<String[]> orders = readCSV("./data/" + csvfile);
			totalFiles.addAll(orders);
		}

		// perform a SUM of Quantity Gruoped By (Product, Currency) using the equals method of Order
		Map<Order, Integer> SumProducts = totalFiles.stream()
				.collect(Collectors.groupingBy(
						p -> new Order(Integer.parseInt(p[1]), p[2], p[4], Double.parseDouble(p[3])),
						Collectors.summingInt(p -> Integer.parseInt(p[1]))));

		// sort the result in desc ( second argument as true )
		SortedSumProducts = sortByValue(SumProducts, true);
		Iterator<Entry<Order, Integer>> entries = SortedSumProducts.entrySet().iterator();
		this.NbRecors = 0;
		while (entries.hasNext()) {
				this.NbRecors++;
				entries.next();
		}

	}
	
	/** 
	  * Print in the terminal the Top nbLines of the sorted Map<Order, Integer> SortedSumProducts
	  *
	*/
	public void printSummary(int nbLines) {
		System.out.println(header);
		int i = 0;
		//Iterator untill nbLines and print thhe nbLines first lines in the Terminal
		Iterator<Entry<Order, Integer>> entries = SortedSumProducts.entrySet().iterator();
		while (entries.hasNext() && i < nbLines) {
			i++;
			Map.Entry<Order, Integer> entry = entries.next();
			System.out.println(entry.getKey().getProduct() + "\t\t" + entry.getValue() + "\t\t\t" + entry.getKey().getCurrency()
					+ "\t\t" + (entry.getValue() * entry.getKey().getPrice()));

		}
	}

}
