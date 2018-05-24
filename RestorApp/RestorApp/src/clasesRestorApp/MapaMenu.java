package clasesRestorApp;

import java.io.IOException;
import java.util.*;
import javax.swing.JTextArea;



public class MapaMenu
{
	private HashMap<String, Producto> menu;

	
	// Constructor
	public MapaMenu()
	{
		menu = new HashMap<String, Producto>();
	}

	
	// Mhtodos
	/*
	 * agregarProductoAlMenu busca aumentar la pool de productos que restaurant
	 * posee. Este metodo recibe un producto y sus caracterhsticas, revisa que ya no
	 * exista previamente y lo ingresa al mapa. Si el proceso es exitoso retorna un
	 * "true" al sistema.
	 */
	public void putCodigoProducto(String codigo, Producto producto)
	{
		menu.put(codigo, producto);
	}
	
	public boolean agregarProductoAlMenu(Producto productoPorAgregar)
	{
		if (menu.containsKey(productoPorAgregar.getCodeProducto()) != true) {
			menu.put(productoPorAgregar.getCodeProducto(), productoPorAgregar);
			return true;
		}
		return false;
	}
	
	
	
	
	/*
	 * Este metodo recibe el id del producto y retorna una referencia al producto obtenido
	 * Cada ves que se llame a este metodo, la cantidad disponible del producto mapeado disminuira en 1
	 * Disminuira porque el producto fue ordenado para "servir"
	 */
	public Producto obtenerProductoEspecifico(String idProducto)
	{
		if (menu.containsKey(idProducto) == true) {
			Producto aux = menu.get(idProducto);
			return aux;
		}
		return null;
	}
	
	
	
	
	/*
	 * eliminarProductoDelMenu busca sacar un empleado espechfico de la piscina
	 * (tabla) de trabajadores. En caso exitoso de la eliminacihn retorna aquel
	 * exito en un "verdadero", y "falso" en caso contrario. El metodo es
	 * implementado en sobrecarga.
	 * 
	 */
	public boolean eliminarProductoDelMenu(String idProductoPorEliminar)
	{
		if (menu.containsKey(idProductoPorEliminar) == true) {
			menu.remove(idProductoPorEliminar);
			return true;
		}

		return false;
	}
	
	//Sobrecarga
	public Producto eliminarProductoDelMenu(Producto productoPorEliminar)
	{
		if (menu.containsKey(productoPorEliminar.getCodeProducto()) == true) {
			menu.remove(productoPorEliminar.getCodeProducto());
			return productoPorEliminar;
		}
		return null;
	}
	
	
	
	
	/*
	 * mostrarProductosSinStock busca tomar todo el mapa de los productos que el
	 * restaurante posee y mostrarselos al usario en una lista epscifica,
	 * todos aquellos productos que no poseen ya stock en el local.
	 */
	public ArrayList<String> mostrarProductosSinStock()
	{
		ArrayList<String> ListaDeProductosSinStock = new ArrayList<String>();
		
		if(menu != null && menu.isEmpty() != true)
		{
			Iterator<Map.Entry<String, Producto>> iterador = menu.entrySet().iterator(); //Para recorrer cada producto del mapa;
			
			while (iterador.hasNext())
			{
				Map.Entry<String, Producto> actual = iterador.next();
				
				if(actual!=null && actual.getValue().getCantDisponibleEnElRestaurante() == 0){
					ListaDeProductosSinStock.add(actual.getValue().getNombre());	
				}
			}
			return ListaDeProductosSinStock;
		}
		return null;
	}
	
	
	/*
	 * mostrarProductosDelMenu posee la intencion de mostrarle al usuario
	 * todos los productos del menu en funcion de dos atributos de los Productos,
	 * su Nombre y su CantDisponibleEnElRestaurante.
	 */
	public HashMap<String,Integer> mostrarStockProductosDelMenu()
	{
		HashMap<String,Integer> StockProductosDelMenu = new HashMap<String,Integer>();
		
		if(menu != null && menu.isEmpty() != true)
		{
			Iterator<Map.Entry<String, Producto>> iterador = menu.entrySet().iterator(); //Para recorrer cada producto del mapa;
			
			while (iterador.hasNext())
			{
				Map.Entry<String, Producto> actual = iterador.next();
				
				if(actual!=null){
					StockProductosDelMenu.put(actual.getValue().getNombre(), actual.getValue().getCantDisponibleEnElRestaurante());	
				}
			}
			return StockProductosDelMenu;
		}
		return null;
	}
	
	
	
	
	/*
	 * renovarInventario es un metodo que busca volver a poner un producto disponible para ser pedido.
	 * Para ello es implementado en sobrecarga para suplir 2 necesidades, la primera referente a
	 * renovar el stock de un solo preoducto, y la segunda para suplir al mismo tiempo
	 * todos y cada uno de los productos del local (con la misma cantidad)
	 */
	public boolean renovarInventario(String codeProducto, int cantidad)
	{
				
		if(menu != null && menu.isEmpty() != true)
		{
			if(menu.containsKey(codeProducto)){
				menu.get(codeProducto).setCantDisponibleEnElRestaurante(cantidad);
				return true;
			}
		}
		return false;
	}
	
