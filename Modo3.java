import java.util.*;
import java.io.*;

/**
 * Esta clase gestiona el Modo3 del juego, ejecución de comandos.
 */
public class Modo3 extends Modo{
 
    private PrintWriter salida;
    private Hashtable<Integer, Jugador> diccionarioJugadores = new Hashtable<Integer, Jugador>();

    /**
     * Método constructor de la clase(?)
     */
    public Modo3(Ficheros f){

        super(f);
        siguienteLinea();

    }

    /**
     * Método que obtiene la siguiente línea del fichero y comprueba si está o no vacía.
     */

    protected void siguienteLinea(){

        linea = f.getLinea();
        if(linea != "Nada"){
            obtenerComando();
        }else f.cerrarSalida();

    }

    /**
     * Método que obtiene la primera palabra de la linea leída y comprueba si se corresponde con algún caso de los descritos, si no es así pasa a la siguiente linea.
     */
    private void obtenerComando(){

        String[] partes = linea.split(" ");
        String comando = partes[0];
        switch(comando){
            case "NewPlayer": 
                crearJugador();
                break;
            case "DeletePlayer":
                eliminarJugador();
                break;
            case "NewCouple":
                crearPareja();
                break;
            case "DeleteCouple":
                eliminarPareja();
                break;
            case "DumpPlayers":
                generaFicheroJugadores();
                break;
            case "ResetPlayers":
                reseteaJugadores();
                break;
            case "LoadPlayers":
                cargarJugadores();
                break;
            case "GenerateRandomDelivery":
                generaFicheroJugadas();
                break;
            case "PlayGame":
                jugar();
                break;
            case "PlayHand":
                jugarMano();
                break;
            case "ResolvePares":
                pares();
                break;
            case "ResolveJuego":
                juego();
                break;
            case "ResolveGrande":
                grande();
                break;
            case "ResolveChica":
                chica();
                break;
        }

        siguienteLinea();

    }

    /**
     * Método que genera un fichero, de nombre dado, con el número de jugadas e iD del jugador mano dados
     */
    private void generaFicheroJugadas(){
        String[] parte = linea.split(" ");
        PrintWriter p = null;
        int i = Integer.parseInt(parte[1]);
        Baraja baraja = new Baraja();
        baraja.barajar();
        String[] mano = new String[4];
        List<Carta> cartas = new ArrayList<Carta>();
        try{
            p = new PrintWriter(new FileOutputStream(parte[3]));
        }catch(Exception ex){
            f.imprimir("GenerateRandomDelivery " + parte[1] + " " + parte[2] + " " + parte[3] + ": FAIL.");
            return;
        }

        if (Integer.parseInt(parte[2]) == 0){
            mano[0] = "*";mano[1] = "-"; mano[2] = "-"; mano[3] = "-";
        }else if (Integer.parseInt(parte[2]) == 1){
            mano[0] = "-";mano[1] = "-"; mano[2] = "*"; mano[3] = "-";
        }else if (Integer.parseInt(parte[2]) == 2){
            mano[0] = "-";mano[1] = "*"; mano[2] = "-"; mano[3] = "-";
        }else mano[0] = "-";mano[1] = "-"; mano[2] = "-"; mano[3] = "*";

        while(i != 0){

            cartas.clear();
            cartas.addAll(baraja.obtener(16));
            p.print(mano[0] + baraja.imprimir(cartas.subList(0,4)) + mano [1] + baraja.imprimir(cartas.subList(4,8)) + mano [2] + baraja.imprimir(cartas.subList(8,12)) + mano [3] + baraja.imprimir(cartas.subList(12,16)) + "\n");
            baraja.restablecer();
            baraja.barajar();
            i--;
            mano[0] = "-";mano[1] = "-"; mano[2] = "-"; mano[3] = "-";

        }
        p.close();
        
        f.imprimir("GenerateRandomDelivery " + parte[1] + " " + parte[2] + " " + parte[3] + ": OK.");

    }

