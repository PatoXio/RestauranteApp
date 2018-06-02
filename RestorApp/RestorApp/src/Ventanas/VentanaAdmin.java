package Ventanas;


import java.awt.Font;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clasesRestorApp.Restaurante;




public class VentanaAdmin extends JFrame  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel titulo;
	private JButton botonAgregar;
	private JButton botonModificar;
	private JButton botonEliminar;
	private JButton botonGenerarReporte;
	private JButton botonSalir;
	private Restaurante restaurante;
	
	
	
	public VentanaAdmin(Restaurante restaurante){
		super();
		configurarVentana();
		inicializarComponentes();
		this.restaurante =restaurante;
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
		titulo = new JLabel();
		botonAgregar = new JButton();
		botonModificar = new JButton();
		botonEliminar = new JButton();
		botonGenerarReporte= new JButton();
		
		titulo =new JLabel("Seleccione lo que desee realizar");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		titulo.setBounds(123, 26, 230, 14);
		
		
		botonAgregar = new JButton("Agregar");
		botonAgregar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botonAgregar.setBounds(142, 68, 141, 23);
		botonAgregar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {// si el boton presionado fue 
				if(e.getSource() == botonAgregar){
					dispose();
		    		AdminAgregar ventanaAdminAgregar = new AdminAgregar(restaurante);
		    		ventanaAdminAgregar.setVisible(true);
				}
				
			}
		});
		
		
		botonModificar = new JButton("Modificar");
		botonModificar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botonModificar.setBounds(142, 102, 141, 23);
		botonModificar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == botonModificar){
					dispose();
					ModificarAdmin ventanaAdminModificar = new ModificarAdmin(restaurante);
		    		ventanaAdminModificar.setVisible(true);
					
					
				}
				
			}
		});
		
		
		botonEliminar = new JButton("Eliminar");
		botonEliminar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botonEliminar.setBounds(142, 136, 141, 23);
		botonEliminar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == botonEliminar){
					dispose();
		    		AdminEliminar ventana = new AdminEliminar(restaurante);
		    		ventana.setVisible(true);
					
				}
				
			}
		});
		
		
		botonGenerarReporte = new JButton("Generar Reporte");
		botonGenerarReporte.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botonGenerarReporte.setBounds(142, 170, 141, 23);
		botonGenerarReporte.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == botonGenerarReporte){
					dispose();
		    		ReporteAdmin ventanaAdminMostrar = new ReporteAdmin(restaurante);
		    		ventanaAdminMostrar.setVisible(true);
					
				}
				
			}
		});
		
		
		contentPane.add(titulo);
		contentPane.add(botonAgregar);
		contentPane.add(botonModificar);
		contentPane.add(botonEliminar);
		contentPane.add(botonGenerarReporte);
		
		botonSalir = new JButton("Salir");
		botonSalir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botonSalir.setBounds(20, 227, 89, 23);
		botonSalir.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == botonSalir){
					dispose();
					
				}
				
			}
		});
		contentPane.add(botonSalir);
		
	}
	
		
	
}
