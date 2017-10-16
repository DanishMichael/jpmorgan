package jpmorganDev;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Danish Michael
 *this class handles changing the sale log and applying the adjustments
 *the methods are self-explanatory
 *
 */
public class SaleLog {

	private Map<String, Product> saleLog; 
	//= new HashMap<String, Product>();
	private Map<String, List<String>> adjustments;
	private List<String> saleMemo;
	private Printer printer;
	
	public SaleLog()
	{
		adjustments = new HashMap<String,List<String>>();
		saleLog = new HashMap<String, Product>();
		printer = new Printer();
		saleMemo = new ArrayList<String>();
	}
	
	public void addProduct(Sale sale, int quantity)
	{
		String type = sale.getProductType();
		double cost = sale.getValue();
		if(saleLog.containsKey(type))
		{
			Product p = saleLog.get(type);
			double prevCost = p.getTotalPrice();
			int prevQty = p.getTotalCount();
			prevCost = prevCost+(cost*quantity);
			prevQty = prevQty+quantity;
			saleLog.get(type).setTotalPrice(prevCost);
			saleLog.get(type).setTotalCount(prevQty);
		}
		else
		{
			Product p = new Product(type);
			p.setTotalPrice(cost*quantity);
			p.setTotalCount(quantity);
			saleLog.put(type, p);
		}
		
		saleMemo.add(quantity+" "+type+"(s) sold for "+cost+"p each");
	}
	
	public void applyAdjustment(String type, String operator, double value)
	{
		if(saleLog.containsKey(type))
		{
			Product p = saleLog.get(type);
			int qty = p.getTotalCount();
			double price = p.getTotalPrice();
			double newPrice = price;
			if(operator.equalsIgnoreCase("add"))
			{
				double add = qty*value;
				newPrice = price+add;
			}
			else if(operator.equalsIgnoreCase("subtract"))
			{
				double subtract = qty*value;
				newPrice = price-subtract;
			}
			else if(operator.equalsIgnoreCase("multiply"))
			{
				newPrice = price*value;
			}
			saleLog.get(type).setTotalPrice(newPrice);
		
			String adjStr = operator+" "+value+"p applied to "+qty+" "+type+" changing total from $"+(price/100)+" to $"+(newPrice/100);
			if(adjustments.containsKey(type))
			{
				adjustments.get(type).add(adjStr);
			}
			else
			{
				ArrayList<String> str = new ArrayList<String>();
				str.add(adjStr);
				adjustments.put(type, str);
			}	
		}
	}
	
	public void printSaleLog()
	{
		printer.printSaleLog(getSaleLog());
	}

	public Map<String, Product> getSaleLog() {
		return saleLog;
	}

	public void setSaleLog(Map<String, Product> saleLog) {
		this.saleLog = saleLog;
	}

	public Map<String, List<String>> getAdjustments() {
		return adjustments;
	}

	public void setAdjustments(Map<String, List<String>> adjustments) {
		this.adjustments = adjustments;
	}

	public void printAdjustments()
	{
		printer.printAdjustments(adjustments);
	}

	public List<String> getSaleMemo() {
		return saleMemo;
	}

	public void setSaleMemo(List<String> saleMemo) {
		this.saleMemo = saleMemo;
	}

	
	
	
}
