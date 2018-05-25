package clasesRestorApp;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.JTextArea;



public class TablaEmpleados
{

	private Hashtable<String, Empleados> tablaDeEmpleados;

	
	// Constructor
	public TablaEmpleados()
	{
		tablaDeEmpleados = new Hashtable<String, Empleados>();
	}

	
	// Mhtodos
	/*
	 * agregarEmpleado recibe del usuario de la app todo los datos necesarios para
	 * agregar un empleado nuevo a la tabla. Para ello es necesario primero revisar
	 * que no se esth duplicando un empleado ya en registro. En caso de que el
	 * empleado si sea nuevo, se inserta en la tabla y se retorna un positivo al
	 * proceso.
	 * 
	 */
	public boolean agregarGarzon(Garzon GarzonPorAgregar)
	{
		if (tablaDeEmpleados.containsKey(GarzonPorAgregar.getRut()) == true)
		{
			return false;
		}

		tablaDeEmpleados.put(GarzonPorAgregar.getRut(), GarzonPorAgregar);
		return true;
	}
	
	public boolean agregarCajero(Cajero CajeroPorAgregar)
	{
		if (tablaDeEmpleados.containsKey(CajeroPorAgregar.getRut()) == true)
		{
			return false;
		}

		tablaDeEmpleados.put(CajeroPorAgregar.getRut(), CajeroPorAgregar);
		return true;
	}
	
	public boolean agregarCocinero(Cocinero CocineroPorAgregar)
	{
		if (tablaDeEmpleados.containsKey(CocineroPorAgregar.getRut()) == true)
		{
			return false;
		}

		tablaDeEmpleados.put(CocineroPorAgregar.getRut(), CocineroPorAgregar);
		return true;
	}
	
	/*
	 * eliminarEmpleado busca sacar un empleado espechfico de la piscina (tabla) de
	 * trabajadores. En caso exitoso de la eliminacihn retorna aquel exito en un
	 * "verdadero", y "falso" en caso contrario. El metodo es implementado en
	 * sobrecarga.
	 * 
	 */
	public Empleados eliminarEmpleado(Empleados empleadoPorEliminar)
	{
		if (tablaDeEmpleados.containsKey(empleadoPorEliminar.getRut()) == true) {
			tablaDeEmpleados.remove(empleadoPorEliminar.getRut());
			return empleadoPorEliminar;
		}
		return null;
	}

	public boolean eliminarEmpleado(String identificador)
	{
		if (tablaDeEmpleados.containsKey(identificador) == true) {
			tablaDeEmpleados.remove(identificador);
			return true;
		}
		return false;
	}
	
	/*
	 * 
	 * 
	 * 
	 */
	public void repoteEmpleadosArchivo()throws IOException
	{
		for(Map.Entry<String,Empleados> entrada : tablaDeEmpleados.entrySet())
		{
			String clave = entrada.getKey(); //guardamos la clave
			tablaDeEmpleados.get(clave).mostrarEmpleadoArchivo();
		}
	}
	public void mostrarEmpleadosVentanaX(JTextArea textArea) 
	{
		for (Map.Entry<String, Empleados> entrada : tablaDeEmpleados.entrySet() )
		{
			String clave = entrada.getKey(); //Guardamos la clave para luego buscar el objeto espechfico
			tablaDeEmpleados.get(clave).mostrar(textArea);
		}	
	}
	
	/*
	 * calcularTotalSueldos permite tener el numero de "gastos" en lo que respecta
	 * al personal contratado a fin de tener parte de los datos necesario para
	 * calcular rentabilidad del negocio.
	 */
	public int calcularTotalSueldos()
	{
		int sumaSueldosEmpleados = 0;
		
		//Recorrer cada uno de los Empleados, y obtener su sueldo e ir acumulandolos.
		if (tablaDeEmpleados != null && tablaDeEmpleados.isEmpty()!= true)
		{
			return sumaSueldosEmpleados;
		}
		
		return 0;
	}
}