package py.edu.ucom.is2.proyectocamel.routes.transaccion;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class Filtro {

		public static boolean validar(Clase clase) {
			if (clase.MONTO < 20000000) {
				return true;
			}
			else
			{
				return false;
			}
		}
		
		public static boolean validarFecha(Clase clase) {	

			String output = LocalDate.now().toString();
			DateTimeFormatter f = DateTimeFormatter.ofPattern( "uuuu-MM-dd" );

			LocalDate start = LocalDate.parse( clase.FECHA , f ) ;
			LocalDate stop = LocalDate.parse( output , f ) ;
			boolean siNo = start.isEqual( stop ) ;
			
			return siNo;
		}
}
