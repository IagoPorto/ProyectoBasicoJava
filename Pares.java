import java.util.*;

/**
 * Esta clase realiza el lance de Pares, donde se cuenta el numero de pares de cada jugador. Los pares podrían ser: par, si hay dos cartas iguales,
 *  medias, si hay tres cartas iguales, y duplex si hay dos parejas de cartas iguales o cuatro cartas iguales.
 */

public class Pares{
    
    private int piedras;
    private String resultado;
    private int[] piedrasJugadores = new int[4]; //pJ1Pa, pJ1Pb, pJ2Pa, pJ2Pb; 

/**
 * Método constructor de lance de Pares 
 * @param pA Primer parámetro que se le pasa, que se corresponde con la primera pareja
 * @param pB Segundo Parámetro que se le pasa, que se corresponde con la segunda pareja
 */
    public Pares(Pareja pA, Pareja pB){
        ganadorPares(pA, pB);
    }

    /**
     * Método que devuelve el resultado del lance
     * @return el resultado de lance de Pares
     */
    public String getResultado(){
        return resultado;
    }

/**
 * Método que devuelve el numero de piedras de los jugadores
 * @return el número de piedras de cada jugador
 */
    public int[] getPiedrasJugadores(){
        return piedrasJugadores;
    }

    /**
     * Método que asigna la puntuación de pares a cada jugador según el par que hubiese tenido, calculado en el método resuelvePares.
     * @param pA se corresponde con la primera pareja
     * @param pB se corresponde con la segunda pareja
     */
    private void ganadorPares(Pareja pA, Pareja pB){

        int resultadoA = 0, resultadoB = 0;

        resuelvePares(pA.getJ1().getLance());                          //Pares J1P1
        pA.setPiedras(piedras);
        resultadoA += piedras;
        piedrasJugadores[0] = piedras;

        resuelvePares(pA.getJ2().getLance());                          //Pares J2P1
        pA.setPiedras(piedras);
        resultadoA += piedras;
        piedrasJugadores[2] = piedras;


        resuelvePares(pB.getJ1().getLance());                          //Pares J1P2
        pB.setPiedras(piedras);
        resultadoB += piedras;
        piedrasJugadores[1] = piedras;


        resuelvePares(pB.getJ2().getLance());                          //Pares J2P2
        pB.setPiedras(piedras);
        resultadoB += piedras;
        piedrasJugadores[3] = piedras;


        resultado = "Pares " + Integer.toString(resultadoA) + " " + Integer.toString(resultadoB);

    }

/**
 * Método que comprueba si existe alguno de los casos de pares, dúplex, medias o par. Es decir, comprueba si hay dos, tres, cuatro o dos parejas cartas iguales y asigna el resultado a piedras.
 * @param L1 lista de enteros con los lances del jugador
 */
    private void resuelvePares(List<Integer> L1){

        //par = "Nada";
        int num = 0;
        piedras = 0;

        for(int l: L1){
            if (l == 2){
                //par = "Par";
                num += 1;
                piedras = 1;
            }else if (l == 3){
                //par = "Medias";
                piedras = 2;
                break;
            }else if(l == 4){
                //par = "Duplex";
                piedras = 3;
                break;
            }
        }

        if (num == 2){
            //par = "Duplex";
            piedras = 3;
        }

    }
}