package py.edu.ucom.is2.proyectocamel.routes.transaccion;

import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.camel.model.dataformat.JsonLibrary;


@Component
public class Consumer extends RouteBuilder  {
	@Autowired
	
	Servicio banco;
	JacksonDataFormat JsonDataFormat;
	
	@Override
	public void configure() throws Exception {
		restConfiguration().component("servlet").bindingMode(RestBindingMode.auto);
		JsonDataFormat   = new JacksonDataFormat(Clase.class);
		
		from("activemq:ACOSTA-ITAU-IN")		
			.unmarshal(JsonDataFormat)		
			.process(new AgregarHeaderProcessor())
				.filter().method(Filtro.class,"validarFecha")
			.to("direct:procesartransitau")
			.stop()
		.end()
				.to("direct:noPasaitau");
		
		
			from("direct:procesartransitau")
			
			.choice()
			.when(header("BANCO_ORIGEN").contains("ATLAS"))	
				.bean(Response.class,"Valido")
				.setExchangePattern(ExchangePattern.InOnly)
				.marshal().json(JsonLibrary.Jackson)				
				.to("activemq:ACOSTA-ATLAS-OUT?explicitQosEnabled=true&timeToLive=1000")
				.setExchangePattern(ExchangePattern.InOut)
				.transform().simple("${body}")
			.endChoice()			
		
				.when(header("BANCO_ORIGEN").contains("BASA"))	
				.bean(Response.class,"Valido")
				.setExchangePattern(ExchangePattern.InOnly)
				.marshal().json(JsonLibrary.Jackson)
				.log("${body}")
				.to("activemq:ACOSTA-BASA-OUT?explicitQosEnabled=true&timeToLive=1000")
				.setExchangePattern(ExchangePattern.InOut)
				.transform().simple("${body}")
				
			.endChoice()		
			
			.otherwise()
				.bean(Response.class,"Rechazado")
				.setExchangePattern(ExchangePattern.InOut)
				.marshal().json(JsonLibrary.Jackson)				
				.transform().simple("${body}");				
				
		from("direct:noPasaitau")
		.bean(Response.class,"Rechazado")
		.setExchangePattern(ExchangePattern.InOut)
		.marshal().json(JsonLibrary.Jackson);
			
			
}
}
