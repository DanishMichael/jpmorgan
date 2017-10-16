package jpmorganDev;
/**
 * 
 * @author Danish Michael
 *This class holds the totalCount and totalPrice of a single product type 
 *such as apple
 */
public class Product {

	private String type;
	private Integer totalCount;
	private Double totalPrice;
	
	public Product(String type)
	{
		this.type = type;
		totalCount = 0;
		totalPrice = 0.0;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
}