	public boolean renovarInventario(int cantidad)
	{	
		if(menu != null && menu.isEmpty() != true)
		{
			Iterator<Map.Entry<String, Producto>> iterador = menu.entrySet().iterator(); //Para recorrer cada producto del mapa;
			
			while (iterador.hasNext())
			{
				Map.Entry<String, Producto> actual = iterador.next();
				
				if(actual!=null){
					actual.getValue().setCantDisponibleEnElRestaurante(cantidad);
				}
			}
			return true;
		}
		return false;
	}
	
	
	
	
	/* Metodo Mostrar productos, que recibe la variable texArea
	 * Recorremos el menu HashMap usando el metodo entrySet,
	 * se toman todas las claves del menu las cuales son usadas para la obtencion del objeto,
	 * con ello podemos usar el metodo mostrar con cada objeto del menu
	 */
	public void mostrarProductosVentanaX(JTextArea textArea) 
	{
		for (Map.Entry<String, Producto> entrada : menu.entrySet() )
		{
			String clave = entrada.getKey(); //Guardamos la clave para luego buscar el objeto espechfico
			menu.get(clave).mostrar(textArea);
		}	
	}
	
	
	
	
	/*
	 * 
	 */
	public boolean modificarProductos(String key, int cantidad, int precio) 
	{
		for (Map.Entry<String, Producto> entrada : menu.entrySet() )
		{ 
				if( entrada.getKey().equals(key)) { //Encontramos el obj.
					menu.get(key).setCantDisponibleEnElRestaurante(cantidad);;
					menu.get(key).setPrecioProducto(precio);
					return true; 
				}
		}	
		return false;
	}
	
	
	
	
	/*
	 *  Se utilizara este metodo para que cada producto del menu
	 *  llame al metodo donde sus datos seran traspasado al archivo
	 */
	public void reporteProductosArchivo() throws IOException
	{
		for (Map.Entry<String, Producto> entrada : menu.entrySet() )
		{
			String clave = entrada.getKey(); //Guardamos la clave para luego buscar el objeto espechfico
			menu.get(clave).mostrarProductoArchivo();
		}	
	}
	
	
	
	
	/*
	 * calcularTotalGastosProductos busca obtener el precio que se invierte en
	 * todos los productos que el restaurante posee (no al que se vende), y asi tener
	 * el gasto en bruto de los productos del local y permitirnos calcular la rentabilidad del negocio.
	 */
	public int calcularTotalGastosProductos()
	{
		int sumaPreciosProductos = 0;
		
		//Recorrer cada uno de los Empleados, y obtener su sueldo e ir acumulandolos.
		if (menu != null && menu.isEmpty()!= true)
		{
			return sumaPreciosProductos;
		}
		
		return 0;
	}
	
	
	
	
	
	
	
}