package clasesRestorApp;

import java.io.IOException;
import javax.swing.JTextArea;


public class Restaurante
{
	private String nombre;
	private String direccion;
	private ListaMesas mesasRestaurante; // Lista de las Mesas que el restaurante posee.
	private TablaEmpleados empleadosRestaurante; // Tabla con Key el rut de cada Empleado;
	private MapaMenu menuRestaurante; // Mapa con Key el codeProducto de cada Producto;
	private Archivos arc;
	
	// Constructores
	public Restaurante()
	{
		nombre=null;
		direccion=null;
		mesasRestaurante=new ListaMesas();
		empleadosRestaurante=new TablaEmpleados();
		menuRestaurante=new MapaMenu();
		arc = new Archivos();
		arc.cargarDatos(mesasRestaurante,menuRestaurante,empleadosRestaurante); //falta empleados
	}
	public Restaurante(String nombre, String direccion) throws IOException
	{
		this.nombre = nombre;
		this.direccion = direccion;
		mesasRestaurante = new ListaMesas();
		empleadosRestaurante = new TablaEmpleados();
		menuRestaurante = new MapaMenu();
		arc = new Archivos(); //se crea un espacio de memoria para los archivos
		arc.cargarDatos(mesasRestaurante,menuRestaurante,empleadosRestaurante);
	}
	
	// Getter & Setter
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	
	
	//Metodos
	
	/*
	 * 
	 * 
	 * 
	 */
	public boolean agregarGarzon(String rut, String nombre, int sueldo, int edad, String nivelDeIngles,int mesasAtendidas) throws IOException
	{
		Garzon nuevo = new Garzon(rut,nombre,sueldo,edad,nivelDeIngles,mesasAtendidas);
		arc.escribirTxTGarzones(rut, nombre, sueldo, edad, nivelDeIngles, mesasAtendidas);
		return empleadosRestaurante.agregarGarzon(nuevo);
	}
	
	public boolean agregarCocinero(String rut, String nombre, int sueldo, int edad) throws IOException
	{
		Cocinero nuevo = new Cocinero(rut,nombre,sueldo,edad);
		arc.escribirTxTCocineros(rut, nombre, sueldo, edad);
		return empleadosRestaurante.agregarCocinero(nuevo);
	}
	
	public boolean agregarCajero(String rut, String nombre, int sueldo, int edad) throws IOException //Se cambia nombre por que estaba mal.
	{
		Cajero nuevo = new Cajero(rut,nombre,sueldo,edad);
		arc.escribirTxTCajeros(rut, nombre, sueldo, edad);
		return empleadosRestaurante.agregarCajero(nuevo);
	}
	
