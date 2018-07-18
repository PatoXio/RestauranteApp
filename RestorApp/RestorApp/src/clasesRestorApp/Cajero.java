package clasesRestorApp;

import java.io.IOException;

import javax.swing.JTextArea;

public class Cajero extends Empleados
{
	// Atributos particulares de un Cajero(a)
	private int totalCaja;
	private int diferenciaCaja;
	
	// Constructor
	public Cajero()
	{
		super(null,null,0,0);
		totalCaja=0;
		diferenciaCaja=0;
	}
	public Cajero(String rut, String nombre, int sueldo, int edad, int total, int diferencia)
	{
		super(rut, nombre, sueldo, edad);
		totalCaja=total; // 
		diferenciaCaja=diferencia;
	}
	public int getTotalCaja()
	{
		return totalCaja;
	}
	public int getDiferencia()
	{
		return diferenciaCaja;
	}
	@Override
	public int getSueldo()
	{
		int sueldoFinal=sueldo;
		if (diferenciaCaja<0)
		{
			sueldoFinal = sueldo + (-1*diferenciaCaja);
		}
		else
		{
			sueldoFinal = sueldo + diferenciaCaja;
		}
		return sueldoFinal;
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
	public String getCodigo() {
		String codigo=rut+"Cajero";
		return codigo;
	}
}