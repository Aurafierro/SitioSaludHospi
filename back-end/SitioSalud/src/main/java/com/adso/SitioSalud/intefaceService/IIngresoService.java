package com.adso.SitioSalud.intefaceService;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.adso.SitioSalud.models.ingreso;

public interface IIngresoService {
 
	public String save(ingreso ingreso);
    public List <ingreso> findAll();
    public List<ingreso> filtroIngreso(String filtro);
    public List<ingreso> filtroEstado(String id_paciente);
    public List<ingreso> filtroFechaIngreso(Date fecha_ingreso);
	public Optional<ingreso> findOne(String id);
	public int delete (String id);
}

