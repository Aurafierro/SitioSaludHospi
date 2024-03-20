package com.adso.SitioSalud.interfaces;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adso.SitioSalud.models.ingreso;
import com.adso.SitioSalud.models.paciente;

@Repository

public interface IPaciente extends CrudRepository<paciente,String>{
	@Query("SELECT p FROM paciente p WHERE "
			+ "p.tipo_documento LIKE %?1% OR "
			+ "p.numero_documento LIKE %?1% OR "
			+ "p.primer_nombre LIKE %?1% OR "
			+ "p.segundo_nombre LIKE %?1% OR "
			+ "p.primer_apellido LIKE %?1% OR "
			+ "p.segundo_apellido LIKE %?1% OR " 
			+ "p.telefono LIKE %?1% OR "
			+ "p.correo LIKE %?1% OR "
			+ "p.direccion LIKE %?1% OR "
			+ "p.nombre_persona_contacto LIKE %?1% OR "
			+ "p.telefono_persona_contacto LIKE %?1% OR "
			+ "p.estado LIKE %?1%")
	List<paciente>filtroPaciente(String filtro);
	
	@Query("SELECT p FROM paciente p WHERE p.numero_documento = ?1 AND p.estado='H'")
	List<paciente> filtroIngreso(String numero_documento );
}


