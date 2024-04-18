package com.adso.SitioSalud.controller;

import java.util.List;

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
	
public ResponseEntity<Object> save(@ModelAttribute("paciente") paciente paciente) {
	    
	    List<paciente> pacientes = pacienteService.filtroIngreso(paciente.getNumero_documento());
	    if (!pacientes.isEmpty()) {
	        return new ResponseEntity<>("El paciente ya tiene un ingreso activo", HttpStatus.BAD_REQUEST);
	    }
	    
	    if (paciente.getNumero_documento().equals("")) {

            return new ResponseEntity<>("El documento de identidad es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (paciente.getPrimer_nombre().equals("")) {

            return new ResponseEntity<>("El primer nombre es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (paciente.getPrimer_apellido().equals("")) {

            return new ResponseEntity<>("El primer apellido es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (paciente.getTelefono().equals("")) {

            return new ResponseEntity<>("El numero de telefono es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (paciente.getCorreo().equals("")) {

            return new ResponseEntity<>("El correo es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (paciente.getEstado().equals("")) {

            return new ResponseEntity<>("El estado es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }
        
        if (paciente.getDireccion().equals("")) {

            return new ResponseEntity<>("La dirección es un campo  obligatoria", HttpStatus.BAD_REQUEST);
        }
        if (paciente.getNombre_persona_contacto().equals("")) {

            return new ResponseEntity<>("El Nombre de la Persona  Contacto es  un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (paciente.getTelefono_persona_contacto().equals("")) {

            return new ResponseEntity<>(" El Telefono de la Persona Contacto es un campo obligatorio", HttpStatus.BAD_REQUEST);
        }

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
		 var paciente=pacienteService.findOne(id).get();
		 if (paciente!=null) {
			 if (paciente.getEstado().equals("H")) {
				 pacienteService.save(paciente);
				 paciente.setEstado("D");
				 pacienteService.save(paciente);
				 return new ResponseEntity<>("Se ha deshabilitado correctamente", HttpStatus.OK);
				 
				 
			 } else 
				 paciente.setEstado("H");
			 pacienteService.save(paciente);
			 return new ResponseEntity<>("Seha habilitado correctamente",HttpStatus.OK);
			 
		 } else {
			 return new ResponseEntity<>("No se ha encontrado el medico", HttpStatus.BAD_REQUEST);
		 }
	
	
    }
	
	@PutMapping("/{id_paciente}")
	public ResponseEntity<Object> update  ( @PathVariable String id_paciente, @ModelAttribute("paciente") paciente pacienteUpdate){
		 // Verificar si hay campos vacíos
        if (pacienteUpdate.contieneCamposVacios()) {
            return new ResponseEntity<>("Todos los campos son obligatorios", HttpStatus.BAD_REQUEST);
        }
		var paciente= pacienteService.findOne(id_paciente).get();
		if (paciente != null) {

			 // Verificar si el número de documento se está cambiando
		    if (!paciente.getNumero_documento().equals(pacienteUpdate.getNumero_documento())) {
		        // El número de documento se está cambiando, verificar si ya está en uso
		        List<paciente> pacientesConMismoDocumento = pacienteService.filtroIngreso(pacienteUpdate.getNumero_documento());
		        if (!pacientesConMismoDocumento.isEmpty()) {
		            // Si hay otros pacientes con el mismo número de documento, devuelve un error
		            return new ResponseEntity<>("El paciente ya tiene un ingreso activo", HttpStatus.BAD_REQUEST);
		        }
		    }

			
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