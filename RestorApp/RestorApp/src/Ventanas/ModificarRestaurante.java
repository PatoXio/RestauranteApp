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

import clasesRestorApp.Restaurante;
import clasesRestorApp.Secundaria;

public class ModificarRestaurante extends JFrame {
	private JPanel contentPane;
	private JLabel titulo;
	private JLabel rutCocinero;
	private JLabel nombreCocinero;
	private JTextField textoNombre;
	private JTextField textoDireccion;
	private JButton botonModificar;
	private JButton botonAtras;
	private Restaurante restaurante;
	private Secundaria sec;
	private static final long serialVersionUID = 1L;
	
	public ModificarRestaurante(Restaurante restaurante) {
		super();
		
		configurarVentana();
		this.setLocationRelativeTo(null);
		inicializarComponentes();
		
		sec = new Secundaria();
		this.restaurante=restaurante;
	}
	
	private void configurarVentana() 
	{
		setTitle("RestorApp-Administrador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	
	private void inicializarComponentes() {
		
		titulo = new JLabel("Ingrese los nuevos datos del Restaurante");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		titulo.setBounds(75, 11, 259, 28);
		contentPane.add(titulo);
		
		rutCocinero = new JLabel("Nombre :");
		rutCocinero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rutCocinero.setBounds(75, 60, 59,20);
		contentPane.add(rutCocinero);
		
		textoNombre = new JTextField();
		textoNombre.setBounds(154,60,134,20);
		contentPane.add(textoNombre);
		textoNombre.setColumns(10);
		
		nombreCocinero = new JLabel("Direcci\u00F3n :");
		nombreCocinero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nombreCocinero.setBounds(75, 96, 80, 20);
		contentPane.add(nombreCocinero);
	
		textoDireccion = new JTextField();
		textoDireccion.setBounds(154, 98, 134, 20);
		contentPane.add(textoDireccion);
		textoDireccion.setColumns(10);
		
		botonModificar = new JButton("Editar");
		botonModificar.setBounds(164, 129, 89, 23);
		botonModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(e.getSource()==botonModificar) 
				{
					
						String nombre = textoNombre.getText();
						String direccion = textoDireccion.getText();

								try {
									restaurante.modificarRestaurante(nombre, direccion);
									JOptionPane.showMessageDialog(null, "Modificación exitosa");
								} catch (IOException e1) {
									JOptionPane.showMessageDialog(null, "Ha ocurrido un incoveniente");
									e1.printStackTrace();
								}

				}
			}
		});
		contentPane.add(botonModificar);
		
		botonAtras = new JButton("Atras");
		botonAtras.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				new VentanaAdmin(restaurante).setVisible(true);
				dispose();
			}
		});
		botonAtras.setBounds(10, 129, 89, 23);
		contentPane.add(botonAtras);

		
	}
}
