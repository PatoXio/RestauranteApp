package clasesRestorApp;

import java.io.IOException;
import javax.swing.JTextArea;


public class Restaurante
{
	private String nombre;
	private String direccion;
	private double activoNoCorriente;
	private ListaMesas mesasRestaurante; // Lista de las Mesas que el restaurante posee.
	private TablaEmpleados empleadosRestaurante; // Tabla con Key el rut de cada Empleado;
	private MapaMenu menuRestaurante; // Mapa con Key el codeProducto de cada Producto;
	private Archivos arc;
	
	// Constructor
	public Restaurante() throws IOException
	{
		nombre=null;
		direccion=null;
		activoNoCorriente=0;
		mesasRestaurante=new ListaMesas();
		empleadosRestaurante=new TablaEmpleados(this);
		menuRestaurante=new MapaMenu();
		arc = new Archivos();
		arc.cargarDatos(this);
		//arc.actualizarDatos(this);
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
	public double getActivoNoCorriente()
	{
		return activoNoCorriente;
	}
	public void setActivoNoCorriente(double activoNoCorriente)
	{
		this.activoNoCorriente=activoNoCorriente;
	}

	
	
	//Metodos
	
	/*
	 * 
	 * 
	 * 
	 */
	public boolean agregarJefe(String rut,String nombre,int edad, int sueldo) throws IOException
	{
		int cantE = 0;
		if(empleadosRestaurante!=null)
		{
			cantE = empleadosRestaurante.getSize();
		}
		JefeRestaurante nuevo=new JefeRestaurante(rut,nombre,edad,sueldo,cantE);
		arc.escribirTxTJefe(rut,nombre,edad,sueldo,cantE);
		return empleadosRestaurante.agregarJefe(nuevo);
	}
	
	public boolean agregarGarzon(String rut, String nombre, int sueldo, int edad, String nivelDeIngles,int mesasAtendidas) throws IOException
	{
		Garzon nuevo = new Garzon(rut,nombre,sueldo,edad,nivelDeIngles,mesasAtendidas);
		if(empleadosRestaurante.agregarGarzon(nuevo)==true)
		{
			arc.actualizarJefe(this);
			arc.escribirTxTGarzones(rut, nombre, sueldo, edad, nivelDeIngles, mesasAtendidas);
			return true;
		}
		return false;
	}
	
	public boolean agregarCocinero(String rut, String nombre, int sueldo, int edad) throws IOException
	{
		Cocinero nuevo = new Cocinero(rut,nombre,sueldo,edad);
		if(empleadosRestaurante.agregarCocinero(nuevo)==true)
		{
			arc.escribirTxTCocineros(rut, nombre, sueldo, edad);
			arc.actualizarJefe(this);
			return true;
		}
		return false;
	}
	
	public boolean agregarCajero(String rut, String nombre, int sueldo, int edad,int total, int diferencia) throws IOException //Se cambia nombre por que estaba mal.
	{
		Cajero nuevo = new Cajero(rut,nombre,sueldo,edad,total,diferencia);
		if(empleadosRestaurante.agregarCajero(nuevo)==true)
		{
			arc.escribirTxTCajeros(rut, nombre, sueldo, edad,total,diferencia);
			arc.actualizarJefe(this);
			return true;
		}
		return false;
	}
	
	/*
	 * Este metodo se utilizara para agregar mesa desde la ventana AgregarMesa
	 * Recibe el codMesa, se declara e instancia el objeto Mesa dado su codigo
	 * Dependiendo de si la mesa se agrego o no, sera el booleano que retorne
	 */
	public boolean agregarMesa(int codMesa) throws IOException//se ocupa en la ventana agregarMesa
	{
		Mesa mesaNueva = new Mesa(codMesa);
		if(mesasRestaurante.agregarMesa(mesaNueva)==true && empleadosRestaurante.atenderMesa(codMesa)==true)
		{
			mesasRestaurante.ocuparMesa(codMesa);
			arc.actualizarMesas(this);
			return true;
		}
		return false;
	}
	
	public boolean retirarMesa(int numeroMesa, int propina)
	{
		if(empleadosRestaurante.retirarMesa(numeroMesa,propina)==true)
		{
			mesasRestaurante.limpiarMesa(numeroMesa);
			return true;
		}
		return false;
	}
	
	
	/*
	 * Este metodo se utilizara para agregar pedido desde la ventana AgregarPedido
	 * Recibe el codMesa y codPedido, se verifica si la mesa existe
	 * Si no existe, retorna false. Si la mesa existe, se declara e instancia,
	 * Retornara un booleano dependiendo si el pedido se logro agregar o no.
	 */
	public boolean agregarPedido(int codMesa,int codPedido) throws IOException// se ocupa en la ventana agregarPedido
	{
		if(mesasRestaurante.buscarMesa(codMesa)==true){// si el codmesa existe  
			Mesa m = mesasRestaurante.obtenerMesa(codMesa);  // obtengo la mesa para agregarle el pedido
			if(m.agregarPedido(codPedido)==true)//retorna booleano dependiendo si fue agregado o no
			{
				arc.actualizarPedidos(this);
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Este metodo se utilizara para agregar productos al menu
	 * desde la ventana AgregarProductoMenu.
	 * Recibe el objeto Producto, y dependiendo si el producto se logro
	 * agregar o no, sera el retorno (true o false) de este proceso.
	 */
	public boolean agregarProductoMenu(Producto producto) throws IOException//  se ocupa en la ventana agregarProductoMenu
	{
		if(menuRestaurante.agregarProductoAlMenu(producto)==true)
		{
			arc.actualizarProductos(this);
			return true;
		}
		return false;
	}
	
	
	/*
	 * agregarProductoAlPedido busca agregar productos al pedido correspondiente segun su codigo
	 * Recibe el codigo de la mesa,codigo del pedido y codigo del producto
	 * Se instancia tanto el objeto mesa como producto
	 * Si ambos no son null, entonces se llama al metodo del objeto mesa para verificar si
	 * se puedo agregar el producto. Basta con que uno sea null para que retorne false.
	 */
	public boolean agregarProductoAlPedido(int codMesa,int codigoPedido,String codProducto) throws IOException
	{
		Mesa mesa = mesasRestaurante.obtenerMesa(codMesa);
		Producto producto = menuRestaurante.obtenerProductoEspecifico(codProducto);
		if(mesa !=null && producto!=null){
			if(mesa.agregarProductoAPedido(codigoPedido, producto)==true)
			{
				arc.actualizarPedidos(this);
			}
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
	public boolean elminarMesa(int codigoMesa) throws IOException
	{
		if(mesasRestaurante!=null){
			if(mesasRestaurante.buscarMesa(codigoMesa) == true){
				if(mesasRestaurante.eliminarMesa(codigoMesa)==true){
					arc.actualizarMesas(this);
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
	public boolean eliminarPedido(int codMesa,int codPedido) throws IOException
	{
		if(mesasRestaurante.buscarMesa(codMesa) == true){
			Mesa mesa = mesasRestaurante.obtenerMesa(codMesa);
			if(mesa.elimnarPedido(codPedido)==true){
				arc.actualizarPedidos(this);
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
	public boolean eliminarProductoMenu(String idProducto) throws IOException
	{
		if(menuRestaurante.eliminarProductoDelMenu(idProducto)==true)
		{
			arc.actualizarProductos(this);
			return true;
		}
		else
		{
			return false;
		}
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
	public boolean eliminarProductoPedido(int codMesa,int codigoPedido,String codProducto) throws IOException
	{
		Mesa mesa = mesasRestaurante.obtenerMesa(codMesa);
		Producto producto = menuRestaurante.obtenerProductoEspecifico(codProducto);
		if(mesa !=null && producto!=null){
			if(mesa.eliminarProductoAPedido(codigoPedido, producto)==true)
			{
				arc.actualizarProductos(this);
				arc.actualizarPedidos(this);
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Eliminar empleados
	 */
	
	public boolean eliminarEmpleado(String rut) throws IOException
	{
		if(empleadosRestaurante.eliminarEmpleado(rut)==true)
		{
			arc.actualizarEmpleados(this);
			return true;
		}
		return false;
	}
	
	/*
	 * editarMesa y buscarMesaX logran obtener manejo sobre el estado de la mesa y
	 * la existensia de la misma en los casos que se necesite (agregar, eliminar, editar, etc.)
	 */
	public void editarMesa (int codeMesa, String estado ) throws IOException 
	{
		mesasRestaurante.editarMesaX(codeMesa, estado);
		arc.actualizarMesas(this);
	}
	
	public boolean buscarMesaX(int id) 
	{
		return mesasRestaurante.buscarMesa(id);
	}
	
	/*
	 * 
	 */
	public boolean modificarProductos(String key, int cantidad, int precio) throws IOException 
	{
		if(menuRestaurante.modificarProductos(key, cantidad, precio)==true)
		{
			arc.actualizarProductos(this);
			return true;
		}
		return false;
	}
	
	
	
	/*
	 * mostrar*Ventana busca otorgar un reporte por las ventanas producidas de
	 * las Mesas, Pedidos y Productos mediante el llamado a sus metodos asociados
	 */
	public void mostrarMesasVentana(JTextArea textArea) //FALTA EL METODO EN LISTAMESAS.
	{
		mesasRestaurante.mostrarMesasVentanaX(textArea);
	}
	
	public void mostrarMesasGarzonesVentana(JTextArea textArea)
	{
		empleadosRestaurante.mostrarMesasGarzonesVentana(textArea);
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
	 * renovarInventario son aquellos metodos que le permite al restaurante renovar y proveer mas stock
	 * Ya sea a un producto especifico, o a todos los productos al mismo tiempo.
	 */
	public boolean renovarInventarioDelProducto(String codigoProducto, int cantidadStock) throws IOException
	{
		if(menuRestaurante != null) {
			menuRestaurante.renovarInventario(codigoProducto, cantidadStock);
			arc.actualizarProductos(this);
			return true;
		}
		return false;	
	}
	
	public boolean renovarInventarioDelMenu(int cantidadStockTotal) throws IOException
	{
		if(menuRestaurante != null) {
			menuRestaurante.renovarInventario(cantidadStockTotal);
			arc.actualizarProductos(this);
			return true;
		}
		return false;	
	}
	
	
	
	
	/*
	 * obteneBoletaDeMesa es una sucesion de procesos que buscan tomar los pedidos de una mesa
	 * y ser adjuntados (en un arreglo) para imprimirlos en una boleta a ser recibida ante el pago de la misma.
	 * Para ello (antes de eliminar los pedidos de la mesa) se busca esta y se pide obtener la Boleta de la mesa.
	 */
	public String obteneBoletaDeMesa(int codeMesa)
	{
		if (mesasRestaurante != null) {
			return mesasRestaurante.obtenerMesa(codeMesa).obtenerBoletaMesa();
		}
		return null;
	}

    public boolean AgregarPedido(int codMesa, int codPedido) throws IOException {
        if(mesasRestaurante.AgregarPedido(codMesa,codPedido)==true)
        {
        	arc.actualizarPedidos(this);
        	return true;
        }
        return false;
    }
    
    /*
     * METODOS DE BUSQUEDA
     */
    
    public Garzon traerGarzon(String rut)
    {
    	return empleadosRestaurante.traerGarzon(rut);
    }
    public Cocinero traerCocinero(String rut)
    {
    	return empleadosRestaurante.traerCocinero(rut);
    }
    public Cajero traerCajero(String rut)
    {
    	return empleadosRestaurante.traerCajero(rut);
    }
    public JefeRestaurante traerJefe()
    {
    	return empleadosRestaurante.traerJefe();
    }
    
    public Mesa obtenerMesa(int codMesa)
    {
    	return mesasRestaurante.obtenerMesa(codMesa);
    }
    public Producto obtenerProductoEspecifico(String id)
    {
    	return menuRestaurante.obtenerProductoEspecifico(id);
    }
    /*public boolean buscarGarzon(String rut)
    {
    	return empleadosRestaurante.buscarGarzon(rut);
    }
    public boolean buscarCocinero(String rut)
    {
    	return empleadosRestaurante.buscarCocinero(rut);
    }
    public boolean buscarCajero(String rut)
    {
    	return empleadosRestaurante.buscarCajero(rut);
    }
    public boolean buscarJefe()
    {
    	return empleadosRestaurante.buscarJefe();
    }*/
    
    public boolean buscarEmpleade(String rut)
    {
    	return empleadosRestaurante.buscarEmpleade(rut);
    }
    
    /*
     * METODOS MODIFICAR (POR AHORA HICE LOS 4 IGUALES YA QUE CADA EMPLEADO Y EL JEFE ESTAN EXPUESTOS A CAMBIOS)
     * 
     */
    
    public void modificarGarzon(String rut, String nombre, int edad, int sueldo) throws IOException
    {
    	empleadosRestaurante.modificarGarzon(rut, nombre, edad, sueldo);
    	arc.actualizarGarzones(this);
    }
    
    public void modificarCocinero(String rut, String nombre, int edad, int sueldo) throws IOException
    {
    	empleadosRestaurante.modificarCocinero(rut, nombre, edad, sueldo);
    	arc.actualizarCocineros(this);
    }
    
    public void modificarCajero(String rut, String nombre, int edad, int sueldo) throws IOException
    {
    	empleadosRestaurante.modificarCajero(rut, nombre, edad, sueldo);
    	arc.actualizarCajeros(this);
    }
    
    public void modificarJefe(String rut, String nombre, int edad, int sueldo) throws IOException
    {
    	empleadosRestaurante.modificarJefe(rut, nombre, edad, sueldo);
    	arc.actualizarJefe(this);
    }
    
    /*
     * 
     * METODOS EXTRAS
     * 
     */
    
    public int calcularTotalSueldos()
    {
    	return empleadosRestaurante.calcularTotalSueldos();
    }
    
    public String porcentajeDeEmpleados()
    {
    	return empleadosRestaurante.porcentajeDeEmpleados();
    }
    
    public int calcularTotalPrecioProductos()
    {
    	return menuRestaurante.calcularTotalPrecioProductos();
    }
    
    public int calcularCantidadTotalProductos()
    {
    	return menuRestaurante.calcularCantidadTotalProductos();
    }
    
    public String procentajeDeCadaProductoEnElVaLorToTal()
    {
    	return menuRestaurante.procentajeDeCadaProductoEnElVaLorToTal();
    }
    
    /*
	 * calcularRentabilidadDelNegocio es una funcionalidad que invita al Admin
	 * a saber cuan viable es el negocio en aspectos economicos.
	 * Para ello se le pide que ingrese cuanto es el TA del negocio y en una serie
	 * de procesos se puede obtener el ROA del local (en aspectos porcentuales).
	 */
    public double obtenerGanancias()
    {
    	return menuRestaurante.obtenerGanancias();
    }
    
	public String calcularRentabilidadDelNegocio ()
	{
		double activoTotal=(obtenerGanancias()+activoNoCorriente);
		if(mesasRestaurante != null && empleadosRestaurante != null && menuRestaurante != null) {
			double ROA = 0.0; //RentabilidadEconomica
			double NI = 0; //IngresoNeto
			int costesDelNegocio = 0;
			costesDelNegocio = (empleadosRestaurante.calcularTotalSueldos() + menuRestaurante.calcularTotalPrecioProductos()/2);
			//IngresoNeto(NI) = Ganancias - CostesDelNegocio;
			NI = (int) (obtenerGanancias() - costesDelNegocio);
			//RentabilidadEconomica(ROA) = IngresoNeto(NI) / ActivoTotal(TA);
			if(activoTotal>0)
			{
				ROA = NI / activoTotal;
				return "La rentabilidad es de un: "+(ROA*(double)100);
			}
			else
			{
				return "El activo total es 0";
			}
		}
		return "No es posible determinar la rentabilidad";
	}
    
	public String finalizarElDia() throws IOException
	{
		double activoCorriente=obtenerGanancias();
		String datos;
		
		datos="Las ganancias actuales fueron: $"+String.format("%.0f",activoCorriente)+" pesos.\n";
		
		double costeNegocio=(empleadosRestaurante.calcularTotalSueldos() + menuRestaurante.calcularTotalPrecioProductos()/2);
		
		datos+="Mientras que el coste de negocio fue de un equivalente a: $"+String.format("%.0f", costeNegocio)+" pesos.\n";
		
		activoNoCorriente=activoNoCorriente+activoCorriente-costeNegocio;
		
		datos+="Por lo tanto el activo no corriente total se establece con un monto de: $"+String.format("%.0f", activoNoCorriente)+" pesos.\n";
				
		arc.actualizarActivoNoCorriente(this);
		
		return datos;
		
	}
    
	public void modificarRestaurante(String nombre, String direccion) throws IOException
	{
		this.nombre=nombre;
		this.direccion=direccion;
		arc.actualizarRestaurante(this);
	}

	public void escribirTxTCompletoMesas() throws IOException {
		mesasRestaurante.escribirTxTCompletoMesas();
		
	}

	public void escribirTxTCompletoPedidos() throws IOException {
		mesasRestaurante.escribirTxTCompletoPedidos();
		
	}

	public void putCodigoProducto(String codProducto, Producto producto) {
		menuRestaurante.putCodigoProducto(codProducto, producto);
	}

	public void escribirTxTCompletoGarzones() throws IOException {
		empleadosRestaurante.escribirTxTCompletoGarzones();
		
	}

	public void escribirTxTCompletoCajeros() throws IOException {
		empleadosRestaurante.escribirTxTCompletoCajeros();
	}

	public void escribirTxTCompletoCocineros() throws IOException {
		empleadosRestaurante.escribirTxTCompletoCocineros();
	}

	public void escribirTxTCompletoJefe() throws IOException {
		empleadosRestaurante.escribirTxTCompletoJefe();
	}

	public void escribirTxTCompletoProductos() throws IOException {
		menuRestaurante.escribirTxTCompletoProductos();
	}
    
    
    
    
    
    
    
    
}