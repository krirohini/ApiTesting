package com.apex.service.sample;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.apex.service.bean.Customer;

public class JAXBSample {

	public static void main(String[] args)  {
		
		// Marshelling 
		// Java Object to XML
		// Create Object 
		Customer customer = new Customer();
		customer.setId(10);
		customer.setFirstName("John");
		customer.setLastName("xyz");
		customer.setStreet("Fremont Blvd");
		customer.setCity("Fremont");
		
		// Location of the file to write after marshaling
		final String mytest = "c://Rohini/mytestmarshall.txt";
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(mytest));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Now, use the Parser to marshal
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(Customer.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			
			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);		
			jaxbMarshaller.marshal(customer, System.out);
			
			// To write in the file "mytestmarshall.txt"
			//jaxbMarshaller.marshal(customer, mytest);
			
			System.out.println("=========================");
			// To get the data in the String Format
			StringWriter writer = new StringWriter();
			jaxbMarshaller.marshal(customer, writer);
			System.out.println("In String Format...\n" + writer.toString());
			System.out.println("");
			
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
