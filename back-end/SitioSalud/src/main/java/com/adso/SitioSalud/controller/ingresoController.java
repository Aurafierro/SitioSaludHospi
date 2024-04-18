package com.adso.SitioSalud.controller;

import java.time.LocalDate;
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

import com.adso.SitioSalud.intefaceService.IIngresoService;
import com.adso.SitioSalud.models.ingreso;





@RequestMapping("/api/v1/ingreso")
@RestController
@CrossOrigin

public class ingresoController {
  @Autowired
  


  private IIngresoService ingresoService;
	/*
	 * retorna un json , indicando si funciono, presentó
	 * error, los datos solicitados
	 */
  
  @PostMapping("/")
  public ResponseEntity<Object> save(@ModelAttribute("ingreso") ingreso ingreso) {
	  
	    // V erificar si el paciente ya tiene un ingreso activo
	    List<ingreso> listaPacienteA = ingresoService.filtroEstado(ingreso.getPaciente().getId_paciente());
	    if (!listaPacienteA.isEmpty()) {
	        return new ResponseEntity<>("el paciente ya tiene un ingreso activo", HttpStatus.BAD_REQUEST);
	    }
 	    List<ingreso> ingresos = ingresoService.filtroCamaOcupada(ingreso.getCama(), ingreso.getHabitacion());
	    if (!ingresos.isEmpty()) {
	        return new ResponseEntity<>("La cama y la habitación ya están ocupadas", HttpStatus.BAD_REQUEST);
	    }
	     
	  //verificar que el campo documento de identidad sea diferente vacio
        if (ingreso.getHabitacion().equals("")) {

            return new ResponseEntity<>("El campo habitacion es obligatorio", HttpStatus.BAD_REQUEST);
        }

        if (ingreso.getCama().equals("")) {
            
            return new ResponseEntity<>("El campo cama es obligatorio", HttpStatus.BAD_REQUEST);
            
        }
     // Verificar que las fechas sean válidas
        LocalDate fechaIngreso = LocalDate.parse(ingreso.getFecha_ingreso());
        LocalDate fechaSalida = LocalDate.parse(ingreso.getFecha_salida());
        if (fechaSalida.compareTo(fechaIngreso) < 0) {
            return new ResponseEntity<>("La fecha de salida no puede ser anterior a la fecha de ingreso", HttpStatus.BAD_REQUEST);
        }
        if (ingreso.getPaciente().equals("")) {
            
            return new ResponseEntity<>("El campo paciente es obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (ingreso.getMedico().equals("")) {
            
            return new ResponseEntity<>("El campo medico es obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (ingreso.getFecha_ingreso().equals("")) {
            
            return new ResponseEntity<>("El campo fecha ingreso es obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (ingreso.getFecha_salida().equals("")) {
            
            return new ResponseEntity<>("El campo fecha salida es obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (ingreso.getHabitacion().equals("")) {
            
            return new ResponseEntity<>("El campo habitacion es obligatorio", HttpStatus.BAD_REQUEST);
        }
        if (ingreso.getCama().equals("")) {
            
            return new ResponseEntity<>("El campo cama es obligatorio", HttpStatus.BAD_REQUEST);
        }
	        
	        
        if (ingreso.getEstado().equals("")) {
            
            return new ResponseEntity<>("El campo estado es obligatorio", HttpStatus.BAD_REQUEST);
        }
	           
	    	  
	    // Guardar el nuevo ingreso

	    ingresoService.save(ingreso);

	    return new ResponseEntity<>(ingreso, HttpStatus.OK);
	    
	}
  
	@GetMapping("/")
	public ResponseEntity<Object>findAll(){
		var ListaIngreso = ingresoService.findAll();
		return new ResponseEntity<>(ListaIngreso, HttpStatus.OK);
	}
	
	//filtro
		@GetMapping("/busquedafiltro/{filtro}")
		public ResponseEntity<Object>filtroIngreso(@PathVariable String filtro){
			var ListaIngreso = ingresoService.filtroIngreso(filtro);
			return new ResponseEntity<>(ListaIngreso, HttpStatus.OK);
		}
	
	//@PathVariable recibe una variable por el enlace 
	
	@GetMapping("/{id_ingreso}")
	public ResponseEntity<Object> findOne ( @PathVariable String id_ingreso ){
		var cliente= ingresoService.findOne(id_ingreso);
		return new ResponseEntity<>(cliente, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete ( @PathVariable String id ){
		 var ingreso= ingresoService.findOne(id).get();
		 if (ingreso!=null) {
			 if (ingreso.getEstado().equals("H")) {
				 ingreso.setEstado("AH");
				 ingresoService.save(ingreso);
				 return new ResponseEntity<>("Se ha deshabilitado correctamente", HttpStatus.OK);
				 
			 } else 
				 ingreso.setEstado("H");
			 ingresoService.save(ingreso);   
			 return new ResponseEntity<>("Se ha habilitado correctamente",HttpStatus.OK);
			 
		 } else {
			 return new ResponseEntity<>("No se ha encontrado el medico", HttpStatus.BAD_REQUEST);
		 }
	
	
     }
		
	
	
	@PutMapping("/{id_ingreso}")
	public ResponseEntity<Object> update  ( @PathVariable String id_ingreso, @ModelAttribute("ingreso") ingreso ingresoUpdate){
		 // Verificar si hay campos vacíos
        if (ingresoUpdate.contieneCamposVacios()) {
            return new ResponseEntity<>("Todos los campos son obligatorios", HttpStatus.BAD_REQUEST);
        }

		var ingreso= ingresoService.findOne(id_ingreso).get();
		if (ingreso != null) {
			
			 List<ingreso> listaPacienteA = ingresoService.filtroEstado(ingresoUpdate.getPaciente().getId_paciente());
		        if (!listaPacienteA.isEmpty()) {
		            return new ResponseEntity<>("el paciente ya tiene un ingreso activo", HttpStatus.BAD_REQUEST);
		        }
		        
		        // Verificar si la cama y la habitación ya están ocupadas
		        List<ingreso> ingresos = ingresoService.filtroCamaOcupada(ingresoUpdate.getCama(), ingresoUpdate.getHabitacion());
		        if (!ingresos.isEmpty()) {
		            return new ResponseEntity<>("La cama y la habitación ya están ocupadas", HttpStatus.BAD_REQUEST);
		        }
		        
			 // Verificar que la fecha de salida no sea anterior a la fecha de ingreso
	        LocalDate fechaIngreso = LocalDate.parse(ingresoUpdate.getFecha_ingreso());
	        LocalDate fechaSalida = LocalDate.parse(ingresoUpdate.getFecha_salida());
	        if (fechaSalida.compareTo(fechaIngreso) < 0) {
	            return new ResponseEntity<>("La fecha de salida no puede ser anterior a la fecha de ingreso", HttpStatus.BAD_REQUEST);
	        }
			
			ingreso.setPaciente(ingresoUpdate.getPaciente());
			ingreso.setMedico(ingresoUpdate.getMedico());
			ingreso.setHabitacion(ingresoUpdate.getHabitacion());
			ingreso.setCama(ingresoUpdate.getCama());
			ingreso.setFecha_ingreso(ingresoUpdate.getFecha_ingreso());
			ingreso.setFecha_salida(ingresoUpdate.getFecha_salida());
			ingreso.setEstado(ingresoUpdate.getEstado());
			
			ingresoService.save(ingreso);
			return new ResponseEntity<>("Guardado", HttpStatus.OK);
			
		}
		else {
			return new ResponseEntity<>("Error ingreso no encontrado", HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
}
	