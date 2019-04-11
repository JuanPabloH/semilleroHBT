package com.hbt.semillero.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.hbt.semillero.dto.LineaDTO;
import com.hbt.semillero.dto.MarcaDTO;
import com.hbt.semillero.dto.PersonaDTO;
import com.hbt.semillero.dto.ResultadoDTO;
import com.hbt.semillero.entidades.Linea;
import com.hbt.semillero.entidades.Marca;
import com.hbt.semillero.entidades.Persona;
import com.hbt.semillero.servicios.interfaces.IConsultasBeanLocal;

@Path("/ServiciosRest")
public class ServiciosRest {
	
	@EJB
	private IConsultasBeanLocal consultasBean;
	
	
	/**
	 * Metodo que invoca al EJB para obtener las marcas existentes y las retorna en 
	 * una lista de tipo MarcaDTO
	 * */
	@GET
	@Path("/consultarMarcas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MarcaDTO> consultarMarcas(){
		List<Marca> marcas= consultasBean.consultarMarcas();
		List<MarcaDTO> retorno=new ArrayList<>();
		
		for (Marca marca : marcas) {
			MarcaDTO marcaDTO= construirMarcaDTO(marca);
			retorno.add(marcaDTO);
			
		}
		return retorno;
	}
	
	
	/**
	 * Metodo que invoca al EJB para obtener las lineas existentes segun 
	 * la marca y las retorna una lista de tipo LineaDTO
	 * @param idMarca
	 * */
	@GET
	@Path("/consultarLineas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<LineaDTO> consultarLineas(@QueryParam(value="idMarca") Long idMarca){
		List<Linea> lineas= consultasBean.consultarLineas(idMarca);
		List<LineaDTO> retorno= new ArrayList<>();
		for (Linea linea : lineas) {
			LineaDTO lineaDTO= new LineaDTO();
			lineaDTO.setIdLinea(linea.getIdLinea());
			lineaDTO.setCilindraje(linea.getCilindraje());
			lineaDTO.setNombre(linea.getNombre());
			MarcaDTO marcaDTO= construirMarcaDTO(linea.getMarca());
			lineaDTO.setMarca(marcaDTO);
			retorno.add(lineaDTO);
		}
		
		return retorno;
		
	}
	
	/**
	 * Metodo que invoca al EJB para la creacion de una persona, recibe como parametro la
	 * persona que se va a agregar, retorna un objeto de tipo ResultadoDTO para identificar
	 * si hubo exito o no
	 * @param personaDTO
	 * */
	
	@POST
	@Path("/crearPersona")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultadoDTO crearPersona(PersonaDTO personaDTO){
		
		ResultadoDTO retorno= new ResultadoDTO();
		retorno.setExitoso(true);
		try {
			consultasBean.crearPersona(personaDTO);
			
		} catch (Exception e) {
			retorno.setExitoso(false);
			retorno.setMensajeError("No se pudo ingresar la persona");			
		}
		
		return retorno;
		
	}
	
	/**
	 * Metodo que invoca al EJB para la modificacion de una persona, recibe como parametro la
	 * persona que se va a modificar, retorna un objeto de tipo ResultadoDTO para identificar
	 * si hubo exito o no
	 * @param personaDTO
	 * */
	
	@PUT
	@Path("/modificarPersona")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultadoDTO modificarPersona(PersonaDTO personaDTO){
		
		ResultadoDTO retorno= new ResultadoDTO();
		retorno.setExitoso(true);
		try {
			consultasBean.modificarPersona(personaDTO);
			
		} catch (Exception e) {
			retorno.setExitoso(false);
			retorno.setMensajeError("No se pudo modificar la persona");			
		}
		
		return retorno;
		
	}
	
	/**
	 * Metodo que invoca al EJB para la consulta de una persona, recibe como parametros el numero de
	 * identificacion y el tipo de identificacion de la persona a consultar
	 * Retorna una lista de tipo Persona
	 * @param numeroIdentificacion
	 * @param tipoIdentificacion
	 * */
	@GET
	@Path("/consultarPersonas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PersonaDTO> consultarPersona(@QueryParam(value="numeroIdentificacion") String numeroIdentificacion,@QueryParam(value="tipoIdentificacion") String tipoIdentificacion){
		List<Persona> personas= consultasBean.consultarPersona(numeroIdentificacion,tipoIdentificacion);
		List<PersonaDTO> retorno= new ArrayList<>();
		for (Persona persona : personas) {
			PersonaDTO personaDTO= new PersonaDTO();
			
			personaDTO.setNumeroIdentificacion(persona.getNumeroIdentificacion());
			personaDTO.setTipoIdentificacion(persona.getTipoIdentificacion());
			personaDTO.setNumeroTelefonico(persona.getNumeroTelefonico());
			personaDTO.setNombres(persona.getNombres());
			personaDTO.setApellidos(persona.getApellidos());
			personaDTO.setEdad(persona.getEdad());
		
			retorno.add(personaDTO);
		}
		
		return retorno;
		
	}
	
	/**
	 * metodo que permite la creacion de un objeto de tipo MarcaDTO
	 * @param marca
	 * */
	
	private MarcaDTO construirMarcaDTO(Marca marca) {
		MarcaDTO marcaDTO= new MarcaDTO();
		marcaDTO.setIdMarca(marca.getIdMarca());
		marcaDTO.setNombre(marca.getNombre());
		return marcaDTO;
	}
	
	
	
	
}
