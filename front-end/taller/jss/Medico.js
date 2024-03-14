var url = "http://localhost:8080/api/v1/medico/";

function listarMedico() {
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
        let celdaTipoDocumento = document.createElement("td")
        let celdaNumeroDocumento = document.createElement("td")
        let celdaPrimerNombre = document.createElement("td")
        let celdaSegundoNombre = document.createElement("td")
        let celdaPrimerApellido = document.createElement("td")
        let celdaSegundoApellido = document.createElement("td")
        let celdaTelefono = document.createElement("td")
        let celdaCorreo = document.createElement("td")
        let celdaDireccion = document.createElement("td")
        let celdaEstado = document.createElement("td")

        let celdaOpcion = document.createElement("td");
        let botonEditarMedico = document.createElement("button");
        botonEditarMedico.value=result[i]["id_medico"];
        botonEditarMedico.innerHTML = "Editar";
        
        botonEditarMedico.onclick=function(e){
          $('#exampleModal').modal('show');
          consultarMedicoID(this.value);
        }

        botonEditarMedico.className = "btn btn-warning editar-medico";

        let botonDeshabilitarMedico = document.createElement("button");
        botonDeshabilitarMedico.innerHTML = "Deshabilitar"
        botonDeshabilitarMedico.className = "btn btn-danger"

        celdaId.innerText = result[i]["id_medico"];
        celdaTipoDocumento.innerText = result[i]["tipo_documento"];
        celdaNumeroDocumento.innerText = result[i]["numero_documento"];
        celdaPrimerNombre.innerText = result[i]["primer_nombre"];
        celdaSegundoNombre.innerText = result[i]["segundo_nombre"];
        celdaPrimerApellido.innerText = result[i]["primer_apellido"];
        celdaSegundoApellido.innerText = result[i]["segundo_apellido"];
        celdaTelefono.innerText = result[i]["telefono"];
        celdaCorreo.innerText = result[i]["correo"];
        celdaDireccion.innerText = result[i]["direccion"];
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
        trResgistro.appendChild(celdaEstado);


        celdaOpcion.appendChild(botonEditarMedico);
        trResgistro.appendChild(celdaOpcion)

        celdaOpcion.appendChild(botonDeshabilitarMedico);
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

function consultarMedicoID(id){
  //alert(id);
  $.ajax({
      url:url+id,
      type:"GET",
      success: function(result){
          document.getElementById("id_medico").value=result["id_medico"];
          document.getElementById("tipo_documento").value=result["tipo_documento"];
          document.getElementById("numero_documento").value=result["numero_documento"];
          document.getElementById("primer_nombre").value=result["primer_nombre"];
          document.getElementById("segundo_nombre").value=result["segundo_nombre"];
          document.getElementById("primer_apellido").value=result["primer_apellido"];
          document.getElementById("segundo_apellido").value=result["segundo_apellido"];
          document.getElementById("telefono").value=result["telefono"];
          document.getElementById("correo").value=result["correo"];
          document.getElementById("direccion").value=result["direccion"];
          document.getElementById("estado").value=result["estado"];
      }
  });
}
//2.Crear petición que actualice la información del medico

function actualizarMedico() { 
  var id_medico=document.getElementById("id_medico").value
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
      "estado": document.getElementById("estado").value
};

if (validarCampos()) {
  $.ajax({
      url:url+id_medico,
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
          listarMedico();
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

  

function registrarMedico() {

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
    "estado": document.getElementById("estado").value

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

//Validadnombre

function validarCampos() {
  var primer_nombre = document.getElementById("primer_nombre");
  return validarPrimer_nombre(primer_nombre);
}
function validarPrimer_nombre(cuadroNumero) {

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
  if (valor.length < 5 || valor.length > 200) {
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
function limpiarMedico() {
  document.getElementById("tipo_documento").value = "";
  document.getElementById("numero_documento").value = "";
  document.getElementById("primer_nombre").value = "";
  document.getElementById("segundo_nombre").value = "";
  document.getElementById("primer_apellido").value = "";
  document.getElementById("segundo_apellido").value = "";
  document.getElementById("telefono").value = "";
  document.getElementById("correo").value = "";
  document.getElementById("direccion").value = "";
  document.getElementById("estado").value = "";
}


