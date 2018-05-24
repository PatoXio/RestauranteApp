package clasesRestorApp;


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
}