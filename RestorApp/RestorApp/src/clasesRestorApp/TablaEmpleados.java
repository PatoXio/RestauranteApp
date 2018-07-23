package clasesRestorApp;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;



public class TablaEmpleados
{
	private Archivos arc;
	private Hashtable<String,Empleados> tablaDeEmpleados;
	private JefeRestaurante jefe;
	private Restaurante rest;

	
	// Constructor
	public TablaEmpleados(Restaurante rest)
	{
		jefe=null;
		arc=new Archivos();
		tablaDeEmpleados = new Hashtable<String, Empleados>();
		this.rest=rest;
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
	public int getSize()
	{
		return tablaDeEmpleados.size();
	}
	
	public boolean agregarJefe(JefeRestaurante jefeRes) {
		if(jefe==null)
		{
			jefe=jefeRes;
			return true;
		}
		else
		{
			if(jefe.getRut().equals(jefeRes.getRut())==false)
			{
				jefe=jefeRes;
			}
		}
		return false;
	}
	
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
		if(jefe!=null)
		{
			jefe.mostrarPersonasArchivo();
		}
		for(Map.Entry<String,Empleados> entrada : tablaDeEmpleados.entrySet())
		{
			String clave = entrada.getKey(); //guardamos la clave
			tablaDeEmpleados.get(clave).mostrarPersonasArchivo();
		}
	}
	
	public void mostrarMesasGarzonesVentana(JTextArea textArea) {
		for(Map.Entry<String, Empleados> entrada : tablaDeEmpleados.entrySet())
		{
			String clave = entrada.getKey();
			if(tablaDeEmpleados.get(clave).getCodigo().equals(clave+"Garzon")==true)
			{
				Garzon gar=(Garzon) tablaDeEmpleados.get(clave);
				gar.mostrarMesas(textArea);
			}
		}
	}
	
	public void mostrarEmpleadosVentanaX(JTextArea textArea) 
	{
		if(jefe!=null)
		{
			jefe.mostrar(textArea);
		}
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
		int sueldoJefe=0;
		if(jefe!=null) sueldoJefe=jefe.getSueldo();
		//Recorrer cada uno de los Empleados, y obtener su sueldo e ir acumulandolos.
		if (tablaDeEmpleados != null && tablaDeEmpleados.isEmpty()!= true)
		{
			Enumeration<String> e = tablaDeEmpleados.keys();
			while(e.hasMoreElements())
			{
				String clave = e.nextElement();
				String codigo=tablaDeEmpleados.get(clave).getCodigo();
				if(codigo.equals(clave+"Garzon")==true)
				{
					sumaSueldosEmpleados+=traerGarzon(clave).getSueldo();
				}
				if(codigo.equals(clave+"Cajero")==true)
				{
					sumaSueldosEmpleados+=traerCajero(clave).getSueldo();
				}
				if(codigo.equals(clave+"Cocinero")==true)
				{
					sumaSueldosEmpleados+=traerCocinero(clave).getSueldo();
				}
			}
			return sumaSueldosEmpleados+sueldoJefe;
		}
		return sumaSueldosEmpleados+sueldoJefe;
	}
	
	public String porcentajeDeEmpleados()
	{
		int cantGarzones=0;
		int cantCocineros=0;
		int cantCajeros=0;
		double porcentajeGarzones=0;
		double porcentajeCocineros=0;
		double porcentajeCajeros=0;
		int totalEmpleados=0;
		if(tablaDeEmpleados!=null && tablaDeEmpleados.isEmpty()==false)
		{
			Enumeration<String> e = tablaDeEmpleados.keys();
			while(e.hasMoreElements())
			{
				String clave = e.nextElement();
				String codigo=tablaDeEmpleados.get(clave).getCodigo();
				if(codigo.equals(clave+"Garzon")==true) cantGarzones++;
				if(codigo.equals(clave+"Cajero")==true) cantCajeros++;
				if(codigo.equals(clave+"Cocinero")==true) cantCocineros++;
			}
			totalEmpleados=cantGarzones+cantCajeros+cantCocineros;
			porcentajeGarzones=(cantGarzones*100)/(double)totalEmpleados;
			porcentajeCajeros=(cantCajeros*100)/(double)totalEmpleados;
			porcentajeCocineros=(cantCocineros*100)/(double)totalEmpleados;
		}
		return "Total de empleados: "+totalEmpleados+".\nEl "+String.format("%.1f", porcentajeGarzones)+"% son garzones.\nEl"
				+ " "+String.format("%.1f", porcentajeCocineros)+"% son cocineros.\nEl "+String.format("%.1f", porcentajeCajeros)+"% son cajeros.";
	}

