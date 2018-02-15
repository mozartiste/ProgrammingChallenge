package com.mozartiste.codechallenge;

/**
* The class is the descriptor of an order
* orders is described by Quantity, Price, Product, Currency
* @version 0.0
* @author Mehdi Korti
*/
public class Order {
	// Attributs
	private int Quantity;
	private Double Price;
	private String Product;
	private String Currency;
	


	// constructor
	public Order(int quantity, String product, String currency, Double price) {
		super();
		this.Quantity = quantity;
		this.Product = product;
		this.Currency = currency;
		this.Price = price;
		;
	}
	
	// Setters and Getters
	
	/**
	* Get Quantity
	* @return int the quantity
	*/
	public int getQuantity() {
		return Quantity;
	}

	/**
	* Set Quantity the number of product order
	* @param quantity is the number of articles ordered
	*/
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	/**
	* Get Price
	* @return Double the Price
	*/
	public Double getPrice() {
		return Price;
	}

	/**
	* Set Price
	* @param price is the unit price of the product
	*/
	public void setPrice(Double price) {
		Price = price;
	}

	/**
	* Get Product Name
	* @return String the Product Name
	*/
	public String getProduct() {
		return Product;
	}
	
	/**
	* Set Product Name
	* @param product is the name of the Product
	*/
	public void setProduct(String product) {
		Product = product;
	}

	/**
	* Get Currency
	* @return String the Currency
	*/
	public String getCurrency() {
		return Currency;
	}

	/**
	* Set Currency
	* @param currency of the order
	*/
	public void setCurrency(String currency) {
		Currency = currency;
	}

	/**
	* Overriding the method equals
	* @param obj is the Order to compare
	*/
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

	/**
	* Override the hashCode method
	*/
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + Currency.hashCode();
		result = 31 * result + Product.hashCode();
		return result;
	}

}
