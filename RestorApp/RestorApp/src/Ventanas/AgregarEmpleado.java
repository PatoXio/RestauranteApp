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

public class AgregarEmpleado extends JFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel titulo;
	private JButton botonAgregarCocinero;
	private JButton botonAgregarGarzon;
	private JButton botonAgregarCajero;
	private JButton botonAgregarJefe;
	private JButton botonAtras;
	
	private Restaurante restaurante;
	//testing
	

	public AgregarEmpleado(Restaurante restaurante)
	{
		super();
		this.restaurante = restaurante;
		configurarVentana();
		inicializarComponenetes();
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

	private void inicializarComponenetes() {
		titulo = new JLabel ("¿Que tipo de empleado ingresará?");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		titulo.setBounds(107, 11, 238, 23);
		contentPane.add(titulo);
		
		botonAgregarCocinero = new JButton("Cocinero") ;
		botonAgregarCocinero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botonAgregarCocinero.setBounds(45, 75, 106, 40);
		botonAgregarCocinero.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == botonAgregarCocinero){
					dispose();
					AgregarCocinero ventana = new AgregarCocinero(restaurante);
					ventana.setVisible(true);
				}
			}
		});
		contentPane.add(botonAgregarCocinero);
		
		
		botonAgregarGarzon = new JButton("Garzon") ;
		botonAgregarGarzon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botonAgregarGarzon.setBounds(260, 75, 106, 40);
		botonAgregarGarzon.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==botonAgregarGarzon) {
					dispose();
					AgregarGarzon ventana = new AgregarGarzon(restaurante);
					ventana.setVisible(true);
				}
			}
		}
		);
		contentPane.add(botonAgregarGarzon);
		
		
		botonAgregarCajero = new JButton("Cajero") ;
		botonAgregarCajero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botonAgregarCajero.setBounds(45, 150, 106, 40);
		botonAgregarCajero.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == botonAgregarCajero){
					dispose();
					AgregarCajero ventana = new AgregarCajero(restaurante);
					ventana.setVisible(true);
				}
			}
		});
		contentPane.add(botonAgregarCajero);
		
		botonAgregarJefe =  new JButton("Jefe");
		botonAgregarJefe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botonAgregarJefe.setBounds(260, 145, 106, 40);
		botonAgregarJefe.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == botonAgregarJefe){
					dispose();
					AgregarJefe ventana = new AgregarJefe(restaurante);
					ventana.setVisible(true);
				}
			}
		});
		contentPane.add(botonAgregarJefe);
		
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
