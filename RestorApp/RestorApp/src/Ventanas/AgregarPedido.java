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

import clasesRestorApp.Archivos;
import clasesRestorApp.Restaurante;
import clasesRestorApp.Secundaria;

public class AgregarPedido extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textMesa;
	private JTextField textPedido;
	private JLabel caractePedido;
	private JLabel numMesa;
	private JLabel numPedido;
	private JButton botonRegistrar;
	private JButton botonAtras;
	private Secundaria sec;
	//private JButton botonAgregarOtro;
	private Restaurante restaurante;
	private Archivos arc;
	
	public AgregarPedido(Restaurante restaurante){
		super();
		this.restaurante = restaurante;
		configurarVentana();
		inicializarComponentes();
		sec=new Secundaria();
		arc= new Archivos();
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
		caractePedido = new JLabel("Ingrese caracteristicas del pedido");
		caractePedido.setFont(new Font("Tahoma", Font.PLAIN, 14));
		caractePedido.setBounds(103, 27, 246, 14);
		contentPane.add(caractePedido);
		
		numMesa = new JLabel("Numero de la mesa");
		numMesa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		numMesa.setBounds(78, 91, 123, 14);
		contentPane.add(numMesa);
		
		textMesa = new JTextField();
		textMesa.setBounds(244, 90, 105, 20);
		contentPane.add(textMesa);
		textMesa.setColumns(10);
		
		numPedido = new JLabel("Numero del pedido");
		numPedido.setFont(new Font("Tahoma", Font.PLAIN, 14));
		numPedido.setBounds(78, 145, 123, 14);
		contentPane.add(numPedido);
		
		textPedido = new JTextField();
		textPedido.setBounds(244, 144, 105, 20);
		contentPane.add(textPedido);
		textPedido.setColumns(10);
		
		botonRegistrar = new JButton("Registrar");
		botonRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		botonRegistrar.setBounds(159, 188, 105, 31);
		botonRegistrar.addActionListener(new ActionListener()
		{
                        @Override
			public void actionPerformed(ActionEvent e)
			{
                if(sec.validarNumeros(textMesa.getText())==true)
                {
					int codMesa = Integer.parseInt(textMesa.getText());
					if(sec.validarNumeros(textPedido.getText())==true)
					{
						int codPedido = Integer.parseInt(textPedido.getText());
						if(e.getSource() == botonRegistrar){
							try {
								if(restaurante.AgregarPedido(codMesa, codPedido)==true){
									try {
										arc.escribirSoloPedidoTxT(Integer.toString(codMesa),Integer.toString(codPedido));
									} catch (IOException e1) {
										JOptionPane.showMessageDialog(null, "Ocurrio un error sobreescribir el archivo");
									}
									JOptionPane.showMessageDialog(null, "Se agrego con exito");
								}
								else{
									JOptionPane.showMessageDialog(null, "No se logro agregar el pedido");
									textMesa.setText("");
									textPedido.setText("");
									
								}
							} catch (HeadlessException | IOException e1) {
								JOptionPane.showMessageDialog(null, "Error al agregar el pedido");								
								e1.printStackTrace();
							}
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "El codigo del pedido solo debe contener numeros");
					}
				}
                else
				{
					JOptionPane.showMessageDialog(null, "El codigo de mesa solo debe llevar numeros");
				}
				
			}
		});
		
		contentPane.add(botonRegistrar);
		
		
		botonAtras = new JButton("Atras");
		botonAtras.addActionListener(new ActionListener() 
		{
                        @Override
			public void actionPerformed(ActionEvent e) 
			{
				new AdminAgregar(restaurante).setVisible(true);
				dispose();
			}
		});
		botonAtras.setBounds(10, 227, 89, 23);
		contentPane.add(botonAtras);
		final JButton botonAgregarOtro = new JButton("Agregar otro ");
		botonAgregarOtro.addActionListener(new ActionListener() {
                        @Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == botonAgregarOtro){
					textMesa.setText("");
					textPedido.setText("");
				}	
			}
		});
		botonAgregarOtro.setBounds(308, 227, 116, 23);
		contentPane.add(botonAgregarOtro);
	}
	
	
}