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

public class EliminarEmpleado extends JFrame {

	/**
	 * 
	 */
	private JPanel contentPane;
	private JLabel titulo;
	private JButton botonAtras;
	private Restaurante restaurante;
	private static final long serialVersionUID = 1L;
	private JButton botonGarzon;
	private JButton botonCajero;
	private JButton botonCocinero;
	
	public EliminarEmpleado(Restaurante restaurante)
	{
		super();
		this.restaurante=restaurante;
		this.setLocationRelativeTo(null);
		configurarVentana();
		inicializarComponentes();
	}
	public void configurarVentana() 
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
		titulo = new JLabel("Seleccione el empleado que desea eliminar");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		titulo.setBounds(81, 22, 323, 23);
		contentPane.add(titulo);
		
		botonAtras = new JButton("Atras");
		botonAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new AdminEliminar(restaurante).setVisible(true);
				dispose();
			}
		});
		botonAtras.setBounds(10, 227, 89, 23);
		contentPane.add(botonAtras);
		
		botonGarzon = new JButton("Garzon");
		botonGarzon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botonGarzon.setBounds(160, 56, 107, 31);
		contentPane.add(botonGarzon);
		
		botonCocinero = new JButton("Cocinero");
		botonCocinero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botonCocinero.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				EliminarCocinero ventana = new EliminarCocinero(restaurante);
				ventana.setVisible(true);
				dispose();
			}
		});
		botonCocinero.setBounds(160, 113, 107, 31);
		contentPane.add(botonCocinero);
		
		botonCajero = new JButton("Cajero");
		botonCajero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botonCajero.setBounds(160, 173, 107, 31);
		contentPane.add(botonCajero);
	}
}
