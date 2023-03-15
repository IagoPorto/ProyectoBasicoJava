import java.util.*;
/**
 * Clase que establece un jugador (?)
 */
public final class Jugador implements Comparable <Jugador>{
    
    private int iD;
    private int puntos;
    private String nombre;
    private boolean mano = false;
    private List<Integer> lance = new ArrayList<Integer>();
    private List<Carta> cartas = new ArrayList<Carta>();
    private static final String[] denominacion = {"1", "2", "3", "4", "5", "6", "7", "S", "C", "R"};


    /**
     * Método constructor de la clase
     * @param iD El parámetro iD se corresponde con el iD del jugador
     * @param nombre El parámetro nombre se corresponde con el nombre del jugador
     */
    public Jugador(int iD, String nombre){

        this.iD = iD;
        this.nombre = nombre;
  
    }

    /**
     * Método que modifica(?) el jugador mano
     * @param mano El parámetro mano se corresponde con el símbolo que tiene cada jugador en el fichero y con el cuál se indica quién es mano.
     */
    public void setMano(char mano){
        esMano(mano);
    }

    /**
     * 
     */
    public void setCartas(List<Carta> cartas){
        this.cartas = cartas;
        lance.clear();
        calculaLance();
        calculaPuntos();
    }

    /**
     * Método que devuelve una lista con las cartas
     * @return lista de cartas
     */
    public List<Carta> getCartas(){
        return cartas;
    }

    /**
     * Método que suma los puntos de las cartas de cada jugador y se los asigna al atributo puntos.
     */
    private void calculaPuntos(){
        puntos = cartas.get(0).getPuntos() + cartas.get(1).getPuntos() + cartas.get(2).getPuntos() + cartas.get(3).getPuntos();
    }

    /**
     * Método que devuelve el número de puntos de las cartas del jugador
     * @return número de puntos de las cartas de cada jugador
     */
    public int getPuntos(){
        return puntos;
    }

    /**
     * Método que devuelve una lista con los lances del jugador
     * @return lista con los lances de cada jugador
     */
    public List<Integer> getLance(){
        return lance;
    }

    /**
     * Método que devuelve el iD del jugador
     * @return el iD del jugador
     */
    public int getID(){
        return iD;
    }

    /**
     * Método que devuelve el nombre del jugador
     * @return el nombre del jugador
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Método que devuelve un true o un false indicando si es mano
     * @return true si es mano, false si no lo es
     */
    public boolean getMano(){
        return mano;
    }

    /**
     * Método que comprueba si el jugador es mano devolviendo un booleano, true si lo es, false en otro caso.
     * @param c El parámetro c se corresponde con el símbolo de cada jugador, que nos indica si es mano o no.
     */
    private void esMano(char c){
        if (c == '*'){
            mano = true;
        }else mano = false;
    }

    /**
     * Método
     */
    private void calculaLance(){

        int nVeces = 0;
        for (String d: denominacion){

            if (Character.toString(cartas.get(0).getDenominacion()).equals(d)){
                nVeces += 1;
            }
            if (Character.toString(cartas.get(1).getDenominacion()).equals(d)){
                nVeces += 1;
            }
            if (Character.toString(cartas.get(2).getDenominacion()).equals(d)){
                nVeces += 1;
            }
            if (Character.toString(cartas.get(3).getDenominacion()).equals(d)){
                nVeces += 1;
            }
            lance.add(nVeces);
            nVeces = 0;
        }
        nVeces = lance.get(0) + lance.get(1);
        lance.remove(0); lance.remove(0);
        lance.add(0, nVeces);
        nVeces = lance.get(1) + lance.get(8);
        lance.remove(1); lance.remove(7);
        lance.add(nVeces);
    }

    /**
     * Método que ordena las cartas
     */
    public void ordenar(){
        Collections.sort(cartas);
    }

    /**
     * Método que retorna las cartas del jugador
     * @return una cadena con las cartas del jugador
     */
    public String imprimir(){

        String c;
        if(mano){
            c = "*";
        }else c = "-";

        return c + "(" + cartas.get(0).toString() + ", " + cartas.get(1).toString() + ", " + cartas.get(2).toString() + ", " + cartas.get(3).toString() + ")"; 
    }

    /**
     * Método que compara los iDs de los jugadores para saber si se repiten
     */
    public int compareTo (Jugador j){

        if(this.getID() < j.getID()){
            return -1;
        }else if(this.getID() > j.getID()){
            return 1;
        }else return 0;

    }
}