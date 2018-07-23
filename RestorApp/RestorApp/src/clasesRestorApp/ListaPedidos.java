package clasesRestorApp;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTextArea;


public class ListaPedidos
{
	private ArrayList<Pedido> pedidos;
	private Archivos arc;

	
	// Constructor
	public ListaPedidos()
	{
		arc=new Archivos();
		pedidos = new ArrayList<Pedido>();
	}


	// Metodos
	/*
	 * Busca el pedido en la lista de pedidos, recibiendo un numero y comparandolo
	 * con el resto de numeros de los pedidos hasta encontrarla y retornar true , o
	 * false en caso contrario. Sobrecarga implmentada para poder tener el indice
	 * del pedido buscado en casos especiales que se necesite.
	 */
	public boolean buscarPedido(int numero)
	{
		if (pedidos != null && pedidos.isEmpty() != true) {
			for (int i = 0; i < pedidos.size(); i++)
			{
				if (pedidos.get(i).getNumDePedido() == numero) {
					return true;
				}
			}
		}
		return false;
	}

	public int buscarPedido(Pedido pedidoABuscar)
	{
		if (pedidos != null && pedidos.isEmpty() != true) {
			if (pedidos.contains(pedidoABuscar)) {
				return pedidos.indexOf(pedidoABuscar);
			}
		}
		return -1;
	}

	
	/*
	 * agregarPedido cumple la funcion de instanciar un pedido nuevo en la Lista de
	 * Pedidos de la Mesa. El metodo es utilizado en Sobrecarga sea el caso que el
	 * pedido viene completamente listo (como objeto) para ingresarse. Es importante
	 * tener en cosideracin, que los pedidos particulares pueden repetirse dentro
	 * de la mesa, ya que c/cliente de la mesa "pide" lo que desee, an si es lo
	 * mismo que lo de otra persona de la misma mesa.
	 */
	public boolean agregarPedido(int numPedidoPorAgregar, int idMesa)
	{
		Pedido p = new Pedido(numPedidoPorAgregar, idMesa);

		if(buscarPedido(p.getNumDePedido()) == false){ // Comprobamos si la mesa ya existe
			pedidos.add(p);
			return true;
		}
		return false;
	}
	/*El metodo recibe el numero del pedido , el producto a agregar y el codigo de la mesa donde se agregara el pedido
	 * Este metodo realiza dos procesos dependiendo si el pedido existe o no
	 *  Si el pedido existe, entonces agrega el producto a ese pedido
	 *  retornara false porque el pedido no fue agregado
	 *  Si no existe, crea el nuevo pedido y agrega el producto a ese pedido
	 *  retornara true  si el pedido fue agregad
	 *  
	 */
	public boolean agregarPedido(int numeroPedido, Producto producto, int idMesa)
	{

		if (buscarPedido(numeroPedido) == true) { // Comprobamos si el pedido ya existe
			for (int i = 0; i < pedidos.size(); i++) // Si el pedido ya existe, le agregamos directamente el producto.
			{
				if (pedidos.get(i).getNumDePedido() == numeroPedido) {
					pedidos.get(i).agregarProductoPedido(producto);
					return false; //Retorna "false" porq no se agreg nuevo pedido, a pesar que si al pedido se le agreg un nuevo producto.
				}
			}
		}
		
		Pedido p = new Pedido(numeroPedido, idMesa);
		p.agregarProductoPedido(producto);
		pedidos.add(p);
		return true;
	}

	
	/*
	 * eliminarPedido se encarga de eliminar uno o ms pedidos particulares de cada
	 * pedido. En caso de exito retorna un verdadero hacia la peticin. Metodo esta
	 * implementado en sobrecarga por 2 razones: 1, en caso que se desee obtener el
	 * pedido eliminado y no perder sus datos an pero si sacarle del ArrayList. Y
	 * 2, en caso que se desee limpiar toda la lista de pedidos debido a que el
	 * cliente desea cancelar Todos los pedidos de la mesa o pagar la cuenta de la
	 * misma.
	 */
	public boolean eliminarPedido(int numPedidoAEliminar)
	{
		for (int i = 0; i < pedidos.size(); i++)
		{
			if (pedidos.get(i) != null) {
				if (pedidos.get(i).getNumDePedido() == numPedidoAEliminar) {
					pedidos.remove(i);
					return true;
				}
			}
		}
		return false;
	}

	// Sobrecarga 1
	public Pedido eliminarPedido(Pedido pedidoRealizado)
	{
		for (int i = 0; i < pedidos.size(); i++)
		{
			if (pedidos.get(i) != null) {
				if (pedidos.get(i).getNumDePedido() == pedidoRealizado.getNumDePedido()) {
					pedidos.remove(i);
					return pedidoRealizado;
				}
			}
		}
		return null;
	}

