package py.edu.ucom.is2.proyectocamel.routes.transaccion;

public class Response {
	String mensaje;
	String idtransaccion;

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public Response Valido (Clase banco) {
		Response respuesta = new Response();
		this.mensaje = "Mensaje Encolado";
		this.idtransaccion =String.valueOf(banco.ID);  
		respuesta.setMensaje(mensaje);
		respuesta.setIdtransaccion(idtransaccion);
		return respuesta;
	}
	
	public Response Rechazado (Clase banco) {
		Response respuesta = new Response();
		this.mensaje = "Rechazado";
		this.idtransaccion =String.valueOf(banco.ID);  
		respuesta.setMensaje(mensaje);
		respuesta.setIdtransaccion(idtransaccion);
		return respuesta;
	}

	public String getIdtransaccion() {
		return idtransaccion;
	}

	public void setIdtransaccion(String idtransaccion) {
		this.idtransaccion = idtransaccion;
	}
}