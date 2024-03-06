var url = "http://localhost:8080/api/v1/ingreso/";

function listarIngreso() {
  //METODO PARA LISTAR LOS CLIENTES
  //SE CREA LA PETICION AJAX
  $.ajax({
    url: url,
    type: "GET",
    success: function (result) {
      //success: funcion que se ejecuta
      //cuando la peticion tiene exito
      console.log(result);

      var cuerpoTabla = document.getElementById("cuerpoTabla");
      //Se limpia el cuepro de la tabla
      cuerpoTabla.innerHTML = "";
      //se hace un ciclo que recorra l arreglo con los datos
      for (var i = 0; i < result.length; i++) {
        //UNA ETIQUETA tr por cada registro
        var trResgistro = document.createElement("tr");

        var celdaId = document.createElement("td");
        let celdaIdPaciente = document.createElement("td")
        let celdaIdMedico = document.createElement("td")
        let celdaHabitacion = document.createElement("td")
        let celdaCama = document.createElement("td")
        let celdaFechaIngreso = document.createElement("td")
        let celdaFechaSalida = document.createElement("td")
        let celdaEstado = document.createElement("td")


        let celdaOpcion = document.createElement("td");
        let botonEditarIngreso = document.createElement("button")
        botonEditarIngreso.value = result [i]["id_ingreso"]
        botonEditarIngreso.innerHTML = "Editar"
        botonEditarIngreso.onclick=function(e){
          $('#exampleModal').modal('show');
          consultarIngresoID(this.value);
        }

        botonEditarIngreso.className = "btn btn-warning editar_ingreso"

        let botonDeshabilitarIngreso = document.createElement("button")
        botonDeshabilitarIngreso.innerHTML = "Deshabilitar"
        botonDeshabilitarIngreso.className = "btn btn-danger"

        
        celdaId.innerText = result[i]["id_ingreso"];
        celdaIdPaciente.innerText = result[i]["paciente"]["primer_nombre"]+" "+result[i]["paciente"]["segundo_nombre"]+" "+result[i]["paciente"]["primer_apellido"]+" "+result[i]["paciente"]["segundo_apellido"];
        celdaIdMedico.innerText = result[i]["medico"]["primer_nombre"]+" "+result[i]["medico"]["segundo_nombre"]+" "+result[i]["medico"]["primer_apellido"]+" "+result[i]["medico"]["segundo_apellido"];
        celdaHabitacion.innerText = result[i]["habitacion"];
        celdaCama.innerText = result[i]["cama"];
        celdaFechaIngreso.innerText = result[i]["fecha_ingreso"];
        celdaFechaSalida.innerText = result[i]["fecha_salida"];
        celdaEstado.innerText = result[i]["estado"];


        trResgistro.appendChild(celdaId);
        trResgistro.appendChild(celdaIdPaciente);
        trResgistro.appendChild(celdaIdMedico);
        trResgistro.appendChild(celdaHabitacion);
        trResgistro.appendChild(celdaCama);
        trResgistro.appendChild(celdaFechaIngreso);
        trResgistro.appendChild(celdaFechaSalida);
        trResgistro.appendChild(celdaEstado);


        celdaOpcion.appendChild(botonEditarIngreso);
        trResgistro.appendChild(celdaOpcion)

        celdaOpcion.appendChild(botonDeshabilitarIngreso);
        trResgistro.appendChild(celdaOpcion)

        cuerpoTabla.appendChild(trResgistro);


        //creamos un td por cada campo de resgistro

      }
    },
    error: function (error) {
      /*
      ERROR: funcion que se ejecuta cuando la peticion tiene un error
      */
      alert("Error en la petición " + error);
    }
  })
}

function consultarIngresoID(id){
  //alert(id);
  $.ajax({
      url:url+id,
      type:"GET",
      success: function(result){
          document.getElementById("id_ingreso").value=result["id_ingreso"];
          document.getElementById("id_paciente").value=result["paciente"]["id_paciente"];
          document.getElementById("id_medico").value=result["medico"]["id_medico"];
          document.getElementById("habitacion").value=result["habitacion"];
          document.getElementById("cama").value=result["cama"];
          document.getElementById("fecha_ingreso").value=result["fecha_ingreso"];
          document.getElementById("fecha_salida").value=result["fecha_salida"];
          document.getElementById("estado").value=result["estado"];
      }
  });
}
//2.Crear petición que actualice la información del medico

function actualizarIngreso() { 
  var id_ingreso=document.getElementById("id_ingreso").value
  let formData={ 
    "paciente": document.getElementById("id_paciente").value,
    "medico": document.getElementById("id_medico").value,
    "habitacion": document.getElementById("habitacion").value,
    "cama": document.getElementById("cama").value,
    "fecha_ingreso": document.getElementById("fecha_ingreso").value,
    "fecha_salida": document.getElementById("fecha_salida").value,
    "estado": document.getElementById("estado")

};

if (validarCampos()) {
  $.ajax({
      url:url+id_ingreso,
      type: "PUT",
      data: formData,
      success: function(result) {
          // Manejar la respuesta exitosa según necesites
          Swal.fire({
              title: "¡Excelente!",
              text: "Se guardó correctamente",
              icon: "success"
            });
          // Puedes hacer algo adicional como recargar la lista de médicos
          listarIngreso();
      },
      error: function(error) {
          // Manejar el error de la petición
          Swal.fire({
              title: "¡Error!",
              text: "No se guardó",
              icon: "error"
            });
      }
  });
  } else {
  Swal.fire({
      title: "¡Error!",
      text: "Llene todos los campos correctamente",
      icon: "error"
    });
  }
}

