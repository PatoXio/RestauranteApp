package clasesRestorApp;

import java.io.IOException;

import javax.swing.JTextArea;



public class Mesa
{
	private int codeMesa;
	private int cantPedidos;
	private String estadoMesa; // Son 3 estados disponibles: 1.- Libre ; 2.- Ocupada; 3.- Sucia
	private ListaPedidos pedidosMesa;
	// private Garzon garzonAsignado; //Ahn no implementado

	
	// Constructor
	public Mesa()
	{
		codeMesa=0;
		cantPedidos=0;
		estadoMesa= "Libre";
		pedidosMesa = new ListaPedidos();
	}
	public Mesa(int codeMesa)
	{
		this.codeMesa = codeMesa;
		this.cantPedidos = 0; //hay que arreglar esto en archivos, ya que deberia de recibir algo
		this.estadoMesa = "Libre";
		pedidosMesa = new ListaPedidos();
		// this.garzonAsignado = garzonAsignado; //Ahn no implementado
	}

	
	// Getter & Setter
	public int getCodeMesa() {
		return codeMesa;
	}
	public void setCodeMesa(int codeMesa) {
		this.codeMesa = codeMesa;
	}

	public int getCantPedidos() {
		return cantPedidos;
	}
	public void setCantPedidos(int cantPedidos) {
		this.cantPedidos = cantPedidos;
	}

	public String getEstadoMesa() {
		return estadoMesa;
	}
	public void setEstadoMesa(String estadoMesa) {
		this.estadoMesa = estadoMesa;
	}

	public ListaPedidos getPedidosMesa() {
		return pedidosMesa;
	}
	public void setPedidosMesa(ListaPedidos pedidosMesa) {
		this.pedidosMesa = pedidosMesa;
	}

	/*
	 * public Garzon getGarzonAsignado() { return garzonAsignado; }
	 * public void setGarzonAsignado(Garzon garzonAsignado) {
	 * this.garzonAsignado = garzonAsignado;
	 * }
	 */

	
	// Mhtodos
	/*
	 * limpiarMesa, ocuparMesa, mesaEnsuciada son procedimientos sencillos que
	 * buscan actualizar el estado de las mesas del restaurante. La secuencia de los
	 * estados de una Mesa son: Limpia -> Ocupada -> Sucia. Esto quiere decir que si
	 * la mesa esta limpia, se puede ocupar; Y si se desocupa, queda sucia, por lo
	 * que necesita limpiarse. El metodo retorna el estado de la mesa nuevo en el
	 * que queda luego de su estado anterior.
	 */
	public boolean limpiarMesa() {
		if (estadoMesa.equals("Sucia")) {
			estadoMesa = "Libre";
			return true;
		}
		return false;
	}

	public boolean ocuparMesa() {
		if (estadoMesa.equals("Libre")) {
			estadoMesa = "Ocupada";
			return true;
		}
		return false;
	}

	public boolean mesaEnsuciada() {
		if (estadoMesa.equals("Ocupada")) {
			estadoMesa = "Sucia";
			return true;
		}
		return false;
	}

	
	/*
	 * agregarPedido busca solventar varios casos espechficos en el sistema,
	 * para ello es que se ha implementado en sobrecarga el metodo:
	 * S1: Agregamos un producto a un pedido ya existente segun el NumPedido que recibe y el producto mismo.
	 * S2: Un pedido nuevo es ingresado seghn su codigo correspondiente,
	 * se busca agregar un pedido a la Lista completamente nuevo, si no retornarh false.
	 */
	public void agregarPedido(int codPedido,Producto producto)// es ocupado en el txt
	{
		if (pedidosMesa.agregarPedido(codPedido, producto, getCodeMesa()) == true) {
			cantPedidos++;
		}
	}
	
