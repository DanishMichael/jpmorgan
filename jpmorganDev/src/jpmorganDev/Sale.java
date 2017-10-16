package jpmorganDev;

/**
 * Author: Danish Michael
 * This class represent a unit of product sale such as 1 apple at 10p
 */
public class Sale {

	private String productType;
	private double value;
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	public Sale(String prodType, double value)
	{
		productType = prodType;
		this.value = value;
	}
}
