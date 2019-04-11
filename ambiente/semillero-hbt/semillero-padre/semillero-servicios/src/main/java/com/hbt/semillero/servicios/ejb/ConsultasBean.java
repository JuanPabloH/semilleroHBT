package com.hbt.semillero.servicios.ejb;
import java.util.*;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.hbt.semillero.dto.PersonaDTO;
import com.hbt.semillero.entidades.Comprador;
import com.hbt.semillero.entidades.Linea;
import com.hbt.semillero.entidades.Marca;
import com.hbt.semillero.entidades.Persona;
import com.hbt.semillero.entidades.Vendedor;
import com.hbt.semillero.servicios.interfaces.IConsultasBeanLocal;

@Stateless
public class ConsultasBean implements IConsultasBeanLocal{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	//contenedor controla las transacciones
	/**
	 * Metodo para consultar las Marcas
	 * retorna un objeto de tipo List<Marca>
	 * */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Marca> consultarMarcas() {
		//Transformar consulta en una consulta nativa 
		return entityManager.createQuery("Select ma FROM Marca ma").getResultList();				
	}
	
	
	/**
	 * Metodo que permite consultar las lineas existentes, dicha consulta se hace
	 * por medio del atributo de entrada idMarca, en donde retornara las lineas que pertenecen
	 * a la marca que se ingresa, retorna un una lista de la clase Linea
	 * 
	 * 
	 * @param idMarca
	 * 
	 * */
	public List<Linea> consultarLineas(Long idMarca) {
		return entityManager.createQuery("Select ln FROM Linea ln JOIN FETCH ln.marca where ln.marca.idMarca =:idMarca").
				setParameter("idMarca", idMarca).getResultList();
		
	}
	
	/**
	 * Metodo que permite consultar informacion de una persona, dicha consulta se realiza con los atributos 
	 * de entrada, retorna una lista de tipo Persona
	 * @param numeroIdentificacion
	 * @param tipoIdentificacion
	 * */
	public List<Persona> consultarPersona(String numeroIdentificacion,String tipoIdentificacion) {
		return entityManager.createQuery("Select per FROM Persona per where per.numeroIdentificacion =:numeroIdentificacion and per.tipoIdentificacion=:tipoIdentificacion").
				setParameter("numeroIdentificacion", numeroIdentificacion).setParameter("tipoIdentificacion", tipoIdentificacion).getResultList();
		
	}
	
	
	
	/**
	 * metodo que permite el registro de una persona en la base de datos,
	 * recibe como parametro un objeto de tipo PersonaDTO,
	 * en donde los valores recibidos del parametro se asignan a la persona a registrar 
	 * en la base de datos
	 * @param personaDTO
	 * */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void crearPersona(PersonaDTO personaDTO) {
		Persona persona= new Persona();
		persona.setNombres(personaDTO.getNombres());
		persona.setApellidos(personaDTO.getApellidos());
		persona.setNumeroIdentificacion(personaDTO.getNumeroIdentificacion());
		persona.setTipoIdentificacion(personaDTO.getTipoIdentificacion());
		persona.setNumeroTelefonico(personaDTO.getNumeroTelefonico());
		persona.setEdad(personaDTO.getEdad());
		
		entityManager.persist(persona);
		
		
		if (personaDTO.isComprador()) {
			Comprador comprador=new Comprador();
			comprador.setFechaAfiliacion(Calendar.getInstance());
			comprador.setPersona(persona);
			entityManager.persist(comprador);
		}
		if (personaDTO.isVendedor()) {
			Vendedor vendedor= new Vendedor();
			vendedor.setFechaIngreso(Calendar.getInstance());
			vendedor.setPersona(persona);
			entityManager.persist(vendedor);
			
		}
		
		
	}
	
	/**Metodo que permite la mpdificacion de una persona,
	 * recibe como parametro un objeto de tipo PersonaDTO el cual contiene
	 * los datos modificados de la persona
	 * @param personaDTO
	 * */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void modificarPersona(PersonaDTO personaDTO) {
		
		Persona persona= entityManager.find(Persona.class, personaDTO.getIdPersona());
		persona.setNombres(personaDTO.getNombres());
		persona.setApellidos(personaDTO.getApellidos());
		persona.setNumeroIdentificacion(personaDTO.getNumeroIdentificacion());
		persona.setTipoIdentificacion(personaDTO.getTipoIdentificacion());
		persona.setNumeroTelefonico(personaDTO.getNumeroTelefonico());
		persona.setEdad(personaDTO.getEdad());
		
		entityManager.merge(persona);
		
		
		
	}
}
