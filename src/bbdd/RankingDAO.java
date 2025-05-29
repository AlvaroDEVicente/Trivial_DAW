package bbdd;

// ----------------------------
// IMPORTACIONES NECESARIAS
// ----------------------------

import java.sql.Connection;
// Para la conexión a la base de datos

import java.sql.PreparedStatement;
// Para ejecutar sentencias SQL parametrizadas

import java.sql.ResultSet;
// Para recorrer los resultados de la consulta

import java.sql.SQLException;
// Para capturar posibles errores SQL

import java.util.ArrayList;
// Para almacenar la lista de jugadores

import modelo.Jugador;
// Importamos la clase Jugador para almacenar los resultados

/**
 * CLASE QUE ACCEDE A LA BASE DE DATOS PARA RECUPERAR EL RANKING DE JUGADORES.
 */
public class RankingDAO {

    /**
     * MÉTODO QUE RECUPERA LOS 10 JUGADORES CON MAYOR PUNTUACIÓN DE LA TABLA 'estadisticas'.
     * 
     * @return Lista de objetos Jugador con nombre y puntuación.
     */
    public static ArrayList<Jugador> obtenerRanking() {
        ArrayList<Jugador> listaRanking = new ArrayList<>();

        String consultaSQL = "SELECT nombre, puntuacion FROM estadisticas ORDER BY puntuacion DESC LIMIT 10";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(consultaSQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                int puntuacion = rs.getInt("puntuacion");

                Jugador jugador = new Jugador();
                jugador.setNombre(nombre);
                jugador.setPuntuacion(puntuacion);

                listaRanking.add(jugador);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener el ranking: " + e.getMessage());
        }

        return listaRanking;
    }
    
    /**
     * MÉTODO QUE INSERTA LA PUNTUACIÓN DE UN JUGADOR EN LA TABLA 'estadisticas'.
     * 
     * @param pJugador Jugador con nombre y puntuación.
     */
    public static void insertarPuntuacion(Jugador pJugador) {
        String insertSQL = "INSERT INTO estadisticas (nombre, puntuacion) VALUES (?, ?)";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(insertSQL)) {

            ps.setString(1, pJugador.getNombre());
            ps.setInt(2, pJugador.getPuntuacion());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al insertar la puntuación: " + e.getMessage());
        }
    }

}
