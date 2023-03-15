import java.util.*;

/**
 * la clase partida es la que establece y define la partida
 */
public final class Partida{
    
    private Jugada jugada = new Jugada();
    private int numJugadas = 0;
    private Pareja pA, pB;
    private Ficheros f;
    private String[] mano = new String[4]; //J1PA, J1PB, J2PA, J2PB
    private boolean esModo1 = false;
    private Baraja baraja;
    private Modo2 modo2;
    
/**
 * Es uno de los métodos constructores de la clase (?)
 * @param pA El parámetro pA se corresponde con la primera pareja
 * @param pB El parámetro pB se corresponde con la segunda pareja
 * @param f El parámetro f se corresponde con el fichero
 */
    public Partida(Pareja pA, Pareja pB, Ficheros f){

        esModo1 = true;
        inicializarBaraja();
        this.pA = pA;
        this.pB = pB;
        this.f = f;
        empezarPartida();
        siguienteJugada();

    }

    /**
     * Es uno de los métodos constructores de la clase (?)
     * @param f El paraámetro f se corresponde con el fichero
     * @param modo2 El parámetro modo2 se corresponde con el Modo2 de juego
     */
    public Partida(Ficheros f, Modo2 modo2){
        this.modo2 = modo2;
        pA = modo2.getListaParejas().get(0);
        pB = modo2.getListaParejas().get(1);
        this.f = f;
        siguienteJugada();
        //empezarPartida();
    }

    /**
     * Es uno de los métodos constructores de la clase(?)
     * @param pA El parámetro pA se corresponde con la primera pareja
     * @param pB El parámetro pB se corresponde con la segunda pareja
     * @param numeroJugadas El parámetro numeroJugadas se corresponde con el número de jugadas dadas
     * @param f El parámetro f se corresponde con el fichero
     */
    public Partida(Pareja pA, Pareja pB, int numeroJugadas, Ficheros f){

        this.f = f;
        this.pA = pA;
        this.pB = pB;
        inicializarBaraja();
        empezarPartida();
        do{

            asignarCartas();
            jugada.nuevaJugada(pA, pB);
            escribirJugada();
            numJugadas++;
            numeroJugadas--;

        }while (numeroJugadas != 0);

        finPartida();

    }

    private void empezarPartida(){
        f.imprimir(pA.getNombre() + ": " + pA.getJ1().getNombre() + " y " + pA.getJ2().getNombre() + ".\n" +
                   pB.getNombre() + ": " + pB.getJ1().getNombre() + " y " + pB.getJ2().getNombre() + ".");
        saberNombreMano();
        //siguienteJugada();
    }

    /**
     * Método que inicializa la baraja
     */
    private void inicializarBaraja(){

            baraja = new Baraja();
            baraja.barajar();

    }

    /**
     * Método que devuelve el número de jugadas
     * @return número de jugadas
     */
    public int getNumJugadas(){
        return numJugadas;
    }

    /**
     * Método que indica el nombre del jugador mano
     */
    private void saberNombreMano(){

        String nombre;
        if(pA.getJ1().getMano()){
            nombre = pA.getJ1().getNombre();
        }else if(pA.getJ2().getMano()){
            nombre = pA.getJ2().getNombre();
        }else if(pB.getJ1().getMano()){
            nombre = pB.getJ1().getNombre();
        }else  nombre = pB.getJ2().getNombre();

        f.imprimir("Mano: " + nombre + ".");

    }

    private void siguienteJugada(){

        //añadir cosas importantes
        if(esModo1){
            asignarCartas();
        }else {
            modo2.siguienteLinea();
            if (numJugadas == 0){
                empezarPartida();
            }else cambiarMano();
        }

        if (esModo1 || modo2.getExisteJugada()){
            jugada.nuevaJugada(pA, pB);
            numJugadas += 1;
            comprobarResultado();
        }else finPartidaIncompleta();

    }

