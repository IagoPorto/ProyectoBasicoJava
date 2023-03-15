/**
 * Esta clase gestiona el Modo1 del juego, juego autónomo. 
 */

public class Modo1 extends Modo{   
    
    /*private String linea;
    private Ficheros f;
    private Hashtable<Integer, String> jugadores = new Hashtable<Integer, String>();
    private List<Jugador> listaJugadores = new ArrayList<Jugador>();
    private List<Pareja> pareja = new ArrayList<Pareja>();
    private int numJugadoresCreados = 0; */

  /**
   * Es uno de los métodos constructores de la clase
   * @param f El parámetro f se corresponde con el fichero(?)
   */
    public Modo1(Ficheros f){
        
        super(f);
        siguienteLinea();
        f.cerrarEntrada();
        asignarMano();

    }

    
/**
 * Es uno de los métodos constructores de la clase(???????????????? en minimus)
 * @param salida 
 */
    public Modo1(String salida){
        
        super();
        f = new Ficheros(salida);
        autocompletar();
        asignarMano();

    }

    /**
     * Método (?)
     */
    protected void siguienteLinea(){

        linea = f.getLinea();
        if( linea != "Nada"){
            jugadorOPareja();
        }else autocompletar();

    }

    /**
     * Método que lee la primera letra de cada linea del fichero y comprueba si es un jugador (J) o una pareja (P). 
     */
    private void jugadorOPareja(){

        if (linea.charAt(0) == 'J'){
            crearJugador();
        }else obtenerPareja();

    }

/**
 * Método que crea el jugador extrayendo los datos dados por el fichero.
 */
    protected void crearJugador(){//Integer.parseInt(linea.substring(2,contador + 1))
        
        String iD =  obtenerId(2,4);
        String nombre = leerUltimaPalabra(iD.length() + 3, linea);

        jugadores.put(Integer.parseInt(iD), nombre);
        siguienteLinea();
    }

   /**
    * Método que en caso de que la línea de fichero empiece por P y obtiene los datos dados. 
    * Comprueba si el iD de los jugadores está en el mapa y si el número de parejas es cero, si es así crea una pareja con los datos obtenidos. 
    * Si la lista de parejas tiene una pareja y los iDs de los jugadores no están utilizados se crea una pareja (?????)
    * En otro caso, si la lista de parejas es menor que dos, se lee otra linea de fichero
    */
    private void obtenerPareja(){

        String iDPareja = obtenerId(2,4);
        String iDJ1 = obtenerId(iDPareja.length() + 3, iDPareja.length() + 5);
        String iDJ2 = obtenerId(iDJ1.length() + iDPareja.length() + 4, iDJ1.length() + iDPareja.length() + 6);
        String nombre = leerUltimaPalabra(iDJ2.length() + iDJ1.length() + iDPareja.length() + 5, linea);
        
        if(jugadores.containsKey(Integer.parseInt(iDJ1)) && jugadores.containsKey(Integer.parseInt(iDJ2)) && listaParejas.size() == 0){
            crearPareja(Integer.parseInt(iDJ1), Integer.parseInt(iDJ2), Integer.parseInt(iDPareja), nombre);
        }else {
            if( listaParejas.size() == 1 && noEstaEnUnaPareja(Integer.parseInt(iDJ1)) && noEstaEnUnaPareja(Integer.parseInt(iDJ2))){
                crearPareja(Integer.parseInt(iDJ1), Integer.parseInt(iDJ2), Integer.parseInt(iDPareja), nombre);
            }else siguienteLinea();
        }

        if(listaParejas.size() < 2){
            siguienteLinea();
        }

    }

    /**
     * Método que comprueba si el iD del jugador está o no utilizado
     * @param iD El parámetro iD es el iD que se le pasa para saber si está o no utilizado
     * @return true si no está utlizado, o false si si lo está
     */
    private boolean noEstaEnUnaPareja(int iD){

        if (listaParejas.get(0).getJ1().getID() == iD || listaParejas.get(0).getJ2().getID() == iD){
            return false;
        }else return true;

    }

    /**
     * Método que crea la pareja extrayendo los datos dados por el fichero 
     * @param iDJ1 El parámetro iDJ1 se corresponde con el iD del jugador uno
     * @param iDJ2 El parámetro iDJ2 se corresponde con el iD del jugador dos
     * @param iD El parámetro iD se corresponde con el iD de la pareja
     * @param nombre El parámetro nombre se corresponde con el nombre de la pareja
     */
    private void crearPareja(int iDJ1, int iDJ2, int iD, String nombre){

        listaJugadores.add(new Jugador(iDJ1, jugadores.get(iDJ1)));
        numJugadoresCreados++;
        listaJugadores.add(new Jugador(iDJ2, jugadores.get(iDJ2)));
        numJugadoresCreados++;
        listaParejas.add(new Pareja(listaJugadores.get(numJugadoresCreados - 2), listaJugadores.get(numJugadoresCreados - 1), iD, nombre));

    }

    /**
     * Método que establece el jugador mano. En la primera ronda es aleatorio y en las demás irá en orden (depende de quién fuese mano en la ronda anterior)
     */
    private void asignarMano(){     //metodo para establecer el jugador mano. En la 
//primera ronda es aleatorio en las demas ira en orden (depende de quien fuese mano en la ronda anterior)
        int numAleatorio = (int) (Math.random() * 4);
        if (numAleatorio == 0){
            listaParejas.get(0).getJ1().setMano('*');listaParejas.get(0).getJ2().setMano('-'); listaParejas.get(1).getJ1().setMano('-'); listaParejas.get(1).getJ2().setMano('-');
        }else if (numAleatorio == 1){            
            listaParejas.get(0).getJ1().setMano('-'); listaParejas.get(0).getJ2().setMano('-'); listaParejas.get(1).getJ1().setMano('*'); listaParejas.get(1).getJ2().setMano('-'); 
        }else if(numAleatorio == 2){
            listaParejas.get(0).getJ1().setMano('-'); listaParejas.get(0).getJ2().setMano('*'); listaParejas.get(1).getJ1().setMano('-'); listaParejas.get(1).getJ2().setMano('-');
        }else if(numAleatorio == 3){
            listaParejas.get(0).getJ1().setMano('-'); listaParejas.get(0).getJ2().setMano('-'); listaParejas.get(1).getJ1().setMano('-'); listaParejas.get(1).getJ2().setMano('*');
        }
        
    }

}