	// Sobrecarga 2
	public void eliminarPedido() {
		pedidos.clear();
	}
	/*Este metodo recibe por parametro el codigo del pedido y el objeto producto
	 * Recorre la lista de pedidos hasta encontrar el pedido donde se desea eliminar el producto
	 * retornara true si el producto fue eliminado del pedido
	 * retornara false en caso contrario
	 *  
	 */
	public boolean eliminarProductoPedido(int codigoPedido, Producto producto){
		for(int i=0;i<pedidos.size() && pedidos.get(i)!=null;i++){
			if(pedidos.get(i).getNumDePedido() == codigoPedido){
				if(pedidos.get(i).eliminarProductoPedido(producto)==true){
					return true;
				}
				else{
					return false;
				}
			}
		}
		return false;
		
	}
	
	/*
	 * obtenerPedidoParaBoleta es un proceso que ocurre cuando se desea pagar una
	 * mesa ocupada y antes de que se eliminen todos los pedidos de la misma mesa.
	 * El metodo copia la lista de pedidos y retorna aquella copia para ser
	 * "impresa" como parte de la boleta.
	 */
	public String obtenerPedidosParaBoleta(int codMesa)
	{
		int PrecioT=0;
		String info="\nNo tiene pedidos\n";
		if (pedidos != null && pedidos.size()>0)
		{
			for(int i=0;i<pedidos.size();i++)
			{
				if(i==0) info="\nMesa N°: "+codMesa+".\nN° del Pedido\tPrecio\n"+pedidos.get(i).getNumDePedido()+"\t\t"+pedidos.get(i).getPrecio()+"\n";
				info=info+pedidos.get(i).getNumDePedido()+"\t\t"+pedidos.get(i).getPrecio()+"\n";
				PrecioT+=pedidos.get(i).getPrecio();
			}
			info=info+"\nPrecio Total:   $"+PrecioT+".";
		}
		return info;
	}

	
	/*
	 * editarPedidoRealizado busca editar o cambiar el pedido entero en caso de
	 * algn mal entendido en el 1er ingreso del pedido.
	 */
	public Pedido editarPedidoRealizado(Pedido pedidoRealizado)
	{
		if (pedidos != null) {
			if (buscarPedido(pedidoRealizado) != -1) {
				return pedidos.set(buscarPedido(pedidoRealizado), pedidoRealizado);
			}
		}
		return null;
	}

	
	/*
	 * obtenerCantidadPedidos es un procedimiento simple para verificar la cantidad
	 * de pedidos que posee el restaurante.
	 */
	public int obtenerCantidadPedidos()
	{
		return pedidos.size();
	}

	
	/*
	 * Este metodo recibe un codigo del pedido y retorna una referencia al pedido obtenido
	 */
	public Pedido obtenerPedido(int codPedido)
	{
		for (int i = 0; i < pedidos.size() && pedidos.get(i) != null; i++)
		{
			if (pedidos.get(i).getNumDePedido() == codPedido) {
				Pedido aux = pedidos.get(i);
				return aux;
			}
		}
		return null;
	}

																											
	/*
	 * 
	 */
	public int darNumeroDePedido (int numero)
	{
		for (int i=0;i<pedidos.size();i++)
		{
			if(pedidos.get(i).getNumDePedido() == numero )
			{
				return pedidos.get(i).getNumDePedido();
			}
		}
		return -1; //En caso de que no exista la mesa retorna -1
	}
	
	
	
	
	
	/*
	 * 
	 */
	public void mostrarPedidosVentanaX(JTextArea textArea) 
	{
		for (int i=0 ; i < pedidos.size() ;i++ )
		{
			pedidos.get(i).mostrar(textArea);
		}
	}
	/*
	 *En este metodo los datos del pedido se van pasando por parametro a la clase archivo para generar el archivo y escribir sus datos en el  
	 */
	public void mostrarSusDatos() throws IOException{
		Archivos archivo = new Archivos();
		for(int i=0;i<pedidos.size() && pedidos.get(i)!=null;i++){
			archivo.crearArchivoReportePedidos(Integer.toString(pedidos.get(i).getidMesa()),Integer.toString(pedidos.get(i).getNumDePedido()), Integer.toString(pedidos.get(i).getPrecio()));
		}
	}

	public void escribirTxTMesaPedidoProductos() throws IOException {
		for(int i=0;i<pedidos.size();i++)
		{
			Pedido ped=pedidos.get(i);
			arc.escribirSoloPedidoTxT(Integer.toString(ped.getidMesa()), Integer.toString(ped.getNumDePedido()));
			ped.escribirTxTProductosPedido();
		}
	}
	
	
	
	
}