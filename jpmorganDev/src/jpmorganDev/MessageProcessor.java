package jpmorganDev;
/**
 * 
 * @author Danish Michael
 * This class is used to decode the messages into programmable form
 * and further process them to record sales and adjustments
 */
public class MessageProcessor {

	private SaleLog saleLog;
	
	public MessageProcessor()
	{
		saleLog = new SaleLog();
	}
	
	/**
	 * 
	 * @param msg: the message to be decoded
	 */
	public void processMsg(String msg)
	{
		msg = msg.trim().toLowerCase();
		String[] arr = msg.split(" ");
		if(msg.matches("[a-z]*\\sat\\s[0-9]*p"))
		{
			processMsgOne(arr);
		}
		else if( msg.matches("[0-9]*\\ssales\\sof\\s[a-z]*\\sat\\s[0-9]*p\\seach"))
		{
			processMsgTwo(arr);
		}
		else if(msg.matches("[add|subtract|multiply]*\\s[0-9]*p\\s[a-z]*"))
		{
			processMsgThree(arr);
		}
		else
		{
			System.out.println("Invalid message format");
		}
			
	}
	
	public void processMsgOne(String[] msgArr)
	{
		String productType = msgArr[0];
		String productCostStr = msgArr[2];
		productCostStr = productCostStr.substring(0,productCostStr.length()-1);
		double prodCost = Double.parseDouble(productCostStr);
		Sale sale = new Sale(productType, prodCost);
		saleLog.addProduct(sale, 1);
	}
	
	
	public void processMsgTwo(String[] msgArr)
	{
		String productType = getSingular(msgArr[3]);
		String prodCostStr = msgArr[5];
		String prodQtyStr = msgArr[0];
		prodCostStr = prodCostStr.substring(0,prodCostStr.length()-1);
		double prodCost = Double.parseDouble(prodCostStr);
		int prodQty = Integer.parseInt(prodQtyStr);
		Sale sale = new Sale(productType, prodCost);
		saleLog.addProduct(sale, prodQty);
	}
	
	
	public void processMsgThree(String[] msgArr)
	{
		String operator = msgArr[0];
		String productType = getSingular(msgArr[2]);
		String valueStr = msgArr[1];
		valueStr = valueStr.substring(0,valueStr.length()-1);
		double value = Double.parseDouble(valueStr);
		saleLog.applyAdjustment(productType, operator, value);
	}

	public SaleLog getSaleLog() {
		return saleLog;
	}

	public void setSaleLog(SaleLog saleLog) {
		this.saleLog = saleLog;
	}
	
	/**
	 * 
	 * @param plural : a plural string such as mangoes
	 * @return : singular of that plural such as mango
	 */
	public String getSingular(String plural)
	{
		if(plural.matches("[a-z]*ies"))
		{
			plural = plural.substring(0, plural.length()-3);
			return plural+"y";
		}
		else if (plural.matches("[a-z]*[o|s|ch|x|z]es"))
		{
			return plural.substring(0, plural.length()-2);
		}
		else if(plural.matches("[a-z]*s"))
		{
			return plural.substring(0, plural.length()-1);
		}
		else
		{
			return plural;
		}
	}
}