function registrarIngreso() {

  let formData = {
    "paciente": document.getElementById("id_paciente").value,
    "medico": document.getElementById("id_medico").value,
    "habitacion": document.getElementById("habitacion").value,
    "cama": document.getElementById("cama").value,
    "fecha_ingreso": document.getElementById("fecha_ingreso").value,
    "fecha_salida": document.getElementById("fecha_salida").value,
    "estado": document.getElementById("estado")

  };

  if (validarCampos()) {
    $.ajax({
      url: url,
      type: "POST",
      data: formData,
      success: function (result) {
        //
        Swal.fire({
          title: "¡Excelente!",
          text: "Se guardó correctamente",
          icon: "success"
        });
      },
    })
  } else {
    Swal.fire({
      title: "¡Error!",
      text: "Llene todos los campos correctamente",
      icon: "error"
    });
  }
}

//se ejecuta la peticion


function validarCampos() {
  var cama = document.getElementById("cama");
  return validarCama(cama);
}
function validarCama(cuadroNumero) {
  /*
  numero documento 
  min=5
  max=11
  numero entero

  si cumple, se cambia color a verde
  si no, se cambia a rojo
  */
  var valor = cuadroNumero.value;
  var valido = true;
  if (valor.length < 1 || valor.length > 11) {
    valido = false
  }

  if (valido) {
    //cuadro de texto cumple s
    cuadroNumero.className = "form-control is-valid";
  } else {
    //cuadro de texto no cumple
    cuadroNumero.className = "form-control is-invalid";
  }
  return valido;

}

//ValidarIdPaciente
function validarCampos() {
  var id_paciente = document.getElementById("id_paciente");
  return ValidarIdPaciente(id_paciente);
}
function ValidarIdPaciente(cuadroNumero) {
  
  var valor = cuadroNumero.value;
  var valido = true;
  if (valor.length < 1 || valor.length > 20) {
    valido = false
  }

  if (valido) {
    //cuadro de texto cumple
    cuadroNumero.className = "form-control is-valid";
  } else {
    //cuadro de texto no cumple
    cuadroNumero.className = "form-control is-invalid";
  }
  return valido;

}

//validarIdMedico
function validarCampos() {
  var id_medico = document.getElementById("id_medico");
  return validarIdMedico(id_medico);
}
function validarIdMedico(cuadroNumero) {
  
  var valor = cuadroNumero.value;
  var valido = true;
  if (valor.length < 1 || valor.length > 20) {
    valido = false
  }

  if (valido) {
    //cuadro de texto cumple
    cuadroNumero.className = "form-control is-valid";
  } else {
    //cuadro de texto no cumple
    cuadroNumero.className = "form-control is-invalid";
  }
  return valido;

}

//validarHabitacion
function validarCampos() {
  var habitacion = document.getElementById("habitacion");
  return validarHabitacion(habitacion);
}
function validarHabitacion(cuadroNumero) {
  
  var valor = cuadroNumero.value;
  var valido = true;
  if (valor.length < 1 || valor.length > 20) {
    valido = false
  }

  if (valido) {
    //cuadro de texto cumple
    cuadroNumero.className = "form-control is-valid";
  } else {
    //cuadro de texto no cumple
    cuadroNumero.className = "form-control is-invalid";
  }
  return valido;

}

//validarFechaIngreso
function validarCampos() {
  var fecha_ingreso = document.getElementById("fecha_ingreso");
  return validarFechaIngreso(fecha_ingreso);
}
function validarFechaIngreso(cuadroNumero) {
  
  var valor = cuadroNumero.value;
  var valido = true;
  if (valor.length < 10|| valor.length > 10) {
    valido = false
  }

  if (valido) {
    //cuadro de texto cumple
    cuadroNumero.className = "form-control is-valid";
  } else {
    //cuadro de texto no cumple
    cuadroNumero.className = "form-control is-invalid";
  }
  return valido;

}

//validarFechaSalida
function validarCampos() {
  var fecha_salida = document.getElementById("fecha_salida");
  return validarFechaSalida(fecha_salida);
}
function validarFechaSalida(cuadroNumero) {
  
  var valor = cuadroNumero.value;
  var valido = true;
  if (valor.length < 10|| valor.length > 10) {
    valido = false
  }

  if (valido) {
    //cuadro de texto cumple
    cuadroNumero.className = "form-control is-valid";
  } else {
    //cuadro de texto no cumple
    cuadroNumero.className = "form-control is-invalid";
  }
  return valido;

}

//ValidadEstado


function validarCampos() {
  var estado = document.getElementById("estado");
  return validarEstado(estado);
}
function validarEstado(cuadroNumero) {
  
  var valor = cuadroNumero.value;
  var valido = true;
  if (valor.length < 1  || valor.length > 1) {
    valido = false
  }

  if (valido) {
    //cuadro de texto cumple
    cuadroNumero.className = "form-control is-valid";
  } else {
    //cuadro de texto no cumple
    cuadroNumero.className = "form-control is-invalid";
  }
  return valido;

}
function limpiarIngreso() {
  document.getElementById("id_paciente").value = "";
  document.getElementById("id_medico").value = "";
  document.getElementById("habitacion").value = "";
  document.getElementById("cama").value = "";
  document.getElementById("fecha_ingreso").value = "";
  document.getElementById("fecha_salida").value = "";
  document.getElementById("estado").value = "";
}
