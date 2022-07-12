package py.edu.ucom.is2.proyectocamel.routes.transaccion;

import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer extends RouteBuilder  {
	@Autowired
	
	Servicio banco;
	JacksonDataFormat JsonDataFormat;
	
	@Override
	public void configure() throws Exception {
		restConfiguration().component("servlet").bindingMode(RestBindingMode.auto);
		JsonDataFormat   = new JacksonDataFormat(Clase.class);
		
		from("activemq:Acosta-ATLAS-IN")
		.unmarshal(JsonDataFormat)
		.bean(banco)
		.to("log:is2log");
		
		from("activemq:Acosta-Itau-IN")
		.unmarshal(JsonDataFormat)
		.bean(banco)
		.to("log:is2log");
		;
		
}
}