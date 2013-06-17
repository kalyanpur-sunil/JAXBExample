package com.sunil.jaxb.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.sunil.schema.classes.Items;
import com.sunil.schema.classes.PurchaseOrderType;
import com.sunil.schema.classes.USAddress;

/**
 * This class unmarshal XML file 
 * 
 * @author Sunil Kalyanpur
 *
 */
public class JAXBUnmarshaller {
	
	public static void main(String[] args) {
		try {
			//create JAXBContext
			JAXBContext context = JAXBContext.newInstance("com.sunil.schema.classes");
			
			//create unmarshaller from JAXBContext
			Unmarshaller unmarshaller = context.createUnmarshaller();
			
			//Unmarshal XML data from the specified InputStream and return the resulting content tree.
			JAXBElement<?> contentTree = (JAXBElement<?>) unmarshaller.unmarshal(new FileInputStream("src/po.xml"));
			
			//get PurchaseOrder element (Root element in xml)
			PurchaseOrderType purchaseOrder = (PurchaseOrderType)(contentTree.getValue());
			
			//get billTo address
			USAddress billTo = purchaseOrder.getBillTo();
			System.out.println("Bill to: "+billTo.getName());
			
			//get <Items> element
			Items items = purchaseOrder.getItems();
			
			//get items list from Items
			List<Items.Item>itemList = items.getItem();
			
			//iterate through list and print of element values
			for(Items.Item item : itemList){
				System.out.println(item.getPartNum() +"\t" + item.getProductName() +"\t"+item.getQuantity());
			}

		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
