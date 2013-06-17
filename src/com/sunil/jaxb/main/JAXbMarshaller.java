package com.sunil.jaxb.main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.sunil.schema.classes.Items;
import com.sunil.schema.classes.Items.Item;
import com.sunil.schema.classes.ObjectFactory;
import com.sunil.schema.classes.PurchaseOrderType;
import com.sunil.schema.classes.USAddress;

public class JAXbMarshaller {
	
	public static void main(String[] args) {
		
		//create billTo object
		USAddress billTo = new USAddress();
		billTo.setName("Name1");
		billTo.setStreet("XYZ");
		billTo.setCountry("USA");
		billTo.setZip(new BigDecimal(12345));
		
		
		//create shipTo object
		USAddress shipTo = new USAddress();
		shipTo.setName("Name2");
		shipTo.setStreet("abc");
		shipTo.setCountry("USA");
		shipTo.setZip(new BigDecimal(56789));
		
		
		//create items
		Items.Item item1 = new Item();
		item1.setProductName("Item1");
		item1.setPartNum("12112");
		item1.setQuantity(2);
		item1.setComment("Sample Part1");
		
		
		Items.Item item2 = new Item();
		item2.setProductName("Item2");
		item2.setPartNum("12115");
		item2.setQuantity(5);
		item2.setComment("Sample Part2");
		
		//create Items Object
		Items items = new Items();
		List<Items.Item> itemList = items.getItem();
		itemList.add(item1);
		itemList.add(item2);
		
		
		//add item1 and item2 to items list
		
		PurchaseOrderType purchaseOrder = new PurchaseOrderType();
		
	
		
		purchaseOrder.setItems(items);
		purchaseOrder.setBillTo(billTo);
		purchaseOrder.setShipTo(shipTo);
		
		try {
			//create JAXBContext
			JAXBContext context = JAXBContext.newInstance("com.sunil.schema.classes");
			
			//create Marshaller from context
			Marshaller marshaller = context.createMarshaller();
			
			marshaller.marshal(new ObjectFactory().createPurchaseOrder(purchaseOrder), new FileOutputStream("src/output.xml"));
		
			
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
