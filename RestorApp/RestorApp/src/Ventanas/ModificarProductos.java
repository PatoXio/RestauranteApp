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

public class ModificarProductos extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Restaurante restaurante;
	private JTextField textClaveProducto;
	private JTextField textPrecioProducto;
	private JTextField textCantidadProducto;
	private JLabel caracteProducto;
	private JLabel claveProducto;
	private JButton botonModificar;
	private JButton botonAtras;
	private JLabel PrecioProducto;
	private Secundaria sec;
	private JLabel CantidadDelProducto;
	
	
	public ModificarProductos(Restaurante restaurante)
	{
		super();
		configurarVentana();
		inicializarComponentes();
		sec=new Secundaria();
		this.restaurante = restaurante;
	}
	
	public void configurarVentana() 
	{
		setTitle("RestorApp-Administrador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	
	public void inicializarComponentes() 
	{
		
		caracteProducto = new JLabel("Ingrese la clave del producto");
		caracteProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		caracteProducto.setBounds(122, 22, 228, 28);
		contentPane.add(caracteProducto);
		
		claveProducto = new JLabel("Clave del producto");
		claveProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		claveProducto.setBounds(26, 61, 119, 28);
		contentPane.add(claveProducto);
		
		textClaveProducto = new JTextField();
		textClaveProducto.setBounds(177, 66, 80, 23);
		contentPane.add(textClaveProducto);
		textClaveProducto.setColumns(10);
		
		botonModificar = new JButton("Modificar");
		botonModificar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botonModificar.setBounds(121, 186, 182, 31);
		botonModificar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				String claveProducto = textClaveProducto.getText();
				if(sec.validarNumeros(textPrecioProducto.getText())==true)
				{
					int precioProducto = Integer.parseInt(textPrecioProducto.getText());
					if(sec.validarNumeros(textCantidadProducto.getText())==true)
					{
						int cantidadProducto  = Integer.parseInt(textCantidadProducto.getText());
						
						if(e.getSource() == botonModificar)
						{
							if(restaurante.modificarProductos(claveProducto, cantidadProducto, precioProducto) == true) //Si se encuentra la mesa
							{
								JOptionPane.showMessageDialog(null, "Se Modifico correctamente el producto.");
							}
							else{
								JOptionPane.showMessageDialog(null, "La clave del producto no corresponde,ingrese otra.");
							}
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "La cantidad debe ser numerica");
					}

					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "El precio debe ser solo numerico");
				}
				
			}
		});
		contentPane.add(botonModificar);
		
		botonAtras = new JButton("Atras");
		botonAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				new ModificarAdmin(restaurante).setVisible(true);
				dispose();
			}
		});
		botonAtras.setBounds(10, 227, 89, 23);
		contentPane.add(botonAtras);
		
		PrecioProducto = new JLabel("Precio del producto");
		PrecioProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PrecioProducto.setBounds(26, 100, 119, 28);
		contentPane.add(PrecioProducto);
		
		textPrecioProducto = new JTextField();
		textPrecioProducto.setColumns(10);
		textPrecioProducto.setBounds(177, 105, 80, 23);
		contentPane.add(textPrecioProducto);
		
		CantidadDelProducto = new JLabel("Cantidad del producto");
		CantidadDelProducto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		CantidadDelProducto.setBounds(26, 139, 142, 28);
		contentPane.add(CantidadDelProducto);
		
		textCantidadProducto = new JTextField();
		textCantidadProducto.setColumns(10);
		textCantidadProducto.setBounds(177, 139, 80, 23);
		contentPane.add(textCantidadProducto);
	}
}	