package clasesRestorApp;

import java.io.IOException;

import javax.swing.JTextArea;

public abstract class Empleados implements Interfaz
{	
	protected String rut;
	protected String nombre;
	protected int sueldo;
	protected int edad;
	
	//Constructor
	public Empleados()
	{
		rut=null;
		nombre=null;
		sueldo=0;
		edad=0;
	}
	public Empleados(String rut, String nombre, int sueldo, int edad)
	{
		this.rut = rut;
		this.nombre = nombre;
		this.sueldo = sueldo;
		this.edad = edad;
	}
	
	
	// Getter & Setters
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setSueldo(int sueldo) {
		this.sueldo = sueldo;
	}
	
	public abstract int getSueldo();
	
	public int getEdad (){
		return edad;
	}
	public void setEdad (int edad) {
		this.edad = edad;
	}
	public abstract void mostrar(JTextArea textArea);
	public abstract void mostrarEmpleadosArchivo()throws IOException;
}