	/*
	 * Este metodo se utilizara para agregar mesa desde la ventana AgregarMesa
	 * Recibe el codMesa, se declara e instancia el objeto Mesa dado su codigo
	 * Dependiendo de si la mesa se agrego o no, ser� el booleano que retorne
	 */
	public boolean agregarMesa(int codMesa)//se ocupa en la ventana agregarMesa
	{
		Mesa mesaNueva = new Mesa(codMesa);
		return mesasRestaurante.agregarMesa(mesaNueva);
	}
	
	
	
	
	/*
	 * Este metodo se utilizara para agregar pedido desde la ventana AgregarPedido
	 * Recibe el codMesa y codPedido, se verifica si la mesa existe
	 * Si no existe, retorna false. Si la mesa existe, se declara e instancia,
	 * Retornara un booleano dependiendo si el pedido se logr� agregar o no.
	 */
	public boolean agregarPedido(int codMesa,int codPedido)// se ocupa en la ventana agregarPedido
	{
		if(mesasRestaurante.buscarMesa(codMesa)==true){// si el codmesa existe  
			Mesa m = mesasRestaurante.obtenerMesa(codMesa);  // obtengo la mesa para agregarle el pedido
			return m.agregarPedido(codPedido);//retorna booleano dependiendo si fue agregado o no
		}
		else //La mesa no existe
		{
			
			return false;
		}
	}
	
	
	
	
	/*
	 * Este metodo se utilizara para agregar productos al menu
	 * desde la ventana AgregarProductoMenu.
	 * Recibe el objeto Producto, y dependiendo si el producto se logr�
	 * agregar o no, ser� el retorno (true o false) de este proceso.
	 */
	public boolean agregarProductoMenu(Producto producto)//  se ocupa en la ventana agregarProductoMenu
	{
		return menuRestaurante.agregarProductoAlMenu(producto);
	}
	
	
	
	
	/*
	 * agregarProductoAlPedido busca agregar productos al pedido correspondiente segun su codigo
	 * Recibe el codigo de la mesa,codigo del pedido y codigo del producto
	 * Se instancia tanto el objeto mesa como producto
	 * Si ambos no son null, entonces se llama al metodo del objeto mesa para verificar si
	 * se puedo agregar el producto. Basta con que uno sea null para que retorne false.
	 */
	public boolean agregarProductoAlPedido(int codMesa,int codigoPedido,String codProducto)
	{
		Mesa mesa = mesasRestaurante.obtenerMesa(codMesa);
		Producto producto = menuRestaurante.obtenerProductoEspecifico(codProducto);
		if(mesa !=null && producto!=null){
			return mesa.agregarProductoAPedido(codigoPedido, producto);
		}
		return false;
	}
	
	
	
	
	/*
	 * elminarMesa recibira el codigo de la mesa
	 * Se verifica que la lista de mesas no sea null y que la mesa exista(sea encontrada)
	 * retornara true este metodo si la mesa fue eliminada
	 * retornara false en caso que la mesa no se haya podido eliminar
	 * retornara false si la lista de mesas era null
	 */
	public boolean elminarMesa(int codigoMesa)
	{
		if(mesasRestaurante!=null){
			if(mesasRestaurante.buscarMesa(codigoMesa) == true){
				if(mesasRestaurante.eliminarMesa(codigoMesa)==true){
					return true;
				}
				
			}
			return false;
		}
		
		return false;
	}
	
	
	
	
	/*
	 * Este metodo eliminara el pedido de la mesa correspondiente
	 * Los parametros del metodo seran codigo de la mesa y del pedido
	 * Se verifica que la mesa exista para poder referenciarla
	 * El metodo retornara true si fue posible eliminar el pedido
	 * retornara false si no se pudo eliminar
	 * retornara false si no se encontro la mesa 
	 */
	public boolean eliminarPedido(int codMesa,int codPedido)
	{
		if(mesasRestaurante.buscarMesa(codMesa) == true){
			Mesa mesa = mesasRestaurante.obtenerMesa(codMesa);
			if(mesa.elimnarPedido(codPedido)==true){
				return true;
			}
			else{
				return false;
			}
		}
		return false;
	}
	
	
	
	
	/*
	 * eliminarProductoMenu eliminara el producto correspondiente del menu
	 * Recibe por parametro el id del producto de tipo String
	 * retornara true si fue posible eliminar el producto
	 * Retornara false en caso contrario
	 */
	public boolean eliminarProductoMenu(String idProducto)
	{
		return menuRestaurante.eliminarProductoDelMenu(idProducto);
	}
	
	
	
	
	/*
	 * eliminarProductoPedido eliminara el producto solicitado del pedido correspondiente a su mesa
	 * El metodo recibira por parametro el codigo de la mesa ,el codigo del pedido y el codigo del producto
	 * declario e instancio referencias a la mesa y el producto a utilizar dado sus codigos
	 * Solamente si ambas referencias no son nulas,entonces el objeto mesa llama el metodo que se elimine el producto del pedido
	 * retornara true si fue posible eliminar el producto del pedido
	 * rerornara false si no fue posible eliminarlo
	 * retornara false en cualquier otro caso que donde los objetos instanciados sean null
	 */
	public boolean eliminarProductoPedido(int codMesa,int codigoPedido,String codProducto)
	{
		Mesa mesa = mesasRestaurante.obtenerMesa(codMesa);
		Producto producto = menuRestaurante.obtenerProductoEspecifico(codProducto);
		if(mesa !=null && producto!=null){
			return mesa.eliminarProductoAPedido(codigoPedido, producto);
		}
			
		return false;
	}
	
	
	
	
	/*
	 * editarMesa y buscarMesaX logran obtener manejo sobre el estado de la mesa y
	 * la existensia de la misma en los casos que se necesite (agregar, eliminar, editar, etc.)
	 */
	public void editarMesa (int codeMesa, String estado ) 
	{
		mesasRestaurante.editarMesaX(codeMesa, estado);
	}
	
