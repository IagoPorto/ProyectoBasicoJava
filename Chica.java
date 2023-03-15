import java.util.*;

/**
 * Esta clase realiza el lance de Chica, donde gana la pareja en la que uno de sus jugadores tiene las cartas de menor valor.
 */

public class Chica{

    private String resultadoChica;
    private int[] piedrasJugadores = new int[4];


/**
 * Es el constructor de la clase que accede al metodo ganadorChica para resolver el lance
 * @param pA Primera pareja que se le pasa al constructor
 * @param pB Segunda pareja que se le pasa al constructor
 */
    public Chica(Pareja pA, Pareja pB){

        ganadorChica(pA, pB);
    }


/**
 * Metodo que devuelve el numero de piedras de los jugadores
 * @return el numero de piedras de cada jugador en cada caso
 */
    public int[] getPiedrasJugadores(){
        return piedrasJugadores;
    }

/**
 * Método que devuelve el resultado del lance
 * @return el resultado de lance de Chica
 */
    public String getResultado(){
        return resultadoChica;
    }


/**
 * Método que asigna el numero de piedras de cada jugador según los jugadores que hayan tenido menos puntuación en ambas parejas, calculado con resuelveChica.
 * Y establece un resultado final según las piedras de cada jugador, que se corresponde con resultadoChica.
 * @param p1 se corresponde con la primera pareja
 * @param p2 se corresponde con la segunda pareja
 */
    private void ganadorChica(Pareja p1, Pareja p2){

        int lp1 = resuelveChica(p1.getJ1().getLance(), p1.getJ2().getLance());
        int lp2 = resuelveChica(p2.getJ1().getLance(), p2.getJ2().getLance());
        int lpfinal = 0;

        if (lp1 >= 0 && lp2 >= 0){
            lpfinal = resuelveChica(p1.getJ1().getLance(), p2.getJ1().getLance());
            if (lpfinal > 0){
                piedrasJugadores[0] = 3; piedrasJugadores[1] = 0; piedrasJugadores[2] = 0; piedrasJugadores[3] = 0;
            }else if (lpfinal < 0){
                piedrasJugadores[0] = 0; piedrasJugadores[1] = 3; piedrasJugadores[2] = 0; piedrasJugadores[3] = 0;
            }else{
                piedrasJugadores[0] = 1; piedrasJugadores[1] = 1; piedrasJugadores[2] = 0; piedrasJugadores[3] = 0;
            }
        }else if (lp1 < 0 && lp2 >= 0){
            lpfinal = resuelveChica(p1.getJ2().getLance(), p2.getJ1().getLance());
            if (lpfinal > 0){
                piedrasJugadores[0] = 0; piedrasJugadores[1] = 0; piedrasJugadores[2] = 3; piedrasJugadores[3] = 0;
            }else if (lpfinal < 0){
                piedrasJugadores[0] = 0; piedrasJugadores[1] = 3; piedrasJugadores[2] = 0; piedrasJugadores[3] = 0;
            }else{
                piedrasJugadores[0] = 0; piedrasJugadores[1] = 1; piedrasJugadores[2] = 1; piedrasJugadores[3] = 0;
            }
        }else if (lp1 >= 0 && lp2 < 0){
            lpfinal = resuelveChica(p1.getJ1().getLance(), p2.getJ2().getLance());
            if (lpfinal > 0){
                piedrasJugadores[0] = 3; piedrasJugadores[1] = 0; piedrasJugadores[2] = 0; piedrasJugadores[3] = 0;
            }else if (lpfinal < 0){
                piedrasJugadores[0] = 0; piedrasJugadores[1] = 0; piedrasJugadores[2] = 0; piedrasJugadores[3] = 3;
            }else{
                piedrasJugadores[0] = 1; piedrasJugadores[1] = 0; piedrasJugadores[2] = 0; piedrasJugadores[3] = 1;
            }
        }else if (lp1 < 0 && lp2 < 0){
            lpfinal = resuelveChica(p1.getJ2().getLance(), p2.getJ2().getLance());
            if (lpfinal > 0){
                piedrasJugadores[0] = 0; piedrasJugadores[1] = 0; piedrasJugadores[2] = 3; piedrasJugadores[3] = 0;
            }else if (lpfinal < 0){
                piedrasJugadores[0] = 0; piedrasJugadores[1] = 0; piedrasJugadores[2] = 0; piedrasJugadores[3] = 3;
            }else{
                piedrasJugadores[0] = 0; piedrasJugadores[1] = 0; piedrasJugadores[2] = 1; piedrasJugadores[3] = 1;
            }
        }

        if(lpfinal > 0){
            p1.setPiedras(3);
            resultadoChica = "Chica 3 0";
        }else if (lpfinal < 0){
            p2.setPiedras(3);
            resultadoChica = "Chica 0 3";
        }else{
            p1.setPiedras(1);
            p2.setPiedras(1);
            resultadoChica = "Chica 1 1";
        }

    }

    /**
     * Método que comprueba que jugador tiene mayor y menor puntuación y si hay empate
     * @param l1 lista de enteros con los lances del primer jugador que se quiere comparar
     * @param l2 lista de enteros con los lances del segundo jugador que se quiere comparar
     * @return un -1 si gana l2, un 0 si empatan y un 1 si gana l1
     */
    private int resuelveChica(List<Integer> l1, List<Integer> l2){//Devuelve -1 si ganna l2, 0 si empatan y 1 si gana l1.
        
        int resultado = 0;
        for (int i = 0; i <= 7; i++){
            if (l1.get(i) > l2.get(i)){
                resultado = 1;
                break;
            }else if (l1.get(i) < l2.get(i)){
                resultado = -1;
                break;
            }
        }
        return resultado;

    }
}