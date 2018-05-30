package clasesRestorApp;

import java.io.IOException;

import javax.swing.JTextArea;

public class JefeRestaurante extends Empleados{

	private String rut;
	private String nombre;
	private int edad;
	private int sueldo;
	
	public JefeRestaurante()
	{
		rut=null;
		nombre=null;
		edad=0;
		sueldo=0;
	}
	public JefeRestaurante(String rut,String nombre,int edad, int sueldo)
	{
		this.rut=rut;
		this.nombre=nombre;
		this.edad=edad;
		this.sueldo=sueldo;
	}
	@Override
	public String obtenerInformacion() {
		String info="\n////////////////////-Jefe-////////////////////\nRut: " + getRut() + "\nNombre: " + getNombre() + "\nEdad: " + getEdad() +
			"\nSalario: " + getSueldo() +"\n////////////////////-Jefe-////////////////////\n";
		return info;
	}
	public int getSueldo()
	{
		return sueldo;
	}
	@Override
	public String getRut() {
		return rut;
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public int getEdad() {
		return edad;
	}

	@Override
	public void setRut(String rut) {
		this.rut=rut;
	}

	@Override
	public void setNombre(String nombre) {
		this.nombre=nombre;
	}

	@Override
	public void setSueldo(int sueldo) {
		this.sueldo=sueldo;
	}

	@Override
	public void setEdad(int edad) {
		this.edad=edad;
	}

	@Override
	public void mostrar(JTextArea textArea) {
		textArea.append("\n\n" + obtenerInformacion() +"\n\n");
	}

	@Override
	public void mostrarPersonasArchivo() throws IOException {
		Archivos archivo = new Archivos();
		archivo.crearArchivoReporteEmpleados(obtenerInformacion());		
	}
	@Override
	public String getCodigo() {
		String codigo=rut+"Jefe";
		return codigo;
	}

}
