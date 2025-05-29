package bbdd;

import java.sql.Connection;
// Para manejar la conexión con la base de datos

import java.sql.PreparedStatement;
// Permite ejecutar sentencias SQL con parámetros

import java.sql.SQLException;
// Para manejar errores al acceder a la base de datos

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
// Para obtener la fecha y hora actual en formato compatible con MySQL

import modelo.Jugador;
// Importamos la clase Jugador desde el paquete modelo

/**
 * CLASE ENCARGADA DE GESTIONAR EL ACCESO A LA TABLA ESTADISTICAS EN LA BASE DE DATOS.
 */
public class EstadisticasDAO
{

	/**
	 * MÉTODO PARA GUARDAR LA PUNTUACIÓN DE UN JUGADOR EN LA BASE DE DATOS.
	 * 
	 * @param pJugador    JUGADOR CUYA INFORMACIÓN SE VA A GUARDAR
	 * @param pPuntuacion PUNTUACIÓN OBTENIDA EN LA PARTIDA
	 */
	public static void guardarEstadistica(Jugador pJugador, int pPuntuacion)
	{
		// SENTENCIA SQL DE INSERCIÓN
		String sql = "INSERT INTO estadisticas (nombre, puntuacion, fecha) VALUES (?, ?, ?)";

		// OBTENEMOS LA FECHA ACTUAL
		LocalDateTime ahora = LocalDateTime.now();
		String fechaFormateada = ahora.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

		// INTENTAMOS GUARDAR LA INFORMACIÓN
		try (Connection conexion = ConexionBD.conectar(); PreparedStatement ps = conexion.prepareStatement(sql))
		{

			ps.setString(1, pJugador.getNombre());
			ps.setInt(2, pPuntuacion);
			ps.setString(3, fechaFormateada);

			ps.executeUpdate();
			System.out.println("Estadística guardada correctamente.");

		} catch (SQLException e)
		{
			System.out.println("Error al guardar estadística: " + e.getMessage());
		}
	}
}
