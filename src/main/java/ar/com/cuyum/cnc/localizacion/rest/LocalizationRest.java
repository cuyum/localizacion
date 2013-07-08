/**
 * 
 */
package ar.com.cuyum.cnc.localizacion.rest;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import ar.com.cuyum.cnc.localizacion.model.Acceso;
import ar.com.cuyum.cnc.localizacion.model.Area;
import ar.com.cuyum.cnc.localizacion.model.Localidad;
import ar.com.cuyum.cnc.localizacion.model.Partido;
import ar.com.cuyum.cnc.localizacion.model.Prestador;
import ar.com.cuyum.cnc.localizacion.model.Provincia;
import ar.com.cuyum.cnc.localizacion.service.AccesosService;
import ar.com.cuyum.cnc.localizacion.service.LocalizacionService;
import ar.com.cuyum.cnc.localizacion.service.PrestadoresService;
import ar.com.cuyum.cnc.localizacion.vo.ListObject;

/**
 * @author Jorge Morando
 *
 */
@Path("/localizaciones")
@RequestScoped
public class LocalizationRest {

	private Logger log = Logger.getLogger(LocalizationRest.class);

	@Inject
	LocalizacionService localizacionService;
	
	@Inject
	PrestadoresService prestadoresService;
	
	@Inject
	AccesosService accesosService;
	
	@POST
	@Path("/provincias")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=UTF-8")
	public RestResponse provincias(@QueryParam("term") String term, @QueryParam("limit") Integer limit, @QueryParam("page") Integer page) {
		/*devolver un json valido como string*/
		RestResponse response = new RestResponse();
		List<Provincia> listaProv = new ArrayList<Provincia>();
		List<ListObject> results = new ArrayList<ListObject>();
		
		try {
			if(limit==null){
				listaProv = localizacionService.buscarProvincias(term);		
			}else{
				page=page==null?1:page;
				listaProv = localizacionService.buscarProvincias(term,limit,page);	
				response.setTotal(localizacionService.contarProvincias(term));
			}
			if(listaProv!=null && listaProv.size()>0){
				for (Provincia prov : listaProv) {
					results.add(new ListObject(prov.getId().toString(),prov.getNombre()));
				}
			}
			response.setResult(results);
			response.setSuccess(true);
		} catch (Exception e) {
			response.setSuccess(false);
			log.error(e.getMessage());
		}		
		return response;
	}
	
	@POST
	@Path("/partidos")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=UTF-8")
	public RestResponse partidos(@FormParam("fkey") String fkey,
			@QueryParam("term") String term, @QueryParam("limit") Integer limit, @QueryParam("page") Integer page) {
		/*devolver un json valido como string*/
		RestResponse response = new RestResponse();
		List<Partido> listaPart = new ArrayList<Partido>();
		List<ListObject> results = new ArrayList<ListObject>();
		
		try {
			if(fkey==null || fkey.isEmpty()){
				response.setSuccess(false);
				response.setMsg("fkey invalid");
				response.setResult(results);
				return response;
			}
			if(limit==null){
				listaPart = localizacionService.buscarPartidos(Long.valueOf(fkey),term);		
			}else{
				page=page==null?1:page;
				listaPart = localizacionService.buscarPartidos(Long.valueOf(fkey),term,limit,page);	
				response.setTotal(localizacionService.contarPartidos(Long.valueOf(fkey),term));
			}
			if(listaPart!=null && listaPart.size()>0){
				for (Partido partido : listaPart) {
					results.add(new ListObject(partido.getId().toString(),partido.getNombre()));
				}
			}
			response.setResult(results);
			response.setSuccess(true);
		} catch (Exception e) {
			response.setSuccess(false);
			log.error(e.getMessage());
		}
		return response;
	}
	
