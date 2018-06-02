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
	
	public void cargarDatos(ListaMesas mesasRestaurante,MapaMenu menuRestaurante,TablaEmpleados empleadosRestaurante)
	{
		cargarMesas(mesasRestaurante);
		cargarProductos(menuRestaurante);
		cargarPedidos(menuRestaurante,mesasRestaurante);
		cargarGarzones(empleadosRestaurante);
		cargarCajeros(empleadosRestaurante);
		cargarCocineros(empleadosRestaurante);
		cargarJefe(empleadosRestaurante);
	}
	
	public void cargarMesas(ListaMesas mesasRestaurante)
	{
		File f2 = new File("Mesas.txt"); // indica el archivo a manipular
		Scanner s2; // clase para leer los archivos
		try {
			s2 = new Scanner(f2); //se instancia sobre que elemento se operara
			while (s2.hasNextLine() == true)// Verifica si tiene otra linea
			{
				String linea = s2.nextLine().trim(); // A la linea se le eliminan los espacios
				Mesa mesa = new Mesa(Integer.parseInt(linea)); // declaracion e instanciacion del objeto mesa
				mesasRestaurante.agregarMesa(mesa); // se va agregando  a la lista mesas cada una de las mesas instanciadas con sus datos
			}
			s2.close(); // se cierra el archivo
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo inicial de mesas");
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Revise los datos del archivo inicial de mesas");
			e1.printStackTrace();
		}
	}
	public void cargarPedidos(MapaMenu menuRestaurante, ListaMesas mesasRestaurante)
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
				int codMesa = Integer.parseInt(lineaSeparada[0].trim()); //codMesa:1
				Mesa m = mesasRestaurante.obtenerMesa(codMesa);
				codPedido = Integer.parseInt(lineaSeparada[1].substring(0, 1).trim()); // codPedido:1
				if(sec.contarCaracter(linea,"/")>=1)
				{
					lineaProductos = lineaSeparada[1].substring(2); //lineaProductos = 1,4,7,12
					idProductosSeparados = lineaProductos.split(",");
					for (int i = 0; i < idProductosSeparados.length && idProductosSeparados[i] != null; i++) { // se va recorriendo el arreglo 
						Producto producto = menuRestaurante.obtenerProductoEspecifico(idProductosSeparados[i].trim()); // producto sera a una referencia  del objeto producto del menu
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
			s3.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo inicial de pedidos");
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Revise los datos del archivo inicial de pedidos");
			e1.printStackTrace();
		}
	}
	
	public void cargarCajeros(TablaEmpleados empleados)
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
				
				int suel=Integer.parseInt(sueldo);
				int age=Integer.parseInt(edad);
				
				
				Cajero caj=new Cajero(rut,nombre,suel,age);
				
				empleados.agregarCajero(caj);	
			}
			s1.close(); 
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error al agregar Cajeros");
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Revise datos de cajeros");
			e1.printStackTrace();
		}
	}
	public void cargarCocineros(TablaEmpleados empleados)
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
				
				Cocinero coc=new Cocinero(rut,nombre,suel,age);
				
				empleados.agregarCocinero(coc);
				

			}
			s2.close(); // se cierra el archivo
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error al agregar Cocineros");
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Revise datos cocineros");
			e1.printStackTrace();
		}
	}
	public void cargarGarzones(TablaEmpleados empleados)
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
				
				Garzon gar=new Garzon(rut,nombre,suel,age,nivelIngles,mesas);
				
				
				empleados.agregarGarzon(gar);
			}
			s3.close(); // se cierra el archivo
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error al agregar Garzones");
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Revise datos Garzones");
			e1.printStackTrace();
		}
	}
	
	public void cargarJefe(TablaEmpleados empleados)
	{
		File f4 = new File("Jefe.txt"); 
		Scanner s4; 
		try {
			s4 = new Scanner(f4);
			while (s4.hasNextLine() == true)
			{
				String linea = s4.nextLine().trim(); 
				
				StringTokenizer datos = new StringTokenizer(linea,",");
				
				String rut = datos.nextToken().trim();
				String nombre = datos.nextToken().trim();
				String edad = datos.nextToken().trim();
				String sueldo = datos.nextToken().trim(); 
				
				int suel=Integer.parseInt(sueldo);
				int age=Integer.parseInt(edad);
				
				JefeRestaurante jefe=new JefeRestaurante(rut,nombre,age,suel);
				
				
				empleados.agregarJefe(jefe);
			}
			s4.close(); // se cierra el archivo
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error al agregar al Jefe");
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Revise datos Jefe");
			e1.printStackTrace();
		}
		
		
	}

	
	public void cargarProductos(MapaMenu menuRestaurante)
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
				Producto producto = new Producto(codProducto, nombre,precioProducto , cantidadDisponibleDelProducto);
				menuRestaurante.putCodigoProducto(codProducto, producto);
			}
			s3.close();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo inicial de productos");
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Revise los datos del archivo inicial de productos");
			e1.printStackTrace();
		}
	}
	public void actualizarEmpleados(TablaEmpleados empleados) throws IOException
	{
		actualizarGarzones(empleados);
		actualizarCajeros(empleados);
		actualizarCocineros(empleados);
		actualizarJefe(empleados);
	}
	
	public void actualizarGarzones(TablaEmpleados empleados) throws IOException
	{
		if(eliminarArchivoTxT("Garzones")==true)
		{
			empleados.escribirTxTCompletoGarzones();
			cargarGarzones(empleados);
		}
	}

	public void actualizarCajeros(TablaEmpleados empleados) throws IOException
	{
		if(eliminarArchivoTxT("Cajeros")==true)
		{
			empleados.escribirTxTCompletoCajeros();
			cargarCajeros(empleados);
		}
	}
	public void actualizarCocineros(TablaEmpleados empleados) throws IOException
	{
		if(eliminarArchivoTxT("Cocineros")==true)
		{
			empleados.escribirTxTCompletoCocineros();
			cargarCocineros(empleados);
		}
	}
	public void actualizarJefe(TablaEmpleados empleados) throws IOException
	{
		if(eliminarArchivoTxT("Jefe")==true)
		{
			empleados.escribirTxTCompletoJefe();
			cargarJefe(empleados);
		}
	}
	public void actualizarMesas(ListaMesas mesasRestaurante) throws IOException
	{
		if(eliminarArchivoTxT("Mesas")==true)
		{
			mesasRestaurante.escribirTxTCompletoMesas();
			cargarMesas(mesasRestaurante);
		}
	}
	public void actualizarProductos(MapaMenu menuRestaurante) throws IOException
	{
		if(eliminarArchivoTxT("Productos")==true)
		{
			menuRestaurante.escribirTxTCompletoProductos();
			cargarProductos(menuRestaurante);
		}
	}
	public void actualizarPedidos(ListaMesas mesasRestaurante,MapaMenu menuRestaurante) throws IOException
	{
		if(eliminarArchivoTxT("Pedidos")==true)
		{
			mesasRestaurante.escribirTxTCompletoPedidos();
			cargarPedidos(menuRestaurante,mesasRestaurante);
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
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo inicial de pedidos");
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
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo inicial de Garzones");
		}
	}
	public void escribirTxTCocineros(String rut, String nombre, int sueldo, int edad) throws IOException{
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
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo inicial de Cocineros");
		}
	}
	public void escribirTxTJefe(String rut, String nombre, int edad, int sueldo) throws IOException
	{
		File file = new File("Jefe.txt");// prepara el archivo para ser manipulado
		FileWriter escribir; // escribir en el fichero
		PrintWriter linea; // permite escribir en el ficher de la misma forma que por pantalla 
		
		try{
			
			escribir = new FileWriter(file,true);
			linea = new PrintWriter(escribir);
			String texto=rut + "," + nombre +","+ edad +"," + sueldo;
			linea.println(texto);
			escribir.close();
			
			
		}catch(FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo inicial del Jefe");
		}
	}
	
	public void escribirTxTCajeros(String rut, String nombre, int sueldo, int edad) throws IOException{
		File file = new File("Cajeros.txt");// prepara el archivo para ser manipulado
		FileWriter escribir; // escribir en el fichero
		PrintWriter linea; // permite escribir en el ficher de la misma forma que por pantalla 
		
		try{
			
			escribir = new FileWriter(file,true);
			linea = new PrintWriter(escribir);
			String texto=rut + "," + nombre +","+ sueldo +"," + edad;
			linea.println(texto);
			escribir.close();
			
			
		}catch(FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo inicial de Cajeros");
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
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo inicial de pedidos");
		}
	}
	
	
	
	public void escribirSoloPEdidoTxT(String codigoMesa,String codigoPedido) throws IOException{
		File file = new File("Pedidos.txt");// prepara el archivo para ser manipulado
		FileWriter escribir; // escribir en el fichero
		PrintWriter linea; // permite escribir en el ficher de la misma forma que por pantalla 
		
		try{
			
			escribir = new FileWriter(file,true);
			linea = new PrintWriter(escribir);
			String texto=codigoMesa + ":" + codigoPedido;
			linea.println(texto);
			escribir.close();
			
			
		}catch(FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo inicial de pedidos");
		}
	}
	public void escribirSoloPedidoTxT(String codigoMesa,String codigoPedido) throws IOException{
		File file = new File("Pedidos.txt");// prepara el archivo para ser manipulado		
		try{
			
			FileWriter escribir = new FileWriter(file,true); // escribir en el fichero
			PrintWriter linea = new PrintWriter(escribir); // permite escribir en el ficher de la misma forma que por pantalla 
			String texto=codigoMesa + ":" + codigoPedido;
			linea.print(texto);
			escribir.close();
			
			
		}catch(FileNotFoundException e){
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo inicial de pedidos");
		}
	}

	public void escribirTxTCodigoProducto(String codPro) throws IOException {
		File file = new File("Pedidos.txt");
		try
		{
			FileWriter escribir = new FileWriter(file,true);
			PrintWriter linea =new PrintWriter(escribir);
			linea.println(codPro);
			escribir.close();
		}
		catch(FileNotFoundException e)
		{
			JOptionPane.showMessageDialog(null, "Error al abrir el archivo inicial de pedidos");
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
