package py.edu.ucom.is2.proyectocamel.routes.transaccion;
import org.springframework.stereotype.Component;


@Component
public class Servicio {

	
		public String insertarBanco(Clase banco ) {
				Response respuesta = new Response();
				//CONECTARSE A LA BASE DE DATOS
				respuesta.setMensaje("Banco de Origen: "+banco.getBANCO_ORIGEN() + " Banco Destino; "+ banco.getBANCO_DESTINO() + " Monto:" + banco.getMONTO() + " Cuenta:" + banco.getCUENTA());
				return respuesta.getMensaje() + "Transferencia Exitosa";
		}
	}