    /**
     * Método que carga la información de jugadores y parejas almacenada en el fichero dado y las añade a la sesión
     */
    private void cargarJugadores(){

        Scanner nuevaEntrada;
        String nombre = leerUltimaPalabra(12, linea);
        String nuevaLinea;
        File fichero =  new File(nombre);
        try{
            
            nuevaEntrada = new Scanner(fichero);

        }catch(Exception ex){
            f.imprimir("LoadPlayers " + nombre + ": FAIL.");
            return;
        }

        listaJugadores.clear();
        listaParejas.clear();
        while(nuevaEntrada.hasNext()){
            nuevaLinea = nuevaEntrada.nextLine();
            String[] parte = nuevaLinea.split(" ");
            if (parte[0].equals("J")){
                parte[2] = leerUltimaPalabra(3 + parte[1].length(), nuevaLinea);
                listaJugadores.add(new Jugador(Integer.parseInt(parte[1]), parte[2]));
                diccionarioJugadores.put(Integer.parseInt(parte[1]), listaJugadores.get(listaJugadores.size()-1));
            }else if(parte[0].equals("P")){
                parte[4] = leerUltimaPalabra(5 +  parte[1].length() + parte[2].length() +  parte[3].length(), nuevaLinea);
                obtenerPareja(parte);
            }
        }
        nuevaEntrada.close();
        f.imprimir("LoadPlayers " + nombre + ": OK.");

    }

    /**
     * Método que comprueba que (?)
     * @param parte
     */
    private void obtenerPareja(String[] parte){

        
        if(noEstaEnUnaPareja(Integer.parseInt(parte[2])) && noEstaEnUnaPareja(Integer.parseInt(parte[3]))){
            crearPareja(Integer.parseInt(parte[2]), Integer.parseInt(parte[3]), Integer.parseInt(parte[1]), parte[4]);
        }

    }

    /**
     * Método que compara los iDs de los jugadores para saber si están utlizados en alguna pareja
     * @param iD El parámetro iD se le pasa para comprobar que no está en ninguna lista
     * @return un false si está utlizado o un true si no lo está
     */
    private boolean noEstaEnUnaPareja(int iD){

        boolean o = true;
        for (int i = 0; i < listaParejas.size(); i++){
            if (listaParejas.get(i).getJ1().getID() == iD || listaParejas.get(i).getJ2().getID() == iD){
                o = false;
            }
        }
        return o;

    }

    /**
     * Método que crea una pareja con los datos dados en el comando
     * @param iDJ1 El parámetro iDJ1 se corresponde con el iD del jugador1
     * @param iDJ2 El parámetro iDJ2 se corresponde con el iD del jugador2
     * @param iD El parámetro iD se corresponde con el iD de la pareja
     * @param nombre El parámetro nombre se corresponde con el nombre de la pareja
     */
    private void crearPareja(int iDJ1, int iDJ2, int iD, String nombre){

        
        listaParejas.add(new Pareja(diccionarioJugadores.get(iDJ1), diccionarioJugadores.get(iDJ2), iD, nombre));

    }

    /**
     * Método que juega una partida con el fichero dado y almacena el resultado en el otro fichero dado en el comando
     */
    private void jugar(){

        String[] nombresFicheros = linea.split(" ");
        String entrada = nombresFicheros[1];
        String salida = nombresFicheros[2];
        Ficheros fichero;
        Modo2 modo2;
        Partida partida;

        fichero = new Ficheros(entrada, salida, f, "PlayGame " + entrada + " " + salida + ": FAIL.");

        if(listaParejas.size() > 1){
            modo2 = new Modo2(fichero, listaParejas.get(listaParejas.size() - 2), listaParejas.get(listaParejas.size() - 1));
            partida = new Partida(fichero, modo2);
        }else{
            modo2 = new Modo2(fichero);
            partida = new Partida(fichero, modo2);
        }
        f.imprimir("PlayGame " + entrada + " " + salida + ": OK.");

    }

    /**
     * Método que resuleve los cuatro lances para la jugada dada, indicando con un asterisco el jugador mano.
     */
    private void jugarMano(){

        String jugada = leerUltimaPalabra(9, linea);
        
        List<Carta> cartas = new ArrayList<Carta>();

        autocompletar();
        int tamanho = listaParejas.size();
        
        listaParejas.get(tamanho - 2).getJ1().setMano(jugada.charAt(0));
        listaParejas.get(tamanho - 2).getJ2().setMano(jugada.charAt(34));
        listaParejas.get(tamanho - 1).getJ1().setMano(jugada.charAt(17));
        listaParejas.get(tamanho - 1).getJ2().setMano(jugada.charAt(51));
        cartas = obtenerListaCartas(jugada);
        listaParejas.get(tamanho - 2).getJ1().setCartas(cartas.subList(0, 4));
        listaParejas.get(tamanho - 2).getJ2().setCartas(cartas.subList(8, 12));
        listaParejas.get(tamanho - 1).getJ1().setCartas(cartas.subList(4, 8));
        listaParejas.get(tamanho - 1).getJ2().setCartas(cartas.subList(12, 16));

        Jugada juego = new Jugada();
        juego.nuevaJugada(listaParejas.get(tamanho - 2), listaParejas.get(tamanho - 1));

        f.imprimir("PlayHand " + jugada + ": " + juego.getResGrande() + ", " + juego.getResChica() + ", " + juego.getResPares() + ", " + juego.getResJuego());

    }

