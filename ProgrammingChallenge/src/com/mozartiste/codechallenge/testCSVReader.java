package com.mozartiste.codechallenge;

public class testCSVReader {

	public static void main(String[] args) {
		CsvManager mng = new CsvManager(args, ",");
		mng.summarizeAll(10);
	}

}
