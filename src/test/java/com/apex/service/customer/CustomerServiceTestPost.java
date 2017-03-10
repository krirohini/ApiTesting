package com.apex.service.customer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.apex.service.bean.Customer;

public class CustomerServiceTestPost {

	public static void main(String[] args) throws Exception {
		
		// Create the Customer Object
		Customer customer = new Customer();
		customer.setId(66666);
		customer.setFirstName("Rohini");
		customer.setLastName("Kumari");
		customer.setCity("San Jose");
		customer.setStreet("Hamilton Ave");
		
		StringWriter writer = new StringWriter();
		JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(customer, writer);
		jaxbMarshaller.marshal(customer, System.out);
		
		// Get the URL
		//String url = "http://www.thomas-bayer.com/sqlrest/CUSTOMER/";
		
		// Create the Apache HttpClient
		HttpClient client = HttpClientBuilder.create().build();
		
		// Create the Request object for POST Method 
		//HttpPost request = new HttpPost(url);
		HttpPost request = new HttpPost("http://www.thomas-bayer.com/sqlrest/CUSTOMER/");
		
		/*StringEntity input = new StringEntity("{ \"ID\":333, \"FIRSTNAME\":\"Rohini, \"LASTNAME\":\"Kumari, \" }");
		input.setContentType("application/json");
		request.setEntity(input);*/
		
		request.addHeader("content-type", "application/xml");
		StringEntity postCustomerData = new StringEntity(writer.getBuffer().toString());
		request.setEntity(postCustomerData);
		

		HttpResponse response = client.execute(request);
		
		
		if (response.getStatusLine().getStatusCode() != 201) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatusLine().getStatusCode());
		}

		BufferedReader br = new BufferedReader(
                        new InputStreamReader((response.getEntity().getContent())));
		
		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}

		
		
		// Send the message
		//HttpResponse response = client.execute(request);
		
		System.out.println("Hello");
		/*// Validate
			// Status Code		
			System.out.println(response.getStatusLine().getStatusCode());
			// Status Message
			System.out.println(response.getStatusLine().getReasonPhrase());
			// Data
				// Complete Message
				// Tags, Tags Repetitions, Data		
			
			String result = readingContentOfThePage(response);
			
			System.out.println(result);
			System.out.println(result.contains("<CUSTOMER"));
			System.out.println(result.contains("Anne"));*/
	}

	private static String readingContentOfThePage(HttpResponse response) throws IOException {
		BufferedReader rd = new BufferedReader(
		        				new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		return result.toString();
	}
	
}
