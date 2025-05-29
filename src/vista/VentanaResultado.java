package vista;

// ----------------------------
// IMPORTACIONES NECESARIAS
// ----------------------------

import javax.swing.*;
// Componentes básicos de la interfaz gráfica

import java.awt.*;
// Para el diseño de los layouts y colores

import java.awt.event.*;
// Para gestionar eventos de botones

import modelo.Jugador;
// Clase que contiene nombre y puntuación del jugador

import bbdd.EstadisticasDAO;
// Clase que permite guardar puntuaciones en la BBDD

/**
 * VENTANA FINAL DE RESULTADOS QUE MUESTRA LA PUNTUACIÓN DEL JUGADOR Y ACCIONES POSIBLES.
 */
public class VentanaResultado extends JFrame
{
	private static final long serialVersionUID = 1L;


	// COMPONENTES DE INTERFAZ
	private JLabel etiquetaTitulo;
	private JLabel etiquetaNombre;
	private JLabel etiquetaPuntuacion;
	private JButton botonVolverAJugar;
	private JButton botonRanking;
	private JButton botonSalir;

	/**
	 * CONSTRUCTOR QUE RECIBE AL JUGADOR Y MUESTRA SUS RESULTADOS FINALES.
	 *
	 * @param pJugador Jugador actual con su nombre y puntuación final.
	 */
	public VentanaResultado(Jugador pJugador)
	{
		setTitle("Resultado Final");
		setSize(450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // CENTRADO EN PANTALLA

		inicializarComponentes(pJugador);
		registrarEventos();

		// GUARDAMOS LA PUNTUACIÓN EN LA BASE DE DATOS
		EstadisticasDAO.guardarEstadistica(pJugador, pJugador.getPuntuacion());

		setVisible(true);
	}

	/**
	 * CONFIGURA LOS ELEMENTOS VISUALES DE LA VENTANA.
	 */
	private void inicializarComponentes(Jugador pJugador)
	{
		setLayout(new GridLayout(6, 1, 10, 10));
		getContentPane().setBackground(Color.WHITE);

		etiquetaTitulo = new JLabel("¡Partida finalizada!", SwingConstants.CENTER);
		etiquetaTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
		add(etiquetaTitulo);

		etiquetaNombre = new JLabel("Jugador: " + pJugador.getNombre(), SwingConstants.CENTER);
		etiquetaNombre.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		add(etiquetaNombre);

		etiquetaPuntuacion = new JLabel("Puntuación: " + pJugador.getPuntuacion(), SwingConstants.CENTER);
		etiquetaPuntuacion.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		add(etiquetaPuntuacion);

		botonVolverAJugar = new JButton("Volver a jugar");
		estilizarBoton(botonVolverAJugar, new Color(76, 175, 80)); // Verde
		add(botonVolverAJugar);

		botonRanking = new JButton("Mostrar Ranking");
		estilizarBoton(botonRanking, new Color(33, 150, 243)); // Azul
		add(botonRanking);

		botonSalir = new JButton("Salir del juego");
		estilizarBoton(botonSalir, new Color(220, 53, 69)); // Rojo
		add(botonSalir);
	}

	/**
	 * APLICA ESTILO A LOS BOTONES DE LA INTERFAZ.
	 */
	private void estilizarBoton(JButton boton, Color colorFondo)
	{
		boton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		boton.setBackground(colorFondo);
		boton.setForeground(Color.WHITE);
		boton.setFocusPainted(false);
		boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
	}

	/**
	 * ASIGNA FUNCIONALIDAD A LOS BOTONES.
	 */
	private void registrarEventos()
	{
		// VOLVER A JUGAR (abre ventana de login)
		botonVolverAJugar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new VentanaLogin(); // REINICIAMOS EL JUEGO
				dispose();
			}
		});

		// MOSTRAR RANKING (abre nueva ventana independiente)
		botonRanking.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new VentanaRanking(); // VENTANA INDEPENDIENTE
			}
		});

		// SALIR DE LA APLICACIÓN
		botonSalir.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0); // TERMINA LA APP
			}
		});
	}
}
