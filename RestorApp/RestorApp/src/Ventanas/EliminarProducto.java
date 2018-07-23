package Ventanas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clasesRestorApp.Restaurante;

public class EliminarProducto extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton botonAtras;
	private Restaurante restaurante;
	
	public EliminarProducto(Restaurante restaurante){
		super();
		this.restaurante = restaurante;
		configurarVentana();
		this.setLocationRelativeTo(null);
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
		JLabel titulo = new JLabel("Seleccione la operaci\u00F3n a realizar sobre los productos");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		titulo.setBounds(60, 23, 323, 14);
		contentPane.add(titulo);
		
		final JButton botonEliminarProductoMenu = new JButton("Eliminar del menu");
		botonEliminarProductoMenu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botonEliminarProductoMenu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(e.getSource() == botonEliminarProductoMenu ){
					dispose();
					EliminarProductoMenu ventana = new EliminarProductoMenu(restaurante);
					ventana.setVisible(true);
				}
			}
		});
		botonEliminarProductoMenu.setBounds(131, 83, 168, 36);
		contentPane.add(botonEliminarProductoMenu);
		
		final JButton botonEliminarProductoPedido = new JButton("Eliminar del pedido");
		botonEliminarProductoPedido.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botonEliminarProductoPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == botonEliminarProductoPedido ){
					dispose();
					EliminarProductoPedido ventana = new EliminarProductoPedido(restaurante);
					ventana.setVisible(true);
				}
			}
		});
		botonEliminarProductoPedido.setBounds(131, 145, 168, 36);
		contentPane.add(botonEliminarProductoPedido);
		
		botonAtras = new JButton("Atras");
		botonAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				VentanaAdmin ventana = new VentanaAdmin(restaurante);
				ventana.setVisible(true);
				dispose();
			}
		});
		botonAtras.setBounds(10, 227, 89, 23);
		contentPane.add(botonAtras);
	}
	
}