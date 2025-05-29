package vista;

// ----------------------------
// IMPORTACIONES NECESARIAS
// ----------------------------

import javax.swing.*; // Componentes gráficos Swing
import java.awt.*; // Layouts y estilos
import java.awt.event.*; // Gestión de eventos
import java.util.ArrayList; // Lista de preguntas
import java.util.Collections; // Para barajar

import modelo.Jugador; // Clase del jugador
import modelo.Pregunta; // Clase de preguntas
import persistencia.GestorFicheros; // Clase que carga las preguntas desde JSON

/**
 * VENTANA DE LOGIN DONDE EL USUARIO INTRODUCE SU NOMBRE Y COMIENZA LA PARTIDA.
 */
public class VentanaLogin extends JFrame
{
	private static final long serialVersionUID = 1L;
	// COMPONENTES
	private JLabel etiquetaTitulo;
	private JLabel etiquetaNombre;
	private JTextField campoNombre;
	private JButton botonComenzar;
	private JLabel etiquetaError;

	// PREGUNTAS
	private ArrayList<Pregunta> listaPreguntas;

	/**
	 * CONSTRUCTOR DE LA VENTANA DE INICIO.
	 */
	public VentanaLogin()
	{
		setTitle("TriviAlvaro - Inicio");
		setSize(450, 260);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); // Centrar ventana

		cargarPreguntas();
		inicializarComponentes();
		registrarEventos();

		setVisible(true);
	}

	/**
	 * CARGA LAS PREGUNTAS DESDE EL ARCHIVO JSON Y LAS BARAJA.
	 */
	private void cargarPreguntas()
	{
		listaPreguntas = GestorFicheros.cargarPreguntasDesdeJSON("preguntas.json");
		Collections.shuffle(listaPreguntas); // Barajar preguntas al inicio
	}

	/**
	 * CREA Y ESTILIZA LOS COMPONENTES DE LA VENTANA.
	 */
	private void inicializarComponentes()
	{
		setLayout(new BorderLayout());
		getContentPane().setBackground(Color.WHITE);

		// TÍTULO
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBackground(new Color(240, 240, 240));
		etiquetaTitulo = new JLabel("Bienvenido a TriviAlvaro");
		etiquetaTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
		panelTitulo.add(etiquetaTitulo);
		add(panelTitulo, BorderLayout.NORTH);

		// CENTRO
		JPanel panelCentro = new JPanel(new GridLayout(2, 1, 10, 10));
		panelCentro.setBackground(Color.WHITE);
		panelCentro.setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 40));

		etiquetaNombre = new JLabel("Introduce tu nombre:");
		etiquetaNombre.setFont(new Font("Segoe UI", Font.PLAIN, 16));

		campoNombre = new JTextField();
		campoNombre.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		panelCentro.add(etiquetaNombre);
		panelCentro.add(campoNombre);

		add(panelCentro, BorderLayout.CENTER);

		// INFERIOR
		JPanel panelInferior = new JPanel(new GridLayout(2, 1));
		panelInferior.setBackground(Color.WHITE);
		panelInferior.setBorder(BorderFactory.createEmptyBorder(10, 40, 20, 40));

		botonComenzar = new JButton("Comenzar");
		botonComenzar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		botonComenzar.setFocusPainted(false);
		botonComenzar.setBackground(new Color(33, 150, 243));
		botonComenzar.setForeground(Color.WHITE);

		etiquetaError = new JLabel("", SwingConstants.CENTER);
		etiquetaError.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		etiquetaError.setForeground(Color.RED);

		panelInferior.add(botonComenzar);
		panelInferior.add(etiquetaError);

		add(panelInferior, BorderLayout.SOUTH);
	}

	/**
	 * GESTIONA EL EVENTO DEL BOTÓN COMENZAR.
	 */
	private void registrarEventos()
	{
		botonComenzar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent pEvento)
			{
				String nombreJugador = campoNombre.getText().trim();

				if (nombreJugador.isEmpty())
				{
					etiquetaError.setText("Por favor, introduce un nombre.");
				} else
				{
					Jugador jugador = new Jugador(nombreJugador);
					new VentanaJuego(jugador, listaPreguntas); // Abre el juego
					dispose(); // Cierra el login
				}
			}
		});
	}
}
