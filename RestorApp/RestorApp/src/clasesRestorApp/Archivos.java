package clasesRestorApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer; //Para sacar datos de Empleado separados por ","

import javax.swing.JOptionPane;

public class Archivos {
	
	private Secundaria sec;	
	public Archivos()
	{
		sec=new Secundaria();
	}
	
	public Archivos(Restaurante rest)
	{
		sec=new Secundaria();
	}
	
	public void cargarDatos(Restaurante rest) throws IOException
	{
		cargarRestaurante(rest);
		cargarMesas(rest);
		cargarProductos(rest);
		cargarPedidos(rest);
		cargarGarzones(rest);
		cargarCajeros(rest);
		cargarCocineros(rest);
		cargarJefe(rest);
	}
	
	public void cargarMesas(Restaurante rest)
	{
		File f2 = new File("Mesas.txt"); // indica el archivo a manipular
		Scanner s2; // clase para leer los archivos
		try {
			s2 = new Scanner(f2); //se instancia sobre que elemento se operara
			while (s2.hasNextLine() == true)// Verifica si tiene otra linea
			{
				String linea = s2.nextLine().trim(); // A la linea se le eliminan los espacios
				rest.agregarMesa(Integer.parseInt(linea)); // se va agregando  a la lista mesas cada una de las mesas instanciadas con sus datos
			}
			s2.close(); // se cierra el archivo
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo inicial de mesas. Quizas no existe.");
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Revise los datos del archivo inicial de mesas");
			e1.printStackTrace();
		}
	}
	public void cargarPedidos(Restaurante rest)
	{
		File f3 = new File("Pedidos.txt");
		Scanner s3;
		int codPedido = 0;
		String[] lineaSeparada = null;
		String lineaProductos = "";
		String[] idProductosSeparados = null;
		try {
			s3 = new Scanner(f3);
			while (s3.hasNextLine() == true) {
				String linea = s3.nextLine().trim(); //linea = "1:1/1,4,7,12" ---> codMesa,codPedido,pro1,pro2,pro3,pro4
				lineaSeparada = linea.split(":"); // separo la linea = "1:1/1,4,7,12" por el ","
				if(lineaSeparada[0].trim().equals("")==false)
				{
					int codMesa = Integer.parseInt(lineaSeparada[0].trim()); //codMesa:1
					Mesa m = rest.obtenerMesa(codMesa);
					codPedido = Integer.parseInt(lineaSeparada[1].substring(0, 1).trim()); // codPedido:1
					if(sec.contarCaracter(linea,"/")>=1)
					{
						lineaProductos = lineaSeparada[1].substring(2); //lineaProductos = 1,4,7,12
						idProductosSeparados = lineaProductos.split(",");
						for (int i = 0; i < idProductosSeparados.length && idProductosSeparados[i] != null; i++) { // se va recorriendo el arreglo 
							Producto producto = rest.obtenerProductoEspecifico(idProductosSeparados[i]); // producto sera a una referencia  del objeto producto del menu
							if (producto != null && m!=null) {
								m.agregarPedido(codPedido, producto);
								m.setEstadoMesa("Ocupada");
							}
						}
					}
					else
					{
						m.agregarPedido(codPedido);
					}
				}
			}
			s3.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo inicial de pedidos. Quizas no existe.");
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Revise los datos del archivo inicial de pedidos");
			e1.printStackTrace();
		}
	}
	public double cargarActivoNoCorriente(Restaurante rest)
	{
		File fr = new File("ActivoNoCorriente.txt");
		Scanner sr;
		double activo=0;
		try {
			sr=new Scanner(fr);
			while(sr.hasNextLine()==true)
			{
				String linea =sr.nextLine();
				StringTokenizer datos =new StringTokenizer(linea,",");
				rest.setActivoNoCorriente(Double.parseDouble(datos.nextToken()));
			}
			sr.close();
		}catch(FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error al cargar el activo no corriente del restaurante\nEL TXT DE RESTAURANTE ESTA SUPER BUGUEADOOOOOOOOOOOOOOOOOOOOO.");
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Revise datos de Restaurante");
			e1.printStackTrace();
		}
		return activo;
	}
	
