package clasesRestorApp;


import java.io.IOException;
import Ventanas.Ventana;


public class AppInicio
{
	public static void main(String[] args) throws IOException
	{
		Restaurante restaurante = new Restaurante("La Coca", "Av. Sin Nombre #1277");
		Ventana ventana = new Ventana(restaurante);
		ventana.setVisible(true);
	}	
}
//Cristian: EL PATO ME LO ESTA METIENDO SUPER RICO