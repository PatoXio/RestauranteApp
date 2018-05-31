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

public class EliminarPedido extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Secundaria sec;
	private JTextField textMesa;
	private JTextField textPedido;
	private Restaurante restaurante;
	
	public EliminarPedido(Restaurante restaurante){
		super();
		this.restaurante = restaurante;
		configurarVentana();
		sec=new Secundaria();
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
		JLabel caractePedido = new JLabel(" caracteristicas del pedido a eliminar");
		caractePedido.setFont(new Font("Tahoma", Font.PLAIN, 14));
		caractePedido.setBounds(107, 11, 238, 23);
		contentPane.add(caractePedido);
		
		JLabel numMesa = new JLabel("Numero de la mesa");
		numMesa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		numMesa.setBounds(78, 91, 123, 14);
		contentPane.add(numMesa);
		
		textMesa = new JTextField();
		textMesa.setBounds(244, 90, 105, 20);
		contentPane.add(textMesa);
		textMesa.setColumns(10);
		
		JLabel numPedido = new JLabel("Numero del pedido");
		numPedido.setFont(new Font("Tahoma", Font.PLAIN, 14));
		numPedido.setBounds(78, 145, 123, 14);
		contentPane.add(numPedido);
		
		textPedido = new JTextField();
		textPedido.setBounds(244, 144, 105, 20);
		contentPane.add(textPedido);
		textPedido.setColumns(10);
		
		final JButton botonEliminar = new JButton("Eliminar");
		botonEliminar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botonEliminar.setBounds(159, 188, 105, 31);
		botonEliminar.addActionListener(new ActionListener(){
                        @Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == botonEliminar ){
					if(sec.validarNumeros(textMesa.getText())==true)
					{
						int codMesa = Integer.parseInt(textMesa.getText());
						if(sec.validarNumeros(textPedido.getText())==true)
						{
							int codPedido = Integer.parseInt(textPedido.getText());
							try {
								if(restaurante.eliminarPedido(codMesa, codPedido)==true){
									JOptionPane.showMessageDialog(null, "Se elimino con exito");
								}
								else{
									JOptionPane.showMessageDialog(null, "No se logro eliminar el pedido. Quizas porque este no existe");
								}
							} catch (HeadlessException | IOException e1) {
								JOptionPane.showMessageDialog(null, "No se logro eliminar. Quizas ya no exista");
								e1.printStackTrace();
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "El codigo del pedido debe ser numerico");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "El codigo de la mesa debe ser numerico");
					}
					
				}
			}
		});
		
		
		contentPane.add(botonEliminar);
		final JButton botonEliminarOtro = new JButton("Eliminar otro ");
		botonEliminarOtro.addActionListener(new ActionListener() {
                        @Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == botonEliminarOtro){
					textMesa.setText("");
					textPedido.setText("");
				}	
			}
		});
		botonEliminarOtro.setBounds(308, 227, 116, 23);
		contentPane.add(botonEliminarOtro);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() 
		{
                        @Override
			public void actionPerformed(ActionEvent e) 
			{
				dispose();
				new AdminEliminar(restaurante).setVisible(true);
				
			}
		});
		btnAtras.setBounds(10, 227, 89, 23);
		contentPane.add(btnAtras);
	}
		
	
		
}
