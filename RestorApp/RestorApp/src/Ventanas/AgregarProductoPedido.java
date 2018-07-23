package Ventanas;

import java.awt.Font;
import java.awt.HeadlessException;
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

public class AgregarProductoPedido extends JFrame
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel titulo;
	private JButton botonRegistrar;
	private Restaurante restaurante;
	private JTextField textMesa;
	private JTextField textPedido;
	private JTextField textProducto;
	private JLabel codMesa;
	private JLabel codPedido;
	private JLabel codPro;
	private JButton botonAgregarOtro;
	private JButton botonAtras;
	private Secundaria sec;
	
	public AgregarProductoPedido(Restaurante restaurante){
		super();
		this.restaurante = restaurante;
		this.setLocationRelativeTo(null);
		configurarVentana();
		sec=new Secundaria();
		inicializarComponentes();
	}
	private void configurarVentana(){
		setTitle("RestorApp-Administrador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	private void inicializarComponentes(){

		titulo = new JLabel("Ingrese datos del producto");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		titulo.setBounds(125, 11, 199, 34);
		contentPane.add(titulo);
		 
		codMesa = new JLabel("Codigo Mesa");
		codMesa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		codMesa.setBounds(60, 74, 117, 17);
		contentPane.add(codMesa);
		
		codPedido = new JLabel("Codigo Pedido");
		codPedido.setFont(new Font("Tahoma", Font.PLAIN, 14));
		codPedido.setBounds(60, 125, 117, 17);
		contentPane.add(codPedido);
		
		codPro = new JLabel("Codigo Producto");
		codPro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		codPro.setBounds(60, 168, 117, 14);
		contentPane.add(codPro);
		
		textMesa = new JTextField();
		textMesa.setBounds(187, 74, 86, 20);
		contentPane.add(textMesa);
		textMesa.setColumns(10);
		
		textPedido = new JTextField();
		textPedido.setBounds(187, 124, 86, 20);
		contentPane.add(textPedido);
		textPedido.setColumns(10);
		
		textProducto = new JTextField();
		textProducto.setBounds(187, 167, 86, 20);
		contentPane.add(textProducto);
		textProducto.setColumns(10);
		
		botonRegistrar = new JButton("Registrar");
		botonRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == botonRegistrar ){
					if(sec.validarNumeros(textMesa.getText())==true)
					{
						int codMesa = Integer.parseInt(textMesa.getText());
						if(sec.validarNumeros(textPedido.getText())==true)
						{
							int codPedido = Integer.parseInt(textPedido.getText());
							String producto = textProducto.getText();
							try {
								if(restaurante.agregarProductoAlPedido(codMesa, codPedido, producto)==true){
									JOptionPane.showMessageDialog(null, "Se agrego con exito");
								}
								else
								{
									JOptionPane.showMessageDialog(null, "No se pudo agregar. Quizas el producto no exista");
								}
							} catch (HeadlessException | IOException e1) {
								JOptionPane.showMessageDialog(null, "No se pudo agregar");
								e1.printStackTrace();
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "El codigo del pedido debe contener solo numeros");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "El codigo de mesa solo debe contener numeros");
					}
				}
			}
		});
		botonRegistrar.setBounds(158, 206, 89, 23);
		contentPane.add(botonRegistrar);
		
		botonAgregarOtro = new JButton("Agregar otro ");
		botonAgregarOtro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == botonAgregarOtro){
					textMesa.setText("");
					textPedido.setText("");
					textProducto.setText("");
					
				}	
			}
		});
		botonAgregarOtro.setBounds(308, 227, 116, 23);
		contentPane.add(botonAgregarOtro);
		
		
		
		botonAtras = new JButton("Atras");
		botonAtras.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botonAtras.setBounds(20, 227, 89, 23);
		botonAtras.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == botonAtras){
					dispose();
					VentanaAdmin ventana = new VentanaAdmin(restaurante);
					ventana.setVisible(true);
				}
				
			}
		});
		contentPane.add(botonAtras);
	
	}
	
}