    /**
     * Método que ejecuta en un lance de chica para la jugada descrita en el comando, indicando los puntos de cada jugador y pareja
     */
    private void chica(){

        autocompletar();
        String jugada = leerUltimaPalabra(13, linea);
        int i = listaParejas.size();
        int tamanho = listaParejas.size();
        List<Carta> cartas = new ArrayList<Carta>();

        listaParejas.get(tamanho - 2).getJ1().setMano(jugada.charAt(0));
        listaParejas.get(tamanho - 2).getJ2().setMano(jugada.charAt(34));
        listaParejas.get(tamanho - 1).getJ1().setMano(jugada.charAt(17));
        listaParejas.get(tamanho - 1).getJ2().setMano(jugada.charAt(51));
        cartas = obtenerListaCartas(jugada);
        listaParejas.get(tamanho - 2).getJ1().setCartas(cartas.subList(0, 4));
        listaParejas.get(tamanho - 2).getJ2().setCartas(cartas.subList(8, 12));
        listaParejas.get(tamanho - 1).getJ1().setCartas(cartas.subList(4, 8));
        listaParejas.get(tamanho - 1).getJ2().setCartas(cartas.subList(12, 16));
        Chica p = new Chica(listaParejas.get(i -2), listaParejas.get(i -1));
        int[] piedras = p.getPiedrasJugadores();

        f.imprimir("ResolveChica " + jugada + ": " + Integer.toString(piedras[0]) + " " + Integer.toString(piedras[1]) + " " + Integer.toString(piedras[2])+ " " + Integer.toString(piedras[3]) + " - " + Integer.toString(piedras[0] + piedras[2]) + " " + Integer.toString(piedras[1] + piedras[3]));

    }

    /**
     * Método que ejecuta un lance de grande para la jugada descrita en el comando, indicando los puntos de cada jugador y pareja
     */
    private void grande(){
        autocompletar();
        String jugada = leerUltimaPalabra(14, linea);
        int i = listaParejas.size();
        int tamanho = listaParejas.size();
        List<Carta> cartas = new ArrayList<Carta>();

        listaParejas.get(tamanho - 2).getJ1().setMano(jugada.charAt(0));
        listaParejas.get(tamanho - 2).getJ2().setMano(jugada.charAt(34));
        listaParejas.get(tamanho - 1).getJ1().setMano(jugada.charAt(17));
        listaParejas.get(tamanho - 1).getJ2().setMano(jugada.charAt(51));
        cartas = obtenerListaCartas(jugada);
        listaParejas.get(tamanho - 2).getJ1().setCartas(cartas.subList(0, 4));
        listaParejas.get(tamanho - 2).getJ2().setCartas(cartas.subList(8, 12));
        listaParejas.get(tamanho - 1).getJ1().setCartas(cartas.subList(4, 8));
        listaParejas.get(tamanho - 1).getJ2().setCartas(cartas.subList(12, 16));
        Grande p = new Grande(listaParejas.get(i -2), listaParejas.get(i -1));
        int[] piedras = p.getPiedrasJugadores();

        f.imprimir("ResolveGrande " + jugada + ": " + Integer.toString(piedras[0]) + " " + Integer.toString(piedras[1]) + " " + Integer.toString(piedras[2])+ " " + Integer.toString(piedras[3]) + " - " + Integer.toString(piedras[0] + piedras[2]) + " " + Integer.toString(piedras[1] + piedras[3]));

    }

    /**
     * Método que ejecuta un lance de juego para la jugada descrita en el comando, indicando los puntos de cada jugador y pareja
     */
    private void juego(){
        autocompletar();
        String jugada = leerUltimaPalabra(13, linea);
        int i = listaParejas.size();
        int tamanho = listaParejas.size();
        List<Carta> cartas = new ArrayList<Carta>();

        listaParejas.get(tamanho - 2).getJ1().setMano(jugada.charAt(0));
        listaParejas.get(tamanho - 2).getJ2().setMano(jugada.charAt(34));
        listaParejas.get(tamanho - 1).getJ1().setMano(jugada.charAt(17));
        listaParejas.get(tamanho - 1).getJ2().setMano(jugada.charAt(51));
        cartas = obtenerListaCartas(jugada);
        listaParejas.get(tamanho - 2).getJ1().setCartas(cartas.subList(0, 4));
        listaParejas.get(tamanho - 2).getJ2().setCartas(cartas.subList(8, 12));
        listaParejas.get(tamanho - 1).getJ1().setCartas(cartas.subList(4, 8));
        listaParejas.get(tamanho - 1).getJ2().setCartas(cartas.subList(12, 16));
        Juego p = new Juego(listaParejas.get(i -2), listaParejas.get(i -1));
        int[] piedras = p.getPiedrasJugadores();

        f.imprimir("ResolveJuego " + jugada + ": " + Integer.toString(piedras[0]) + " " + Integer.toString(piedras[1]) + " " + Integer.toString(piedras[2])+ " " + Integer.toString(piedras[3]) + " - " + Integer.toString(piedras[0] + piedras[2]) + " " + Integer.toString(piedras[1] + piedras[3]));

    }

