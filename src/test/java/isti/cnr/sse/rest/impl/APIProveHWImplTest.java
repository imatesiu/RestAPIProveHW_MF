package isti.cnr.sse.rest.impl;

import static org.junit.Assert.*;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.test.DeploymentContext;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.ServletDeploymentContext;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.junit.Test;

import com.google.gson.Gson;

import isti.cnr.sse.rest.data.pojo.MisuratoriFiscale;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class APIProveHWImplTest extends JerseyTest {
	

	@Override
	protected TestContainerFactory getTestContainerFactory()  {
		return new GrizzlyWebTestContainerFactory();
	}

	@Override
	protected DeploymentContext configureDeployment() {
		forceSet(TestProperties.CONTAINER_PORT, "0");
		return ServletDeploymentContext.forServlet(new ServletContainer(new ResourceConfig(APIProveHWImpl.class)))
				.build();

	}

	@Override
	protected Application configure() {
		return new ResourceConfig(APIProveHWImpl.class);
	}

	
	
	@Test
	public void test() {
		
		
		
		

		Response response =  target("/sse/provehw/datacontent/misuratori2/test").request(MediaType.APPLICATION_JSON).get();

		String res =	response.readEntity(String.class);
		Gson g = new Gson();
		MisuratoriFiscale misuratoriFiscale  = new MisuratoriFiscale();
		misuratoriFiscale = g.fromJson(res, MisuratoriFiscale.class);
		System.out.println(misuratoriFiscale);
		
		
		
	}

}
