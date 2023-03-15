import java.util.*;
import java.io.*;
// Nombres de las parejas y los jugadores
public final class Modo2 extends Modo{
    
    /*private String linea;
    private Ficheros f;
    private List<Jugador> listaJugadores = new ArrayList<Jugador>();*/
    private List<Carta> listaCartas = new ArrayList<Carta>();
    private boolean existeJugada;
    private int numJugadas = 0;
    private Scanner ficheroJugadores = null;

    public Modo2(Ficheros f){

        /*this.f = f;
        listaJugadores = list;*/
        super(f);
        autocompletar();
 

    }

    public Modo2(Ficheros f, Pareja pA, Pareja pB){

        super(f);
        listaParejas.add(pA);
        listaParejas.add(pB);
        listaJugadores.add(pA.getJ1());
        jugadores.put(pA.getJ1().getID(), pA.getJ1().getNombre());
        numJugadoresCreados++;
        listaJugadores.add(pA.getJ2());
        jugadores.put(pA.getJ2().getID(), pA.getJ2().getNombre());
        numJugadoresCreados++;
        listaJugadores.add(pB.getJ1());
        jugadores.put(pB.getJ1().getID(), pB.getJ1().getNombre());
        numJugadoresCreados++;
        listaJugadores.add(pB.getJ2());
        jugadores.put(pB.getJ2().getID(), pB.getJ2().getNombre());
        numJugadoresCreados++;

    }

    public Modo2(Ficheros f, String ficheroJugadores){

        super(f);

        try {
            this.ficheroJugadores = new Scanner(new File(ficheroJugadores));
			
		} catch (Exception ex) {
            System.out.println("Error");
            System.exit(-1);
        }

        siguienteLineaJugadores();
        

    }

    protected void siguienteLineaJugadores(){

        if(ficheroJugadores.hasNextLine()) {    // Leemos linea a linea el fichero
            linea = ficheroJugadores.nextLine(); 	 // Guardamos la linea en un String
        } else{
             linea = "Nada";
             ficheroJugadores.close();
        }

        if( linea != "Nada"){
            jugadorOPareja();
        }else autocompletar();
//???????????????
    }

    private void jugadorOPareja(){

        if (linea.charAt(0) == 'J'){
            crearJugador();
        }else obtenerPareja();

    }

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
            siguienteLineaJugadores();
        }

    }

    private boolean noEstaEnUnaPareja(int iD){

        if (listaParejas.get(0).getJ1().getID() == iD || listaParejas.get(0).getJ2().getID() == iD){
            return false;
        }else return true;

    }

    private void crearPareja(int iDJ1, int iDJ2, int iD, String nombre){

        listaJugadores.add(new Jugador(iDJ1, jugadores.get(iDJ1)));
        numJugadoresCreados++;
        listaJugadores.add(new Jugador(iDJ2, jugadores.get(iDJ2)));
        numJugadoresCreados++;
        listaParejas.add(new Pareja(listaJugadores.get(numJugadoresCreados - 2), listaJugadores.get(numJugadoresCreados - 1), iD, nombre));

    }

    protected void crearJugador(){//Integer.parseInt(linea.substring(2,contador + 1))
        
        String iD =  obtenerId(2,4);
        String nombre = leerUltimaPalabra(iD.length() + 3, linea);

        jugadores.put(Integer.parseInt(iD), nombre);
        siguienteLineaJugadores();
    }

    public void siguienteLinea(){

        linea = f.getLinea();
        if (linea != "Nada"){
            existeJugada = true;
            asignarCartas();
            if (numJugadas == 0){
                asignarMano();
            }
            numJugadas += 1;
            
        }else existeJugada = false;
        
    }

    public boolean getExisteJugada(){

        return existeJugada;

    }

    private void asignarCartas(){

        listaCartas = obtenerListaCartas();
        listaJugadores.get(0).setCartas(listaCartas.subList(0, 4));
        listaJugadores.get(2).setCartas(listaCartas.subList(4, 8));
        listaJugadores.get(1).setCartas(listaCartas.subList(8, 12));
        listaJugadores.get(3).setCartas(listaCartas.subList(12, 16));

    }

    private void asignarMano(){

        listaJugadores.get(0).setMano(linea.charAt(0));
        listaJugadores.get(2).setMano(linea.charAt(17));
        listaJugadores.get(1).setMano(linea.charAt(34));
        listaJugadores.get(3).setMano(linea.charAt(51));

    }

}