	public void cargarRestaurante(Restaurante rest) throws IOException
	{
		File f1 = new File("Restaurante.txt");
		if (f1.exists()==true) { 
			Scanner s1;
			try {
				s1=new Scanner(f1);
				while(s1.hasNextLine()==true)
				{
					String linea =s1.nextLine();
					StringTokenizer datos =new StringTokenizer(linea,",");
					rest.setDireccion(datos.nextToken());
					rest.setNombre(datos.nextToken());
				}
				s1.close();
				cargarActivoNoCorriente(rest);
			}catch(FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "Error al abrir el archivo inicial de Restaurante. Quizas no existe.");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Revise datos de Restaurante");
				e1.printStackTrace();
			}
		}else
		{
			escribirTxTRestaurante(rest);
		}
	}
	
	public void cargarCajeros(Restaurante rest)
	{
		File f1 = new File("Cajeros.txt"); 
		Scanner s1; 
		try {
			s1 = new Scanner(f1);
			while (s1.hasNextLine() == true)
			{
				String linea = s1.nextLine();
				
				StringTokenizer datos = new StringTokenizer(linea,",");
				
				String rut = datos.nextToken().trim();
				String nombre = datos.nextToken().trim();
				String sueldo = datos.nextToken().trim();
				String edad = datos.nextToken().trim(); 
				String total = datos.nextToken().trim();
				String diferencia = datos.nextToken().trim();
				
				int suel=Integer.parseInt(sueldo);
				int age=Integer.parseInt(edad);
				int tot=Integer.parseInt(total);
				int dif=Integer.parseInt(diferencia);
				
				rest.agregarCajero(rut,nombre,suel,age,tot,dif);	
			}
			s1.close(); 
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo inicial de Cajeros. Quizas no existe.");
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Revise datos de cajeros");
			e1.printStackTrace();
		}
	}
	public void cargarCocineros(Restaurante rest)
	{
		File f2 = new File("Cocineros.txt"); 
		Scanner s2; 
		try {
			s2 = new Scanner(f2);
			while (s2.hasNextLine() == true)
			{
				String linea = s2.nextLine(); 
				
				StringTokenizer datos = new StringTokenizer(linea,",");
				
				String rut = datos.nextToken().trim();
				String nombre = datos.nextToken().trim();
				String sueldo = datos.nextToken().trim();
				String edad = datos.nextToken().trim(); 
				
				int suel=Integer.parseInt(sueldo);
				int age=Integer.parseInt(edad);
				
				
				rest.agregarCocinero(rut,nombre,suel,age);
				

			}
			s2.close(); // se cierra el archivo
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo inicial de Cocineros. Quizas no existe.");
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Revise datos cocineros");
			e1.printStackTrace();
		}
	}
	public void cargarGarzones(Restaurante rest)
	{
		File f3 = new File("Garzones.txt"); 
		Scanner s3; 
		try {
			s3 = new Scanner(f3);
			while (s3.hasNextLine() == true)
			{
				String linea = s3.nextLine().trim(); 
				
				StringTokenizer datos = new StringTokenizer(linea,",");
				
				String rut = datos.nextToken().trim();
				String nombre = datos.nextToken().trim();
				String sueldo = datos.nextToken().trim();
				String edad = datos.nextToken().trim(); 
				String nivelIngles = datos.nextToken().trim();
				String mesasAtendidas = datos.nextToken().trim();
				
				int suel=Integer.parseInt(sueldo);
				int age=Integer.parseInt(edad);
				int mesas=Integer.parseInt(mesasAtendidas);				
				
				rest.agregarGarzon(rut,nombre,suel,age,nivelIngles,mesas);
			}
			s3.close(); // se cierra el archivo
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo inicial de Garzones. Quizas no existe.");
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Revise datos Garzones");
			e1.printStackTrace();
		}
	}
	
	public void cargarJefe(Restaurante rest)
	{
		File f4 = new File("Jefe.txt"); 
		Scanner s4; 
		try {
				s4 = new Scanner(f4);
					String linea = s4.nextLine().trim(); 
					
					StringTokenizer datos = new StringTokenizer(linea,",");
					
					String rut = datos.nextToken().trim();
					String nombre = datos.nextToken().trim();
					String edad = datos.nextToken().trim();
					String sueldo = datos.nextToken().trim();
					
					int suel=Integer.parseInt(sueldo);
					int age=Integer.parseInt(edad);
					
					rest.agregarJefe(rut,nombre,age,suel);
				s4.close(); // se cierra el archivo
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo inicial de Jefe. Quizas no existe.");
			return;
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Revise datos Jefe");
			e1.printStackTrace();
		}
		
		
	}

	
	public void cargarProductos(Restaurante rest)
	{
		File f3 = new File("Productos.txt");
		Scanner s3;
		try {
			s3 = new Scanner(f3);
			while (s3.hasNextLine() == true) {// Verifica si tiene otra linea siguiente
				String linea = s3.nextLine();
				String[] separacion = linea.split(",");
				String codProducto = separacion[0].trim();
				String nombre = separacion[1].trim();
				int cantidadDisponibleDelProducto = Integer.parseInt(separacion[2].trim());
				int precioProducto = Integer.parseInt(separacion[3].trim());
				Producto producto = new Producto(codProducto, nombre,precioProducto, cantidadDisponibleDelProducto);
				rest.putCodigoProducto(codProducto, producto);
			}
			s3.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo inicial de productos. Quizas no existe.");
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Revise los datos del archivo inicial de productos");
			e1.printStackTrace();
		}
	}
	
	public void actualizarEmpleados(Restaurante rest) throws IOException
	{
		actualizarGarzones(rest);
		actualizarCajeros(rest);
		actualizarCocineros(rest);
	}
	
	public void actualizarDatos(Restaurante rest) throws IOException
	{
		actualizarRestaurante(rest);
		actualizarActivoNoCorriente(rest);
		actualizarGarzones(rest);
		actualizarCajeros(rest);
		actualizarCocineros(rest);
		actualizarJefe(rest);
		actualizarMesas(rest);
		actualizarPedidos(rest);
		actualizarProductos(rest);
		
	}
	
	public void actualizarActivoNoCorriente(Restaurante rest) throws IOException
	{
		if(eliminarArchivoTxT("ActivoNoCorriente")==true)
		{
			escribirTxTActivoNoCorriente(rest);
			cargarActivoNoCorriente(rest);
		}
	}
	
	public void actualizarRestaurante(Restaurante rest) throws IOException
	{
		if(eliminarArchivoTxT("Restaurante")==true)
		{
			escribirTxTRestaurante(rest);
			cargarRestaurante(rest);
		}else {
			JOptionPane.showMessageDialog(null,"No puedo eliminar el archivo Restaurante aun xd");
		}
	}
	
	public void actualizarGarzones(Restaurante rest) throws IOException
	{
		if(eliminarArchivoTxT("Garzones")==true)
		{
			rest.escribirTxTCompletoGarzones();
			cargarGarzones(rest);
		}
	}

	public void actualizarCajeros(Restaurante rest) throws IOException
	{
		//if(eliminarArchivoTxT("Cajeros")==true)
		{
			rest.escribirTxTCompletoCajeros();
			cargarCajeros(rest);
		}
	}
	public void actualizarCocineros(Restaurante rest) throws IOException
	{
		if(eliminarArchivoTxT("Cocineros")==true)
		{
			rest.escribirTxTCompletoCocineros();
			cargarCocineros(rest);
		}
	}
	public void actualizarJefe(Restaurante rest) throws IOException
	{
		//if(eliminarArchivoTxT("Jefe")==true)
		//{
			rest.escribirTxTCompletoJefe();
			cargarJefe(rest);
		//}
	}
	public void actualizarMesas(Restaurante rest) throws IOException
	{
		if(eliminarArchivoTxT("Mesas")==true)
		{
			rest.escribirTxTCompletoMesas();
			cargarMesas(rest);
		}
	}
	public void actualizarProductos(Restaurante rest) throws IOException
	{
		if(eliminarArchivoTxT("Productos")==true)
		{
			rest.escribirTxTCompletoProductos();
			cargarProductos(rest);
		}
	}
	public void actualizarPedidos(Restaurante rest) throws IOException
	{
		if(eliminarArchivoTxT("Pedidos")==true)
		{
			rest.escribirTxTCompletoPedidos();
			cargarPedidos(rest);
		}
	}
	
	public boolean eliminarArchivoTxT(String nombreArchivo)
	{
		try{
            File archivo = new File(nombreArchivo+".txt");
            boolean estatus = archivo.delete();
            return estatus;
        }catch(Exception e)
        {
			JOptionPane.showMessageDialog(null, "El archivo no existe");
            return false;
        }
	}
	
	public void escribirTxTMesas(String i)throws IOException{
		File file = new File("Mesas.txt");// prepara el archivo para ser manipulado
		FileWriter escribir; // escribir en el fichero
		PrintWriter linea; // permite escribir en el ficher de la misma forma que por pantalla 
		
		try{
			
			escribir = new FileWriter(file,true);
			linea = new PrintWriter(escribir);
			linea.println(i);
			escribir.close();
			
			
		}catch(FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo inicial de pedidos. Quizas no existe.");
		}
	}
	public void escribirTxTGarzones(String rut, String nombre, int sueldo, int edad, String nivelDeIngles,int mesasAtendidas) throws IOException{
		File file = new File("Garzones.txt");// prepara el archivo para ser manipulado
		FileWriter escribir; // escribir en el fichero
		PrintWriter linea; // permite escribir en el ficher de la misma forma que por pantalla 
		
		try{
			
			escribir = new FileWriter(file,true);
			linea = new PrintWriter(escribir);
			String texto=rut + "," + nombre +","+ sueldo +"," + edad + "," + nivelDeIngles + "," + mesasAtendidas;
			linea.println(texto);
			escribir.close();
			
			
		}catch(FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo inicial de Garzones. Quizas no existe.");
		}
	}
	public void escribirTxTActivoNoCorriente(Restaurante rest) throws IOException
	{
		File file = new File("ActivoNoCorriente.txt");
		FileWriter escribir;
		PrintWriter linea;
		try {
			escribir=new FileWriter(file,true);
			linea = new PrintWriter(escribir);
			String texto=Double.toString(rest.getActivoNoCorriente());
			linea.println(texto);
			escribir.close();
		}catch(FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo inicial del Activo no corriente. Quizas no existe.");
		}
	}
	
	public void escribirTxTRestaurante(Restaurante rest) throws IOException
	{
		File file = new File("Restaurante.txt");
		FileWriter escribir;
		PrintWriter linea;
		try {			
			escribir=new FileWriter(file,true);
			linea = new PrintWriter(escribir);
			String texto=rest.getDireccion()+","+rest.getNombre();
			linea.println(texto);
			escribir.close();
			escribirTxTActivoNoCorriente(rest);
		}catch(FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo inicial del Restaurante. Quizas no existe.");
		}
	}
	
	public void escribirTxTCocineros(String rut, String nombre, int sueldo, int edad) throws IOException
	{
		File file = new File("Cocineros.txt");// prepara el archivo para ser manipulado
		FileWriter escribir; // escribir en el fichero
		PrintWriter linea; // permite escribir en el ficher de la misma forma que por pantalla 
		try{
			escribir = new FileWriter(file,true);
			linea = new PrintWriter(escribir);
			String texto=rut + "," + nombre +","+ sueldo +"," + edad;
			linea.println(texto);
			escribir.close();
			
			
		}catch(FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo inicial de Cocineros. Quizas no existe.");
		}
	}
	public void escribirTxTJefe(String rut, String nombre, int edad, int sueldo, int cantE) throws IOException
	{
		File file = new File("Jefe.txt");// prepara el archivo para ser manipulado
		FileWriter escribir; // escribir en el fichero
		PrintWriter linea; // permite escribir en el ficher de la misma forma que por pantalla 
		
		try{
			
			escribir = new FileWriter(file,true);
			linea = new PrintWriter(escribir);
			String texto=rut + "," + nombre +","+ edad +"," + sueldo + "," + cantE;
			linea.println(texto);
			escribir.close();
			
			
		}catch(FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo inicial del Jefe. Quizas no existe.");
		}
	}
	
	public void escribirTxTCajeros(String rut, String nombre, int sueldo, int edad,int total,int diferencia) throws IOException{
		File file = new File("Cajeros.txt");// prepara el archivo para ser manipulado
		FileWriter escribir; // escribir en el fichero
		PrintWriter linea; // permite escribir en el ficher de la misma forma que por pantalla 
		
		try{
			
			escribir = new FileWriter(file,true);
			linea = new PrintWriter(escribir);
			String texto=rut + "," + nombre +","+ sueldo +"," + edad + "," + total + "," + diferencia;
			linea.println(texto);
			escribir.close();
			
			
		}catch(FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo inicial de Cajeros. Quizas no existe.");
		}
	}
	
	public void escribirTxTProductos(String codigo,String nombre,String precio,String cantidad) throws IOException{
		File file = new File("Productos.txt");// prepara el archivo para ser manipulado
		FileWriter escribir; // escribir en el fichero
		PrintWriter linea; // permite escribir en el ficher de la misma forma que por pantalla 
		
		try{
			
			escribir = new FileWriter(file,true);
			linea = new PrintWriter(escribir);
			String texto=codigo + "," + nombre + "," + cantidad + "," + precio;
			linea.println(texto);
			escribir.close();
			
			
		}catch(FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo inicial de pedidos. Quizas no existe.");
		}
	}
	
	
	
	/*public void escribirSoloPEdidoTxT(String codigoMesa,String codigoPedido) throws IOException{
		File file = new File("Pedidos.txt");// prepara el archivo para ser manipulado
		try{
			FileWriter escribir = new FileWriter(file,true); // escribir en el fichero
			PrintWriter linea = new PrintWriter(escribir); // permite escribir en el ficher de la misma forma que por pantalla 
			String texto=codigoMesa + ":" + codigoPedido;
			linea.println(texto);
			escribir.close();
			
			
		}catch(FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo inicial de pedidos. Quizas no existe.");
		}
	}*/
	
	public void escribirSoloPedidoTxT(String codigoMesa,String codigoPedido) throws IOException{
		File file = new File("Pedidos.txt");// prepara el archivo para ser manipulado		
		try{
			FileWriter escribir = new FileWriter(file,true); // escribir en el fichero
			PrintWriter linea = new PrintWriter(escribir); // permite escribir en el ficher de la misma forma que por pantalla 
			String texto=codigoMesa + ":" + codigoPedido;
			linea.print(texto);
			escribir.close();
			
			
		}catch(FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo inicial de pedidos. Quizas no existe.");
		}
	}

	public void escribirTxTCodigoProducto(String codsPro) throws IOException {
		File file = new File("Pedidos.txt");
		try
		{
			FileWriter escribir = new FileWriter(file,true);
			PrintWriter linea =new PrintWriter(escribir);
			linea.println(codsPro);
			escribir.close();
		}
		catch(FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo inicial de pedidos. Quizas no existe.");
		}
		
	}
	
	public void crearArchivoReporteMesas(String codigoMesa,String cantidadPedidos,String estadoMesa ) throws IOException{
		File file = new File("ReporteMesas");// prepara el archivo para ser manipulado
		FileWriter escribir; // escribir en el fichero
		PrintWriter linea; // permite escribir en el ficher de la misma forma que por pantalla
		
		try{
			if(file.exists()==true){
				escribir = new FileWriter(file,true);
				linea = new PrintWriter(escribir);
				linea.println("CodeMesa" + ":" + codigoMesa);
				linea.println("CantidadDePedidos" + ":" + cantidadPedidos);
				linea.println("EstadoMesa" + ":" + estadoMesa);
				linea.println();
				
				escribir.close();
				
			}
			else{
				escribir = new FileWriter("ReporteMesas"); 
				
				linea = new PrintWriter(escribir);
				linea.println("EodeMesa" + ":" + codigoMesa);
				linea.println("CantidadDePedidos" + ":" + cantidadPedidos);
				linea.println("EstadoMesa" + ":" + estadoMesa);
				linea.println();
				
				escribir.close();
			}
			
			
			
		}catch(FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo de reporte");
		}
		
	}
	public void crearArchivoReportePedidos(String codMesa,String codPedido,String precioTotal) throws IOException{
		File file = new File("ReportePedidos");// prepara el archivo para ser manipulado
		FileWriter escribir; // escribir en el fichero
		PrintWriter linea; // permite escribir en el ficher de la misma forma que por pantalla
		
		try{
			if(file.exists()==true){
				escribir = new FileWriter(file,true);
				linea = new PrintWriter(escribir);
				
				linea.println("Numero de mesa" + ":" + codMesa);
				linea.println("Numero de pedido" + ":" + codPedido);
				linea.println("precio total Del pedido" + ":" + precioTotal);
				linea.println();
				
				escribir.close();
				
			}
			else{
				escribir = new FileWriter("ReportePedidos"); 
				
				linea = new PrintWriter(escribir);
				linea = new PrintWriter(escribir);  
				linea.println("Numero de mesa" + ":" + codMesa);
				linea.println("Numero de pedido" + ":" + codPedido);
				linea.println("precio total Del pedido" + ":" + precioTotal);
				linea.println();
				
				escribir.close();
			}
			
			
			
		}catch(FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo de reporte");
		}
	}
		public void crearArchivoReporteEmpleados(String datos) throws IOException{
			File file = new File("ReporteEmpleados");// prepara el archivo para ser manipulado
			FileWriter escribir; // escribir en el fichero
			PrintWriter linea; // permite escribir en el ficher de la misma forma que por pantalla
			
			try{
				if(file.exists()==true){
					escribir = new FileWriter(file,true);
					linea = new PrintWriter(escribir);
					
					linea.println(datos);
					linea.println();
					
					escribir.close();
					
				}
				else{
					escribir = new FileWriter("ReporteEmpleados"); 
					
					linea = new PrintWriter(escribir);
					linea = new PrintWriter(escribir);
					linea.println(datos);
					linea.println();
					
					escribir.close();
				}
				
				
				
			}catch(FileNotFoundException e){
				JOptionPane.showMessageDialog(null, "Error al abrir el archivo de reporte");
			}
	}
	public void crearArchivoReporteProductos(String codProducto,String nombrePro,String precio,String cantidad) throws IOException{
		File file = new File("ReporteProductos");// prepara el archivo para ser manipulado
		FileWriter escribir; // escribir en el fichero
		PrintWriter linea; // permite escribir en el ficher de la misma forma que por pantalla
		
		try{
			if(file.exists()==true){
				escribir = new FileWriter(file,true);
				linea = new PrintWriter(escribir);
				
				linea.println("Codigo del producto " + ":" + codProducto);
				linea.println("Nombre" + ":" + nombrePro);
				linea.println("precio " + ":" + precio);
				linea.println("Cantidad Disponible " + ":" + cantidad);
				linea.println();
				
				escribir.close();
				
			}
			else{
				escribir = new FileWriter("ReporteProductos"); 
				
				linea = new PrintWriter(escribir);
				linea = new PrintWriter(escribir);
				
				linea.println("Codigo del producto " + ":" + codProducto);
				linea.println("Nombre" + ":" + nombrePro);
				linea.println("precio " + ":" + precio);
				linea.println("Cantidad Disponible " + ":" + cantidad);
				linea.println();
				
				escribir.close();
			}
			
			
			
		}catch(FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo de reporte");
		}
	}


	
}