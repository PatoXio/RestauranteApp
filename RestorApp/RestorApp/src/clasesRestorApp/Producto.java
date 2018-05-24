package clasesRestorApp;


import java.io.IOException;

import javax.swing.JTextArea;



public class Producto
{
	private String codeProducto;
	private String nombre;
	private int precioProducto;
	private int cantDisponibleEnElRestaurante;

	
	// Constructor
	public Producto()
	{
		codeProducto=null;
		nombre=null;
		precioProducto=0;
		cantDisponibleEnElRestaurante=0;
	}
	public Producto(String codeProducto, String nombre, int precioProducto, int cantDisponibleEnElRestaurante)
	{
		this.codeProducto = codeProducto;
		this.nombre = nombre;
		this.precioProducto = precioProducto;
		this.cantDisponibleEnElRestaurante = cantDisponibleEnElRestaurante;
	}

	
	// Getter & Setters
	public String getCodeProducto() {
		return codeProducto;
	}
	public void setCodeProducto(String codeProducto) {
		this.codeProducto = codeProducto;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPrecioProducto() {
		return precioProducto;
	}
	public void setPrecioProducto(int precioProducto) {
		this.precioProducto = precioProducto;
	}

	public int getCantDisponibleEnElRestaurante() {
		return cantDisponibleEnElRestaurante;
	}
	public void setCantDisponibleEnElRestaurante(int cantDisponibleEnElRestaurante) {
		this.cantDisponibleEnElRestaurante = cantDisponibleEnElRestaurante;
	}
	
	
	//Metodos
	public String obtenerInformacion() 
	{
		String info="Codigo del producto: " + getCodeProducto() + "\nNombre: " + getNombre() + "\nPrecio: " + getPrecioProducto() +
			"\nCantidad Disponible: " + getCantDisponibleEnElRestaurante() ;
		return info;
	}
	
	public void mostrar(JTextArea textArea) 
	{	
		textArea.append("\n\n" + obtenerInformacion() +"\n\n");
	}
	public void mostrarProductoArchivo() throws IOException{
		Archivos archivo = new Archivos();
		archivo.crearArchivoReporteProductos(getCodeProducto(), getNombre(), Integer.toString(getPrecioProducto()), Integer.toString(getCantDisponibleEnElRestaurante() ));
	}
	
	
	
	
	
	
}