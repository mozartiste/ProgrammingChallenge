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
	* Set Quantity
	* @param int the quantity
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
	* @param Double the quantity
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
	* @param String the Product Name
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
	* @param String the Currency
	*/
	public void setCurrency(String currency) {
		Currency = currency;
	}

	/**
	* @Override the method equals
	* @param Order to compare
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
	* @Override hashCode
	*/
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + Currency.hashCode();
		result = 31 * result + Product.hashCode();
		return result;
	}

}
