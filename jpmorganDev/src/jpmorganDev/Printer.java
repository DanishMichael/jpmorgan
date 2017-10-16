package jpmorganDev;

import java.util.List;
import java.util.Map;
/**
 * 
 * @author Danish Michael
 *	This class has self explanatory methods for printing the logs
 */
public class Printer {
	/**
	 * prints the adjustment logs
	 * @param adjustments : has the map which contains all products and their respective adjustments
	 */
	public void printAdjustments(Map<String,List<String>> adjustments)
	{
		System.out.println("======================start of adjustment log==============================");
		for(String key : adjustments.keySet())
		{
			System.out.println(key);
			for(String str : adjustments.get(key))
			{
				System.out.println(str);
			}
			System.out.println();
		}
		System.out.println("======================end of adjustment log==============================");
	}
	
	/**
	 * prints the sale log
	 * @param map : the map which contains all the products and their respective sale values
	 */
	public void printSaleLog(Map<String, Product> map)
	{
		Product p = null;
		System.out.println("======================start of salelog==============================");
		for(String key : map.keySet())
		{
			p = map.get(key);
			System.out.println("====================================================");
			System.out.println("<Product Type>");
			System.out.println(key);
			System.out.println("<Total sale>");
			System.out.println(p.getTotalCount());
			System.out.println("<Total value>");
			System.out.println("$"+p.getTotalPrice()/100);
		}
		System.out.println("=======================end of salelog=============================");
		System.out.println();
		System.out.println();
		System.out.println();
	}
}
