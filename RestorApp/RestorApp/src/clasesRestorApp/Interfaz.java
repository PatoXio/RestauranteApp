package clasesRestorApp;

import javax.swing.JTextArea;

public interface Interfaz
{
	String getRut();
	void setRut(String rut);
	String getNombre();
	void setNombre(String nombre);
	void setSueldo(int sueldo);
	int getEdad ();
	void setEdad (int edad);	
	String obtenerInformacion();
	void mostrar(JTextArea textArea);
}
