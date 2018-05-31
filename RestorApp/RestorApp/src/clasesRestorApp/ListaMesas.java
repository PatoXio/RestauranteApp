package clasesRestorApp;

import java.util.*;
import java.io.IOException;

import javax.swing.JTextArea;



public class ListaMesas
{
	private ArrayList<Mesa> listaMesas;
	private Archivos arc;

	
	// Constructor
	public ListaMesas()
	{
		arc=new Archivos();
		listaMesas = new ArrayList<Mesa>();
	}
	
	
	// M�todos
	/*
	 * 
	 * 
	 * 
	 * 
	 */
	public boolean AgregarPedido(int codMesa, int codPedido)
	{
		for(int i=0;i<listaMesas.size();i++)
		{
			if(listaMesas.get(i).getCodeMesa()==codMesa)
			{
				return listaMesas.get(i).agregarPedido(codPedido);
			}
		}
		return false;
	}
	/*
	 * buscarMesa recorre la lista de mesas del restaurante mediante sus CodeMesa y
	 * busca la deseada. En el caso exitoso de �sta b�squeda, retorna un "true" o
	 * "false" en caso contrario.
	 */
	public boolean buscarMesa(int id)
	{
		for (int i = 0; i < listaMesas.size(); i++)
		{
			if (listaMesas.get(i).getCodeMesa() == id) {
				return true;
			}
		}
		return false;
	}
	
	
	
	
	/*
	 * obtenerMesa busca obtener una mesa espec�fica mediante su codigo y as� poder
	 * almacenarla y/o usarla
	 */
	public Mesa obtenerMesa(int codigo)
	{
		for (int i = 0; i < listaMesas.size() && listaMesas.get(i) != null; i++)
		{
			if (listaMesas.get(i).getCodeMesa() == codigo) {
				Mesa aux = listaMesas.get(i);
				return aux;
			}
		}
		return null;
	}
	
	
	
	
	/*
	 * agregarMesa permite insertar en la lista de Mesas existentes una mesa nueva.
	 * Para ello, se comprueba si la mesa que se desea agregar ya existe mediante la
	 * b�squeda en la lista de mesas existentes. Posterior a ello, en caso de que no
	 * sea as�, la mesa de adhiere a la lista de mesas que el restaurante posee.
	 */
	public boolean agregarMesa(Mesa mesaPorAgregar)
	{
		if (buscarMesa(mesaPorAgregar.getCodeMesa()) == true) {
			return false;
		}
		listaMesas.add(mesaPorAgregar);
		return true;
	}

	
	
	
	/*
	 * darCodeMesa tiene la intenci�n de encontrar una mesa del restaurante por su
	 * c�digo asignado. Si �sta existe, el m�todo retornar� el mismo CodeMesa, sino
	 * un "-1" representativo de la no existencia.
	 */
	public int darCodeMesa(int id)
	{
		for (int i = 0; i < listaMesas.size(); i++) // Recorremos las mesas
		{
			if (listaMesas.get(i).getCodeMesa() == id) {
				return listaMesas.get(i).getCodeMesa();
			}
		}
		return -1; // En caso de que no exista la Mesa pedida retorna -1
	}
	
	
	
	
	/*
	 * eliminarMesa busca sacar la mesa del ArrayList de Mesas mediante el
	 * recibimiento de su ID. En caso de que la mesa exista, �sta es removida de la
	 * lista retornando el proceso de manera positiva. Metodo es implementado en
	 * sobrecarga para el caso que se desee tener aquella mesa eliminada a�n habien
	 * sido exitoso su removisi�n.
	 */
	public boolean eliminarMesa(int id)
	{
		for (int i = 0; i < listaMesas.size(); i++)
		{
			if (listaMesas.get(i) != null) { //Separados para mejor lectura visual
				if (listaMesas.get(i).getCodeMesa() == id) {
					listaMesas.remove(listaMesas.get(i));
					return true;
				}
			}
		}
		return false;
	}
	
