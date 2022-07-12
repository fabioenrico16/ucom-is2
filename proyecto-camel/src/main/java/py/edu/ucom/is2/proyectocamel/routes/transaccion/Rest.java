package py.edu.ucom.is2.proyectocamel.routes.transaccion;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class Rest extends RouteBuilder {




	@Override
	public void configure() throws Exception {
		
		restConfiguration().component("servlet").bindingMode(RestBindingMode.off);
		rest().path("/api")
		.consumes("application/json")
		.produces("application/json")	
		
			
			.post("/transferencia")
			.to("direct:procesar-transferencia");		
		
		
		from("direct:procesar-transferencia")
		.setExchangePattern(ExchangePattern.InOnly) 
			.choice()
			.when(header("BANCO_DESTINO").contains("ATLAS")).to("activemq:Canclini-ATLAS-IN").endChoice()
			.when(header("BANCO_DESTINO").contains("ITAU")).to("activemq:Canclini-ITAU-IN").endChoice()
			.otherwise()
				.transform().constant("El valor enviado no ex valido")
		.setExchangePattern(ExchangePattern.InOut) ;
		
		
		
		
	}

}

//http://localhost:8080/camel/api/enviar



