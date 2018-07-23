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

public class ModificarEmpleados extends JFrame {

	/**
	 * 
	 */
	private JPanel contentPane;
	private JLabel titulo;
	private JButton botonModificarJefe;
	private JButton botonModificarGarzon;
	private JButton botonModificarCocinero;
	private JButton botonModificarCajero;
	private JButton botonAtras;
	private Restaurante restaurante;
	private static final long serialVersionUID = 1L;
	
	public ModificarEmpleados (Restaurante restaurante) {
		super();
		configurarVentana();
		inicializarComponentes();
		this.setLocationRelativeTo(null);
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
	
	private void inicializarComponentes() {
		
		titulo = new JLabel("¿Que empleado desea editar?");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		titulo.setBounds(110, 11, 228, 28);
		contentPane.add(titulo);
		
		botonModificarJefe = new JButton("Jefe"); 
		botonModificarJefe.setFont(new Font("Tahoma", Font.PLAIN, 13));
		botonModificarJefe.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				ModificarJefe ventanaModificar = new ModificarJefe(restaurante); 
				ventanaModificar.setVisible(true);
				dispose();
			}
		});
		botonModificarJefe.setBounds(139, 57, 123, 28);
		contentPane.add(botonModificarJefe);
		
		botonModificarGarzon = new JButton("Garzon");
		botonModificarGarzon.setFont(new Font("Tahoma", Font.PLAIN, 13));
		botonModificarGarzon.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ModificarGarzon ventanaModificar = new ModificarGarzon(restaurante); 
				ventanaModificar.setVisible(true);
				dispose();
			}
		});
		botonModificarGarzon.setBounds(139, 96, 123, 28);
		contentPane.add(botonModificarGarzon);
		
		botonModificarCocinero = new JButton("Cocinero");
		botonModificarCocinero.setFont(new Font("Tahoma", Font.PLAIN, 13));
		botonModificarCocinero.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent e) 
			{
				ModificarCocinero ventanaModificar = new ModificarCocinero(restaurante);
				ventanaModificar.setVisible(true);
				dispose();
			}
		});
		botonModificarCocinero.setBounds(139, 135, 123, 28);
		contentPane.add(botonModificarCocinero);
		
		botonModificarCajero = new JButton("Cajero");
		botonModificarCajero.setFont(new Font("Tahoma", Font.PLAIN, 13));
		botonModificarCajero.addActionListener(new ActionListener()
		{
			public void actionPerformed (ActionEvent e) 
			{
				ModificarCajero ventanaModificar = new ModificarCajero(restaurante);
				ventanaModificar.setVisible(true);
				dispose();
			}
		});
		botonModificarCajero.setBounds(139, 174, 123, 28);
		contentPane.add(botonModificarCajero);
		
		
		botonAtras = new JButton("Atras");
		botonAtras.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				new VentanaAdmin(restaurante).setVisible(true);
				dispose();
			}
		});
		botonAtras.setBounds(10, 227, 89, 23);
		contentPane.add(botonAtras);
	}

}
