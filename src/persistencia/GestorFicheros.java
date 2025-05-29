package persistencia;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import modelo.Pregunta;

import java.io.FileReader;
import java.util.ArrayList;

/**
 * Clase encargada de la lectura de preguntas desde un archivo JSON.
 */
public class GestorFicheros
{

	/**
	 * Método que carga preguntas desde un archivo JSON utilizando la librería Gson.
	 * 
	 * @param pRuta Ruta del archivo JSON.
	 * @return Lista de preguntas cargadas o null si ocurre un error.
	 */
	public static ArrayList<Pregunta> cargarPreguntasDesdeJSON(String pRuta)
	{
		ArrayList<Pregunta> listaPreguntas = new ArrayList<>();

		try
		{
			// Creamos un lector de archivo y utilizamos Gson para deserializar
			FileReader lector = new FileReader(pRuta);
			Gson gson = new Gson();

			listaPreguntas = gson.fromJson(lector, new TypeToken<ArrayList<Pregunta>>()
			{
			}.getType());

			lector.close();
		} catch (Exception e)
		{
			// Mostramos el error si ocurre una excepción al leer el archivo
			System.out.println("Error al cargar el archivo JSON: " + e.getMessage());
		}

		return listaPreguntas;
	}
}
