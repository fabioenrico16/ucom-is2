package py.edu.ucom.is2.proyectocamel.routes.transaccion;

import java.sql.Date;

import javax.print.DocFlavor.STRING;

public class Clase {
public String BANCO_ORIGEN;
public String CUENTA;
public Integer MONTO;
public String BANCO_DESTINO;
public String FECHA;

public String getBANCO_ORIGEN() {
	return BANCO_ORIGEN;
}


public void setBANCO_ORIGEN(String bANCO_ORIGEN) {
	BANCO_ORIGEN = bANCO_ORIGEN;
}


public String getCUENTA() {
	return CUENTA;
}


public void setCUENTA(String cUENTA) {
	CUENTA = cUENTA;
}


public Integer getMONTO() {
	return MONTO;
}


public void setMONTO(Integer mONTO) {
	MONTO = mONTO;
}


public String getBANCO_DESTINO() {
	return BANCO_DESTINO;
}


public void setBANCO_DESTINO(String bANCO_DESTINO) {
	BANCO_DESTINO = bANCO_DESTINO;
}


public Clase() {
	
}
public String toString() {
	return "Se ha recibido correctamente" + this.BANCO_DESTINO.toString();

}
}
