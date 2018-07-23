package Ventanas;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import clasesRestorApp.Archivos;
import clasesRestorApp.Producto;
import clasesRestorApp.Restaurante;
import clasesRestorApp.Secundaria;

public class AgregarProductoMenu extends JFrame
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textCodigo;
	private JTextField textNombre;
	private JTextField textPrecio;
	private JTextField textCantidad;
	private JLabel titulo;
	private JLabel codigo;
	private Secundaria sec;
	private JLabel nombre;
	private JLabel precio;
	private JLabel cantidadDisponible;
	private JButton botonRegistrar;
	//private JButton botonAgregarOtro;
	private JButton botonSalir;
	
	private Restaurante restaurante;
	
	public AgregarProductoMenu(Restaurante restaurante){
		super();
		this.restaurante = restaurante;
		configurarVentana();
		sec=new Secundaria();
		inicializarComponentes();
	}
	private void configurarVentana(){
		setTitle("RestorApp-Administrador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	private void inicializarComponentes(){
		titulo = new JLabel("Ingrese datos del producto ");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		titulo.setBounds(118, 26, 233, 20);
		contentPane.add(titulo);
		
		textCodigo = new JTextField();
		textCodigo.setBounds(224, 78, 86, 20);
		contentPane.add(textCodigo);
		textCodigo.setColumns(10);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(224, 109, 86, 20);
		contentPane.add(textNombre);
		
		textPrecio = new JTextField();
		textPrecio.setColumns(10);
		textPrecio.setBounds(224, 140, 86, 20);
		contentPane.add(textPrecio);
		
		textCantidad = new JTextField();
		textCantidad.setColumns(10);
		textCantidad.setBounds(224, 170, 86, 20);
		contentPane.add(textCantidad);
		
		codigo = new JLabel("Codigo");
		codigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		codigo.setBounds(85, 79, 86, 14);
		contentPane.add(codigo);
		
		nombre = new JLabel("Nombre");
		nombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nombre.setBounds(85, 113, 86, 14);
		contentPane.add(nombre);
		
		precio = new JLabel("Precio");
		precio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		precio.setBounds(85, 145, 46, 14);
		contentPane.add(precio);
		
		cantidadDisponible = new JLabel("Cantidad disponible");
		cantidadDisponible.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cantidadDisponible.setBounds(85, 170, 119, 17);
		contentPane.add(cantidadDisponible);
		
		botonRegistrar = new JButton("Registrar");
		botonRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == botonRegistrar){
					String codigo = textCodigo.getText();
					String nombre = textNombre.getText();
					if(sec.validarNumeros(textPrecio.getText())==true)
					{
						int precio = Integer.parseInt(textPrecio.getText());
						if(sec.validarNumeros(textCantidad.getText())==true)
						{
							int cant = Integer.parseInt(textCantidad.getText());
							Producto producto = new Producto(codigo,nombre,cant,precio);
							try {
								if(restaurante.agregarProductoMenu(producto)==true){
									Archivos a = new Archivos();
									try {
										a.escribirTxTProductos(codigo,nombre,textPrecio.getText(),textCantidad.getText());
									} catch(IOException e1){
										JOptionPane.showMessageDialog(null, "Error al escribir en el archivo");
									}
									JOptionPane.showMessageDialog(null, "Se agrego con exito");
								
								}
								else{
									JOptionPane.showMessageDialog(null, "No se logro agregar el producto");
									textCodigo.setText("");
									textNombre.setText("");
									textPrecio.setText("");
									textCantidad.setText("");
									
									
								}
							} catch (HeadlessException | IOException e1) {
								JOptionPane.showMessageDialog(null, "Error al agregar el producto");
								e1.printStackTrace();
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "La cantidad debe contener solo numeros");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "El precio debe contener solo numeros");
					}
				}
			}
		});
		botonRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botonRegistrar.setBounds(146, 201, 119, 27);
		contentPane.add(botonRegistrar);
		
		final JButton botonAgregarOtro = new JButton("Agregar otro ");
		botonAgregarOtro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == botonAgregarOtro){
					textCodigo.setText("");
					textNombre.setText("");
					textPrecio.setText("");
					textCantidad.setText("");
				}
			}
		});
		botonAgregarOtro.setBounds(308, 227, 116, 23);
		contentPane.add(botonAgregarOtro);
		
		botonSalir = new JButton("Salir ");
		botonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == botonSalir ){
					dispose();
					VentanaAdmin ventana = new VentanaAdmin(restaurante);
					ventana.setVisible(true);
				}
				
			}
		});
		botonSalir.setBounds(10, 227, 116, 23);
		contentPane.add(botonSalir);
		
	}
}
