package com.mozartiste.codechallenge.test;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mozartiste.codechallenge.CsvManager;
import com.mozartiste.codechallenge.Order;

public class testCSVparser {
	protected Order order1;
	protected Order order2;
	protected int nbOrders;
	
	@Before
	public void setUp() throws Exception {
		String[] filesArg = {"orders1.csv","orders2.csv","orders3.csv"};
		CsvManager mng = new CsvManager(filesArg, ",");
		mng.summarizeAll();
		nbOrders=mng.getNbRecors();
		Map<Order, Integer> SortedSumProducts=mng.getSortedSumProducts();
		Iterator<Entry<Order, Integer>> entries = SortedSumProducts.entrySet().iterator();
		Map.Entry<Order, Integer> entry = entries.next();
		order1 = new Order(1000, "Blue Shoes", "GBP", 40.00);
		order2 = entry.getKey();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFirstLine() {
		assertEquals(order2, order1);
	}
	
	@Test
	public void totalOrders() {
		assertEquals(12, nbOrders);
	}

}
