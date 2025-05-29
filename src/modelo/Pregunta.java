package modelo;

import java.util.ArrayList;

/**
 * Clase que representa una pregunta de trivia.
 */
public class Pregunta
{

	// ENUNCIADO DE LA PREGUNTA
	private String enunciado;

	// LISTA DE OPCIONES DISPONIBLES
	private ArrayList<String> opciones;

	// ÍNDICE DE LA RESPUESTA CORRECTA (0, 1, 2 o 3)
	private int respuestaCorrecta;

	// CATEGORÍA DE LA PREGUNTA (Ej: Historia, Ciencia, etc.)
	private String categoria;

	// NIVEL DE DIFICULTAD (1: fácil, 2: medio, 3: difícil)
	private int nivel;

	// CONSTRUCTOR VACÍO
	public Pregunta()
	{
		this.enunciado = "";
		this.opciones = new ArrayList<>();
		this.respuestaCorrecta = -1;
		this.categoria = "";
		this.nivel = 1;
	}

	// CONSTRUCTOR PARCIAL (solo enunciado y opciones)
	public Pregunta(String pEnunciado, ArrayList<String> pOpciones)
	{
		this.enunciado = pEnunciado;
		this.opciones = pOpciones;
		this.respuestaCorrecta = -1;
		this.categoria = "";
		this.nivel = 1;
	}

	// CONSTRUCTOR COMPLETO
	public Pregunta(String pEnunciado, ArrayList<String> pOpciones, int pRespuestaCorrecta, String pCategoria, int pNivel)
	{
		this.enunciado = pEnunciado;
		this.opciones = pOpciones;
		this.respuestaCorrecta = pRespuestaCorrecta;
		this.categoria = pCategoria;
		this.nivel = pNivel;
	}

	// GETTER Y SETTER PARA ENUNCIADO
	public String getEnunciado()
	{
		return enunciado;
	}

	public void setEnunciado(String pEnunciado)
	{
		this.enunciado = pEnunciado;
	}

	// GETTER Y SETTER PARA OPCIONES
	public ArrayList<String> getOpciones()
	{
		return opciones;
	}

	public void setOpciones(ArrayList<String> pOpciones)
	{
		this.opciones = pOpciones;
	}

	// GETTER Y SETTER PARA RESPUESTA CORRECTA
	public int getRespuestaCorrecta()
	{
		return respuestaCorrecta;
	}

	public void setRespuestaCorrecta(int pRespuestaCorrecta)
	{
		this.respuestaCorrecta = pRespuestaCorrecta;
	}

	// GETTER Y SETTER PARA CATEGORÍA
	public String getCategoria()
	{
		return categoria;
	}

	public void setCategoria(String pCategoria)
	{
		this.categoria = pCategoria;
	}

	// GETTER Y SETTER PARA NIVEL
	public int getNivel()
	{
		return nivel;
	}

	public void setNivel(int pNivel)
	{
		this.nivel = pNivel;
	}

	// MÉTODO toString PARA MOSTRAR DATOS DE LA PREGUNTA
	@Override
	public String toString()
	{
		return "Pregunta{" + "enunciado='" + enunciado + '\'' + ", opciones=" + opciones + ", respuestaCorrecta=" + respuestaCorrecta + ", categoria='" + categoria + '\'' + ", nivel=" + nivel + '}';
	}
}
