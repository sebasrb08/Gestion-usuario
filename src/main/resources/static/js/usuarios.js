// Call the dataTables jQuery plugin
$(document).ready(function() {
  cargarUsuarios();
  $('#usuarios').DataTable();
});

const usuario = document.querySelector(".table-usuario")
const iniciar =  document.getElementById("button-iniciar");
const registrar =  document.getElementById("button-registar");



async function cargarUsuarios(){

    const request = await fetch('api/usuarios', {
      method: 'GET',
      headers: getHeader()
    });

    const usuarios = await request.json();

    if(usuarios !=  0){
        iniciar.style.display='none';
        registrar.style.display='none';
    }
    
    for (let i = 0; i < usuarios.length; i++) {
      let botonEliminar=`<a href="#" onclick="eliminarUsuario(${usuarios[i].id})" class="btn btn-danger btn-circle"><i class="fas fa-trash"></i></a>`
      
      usuario.innerHTML+=`
      <tr>
      <td>${usuarios[i].id}</td>
      <td>${usuarios[i].nombre} </td>
      <td>${usuarios[i].email}</td>
      <td>${usuarios[i].telefono}</td>
      <td>${usuarios[i].direccion}</td>
      <td>
      ${botonEliminar}
      </td>
      </tr>
      `
    }


  }

function getHeader(){

    return {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization':localStorage.token 
    }
}
  
async function eliminarUsuario(id){

  const request = await fetch(`api/usuarios/${id}`, {
    method: 'DELETE',
    headers: getHeader()
  });

  location.reload();

}

function cargarRegistrar(){
  window.location.href="registrar.html"
}


function cargarLogin(){
  window.location.href="login.html"
}