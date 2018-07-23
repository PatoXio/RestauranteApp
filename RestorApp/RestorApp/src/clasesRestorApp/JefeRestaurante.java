package clasesRestorApp;

import java.io.IOException;

import javax.swing.JTextArea;

public class JefeRestaurante implements InterfazYDatosDelPersonal{

	private String rut;
	private String nombre;
	private int edad;
	private int sueldo;
	private int cantEmpleados;
	
	public JefeRestaurante()
	{
		rut=null;
		nombre=null;
		edad=0;
		sueldo=0;
		cantEmpleados=0;
	}
	public JefeRestaurante(String rut,String nombre,int edad, int sueldo, int cantEmpleados)
	{
		this.rut=rut;
		this.nombre=nombre;
		this.edad=edad;
		this.sueldo=sueldo;
		this.cantEmpleados=cantEmpleados;
	}
	@Override
	public String obtenerInformacion()
	{
		String info="\n////////////////////-Jefe-////////////////////\nRut: " + getRut() + "\nNombre: " + getNombre() + "\nEdad: " + getEdad() +
		"\nSalario: " + getSueldo() + "\nCantidad de Empleados: " + getCantEmpleados()+ "\n////////////////////-Jefe-////////////////////\n";
		return info;
	}
	public int getCantEmpleados()
	{
		return cantEmpleados;
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
	
	public void setCantEmpleados(int cantE)
	{
		cantEmpleados=cantE;
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

}