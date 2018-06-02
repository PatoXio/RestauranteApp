package clasesRestorApp;

import java.io.IOException;

import javax.swing.JTextArea;

public class Cajero extends Empleados
{
	// Atributos particulares de un Cajero(a)
	
	
	// Constructor
	public Cajero()
	{
		super(null,null,0,0);
	}
	public Cajero(String rut, String nombre, int sueldo, int edad)
	{
		super(rut, nombre, sueldo, edad);

	}
	@Override
	public int getSueldo()
	{
		return sueldo;
	}
	@Override
	public String obtenerInformacion() 
	{
		String info="Rut: " + getRut() + "\nNombre: " + getNombre() + "\nEdad: " + getEdad() +
			"\nSalario: " + getSueldo() ;
		return info;
	}
	@Override
	public void mostrarPersonasArchivo() throws IOException {
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
		String codigo=rut+"Cajero";
		return codigo;
	}
}