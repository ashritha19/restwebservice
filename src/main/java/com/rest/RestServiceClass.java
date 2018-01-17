package com.rest;
 
import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.util.Customer;
import com.util.JsonVo;
 
@Path("/RESTClass")
public class RestServiceClass {
 
	@GET
	@Path("/{param}")
	public Response getMethodWithParam(@PathParam("param") String msg) {
 
		String output = "REST Application - get method with param : " + msg;
		return Response.status(200).entity(output).build();
 
	}
	
	@GET
	@Path("/getmethod/{param}")
	public Response getMethod(@PathParam("param") String msg) {
		
		String output = "REST Application - get method - URL with param : " + msg;
		return Response.status(200).entity(output).build();
		
	}

	@GET
	@Path("/getString")
	@Produces(MediaType.APPLICATION_JSON) // Send Data
	public Response getStringJSON() {
		
		JsonVo jsonVo = new JsonVo();
		jsonVo.setName("Babu");
		jsonVo.setAddress("Coimbatore");
		
		return Response.status(200).entity(jsonVo.toString()).build();
	}
	

	@POST
	@Path("/postString")
	@Consumes(MediaType.APPLICATION_JSON) // Receive Data and Send
	public Response postStringJSON(String jsonVo) {

		String result = "JsonVo saved : " + jsonVo.toString();
		return Response.status(200).entity(result).build();
		
	}
	
	
	@GET
	@Path("/getObject/{cusNo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getObject(@PathParam("cusNo") int no) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		Customer cust = new Customer();        
		cust .setCustNo(no);
        cust .setCustName("Java4s");
        cust .setCustCountry("India");
		String jsonInString = null;
		try {
			jsonInString = mapper.writeValueAsString(cust);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
        return Response.status(200).entity(jsonInString).build();
	}
	

	@POST
	@Path("/postObject")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postObject(String reqParam) {

		ObjectMapper mapper = new ObjectMapper();
		JsonVo jsonVo = new JsonVo();
		try {
			jsonVo = mapper.readValue(reqParam, JsonVo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String result = "JsonVo saved : " + jsonVo.toString();
		return Response.status(200).entity(result).build();
		
	}

}