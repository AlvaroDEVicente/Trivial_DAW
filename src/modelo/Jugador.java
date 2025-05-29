package modelo;

/**
 * CLASE QUE REPRESENTA A UN JUGADOR DE TRIVIA. CONTIENE SU NOMBRE Y SU PUNTUACIÓN ACTUAL.
 */
public class Jugador
{

	// ------------------------
	// ATRIBUTOS DEL JUGADOR
	// ------------------------

	private String nombre;
	private int puntuacion;

	// ------------------------
	// CONSTRUCTORES
	// ------------------------

	/**
	 * CREA UN NUEVO JUGADOR CON NOMBRE Y PUNTUACIÓN INICIAL A CERO.
	 * 
	 * @param pNombre NOMBRE DEL JUGADOR.
	 */
	public Jugador(String pNombre)
	{
		this.nombre = pNombre;
		this.puntuacion = 0;
	}

	/**
	 * CONSTRUCTOR VACÍO PARA USO INTERNO O CUANDO SE RELLENA POSTERIORMENTE.
	 */
	public Jugador()
	{
		this.nombre = "";
		this.puntuacion = 0;
	}

	// ------------------------
	// MÉTODOS PÚBLICOS
	// ------------------------

	/**
	 * INCREMENTA LA PUNTUACIÓN DEL JUGADOR EN 1 PUNTO.
	 */
	public void incrementarPuntuacion()
	{
		puntuacion++;
	}

	/**
	 * OBTIENE EL NOMBRE DEL JUGADOR.
	 * 
	 * @return NOMBRE COMO STRING.
	 */
	public String getNombre()
	{
		return nombre;
	}

	/**
	 * ESTABLECE UN NUEVO NOMBRE PARA EL JUGADOR.
	 * 
	 * @param pNombre NOMBRE A ASIGNAR.
	 */
	public void setNombre(String pNombre)
	{
		this.nombre = pNombre;
	}

	/**
	 * OBTIENE LA PUNTUACIÓN ACTUAL DEL JUGADOR.
	 * 
	 * @return PUNTUACIÓN COMO ENTERO.
	 */
	public int getPuntuacion()
	{
		return puntuacion;
	}

	/**
	 * ESTABLECE UNA NUEVA PUNTUACIÓN (ÚTIL PARA PRUEBAS O ESTADÍSTICAS).
	 * 
	 * @param pPuntuacion NUEVA PUNTUACIÓN A ASIGNAR.
	 */
	public void setPuntuacion(int pPuntuacion)
	{
		this.puntuacion = pPuntuacion;
	}
}
