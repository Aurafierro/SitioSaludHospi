package com.adso.SitioSalud.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence. Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;



@Entity
public class ingreso {

	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	@Column(name="id_ingreso", nullable= false, length = 36)
	private String id_ingreso;

	@ManyToOne
	@JoinColumn (name="id_paciente")
	private paciente paciente;
	
	@ManyToOne
	@JoinColumn (name="id_medico")
	private medico medico;
	
	@Column(name="habitacion", nullable= false, length = 20)
	private String habitacion;

	@Column(name="cama", nullable= false, length = 11)
	private String cama;

	@Column(name="fecha_ingreso", nullable= false, length = 20)
	private String fecha_ingreso;
	
	@Column(name="fecha_salida", nullable= false, length = 20)
	private String fecha_salida;
	
	@Column(name="estado", nullable= false, length = 5)
	private String estado;

	public ingreso() {
		super();
	}

	public ingreso(String id_ingreso, com.adso.SitioSalud.models.paciente paciente,
			com.adso.SitioSalud.models.medico medico, String habitacion, String cama, String fecha_ingreso,
			String fecha_salida, String estado) {
		super();
		this.id_ingreso = id_ingreso;
		this.paciente = paciente;
		this.medico = medico;
		this.habitacion = habitacion;
		this.cama = cama;
		this.fecha_ingreso = fecha_ingreso;
		this.fecha_salida = fecha_salida;
		this.estado = estado;
	}

	public String getId_ingreso() {
		return id_ingreso;
	}

	public void setId_ingreso(String id_ingreso) {
		this.id_ingreso = id_ingreso;
	}

	public paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(paciente paciente) {
		this.paciente = paciente;
	}

	public medico getMedico() {
		return medico;
	}

	public void setMedico(medico medico) {
		this.medico = medico;
	}

	public String getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(String habitacion) {
		this.habitacion = habitacion;
	}

	public String getCama() {
		return cama;
	}

	public void setCama(String cama) {
		this.cama = cama;
	}

	public String getFecha_ingreso() {
		return fecha_ingreso;
	}

	public void setFecha_ingreso(String fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}

	public String getFecha_salida() {
		return fecha_salida;
	}

	public void setFecha_salida(String fecha_salida) {
		this.fecha_salida = fecha_salida;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	
	

	
	

	
	
	
}