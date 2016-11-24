package isti.cnr.sse.rest.impl;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

//import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import isti.cnr.sse.rest.APIProveHW;
import isti.cnr.sse.rest.data.pojo.MisuratoreFiscale;
import isti.cnr.sse.rest.data.pojo.MisuratoriFiscale;
import isti.cnr.sse.util.JsonFactory;
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/sse/provehw/datacontent")
public class APIProveHWImpl implements APIProveHW{
	
	//@Inject 
	//TokenPersistence em;

	private static Map<String,MisuratoreFiscale> map = new HashMap<String,MisuratoreFiscale>();

	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(APIProveHWImpl.class);



/*	@Path("/")
	@POST
	public String putListMisuratoriFiscale(@FormParam("sendmisuratori") MisuratoriFiscale misuratori){
		return "OK";
		
	}*/
	

	@Path("/misuratori/{howmany:.*}")
	@GET
	public MisuratoriFiscale getListMisuratoriFiscale(@PathParam("howmany") String numeroelementi){
		try{
			if(numeroelementi.equals("all")){			
				MisuratoriFiscale MFS = new MisuratoriFiscale();
				MFS.setMap(map);
				return  MFS;
			}
			if(numeroelementi.equals("test")){			
				JsonFactory factory = new JsonFactory();
				
				MisuratoriFiscale LMF = factory.getMisuratoriFiscale();
				return  LMF;
			}
			return null;
		}catch(Exception e){
			log.fatal("Fatal "+e.getMessage());
			return null;
		}
		
		
	}
	
	@Path("/misuratori2/{howmany:.*}")
	@GET
	public String getListMisuratoriFiscale2(@PathParam("howmany") String numeroelementi){
		try{
			
			if(numeroelementi.equals("test")){			
				JsonFactory factory = new JsonFactory();
				
				MisuratoriFiscale LMF = factory.getMisuratoriFiscale();
				Gson gson = new Gson();
				 
				
				return  gson.toJson(LMF);
			}
			return null;
		}catch(Exception e){
			log.fatal("Fatal "+e.getMessage());
			return null;
		}
		
		
	}
	
	@Path("/misuratore/{matricola:.*}")
	@GET
	public MisuratoreFiscale getStatusCollaborativeContentVerifications(@PathParam("matricola") String matricola){
		try{
			if(map.containsKey(Integer.valueOf(matricola))){
				return  map.get(Integer.valueOf(matricola));
			}
			return null;
		}catch(Exception e){
			log.fatal("Fatal "+e.getMessage());
			return null;
		}
		
	}


}
