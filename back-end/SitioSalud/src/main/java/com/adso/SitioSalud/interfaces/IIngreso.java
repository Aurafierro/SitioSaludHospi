package com.adso.SitioSalud.interfaces;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.adso.SitioSalud.models.ingreso;

@Repository

public interface IIngreso extends CrudRepository<ingreso,String>{
	@Query("SELECT i FROM ingreso i WHERE "
			+ "i.habitacion LIKE %?1% OR "
			+ "i.cama LIKE %?1% OR "
			+ "i.fecha_ingreso = ?1 OR "
			+ "i.fecha_salida = ?1 OR "
			+ "i.estado LIKE %?1%")
	List<ingreso>filtroIngreso (String filtro);
}
