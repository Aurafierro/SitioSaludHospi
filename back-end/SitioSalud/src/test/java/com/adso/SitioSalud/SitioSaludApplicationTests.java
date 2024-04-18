 package com.adso.SitioSalud;

 import org.junit.jupiter.api.Test;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
 import org.springframework.boot.test.mock.mockito.MockBean;
 import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import com.adso.SitioSalud.controller.*;
import com.adso.SitioSalud.intefaceService.IIngresoService;
import com.adso.SitioSalud.intefaceService.IMedicoService;
import com.adso.SitioSalud.intefaceService.IPacienteService;
import com.adso.SitioSalud.models.ingreso;
import com.adso.SitioSalud.models.medico;
import com.adso.SitioSalud.models.paciente;

import jakarta.transaction.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@AutoConfigureMockMvc


class SitioSaludApplicationTests {
	
	 @Autowired
	    private medicoController medicoController;

	    @MockBean
	    private IMedicoService medicoService;

	    @Test
	    @Rollback
	    public void testMedicoGuardar() {
	        medico medico = new medico(); 
	        medico.setNumero_documento("1077226358");
	        medico.setPrimer_nombre("Aura");
	        medico.setSegundo_nombre("Maria");
	        medico.setPrimer_apellido("Fierro");
	        medico.setSegundo_apellido("Fierro");
	        medico.setTelefono("3168704586");
	        medico.setCorreo("auramariafierrofierro@gmail.com");
	        medico.setDireccion("Calle 12 n3-4");
	        medico.setEstado("H");
	        
	        ResponseEntity<Object> response = medicoController.save(medico);
		
			assertEquals(HttpStatus.OK, response.getStatusCode());


	         
	    }
	   

	    @Test
	    @Rollback
	    public void testMedicoIncompletoGuardar() {
	        medico medico = new medico(); 
	        medico.setNumero_documento("1077226358");
	        medico.setPrimer_nombre("");
	        medico.setSegundo_nombre("Maria");
	        medico.setPrimer_apellido("Fierro");
	        medico.setSegundo_apellido("Fierro");
	        medico.setTelefono("3168704586");
	        medico.setCorreo("auramariafierrofierro@gmail.com");
	        medico.setDireccion("Calle 12 n3-4");
	        medico.setEstado("H");

	       
	        ResponseEntity<Object> response = medicoController.save(medico);

	       
	        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	        assertEquals("El primer nombre es un campo obligatorio", response.getBody());
	    }

	    
	    
	    @Test
	    @Rollback
	    public void testMedicoRepetidoGuardar() {
	        medico medico = new medico(); 
	        medico.setNumero_documento("1077226358");
	        medico.setPrimer_nombre("Aura");
	        medico.setSegundo_nombre("Maria");
	        medico.setPrimer_apellido("Fierro");
	        medico.setSegundo_apellido("Fierro");
	        medico.setTelefono("3168704586");
	        medico.setCorreo("auramariafierrofierro@gmail.com");
	        medico.setDireccion("Calle 12 n3-4");
	        medico.setEstado("H");

	       
	        List<medico> listamedicos=new ArrayList<>();
			listamedicos.add(medico);
			//simula una base datos
			when(medicoService.filtroIngresoMedico(anyString())).thenReturn(listamedicos);

			ResponseEntity<Object> response = medicoController.save(medico);
			//valor esperado
			assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
			assertEquals("El medico ya tiene un ingreso activo", response.getBody());
	    }
	    
	    @Test
	    @Rollback
	    public  void ListarMedicos() {
	        // Simular una lista de médicos no vacía
	        List<medico> medicos = new ArrayList<>();
	        medicos.add(new medico());
	        medicos.add(new medico());
	        medicos.add(new medico());
	        when(medicoService.findAll()).thenReturn(medicos);

	        // Ejecutar el método findAll del controlador
	        ResponseEntity<Object> response = medicoController.findAll();

	        // Verificar que la respuesta sea un código de estado OK (200)
	        assertEquals(HttpStatus.OK, response.getStatusCode());

	        // Verificar que la lista de médicos devuelta sea la misma que la simulada
	        assertEquals(medicos, response.getBody());
	    }
	
	   
	    @Test
	    @Rollback
	    public void testEditarMedicoGuardar() {
	        medico medico = new medico(); 
	        medico.setNumero_documento("1077226358");
	        medico.setPrimer_nombre("Aura");
	        medico.setSegundo_nombre("Maria");
	        medico.setPrimer_apellido("Fierro");
	        medico.setSegundo_apellido("Fierro");
	        medico.setTelefono("3166503680");
	        medico.setCorreo("auramariafierrofierro@gmail.com");
	        medico.setDireccion("Calle 12 n3-4");
	        medico.setEstado("H");

	        ResponseEntity<Object> response = medicoController.save(medico);

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        
	         // Verificar que el objeto devuelto corresponda al médico guardado
	        assertEquals(medico, response.getBody());
	    }

