var url = "http://localhost:8080/api/v1/paciente/";

function listarPaciente() {
  //METODO PARA LISTAR LOS CLIENTES
  //SE CREA LA PETICION AJAX
  var capturarFiltro = document.getElementById("inputSearch").value;
  var urlLocal=url;
  if (capturarFiltro!=""){
    urlLocal+="busquedafiltro/"+capturarFiltro;
  }
  $.ajax({
    url: urlLocal,
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
        let celdaTipoDocumento = document.createElement("td")
        let celdaNumeroDocumento = document.createElement("td")
        let celdaPrimerNombre = document.createElement("td")
        let celdaSegundoNombre = document.createElement("td")
        let celdaPrimerApellido = document.createElement("td")
        let celdaSegundoApellido = document.createElement("td")
        let celdaTelefono = document.createElement("td")
        let celdaCorreo = document.createElement("td")
        let celdaDireccion = document.createElement("td")
        let celdaNombre_persona_contacto = document.createElement("td")
        let celdaTelefono_persona_contacto = document.createElement("td")
        let celdaEstado = document.createElement("td")

        let celdaOpcion = document.createElement("td");
        let botonEditarPaciente = document.createElement("button");
        botonEditarPaciente.value=result[i]["id_paciente"];
        botonEditarPaciente.innerHTML = "Editar";

        
        botonEditarPaciente.onclick=function(e){
          $('#exampleModal').modal('show');
          consultarPacienteID(this.value);
        }
        botonEditarPaciente.className = "btn btn-warning editar-paciente";


        

        celdaId.innerText = result[i]["id_paciente"];
        celdaTipoDocumento.innerText = result[i]["tipo_documento"];
        celdaNumeroDocumento.innerText = result[i]["numero_documento"];
        celdaPrimerNombre.innerText = result[i]["primer_nombre"];
        celdaSegundoNombre.innerText = result[i]["segundo_nombre"];
        celdaPrimerApellido.innerText = result[i]["primer_apellido"];
        celdaSegundoApellido.innerText = result[i]["segundo_apellido"];
        celdaTelefono.innerText = result[i]["telefono"];
        celdaCorreo.innerText = result[i]["correo"];
        celdaDireccion.innerText = result[i]["direccion"];
        celdaNombre_persona_contacto.innerText = result[i]["nombre_persona_contacto"];
        celdaTelefono_persona_contacto.innerText = result[i]["telefono_persona_contacto"];
        celdaEstado.innerText = result[i]["estado"];


        trResgistro.appendChild(celdaId);
        trResgistro.appendChild(celdaTipoDocumento);
        trResgistro.appendChild(celdaNumeroDocumento);
        trResgistro.appendChild(celdaPrimerNombre);
        trResgistro.appendChild(celdaSegundoNombre);
        trResgistro.appendChild(celdaPrimerApellido);
        trResgistro.appendChild(celdaSegundoApellido);
        trResgistro.appendChild(celdaTelefono);
        trResgistro.appendChild(celdaCorreo);
        trResgistro.appendChild(celdaDireccion);
        trResgistro.appendChild(celdaNombre_persona_contacto);
        trResgistro.appendChild(celdaTelefono_persona_contacto);
        trResgistro.appendChild(celdaEstado);


        celdaOpcion.appendChild(botonEditarPaciente);
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

function consultarPacienteID(id){
  //alert(id);
  $.ajax({
      url:url+id,
      type:"GET",
      success: function(result){
          document.getElementById("id_paciente").value=result["id_paciente"];
          document.getElementById("tipo_documento").value=result["tipo_documento"];
          document.getElementById("numero_documento").value=result["numero_documento"];
          document.getElementById("primer_nombre").value=result["primer_nombre"];
          document.getElementById("segundo_nombre").value=result["segundo_nombre"];
          document.getElementById("primer_apellido").value=result["primer_apellido"];
          document.getElementById("segundo_apellido").value=result["segundo_apellido"];
          document.getElementById("telefono").value=result["telefono"];
          document.getElementById("correo").value=result["correo"];
          document.getElementById("direccion").value=result["direccion"];
          document.getElementById("nombre_persona_contacto").value=result["nombre_persona_contacto"];
          document.getElementById("telefono_persona_contacto").value=result["telefono_persona_contacto"];
          document.getElementById("estado").value=result["estado"];
      }
  });
}
//2.Crear petición que actualice la información del medico

function actualizarPaciente() { 
  var id_paciente=document.getElementById("id_paciente").value
  let formData={
      "tipo_documento": document.getElementById("tipo_documento").value,
      "numero_documento": document.getElementById("numero_documento").value,
      "primer_nombre": document.getElementById("primer_nombre").value,
      "segundo_nombre": document.getElementById("segundo_nombre").value,
      "primer_apellido": document.getElementById("primer_apellido").value,
      "segundo_apellido": document.getElementById("segundo_apellido").value,
      "telefono": document.getElementById("telefono").value,
      "correo": document.getElementById("correo").value,
      "direccion": document.getElementById("direccion").value,
      "nombre_persona_contacto": document.getElementById("nombre_persona_contacto").value,
      "telefono_persona_contacto": document.getElementById("telefono_persona_contacto").value,
      "estado": document.getElementById("estado").value
};

if (validarCampos()) {
  $.ajax({
      url:url+id_paciente,
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
          listarPaciente();
      },
      
      error: function(error) {
          // Manejar el error de la petición
          Swal.fire({
              title: "¡Error!",
              text: "No se guardó",
              icon: "error"
            });
      },
      error: function (error) {
        Swal.fire("Error", "Error al guardar, " + error.responseText, "error");
    }
  });
  } else {
  Swal.fire({
      title: "¡Error!",
      text: "Llene todos los campos correctamente",
      icon: "error"
    });
  }
  function validarCampos() {
    // Obtener los valores de los campos
    var tipo_documento = document.getElementById("tipo_documento").value;
    var numero_documento = document.getElementById("numero_documento").value;
    var primer_nombre = document.getElementById("primer_nombre").value;
    var segundo_nombre = document.getElementById("segundo_nombre").value;
    var primer_apellido = document.getElementById("primer_apellido").value;
    var segundo_apellido = document.getElementById("segundo_apellido").value;
    var telefono = document.getElementById("telefono").value;
    var correo = document.getElementById("correo").value;
    var direccion = document.getElementById("direccion").value;
    var estado = document.getElementById("estado").value;
  
    // Verificar si algún campo está vacío
    if (tipo_documento === '' || numero_documento === '' || primer_nombre === '' || primer_apellido === '' || telefono === '' || correo === '' || direccion === '' || estado === '') {
      return false; // Al menos un campo está vacío
    } else {
      return true; // Todos los campos están llenos
    }
  }
  
}
function registrarPaciente() {

  let formData = {
    "tipo_documento": document.getElementById("tipo_documento").value,
    "numero_documento": document.getElementById("numero_documento").value,
    "primer_nombre": document.getElementById("primer_nombre").value,
    "segundo_nombre": document.getElementById("segundo_nombre").value,
    "primer_apellido": document.getElementById("primer_apellido").value,
    "segundo_apellido": document.getElementById("segundo_apellido").value,
    "telefono": document.getElementById("telefono").value,
    "correo": document.getElementById("correo").value,
    "direccion": document.getElementById("direccion").value,
    "nombre_persona_contacto": document.getElementById("nombre_persona_contacto").value,
    "telefono_persona_contacto": document.getElementById("telefono_persona_contacto").value,
    "estado": document.getElementById("estado").value

  };
 
  let camposValidos = true;
  let camposRequeridos = [
      "tipo_documento",
      "numero_documento",
      "primer_nombre",
      "primer_apellido",
      "telefono",
      "correo",
      "direccion",
      "nombre_persona_contacto",
      "telefono_persona_contacto",
      "estado"
  ];

  camposRequeridos.forEach(function(campo) {
      let valorCampo = document.getElementById(campo).value.trim();
      if (valorCampo === "") {
          camposValidos = false;
          return false; // Terminar la iteración si se encuentra un campo vacío
      }
  });

  if (camposValidos) {
      $.ajax({
          url: url,
          type: "POST",
          data: formData,
          success: function (result) {
              Swal.fire({
                  title: "¡Excelente!",
                  text: "Se guardó correctamente",
                  icon: "success"
              });
              limpiarPaciente();
          },
          error: function (error) {
              Swal.fire("Error", "Error al guardar, " + error.responseText, "error");
          },
      });

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
  var numero_documento = document.getElementById("numero_documento");
  return validarNumeroDocumento(numero_documento);
}
function validarNumeroDocumento(cuadroNumero) {
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
  if (valor.length < 5 || valor.length > 11) {
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

//validarNombre
function validarCampos() {
  var primer_nombre = document.getElementById("primer_nombre");
  return validarPrimer_nombre(primer_nombre);
}
function validarPrimer_nombre(cuadroNumero) {
  
  var valor = cuadroNumero.value;
  var valido = true;
  if (valor.length < 1|| valor.length > 20) {
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
//ValidadApellido
function validarCampos() {
  var primer_apellido = document.getElementById("primer_apellido");
  return validarPrimer_apellido(primer_apellido);
}
function validarPrimer_apellido(cuadroNumero) {
  
  var valor = cuadroNumero.value;
  var valido = true;
  if (valor.length < 1 || valor.length > 11) {
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
  
//ValidadTelefono
function validarCampos() {
  var telefono = document.getElementById("telefono");
  return validarTelefono(telefono);
}
function validarTelefono(cuadroNumero) {
  
  var valor = cuadroNumero.value;
  var valido = true;
  if (valor.length < 10 || valor.length > 15) {
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

//ValidadCorreo
function validarCampos() {
  var correo = document.getElementById("correo");
  return validarCorreo(correo);
}
function validarCorreo(cuadroNumero) {
  
  var valor = cuadroNumero.value;
  var valido = true;
  if (valor.length < 5 || valor.length > 100) {
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
//ValidadDireccion


function validarCampos() {
  var direccion = document.getElementById("direccion");
  return validarDireccion(direccion);
}
function validarDireccion(cuadroNumero) {
  
  var valor = cuadroNumero.value;
  var valido = true;
  if (valor.length < 10  || valor.length > 200) {
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

//validadNomPersonaContacto


function validarCampos() {
  var nombre_persona_contacto = document.getElementById("nombre_persona_contacto");
  return validadNomPersonaContacto(nombre_persona_contacto);
}
function validadNomPersonaContacto(cuadroNumero) {
  
  var valor = cuadroNumero.value;
  var valido = true;
  if (valor.length < 1|| valor.length > 20) {
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

//validadTelPersonaContacto


function validarCampos() {
  var telefono_persona_contacto = document.getElementById("telefono_persona_contacto");
  return validadTelPersonaContacto(telefono_persona_contacto);
}
function validadTelPersonaContacto(cuadroNumero) {
  
  var valor = cuadroNumero.value;
  var valido = true;
  if (valor.length < 10  || valor.length > 15) {
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
//ValidadEstado v


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


function limpiarPaciente() {
  document.getElementById("numero_documento").className="form-control";
  document.getElementById("primer_nombre").className="form-control";
  document.getElementById("primer_apellido").className="form-control";
  document.getElementById("telefono").className="form-control";
  document.getElementById("correo").className="form-control";
  document.getElementById("direccion").className="form-control";
  document.getElementById("nombre_persona_contacto").className="form-control";
  document.getElementById("telefono_persona_contacto").className="form-control";
  document.getElementById("estado").className="form-control";
  document.getElementById("tipo_documento").value = "";
  document.getElementById("numero_documento").value = "";
  document.getElementById("primer_nombre").value = "";
  document.getElementById("segundo_nombre").value = "";
  document.getElementById("primer_apellido").value = "";
  document.getElementById("segundo_apellido").value = "";
  document.getElementById("telefono").value = "";
  document.getElementById("correo").value = "";
  document.getElementById("direccion").value = "";
  document.getElementById("nombre_persona_contacto").value = "";
  document.getElementById("telefono_persona_contacto").value = "";
  document.getElementById("estado").value = "";
}