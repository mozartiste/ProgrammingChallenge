package com.mozartiste.codechallenge;

public class Start {

	public static void main(String[] args) {
		CsvManager mng = new CsvManager(args, ",");
		mng.summarizeAll();
		mng.printSummary(10);
	}

}
