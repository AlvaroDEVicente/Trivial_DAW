package vista;

// ----------------------------
// IMPORTACIONES NECESARIAS
// ----------------------------

import javax.swing.*;
// Componentes básicos de interfaz

import java.awt.*;
// Layouts y estilo visual

import java.awt.event.*;
// Gestión de eventos

/**
 * VENTANA PRINCIPAL DEL MENÚ DEL JUEGO. PERMITE ACCEDER A LAS DISTINTAS OPCIONES.
 */
public class VentanaMenu extends JFrame
{
	private static final long serialVersionUID = 1L;

	private JLabel etiquetaTitulo;
	private JButton botonJugar;
	private JButton botonRanking;
	private JButton botonSalir;

	/**
	 * CONSTRUCTOR DEL MENÚ PRINCIPAL.
	 */
	public VentanaMenu()
	{
		setTitle("TriviAlvaro - Menú Principal");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		inicializarComponentes();
		registrarEventos();

		setVisible(true);
	}

	/**
	 * INICIALIZA LOS COMPONENTES DE LA VENTANA Y SU ESTILO.
	 */
	private void inicializarComponentes()
	{
		setLayout(new BorderLayout());
		getContentPane().setBackground(Color.WHITE);

		etiquetaTitulo = new JLabel("TriviAlvaro", SwingConstants.CENTER);
		etiquetaTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		etiquetaTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
		add(etiquetaTitulo, BorderLayout.NORTH);

		JPanel panelBotones = new JPanel(new GridLayout(3, 1, 15, 15));
		panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
		panelBotones.setBackground(Color.WHITE);

		botonJugar = new JButton("Jugar");
		botonRanking = new JButton("Ver Ranking");
		botonSalir = new JButton("Salir");

		estilizarBoton(botonJugar, new Color(40, 167, 69));
		estilizarBoton(botonRanking, new Color(0, 123, 255));
		estilizarBoton(botonSalir, new Color(220, 53, 69));

		panelBotones.add(botonJugar);
		panelBotones.add(botonRanking);
		panelBotones.add(botonSalir);

		add(panelBotones, BorderLayout.CENTER);
	}

	/**
	 * REGISTRA LOS EVENTOS DE LOS BOTONES.
	 */
	private void registrarEventos()
	{
		botonJugar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new VentanaLogin(); // Abre la ventana de login
				dispose(); // Cierra el menú
			}
		});

		botonRanking.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new VentanaRanking();
			}
		});

		botonSalir.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				dispose();
				System.exit(0);
			}
		});
	}

	/**
	 * ESTILO UNIFICADO PARA BOTONES.
	 */
	private void estilizarBoton(JButton boton, Color colorFondo)
	{
		boton.setFont(new Font("Segoe UI", Font.BOLD, 16));
		boton.setFocusPainted(false);
		boton.setBackground(colorFondo);
		boton.setForeground(Color.WHITE);
		boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
	}
}
