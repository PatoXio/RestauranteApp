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

public class AgregarProducto extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel titulo;
	private JButton botonAgregarProductoMenu;
	private JButton botonAgregarProductoPedido;
	private JButton botonAtras;
	private Restaurante restaurante;
	
	public AgregarProducto(Restaurante restaurante){
		super();
		this.restaurante = restaurante;
		configurarVentana();
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
		titulo = new JLabel("Seleccione la operaci\u00F3n a realizar sobre los productos");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		titulo.setBounds(60, 23, 323, 14);
		contentPane.add(titulo);
		
		botonAgregarProductoMenu = new JButton("Agregar al menu");
		botonAgregarProductoMenu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botonAgregarProductoMenu.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(e.getSource() == botonAgregarProductoMenu ){
					dispose();
					AgregarProductoMenu ventana = new AgregarProductoMenu(restaurante);
					ventana.setVisible(true);
				}
			}
		});
		botonAgregarProductoMenu.setBounds(131, 83, 168, 36);
		contentPane.add(botonAgregarProductoMenu);
		
		botonAgregarProductoPedido = new JButton("Agregar al pedido");
		botonAgregarProductoPedido.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botonAgregarProductoPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == botonAgregarProductoPedido ){
					dispose();
					AgregarProductoPedido ventana = new AgregarProductoPedido(restaurante);
					ventana.setVisible(true);
				}
			}
		});
		botonAgregarProductoPedido.setBounds(131, 145, 168, 36);
		contentPane.add(botonAgregarProductoPedido);
		
		botonAtras = new JButton("Atras");
		botonAtras.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				new AdminAgregar(restaurante).setVisible(true);
				dispose();
			}
		});
		botonAtras.setBounds(10, 227, 89, 23);
		contentPane.add(botonAtras);
	}
	
}