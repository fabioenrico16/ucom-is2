package py.edu.ucom.is2.proyectocamel.routes.transaccion;
import org.springframework.stereotype.Component;
import java.util.Random;




@Component
public class Servicio {

	
		
		public int id;
	
		public Clase validar(Clase clase ) {
			Random r = new Random();
		    ;
			clase.ID = r.nextInt((1000 - 1) + 1) ;
			
				return clase;
				
		}
		
		public int id(Clase clase ) {
		
				return clase.ID;
				
		}


	}
	