    /**
     * Método que ejecuta un lance de pares para la jugada descrita en el comando, indicando los puntos de cada jugador y pareja
     */
    private void pares(){
        autocompletar();
        String jugada = leerUltimaPalabra(13, linea);
        int i = listaParejas.size();
        int tamanho = listaParejas.size();
        List<Carta> cartas = new ArrayList<Carta>();

        listaParejas.get(tamanho - 2).getJ1().setMano(jugada.charAt(0));
        listaParejas.get(tamanho - 2).getJ2().setMano(jugada.charAt(34));
        listaParejas.get(tamanho - 1).getJ1().setMano(jugada.charAt(17));
        listaParejas.get(tamanho - 1).getJ2().setMano(jugada.charAt(51));
        cartas = obtenerListaCartas(jugada);
        listaParejas.get(tamanho - 2).getJ1().setCartas(cartas.subList(0, 4));
        listaParejas.get(tamanho - 2).getJ2().setCartas(cartas.subList(8, 12));
        listaParejas.get(tamanho - 1).getJ1().setCartas(cartas.subList(4, 8));
        listaParejas.get(tamanho - 1).getJ2().setCartas(cartas.subList(12, 16));
        Pares p = new Pares(listaParejas.get(i -2), listaParejas.get(i -1));
        int[] piedras = p.getPiedrasJugadores();

        f.imprimir("ResolvePares " + jugada + ": " + Integer.toString(piedras[0]) + " " + Integer.toString(piedras[1]) + " " + Integer.toString(piedras[2])+ " " + Integer.toString(piedras[3]) + " - " + Integer.toString(piedras[0] + piedras[2]) + " " + Integer.toString(piedras[1] + piedras[3]));

    }

    /**
     * Método que borra la información de jugadores y parejas generada en la sesión
     */
    private void reseteaJugadores(){
        listaJugadores.removeAll(listaJugadores);
        listaParejas.removeAll(listaParejas);
        f.imprimir("ResetPlayers: OK.");
    }

    /**
     * Método que genera un fichero con los jugadores y parejas creadas en la sesión, ordenando primero los jugadores y luego las parejas por orden de identificador 
     */
    private void generaFicheroJugadores(){

        String nombre = leerUltimaPalabra(12,linea);

        //if (nombre == )

        try{
            salida = new PrintWriter(new FileOutputStream(nombre));
        }catch (Exception ex) {
            f.imprimir("DumpPlayers " + nombre + ": FAIL.");
            return;
        }

        
        Collections.sort(listaJugadores);
        Collections.sort(listaParejas);
        for (int i = 0; i < listaJugadores.size(); i++){
            salida.println("J " + listaJugadores.get(i).getID() + " " + listaJugadores.get(i).getNombre());
        }
        for (int i = 0; i < listaParejas.size(); i++){
            salida.println("P " + listaParejas.get(i).getID() + " " + listaParejas.get(i).getJ1().getID() + " " + listaParejas.get(i).getJ2().getID() + " " + listaParejas.get(i).getNombre());
        }
        f.imprimir("DumpPlayers " + nombre + ": OK.");
        salida.close();
        

    }

    /**
     * Método que elimina la pareja con el iD dado.
     */
    private void eliminarPareja(){

        String iD = leerUltimaPalabra(13,linea);
        if(existeIdPareja(Integer.parseInt(iD), listaParejas)){
            int i = obtenerIndicePareja(Integer.parseInt(iD), listaParejas);
            listaParejas.remove(i);
            f.imprimir("DeleteCouple " + iD + ": OK.");
        }else f.imprimir("DeleteCouple " + iD + ": FAIL.");
    }

