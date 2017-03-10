package com.apex.service.customer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class CustomerServiceTest {

	public static void main(String[] args) throws Exception {
		
		// Get the URL
		String url = "http://www.thomas-bayer.com/sqlrest/CUSTOMER/2";
		
		// Create the Apache HttpClient
		HttpClient client = HttpClientBuilder.create().build();
		
		// Create the Request object for GET method
		HttpGet request = new HttpGet(url);
		
		// Send the message 
		HttpResponse response = client.execute(request);
		
		System.out.println("Hello");
		// Validate
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
			System.out.println(result.contains("Anne"));
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