	public boolean buscarMesaX(int id) 
	{
		return mesasRestaurante.buscarMesa(id);
	}
	
	
	
	
	/*
	 * 
	 */
	public boolean modificarProductos(String key, int cantidad, int precio) 
	{
		return menuRestaurante.modificarProductos(key, cantidad, precio);
	}
	
	
	
	
	/*
	 * mostrar*Ventana busca otorgar un reporte por las ventanas producidas de
	 * las Mesas, Pedidos y Productos mediante el llamado a sus metodos asociados
	 */
	public void mostrarMesasVentana(JTextArea textArea) //FALTA EL METODO EN LISTAMESAS.
	{
		mesasRestaurante.mostrarMesasVentanaX(textArea);
	}
	
	public void mostrarPedidosVentana(JTextArea textArea) 
	{
		mesasRestaurante.mostrarPedidosVentanaX(textArea);
	}
	
	public void mostrarProductosVentana(JTextArea textArea) 
	{
		menuRestaurante.mostrarProductosVentanaX(textArea);
	}
	public void mostrarEmpleadosVentana(JTextArea textArea)
	{
		empleadosRestaurante.mostrarEmpleadosVentanaX(textArea);
	}
	
	/*
	 * mostrar*Archivo se encargara de que se efectue el reporte de las mesas
	 * los Pedidos y Productos por archivo mediante el llamado a los metodos asociados
	 */
	public void mostrarMesasArchivo() throws IOException{
		mesasRestaurante.reporteMesasArchivo();
	}

	public void mostrarPedidosArchivo() throws IOException{
		mesasRestaurante.reportePedidosArchivo();
	}

	public void mostrarProductosArchivo()throws IOException{
		menuRestaurante.reporteProductosArchivo();
	}
	public void mostrarEmpleadosArchivos()throws IOException{
		empleadosRestaurante.repoteEmpleadosArchivo();
	}
	
	
	
	/*
	 * renovarInventario son aquellos metodos que le permite al restaurante renovar y proveer m�s stock
	 * Ya sea a un producto espec�fico, o a todos los productos al mismo tiempo.
	 */
	public boolean renovarInventarioDelProducto(String codigoProducto, int cantidadStock)
	{
		if(menuRestaurante != null) {
			menuRestaurante.renovarInventario(codigoProducto, cantidadStock);
			return true;
		}
		return false;	
	}
	
	public boolean renovarInventarioDelMenu(int cantidadStockTotal)
	{
		if(menuRestaurante != null) {
			menuRestaurante.renovarInventario(cantidadStockTotal);
			return true;
		}
		return false;	
	}
	
	
	
	
	/*
	 * obteneBoletaDeMesa es una sucesi�n de procesos que buscan tomar los pedidos de una mesa
	 * y ser adjuntados (en un arreglo) para imprimirlos en una boleta a ser recibida ante el pago de la misma.
	 * Para ello (antes de eliminar los pedidos de la mesa) se busca �sta y se pide obtener la Boleta de la mesa.
	 */
	public Pedido[] obteneBoletaDeMesa(int codeMesa)
	{
		if (mesasRestaurante != null) {
			return mesasRestaurante.obtenerMesa(codeMesa).obtenerBoletaMesa();
		}
		return null;
	}
	
	
	
	
	/*
	 * calcularRentabilidadDelNegocio es una funcionalidad que invita al Admin
	 * a saber cuan viable es el negocio en aspectos econ�micos.
	 * Para ello se le pide que ingrese cuanto es el TA del negocio y en una serie
	 * de procesos se puede obtener el ROA del local (en aspectos porcentuales).
	 */
	public double calcularRentabilidadDelNegocio (int ActivoTotal)
	{
		if(mesasRestaurante != null && empleadosRestaurante != null && menuRestaurante != null) {
			
			double ROA = 0.0; //RentabilidadEconomica
			int NI = 0; //IngresoNeto
			int costesDelNegocio = 0;
			
			
			costesDelNegocio = (empleadosRestaurante.calcularTotalSueldos() + menuRestaurante.calcularTotalGastosProductos());
			
			//IngresoNeto(NI) = Ganancias - CostesDelNegocio;
			NI = (mesasRestaurante.calcularGanancias() - costesDelNegocio);
			
			//RentabilidadEconomica(ROA) = IngresoNeto(NI) / ActivoTotal(TA);
			ROA = NI / ActivoTotal;
			
			return (ROA*100);
		}
		
		return -1;
	}

    public boolean AgregarPedido(int codMesa, int codPedido) {
        return mesasRestaurante.AgregarPedido(codMesa,codPedido);
    }
	
	
	
	
	
	
	
	
}