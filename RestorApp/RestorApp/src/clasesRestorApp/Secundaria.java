package clasesRestorApp;

public class Secundaria {
	
	public boolean validarNumeros(String cadena)
	{
	    if(cadena==null || cadena.equals("")==true) return false;
	    try
	    {
	        Integer.parseInt(cadena);
	        return true;
	    }
	    catch(NumberFormatException e)
	    {
	        return false;
	    }
	}
	public boolean validarRut(String rut)
	{
	    if(rut.equals("")==true) return false;
	    int ult= rut.length();
	    int largo = rut.length() -3;
	    int constante = 2;
	    int suma = 0;
	    int digito;
	        for (int i= largo; i >=0; i--)
	        {
	            if(validarNumeros(rut.substring(i,i+1))==true)
	            {
	                suma= suma + Integer.parseInt(rut.substring(i,i+1)) * constante;
	                constante = constante + 1 ;
	                if(constante == 8)
	                {
	                    constante =2; 
	                }
	            }
	        }
	    String ultimo = rut.substring(ult-1).toUpperCase();
	    digito =11 - (suma % 11);
	    if (digito==10 && ultimo.equals("K")==true)
	    {
	        return true;
	    }else 
	    { 
	        if(digito == 11 && ultimo.equals("0")){
	           return true;    
	        } 
	        else
	        {   if(validarNumeros(ultimo)==true)
	            {
	                if(digito == Integer.parseInt(ultimo))
	                {
	                    return true;
	                }
	            }
	        }     
	    }
	     return false;
	}
	public int contarCaracter(String string, String texto) {
		int cont=0;
		for(int i=0;i<string.length();i++)
		{
			if(string.substring(i,i+1).equals(texto)==true)
			{
				cont++;
			}
		}
		return cont;
	}
}