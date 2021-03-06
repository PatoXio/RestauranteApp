package clasesRestorApp;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTextArea;

public class Garzon extends Empleados
{
	// Atributos particulares de un/una Garzon(a)
	
	private String nivelDeIngles;
	private int mesasAtendidas;
	private ArrayList<String> arrayMesasAtendiendo;
	public Garzon()
	{
		super(null,null,0,0);
		nivelDeIngles=null;
		mesasAtendidas=0;
		arrayMesasAtendiendo=new ArrayList<String>();
	}
	public Garzon(String rut, String nombre, int sueldo, int edad, String nivelDeIngles,int mesasAtendidas)
	{
		super(rut, nombre, sueldo, edad);
		this.nivelDeIngles = nivelDeIngles;
		this.mesasAtendidas = mesasAtendidas;
		arrayMesasAtendiendo = new ArrayList<String>(); // cantidad de mesas que puede antender a la vez
	}
	
	public String getCodigo()
	{				
		String codigo=rut+"Garzon";
		return codigo;
	}
	
	public String getNivelDeIngles()
	{
		return nivelDeIngles;
	}
	
	public int getMesasAtendidas()
	{
		return mesasAtendidas;
	}
	public int getcantMesas()
	{
		return arrayMesasAtendiendo.size();
	}
	@Override
	public int getSueldo()
	{
		int ganancia = (int) (sueldo*(1+(((double)mesasAtendidas/1000))));
		if(ganancia>=500000)
		{
			ganancia=500000;
		}
		return ganancia;
	}
	
	public boolean atenderMesa(int numeroMesa)
	{
		if(arrayMesasAtendiendo.size()==5) return false;
		String num=Integer.toString(numeroMesa);
		for(int i=0;i<arrayMesasAtendiendo.size();i++)
		{
			if(arrayMesasAtendiendo.get(i).equals(num)==true)
			{
				return false;
			}
		}
		mesasAtendidas=mesasAtendidas+1;
		arrayMesasAtendiendo.add(num);
		return true;
	}
	
	public boolean retirarMesa(int numeroMesa, int propina)
	{
		String num=Integer.toString(numeroMesa);
		for(int i=0;i<arrayMesasAtendiendo.size();i++)
		{
			if(arrayMesasAtendiendo.get(i).equals(num)==true)
			{
				arrayMesasAtendiendo.remove(i);
				sueldo=sueldo+propina;
				return true;
			}
		}
		return false;
	}
	@Override
	public String obtenerInformacion() 
	{
		String info="Rut: " + getRut() + "\nNombre: " + getNombre() + "\nEdad: " + getEdad() +
			"\nSalario: " + getSueldo() ;
		return info;
	}
	public String obtenerMesas()
	{
		String info ="Nombre: "+ getNombre()+"\nRut: "+ getRut()+"\nMesas: "+"0,0,0,0,0";
		if(arrayMesasAtendiendo!=null && arrayMesasAtendiendo.size()>0)
		{
			info="Nombre: "+ getNombre()+"\nRut: "+ getRut()+"\nMesas: "+arrayMesasAtendiendo.get(0);
			for(int i=1;i<arrayMesasAtendiendo.size();i++)
			{
				if(arrayMesasAtendiendo.get(i)==null)
				{
					info = info + "0";
				}
				info = info + "," + arrayMesasAtendiendo.get(i);
			}
		}
		return info+".";
	}
	
	@Override
	public void mostrarPersonasArchivo() throws IOException{
		Archivos archivo = new Archivos();
		archivo.crearArchivoReporteEmpleados(obtenerInformacion());
	}
	@Override
	public void mostrar(JTextArea textArea)
	{	
		textArea.append("\n\n" + obtenerInformacion() +"\n\n");
	}
		
	public void mostrarMesas(JTextArea textArea)
	{
		textArea.append("\n\n" + obtenerMesas()+"\n\n");
	}
}