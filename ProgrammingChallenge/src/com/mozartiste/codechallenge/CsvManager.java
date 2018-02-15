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

public class CsvManager {

	private String[] CSVs;
	private String separator;
	private List<Order> totalOrders;

	// Getters and setters
	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public List<Order> getTotalOrders() {
		return totalOrders;
	}

	public void setTotalOrders(List<Order> totalOrders) {
		this.totalOrders = totalOrders;
	}

	public CsvManager(String[] cSVs, String separator) {
		super();
		CSVs = cSVs;
		this.separator = separator;
		totalOrders = new ArrayList<Order>();
	}

	public List<String[]> readCSV(String fileCSV, String separator) {

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

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map, boolean desc) {
		if (desc) {
			return map.entrySet().stream().sorted(Map.Entry.comparingByValue(Collections.reverseOrder())).collect(
					Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		} else {
			return map.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(
					Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		}
	}

	public void summarizeAll(int nbLines) {
		List<String[]> totalFiles = new ArrayList<String[]>();

		for (String csvfile : CSVs) {
			List<String[]> orders = readCSV("data/" + csvfile, ",");
			totalFiles.addAll(orders);
		}

		Map<Order, Integer> SumProducts = totalFiles.stream()
				.collect(Collectors.groupingBy(
						p -> new Order(Integer.parseInt(p[1]), p[2], p[4], Double.parseDouble(p[3])),
						Collectors.summingInt(p -> Integer.parseInt(p[1]))));

		Map<Order, Integer> SortedSumProducts = sortByValue(SumProducts, true);

		System.out.println(Order.header);
		int i = 0;

		Iterator<Entry<Order, Integer>> entries = SortedSumProducts.entrySet().iterator();
		while (entries.hasNext() && i < nbLines) {
			i++;
			Map.Entry<Order, Integer> entry = entries.next();
			System.out.println(entry.getKey().Product + "\t\t" + entry.getValue() + "\t\t\t" + entry.getKey().Currency
					+ "\t\t" + (entry.getValue() * entry.getKey().Price));

		}

	}

}
