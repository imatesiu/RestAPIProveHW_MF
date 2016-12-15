package isti.cnr.sse.rest.impl;

import static org.junit.Assert.*;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
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

import isti.cnr.sse.rest.AuthenticationFilter;
import isti.cnr.sse.rest.data.Ditte;

public class APITestHWImplTest extends JerseyTest {
	

	@Override
	protected TestContainerFactory getTestContainerFactory()  {
		return new GrizzlyWebTestContainerFactory();
	}

	@Override
	protected DeploymentContext configureDeployment() {
		forceSet(TestProperties.CONTAINER_PORT, "0");
		return ServletDeploymentContext.forServlet(new ServletContainer(new ResourceConfig(APITestHWImpl.class,  AuthenticationFilter.class)))
				.build();

	}

	@Override
	protected Application configure() {
		return new ResourceConfig(APITestHWImpl.class, AuthenticationFilter.class);
	}

	
	
	@Test
	public void test() {
		
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "sse");
	    client().register( feature) ;

		Response response =  target("/cnr/sse/testhw/ditte/test").request(MediaType.APPLICATION_JSON).get();

		String res =	response.readEntity(String.class);
		Gson g = new Gson();
		Ditte misuratoriFiscale  = new Ditte();
		misuratoriFiscale = g.fromJson(res, Ditte.class);
		System.out.println(res);
		
		
		
	}
}
