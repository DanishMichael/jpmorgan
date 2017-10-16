package jpmorganDev;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {
	
	public static void main(String[] args) {
	
		try
		{
			Scanner scan = new Scanner(new File("input/input.txt"));
			MessageProcessor processor  = new MessageProcessor();
			int msgCount = 0;
			while(scan.hasNextLine())
			{
				if(msgCount<50)
				{
					processor.processMsg(scan.nextLine());
					msgCount = msgCount+1;
					if(msgCount%10==0)
					{
						processor.getSaleLog().printSaleLog();
					}
				}
				else
				{
					System.out.println("==========================application pausing=========================");
					processor.getSaleLog().printAdjustments();
					System.exit(0);
				}
			}
			scan.close();	
		}
		catch(FileNotFoundException fe)
		{
			System.out.println("Please place the input file in jpmorganDev/input");
		}
	}
}
