import java.io.*;
import java.util.*;

/**
 * La clase ficheros gestiona los ficheros de entrada y salida y los errores que puedan existir.
 */
public final class Ficheros{

    private PrintWriter salida = null;
    private Scanner entrada = null;
    private String linea = null;
    private File fichero = null;

    /**
     * Es uno de los métodos constructores de la clase
     * @param entrada El parámetro entrada se corresponde con el nombre del fichero de entrada
     * @param salida El parámetro de salida se corresponde con el nombre del fichero de salida
     */
    public Ficheros (String entrada, String salida){

        fichero =  new File(entrada);

        try {
            this.entrada = new Scanner(fichero); 
            this.salida = new PrintWriter(new FileOutputStream(salida));
			
		} catch (Exception ex) {
            System.out.println("Error");
            System.exit(-1);
        }

    }

    /**
     * Es uno de los métodos constructores de la clase
     * @param salida El parámetro salida se corresponde con el nombre del fichero de salida
     */
    public Ficheros (String salida){

        
        try {

            this.salida = new PrintWriter(new FileOutputStream(salida));
			
		} catch (Exception ex) {
            System.out.println("Error");
            System.exit(-1);
        }

    }

    /**
     * Es uno de los métodos constructores de la clase
     * @param entrada El parámetro entrada se corresponde con el nombre del fichero de entrada 
     * @param p El parámetro p 
     */
    public Ficheros(String entrada, PrintWriter p){

        fichero =  new File(entrada);

        try{
            this.entrada = new Scanner(fichero); 
            this.salida = p;
        }catch(Exception ex){
            this.entrada.close();
        }
    }

    public Ficheros (String entrada, String salida, Ficheros f, String error){

        
        fichero =  new File(entrada);

        try {
            this.entrada = new Scanner(fichero); 
            this.salida = new PrintWriter(new FileOutputStream(salida));
			
		} catch (Exception ex) {
            f.imprimir(error);
        }


    }

    /**
     * Método que comprueba si existe la siguiente linea
     * @return la línea
     */
    public String getLinea(){

        if(this.entrada.hasNextLine()) {    // Leemos linea a linea el fichero
            linea = this.entrada.nextLine(); 	 // Guardamos la linea en un String
        } else{
             linea = "Nada";
             cerrarEntrada();
        }

        return linea;
    }

    /**
     * Método que imprime la salida (?)
     * @param s El parámetro s se corresponde con la cadena dada
     */
    public void imprimir(String s){
        salida.println(s);
    }

    /**
     * Método que cierra el fichero de entrada
     */
    public void cerrarEntrada(){
        entrada.close();
    }

    /**
     * Método que cierra el fichero de salida
     */
    public void cerrarSalida(){
        salida.close();
    }

}