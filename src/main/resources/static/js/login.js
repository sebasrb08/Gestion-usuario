(document).ready(function() {

});
  
async function loginUsuarios(){
  
  let datos={};
   datos.email = document.getElementById("txtEmail").value
   datos.password = document.getElementById("txtPassword").value



  const request = await fetch('api/login', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body:JSON.stringify(datos)
    });

    const usuario = await request.text()

    if(usuario != "FAIL"){
        localStorage.token = usuario;
        localStorage.email = datos.email;
        window.location.href="usuarios.html";
    }else{
        alert("Email o password Incorrecto")
    }
  
}