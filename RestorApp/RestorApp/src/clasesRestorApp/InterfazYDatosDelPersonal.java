package clasesRestorApp;

import java.io.IOException;
//import javax.swing.JTextArea;

import javax.swing.JTextArea;

public interface InterfazYDatosDelPersonal
{
	String obtenerInformacion();
	String getRut();
	String getNombre();
	int getSueldo();
	int getEdad ();
	void setRut(String rut);
	void setNombre(String nombre);
	void setSueldo(int sueldo);
	void setEdad (int edad);	
	void mostrar(JTextArea textArea);
	void mostrarPersonasArchivo()throws IOException;
}