	    @Test
	    @Rollback
	    public void testEditarMedicoIncompletoGuardar() {
	        // Crear un objeto medico con campos incompletos
	        medico medico = new medico(); 
	        medico.setNumero_documento("1077226358");
	        medico.setPrimer_nombre("Aura"); // Dejar el primer nombre en blanco
	        medico.setSegundo_nombre("Maria");
	        medico.setPrimer_apellido("Fierro");
	        medico.setSegundo_apellido("Fierro");
	        medico.setTelefono("");
	        medico.setCorreo("auramariafierrofierro@gmail.com");
	        medico.setDireccion("Calle 12 n3-4");
	        medico.setEstado("H");

	        ResponseEntity<Object> response = medicoController.save(medico);

	      
	        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

	        // Verificar que el mensaje de la respuesta sea "Todos los campos son obligatorios"
	        assertEquals("El numero de télefono es un campo obligatorio", response.getBody());
	    }
	    
	    @Test
	    @Rollback
	    public void testMedicoRepetidoEditar() {
	        medico medico = new medico(); 
	        medico.setNumero_documento("1077226358");
	        medico.setPrimer_nombre("Argeny");
	        
	        medico.setPrimer_apellido("Fierro");
	        medico.setSegundo_apellido("Burgos");
	        medico.setTelefono("3168733715");
	        medico.setCorreo("argeny022@gmail.com");
	        medico.setDireccion("carrera 13 n18-47");
	        
	        medico.setEstado("H");

	       
	        List<medico> listamedicos=new ArrayList<>();
			listamedicos.add(medico);
			//simula una base datos
			when(medicoService.filtroIngresoMedico(anyString())).thenReturn(listamedicos);

			ResponseEntity<Object> response = medicoController.save(medico);
			//valor esperado
			assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
			assertEquals("El medico ya tiene un ingreso activo", response.getBody());
	    }
	    

	    //Pendiente El BuscarFiltros
	    
	    /////////////////////////////////////////////////////////////////
	    
	    //PACIENTES 
	    
	    @Autowired
	    private pacienteController pacienteController;

	    @MockBean
	    private IPacienteService pacienteService;

	    @Test
	    @Rollback
	    public void testPacienteGuardar() {
	        paciente paciente = new paciente(); 
	        paciente.setNumero_documento("1755874565");
	        paciente.setPrimer_nombre("Jairo");
	        paciente.setSegundo_nombre("Andres");
	        paciente.setPrimer_apellido("Fierro");
	        paciente.setSegundo_apellido("Fierro");
	        paciente.setTelefono("3104587200");
	        paciente.setCorreo("jaabofierro@gmail.com");
	        paciente.setNombre_persona_contacto("Maria Ortiz");
	        paciente.setTelefono_persona_contacto("3160214581");
	        paciente.setDireccion("	carrera 1 n15-23");
	        paciente.setEstado("H");
	        
	        ResponseEntity<Object> response = pacienteController.save(paciente);
			//valor esperado
			assertEquals(HttpStatus.OK, response.getStatusCode());


	         
	    }
	   

	    
	    
	    @Test
	    @Rollback
	    public void testPacienteIncompletoGuardar() {
	    	paciente paciente = new paciente(); 
	    	paciente.setNumero_documento("1755874565");
	    	paciente.setPrimer_nombre("");
	    	paciente.setSegundo_nombre("Andres");
	    	paciente.setPrimer_apellido("Fierro");
	    	paciente.setSegundo_apellido("Fierro");
	    	paciente.setTelefono("3104587200");
	    	paciente.setCorreo("jaabofierro@gmail.com");
	        paciente.setNombre_persona_contacto("Maria Ortiz");
		    paciente.setTelefono_persona_contacto("3160214581");
		    paciente.setDireccion("carrera 1 n15-23");
		    paciente.setEstado("H");

	       
	        ResponseEntity<Object> response = pacienteController.save(paciente);

	       
	        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	        assertEquals("El primer nombre es un campo obligatorio", response.getBody());
	    }


	    
	    
