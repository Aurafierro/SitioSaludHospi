package com.adso.SitioSalud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adso.SitioSalud.intefaceService.IPacienteService;
import com.adso.SitioSalud.interfaces.IPaciente;

import com.adso.SitioSalud.models.paciente;

@Service
public class pacienteService  implements IPacienteService{

	
	@Autowired
	private IPaciente data;

	@Override
	public String save(paciente paciente) {
		data.save(paciente);
		return paciente.getId_paciente();
	}

	@Override
	public List<paciente> findAll() {
		List <paciente> listaPaciente = (List<paciente>) data.findAll() ;
		
		return listaPaciente;
	}

	@Override
	public List<paciente> filtroPaciente(String filtro) {
		List <paciente> listaPaciente=data.filtroPaciente(filtro);
		return listaPaciente;
	}
	
	@Override
	public Optional<paciente> findOne(String id_paciente) {
		Optional<paciente>paciente=data.findById(id_paciente);
		
		return paciente;
	}

	@Override
	public int delete(String id_paciente) {
		data.deleteById(id_paciente);
		return 1;
	}
	@Override

	public List<paciente> filtroIngreso(String numero_documento) {
		List<paciente> listaPaciente=data.filtroIngreso(numero_documento);
		return listaPaciente;
	}

}
