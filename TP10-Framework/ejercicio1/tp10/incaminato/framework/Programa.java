package tp10.incaminato.framework;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Programa extends JFrame {

	private JPanel contentPanePrograma;

	List<Accion> accionesAMostrar;
	private int indice;

	/**
	 * Create the frame.
	 */
	public Programa(String pathConfig) {
		setTitle("Programa Version 2");

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

		// Contruccion de Ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPanePrograma = new JPanel();
		contentPanePrograma.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanePrograma);
		contentPanePrograma.setLayout(null);

		JLabel lblBienvenido = new JLabel("Bienvenido, estas son sus opciones:");
		lblBienvenido.setForeground(Color.BLUE);
		lblBienvenido.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblBienvenido.setBounds(10, 11, 409, 35);
		contentPanePrograma.add(lblBienvenido);

	}

	public void ejecutar() {

		this.setVisible(true);

		this.indice = mostrarAcciones();

	}

	private int mostrarAcciones() {
		int indice = 1;
		int posicionY = 57;
		for (Accion accion : accionesAMostrar) {

			JButton btnAccion = new JButton(accion.nombreItemMenu());
			btnAccion.setBounds(20, posicionY, 153, 23);
			contentPanePrograma.add(btnAccion);

			// Acciones del boton
			btnAccion.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					accion.ejecutar();

				}
			});

			JLabel lblDescAccion = new JLabel(accion.descripcionItemMenu());
			lblDescAccion.setBounds(194, posicionY, 225, 23);
			contentPanePrograma.add(lblDescAccion);

			indice = indice + 1;
			posicionY = posicionY + 25;
		}

		JButton btnAccion = new JButton("Salir");
		btnAccion.setBounds(20, posicionY, 153, 23);
		contentPanePrograma.add(btnAccion);
		btnAccion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();

			}
		});

		return indice;
	}

}
