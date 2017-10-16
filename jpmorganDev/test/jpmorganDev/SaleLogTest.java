package jpmorganDev;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SaleLogTest {

	
	@Test
	public void testAddProduct_NewType()
	{
		SaleLog log = new SaleLog();
		Sale sale = new Sale("apple", 10.0);
		log.addProduct(sale, 1);
		assertEquals(1, log.getSaleLog().size());
		assertEquals(1, log.getSaleMemo().size());
	}
	
	@Test
	public void testAddProduct_ExistingTypeWithSameCost()
	{
		SaleLog log = new SaleLog();
		Sale sale = new Sale("apple", 10.0);
		log.addProduct(sale, 1);
		log.addProduct(sale, 5);
		assertEquals(1, log.getSaleLog().size());
		int qty = log.getSaleLog().get(sale.getProductType()).getTotalCount();
		assertEquals(6, qty);
		assertEquals(2, log.getSaleMemo().size());
	}
	
	@Test
	public void testAddProduct_ExistingTypeWithDifferentCost()
	{
		SaleLog log = new SaleLog();
		Sale sale = new Sale("apple", 10.0);
		log.addProduct(sale, 1);
		sale = new Sale("apple", 5.0);
		log.addProduct(sale, 1);
		int qty = log.getSaleLog().get(sale.getProductType()).getTotalCount();
		double cost = log.getSaleLog().get(sale.getProductType()).getTotalPrice();
		assertEquals(1, log.getSaleLog().size());
		assertEquals(2, qty);
		assertEquals(15.0, cost, 0.02);
		assertEquals(2, log.getSaleMemo().size());
	}
	
	@Test
	public void testApplyAdjustment_AddOperator()
	{
		SaleLog log = new SaleLog();
		Sale sale = new Sale("apple", 10.0);
		log.addProduct(sale, 2);
		log.applyAdjustment("apple", "add", 5.0);
		double cost = log.getSaleLog().get("apple").getTotalPrice();
		assertEquals(30.0, cost, 0.02);
		assertEquals(1, log.getAdjustments().size());
	}
	
	@Test
	public void testApplyAdjustment_SubtractOperator()
	{
		SaleLog log = new SaleLog();
		Sale sale = new Sale("apple", 10.0);
		log.addProduct(sale, 2);
		log.applyAdjustment("apple", "subtract", 5.0);
		double cost = log.getSaleLog().get("apple").getTotalPrice();
		assertEquals(10.0, cost, 0.02);
		assertEquals(1, log.getAdjustments().size());
	}
	
	@Test
	public void testApplyAdjustment_MultiplyOperator()
	{
		SaleLog log = new SaleLog();
		Sale sale = new Sale("apple", 10.0);
		log.addProduct(sale, 2);
		log.applyAdjustment("apple", "multiply", 2.0);
		double cost = log.getSaleLog().get("apple").getTotalPrice();
		assertEquals(40.0, cost, 0.02);
		assertEquals(1, log.getAdjustments().size());
	}
}
