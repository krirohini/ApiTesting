package com.apex.service.customer;

import org.testng.annotations.Test;

import com.apex.service.Base;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;

public class CustomerServiceTestNG extends Base {

	@Test
	public void getCustomerSuccess() throws Exception {
		// Get the URL
		String url = "http://www.thomas-bayer.com/sqlrest/CUSTOMER/2";
		
		// Create the Apache HttpClient
		HttpClient client = HttpClientBuilder.create().build();

		// Create the Request Method for Get
		HttpGet request = new HttpGet(url);

		// Send the message
		HttpResponse response = client.execute(request);

		// Validate
			// Status Code
		System.out.println(response.getStatusLine().getStatusCode());
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);

			// Status Message
		System.out.println(response.getStatusLine().getReasonPhrase());
		Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");

		// Data
		// Complete Message
		// Tags, Tags Repetitions, Data

		String result = readingContentOfThePage(response);

		System.out.println(result);
		Assert.assertTrue(result.contains("<CUSTOMER"));
		Assert.assertTrue(result.contains("Anne"));		

	}
	
	//@Test
	public void postCustomerSuccess() throws Exception {
		// Get the URL
		/*String url = "http://www.thomas-bayer.com/sqlrest/CUSTOMER/2";*/
		String url = "http://www.thomas-bayer.com/sqlrest/CUSTOMER/";
		
		// Create the Apache HttpClient
		HttpClient client = HttpClientBuilder.create().build();

		// Create the Request Method for Get
		HttpPost request = new HttpPost(url);

		// Send the message
		HttpResponse response = client.execute(request);

		// Validate
		// Status Code
		System.out.println(response.getStatusLine().getStatusCode());
		Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);

		// Status Message
		System.out.println(response.getStatusLine().getReasonPhrase());
		Assert.assertEquals(response.getStatusLine().getReasonPhrase(), "OK");

		// Data
		// Complete Message
		// Tags, Tags Repetitions, Data

		String result = readingContentOfThePage(response);

		System.out.println(result);
		Assert.assertTrue(result.contains("<CUSTOMER"));
		Assert.assertTrue(result.contains("Anne"));
		
		

	}


}
