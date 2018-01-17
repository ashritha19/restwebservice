package com.rest;

import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.util.JsonVo;


public class GetExampleClient {
	
	public static final String BASE_URI = "http://localhost:8080/RESTfulExample/rest";
    public static final String CLASS_PATH_NAME = "/RESTClass/";
    public static final String method_NAME = "/getmethod/";
    public static void main(String[] args) {
    	
    	
        String name = "Arun";
        String address = "Mumbai";
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource resource1 = client.resource(BASE_URI);
        // >>>>>>>>> 1
        WebResource nameResource1 = resource1.path(CLASS_PATH_NAME + name);
        System.out.println("Client Response \n" + getClientResponse(nameResource1));
        System.out.println("Response output : \n" + getResponse(nameResource1) + "\n\n");
        // >>>>>>>>> 2
        WebResource ageResource2 = resource1.path(CLASS_PATH_NAME).path(method_NAME + address);
        System.out.println("Client Response \n" + getClientResponse(ageResource2));
        System.out.println("Response output : \n" + getResponse(ageResource2));
        
        
        // >>>>>>>>> 3
        WebResource webResource3 = client.resource("http://localhost:8080/RESTfulExample/rest/RESTClass/getString");
        
        ClientResponse response3 = webResource3.accept("application/json").get(ClientResponse.class);
		if (response3.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "+ response3.getStatus());
		}
		String output3 = response3.getEntity(String.class);
		System.out.println();
		System.out.println("Client Response \n" + response3);
		System.out.println("Response output : \n" + output3);

		
		// >>>>>>>>> 4
		WebResource webResource4 = client.resource("http://localhost:8080/RESTfulExample/rest/RESTClass/postString");

		String input4 = "{\"name\":\"Sudhakar\",\"address\":\"USA\"}";
		
		JsonVo jsonVo4 = new JsonVo();
		jsonVo4.setName("Sudhakar");
		jsonVo4.setAddress("USA");
		
		ClientResponse response4 = webResource4.type("application/json").post(ClientResponse.class, jsonVo4.toString());
		if (response4.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "+ response4.getStatus());
		}
		String output4 = response4.getEntity(String.class);
		System.out.println();
		System.out.println("Client Response \n" + response4);
		System.out.println("Response output : \n" + output4);
		
		
		// >>>>>>>>> 5
		

		ObjectMapper mapper5 = new ObjectMapper();
		JsonVo jsonVo5 = new JsonVo();
		jsonVo5.setName("Sudhakar");
		jsonVo5.setAddress("USA");
		String jsonInString5 = null;
		try {
			jsonInString5 = mapper5.writeValueAsString(jsonVo5);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		WebResource webResource5 = client.resource("http://localhost:8080/RESTfulExample/rest/RESTClass/getObject/100");
		
		ClientResponse response5 = webResource5.accept("application/json").get(ClientResponse.class);
		if (response5.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "+ response5.getStatus());
		}
		String output5 = response5.getEntity(String.class);
		System.out.println();
		System.out.println("Client Response \n" + response5);
		System.out.println("Response output : \n" + output5);
		
		// >>>>>>>>> 6
		WebResource webResource6 = client.resource("http://localhost:8080/RESTfulExample/rest/RESTClass/postObject");

		ObjectMapper mapper6 = new ObjectMapper();
		JsonVo jsonVo6 = new JsonVo();
		jsonVo6.setName("Sudhakar Babu Ravichandran");
		jsonVo6.setAddress("USA");
		String jsonInString6 = null;
		try {
			jsonInString6 = mapper6.writeValueAsString(jsonVo6);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		ClientResponse response6 = webResource6.type("application/json").post(ClientResponse.class, jsonInString6);
		if (response6.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "+ response6.getStatus());
		}
		String output6 = response6.getEntity(String.class);
		System.out.println();
		System.out.println("Client Response \n" + response6);
		System.out.println("Response output : \n" + output6);
		
    }
    /**
     * Returns client response as:- GET http://localhost:8083/Restful-web-service/rest/GetExampleService/name/Arun
     * @param service
     * @return
     */
    private static String getClientResponse(WebResource resource) {
        return resource.accept(MediaType.TEXT_HTML).get(ClientResponse.class).toString();
    }
    /**
     * Returns the response as HTML
     * @param service
     * @return
     */
    private static String getResponse(WebResource resource) {
        return resource.accept(MediaType.TEXT_HTML).get(String.class);
    }

}
