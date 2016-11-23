package isti.cnr.sse.rest.data.pojo;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.gson.Gson;

import isti.cnr.sse.util.JsonFactory;

public class MisuratoriFiscaleTest {

	@Test
	public void test() {
		
		JsonFactory factory = new JsonFactory();
		
		MisuratoriFiscale LMF = factory.getMisuratoriFiscale();
		
		Gson gson = new Gson();
		 
		System.out.println(gson.toJson(LMF));
		 
		fail("Not yet implemented");
	}

}
