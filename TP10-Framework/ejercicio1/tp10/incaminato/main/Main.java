package tp10.incaminato.main;

import tp10.incaminato.framework.Programa;

public class Main {

	public static void main(String[] args) {

		Programa programa = new Programa("/frw/config/config.properties");
		programa.ejecutar();

	}

}
