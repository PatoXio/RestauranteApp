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

public class ModificarMesas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Restaurante restaurante;
	private JTextField textMesa;
	private JTextField textEstadoMesa;
	private JLabel caracteMesa;
	private JLabel numMesa;
	private JButton botonModificar;
	private JButton botonAtras;
	private Secundaria sec;
	private JLabel estadoDeMesa;
	
	
	public ModificarMesas(Restaurante restaurante)
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
		
		caracteMesa = new JLabel("Ingrese Numero de la mesa");
		caracteMesa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		caracteMesa.setBounds(112, 22, 228, 28);
		contentPane.add(caracteMesa);
		
		numMesa = new JLabel("Numero de mesa");
		numMesa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		numMesa.setBounds(26, 73, 119, 28);
		contentPane.add(numMesa);
		
		textMesa = new JTextField();
		textMesa.setBounds(155, 78, 80, 23);
		contentPane.add(textMesa);
		textMesa.setColumns(10);
		
		botonModificar = new JButton("Modificar");
		botonModificar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botonModificar.setBounds(121, 186, 182, 31);
		botonModificar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(sec.validarNumeros(textMesa.getText())==true)
				{
					int codMesa = Integer.parseInt(textMesa.getText());
					String estadodMesa = textEstadoMesa.getText();
					if(e.getSource() == botonModificar)
					{
						if(restaurante.buscarMesaX(codMesa) == true) //Si se encuentra la mesa
						{
							restaurante.editarMesa(codMesa, estadodMesa);
							JOptionPane.showMessageDialog(null, "Se Modifico correctamente la mesa.");
						}
						else{
							JOptionPane.showMessageDialog(null, "El codigo de esa mesa no esta registrada, ingrese otra.");
						}
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "El codigo de la mesa debe ser solo numerico");
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
		
		estadoDeMesa = new JLabel("Estado de mesa");
		estadoDeMesa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		estadoDeMesa.setBounds(26, 125, 119, 28);
		contentPane.add(estadoDeMesa);
		
		textEstadoMesa = new JTextField();
		textEstadoMesa.setColumns(10);
		textEstadoMesa.setBounds(155, 130, 80, 23);
		contentPane.add(textEstadoMesa);
	}
}
