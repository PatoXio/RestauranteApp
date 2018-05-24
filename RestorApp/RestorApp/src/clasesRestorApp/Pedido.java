package clasesRestorApp;

import java.util.*;

import javax.swing.JTextArea;

import clasesRestorApp.Producto;



public class Pedido
{
	private int numDePedido;
	private int idMesa;
	private int precioTotalDelPedido;
	private Hashtable<Producto, Integer> productosPedidos;

	// Constructor
	public Pedido()
	{
		numDePedido=0;
		idMesa=0;
		precioTotalDelPedido=0;
		productosPedidos=new Hashtable<Producto,Integer>();
	}
	public Pedido(int numDePedido, int idMesa)
	{
		this.numDePedido = numDePedido;
		this.idMesa = idMesa;
		precioTotalDelPedido = 0;
		productosPedidos = new Hashtable<Producto, Integer>();
	}

	
	// Getter y Setters
	public int getNumDePedido() {
		return numDePedido;
	}
	public void setNumDePedido(int numDePedido) {
		this.numDePedido = numDePedido;
	}

	public int getidMesa() {
		return idMesa;
	}
	public void setidMesa(int idMesa) {
		this.idMesa = idMesa;
	}

	public int getPrecio() {
		return precioTotalDelPedido;
	}
	public void setPrecio(int precioTotalPedido) {
		this.precioTotalDelPedido = precioTotalPedido;
	}

	
	// Metodos
	/*
	 * agregarProductoPedido recibe el objeto Producto a agregar.
	 * La intencihn es verificar primero que el prodcuto deseado se encuentre disponible para ser "ordenado".
	 * Cada vez que se agregue un pedido, el precio del pedido aumenta dado el valor del producto.
	 * Para no tener objetos de productos repetidos,se usarh una Tabla con "Producto: Cantidad del Producto"
	 * Si el producto no se encuentra, entonces se agrega el producto con la cantidad minima: 1
	 * Si el producto ya estaba pedido, entonces aumentamos la cantidad en 1.
	 */
	public boolean agregarProductoPedido(Producto productoPorAgregar)
	{
		if (productoPorAgregar.getCantDisponibleEnElRestaurante() > 0)
		{
			precioTotalDelPedido += productoPorAgregar.getPrecioProducto();
			if (productosPedidos.containsKey(productoPorAgregar) == false)
			{
				productosPedidos.put(productoPorAgregar, 1);
				productoPorAgregar.setCantDisponibleEnElRestaurante(productoPorAgregar.getCantDisponibleEnElRestaurante() - 1);
				return true;
			} else {
				int contador = productosPedidos.get(productoPorAgregar);
				productosPedidos.put(productoPorAgregar, contador + 1);
				int conta1 = productoPorAgregar.getCantDisponibleEnElRestaurante() - 1;
				productoPorAgregar.setCantDisponibleEnElRestaurante(conta1);
				return true;
			}
		}
		return false;
	}
	
	
	/*
	 * 
	 */
	public boolean eliminarProductoPedido(Producto productoAEliminar)
	{
		Enumeration<Producto> e = productosPedidos.keys();
		Producto clave;
		while(e.hasMoreElements()){
			clave = e.nextElement();
			if(productoAEliminar.getCodeProducto().equals(clave.getCodeProducto())==true){
				setPrecio(getPrecio()-productoAEliminar.getPrecioProducto());
				productosPedidos.remove(productoAEliminar);
				return true;
			}
		}
		return false;
	}

	
	/*
	 * editarProductoPedido toma un producto del pedido ya hecho, y lo cambia por
	 * aquel que el usuario desea ahora. Retornarh este metodo un positivo (true) si
	 * ah sido exitoso el cambio.
	 * 
	 */
	public boolean editarProductoPedido(Producto productoPorAgregar, String codeProductoAntiguo) {
		return true;
	}
	
	
	/*
	 * 
	 */
	public String obtenerInformacion() 
	{
		String info="Numero de mesa:" +getidMesa() + "\nNumero de pedido: " + getNumDePedido() + "\nPrecio total Del pedido: " + getPrecio();
		
		return info;
		
	}
	
	public void mostrar(JTextArea textArea) 
	{	
		textArea.append("\n\n" + obtenerInformacion() +"\n\n");
	}

	
	
	
}