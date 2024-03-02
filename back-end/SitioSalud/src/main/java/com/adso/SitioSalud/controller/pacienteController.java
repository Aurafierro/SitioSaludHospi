package com.adso.SitioSalud.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adso.SitioSalud.intefaceService.IPacienteService;
import com.adso.SitioSalud.models.paciente;






@RequestMapping("/api/v1/paciente")
@RestController
@CrossOrigin

public class pacienteController {
  @Autowired
  


  private IPacienteService pacienteService;
	/*
	 * retorna un json , indicando si funciono, presentó
	 * error, los datos solicitados
	 */
	@PostMapping("/")
  public ResponseEntity<Object> save(
		  @ModelAttribute("paciente")paciente paciente
		  ){
		pacienteService.save(paciente);
		return new ResponseEntity<>(paciente,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<Object>findAll(){
		var ListaPaciente = pacienteService.findAll();
		return new ResponseEntity<>(ListaPaciente, HttpStatus.OK);
	}
	
	@GetMapping("/busquedafiltro/{filtro}")
	public ResponseEntity<Object>findFiltro(@PathVariable String filtro){
		var ListaPaciente = pacienteService.filtroPaciente(filtro);
		return new ResponseEntity<>(ListaPaciente, HttpStatus.OK);
	}
	//@PathVariable recibe una variable por el enlace
	
	@GetMapping("/{id_paciente}")
	public ResponseEntity<Object> findOne ( @PathVariable String id_paciente ){
		var paciente= pacienteService.findOne(id_paciente);
		return new ResponseEntity<>(paciente, HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete ( @PathVariable String id ){
		 pacienteService.delete(id);
		return new ResponseEntity<>("Paciente Eliminado", HttpStatus.OK);
	
	
     }
	
	@PutMapping("/{id_paciente}")
	public ResponseEntity<Object> update  ( @PathVariable String id_paciente, @ModelAttribute("paciente") paciente pacienteUpdate){
		var paciente= pacienteService.findOne(id_paciente).get();
		if (paciente != null) {
			
			paciente.setTipo_documento(pacienteUpdate.getTipo_documento());
			paciente.setNumero_documento(pacienteUpdate.getNumero_documento());
			paciente.setPrimer_nombre(pacienteUpdate.getPrimer_nombre());
			paciente.setSegundo_nombre(pacienteUpdate.getSegundo_nombre());
			paciente.setPrimer_apellido(pacienteUpdate.getPrimer_apellido());
			paciente.setSegundo_apellido(pacienteUpdate.getSegundo_apellido());
			paciente.setTelefono(pacienteUpdate.getTelefono());
			paciente.setCorreo(pacienteUpdate.getCorreo());
			paciente.setDireccion(pacienteUpdate.getDireccion());
			paciente.setNombre_persona_contacto(pacienteUpdate.getNombre_persona_contacto());
			paciente.setTelefono_persona_contacto(pacienteUpdate.getTelefono_persona_contacto());
			paciente.setEstado(pacienteUpdate.getEstado());
			
			pacienteService.save(paciente);
			return new ResponseEntity<>("Guardado", HttpStatus.OK);
			
		}
		else {
			return new ResponseEntity<>("Error paciente no encontrado", HttpStatus.BAD_REQUEST);
		}
	}
	
}