	@POST
	@Path("/localidades")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=UTF-8")	
	public RestResponse localidades(@FormParam("fkey") String fkey,
			@QueryParam("term") String term, @QueryParam("limit") Integer limit, @QueryParam("page") Integer page) {
		/*devolver un json valido como string*/
		RestResponse response = new RestResponse();
		List<Localidad> listaLocal = new ArrayList<Localidad>();
		List<ListObject> results = new ArrayList<ListObject>();
		
		try {
			if(fkey==null || fkey.isEmpty()){
				response.setSuccess(false);
				response.setResult(results);
				response.setMsg("fkey invalid");
				return response;
			}
			if(limit==null){
				listaLocal = localizacionService.buscarLocalidades(Long.valueOf(fkey),term);		
			}else{
				page=page==null?1:page;
				listaLocal = localizacionService.buscarLocalidades(Long.valueOf(fkey),term,limit,page);	
				response.setTotal(localizacionService.contarLocalidades(Long.valueOf(fkey),term));
			}
			if(listaLocal!=null && listaLocal.size()>0){
				for (Localidad localidad : listaLocal) {
					results.add(new ListObject(localidad.getId().toString(),localidad.getNombre()));
				}
			}
			response.setResult(results);
			response.setSuccess(true);
		} catch (Exception e) {
			response.setSuccess(false);
			log.error(e.getMessage());
		}
		return response;
	}
	
	@POST
	@Path("/areas")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=UTF-8")
	public RestResponse areas(@FormParam("fkey") String fkey,
			@QueryParam("term") String term, @QueryParam("limit") Integer limit, @QueryParam("page") Integer page) {
		/*devolver un json valido como string*/
		RestResponse response = new RestResponse();
		List<Area> listaAreas = new ArrayList<Area>();
		List<ListObject> results = new ArrayList<ListObject>();

		try {
			if(fkey==null || fkey.isEmpty()){
				response.setSuccess(false);
				response.setResult(results);
				response.setMsg("fkey invalid");
				return response;
			}
			if(limit==null){
				listaAreas = localizacionService.buscarAreas(Long.valueOf(fkey),term);		
			}else{
				page=page==null?1:page;
				listaAreas = localizacionService.buscarAreas(Long.valueOf(fkey),term,limit,page);	
				response.setTotal(localizacionService.contarAreas(Long.valueOf(fkey),term));
			}
			if(listaAreas!=null && listaAreas.size()>0){
				for (Area area : listaAreas) {
					results.add(new ListObject(area.getId().toString(),area.getNombre()));
				}
			}
			response.setResult(results);
			response.setSuccess(true);
			
		} catch (Exception e) {
			response.setSuccess(false);
			log.error(e.getMessage());
		}	
		return response;
	}
	
	@POST
	@Path("/prestadores")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=UTF-8")
	public RestResponse prestadores(
			@QueryParam("term") String term, @QueryParam("limit") Integer limit, @QueryParam("page") Integer page) {		
		RestResponse response = new RestResponse();
		List<Prestador> lstPrestadores;
		List<ListObject> results = new ArrayList<ListObject>();
		try {
			if(limit==null){
				lstPrestadores = prestadoresService.buscarPrestadores(term);		
			}else{
				page=page==null?1:page;
				lstPrestadores = prestadoresService.buscarPrestadores(term,limit,page);	
				response.setTotal(prestadoresService.contarPrestadores(term));
			}
			if(lstPrestadores!=null && lstPrestadores.size()>0){
				for (Prestador prestador : lstPrestadores) {
					results.add(new ListObject(prestador.getId(),prestador.getNombre()));
				}
			}
			response.setResult(results);
			response.setSuccess(true);
		} catch (Exception e) {
			response.setSuccess(false);
			log.error(e.getMessage());
		}		
		return response;
	}
	
	@POST
	@Path("/accesos")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=UTF-8")	
	public RestResponse accesos(@FormParam("fkey") String fkey,
			@QueryParam("term") String term, @QueryParam("limit") Integer limit, @QueryParam("page") Integer page) {
		RestResponse response = new RestResponse();
		List<Acceso> lstAccesos = new ArrayList<Acceso>();
		List<Acceso> results = new ArrayList<Acceso>();
		try {
			if(fkey==null || fkey.isEmpty()){
				response.setSuccess(false);
				response.setResult(results);
				response.setMsg("fkey invalid");
				return response;
			}
			
			lstAccesos = accesosService.buscarAccesosPorIdServicio(fkey);
			response.setSuccess(true);
		} catch (Exception e) {
			response.setSuccess(false);
			log.error(e.getMessage());
		}
		response.setResult(lstAccesos);
		return response;
	}
	
}
