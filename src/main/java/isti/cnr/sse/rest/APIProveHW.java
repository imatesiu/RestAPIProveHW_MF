package isti.cnr.sse.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import isti.cnr.sse.rest.data.pojo.MisuratoreFiscale;
import isti.cnr.sse.rest.data.pojo.MisuratoriFiscale;
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)


@Path("/sse/provehw/datacontent")
public interface APIProveHW {
	
	
	
	@Path("/")
	@POST
	String putListMisuratoriFiscale(@FormParam("sendmisuratori") MisuratoriFiscale misuratori);
	

	@Path("/{howmany:.*}")
	@GET
	MisuratoriFiscale getListMisuratoriFiscale(@PathParam("howmany") String numeroelementi);
	
	@Path("/misuratore/{matricola:.*}")
	@GET
	MisuratoreFiscale getStatusCollaborativeContentVerifications(@PathParam("matricola") String matricola);


}
