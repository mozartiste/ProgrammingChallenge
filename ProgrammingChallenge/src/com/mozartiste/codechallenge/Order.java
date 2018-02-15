package com.mozartiste.codechallenge;

public class Order {
	int Quantity;
	Double Price;
	String Product;
	String Currency;
	String Key; // an Key identifiyer for the treeMap

	static String header = "Product\t\t\tTotal Quantity\t\tCurrency\t\tValue ";

	static String GetKey(String product, String currency) {
		return product + "_" + currency;
	}

	public Order(int quantity, String product, String currency, Double price) {
		super();
		this.Quantity = quantity;
		this.Product = product;
		this.Currency = currency;
		this.Key = GetKey(Product, Currency);
		this.Price = price;
		;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == this)
			return true;
		if (!(obj instanceof Order)) {
			return false;
		}

		Order mytuple = (Order) obj;
		return mytuple.Currency.equals(Currency) && mytuple.Product.equals(Product);
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + Currency.hashCode();
		result = 31 * result + Product.hashCode();
		return result;
	}

}
