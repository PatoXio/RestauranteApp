package Ventanas;

import java.awt.Font;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import clasesRestorApp.Restaurante;



public class Ventana extends JFrame implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Alguna  de las siguientes clases se usaran en las siguientes clases:Ventana,VentanaAdmin
	/*
	* JPanel: Clase que genera un contenedor al cual se le puede colocar elementos y acomodarlos a su conveniencia
	* JLabel: Clase que da una etiqueta a la cual se le puede asignar un texto y sus dimensiones (si es que no existe algun tipo de Layout).
    * 
    * JTextField: Es una clase que provee un cuadro de texto en el que el usuario puede ingresar valores.
    * 
    * JPasswordField: Clase que produce cuadro de texto exclusivo para contraseas en el que el usuario puede ingresar valores
    * 				(lo que ingrese el usuario no sera visible).
    * 
    * JButton: Clase que crea un boton al que se le puede asignar un texto y una accion para cuando se presione.
    * 
    * JFrame: Clase que instancia la ventana en la cual se mostraran todos los componentes que uno le agregue.
    * 
    * Restaurante: Es la clase que contiene los datos de todo el local y sus funciones.
	*  
	*/
	private JPanel contentPane;  
	private JTextField textusua;
	private JPasswordField textpass;
	private JLabel titulo;
	private JLabel usuario;
	private JLabel pass;
	private JButton botonIngresar;
	private Restaurante restaurante;
	
	public Ventana(Restaurante restaurante){
		super();							//Es el constructor de un JFrame, basicamente aqui se crea la ventana con valores predeterminados.
		configurarVentana();				//Llamamos a este metodo para configurar la ventana (el JFrame).
		this.setLocationRelativeTo(null);   //Ubicamos la ventana por defecto en el centro de la pantalla.
		inicializarComponentes();			//Llamamos a este metodo para inicializar todos los componentes de la ventana y agregarlos a esta.
		this.restaurante = restaurante;		//Iniciamos restaurante con la clase empresa que el usuario o la respectiva clase nos entregue.
	}
	private void configurarVentana(){
		setTitle("RestorApp");								// Le otorga el texto que se vera en la parte de arriba de la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// Hacemos que cuando se cierre la ventana termina todo el proceso
		setBounds(100, 100, 450, 300);						//Mueve y cambia el tamaï¿½o de un componente para caber dentro de los parametros del panel(PosXEnPanel,PosYEnPanel,LargoComponente,AnchoComponente)
		contentPane = new JPanel();							//instanciando el contenedor para su posterior uso
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); //Pixeles que ocupara el contenedor(Superior,inferior,izquierda,derecha)
		setContentPane(contentPane);						//Establece el contentPane a utilizar.
		contentPane.setLayout(null);						//Establece la forma en que se mostraran los diversos objetos aï¿½adidos
		
		
	}
	private void inicializarComponentes(){
		/// Se instancia cada uno de los componentes de la ventana
		titulo = new JLabel();
		usuario = new JLabel();
		textusua = new JTextField();
		pass = new JLabel();
		textpass = new JPasswordField();
		botonIngresar = new JButton();
		
	/*	Los siguientes metodos han sido usados en las siguientes clases: Ventana, VentanaAdmin, 
	 * 
	 * .setText(String texto): Le asigna el texto entregado al componente sobreescribiendo el actual. 
	 * 
	 * .getText(): Da el texto en String dentro del componente especificado(En este caso de JTextField y JPassWordField).
	 * 
	 * .setBounds(x,y,largo,alto): Se modifica la posicion (x, y como en un plano cartesiano) del componente y sus medidas (largo y alto).
	 * 
	 * .addActionListener(ActionListener e): Le agrega al componente un "ActionListener", este puede ser un click, alguna tecla, etc.
	 * 										Se puede asignar lo que ocurre en el con un @Override public void actionPerformed(ActionEvent e).
	 * 
	 * e.getSource(): Metodo usado con un ActionEvent (mencionado anteriormente), este da el objeto el cual activo el evento, por ejemplo
	 * 				  si es que se presiona un boton, e.getSource dara el objeto JButton presionado.
	 *															
	 *  contentPane.add(Object): Agrega un objeto al contenedor, en este caso los componentes de la ventana.
	 *  
	 *  setFont(TipoLetra,EstiloFuente,Tamaï¿½oLetra): Es la fuente de texto que tendra el componente especifico
	 *  											Debe proveerse tipografia, estilo de fuente y el tamaï¿½o de ï¿½sta.
	 *  
	 *  .getText(): Se obtienen los valores que se encuentran en el respectivo componente (ya sea JTextField, JComboBox o JPasswordField).
	 * 
	 * .setVisible(boolean): Se establece la visibilidad del objeto, siendo true->visible y false->invisible, generalmente las ventanas (JFrame)
	 * 						parten invisibles, por lo que al iniciarse, se les debe hacer un .setVisible(true).	
	 * 
	 * .dispose(): Libera recursos eliminando la(s) ventana(s) y sus componentes.
	 */
		
		
		
		// Modificamos los parametros para darle sentido a la ventana.
		titulo.setText("Bienvenido a RestorApp");
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		titulo.setBounds(127, 24, 165, 14);
		usuario.setText("Usuario");
		usuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		usuario.setBounds(107, 84, 46, 31);
		textusua.setBounds(185, 88, 143, 27);
		pass.setText("Password");
		pass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pass.setBounds(107, 147, 74, 14);
		textpass.setBounds(185, 144, 143, 27);
		botonIngresar = new JButton("Ingresar");
		botonIngresar.setBounds(127, 211, 201, 23);
		botonIngresar.addActionListener(this);
		contentPane.add(titulo);
		contentPane.add(usuario);
		contentPane.add(textusua);
		contentPane.add(textpass);
		contentPane.add(pass);
		contentPane.add(botonIngresar);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String usuarioIngresado = textusua.getText();
		@SuppressWarnings("deprecation")
		String passIngresado = textpass.getText();
		// si el texto ingresado tanto en el usuario como en password coinciden con las del administrador, entonces libera los recursos actuales y pasa al menu del administrador 
		if(usuarioIngresado.equals("admin")&& passIngresado.equals("admin")){
			this.setVisible(false);
			VentanaAdmin ventanaAdmin =new VentanaAdmin(restaurante);
			ventanaAdmin.setVisible(true);
		}
		//Si no coincide, entonces se debe volver a ingresar los datos.
		else if(!usuarioIngresado.equals("admin") || !passIngresado.equals("admin")){
			JOptionPane.showMessageDialog(null, "El usuario o la contraseña ingresadas no existen");
			textusua.setText("");
			textpass.setText("");
		}
	}
}
	
	
	
	
	
	

