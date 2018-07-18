package Ventanas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clasesRestorApp.Restaurante;

public class ModificarEmpleados extends JFrame {

	/**
	 * 
	 */
	private JPanel contentPane;
	private JButton botonModificarJefe;
	private JButton botonAtras;
	private Restaurante restaurante;
	private static final long serialVersionUID = 1L;
	
	public ModificarEmpleados (Restaurante restaurante) {
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
	
	private void inicializarComponentes() {
		
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
		botonModificarJefe.setBounds(139, 45, 123, 42);
		contentPane.add(botonModificarJefe);
		
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
