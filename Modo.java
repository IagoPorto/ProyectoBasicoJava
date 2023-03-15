import java.util.*;

public class Modo{

    protected List<Jugador> listaJugadores = new ArrayList<Jugador>();
    protected List<Pareja> listaParejas = new ArrayList<Pareja>();
    protected Hashtable<Integer, String> jugadores = new Hashtable<Integer, String>();
    protected int numJugadoresCreados = 0;
    protected String linea;
    protected Ficheros f;
    
    public Modo(Ficheros f){
        this.f = f;
    }

    public Modo(){
        
    }

    public Ficheros getFichero(){
        return f;
    }

    
    protected String leerUltimaPalabra(int inicio, String linea){

        return linea.substring(inicio, linea.length());

    }

    protected String obtenerId(int inicio, int ultimo){

        int contador = inicio;

        for (int i = inicio + 1; i <= ultimo; i++){

            if (linea.charAt(i) == ' '){
                break;
            }else contador++;

        }

        return linea.substring(inicio, contador + 1);

    }

    protected void siguienteLinea(){

    }

    protected void crearJugador(){

    }
    
    public List<Jugador> getListaJugadores(){
        return listaJugadores;
    } 

    public List<Pareja> getListaParejas(){
        return listaParejas;
    }

    protected void autocompletar(){

        int iD = 0;
        
        if (listaParejas.size() == 1){
            completarJugador(iD, "Jugador 1B");
            completarJugador(iD, "Jugador 2B");
            completarPareja("Pareja B", true);
        }else{
            completarJugador(iD, "Jugador 1A");
            completarJugador(iD, "Jugador 2A");
            completarPareja("Pareja A", false);
            completarJugador(iD, "Jugador 1B");
            completarJugador(iD, "Jugador 2B");
            completarPareja("Pareja B", true);
        }

    }

    protected void completarJugador(int iD, String nombre){

        boolean b;
        do{
            b = jugadores.containsKey(iD);
            if (b){
                iD++;
            }
        }while (b == true);

        listaJugadores.add(new Jugador(iD,nombre));
        jugadores.put(iD, nombre);
        numJugadoresCreados++;

    }

    protected void completarPareja(String nombre, boolean existe){

        int iD;
        if (existe){
            if (listaParejas.get(0).getID() == 999){
                iD = 998;
            }else iD = listaParejas.get(0).getID() + 1;
            
        }else iD = 1;

        listaParejas.add(new Pareja(listaJugadores.get(numJugadoresCreados - 2), listaJugadores.get(numJugadoresCreados - 1), iD, nombre));
    }

    
    protected List<Carta> obtenerListaCartas(){                //Generación de las 16 cartas

        List<Carta> listaCartas = new ArrayList<Carta>();

        listaCartas.clear();
        int contador = 2;
        for (int j = 0; j < 16; j++){ 
            listaCartas.add(new Carta(linea.substring(contador,contador+2)));
            contador += 4;
            if ((j+1) % 4 == 0){contador++;}
        }

        return listaCartas;

    } 

    protected List<Carta> obtenerListaCartas(String jugada){                //Generación de las 16 cartas

        List<Carta> listaCartas = new ArrayList<Carta>();

        listaCartas.clear();
        int contador = 2;
        for (int j = 0; j < 16; j++){ 
            listaCartas.add(new Carta(jugada.substring(contador,contador+2)));
            contador += 4;
            if ((j+1) % 4 == 0){contador++;}
        }

        return listaCartas;

    } 




}