import java.io.PrintWriter;
import java.util.*;

/**
 * La clase Baraja se encarga de crear la baraja, barajar, ordenar, obtener cartas o restablecer la baraja.
 * Es decir, se encarga de cualquier acción relacionada con la baraja
 */
public final class Baraja{
    
    private List<Carta> listaCartas = new ArrayList<Carta>();
    private static final String[] palo = {"B","C","E","O"};
    private static final String[] denominacion = {"1","2","3","4","5","6","7","S","C","R"};

    /**
     * Constructor de la clase, qal que no se le pasan parámtros y llama al método crear para crear la baraja
     */
    public Baraja(){
        crear();
    }

    /**
     *  Método que crea la baraja
     */ 
    private void crear(){

        for (String p: palo){
            for(String d: denominacion){
                String representacion = d + p;
                listaCartas.add(new Carta(representacion));
            }
        }

    }

    /**
     * Método que devuelve la lista de cartas(?)
     * @return lista de cartas
     */
    public List<Carta> getListaCartas(){
        return listaCartas;
    }

    /**
     * Método que baraja las cartas
     */
    public void barajar(){

        Collections.shuffle(listaCartas);

    }

    /**
     * Método que obtiene un número de cartas de la baraja
     * @param n El parámetro n es el número de cartas a obtener de la baraja
     * @return la lista con las cartas
     */
    public List<Carta> obtener(int n){

        if (n < 1){
            List<Carta> cartas = null;
            return cartas;
        }
        //int contador = 0;
        List<Carta> cartas = new ArrayList<Carta>();
        //n2 = n;
        if (n > listaCartas.size()){
        }else{
            while (n != 0){
                cartas.add(new Carta(this.listaCartas.get(0).toString()));
                this.listaCartas.remove(0);
                n--;
                //contador++;
            }

        }
        
        return cartas;
    }

    /**
     * Método que restablece la baraja
     */
    public void restablecer(){

        listaCartas.clear();
        crear();

    } 

    /**
     * Método que ordena la baraja
     */
    public void ordenar(){
        Collections.sort(listaCartas);
    }

    /**
    * Método que devuelve la lista de cartas(?)
     */
    public String imprimir(List<Carta> c){

        String s = "( ";

        for(int i = 0; i < c.size(); i++){
            
            if((i + 1) != c.size()){
                s = s + c.get(i).toString() + ", ";
            }else s = s + c.get(i).toString();

        }

        s = s + ")";

        return s;

    }

}