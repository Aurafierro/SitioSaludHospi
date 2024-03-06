package com.adso.SitioSalud.interfaces;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adso.SitioSalud.models.ingreso;

@Repository

public interface IIngreso extends CrudRepository<ingreso,String>{
	@Query("SELECT i FROM ingreso i JOIN "
			+"i.medico m "
			+"JOIN i.paciente p "
			+"WHERE p.primer_nombre like %?1% "
			+"OR m.primer_nombre LIKE %?1% ")
	List<ingreso>findFilterIngreso (String filtro);
}
