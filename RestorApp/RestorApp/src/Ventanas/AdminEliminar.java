package Ventanas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clasesRestorApp.Restaurante;

public class AdminEliminar extends JFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	//private JLabel titulo;
	//private JButton botonEliminarMesa;
	//private JButton botonEliminarPedido;
	//private JButton botonEliminarProducto;
	//private JButton botonAtras;
	private Restaurante restaurante;
	
	
	public AdminEliminar(Restaurante restaurante){
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
		JLabel titulo = new JLabel("Seleccione lo que desea eliminar");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		titulo.setBounds(107, 11, 238, 23);
		contentPane.add(titulo);
		
		final JButton botonEliminarMesa = new JButton("Mesa");
		botonEliminarMesa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botonEliminarMesa.setBounds(157, 56, 102, 23);
		botonEliminarMesa.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				if(botonEliminarMesa == e.getSource()){
					dispose();
					EliminarMesa ventana = new EliminarMesa(restaurante);
					ventana.setVisible(true);
				}
				
			}
		});
		contentPane.add(botonEliminarMesa);
		
		final JButton botonEliminarPedido = new JButton("Pedido");
		botonEliminarPedido.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botonEliminarPedido.setBounds(157, 103, 102, 23);
		botonEliminarPedido.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == botonEliminarPedido){
					dispose();
					EliminarPedido ventana = new EliminarPedido(restaurante);
					ventana.setVisible(true);
				}
				
			}
		});
		contentPane.add(botonEliminarPedido);
		
		JButton botonEliminarProducto = new JButton("Producto");
		botonEliminarProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botonEliminarProducto.setBounds(157, 155, 102, 23);
		botonEliminarProducto.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				EliminarProducto ventana = new EliminarProducto(restaurante);
				ventana.setVisible(true);
			}
		});
		
		contentPane.add(botonEliminarProducto);
		
		
		
		final JButton botonAtras = new JButton("Atras");
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