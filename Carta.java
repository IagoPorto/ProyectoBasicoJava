/**
 * La clase Carta indica la información de cada carta: puntos, denominación y palo.
 */
public final class Carta implements Comparable <Carta>{

    private char denominacion; //1, 2, 3, 4, 5, 6 , 7, S, C, R
    private char palo; //B, E, C, O
    private int puntos; //1, 4, 5, 6, 7, 10
    public static final String charDeno = "124567SCR3";

    /***
     * Método constructor de la clase
     * @param representacion El parámetro representación se corresponde con la carta
     */
    public Carta (String representacion){
        denominacion = representacion.charAt(0);
        palo = representacion.charAt(1);
        calculaPuntos(denominacion);
    }

    /**
     * Método que devuelve la carta
     */
    public String toString(){
        return Character.toString(denominacion) + Character.toString(palo);
    }

    /**
     * Método que compara dos cartas para comprobar que no están repetidas
     */
    public int compareTo (Carta c){

        if (this.palo < c.palo) return -1;
        if (this.palo > c.palo) return 1;
        return charDeno.indexOf(this.denominacion) - charDeno.indexOf(c.denominacion);

    }
    
    /**
     * Método que devuelve la denominación de la carta
     * @return la denominación de la carta
     */
    public char getDenominacion(){
        return denominacion;
    }

    /**
     * Método que devuelve el palo de la carta
     * @return el palo de la carta
     */
    public char getPalo(){
        return palo;
    }

    /**
     * Método que devuelve los puntos de la carta
     * @return los puntos de la carta
     */
    public int getPuntos(){
        return puntos;
    }

    /**
     * Método que calcula los puntos de la carta
     * @param denominacion El parámetro denominación se corresponde con el valor de la carta, su denominación, al que se le asigna su valor real.
     */
    private void calculaPuntos (char denominacion){

        switch (denominacion){
            case '1':
                puntos = 1;
            break;
            case '2':
                puntos = 1;
            break;
            case'3':
                puntos = 10;
            break;
            case'R':
                puntos = 10;
            break;
            case'C':
                puntos = 10;
            break;
            case'S':
                puntos = 10;
            break;
            default:
                puntos = denominacion - '0';
            break;

        }

     }

}