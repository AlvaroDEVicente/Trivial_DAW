package bbdd;

// ----------------------------
// IMPORTACIONES NECESARIAS
// ----------------------------

import java.sql.Connection;
// Para representar la conexión con la base de datos

import java.sql.DriverManager;
// Para obtener la conexión usando la URL y las credenciales

import java.sql.SQLException;
// Para capturar errores al conectar con la base de datos

/**
 * CLASE ENCARGADA DE CONECTAR CON LA BASE DE DATOS MySQL. UTILIZA JDBC PARA ESTABLECER LA CONEXIÓN CON LA BASE DE DATOS 'trivia'.
 */
public class ConexionBD {

    // URL DE CONEXIÓN JDBC A LA BASE DE DATOS
    // FORMATO: jdbc:mysql://<host>:<puerto>/<nombre_base_datos>
    // En este caso: localhost (misma máquina), puerto 3306 (por defecto) y base de datos 'trivia'
    private static final String URL = "jdbc:mysql://localhost:3306/trivia";

    // CREDENCIALES DE CONEXIÓN A LA BASE DE DATOS
    // IMPORTANTE: CAMBIA ESTOS VALORES SI TU USUARIO Y CONTRASEÑA DE MySQL SON DISTINTOS
    private static final String USUARIO = "triviauser";
    private static final String CONTRASENA = "triviapass";

    /**
     * MÉTODO QUE INTENTA CONECTAR A LA BASE DE DATOS.
     * SI LA CONEXIÓN SE ESTABLECE CORRECTAMENTE, SE MUESTRA UN MENSAJE Y SE DEVUELVE LA CONEXIÓN.
     * SI OCURRE UN ERROR, SE MUESTRA EL MENSAJE Y SE DEVUELVE NULL.
     */
    public static Connection conectar() {
        try {
            Connection conexion = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            System.out.println("Conexión establecida con éxito.");
            return conexion;
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
            return null;
        }
    }

    /**
     * MÉTODO DE PRUEBA OPCIONAL.
     * SE PUEDE USAR PARA PROBAR SI LA CONEXIÓN FUNCIONA CORRECTAMENTE DESDE ECLIPSE.
     * (ESTÁ COMENTADO PARA EVITAR SU EJECUCIÓN AUTOMÁTICA)
     */
    // public static void main(String[] args) {
    //     Connection conexion = ConexionBD.conectar();
    //
    //     if (conexion != null) {
    //         System.out.println("¡Conexión exitosa a la base de datos!");
    //         try {
    //             conexion.close(); // Cerramos la conexión tras la prueba
    //         } catch (SQLException e) {
    //             System.out.println("Error al cerrar la conexión: " + e.getMessage());
    //         }
    //     } else {
    //         System.out.println("No se pudo establecer la conexión.");
    //     }
    // }
}
