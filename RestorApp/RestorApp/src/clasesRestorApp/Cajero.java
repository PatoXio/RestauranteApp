package clasesRestorApp;

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
}