package com.mozartiste.codechallenge;

public class Order {
	private int Quantity;
	private Double Price;
	private String Product;
	private String Currency;
	
	static String header = "Product\t\t\tTotal Quantity\t\tCurrency\t\tValue ";

	// constructor
	public Order(int quantity, String product, String currency, Double price) {
		super();
		this.Quantity = quantity;
		this.Product = product;
		this.Currency = currency;
		this.Price = price;
		;
	}
	
	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public Double getPrice() {
		return Price;
	}

	public void setPrice(Double price) {
		Price = price;
	}

	public String getProduct() {
		return Product;
	}

	public void setProduct(String product) {
		Product = product;
	}

	public String getCurrency() {
		return Currency;
	}

	public void setCurrency(String currency) {
		Currency = currency;
	}

	public static String getHeader() {
		return header;
	}

	public static void setHeader(String header) {
		Order.header = header;
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
