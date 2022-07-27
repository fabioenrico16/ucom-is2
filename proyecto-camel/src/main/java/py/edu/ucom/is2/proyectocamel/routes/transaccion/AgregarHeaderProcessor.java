package py.edu.ucom.is2.proyectocamel.routes.transaccion;

import java.util.Map;

import org.apache.camel.Exchange;

import org.apache.camel.Processor;



public class AgregarHeaderProcessor implements Processor {
	@Override
	public void process(Exchange exchange) throws Exception {
		Clase clase = (Clase)exchange.getIn().getBody();
		Map<String, Object> headers = exchange.getIn().getHeaders();		
		headers.put("BANCO_ORIGEN",clase.BANCO_ORIGEN);
		exchange.getIn().setHeaders(headers);
}
}
