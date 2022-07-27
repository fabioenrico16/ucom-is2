package py.edu.ucom.is2.proyectocamel.routes.transaccion;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class Rest extends RouteBuilder {

    Servicio servicio;
	JacksonDataFormat JsonDataFormat;


	@Override
	public void configure() throws Exception {
		
		restConfiguration().component("servlet").bindingMode(RestBindingMode.off);
		rest().path("/api")
		.consumes("application/json")
		.produces("application/json")	
		
			
			.post("/transferencia")
				.type(Clase.class)
				.outType(Response.class)
			.to("direct:procesar-transferencia");		
		
		
		from("direct:procesar-transferencia")
			.bean(servicio,"validar")
			.filter().method(Filtro.class,"validar").stop().end();

			from("direct:procesarSwitch")
		
		
			.choice()
				.when(header("BANCO_DESTINO").contains("BANCOA"))
					.setExchangePattern(ExchangePattern.InOnly)						
					.marshal(JsonDataFormat)
					.log("${body}")
					.to("activemq:ACOSTA-ITAU-IN")
					.endChoice()
					
				.when(header("BANCO_DESTINO").contains("BANCOB"))
					.setExchangePattern(ExchangePattern.InOnly)
					.marshal(JsonDataFormat)				
					.to("activemq:ACOSTA-ATLAS-IN")
					.endChoice()
					
				.when(header("BANCO_DESTINO").contains("BANCOC"))
					.setExchangePattern(ExchangePattern.InOnly)
					.marshal(JsonDataFormat)				
					.to("activemq:ACOSTA-BASA-IN").endChoice()
				.otherwise()
				
		 ;
		
		
		
		
	}

}




