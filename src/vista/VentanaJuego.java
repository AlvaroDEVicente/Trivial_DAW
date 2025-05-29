package vista;

// ----------------------------
// IMPORTACIONES NECESARIAS
// ----------------------------

import javax.swing.*; // Componentes de interfaz gráfica
import java.awt.*; // Layouts y estilos visuales
import java.awt.event.*; // Eventos
import java.util.ArrayList; // Lista de preguntas
import java.util.Collections; // Aleatorizar preguntas

import modelo.Jugador;
import modelo.Pregunta;

/**
 * CLASE PRINCIPAL DE JUEGO: Muestra preguntas con opciones, categoría y dificultad. Incluye un temporizador por pregunta mediante hilos y estadísticas básicas.
 */
public class VentanaJuego extends JFrame
{
	private static final long serialVersionUID = 1L;

	// COMPONENTES DE LA INTERFAZ
	private JLabel etiquetaCategoria, etiquetaPregunta, etiquetaContador, etiquetaResultado, etiquetaDificultad, etiquetaEstadisticas;
	private JRadioButton[] botonesOpciones;
	private ButtonGroup grupoOpciones;
	private JButton botonResponder;
	private JProgressBar barraTiempo;

	// DATOS DEL JUEGO
	private ArrayList<Pregunta> preguntas;
	private int indicePregunta;
	private Jugador jugador;
	private int aciertos, errores;
	private final int LIMITE_PREGUNTAS = 10;

	// MULTIHILO
	private Thread hiloTiempo;

	/**
	 * CONSTRUCTOR DE LA VENTANA DE JUEGO
	 */
	public VentanaJuego(Jugador pJugador, ArrayList<Pregunta> pPreguntas)
	{
		setTitle("TriviAlvaro - Juego");
		setSize(650, 430);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		this.jugador = pJugador;
		this.preguntas = new ArrayList<>(pPreguntas);
		Collections.shuffle(this.preguntas);
		this.indicePregunta = 0;
		this.aciertos = 0;
		this.errores = 0;

		inicializarComponentes();
		mostrarPregunta();

		setVisible(true);
	}

	/**
	 * INICIALIZA TODOS LOS ELEMENTOS DE LA INTERFAZ.
	 */
	private void inicializarComponentes()
	{
		setLayout(new BorderLayout(10, 10));
		getContentPane().setBackground(Color.WHITE);

		etiquetaCategoria = new JLabel("", SwingConstants.CENTER);
		etiquetaCategoria.setFont(new Font("Segoe UI", Font.BOLD, 20));
		etiquetaCategoria.setOpaque(true);
		etiquetaCategoria.setForeground(Color.WHITE);
		etiquetaCategoria.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		add(etiquetaCategoria, BorderLayout.NORTH);

		JPanel panelCentro = new JPanel(new GridLayout(8, 1, 5, 5));
		panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		panelCentro.setBackground(Color.WHITE);

		etiquetaPregunta = new JLabel("", SwingConstants.CENTER);
		etiquetaPregunta.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panelCentro.add(etiquetaPregunta);

		botonesOpciones = new JRadioButton[4];
		grupoOpciones = new ButtonGroup();

		for (int i = 0; i < 4; i++)
		{
			botonesOpciones[i] = new JRadioButton();
			botonesOpciones[i].setFocusable(false);
			botonesOpciones[i].setFont(new Font("Segoe UI", Font.PLAIN, 14));
			botonesOpciones[i].setBackground(Color.WHITE);
			panelCentro.add(botonesOpciones[i]);
			grupoOpciones.add(botonesOpciones[i]);

			// EVENTO PARA CAMBIAR A NEGRITA SOLO LA OPCIÓN SELECCIONADA
			botonesOpciones[i].addItemListener(e ->
			{
				for (JRadioButton boton : botonesOpciones)
				{
					boton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				}
				JRadioButton seleccionado = (JRadioButton) e.getItemSelectable();
				if (e.getStateChange() == ItemEvent.SELECTED)
				{
					seleccionado.setFont(new Font("Segoe UI", Font.BOLD, 14));
				}
			});
		}

		etiquetaDificultad = new JLabel("", SwingConstants.CENTER);
		etiquetaDificultad.setFont(new Font("Segoe UI", Font.ITALIC, 14));
		panelCentro.add(etiquetaDificultad);

		etiquetaResultado = new JLabel(" ", SwingConstants.CENTER);
		etiquetaResultado.setFont(new Font("Segoe UI", Font.BOLD, 14));
		panelCentro.add(etiquetaResultado);

		add(panelCentro, BorderLayout.CENTER);

		JPanel panelInferior = new JPanel(new GridLayout(4, 1));
		panelInferior.setBackground(Color.WHITE);

		etiquetaContador = new JLabel("Pregunta 1/10", SwingConstants.CENTER);
		etiquetaContador.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panelInferior.add(etiquetaContador);

		barraTiempo = new JProgressBar(0, 15);
		barraTiempo.setValue(15);
		barraTiempo.setForeground(new Color(255, 235, 59));
		barraTiempo.setString("15 s");
		barraTiempo.setStringPainted(true);
		barraTiempo.setUI(new javax.swing.plaf.basic.BasicProgressBarUI()
		{
			@Override
			protected Color getSelectionForeground()
			{
				return Color.BLACK;
			}

			@Override
			protected Color getSelectionBackground()
			{
				return Color.BLACK;
			}
		});
		panelInferior.add(barraTiempo);

		botonResponder = new JButton("Responder");
		botonResponder.setFont(new Font("Segoe UI", Font.BOLD, 14));
		botonResponder.setBackground(new Color(33, 150, 243));
		botonResponder.setForeground(Color.WHITE);
		botonResponder.setFocusPainted(false);
		panelInferior.add(botonResponder);

		etiquetaEstadisticas = new JLabel("Aciertos: 0 | Errores: 0", SwingConstants.CENTER);
		etiquetaEstadisticas.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panelInferior.add(etiquetaEstadisticas);

		add(panelInferior, BorderLayout.SOUTH);

		// EVENTO DE BOTÓN
		botonResponder.addActionListener(e -> verificarRespuesta(false));
	}

