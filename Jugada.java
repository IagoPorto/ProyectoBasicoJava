/**
 * La clase jugada establece una nueva jugada o lance, sea Grande, Chica, Pares o Juego
 */
public class Jugada{
    
    private String resultadoGrande;
    private String resultadoChica;
    private String resultadoPares;
    private String resultadoJuego;
    private Grande grande;
    private Chica chica;
    private Pares pares;
    private Juego juego;

    /**
     * Método constructor de la clase, crea una nueva jugada o lance y guarda el resultado en el atributo resultadoGrande
     */
    public void nuevaJugada(Pareja pA, Pareja pB){

        grande = new Grande(pA, pB);
        resultadoGrande = grande.getResultado();
        chica = new Chica(pA, pB);
        resultadoChica = chica.getResultado();
        pares = new Pares(pA, pB);
        resultadoPares = pares.getResultado();
        juego = new Juego(pA, pB);
        resultadoJuego = juego.getResultado();

    }

    /**
     * Método que devuelve el resultado del lance de grande
     * @return el resultado de lance de grande
     */
    public String getResGrande(){
        return resultadoGrande;
    }

    /**
     * Método que devuelve el resultado del lance de chica
     * @return el resultado de lance de chica
     */
    public String getResChica(){
        return resultadoChica;
    }

    /**
     * Método que devuelve el resultado del lance de Pares
     * @return el resultado de lance de pares
     */
    public String getResPares(){
        return resultadoPares;
    }

    /**
     * Método que retorna el resultado de lance de juego
     * @return el resultado de lance de juego
     */
    public String getResJuego(){
        return resultadoJuego;
    }

}