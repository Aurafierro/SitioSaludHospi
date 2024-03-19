package com.adso.SitioSalud.interfaces;


import java.sql.Date;
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
			+"OR p.segundo_nombre LIKE %?1% "
			+"OR p.primer_apellido LIKE %?1% "
			+"OR p.segundo_apellido LIKE %?1% "
			+"OR m.primer_nombre LIKE %?1% "
			+"OR m.segundo_nombre LIKE %?1% "
			+"OR m.primer_apellido LIKE %?1% "
			+"OR m.segundo_apellido LIKE %?1% "
			+"OR i.fecha_ingreso= ?1 "
			+"OR i.fecha_salida= ?1 ")
	List<ingreso>filtroIngreso (String filtro);
	
	@Query ("SELECT i FROM ingreso i "

			+ "WHERE i.fecha_ingreso = ?1 "
			
			
			)
			List<ingreso> filtroFechaIngreso(Date fecha_ingreso);

		
			@Query ("SELECT i FROM ingreso i JOIN i.paciente p "
					+"WHERE p.id_paciente=?1 AND i.estado='H' "
			
			
			)
			List<ingreso> filtroEstado(String id_paciente);
			

			@Query("SELECT i FROM ingreso i WHERE i.cama = ?1 AND i.habitacion = ?2 AND i.estado='H'")
			List<ingreso> filtroCamaOcupada(String cama, String habitacion);


}
