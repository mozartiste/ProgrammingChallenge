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
	protected CsvManager mng1;
	protected CsvManager mng2;
	protected Order order1;
	protected Order order2;
	protected int nbOrders;
	
	@Before
	public void setUp() throws Exception {
		String[] filesArg1 = {"orders1.csv"};
		String[] filesArg2 = {"orders1.csv"};
	    mng1 = new CsvManager(filesArg1, ",");
	    mng2 = new CsvManager(filesArg1, ",");
		mng1.summarizeAll();
		mng2.summarizeAll();
		nbOrders=mng1.getNbRecors();
		Map<Order, Integer> SortedSumProducts=mng1.getSortedSumProducts();
		Iterator<Entry<Order, Integer>> entries = SortedSumProducts.entrySet().iterator();
		Map.Entry<Order, Integer> entry = entries.next();
		order1 = new Order(1000, "Red Shoes", "CHF", 35.11);
		order2 = entry.getKey();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void FullTest() {
		assertEquals(mng1, mng1);
	}
	
	@Test
	public void testFirstLine() {
		assertEquals(order2, order1);
	}
	
	@Test
	public void totalOrders() {
		assertEquals(4, nbOrders);
	}

}
