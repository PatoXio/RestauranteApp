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

public class AgregarCajero extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel titulo;
	private JLabel rutEmpleado;
	private JLabel nombreEmpleado;
	private JLabel sueldoEmpleado;
	private JLabel edadEmpleado;
	private JTextField textoRut;
	private JTextField textoNombre;
	private JTextField textoSueldo;
	private JTextField textoEdad;
	private JButton botonSalir;
	private JButton botonRegistro;
	private JButton botonAgregarOtro;
	
	//
	private Restaurante restaurante;
	private Secundaria sec;
	
	public AgregarCajero(Restaurante restaurante) {
		super();
		configurarVentana();
		inicializarVentana();
		this.restaurante = restaurante;
		sec = new Secundaria();
		
	}

	private void configurarVentana() {
		setTitle("RestorApp - Administrador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	
	private void inicializarVentana() {
		titulo = new JLabel ("Ingrese los datos de su empleado: ");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		titulo.setBounds(102, 21, 228, 28);
		contentPane.add(titulo);
		
		rutEmpleado = new JLabel ("Rut: ");
		rutEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rutEmpleado.setBounds(90,60,80,28);
		contentPane.add(rutEmpleado);
		
		textoRut = new JTextField();
		textoRut.setBounds(204, 66, 110, 20);
		contentPane.add(textoRut);
		textoRut.setColumns(10);
		
		nombreEmpleado = new JLabel("Nombre: ");
		nombreEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nombreEmpleado.setBounds(90, 99, 80, 28);
		contentPane.add(nombreEmpleado);
		
		textoNombre = new JTextField();
		textoNombre.setColumns(10);
		textoNombre.setBounds(204, 105, 110, 20);
		contentPane.add(textoNombre);
		
		sueldoEmpleado = new JLabel("Sueldo: ");
		sueldoEmpleado.setFont(new Font ("Tahoma", Font.PLAIN, 14));
		sueldoEmpleado.setBounds(90, 138, 80, 28);
		contentPane.add(sueldoEmpleado);
		
		textoSueldo = new JTextField();
		textoSueldo.setColumns(10);
		textoSueldo.setBounds(204, 144, 110, 20);
		contentPane.add(textoSueldo);
		
		edadEmpleado = new JLabel("Edad: ");
		edadEmpleado.setFont(new Font ("Tahoma", Font.PLAIN, 14));
		edadEmpleado.setBounds(90, 177, 80, 28);
		contentPane.add(edadEmpleado);
		
		textoEdad = new JTextField();
		textoEdad.setColumns(10);
		textoEdad.setBounds(204, 183, 110, 20);
		contentPane.add(textoEdad);
		
		botonRegistro = new JButton("Registrar");
		botonRegistro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botonRegistro.setBounds(150, 216, 143, 31);
		botonRegistro.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) 
			{
				if(e.getSource()== botonRegistro)
				{
					if(sec.validarRut(textoRut.getText())==true)
					{
						String rut = textoRut.getText();
						String nombre = textoNombre.getText();
						if(sec.validarNumeros(textoSueldo.getText())==true)
						{
							int sueldo = Integer.parseInt(textoSueldo.getText());
							if(sec.validarNumeros(textoEdad.getText())==true)
							{
								int edad = Integer.parseInt(textoEdad.getText());
								try {
										if(restaurante.agregarCajero(rut, nombre, sueldo, edad, 0, 0)==true)
										{
											JOptionPane.showMessageDialog(null, "El Cajero ha sido agregado excitosamente");
										}
										else {
											JOptionPane.showMessageDialog(null, "El rut ya exite");
										}
									
								} catch (IOException e1) {
									JOptionPane.showMessageDialog(null, "Ha ocurrido un inconveniente");
								}
								
							}
						}else {
							JOptionPane.showMessageDialog(null, "Debes ingresar numeros");
						}
					}else {
						JOptionPane.showMessageDialog(null, "El rut no es valido");
					}
				}
					
					
			}
			});
		contentPane.add(botonRegistro);
		
		botonAgregarOtro = new JButton("Agregar otro ");
		botonAgregarOtro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == botonAgregarOtro){
					textoRut.setText("");
					textoNombre.setText("");
					textoSueldo.setText("");
					textoEdad.setText("");
				}	
			}
		});
		botonAgregarOtro.setBounds(308, 227, 116, 23);
		contentPane.add(botonAgregarOtro);
		
		botonSalir = new JButton("Salir ");
		botonSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == botonSalir ){
					dispose();
					VentanaAdmin ventana = new VentanaAdmin(restaurante);
					ventana.setVisible(true);
				}
				
			}
		});
		botonSalir.setBounds(10, 227, 116, 23);
		contentPane.add(botonSalir);
	}
}
