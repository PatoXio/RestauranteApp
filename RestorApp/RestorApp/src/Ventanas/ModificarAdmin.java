package Ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clasesRestorApp.Restaurante;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class ModificarAdmin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Restaurante restaurante;
	private JButton botonAtras;
	private JButton botonModificarMesas;
	private JButton botonModificarProductos;
	private JLabel modificar;

	public ModificarAdmin(Restaurante restaurante)
	{
		super();
		configurarVentana();
		inicializarComponentes();
		this.restaurante = restaurante;
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
		
		botonModificarMesas = new JButton("Mesas");
		botonModificarMesas.setFont(new Font("Tahoma", Font.PLAIN, 13));
		botonModificarMesas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
	    		ModificarMesas ventanaAdminModificar = new ModificarMesas(restaurante);
	    		ventanaAdminModificar.setVisible(true);
	    		dispose();
			}
		});
		botonModificarMesas.setBounds(139, 91, 123, 42);
		contentPane.add(botonModificarMesas);
		
		botonModificarProductos = new JButton("Productos");
		botonModificarProductos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		botonModificarProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ModificarProductos ventanaAdminModificar = new ModificarProductos(restaurante);
	    		ventanaAdminModificar.setVisible(true);
	    		dispose();
			}
		});
		botonModificarProductos.setBounds(139, 144, 123, 35);
		contentPane.add(botonModificarProductos);
		
		modificar = new JLabel("Seleccione lo que desea modificar");
		modificar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		modificar.setBounds(107, 11, 238, 23);
		contentPane.add(modificar);
		
		
	}
}