	    @Test
	    @Rollback
	    public void testPacienteRepetidoGuardar() {
	    	paciente paciente = new paciente(); 
	    	paciente.setNumero_documento("1755874565");
	    	paciente.setPrimer_nombre("Jairo");
	    	paciente.setSegundo_nombre("Andres");
	    	paciente.setPrimer_apellido("Fierro");
	    	paciente.setSegundo_apellido("Fierro");
	    	paciente.setTelefono("3104587200");
	    	paciente.setCorreo("jaabofierro@gmail.com");
	    	paciente.setNombre_persona_contacto("Maria Ortiz");
		    paciente.setTelefono_persona_contacto("3160214581");
	    	paciente.setDireccion("	carrera 1 n15-23");
	    	paciente.setEstado("H");

	       
	        List<paciente> listapacientes=new ArrayList<>();
			listapacientes.add(paciente);
			//simula una base datos
			when(pacienteService.filtroIngreso(anyString())).thenReturn(listapacientes);

			ResponseEntity<Object> response = pacienteController.save(paciente);
			//valor esperado
			assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
			assertEquals("El paciente ya tiene un ingreso activo", response.getBody());
	    }
	    
	    
	    @Test
	    @Rollback
	    public void ListarPacientes() {
	        // Simular una lista de pacientes no vacía
	        List<paciente> pacientes = new ArrayList<>();
	        pacientes.add(new paciente());
	        pacientes.add(new paciente());
	        pacientes.add(new paciente());
	        when(pacienteService.findAll()).thenReturn(pacientes);

	        // Ejecutar el método findAll del controlador
	        ResponseEntity<Object> response = pacienteController.findAll();

	        // Verificar que la respuesta sea un código de estado OK (200)
	        assertEquals(HttpStatus.OK, response.getStatusCode());

	        // Verificar que la lista de pacientes devuelta sea la misma que la simulada
	        assertEquals(pacientes, response.getBody());
	    }
	
	   
	    @Test
	    @Rollback
	    public void testEditarPacienteGuardar() {
	    	paciente paciente = new paciente(); 
	    	paciente.setNumero_documento("1755874565");
	    	paciente.setPrimer_nombre("Jairo");
	    	paciente.setSegundo_nombre("Andres");
	    	paciente.setPrimer_apellido("Fierro");
	        paciente.setSegundo_apellido("Fierro");
	        paciente.setTelefono("3112537210");
	        paciente.setCorreo("jaabofierro@gmail.com");
	        paciente.setNombre_persona_contacto("Maria Ortiz");
	        paciente.setTelefono_persona_contacto("3160214581");
	        paciente.setDireccion("	carrera 1 n15-23");
	        paciente.setEstado("H");

	        ResponseEntity<Object> response = pacienteController.save(paciente);

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        
	         // Verificar que el objeto devuelto corresponda al médico guardado
	        assertEquals(paciente, response.getBody());
	    }
	    
	    

