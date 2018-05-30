package clasesRestorApp;

import java.io.IOException;

import javax.swing.JTextArea;

public class Cocinero extends Empleados
{
	// Atributos particulares de un Cocinero(a)
	
	
	
	// Constructor
	public Cocinero()
	{
		super(null,null,0,0);
	}
	public Cocinero(String rut, String nombre, int sueldo, int edad)
	{
		super(rut, nombre, sueldo, edad);

	}
	public int getSueldo()
	{
		return sueldo;
	}
	public String obtenerInformacion() 
	{
		String info="Rut: " + getRut() + "\nNombre: " + getNombre() + "\nEdad: " + getEdad() +
			"\nSalario: " + getSueldo() ;
		return info;
	}
	public void mostrarPersonasArchivo() throws IOException{
		Archivos archivo = new Archivos();
		archivo.crearArchivoReporteEmpleados(obtenerInformacion());
	}
	@Override
	public void mostrar(JTextArea textArea) {
		{	
			textArea.append("\n\n" + obtenerInformacion() +"\n\n");
		}
	}
	@Override
	public String getCodigo() {
		String codigo=rut+"Cocinero";
		return codigo;
	}
	
}