	public void escribirTxTCompletoGarzones() throws IOException
	{
		Enumeration<String> e = tablaDeEmpleados.keys();
		while(e.hasMoreElements())
		{
			String clave= e.nextElement();
			Empleados emp=tablaDeEmpleados.get(clave);
			if(emp.getCodigo().equals(emp.getRut()+"Garzon")==true)
			{
				Garzon gar=(Garzon)emp;
				arc.escribirTxTGarzones(gar.getRut(), gar.getNombre(), gar.getSueldo(), gar.getEdad(), gar.getNivelDeIngles(), gar.getMesasAtendidas());
			}
		}
	}
	
	public boolean retirarMesa(int numeroMesa, int propina) 
	{
		Enumeration<String> e = tablaDeEmpleados.keys();
		while(e.hasMoreElements())
		{
			String clave=(String) e.nextElement();
			Empleados emp=tablaDeEmpleados.get(clave);
			if(emp==null) return false;
			if(emp.getCodigo().equals(emp.getRut()+"Garzon")==true)
			{
				Garzon gar=(Garzon) emp;
				if(gar.retirarMesa(numeroMesa, propina)==true)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean atenderMesa(int numeroMesa)
	{
		Enumeration<String> e = tablaDeEmpleados.keys();
		while(e.hasMoreElements())
		{
			String clave=(String) e.nextElement();
			Empleados emp=tablaDeEmpleados.get(clave);

			if(emp!=null)

			if(emp==null) return false;
			if(emp.getCodigo().equals(emp.getRut()+"Garzon")==true)

			{
				if(emp.getCodigo().equals(emp.getRut()+"Garzon")==true)
				{
					Garzon gar=(Garzon)emp;
					System.out.print(gar.getcantMesas()<5);
					return gar.atenderMesa(numeroMesa);
				}
			}
		}
		return false;
	}
	
	public void escribirTxTCompletoCajeros() throws IOException
	{
		Enumeration<String> e = tablaDeEmpleados.keys();
		while(e.hasMoreElements())
		{
			String clave=(String) e.nextElement();
			Empleados emp=tablaDeEmpleados.get(clave);
			if(emp.getCodigo().equals(emp.getRut()+"Cajero")==true)
			{
				Cajero caj=(Cajero)emp;
				arc.escribirTxTCajeros(caj.getRut(), caj.getNombre(), caj.getSueldo(), caj.getEdad(), caj.getTotalCaja(),caj.getDiferencia());
			}
		}
	}
	
	public void escribirTxTCompletoCocineros() throws IOException {
		Enumeration<String> e = tablaDeEmpleados.keys();
		while(e.hasMoreElements())
		{
			String clave=(String) e.nextElement();
			Empleados emp=tablaDeEmpleados.get(clave);
			if(emp.getCodigo().equals(emp.getRut()+"Cocinero")==true)
			{
				Cocinero coc=(Cocinero)emp;
				arc.escribirTxTCocineros(coc.getRut(), coc.getNombre(), coc.getSueldo(), coc.getEdad());
			}
		}
	}
	
	public void escribirTxTCompletoJefe() throws IOException
	{
		if(jefe!=null)
		{
			arc.escribirTxTJefe(jefe.getRut(), jefe.getNombre(), jefe.getEdad(), jefe.getSueldo(), jefe.getCantEmpleados());
		}
	}


	public Garzon traerGarzon(String rut) {
		if(tablaDeEmpleados.containsKey(rut)==false) return null;
		Garzon gar = (Garzon) tablaDeEmpleados.get(rut);
		if(gar.getCodigo().equals(rut+"Garzon")==false) return null;
		return gar;
	}
	
	public Cocinero traerCocinero(String rut) {
		if(tablaDeEmpleados.containsKey(rut)==false) return null;
		Cocinero coc = (Cocinero) tablaDeEmpleados.get(rut);
		if(coc.getCodigo().equals(rut+"Cocinero")==false) return null;
		return coc;
	}
	
	public Cajero traerCajero(String rut) {
		if(tablaDeEmpleados.containsKey(rut)==false) return null;
		Cajero caj = (Cajero) tablaDeEmpleados.get(rut);
		if(caj.getCodigo().equals(rut+"Cajero")==false) return null;
		return caj;
	}
	
	public JefeRestaurante traerJefe() {
		return jefe;
	}
	
	/*public boolean buscarGarzon(String rut) {
		if(tablaDeEmpleados.containsKey(rut)==false) return false;
		Garzon gar = (Garzon) tablaDeEmpleados.get(rut);
		if(gar.getCodigo().equals(rut+"Garzon")==false) return false;
		return true;
	}
	
	public boolean buscarCocinero(String rut) {
		if(tablaDeEmpleados.containsKey(rut)==false) return false;
		Cocinero coc = (Cocinero) tablaDeEmpleados.get(rut);
		if(coc.getCodigo().equals(rut+"Cocinero")==false) return false;
		return true;
	}
	
	public boolean buscarCajero(String rut) {
		if(tablaDeEmpleados.containsKey(rut)==false) return false;
		Cajero caj = (Cajero) tablaDeEmpleados.get(rut);
		if(caj.getCodigo().equals(rut+"Cajero")==false) return false;
		return true;
	}*/
	
	public boolean buscarEmpleade(String rut)
	{
		if(tablaDeEmpleados.containsKey(rut)==true)
		{
			return true;
		}
		return false;
	}
	
	public boolean buscarJefe()
	{
		if(jefe!=null) return true;
		return false;
	}

	public void modificarGarzon(String rut, String nombre, int edad, int sueldo)
	{
		if(traerGarzon(rut)==null)
		{
			JOptionPane.showMessageDialog(null,"El garzon no existe");
			return;
		}
		try {
			Garzon gar=traerGarzon(rut);
			gar.setEdad(edad);
			gar.setNombre(nombre);
			gar.setRut(rut);
			gar.setSueldo(sueldo);
			arc.actualizarGarzones(rest);
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al modificar garzon");
		}
	}
	
	public void modificarCocinero(String rut, String nombre, int edad, int sueldo)
	{
		if(traerCocinero(rut)==null)
		{
			JOptionPane.showMessageDialog(null,"El cocinero no existe");
			return;
		}
		try {
			Cocinero coc=traerCocinero(rut);
			coc.setEdad(edad);
			coc.setNombre(nombre);
			coc.setRut(rut);
			coc.setSueldo(sueldo);
			arc.actualizarCocineros(rest);
		}catch(IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Error al modificar cocinero");
		}
	}
	
	public void modificarCajero(String rut, String nombre, int edad, int sueldo)
	{
		if(traerCajero(rut)==null)
		{
			JOptionPane.showMessageDialog(null,"El cajero no existe");
			return;
		}
		try {
			Cajero caj=traerCajero(rut);
			caj.setEdad(edad);
			caj.setNombre(nombre);
			caj.setRut(rut);
			caj.setSueldo(sueldo);
			arc.actualizarCajeros(rest);
		}catch(IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Error al modificar Cajero");
		}
	}
	
	public void modificarJefe(String rut, String nombre, int edad, int sueldo)
	{
		if(jefe==null)
		{
			JOptionPane.showMessageDialog(null,"El jefe no existe");
			return;
		}
		try {
			JefeRestaurante jefe=traerJefe();
			jefe.setEdad(edad);
			jefe.setNombre(nombre);
			jefe.setRut(rut);
			jefe.setSueldo(sueldo);
			arc.actualizarJefe(rest);
		}catch(IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Error al modificar Jefe");
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}