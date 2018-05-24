package Ventanas;

import java.awt.Font;
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

public class EliminarProductoMenu extends JFrame {
	private JPanel contentPane;
	private JTextField textCodigo;
	
	private JLabel titulo;
	private JLabel codigo;
	private JButton botonEliminar;
	private JButton botonEliminarOtro;
	private JButton botonSalir;
	
	private Restaurante restaurante;
	
	public EliminarProductoMenu(Restaurante restaurante){
		super();
		this.restaurante = restaurante;
		configurarVentana();
		inicializarComponentes();
	}
	private void configurarVentana(){
		setTitle("RestorApp - Administrador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	private void inicializarComponentes(){
		JLabel titulo = new JLabel("Ingrese datos del producto a eliminar ");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		titulo.setBounds(118, 26, 233, 20);
		contentPane.add(titulo);
		
		textCodigo = new JTextField();
		textCodigo.setBounds(224, 78, 86, 20);
		contentPane.add(textCodigo);
		textCodigo.setColumns(10);
		
		
		JLabel codigo = new JLabel("Codigo");
		codigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		codigo.setBounds(85, 79, 86, 14);
		contentPane.add(codigo);
		
		
		
		
		final JButton botonRegistrar = new JButton("Eliminar");
		botonRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == botonRegistrar){
					String codigo = textCodigo.getText();
					if(restaurante.eliminarProductoMenu(codigo)==true){
						JOptionPane.showMessageDialog(null, "Se elimino el producto con exito");
					}
					else{
						JOptionPane.showMessageDialog(null, "No se logr� eliminar el producto");
						textCodigo.setText("");	
							
					}	
					
				} 
				
				
			}
		});
		botonRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botonRegistrar.setBounds(146, 201, 119, 27);
		contentPane.add(botonRegistrar);
		
		final JButton botonAgregarOtro = new JButton("eliminar otro ");
		botonAgregarOtro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == botonAgregarOtro){
					textCodigo.setText("");
				}
			}
		});
		botonAgregarOtro.setBounds(308, 227, 116, 23);
		contentPane.add(botonAgregarOtro);
		
		final JButton botonSalir = new JButton("Salir ");
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