	/**
	 * MUESTRA UNA NUEVA PREGUNTA Y REINICIA ESTADO
	 */
	private void mostrarPregunta()
	{
		if (indicePregunta >= LIMITE_PREGUNTAS || indicePregunta >= preguntas.size())
		{
			new VentanaResultado(jugador);
			dispose();
			return;
		}

		Pregunta pregunta = preguntas.get(indicePregunta);
		etiquetaPregunta.setText(pregunta.getEnunciado());

		for (int i = 0; i < 4; i++)
		{
			botonesOpciones[i].setText(pregunta.getOpciones().get(i));
			botonesOpciones[i].setFont(new Font("Segoe UI", Font.PLAIN, 14));
		}

		grupoOpciones.clearSelection();
		etiquetaCategoria.setText(pregunta.getCategoria());
		etiquetaCategoria.setBackground(colorCategoria(pregunta.getCategoria()));

		String dificultad = switch (pregunta.getNivel())
		{
		case 1 -> "Fácil";
		case 2 -> "Media";
		case 3 -> "Difícil";
		default -> "Desconocida";
		};
		etiquetaDificultad.setText("Dificultad: " + dificultad);

		etiquetaContador.setText("Pregunta " + (indicePregunta + 1) + "/" + LIMITE_PREGUNTAS);
		etiquetaResultado.setText(" ");
		barraTiempo.setValue(15);
		barraTiempo.setString("15 s");
		botonResponder.setEnabled(true);

		iniciarTemporizador();
	}

	/**
	 * INICIA LA CUENTA ATRÁS POR PREGUNTA (15 segundos)
	 */
	private void iniciarTemporizador()
	{
		hiloTiempo = new Thread(() ->
		{
			try
			{
				for (int i = 15; i >= 0; i--)
				{
					int tiempo = i;
					SwingUtilities.invokeLater(() ->
					{
						barraTiempo.setValue(tiempo);
						barraTiempo.setString(tiempo + " s");
					});
					Thread.sleep(1000);
				}
				SwingUtilities.invokeLater(() -> verificarRespuesta(true));
			} catch (InterruptedException ignored)
			{
			}
		});
		hiloTiempo.start();
	}

	/**
	 * VERIFICA SI LA RESPUESTA ES CORRECTA Y ACTUALIZA ESTADÍSTICAS
	 */
	private void verificarRespuesta(boolean porTiempo)
	{
		botonResponder.setEnabled(false);
		if (hiloTiempo != null && hiloTiempo.isAlive())
		{
			hiloTiempo.interrupt();
		}

		Pregunta pregunta = preguntas.get(indicePregunta);
		int seleccion = -1;

		for (int i = 0; i < botonesOpciones.length; i++)
		{
			if (botonesOpciones[i].isSelected())
			{
				seleccion = i;
				break;
			}
		}

		if (porTiempo || seleccion == -1)
		{
			errores++;
			etiquetaResultado.setForeground(Color.RED);
			String correcta = pregunta.getOpciones().get(pregunta.getRespuestaCorrecta());
			etiquetaResultado.setText("Tiempo agotado. Respuesta correcta: " + correcta);
		} else if (seleccion == pregunta.getRespuestaCorrecta())
		{
			jugador.incrementarPuntuacion();
			aciertos++;
			etiquetaResultado.setForeground(new Color(0, 128, 0));
			etiquetaResultado.setText("¡Correcto!");
		} else
		{
			errores++;
			etiquetaResultado.setForeground(Color.RED);
			String correcta = pregunta.getOpciones().get(pregunta.getRespuestaCorrecta());
			etiquetaResultado.setText("Incorrecto. Respuesta correcta: " + correcta);
		}

		etiquetaEstadisticas.setText("Aciertos: " + aciertos + " | Errores: " + errores);

		Timer t = new Timer(2000, e ->
		{
			indicePregunta++;
			mostrarPregunta();
		});
		t.setRepeats(false);
		t.start();
	}

	/**
	 * DEVUELVE UN COLOR ASOCIADO A LA CATEGORÍA DE LA PREGUNTA
	 */
	private Color colorCategoria(String categoria)
	{
		return switch (categoria)
		{
		case "Geografía" -> new Color(33, 150, 243);
		case "Arte y Literatura" -> new Color(156, 39, 176);
		case "Historia" -> new Color(255, 193, 7);
		case "Entretenimiento" -> new Color(233, 30, 99);
		case "Ciencia" -> new Color(76, 175, 80);
		case "Deportes" -> new Color(255, 87, 34);
		default -> Color.GRAY;
		};
	}
}
