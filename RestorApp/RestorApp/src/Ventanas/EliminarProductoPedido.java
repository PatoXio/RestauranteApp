package Ventanas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import clasesRestorApp.Restaurante;
import clasesRestorApp.Secundaria;

public class EliminarProductoPedido extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel titulo;
	private JButton botonEliminarOtro;
	private Restaurante restaurante;
	private JTextField textMesa;
	private JTextField textPedido;
	private JTextField textProducto;
	private JLabel codMesa;
	private JLabel codPedido;
	private JLabel codPro;
	private Secundaria sec;
	private JButton botonAtras;
	private JButton botonEliminar;
	
	public EliminarProductoPedido(Restaurante restaurante){
		super();
		this.restaurante = restaurante;
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

		titulo = new JLabel("Datos del producto a eliminar del pedido ");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		titulo.setBounds(107, 11, 260, 23);
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
		
		botonEliminar = new JButton("Eliminar");
		botonEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == botonEliminar ){
					if(sec.validarNumeros(textMesa.getText())==true)
					{
						int codMesa = Integer.parseInt(textMesa.getText());
						if(sec.validarNumeros(textPedido.getText())==true)
						{
							int codPedido = Integer.parseInt(textPedido.getText());
							String producto = textProducto.getText();
							if(restaurante.eliminarProductoPedido(codMesa, codPedido, producto)==true){
								JOptionPane.showMessageDialog(null, "Se elimino con exito");
							}
							else{
								JOptionPane.showMessageDialog(null, "No se elimino con exito. Quizas porque no existe el producto");
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "El codigo del pedido debe ser solo numerico");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "El codigo de la mesa debe ser solo numerico");
					}
				}
			}
		});
		botonEliminar.setBounds(158, 206, 89, 23);
		contentPane.add(botonEliminar);
		
		botonEliminarOtro = new JButton("Eliminar otro ");
		botonEliminarOtro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == botonEliminarOtro){
					textMesa.setText("");
					textPedido.setText("");
					textProducto.setText("");
					
				}	
			}
		});
		botonEliminarOtro.setBounds(308, 227, 116, 23);
		contentPane.add(botonEliminarOtro);
		
		
		
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