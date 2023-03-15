/**
 * Esta clase establece las dos parejas necesarias para una partida.
 */
public final class Pareja implements Comparable <Pareja>{

    private int iD;
    private String nombre;
    private int piedras = 0;
    private Jugador j1, j2;

    /**
     * Método constructor de la clase
     * @param j1 El parámetro j1 se corresponde con el primer jugador
     * @param j2 El parámetro j2 se corresponde con el segundo jugador
     * @param iD El parámetro iD se corresponde con el iD de la pareja
     * @param nombre El parámetro nombre se corresponde con el nombre de la pareja
     */
    public Pareja(Jugador j1, Jugador j2, int iD, String nombre){

        this.j1 = j1;
        this.j2 = j2;
        this.iD= iD;
        this.nombre = nombre;

    }

    /**
     * Método que devuelve el jugador1
     * @return jugador1
     */
    public Jugador getJ1(){
        return j1;
    }

    /**
     * Método que devuelve el jugador2
     * @return jugador2
     */
    public Jugador getJ2(){
        return j2;
    }

    /**
     * Método que devuelve el iD
     * @return el iD de la pareja
     */
    public int getID(){
        return iD;
    }

    /**
     * Método que modifica las piedras 
     * @param p El parámetro p se corresponde con las piedras, que va modificando en cada caso
     */
    public void setPiedras(int p){
        piedras += p;
    }

    /**
     * Método que devuelve el número de piedras
     * @return número de piedras
     */
    public int getPiedras(){
        return piedras;
    }

    /**
     * Método que devuelve el nombre de la pareja 
     * @return el nombre de la pareja
     */
    public String getNombre(){
        return nombre;
    }

/**
 * Método que comprueba que los iDs de las parejas no estén repetidos
 */
    public int compareTo (Pareja p){

        if(this.getID() < p.getID()){
            return -1;
        }else if(this.getID() > p.getID()){
            return 1;
        }else return 0;

    }

}