package com.adso.SitioSalud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adso.SitioSalud.intefaceService.IMedicoService;
import com.adso.SitioSalud.interfaces.IMedico;
import com.adso.SitioSalud.models.medico;


@Service
public class medicoService  implements IMedicoService{

	
	@Autowired
	private IMedico data;

	@Override
	public String save(medico medico) {
		data.save(medico);
		return medico.getId_medico();
	}

	@Override
	public List<medico> findAll() {
		List <medico> listaMedico = (List<medico>) data.findAll() ;
		
		return listaMedico;
	}

	@Override
	public List<medico> filtroMedico(String filtro) {
		List <medico> listaMedico=data.filtroMedico(filtro);
		return listaMedico;
	}
	
	
	@Override
	public Optional<medico> findOne(String id_medico) {
		Optional<medico>medico=data.findById(id_medico);
		
		return medico;
	}

	@Override
	public int delete(String id_medico) {
		data.deleteById(id_medico);
		return 1;
	}
	@Override

	public List<medico> filtroIngresoMedico(String numero_documento) {
		List<medico> listaMedico=data.filtroIngresoMedico(numero_documento);
		return listaMedico;
	}

}
