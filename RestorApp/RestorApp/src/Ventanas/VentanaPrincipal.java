package Ventanas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clasesRestorApp.Restaurante;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

public class VentanaPrincipal extends JFrame{

	/**
	 * 
	 */
	private JPanel contentPane;
	private JLabel lbl;
	private JButton botonAdmin;
	private JButton botonAccion;
	private JButton botonSalir;
	private Restaurante restaurante;
	//private JLabel image;
	private static final long serialVersionUID = 1L;
	private ImageIcon imagen;
	private JLabel lblNewLabel;
	public VentanaPrincipal (Restaurante restaurante)
	{
		super();
		configurarVentana();
		this.setLocationRelativeTo(null);
		this.restaurante = restaurante;
	}
	
	private void configurarVentana()
	{
		
		
		setTitle("RestorApp-Administrador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 200, 703, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		imagen = new ImageIcon(VentanaPrincipal.class.getResource("/Images/37656592_10212709660721813_6235967754134880256_n.jpg"));
		this.repaint();
		
		botonAccion = new JButton("Acciones");
		botonAccion.setForeground(new Color(0, 0, 139));
		botonAccion.setFont(new Font("Tahoma", Font.BOLD, 14));
		botonAccion.setBounds(95, 269, 130, 42);
		botonAccion.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(e.getSource() == botonAccion)
				{
					VentanaAcciones ventana = new VentanaAcciones (restaurante);
					ventana.setVisible(true);
					dispose();
				}
			}
		});
		contentPane.add(botonAccion);
		
		botonAdmin = new JButton("Admin");
		botonAdmin.setForeground(new Color(0, 0, 139));
		botonAdmin.setFont(new Font("Tahoma", Font.BOLD, 15));
		botonAdmin.setBounds(454, 269, 130, 42);
		botonAdmin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Ventana ventana = new Ventana(restaurante);
				ventana.setVisible(true);
				dispose();
			}
		});
		contentPane.add(botonAdmin);
		
		botonSalir = new JButton("Salir");
		botonSalir.setForeground(new Color(0, 0, 139));
		botonSalir.setFont(new Font("Tahoma", Font.BOLD, 14));
		botonSalir.setBounds(273, 318, 110, 30);
		botonSalir.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(e.getSource() == botonSalir)
				{
					dispose();
				}
			}
		});
		contentPane.add(botonSalir);
		
		lbl = new JLabel("");
		lbl.setForeground(new Color(105, 105, 105));
		lbl.setBounds(0, 0, 687, 401);
		Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(lbl.getWidth(), lbl.getHeight(), Image.SCALE_DEFAULT));
		
		lblNewLabel = new JLabel("Restaurante App");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel.setForeground(new Color(105, 105, 105));
		lblNewLabel.setBounds(235, 114, 246, 61);
		contentPane.add(lblNewLabel);
		lbl.setIcon(icono);
		contentPane.add(lbl);
		
		/*JLabel imagen = new JLabel("");
		imagen.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Images/maxresdefault.jpg")));
		imagen.setBounds(-147, 11, 834, 401);
		contentPane.add(imagen);*/
	}
}
