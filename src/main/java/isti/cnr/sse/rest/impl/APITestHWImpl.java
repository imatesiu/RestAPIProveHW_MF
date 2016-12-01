package isti.cnr.sse.rest.impl;

import java.util.Map;
import java.lang.reflect.Type;
import java.util.ArrayList;
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
import com.google.gson.reflect.TypeToken;

import isti.cnr.sse.rest.data.Ditta;
import isti.cnr.sse.rest.data.Ditte;
import isti.cnr.sse.rest.data.Factory;
import isti.cnr.sse.rest.data.ModelloMF;
import isti.cnr.sse.rest.data.Prova;
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/cnr/sse/testhw")
public class APITestHWImpl{
	
	//@Inject 
	//TokenPersistence em;

	//private static Map<String,MisuratoreFiscale> map = new HashMap<String,MisuratoreFiscale>();

	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(APITestHWImpl.class);



/*	@Path("/")
	@POST
	public String putListMisuratoriFiscale(@FormParam("sendmisuratori") MisuratoriFiscale misuratori){
		return "OK";
		
	}*/
	
	@Path("/ditta/")
	@POST
	public String putDitta(String  ditta){
		Gson g = new Gson();
		g.fromJson(ditta, Ditta.class);
		return "OK";
		
	}
	
	@Path("/modello/")
	@POST
	public String putModello(String  MF){
		Gson g = new Gson();
		g.fromJson(MF, ModelloMF.class);
		return "OK";
		
	}
	
	@Path("/prove/")
	@POST
	public String putProveHW(String  MF){
		Type listType = new TypeToken<ArrayList<Prova>>(){}.getType();

		
		Gson g = new Gson();
		List<Prova> yourClassList = g.fromJson(MF, listType);
		return "OK";
		
	}
	

	@Path("/ditte/{howmany:.*}")
	@GET
	public String getListMisuratoriFiscale(@PathParam("howmany") String numeroelementi){
		try{
			if(numeroelementi.equals("all")){			
				/**MisuratoriFiscale MFS = new MisuratoriFiscale();
				MFS.setMap(map);
				return  MFS;*/
			}
		//	if(numeroelementi.equals("test")){			
				Factory factory = new Factory();
				
				Ditte d = factory.getDitte();
				Gson g = new Gson();
				return  g.toJson(d);
		//	}
		//	return null;
		}catch(Exception e){
			log.fatal("Fatal "+e.getMessage());
			return null;
		}
		
		
	}
	
/*	@Path("/misuratori2/{howmany:.*}")
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
		
	}*/


}
