package py.edu.ucom.is2.proyectocamel.routes.transaccion;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExtraC extends RouteBuilder{
    @Autowired
	
	Servicio service;
	JacksonDataFormat JsonDataFormat;
	@Override
	public void configure() throws Exception {
		restConfiguration().component("servlet").bindingMode(RestBindingMode.auto);
		
		
		from("activemq:ACOSTA-ATLAS-OUT")		
		.setExchangePattern(ExchangePattern.InOut)
		.transform().simple("${body}");
}
}