    private void finPartidaIncompleta(){
        f.imprimir("Partida Incompleta. Número total de jugadas: " + numJugadas + ".");
        f.cerrarSalida();
    }

    private void comprobarResultado(){

        if (pA.getPiedras() > 39 || pB.getPiedras() > 39){
            finPartida();
        }else{
            escribirJugada();
            siguienteJugada();
        }

    }

    private void escribirJugada(){

        List<Carta> j1,j2,j3,j4;
        j1 = pA.getJ1().getCartas();
        j2 = pB.getJ1().getCartas();
        j3 = pA.getJ2().getCartas();
        j4 = pB.getJ2().getCartas();
        saberMano();

        f.imprimir(  mano[0] + "("+ j1.get(0).toString() + ", " + j1.get(1).toString() + ", " + j1.get(2).toString() + ", " + j1.get(3).toString() + ")"
                   + mano[1] + "("+ j2.get(0).toString() + ", " + j2.get(1).toString() + ", " + j2.get(2).toString() + ", " + j2.get(3).toString() + ")"
                   + mano[2] + "("+ j3.get(0).toString() + ", " + j3.get(1).toString() + ", " + j3.get(2).toString() + ", " + j3.get(3).toString() + ")"
                   + mano[3] + "("+ j4.get(0).toString() + ", " + j4.get(1).toString() + ", " + j4.get(2).toString() + ", " + j4.get(3).toString() + ")");
        f.imprimir(jugada.getResGrande() + " " + jugada.getResChica() + " " + jugada.getResPares() + " " + jugada.getResJuego() + " - " + Integer.toString(pA.getPiedras()) + " " + Integer.toString(pB.getPiedras()));

    }

    private void saberMano(){

        if(pA.getJ1().getMano()){
            mano[0] = "*"; mano[1] = "-"; mano[2] = "-"; mano[3] = "-";
        }else if(pA.getJ2().getMano()){
            mano[0] = "-"; mano[1] = "-"; mano[2] = "*"; mano[3] = "-";
        }else if(pB.getJ1().getMano()){
            mano[0] = "-"; mano[1] = "*"; mano[2] = "-"; mano[3] = "-";
        }else if(pB.getJ2().getMano()){
            mano[0] = "-"; mano[1] = "-"; mano[2] = "-"; mano[3] = "*";
        }

    }

    private void finPartida(){

        String parejaGanadora;
        escribirJugada();
        if (pA.getPiedras() > pB.getPiedras()){
            parejaGanadora = pA.getNombre();
        }else parejaGanadora = pB.getNombre();

        f.imprimir("Gana: " + parejaGanadora + ". " + "Número total de jugadas: " + numJugadas + ".");
        f.cerrarSalida();

    }

    private void cambiarMano(){

        if(pA.getJ1().getMano() == true){
            pA.getJ1().setMano('-'); pB.getJ1().setMano('*');
        }else if(pA.getJ2().getMano() == true){
            pA.getJ2().setMano('-'); pB.getJ2().setMano('*');
        }else if(pB.getJ1().getMano() == true){
            pA.getJ2().setMano('*'); pB.getJ1().setMano('-');
        }else if(pB.getJ2().getMano() == true){
            pA.getJ1().setMano('*'); pB.getJ2().setMano('-');
        }

    }

    /**
     * Método que asigna las cartas a cada jugador si la lista(baraja) tiene al menos 16 cartas
     */
    private void asignarCartas(){

        if(baraja.getListaCartas().size() > 15){
            pA.getJ1().setCartas(baraja.obtener(4));
            pA.getJ2().setCartas(baraja.obtener(4));
            pB.getJ1().setCartas(baraja.obtener(4));
            pB.getJ2().setCartas(baraja.obtener(4));
            if (numJugadas != 0){
                cambiarMano();
            }
        }else {
            baraja.restablecer();
            baraja.barajar();
            asignarCartas();
        }

    }

}