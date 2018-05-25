package clasesRestorApp;

import javax.swing.JTextArea;

public interface Interfaz
{
	String obtenerInformacion();
	String getRut();
	String getNombre();
	int getEdad ();
	int getSueldo();
	void setRut(String rut);
	void setNombre(String nombre);
	void setSueldo(int sueldo);
	void setEdad (int edad);	
	void mostrar(JTextArea textArea);
}
