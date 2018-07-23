package clasesRestorApp;


import java.io.IOException;
import Ventanas.Ventana;


public class AppInicio
{
	public static void main(String[] args) throws IOException
	{
		Restaurante restaurante = new Restaurante();
		Ventana ventana = new Ventana(restaurante);
		ventana.setVisible(true);
	}	
} //weaitas
