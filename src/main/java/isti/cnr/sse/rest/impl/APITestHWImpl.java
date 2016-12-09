package isti.cnr.sse.rest.impl;

import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
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
import javax.ws.rs.core.Response;

import org.apache.commons.codec.binary.Base64;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import isti.cnr.sse.rest.data.Allegato;
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

	private static Map<String,Ditta> map = new HashMap<String,Ditta>();

	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(APITestHWImpl.class);




	/*	@Path("/")
	@POST
	public String putListMisuratoriFiscale(@FormParam("sendmisuratori") MisuratoriFiscale misuratori){
		return "OK";

	}*/

	@Path("/ditta/")
	@POST
	public String putDitta(String  d){
		Factory factory = new Factory();
		map = factory.getMap();
		Gson g = new Gson();
		Ditta ditta = g.fromJson(d, Ditta.class);
		if(map.containsKey(ditta)){
			return "Elemento gia presente";
		}
		map.put(ditta.getNomeDitta(), ditta);
		return "OK";

	}

	@Path("/modello/")
	@POST
	public String putModello(String  MF){
		Gson g = new Gson();
		ModelloMF modello =  g.fromJson(MF, ModelloMF.class);
		if(map.containsKey(modello.getNomeDitta())){
			Ditta d = map.get(modello.getNomeDitta());
			if(!d.getMisuratoriFiscali().contains(modello)){
				d.getMisuratoriFiscali().add(modello);
				return "OK";
			}
			return "Modello gia presente";
		}
		return "Ditta non trovata";

	}

	@Path("/provaupdated/")
	@POST
	public String putProveHW(String  Prova){
		//Type listType = new TypeToken<ArrayList<Prova>>(){}.getType();


		Gson g = new Gson();
		//List<Prova> Listprove = g.fromJson(MF, listType);
		Prova prova = g.fromJson(Prova, Prova.class);
		String d = prova.getNomeDitta();
		if(map.containsKey(d) ){
			Ditta ditta = map.get(d);
			int index = ditta.getMisuratoriFiscali().indexOf(new ModelloMF(prova.getNomeModello(),prova.getNumeroRapportoProva() ,prova.getNomeDitta(),new Date()));
			if(index>=0){
				ModelloMF modello = ditta.getMisuratoriFiscali().get(index);
				int ind = modello.getProve().indexOf(prova);
				if(ind>=0){
					Prova pp = modello.getProve().get(ind);
					//TODO: cambiare dove salvare i file 
					saveallegati(prova.getListallegato());
					pp.setStato(prova.getStato());
					pp.setNote(prova.getNote());
					pp.getListallegato().addAll(prova.getListallegato());
					System.out.println("");
				}
			}
		}

		return "OK";

	}

	@Path("/listprovaupdated/")
	@POST
	public String putlistProveHW(String  listProva){
		Type listType = new TypeToken<ArrayList<Prova>>(){}.getType();


		Gson g = new Gson();
		List<Prova> Listprove = g.fromJson(listProva, listType);

		for(Prova prova: Listprove){

			String d = prova.getNomeDitta();
			if(map.containsKey(d) ){
				Ditta ditta = map.get(d);
				int index = ditta.getMisuratoriFiscali().indexOf(new ModelloMF(prova.getNomeModello(),prova.getNumeroRapportoProva() ,prova.getNomeDitta(),new Date()));
				if(index>=0){
					ModelloMF modello = ditta.getMisuratoriFiscali().get(index);
					int ind = modello.getProve().indexOf(prova);
					if(ind>=0){
						Prova pp = modello.getProve().get(ind);
						//TODO: cambiare dove salvare i file 
						saveallegati(prova.getListallegato());
						pp.setStato(prova.getStato());
						pp.setNote(prova.getNote());
						pp.getListallegato().addAll(prova.getListallegato());
						System.out.println("");
					}
				}
			}
		}

		return "OK";

	}


	@Path("/misuratoriupdated/")
	@POST
	public String putMisuratori(String  misuratori){
		Gson g = new Gson();
		Type listType = new TypeToken<ArrayList<ModelloMF>>(){}.getType();
		List<ModelloMF> Listmf = g.fromJson(misuratori, listType);

		return "OK";
	}


	private void saveallegati(List<Allegato> listallegato) {
		for (Allegato allegato : listallegato) {
			String nome = allegato.getNome();
			String num = allegato.getNumeroRapportoProva();
			String datibase64 = allegato.getDati();
			allegato.setDati("");
			saveFile(nome,num, datibase64);
			allegato.setUrl("http://localhost:9090/cnr/sse/testhw/allegati/"+num+"/"+nome);
		}

	}

	private void saveFile(String nome, String num, String datibase64) {
		// TODO Auto-generated method stub
		try{
			File theDir = new File("../allegati/"+num);

			// if the directory does not exist, create it
			if (!theDir.exists()) {
				theDir.mkdirs();
			}


			byte[] imageByteArray = Base64.decodeBase64(datibase64);

			FileOutputStream imageOutFile = new FileOutputStream("../allegati/"+num+"/"+nome);
			imageOutFile.write(imageByteArray);
			imageOutFile.flush();
			imageOutFile.close();

		} catch (IOException ioe) {
			System.out.println("Exception while reading the Image " + ioe);
		}

	}

	@Path("/dittestring/")
	@GET
	public String getDitteName(){


		Collection<String> d = map.keySet();
		Gson g = new Gson();
		return  g.toJson(d);


	}


	@Path("/ditte/{howmany:.*}")
	@GET
	public String getListDitte(@PathParam("howmany") String numeroelementi){
		try{
			if(numeroelementi.equals("all")){			
				/**MisuratoriFiscale MFS = new MisuratoriFiscale();
				MFS.setMap(map);
				return  MFS;*/
			}
			//	if(numeroelementi.equals("test")){			


			Ditte d = new Ditte();
			d.setListaDitte(new ArrayList<>(map.values()));
			Gson g = new Gson();
			return  g.toJson(d);
			//	}
			//	return null;
		}catch(Exception e){
			log.fatal("Fatal "+e.getMessage());
			return null;
		}


	}

	@Path("/stat/")
	@GET
	public String getStat(){
		try{


			Ditte d = new Ditte();
			d.setListaDitte(new ArrayList<>(map.values()));

			Gson g = new Gson();
			return  g.toJson(d.getStat());

		}catch(Exception e){
			log.fatal("Fatal "+e.getMessage());
			return null;
		}
	}



	@Path("/misuratorifiscali/{howmany:.*}")
	@GET
	public String getListMisuratori(@PathParam("howmany") String numeroelementi){
		try{
			if(numeroelementi.equals("all")){			
				/**MisuratoriFiscale MFS = new MisuratoriFiscale();
				MFS.setMap(map);
				return  MFS;*/
			}
			//Factory factory = new Factory();
			//map = factory.getMap();
			//	if(numeroelementi.equals("test")){			
			List<ModelloMF> lmf = new ArrayList<>();
			for(Ditta d : map.values()){
				lmf.addAll(d.getMisuratoriFiscali());
			}
			Gson g = new Gson();
			return  g.toJson(lmf);
			//	}
			//	return null;
		}catch(Exception e){
			log.fatal("Fatal "+e.getMessage());
			return null;
		}


	}

	@Path("/allegati/{id:.*}/{nome:.*}")
	@GET
	public Response getAllegato(@PathParam("id") String id, @PathParam("nome") String nome){

		//InputStream is = APITestHWImpl.class.getClassLoader().getResourceAsStream("mini.jpg");
		try{
			File  imageOutFile = new File("../allegati/"+id+"/"+nome);
			InputStream targetStream = new FileInputStream(imageOutFile);

			return Response
					.ok(targetStream, MediaType.APPLICATION_OCTET_STREAM)
					.header("content-disposition","attachment; filename = "+nome)
					.build();
		}catch (Exception e) {
			//TODO: non ce il file
			System.out.println("");
		}
		return Response.noContent().build();

	}

	@Path("/modellistring/{ditta:.*}")
	@GET
	public String getModelliString(@PathParam("ditta") String ditta){
		try{



			Ditta d = map.get(ditta);
			List<String> modelli =  d.getMFString();
			Gson g = new Gson();
			return  g.toJson(modelli);
			//	}
			//	return null;
		}catch(Exception e){
			log.fatal("Fatal "+e.getMessage());
			return null;
		}
	}

	@Path("/modello/{ditta:.*}/{modello:.*}")
	@GET
	public String getMisuratore(@PathParam("ditta") String ditta, @PathParam("modello") String modello){
		try{



			Ditta d = map.get(ditta);
			int index = d.getMisuratoriFiscali().indexOf(new ModelloMF(modello, "", ditta, ""));
			if(index>=0){
				Gson g = new Gson();
				return  g.toJson(d.getMisuratoriFiscali().get(index));
			}
			return "KO";
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
