package Ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clasesRestorApp.Restaurante;

import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

public class ReporteAdmin extends JFrame 
{

	private JPanel contentPane;
	private Restaurante restaurante;
	private JButton botonMostrarE;
	private JButton botonMostrarM;
	private JButton botonMostrarPr;
	private JButton botonMostrarP;
	private JSeparator separator;
	private JSeparator separator_1;
	private JLabel mesas;
	private JLabel empleados;
	private JLabel pedidos;
	private JLabel productos;
	private JButton botonAtras;
	
	public ReporteAdmin(Restaurante restaurante)
	{
		super();
		configurarVentana();
		inicializarComponentes();
		this.restaurante = restaurante;
	}

	private void configurarVentana()
	{
		setTitle("RestorApp-Administrador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	
	public void inicializarComponentes() 
	{
		
		
		botonMostrarE = new JButton("Mostrar");
		botonMostrarE.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
	    		MostrarEmpleados ventanaAdminMostrar = new MostrarEmpleados(restaurante);
	    		ventanaAdminMostrar.setVisible(true);
			}
		});
		botonMostrarE.setBounds(49, 64, 89, 23);
		contentPane.add(botonMostrarE);
		
		botonMostrarM = new JButton("Mostrar");
		botonMostrarM.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
				try {
					restaurante.mostrarMesasArchivo();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    		MostrarMesas ventanaAdminMostrar = new MostrarMesas(restaurante);
	    		ventanaAdminMostrar.setVisible(true);
			}
		});
		botonMostrarM.setBounds(49, 175, 89, 23);
		contentPane.add(botonMostrarM);
		
		botonMostrarPr = new JButton("Mostrar");
		botonMostrarPr.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
				try {
					restaurante.mostrarProductosArchivo();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    		MostrarProductos ventanaAdminMostrar = new MostrarProductos(restaurante);
	    		ventanaAdminMostrar.setVisible(true);
			}
		});
		botonMostrarPr.setBounds(278, 175, 89, 23);
		contentPane.add(botonMostrarPr);
		
		botonMostrarP = new JButton("Mostrar");
		botonMostrarP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				dispose();
				try {
					restaurante.mostrarPedidosArchivo();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		MostrarPedidos ventanaAdminMostrar = new MostrarPedidos(restaurante);
	    		ventanaAdminMostrar.setVisible(true);
			}
		});
		botonMostrarP.setBounds(278, 64, 89, 23);
		contentPane.add(botonMostrarP);
		
		separator = new JSeparator();
		separator.setBounds(10, 128, 414, 2);
		contentPane.add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(212, 39, 2, 208);
		contentPane.add(separator_1);
		
		mesas = new JLabel("   Mesas");
		mesas.setFont(new Font("Tahoma", Font.PLAIN, 13));
		mesas.setBounds(57, 150, 69, 14);
		contentPane.add(mesas);
		
		empleados = new JLabel("Empleados");
		empleados.setFont(new Font("Tahoma", Font.PLAIN, 13));
		empleados.setBounds(57, 39, 69, 14);
		contentPane.add(empleados);
		
		 pedidos = new JLabel("  Pedidos");
		 pedidos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		 pedidos.setBounds(286, 39, 70, 14);
		contentPane.add(pedidos);
		
		 productos = new JLabel("Productos");
		 productos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		 productos.setBounds(286, 150, 70, 14);
		contentPane.add(productos);
		
		botonAtras = new JButton("Atras");
		botonAtras.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
				new VentanaAdmin(restaurante).setVisible(true);
				
			}
		});
		botonAtras.setBounds(10, 227, 89, 23);
		contentPane.add(botonAtras);
	}


}