    /**
     * Método que crea la pareja con los datos dados, iD, iD de los jugadores y el nombre.
     */
    private void crearPareja(){

        String iD = obtenerId(10,12);
        String iDJ1 = obtenerId(11 + iD.length(), 13 + iD.length());
        String iDJ2 = obtenerId(12 + iD.length() + iDJ1.length(), 14 + iD.length() + iDJ1.length());
        String nombre = leerUltimaPalabra(13 + iD.length() + iDJ1.length() + iDJ2.length(), linea);

        if(!existeIdPareja(Integer.parseInt(iD), listaParejas) && existeIdJugador(Integer.parseInt(iDJ1), listaJugadores) && existeIdJugador(Integer.parseInt(iDJ2), listaJugadores) && !iDJ1.equals(iDJ2)){
            int i = obtenerIndiceJugador(Integer.parseInt(iDJ1), listaJugadores);
            int j = obtenerIndiceJugador(Integer.parseInt(iDJ2), listaJugadores);
            listaParejas.add(new Pareja(listaJugadores.get(i), listaJugadores.get(j), Integer.parseInt(iD), nombre));
            f.imprimir("NewCouple " + iD + " " + iDJ1 + " "  + iDJ2 + " "  + nombre + ": OK.");
        }else f.imprimir("NewCouple " + iD + " "  + iDJ1 + " "  + iDJ2 + " "  + nombre + ": FAIL.");

        

    }

    /**
     * Método que elimina el jugador con el iD dado
     */
    private void eliminarJugador(){

        int i;
        String iD = leerUltimaPalabra(13, linea);
        if(existeIdJugador(Integer.parseInt(iD), listaJugadores)){
            i = obtenerIndiceJugador(Integer.parseInt(iD), listaJugadores);
            listaJugadores.remove(i);
            f.imprimir("DeletePlayer " + iD + ": OK.");
        }else f.imprimir("DeletePlayer " + iD + ": FAIL.");
    }

    /**
     * Método que obtiene el índice del jugador con el iD dado
     * @param iD El parámetro iD se corresponde con el iD del jugador
     * @param l El parámetro l es la lista de jugadores
     * @return el número del jugador que se corresponde con ese iD en la lista
     */
    private int obtenerIndiceJugador(int iD, List<Jugador> l){

        int k = 0;

        for(int i = 0; i < l.size(); i++){
            if (l.get(i).getID() == iD){
                k = i;
            }
        }
        return k;
    }

    /**
     * Método que obtiene el índice de la pareja con el iD dado
     * @param iD El parámetro iD se corresponde con el iD de la pareja
     * @param l El parámetro l se corresponde con la lista de jugadores
     * @return el número de la pareja que se corresponde con el iD en la lista 
     */
    private int obtenerIndicePareja(int iD, List<Pareja> l){

        int k = 0;

        for(int i = 0; i < l.size(); i++){
            if (l.get(i).getID() == iD){
                k = i;
            }
        }
        
        return k;

    }

    /**
     * Método que crea el jugador con los datos dados
     */
    protected void crearJugador(){

        String iD = obtenerId(10,12);
        String nombre = leerUltimaPalabra(iD.length() + 11, linea);

        if(listaJugadores.size() == 0 || !existeIdJugador(Integer.parseInt(iD), listaJugadores)){
            listaJugadores.add(new Jugador(Integer.parseInt(iD), nombre));
            diccionarioJugadores.put(Integer.parseInt(iD), new Jugador(Integer.parseInt(iD), nombre));
            f.imprimir("NewPlayer " + iD + " " + nombre + ": OK.");
        }else f.imprimir("NewPlayer " + iD + " " + nombre + ": FAIL.");
        
        

    }

    /**
     * Método que comprueba si existe el iD dado en la lista de jugadores
     * @param iD El parámetro iD se corresponde con el iD del jugador 
     * @param l El parámetro l se corresponde con la lista de jugadores
     * @return true si el iD dado se corresponde con algún iD de algún jugador de la lista,  devuelve false en otro caso
     */
    private boolean existeIdJugador(int iD, List<Jugador> l){

        boolean b = false;

        for(int i = 0; i < l.size(); i++){
            if (iD == l.get(i).getID()){
                b = true;
            }
        }
        
        return b;

    }

    /**
     * Método que comprueba si existe el iD en la lista de parejas
     * @param iD El parámetro iD se corresponde con el iD de la pareja
     * @param l El parámetro l se corresponde con la lista de parejas
     * @return true si el iD se corresponde con el iD de alguna pareja de la lista, devuelve false en otro caso
     */
    private boolean existeIdPareja(int iD, List<Pareja> l){

        boolean b = false;

        for(int i = 0; i < l.size(); i++){
            if (iD == l.get(i).getID()){
                b = true;
            }
        }

        return b;

    }

}