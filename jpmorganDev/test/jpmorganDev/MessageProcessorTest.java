package jpmorganDev;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class MessageProcessorTest {
		  
	   MessageProcessor processor = new MessageProcessor();
	   @Test
	   public void testGetSingular_WithS()
	   {
		   assertEquals("orange", processor.getSingular("oranges"));
	   }
	   
	   @Test
	   public void testGetSingular_WithES()
	   {
		   assertEquals("mango", processor.getSingular("mangoes"));
	   }
	   
	   @Test
	   public void testSingular_WithIES()
	   {
		   assertEquals("cherry", processor.getSingular("cherries"));
	   }
	   
	   @Test
	   public void testProcessMsg_MSG1()
	   {
		   processor = new MessageProcessor();
		   processor.processMsg("apple at 10p");
		   int size = processor.getSaleLog().getSaleLog().size();
		   assertEquals(1, size);
	   }
	   
	   @Test
	   public void testProcessMsg_MSG2()
	   {
		   processor = new MessageProcessor();
		   processor.processMsg("2 sales of apples at 10p Each");
		   Product p = processor.getSaleLog().getSaleLog().get("apple");
		   int count = p.getTotalCount();
		   double price = p.getTotalPrice();
		   assertEquals(2, count);
		   assertEquals(20.0, price, 0.02);
		   int size = processor.getSaleLog().getSaleLog().size();
		   assertEquals(1, size);
	   }
	   
	   @Test
	   public void testProcessMsg_MSG3()
	   {
		   processor = new MessageProcessor();
		   processor.processMsg("add 20p Apples");
		   assertFalse(processor.getSaleLog().getAdjustments().size()==1);
		   processor.processMsg("apple at 10p");
		   processor.processMsg("add 20p Apples");
		   int size = processor.getSaleLog().getSaleLog().size();
		   assertEquals(1, size);
		   Product p = processor.getSaleLog().getSaleLog().get("apple");
		   int count = p.getTotalCount();
		   double price = p.getTotalPrice();
		   assertEquals(1, count);
		   assertEquals(30.0, price, 0.02);
	   }
}
