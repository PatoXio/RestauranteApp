package clasesRestorApp;


import java.io.IOException;
import Ventanas.Ventana;
import Ventanas.VentanaPrincipal;


public class AppInicio
{
	public static void main(String[] args) throws IOException
	{
		Restaurante restaurante = new Restaurante();
		VentanaPrincipal ventana = new VentanaPrincipal(restaurante);
		ventana.setVisible(true);
	}	
} //weaitas 2.0 supremas