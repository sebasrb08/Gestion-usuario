(document).ready(function() {

  });
    
async function registrarUsuarios(){
    
    let datos={};

     datos.nombre = document.getElementById("txtNombre").value
     datos.email = document.getElementById("txtEmail").value
     datos.telefono = document.getElementById("txtTelefono").value
     datos.direccion = document.getElementById("txtDireccion").value
     datos.password = document.getElementById("txtPassword").value

     let repeatPassword = document.getElementById("txtRepeatPassword").value

     if(repeatPassword != datos.password){
        alert("La contraseña es diferente")
        return;

     }

    const request = await fetch('api/usuarios', {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body:JSON.stringify(datos)
      });

      alert("Se creado la cuenta con exito ");
      window.location.href="usuarios.html";

}