	    @Test
	    @Rollback
	    public void testEditarPacienteIncompletoGuardar() {
	        // Crear un objeto medico con campos incompletos
	    	paciente paciente = new paciente(); 
	    	paciente.setNumero_documento("1755874565");
	        paciente.setPrimer_nombre("Jairo"); // Dejar el primer nombre en blanco
	        paciente.setSegundo_nombre("Andres");
	        paciente.setPrimer_apellido("Fierro");
	        paciente.setSegundo_apellido("Fierro");
	        paciente.setTelefono("");
	        paciente.setCorreo("jaabofierro@gmail.com");
	        paciente.setNombre_persona_contacto("Maria Ortiz");
	        paciente.setTelefono_persona_contacto("3160214581");
	        paciente.setDireccion("	carrera 1 n15-23");
	        paciente.setEstado("H");

	        ResponseEntity<Object> response = pacienteController.save(paciente);

	      
	        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

	        // Verificar que el mensaje de la respuesta sea "Todos los campos son obligatorios"
	        assertEquals("El numero de telefono es un campo obligatorio", response.getBody());
	    }
	    
	    
	    @Test
	    @Rollback
	    public void testPacienteRepetidoEditar() {
	    	paciente paciente = new paciente(); 
	    	paciente.setNumero_documento("1755874565");
	    	paciente.setPrimer_nombre("Maria");
	        paciente.setSegundo_apellido("Catalina");
	    	paciente.setPrimer_apellido("Fierro");
	    	paciente.setSegundo_apellido("Fierro");
	    	paciente.setTelefono("3104587200");
	    	paciente.setCorreo("mariacataaf@gmail.com");
	        paciente.setNombre_persona_contacto("Juan David  Ortiz");
		     paciente.setTelefono_persona_contacto("3154578220");
	    	paciente.setDireccion("carrera 20 n13-21");
	    	paciente.setEstado("H");

	       
	        List<paciente> listapacientes=new ArrayList<>();
			listapacientes.add(paciente);
			//simula una base datos
			when(pacienteService.filtroIngreso(anyString())).thenReturn(listapacientes);

			ResponseEntity<Object> response = pacienteController.save(paciente);
			//valor esperado
			assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
			assertEquals("El paciente ya tiene un ingreso activo", response.getBody());
	    }
	    //queda faltando el BuscarFiltro
	    
	  /////// ////////////////////////////////////////////////////////////////
	    
	    
	    //INGRESOOOOO
	    /*
	    @Autowired
	    private ingresoController ingresoController;

	    @MockBean
	    private IIngresoService ingresoService;
	    
	    //		Quedan pendientes los test del ingreso

	    @Test
	    @Rollback
	    public void testIngresoGuardar() {
	        // Crear instancias de Paciente y Medico
	        paciente paciente = new paciente();
	        paciente.setPrimer_nombre("Maria Catalina Fierro Fierro");

	        medico medico = new medico();
	        medico.setPrimer_nombre("Jairo Andres Fierro Fierro");

	        
	        ingreso ingreso = new ingreso(); 
	        ingreso.setHabitacion("14");
	        ingreso.setCama("2");
	        ingreso.setFecha_ingreso("2024-04-08");
	        ingreso.setFecha_salida("2024-03-08");
	       
	        ingreso.setEstado("H");
	        
	        // Asignar paciente y medico al ingreso
	        ingreso.setPaciente(paciente);
	        ingreso.setMedico(medico);


	        ResponseEntity<Object> response = ingresoController.save(ingreso);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	    }
	    

	    @Test
	    @Rollback
	    public void testIngresoGuardarHabitacionCamaOcupadas() {
	        // Crear instancia de Ingreso con la misma habitación y cama
	        ingreso ingreso = new ingreso(); 
	        ingreso.setHabitacion("14");
	        ingreso.setCama("2");
	        ingreso.setFecha_ingreso("2024-04-16"); 
	        ingreso.setFecha_salida("2024-04-20"); 
	        ingreso.setEstado("H");
	        ResponseEntity<Object> response = ingresoController.save(ingreso);
	        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	        assertEquals("La cama y la habitación ya están ocupadas", response.getBody());
	    }
	    
	    
	    
	    @Test
	    @Rollback
	    public void testFechaSalidaAnteriorAFechaIngreso() {
	        // Crear instancia de Ingreso con fecha de salida anterior a la fecha de ingreso
	        ingreso ingreso = new ingreso(); 
	        ingreso.setHabitacion("14");
	        ingreso.setCama("2");
	        ingreso.setFecha_ingreso("2024-04-20");
	        ingreso.setFecha_salida("2024-04-18"); // Fecha de salida anterior a la fecha de ingreso
	        ingreso.setEstado("H");
	        
	        // Llamar al controlador para guardar el ingreso y obtener la respuesta
	        ResponseEntity<Object> response = ingresoController.save(ingreso);
	        
	        // Verificar si la respuesta es HttpStatus.BAD_REQUEST (400 - Bad Request)
	        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	        
	      
			assertEquals("La fecha de salida no puede ser anterior a la fecha de ingreso", response.getBody());
	    }
	    */

}





