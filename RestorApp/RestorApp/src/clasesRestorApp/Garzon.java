package clasesRestorApp;

import java.util.ArrayList;

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
		arrayMesasAtendiendo = new ArrayList<String>();
	}
	
	public String getNivelDeIngles()
	{
		return nivelDeIngles;
	}
	
	public int getMesasAtendidas()
	{
		return mesasAtendidas;
	}
	
	public int getSueldo()
	{
		sueldo = sueldo*(1+(mesasAtendidas/1000));
		if(sueldo>=500000)
		{
			sueldo=500000;
		}
		return sueldo;
	}
	
	public boolean AtenderMesa(int numeroMesa)
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
		arrayMesasAtendiendo.add(Integer.toString(numeroMesa));
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
	public String obtenerInformacion() 
	{
		String info="Rut: " + getRut() + "\nNombre: " + getNombre() + "\nEdad: " + getEdad() +
			"\nSalario: " + getSueldo() ;
		return info;
	}
	
	
	
	
	
	
	
	
}
