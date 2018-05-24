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

import clasesRestorApp.Archivos;
import clasesRestorApp.Restaurante;

public class EliminarMesa extends JFrame  {
	private JPanel contentPane;
	private JLabel caracteMesa;
	private JLabel numMesa;
	private JButton botonEliminar;
	private JTextField textMesa;
	private Restaurante restaurante;
	private JButton botonAtras;
	private JButton botonAgregarOtro;
	
	public EliminarMesa(Restaurante restaurante){
		super();
		this.restaurante = restaurante;
		configurarVentana();
		inicializarComponentes();
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
		caracteMesa = new JLabel("Ingrese caracterisiticas de la mesa");
		caracteMesa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		caracteMesa.setBounds(102, 21, 228, 28);
		contentPane.add(caracteMesa);
		
		numMesa = new JLabel("Numero de mesa");
		numMesa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		numMesa.setBounds(102, 105, 119, 28);
		contentPane.add(numMesa);
		
		textMesa = new JTextField();
		textMesa.setBounds(216, 105, 95, 31);
		contentPane.add(textMesa);
		textMesa.setColumns(10);
		
		botonEliminar = new JButton("Eliminar");
		botonEliminar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botonEliminar.setBounds(121, 186, 182, 31);
		botonEliminar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				int codMesa = Integer.parseInt(textMesa.getText());
				if(e.getSource() == botonEliminar){// si el
						if(restaurante.elminarMesa(codMesa)==true){
							JOptionPane.showMessageDialog(null, "Se elimino correctamente  mesa.");
						}
						else{
						JOptionPane.showMessageDialog(null, "La mesa no se puedo eliminar.");
						textMesa.setText("");
						}
	
				}
				
			}
		});
		contentPane.add(botonEliminar);
		
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
		
		botonAgregarOtro = new JButton("Eliminar otro ");
		botonAgregarOtro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == botonAgregarOtro){
					textMesa.setText("");
				}	
			}
		});
		botonAgregarOtro.setBounds(308, 227, 116, 23);
		contentPane.add(botonAgregarOtro);
	}
}
