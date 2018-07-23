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

public class ModificarJefe extends JFrame {

	/**
	 * 
	 */
	private JPanel contentPane;
	private JLabel titulo;
	private JLabel rutJefe;
	private JLabel nombreJefe;
	private JLabel edadJefe;
	private JLabel sueldoJefe;
	private JTextField textoRutBusqueda;
	private JTextField textoRut;
	private JTextField textoNombre;
	private JTextField textoEdad;
	private JTextField textoSueldo;
	private JButton botonBusqueda;
	private JButton botonModificar;
	private JButton botonAtras;
	private Restaurante restaurante;
	private Secundaria sec;
	private static final long serialVersionUID = 1L;
	
	public ModificarJefe(Restaurante restaurante) {
		super();
		configurarVentana();
		inicializarComponentes();
		sec = new Secundaria();
		this.setLocationRelativeTo(null);
		this.restaurante=restaurante;
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
		
		titulo = new JLabel("Ingrese el rut del Jefe");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		titulo.setBounds(134, 11, 228, 28);
		contentPane.add(titulo);
		
		textoRutBusqueda = new JTextField();
		textoRutBusqueda.setBounds(144, 40, 110, 20);
		contentPane.add(textoRutBusqueda);
		textoRutBusqueda.setColumns(10);
		
		rutJefe = new JLabel("RUT :");
		rutJefe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rutJefe.setBounds(65, 97, 59,20);
		contentPane.add(rutJefe);
		
		textoRut = new JTextField();
		textoRut.setBounds(134,99,134,20);
		contentPane.add(textoRut);
		textoRut.setColumns(10);
		
		nombreJefe = new JLabel("Nombre :");
		nombreJefe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nombreJefe.setBounds(65, 128, 59, 20);
		contentPane.add(nombreJefe);
	
		textoNombre = new JTextField();
		textoNombre.setBounds(134, 130, 134, 20);
		contentPane.add(textoNombre);
		textoNombre.setColumns(10);
		
		edadJefe = new JLabel("Edad :");
		edadJefe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		edadJefe.setBounds(65, 159, 59, 20);
		contentPane.add(edadJefe);
		
		textoEdad = new JTextField();
		textoEdad.setBounds(134, 161, 134, 20);
		contentPane.add(textoEdad);
		textoEdad.setColumns(10);
		
		sueldoJefe = new JLabel("Sueldo :");
		sueldoJefe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		sueldoJefe.setBounds(64, 190, 59, 20);
		contentPane.add(sueldoJefe);
		
		textoSueldo = new JTextField();
		textoSueldo.setBounds(134, 192, 134, 20);
		contentPane.add(textoSueldo);
		textoSueldo.setColumns(10);
		
		botonBusqueda = new JButton("Buscar");
		botonBusqueda.setBounds(154, 65, 89, 23);
		botonBusqueda.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(e.getSource()==botonBusqueda) 
				{
					try {
						
						if(textoRutBusqueda.getText().equals(restaurante.traerJefe().getRut()))
						{
							textoRut.setText(restaurante.traerJefe().getRut());
							textoNombre.setText(restaurante.traerJefe().getNombre());
							textoEdad.setText(String.valueOf(restaurante.traerJefe().getEdad()));
							textoSueldo.setText(String.valueOf(restaurante.traerJefe().getSueldo()));
						}else {
							JOptionPane.showMessageDialog(null, "El rut no existe");
						}
						
					}catch(Exception e1) {
						JOptionPane.showMessageDialog(null, "Ha ocurrido un inconveniente");
					}
				}
			}
		});
		contentPane.add(botonBusqueda);
		
		botonModificar = new JButton("Editar");
		botonModificar.setBounds(154, 227, 89, 23);
		botonModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(e.getSource()==botonModificar) 
				{
					if(sec.validarRut(textoRut.getText())) 
					{
						String rut = textoRut.getText();
						String nombre = textoNombre.getText();
						if(sec.validarNumeros(textoEdad.getText()))
						{
							int edad = Integer.parseInt(textoEdad.getText());
							if(sec.validarNumeros(textoSueldo.getText()))
							{
								int sueldo = Integer.parseInt(textoSueldo.getText());
								try {
									restaurante.modificarJefe(rut, nombre, edad, sueldo);
									JOptionPane.showMessageDialog(null, "Modificación exitosa");
								} catch (IOException e1) {
									JOptionPane.showMessageDialog(null, "Ha ocurrido un inconveniente");
									e1.printStackTrace();
								}
								JOptionPane.showMessageDialog(null, "Modificación exitosa");
							}else {
								JOptionPane.showMessageDialog(null, "El sueldo ingresado no es valido");
							}
						}else {
							JOptionPane.showMessageDialog(null, "La edad no es valida");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Rut no valido");
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
		botonAtras.setBounds(10, 227, 89, 23);
		contentPane.add(botonAtras);
		
		
		
		
	}
}