	public boolean agregarPedido(int codPedido)// agregar pedido si es que no es viejo o repetido
	{
		if(pedidosMesa.buscarPedido(codPedido)==true) {
			return false;
		}
		if(pedidosMesa.agregarPedido(codPedido, getCodeMesa())==true){
			cantPedidos++;
			setEstadoMesa("Ocupada");
			return true;
		}
		return pedidosMesa.agregarPedido(codPedido, getCodeMesa());
	}
	
	
	/*
	 * agregarProductoAPedido se utilizarh para insertar el producto al pedido de la mesa 
	 * Para ello se verifica que el pedido existe, y se llama al metodo agregarPedido
	 * El metodo retornara true si se logra agregar el producto al pedido, o false en caso contrario.
	 */
	public boolean agregarProductoAPedido(int codigoPedido, Producto producto){
		if(pedidosMesa.buscarPedido(codigoPedido)==true)
		{
			
			agregarPedido(codigoPedido,producto);
			return true;
		}
		
		return false;
	}
	/*
	 * Este metodo se utilizara para eliminar un pedido de la mesa correspondiente
	 * Recibe el codigo del pedido como parametro
	 * Si el pedido es encontrado,entonces  este sera eliminado
	 * La cantidad de pedidos para esta mesa disminuye 
	 * si la mesa se queda sin pedidos,entonces queda libre 
	 * retornara true si el pedido fue eliminado del objeto de tipo ListaPedidos
	 * retornara false si el pedido no es encontrado
	 */
	public boolean elimnarPedido(int codPedido){
		if(pedidosMesa.buscarPedido(codPedido)==true){
			cantPedidos--;
			if(cantPedidos==0){
				setEstadoMesa("Libre");
			}
			return pedidosMesa.eliminarPedido(codPedido);
		}
		return false;
	}
	/*
	 * Este metodo se utilizara para eliminar algun producto del pedido correspondiente
	 * Recibe por parametro el codigo del pedido y un objeto de tipo Producto
	 * Se verifica que el pedido exista para recien ahh verificar si es posible eliminar el producto
	 */
	public boolean eliminarProductoAPedido(int codigoPedido, Producto producto){
		if(pedidosMesa.buscarPedido(codigoPedido)==true)
		{
			
			if(pedidosMesa.eliminarProductoPedido(codigoPedido,producto)==true){
				return true;
			}
			else{
				return false;
			}
			
		}
		
		return false;
	}
	
	/*
	 * obtenerBoletaMesa es aquel metodo que recopila los pedidos de toda la mesa y
	 * Los manda en un arreglo copia para ser "impresos" en un recibo luego de hacer
	 * efectivo el pago de la mesa.
	 */
	public Pedido[] obtenerBoletaMesa() {
		if (pedidosMesa != null) {
			return pedidosMesa.obtenerPedidosParaBoleta();
		}
		return null;
	}
	
	/*
	 * mostrar y obtenerInformacion son mhtodos que buscan comunicarse hacia las ventanas para
	 * patentizar en ellas las Mesas y pedidos necesarios o solicitados por el usuario.
	 */
	public String obtenerInformacion() 
	{
		String info="codeMesa: " + getCodeMesa() + "\nCantidadDePedidos: " + getCantPedidos() + "\nEstadoMesa: " + getEstadoMesa();
		
		return info;
		
	}
	
	public void mostrar(JTextArea textArea) 
	{	
		textArea.append("\n\n" + obtenerInformacion() +"\n\n");
	}
	
	
	public void mostrarPedidosVentana(JTextArea textArea) 
	{
		pedidosMesa.mostrarPedidosVentanaX(textArea);
	}
	/*
	 * Este metodo se utilizara para llamar el metodo ubicado en listaPedidos y que muestre sus datos
	 * Como es solo para llamar no requiere de parametros y tampoco devuelve algo
	 */
	public void mostrarDatosPedidosArchivo() throws IOException{
		pedidosMesa.mostrarSusDatos();
	}

	
	
	
	
}