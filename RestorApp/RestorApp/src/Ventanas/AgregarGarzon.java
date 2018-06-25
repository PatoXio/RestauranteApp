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

public class AgregarGarzon extends JFrame
{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel titulo;
	private JLabel rutEmpleado;
	private JLabel nombreEmpleado;
	private JLabel sueldoEmpleado;
	private JLabel edadEmpleado;
	private JLabel nivelDeInglesEmpleado;
	private JTextField textoRut;
	private JTextField textoNombre;
	private JTextField textoSueldo;
	private JTextField textoEdad;
	private JTextField textoNivelIngles;
	private JButton botonAgregarOtro;
	private JButton botonRegistro;
	private JButton botonSalir;
	
	
	
	
	private Restaurante restaurante;
	private Secundaria sec;

	public AgregarGarzon(Restaurante restaurante) {
		super();
		this.restaurante = restaurante;
		sec = new Secundaria();
		configurarVentana();
		inicializarVentana();
	}
	
	public void configurarVentana()
	{
		setTitle("RestorApp - Administrador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	
	public void inicializarVentana() 
	{
		titulo = new JLabel ("Ingrese los datos del Garzón: ");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		titulo.setBounds(114, 21, 228, 28);
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
		
		nivelDeInglesEmpleado = new JLabel("Nivel de Ingles:");
		nivelDeInglesEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nivelDeInglesEmpleado.setBounds(90, 216, 104, 28);
		contentPane.add(nivelDeInglesEmpleado);
		
		textoNivelIngles = new JTextField();
		textoNivelIngles.setColumns(10);
		textoNivelIngles.setBounds(204, 222, 110, 20);
		contentPane.add(textoNivelIngles);
		
		
		botonRegistro = new JButton("Registrar");
		botonRegistro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botonRegistro.setBounds(155, 255, 139, 31);
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
								String nivelDeIngles = textoNivelIngles.getText();
									try {
										if(restaurante.agregarGarzon(rut, nombre, sueldo, edad, nivelDeIngles, 0)==true)
										{
											JOptionPane.showMessageDialog(null, "Garzón agregado excitosamente");
										}else {
											JOptionPane.showMessageDialog(null, "El rut ya existe");
										}
									}catch (IOException e1) {
										JOptionPane.showMessageDialog(null, "Ha ocurrido un inconveniente");
									}
								
							}else {
							JOptionPane.showMessageDialog(null, "Numero invalido");
							}
						}else {
						JOptionPane.showMessageDialog(null, "Numero invalido");
						}
					}else {
						JOptionPane.showMessageDialog(null, "Rut invalido");
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
		botonAgregarOtro.setBounds(314, 261, 110, 23);
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
		botonSalir.setBounds(10, 261, 116, 23);
		contentPane.add(botonSalir);
		
		
	}
}
