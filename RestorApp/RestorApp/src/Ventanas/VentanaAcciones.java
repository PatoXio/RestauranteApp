package Ventanas;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import clasesRestorApp.Restaurante;

import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;

public class VentanaAcciones extends JFrame 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private JButton botonCalculo2;
	private JButton botonAtras;
	private JButton btnMostrarPorcentaje;
	private JLabel lblVentaProductos;
	private JSeparator separator_3;
	private JSeparator separator_4;
	private JButton botonCalculoRentabilidad;
	private JLabel lblRentabilidadNegocio;
	private JButton btnTerminar;
	private JLabel lblFinalizarDa;
	
	public VentanaAcciones(Restaurante restaurante)
	{
		super();
		configurarVentana();
		this.setLocationRelativeTo(null);
		inicializarComponentes();
		this.restaurante = restaurante;
	}

	private void configurarVentana()
	{
		setTitle("RestorApp-Administrador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 300);
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
				if(e.getSource() == botonMostrarE)
				{
					MostrarMesasGarzones ventanaAdminMostrar = new MostrarMesasGarzones(restaurante);
					ventanaAdminMostrar.setVisible(true);
					dispose();
				}
				
			}
		});
		botonMostrarE.setBounds(30, 64, 89, 23);
		contentPane.add(botonMostrarE);
		
		botonMostrarM = new JButton("Calcular");
		botonMostrarM.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(e.getSource() == botonMostrarM)
				{
					JOptionPane.showMessageDialog(null, "El total de sueldos es de: $"+restaurante.calcularTotalSueldos()+ " pesos.");
				}
			}
		});
		botonMostrarM.setBounds(30, 175, 89, 23);
		contentPane.add(botonMostrarM);
		
		botonMostrarPr = new JButton("Mostrar");
		botonMostrarPr.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(e.getSource() == botonMostrarPr)
				{
					JOptionPane.showMessageDialog(null, restaurante.porcentajeDeEmpleados());
				}
			}
		});
		botonMostrarPr.setBounds(175, 175, 89, 23);
		contentPane.add(botonMostrarPr);
		
		botonMostrarP = new JButton("Calcular");
		botonMostrarP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(e.getSource() == botonMostrarP)
				{
					JOptionPane.showMessageDialog(null,"El valor en total de todos los productos es:  "+ restaurante.calcularTotalPrecioProductos());
				}
			}
		});
		botonMostrarP.setBounds(175, 64, 89, 23);
		contentPane.add(botonMostrarP);
		
		separator = new JSeparator();
		separator.setBounds(10, 128, 414, 2);
		contentPane.add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(153, 30, 2, 208);
		contentPane.add(separator_1);
		
		mesas = new JLabel("Total de Sueldos");
		mesas.setFont(new Font("Tahoma", Font.PLAIN, 13));
		mesas.setBounds(20, 150, 99, 14);
		contentPane.add(mesas);
		
		empleados = new JLabel("Mostrar mesas garzon");
		empleados.setFont(new Font("Tahoma", Font.PLAIN, 13));
		empleados.setBounds(10, 30, 133, 23);
		contentPane.add(empleados);
		
		 pedidos = new JLabel("$ Total en productos");
		 pedidos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		 pedidos.setBounds(165, 30, 133, 23);
		contentPane.add(pedidos);
		
		 productos = new JLabel("% Empleados");
		 productos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		 productos.setBounds(175, 141, 89, 23);
		contentPane.add(productos);
		
		botonAtras = new JButton("Salir");
		botonAtras.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
			}
		});
		botonAtras.setBounds(10, 227, 89, 23);
		contentPane.add(botonAtras);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(296, 30, 2, 208);
		contentPane.add(separator_2);
		
		botonCalculo2 = new JButton("Calcular");
		botonCalculo2.setBounds(314, 64, 89, 23);
		botonCalculo2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(e.getSource() == botonCalculo2)
				{
					JOptionPane.showMessageDialog(null,"El total de productos disponibles es de: "+ restaurante.calcularCantidadTotalProductos());
				}
			}
		});
		contentPane.add(botonCalculo2);
		
		JLabel label = new JLabel("Cantidad de productos");
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label.setBounds(301, 30, 133, 23);
		contentPane.add(label);
		
		btnMostrarPorcentaje = new JButton("Mostrar");
		btnMostrarPorcentaje.setBounds(314, 175, 89, 23);
		btnMostrarPorcentaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(e.getSource() == btnMostrarPorcentaje)
				{
					JOptionPane.showMessageDialog(null, restaurante.procentajeDeCadaProductoEnElVaLorToTal());
				}
			}
		});
		contentPane.add(btnMostrarPorcentaje);
		
		lblVentaProductos = new JLabel("% Venta Productos");
		lblVentaProductos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblVentaProductos.setBounds(307, 141, 117, 23);
		contentPane.add(lblVentaProductos);
		
		separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(434, 30, 2, 208);
		contentPane.add(separator_3);
		
		separator_4 = new JSeparator();
		separator_4.setBounds(110, 128, 444, 2);
		contentPane.add(separator_4);
		
		botonCalculoRentabilidad = new JButton("Calcular");
		botonCalculoRentabilidad.setBounds(455, 64, 89, 23);
		botonCalculoRentabilidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(e.getSource() == botonCalculoRentabilidad)
				{
					JOptionPane.showMessageDialog(null, restaurante.calcularRentabilidadDelNegocio());
				}
			}
		});
		contentPane.add(botonCalculoRentabilidad);
		
		lblRentabilidadNegocio = new JLabel("Rentabilidad Negocio");
		lblRentabilidadNegocio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRentabilidadNegocio.setBounds(441, 30, 120, 23);
		contentPane.add(lblRentabilidadNegocio);
		
		btnTerminar = new JButton("Terminar");
		btnTerminar.setBounds(455, 175, 89, 23);
		btnTerminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(e.getSource() == btnTerminar)
				{
					try {
						JOptionPane.showMessageDialog(null, restaurante.finalizarElDia());
					} catch (HeadlessException | IOException e1) {
						JOptionPane.showMessageDialog(null, "Ha surgido un problema, y no se logró finalizar el día");
						e1.printStackTrace();
					}
				}
			}
		});
		contentPane.add(btnTerminar);
		
		lblFinalizarDa = new JLabel("Finalizar D\u00EDa");
		lblFinalizarDa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFinalizarDa.setBounds(465, 146, 89, 23);
		contentPane.add(lblFinalizarDa);
	}
}
