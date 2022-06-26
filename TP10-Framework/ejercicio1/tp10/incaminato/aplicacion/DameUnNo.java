package tp10.incaminato.aplicacion;

import tp10.incaminato.framework.Accion;

public class DameUnNo implements Accion {

	@Override
	public void ejecutar() {
		System.out.println("Noooooo");

	}

	@Override
	public String nombreItemMenu() {

		return "Quiero un no. ";
	}

	@Override
	public String descripcionItemMenu() {

		return "(Esta opcion si queres un nooooo)";
	}

}
