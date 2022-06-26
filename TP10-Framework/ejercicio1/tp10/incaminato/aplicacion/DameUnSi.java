package tp10.incaminato.aplicacion;

import tp10.incaminato.framework.Accion;

public class DameUnSi implements Accion {

	@Override
	public void ejecutar() {
		System.out.println("Siiiiiiiii");

	}

	@Override
	public String nombreItemMenu() {

		return "Quiero un si. ";
	}

	@Override
	public String descripcionItemMenu() {

		return "(Esta opcion si queres un siiii)";
	}

}