	//Sobrecarga
	public Mesa eliminarMesa(Mesa mesaPorEliminar) {
		if (buscarMesa(mesaPorEliminar.getCodeMesa()) != false)
		{
			for (int i = 0; i < listaMesas.size(); i++)
			{
				if (listaMesas.get(i) != null) {
					if (mesaPorEliminar.getCodeMesa() == listaMesas.get(i).getCodeMesa()) {
						listaMesas.remove(i); // Si encuentro la mesa deseada se remueve
						return mesaPorEliminar;
					}
				}
			}
		}
		return null;
	}
	
	
	
	
	/*
	 * obtenerCantidadMesas es un procedimiento simple
	 * para verificar la cantidad de mesas que posee el restaurante.
	 */
	public int obtenerCantidadMesas() {
		return listaMesas.size();
	}
	
	
	
	
	/*
	 * editarMesaX nos permite hacer funcional la dinamica del restaurante
	 * en lo que respecta a las mesas que �ste posee.
	 * Para ello buscamos la mesa espec�fica y le asignamos su estado.
	 */
	public void editarMesaX (int codeMesa, String estado)
	{
		for (int i=0;i<listaMesas.size();i++) 
		{
			if (listaMesas.get(i).getCodeMesa() == codeMesa) 
			{
				listaMesas.get(i).setEstadoMesa(estado); // Son 3 estados disponibles: 1.- Libre ; 2.- Ocupada; 3.- Sucia
			}
		}
	} 
	
	
	
	
	/*
	 * restarCantidadPedidosMesa debe actuar una vez haya sido eliminado uno m�s
	 * pedidos de la Mesa en que se est� actuando. En caso de que la mesa exista, en
	 * ella se le resta 1 al total de pedidos en consecuencia a la eliminaci�n
	 * previa de �ste. M�todo es implementado en sobrecarga para el caso especial de
	 * que hayan eliminado todos los pedidos de la mesa de manera inmediata. Y as�
	 * dejar la mesa en cantPedidos=0; lista para ocupar por otros clientes.
	 */
	public void restarCantidadPedidosMesa(int identificadorMesa)
	{
		for (int i = 0; i < listaMesas.size(); i++)
		{
			if (listaMesas.get(i) != null) {
				if (listaMesas.get(i).getCodeMesa() == identificadorMesa) {
					listaMesas.get(i).setCantPedidos(listaMesas.get(i).getCantPedidos() - 1);
				}
			}
		}
	}

	public void restarCantidadPedidosMesa(Mesa mesaAReiniciar)
	{
		for (int i = 0; i < listaMesas.size(); i++)
		{
			if (listaMesas.get(i) != null) {
				if (listaMesas.get(i).getCodeMesa() == mesaAReiniciar.getCodeMesa()) {
					listaMesas.get(i).setCantPedidos(0);
				}
			}
		}
	}

	
	
	
	/*
	 * mostrar*VentanaX se asocia con nuestro paquete de ventanas para
	 * mostrar nuestros Pedidos y Mesas respectivos.
	 */
	public void mostrarPedidosVentanaX(JTextArea textArea) 
	{
		for (int i=0 ; i<listaMesas.size() ;i++ )
		{
			listaMesas.get(i).mostrarPedidosVentana(textArea);
		}
	}
	
	public void mostrarMesasVentanaX(JTextArea textArea){
		for (int i=0 ; i<listaMesas.size() ;i++ )
		{
			listaMesas.get(i).mostrar(textArea);;
		}
	}
	
	
	
	
	/*
	 * Los datos de cada mesa se va pasando por parametro
	 * a la clase archivo donde seran escrito
	 */
	public void reporteMesasArchivo() throws IOException{
		Archivos archivo = new Archivos();
		for (int i=0 ; i<listaMesas.size() && listaMesas.get(i)!=null ;i++ )
		{
			archivo.crearArchivoReporteMesas(Integer.toString(listaMesas.get(i).getCodeMesa()), Integer.toString(listaMesas.get(i).getCantPedidos()), listaMesas.get(i).getEstadoMesa());
		}
	}
	
	
	
	
	/*
	 * Se va reccoriendo la lista de mesas,
	 * para que en cada objeto mesa llame al metodo que
	 * traspase sus datos al archivo
	 */
	public void reportePedidosArchivo() throws IOException{
		for (int i=0 ; i<listaMesas.size() && listaMesas.get(i)!=null ;i++ )
		{
			listaMesas.get(i).mostrarDatosPedidosArchivo();
			
		}
	}
	
	
	
	
	/*
	 * obtenerMesaABoletear es un proceso que obtendr� todos los pedidos de la mesa en un arreglo
	 * con el fin de hacer el pago e impresi�n de la boleta a el/los cliente/s de la misma mesa.
	 */
	public Pedido[] obtenerMesaABoletear(int identificadorMesa)
	{
		if (listaMesas != null && listaMesas.isEmpty() != true) {
			return obtenerMesa(identificadorMesa).obtenerBoletaMesa();
		}
		return null;
	}

	
	
	
	/*
	 * calcularGanancias es una seguidilla de procesos que obtiene las ganancias de cada mesa
	 * (y en consecuencia de cada pedido existente) para poder retornar el valor
	 * y permitirnos calcular la rentabilidad del negocio.
	 */
	public int calcularGanancias()
	{
		// TODO Auto-generated method stub
		return 0;
	}


	public void escribirTxTCompletoMesas() throws IOException {
		
		for(int i=0;i<listaMesas.size();i++)
		{
			Mesa mesa=listaMesas.get(i);
			arc.escribirTxTMesas(Integer.toString(mesa.getCodeMesa()));
		}
	}


	public void escribirTxTCompletoPedidos() throws IOException {
		
		for(int i=0;i<listaMesas.size();i++)
		{
			Mesa mesa=listaMesas.get(i);
			mesa.escribirTxTMesaPedidoProductos();
		}
	}
	
	
	
}