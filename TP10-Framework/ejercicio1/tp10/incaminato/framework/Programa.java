package tp10.incaminato.framework;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

/*
 * Framework de caja negra configurable via archivo de propiedades.
 * 
 * Implemente la interfaz Accion - Genere un archivo llamado config.properties
 * con lo siguiente: clase = paquete.clase #con su clase que implementa la
 * interfaz accion - Cree un paquete de nombre frw.config y guarde el archivo allì.
 */

public class Programa {

	List<Accion> accionesAMostrar;
	private int indice;

	public Programa(String pathConfig) {
		if (pathConfig == null)
			throw new RuntimeException("Necestias pasarme un path del archivo de configuracion");

		Properties prop = new Properties();
		accionesAMostrar = new ArrayList<>();
		try (InputStream configFile = getClass().getResourceAsStream(pathConfig);) {
			prop.load(configFile);
			String clase = prop.getProperty("clase");
			String[] clases = clase.split(";");
			// Class c = Class.forName(clases[0]);
			List<Class> listaClases = new ArrayList<Class>();

			for (String string : clases) {
				Class c = Class.forName(string);
				listaClases.add(c);
			}

			for (Class lista : listaClases) {
				Accion a = (Accion) lista.getDeclaredConstructor().newInstance();
				accionesAMostrar.add(a);
			}

		} catch (Exception e) {
			throw new RuntimeException("No pude crear la instancia de Accion... ", e);
		}
	}

	public void ejecutar() {

		System.out.println("Bienvenido, estas son sus opciones:");
		System.out.println();
		this.indice = mostrarAcciones();

		System.out.println();
		System.out.print("Ingrese su opción: ");
		Scanner reader = new Scanner(System.in);

		int entrada = obtenerEntrada(reader);

		verificarAccionValida(entrada);

		verAccionSeleccionada(entrada);

		// this.ejecutar();

	}

	private int obtenerEntrada(Scanner reader) {
		int entrada = 0;
		try {
			entrada = reader.nextInt();
			System.out.println();
		} catch (Exception e) {
			throw new RuntimeException("Ingrese un valor numerico", e);
		}
		return entrada;
	}

	private int mostrarAcciones() {
		int indice = 1;
		for (Accion accion : accionesAMostrar) {
			System.out.println(indice + ". " + accion.nombreItemMenu() + accion.descripcionItemMenu());
			indice = indice + 1;
		}
		return indice;
	}

	private void verificarAccionValida(int entrada) {
		if (!verificarEntrada(entrada)) {
			System.out.println("Ingrese una accion valida");
			System.out.println();
			this.ejecutar();
		}

	}

	private void verAccionSeleccionada(int entrada) {

		if (entrada != this.indice - 1) { // aca entra si no seleciona Salir
			accionesAMostrar.get(entrada - 1).ejecutar();
			System.out.println();
			this.ejecutar();
		} else { // preguntar como sacar este else
			accionesAMostrar.get(entrada - 1).ejecutar();
			System.exit(0);
		}

	}

	private boolean verificarEntrada(int entrada) {
		if (entrada > 0 && entrada < this.indice) {
			return true;
		}

		return false;

	}

}
