package Ventanas;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import clasesRestorApp.Restaurante;

import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class MostrarProductos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Restaurante restaurante;
	
	public MostrarProductos(Restaurante restaurante)
	{
		super();
		configurarVentana();
		this.setLocationRelativeTo(null);
		inicializarComponentes(restaurante);
		this.restaurante = restaurante;
		
	}
	
	private void configurarVentana()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	}
	
	public void inicializarComponentes(clasesRestorApp.Restaurante Lista ) 
	{
		
		
		JButton btnArchivo = new JButton("Archivo");
		btnArchivo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
			}
		});
		
		btnArchivo.setBounds(335, 227, 89, 23);
		contentPane.add(btnArchivo);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				new ReporteAdmin(restaurante).setVisible(true);
				dispose();
			}
		});
		btnAtras.setBounds(236, 227, 89, 23);
		contentPane.add(btnAtras);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 209, 414, 6);
		contentPane.add(separator);
		
		JLabel lblTitulo = new JLabel("Mostrar Productos");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTitulo.setBounds(153, 11, 114, 14);
		contentPane.add(lblTitulo);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(10, 36, 414, 162);
		contentPane.add(scrollPane_1);
		
		JTextArea textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		textArea.setEditable(false);
		textArea.setColumns(10);
		Lista.mostrarProductosVentana(textArea);
	}
}