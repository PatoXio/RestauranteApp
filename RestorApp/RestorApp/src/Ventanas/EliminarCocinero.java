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

public class EliminarCocinero extends JFrame {

	/**
	 * 
	 */
	private JPanel contentPane;
	private JLabel titulo;
	private JLabel rutEmpleado;
	private JTextField textoRut;
	private JButton botonEliminar;
	private JButton botonEliminarOtro;
	private JButton botonAtras;
	private Secundaria sec;
	private Restaurante restaurante;
	private static final long serialVersionUID = 1L;

	
	
	public EliminarCocinero (Restaurante restaurante)
	{
		super();
		this.restaurante=restaurante;
		sec = new Secundaria();
		configurarVentana();
		inicializarComponentes();
	}
	
	public void configurarVentana() 
	{
		setTitle("RestorApp-Administrador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	
	public void inicializarComponentes()
	{
		titulo = new JLabel("Ingrese el rut del Empleado a eliminar");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		titulo.setBounds(100, 22, 233, 23);
		contentPane.add(titulo);
		
		rutEmpleado = new JLabel("Rut :");
		rutEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rutEmpleado.setBounds(100, 67, 68, 23);
		contentPane.add(rutEmpleado);
		
		textoRut = new JTextField();
		textoRut.setBounds(155, 70, 165, 20);
		contentPane.add(textoRut);
		textoRut.setColumns(10);
		
		botonEliminar = new JButton("Eliminar");
		botonEliminar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botonEliminar.setBounds(140, 101, 140, 31);
		botonEliminar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(e.getSource() == botonEliminar) 
				{
					if(sec.validarRut(textoRut.getText()))
					{
						String rut = textoRut.getText();
						try {
							
								if(restaurante.eliminarEmpleado(rut)==true)
								{
									JOptionPane.showMessageDialog(null, "El Empleado ha sido eliminado exitosamente");
								}else {
									JOptionPane.showMessageDialog(null, "El rut no existe");
								}
							
							
						}catch(IOException e1) {
							JOptionPane.showMessageDialog(null, "Ha ocurrido un inconveniente");
						}
						
					}else {
						JOptionPane.showMessageDialog(null, "Rut invalido");
					}
				}
			}
			
		});
		contentPane.add(botonEliminar);
		
		botonEliminarOtro = new JButton("Eliminar otro");
		botonEliminarOtro.setBounds(319, 160, 105, 23);
		botonEliminarOtro.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(e.getSource() == botonEliminarOtro)
				{
					textoRut.setText("");
				}
			}
		});
		contentPane.add(botonEliminarOtro);
		
		botonAtras = new JButton("Atras");
		botonAtras.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				new VentanaAdmin(restaurante).setVisible(true);
				dispose();
			}
		});
		botonAtras.setBounds(10, 160, 89, 23);
		contentPane.add(botonAtras);
		
		
	}
}
