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



public class AdminAgregar extends JFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel titulo;
	private JButton botonAgregarMesa;
	private JButton botonAgregarPedido;
	private JButton botonAgregarProducto;
	//private JButton botonAgregarEmpleado;
	private JButton botonAtras;
	private Restaurante restaurante;
	
	
	public AdminAgregar(Restaurante restaurante){
		super();
		this.restaurante = restaurante;
		configurarVentana();
		this.setLocationRelativeTo(null);
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
		titulo = new JLabel("Seleccione lo que desea agregar");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		titulo.setBounds(107, 11, 238, 23);
		contentPane.add(titulo);
		
		botonAgregarMesa = new JButton("Mesa");
		botonAgregarMesa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botonAgregarMesa.setBounds(45, 56, 114, 36);
		botonAgregarMesa.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == botonAgregarMesa){
					dispose();
					AgregarMesa ventana = new AgregarMesa(restaurante);
					ventana.setVisible(true);
				}
				
			}
		});
		contentPane.add(botonAgregarMesa);
		
		botonAgregarPedido = new JButton("Pedido");
		botonAgregarPedido.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botonAgregarPedido.setBounds(45, 128, 114, 36);
		botonAgregarPedido.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == botonAgregarPedido){
					dispose();
					AgregarPedido ventana = new AgregarPedido(restaurante);
					ventana.setVisible(true);
				}
				
			}
		});
		contentPane.add(botonAgregarPedido);
		
		botonAgregarProducto = new JButton("Producto");
		botonAgregarProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botonAgregarProducto.setBounds(253, 56, 114, 36);
		botonAgregarProducto.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
				AgregarProducto ventana = new AgregarProducto(restaurante);
				ventana.setVisible(true);
			}
		});
		
		contentPane.add(botonAgregarProducto);
		
		
		
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
		
		JButton button = new JButton("Personal");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AgregarEmpleado ventana = new AgregarEmpleado(restaurante);
				ventana.setVisible(true);
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button.setBounds(253, 128, 114, 36);
		contentPane.add(button);
	}
}