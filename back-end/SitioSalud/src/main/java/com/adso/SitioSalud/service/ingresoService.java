package com.adso.SitioSalud.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adso.SitioSalud.intefaceService.IIngresoService;
import com.adso.SitioSalud.interfaces.IIngreso;
import com.adso.SitioSalud.models.ingreso;

@Service
public class ingresoService  implements IIngresoService{

	
	@Autowired
	private IIngreso data;

	@Override
	public String save(ingreso ingreso) {
		data.save(ingreso);
		return ingreso.getId_ingreso();
	}

	@Override
	public List<ingreso> findAll() {
		List <ingreso> listaIngreso = (List<ingreso>) data.findAll() ;
	
		return listaIngreso;
	}

	@Override
	public List<ingreso> filtroIngreso(String filtro) {
		List <ingreso> listaIngreso=data.filtroIngreso(filtro);
		return listaIngreso;
	}
	
	@Override
	public List<ingreso> filtroEstado(String id_paciente) {
		List<ingreso>ListaIngreso=data.filtroEstado(id_paciente);
		return ListaIngreso;
	}

	@Override
	public List<ingreso> filtroFechaIngreso(Date fecha_ingreso) {
		List<ingreso>ListaIngreso=data.filtroFechaIngreso(fecha_ingreso);
		return ListaIngreso;
	}
	
	
	
	@Override
	public Optional<ingreso> findOne(String id_ingreso) {
		Optional<ingreso>ingreso=data.findById(id_ingreso);
		
		return ingreso;
	}

	@Override
	public int delete(String id_ingreso) {
		data.deleteById(id_ingreso);
		return 1;
	}
	@Override
	public List<ingreso>filtroCamaOcupada(String cama, String habitacion){
		List<ingreso>ListaIngreso=data.filtroCamaOcupada(cama, habitacion);
		return ListaIngreso;
	}
}
