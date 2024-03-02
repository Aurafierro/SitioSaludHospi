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

import com.adso.SitioSalud.intefaceService.IMedicoService;
import com.adso.SitioSalud.models.medico;





@RequestMapping("/api/v1/medico")
@RestController
@CrossOrigin

public class medicoController {

@Autowired
   
  private IMedicoService medicoService;
	/*
	 * retorna un json , indicando si funciono, presentó
	 * error, los datos solicitados
	 */
	@PostMapping("/")
  public ResponseEntity<Object> save(
		  @ModelAttribute("medico")medico medico
		  ){
		medicoService.save(medico);
		return new ResponseEntity<>(medico,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<Object>findAll(){
		var ListaMedico = medicoService.findAll();
		return new ResponseEntity<>(ListaMedico, HttpStatus.OK);
	}
	
	//filtro
	@GetMapping("/busquedafiltro/{filtro}")
	public ResponseEntity<Object>findFiltro(@PathVariable String filtro){
		var ListaMedico = medicoService.filtroMedico(filtro);
		return new ResponseEntity<>(ListaMedico, HttpStatus.OK);
	}
	//@PathVariable recibe una variable por el enlace
	
	@GetMapping("/{id_medico}")
	public ResponseEntity<Object> findOne ( @PathVariable String id_medico ){
		var medico= medicoService.findOne(id_medico);
		return new ResponseEntity<>(medico, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete ( @PathVariable String id ){
		 var medico=medicoService.findOne(id).get();
		 if (medico!=null) {
			 if (medico.getEstado().equals("H")) {
				 medico.setEstado("D");
				 return new ResponseEntity<>("Se ha deshabilitado correctamente", HttpStatus.OK);
				 
			 } else 
				 medico.setEstado("H");
			 return new ResponseEntity<>("Seha habilitado correctamente",HttpStatus.OK);
			 
		 } else {
			 return new ResponseEntity<>("No se ha encontrado el medico", HttpStatus.BAD_REQUEST);
		 }
	
	
     }
	
	
	@PutMapping("/{id_medico}")
	public ResponseEntity<Object> update  ( @PathVariable String id_medico, @ModelAttribute("medico") medico medicoUpdate){
		var medico= medicoService.findOne(id_medico).get();
		if (medico != null) {
			
			medico.setTipo_documento(medicoUpdate.getTipo_documento());
			medico.setNumero_documento(medicoUpdate.getNumero_documento());
			medico.setPrimer_nombre(medicoUpdate.getPrimer_nombre());
			medico.setSegundo_nombre(medicoUpdate.getSegundo_nombre());
			medico.setPrimer_apellido(medicoUpdate.getPrimer_apellido());
			medico.setSegundo_apellido(medicoUpdate.getSegundo_apellido());
			medico.setTelefono(medicoUpdate.getTelefono());
			medico.setCorreo(medicoUpdate.getCorreo());
			medico.setDireccion(medicoUpdate.getDireccion());
			medico.setEstado(medicoUpdate.getEstado());
			
			medicoService.save(medico);
			return new ResponseEntity<>("Guardado", HttpStatus.OK);
			
		}
		else {
			return new ResponseEntity<>("Error medico no encontrado", HttpStatus.BAD_REQUEST);
		}
	}
	
}