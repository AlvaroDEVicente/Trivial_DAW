package vista;

// ----------------------------
// IMPORTACIONES NECESARIAS
// ----------------------------

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import bbdd.RankingDAO;
import modelo.Jugador;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * CLASE RESPONSABLE DE MOSTRAR EL RANKING DE LOS MEJORES JUGADORES EN UNA TABLA CON ESTILO.
 */
public class VentanaRanking extends JFrame
{
	private static final long serialVersionUID = 1L;

	private JTable tablaRanking;
	private JButton botonCerrar;
	private DefaultTableModel modeloTabla;
	private JLabel titulo;

	/**
	 * CONSTRUCTOR DE LA VENTANA DE RANKING CON DISEÑO VISUAL.
	 */
	public VentanaRanking()
	{
		setTitle("Ranking de Jugadores");
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null); // Centrar ventana

		inicializarComponentes();
		cargarRanking();
		registrarEventos();

		setVisible(true);
	}

	/**
	 * CONFIGURA LOS COMPONENTES DE LA INTERFAZ GRÁFICA.
	 */
	private void inicializarComponentes()
	{
		setLayout(new BorderLayout(10, 10));

		// TÍTULO
		titulo = new JLabel("Ranking de Jugadores", SwingConstants.CENTER);
		titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
		titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		add(titulo, BorderLayout.NORTH);

		// TABLA
		modeloTabla = new DefaultTableModel();
		modeloTabla.addColumn("Jugador");
		modeloTabla.addColumn("Puntuación");

		tablaRanking = new JTable(modeloTabla);
		tablaRanking.setRowHeight(30);
		tablaRanking.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tablaRanking.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
		tablaRanking.setGridColor(Color.GRAY);
		tablaRanking.setShowGrid(true);
		tablaRanking.setAutoCreateRowSorter(true); // Habilitar ordenación por columnas

		// CENTRAR TEXTO DE LAS CELDAS
		DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
		centrado.setHorizontalAlignment(SwingConstants.CENTER);
		tablaRanking.getColumnModel().getColumn(0).setCellRenderer(centrado);
		tablaRanking.getColumnModel().getColumn(1).setCellRenderer(centrado);

		JScrollPane scrollPane = new JScrollPane(tablaRanking);
		add(scrollPane, BorderLayout.CENTER);

		// BOTÓN DE CIERRE
		botonCerrar = new JButton("Cerrar");
		botonCerrar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		botonCerrar.setBackground(new Color(220, 53, 69));
		botonCerrar.setForeground(Color.WHITE);
		botonCerrar.setFocusPainted(false);
		botonCerrar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		add(botonCerrar, BorderLayout.SOUTH);
	}

	/**
	 * CARGA EL RANKING DESDE LA BASE DE DATOS Y LO MUESTRA EN LA TABLA.
	 */
	private void cargarRanking()
	{
		modeloTabla.setRowCount(0); // Limpiar la tabla antes de cargar
		ArrayList<Jugador> ranking = RankingDAO.obtenerRanking();

		for (Jugador jugador : ranking)
		{
			Object[] fila = { jugador.getNombre(), jugador.getPuntuacion() };
			modeloTabla.addRow(fila);
		}
	}

	/**
	 * REGISTRA LA ACCIÓN DEL BOTÓN PARA CERRAR LA VENTANA.
	 */
	private void registrarEventos()
	{
		botonCerrar.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				dispose(); // Cierra solo esta ventana
			}
		});
	}
}
