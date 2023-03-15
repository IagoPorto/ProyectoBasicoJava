import java.util.*;

public class Juego{
    
    private List<Integer> orden = new ArrayList<Integer>();   //{31, 32, 40, 37, 36, 35, 34, 33};
    private Pareja pA, pB;
    private String resultado;
    private int[] piedrasJugadores = new int[4];

    public Juego(Pareja pA, Pareja pB){

        this.pA = pA; this.pB = pB;
        orden.add(31); orden.add(32); orden.add(40); orden.add(37); orden.add(36); orden.add(35); orden.add(34); orden.add(33);
        resuelveJuego();

    }

    public int[] getPiedrasJugadores(){
        return piedrasJugadores;
    }

    public String getResultado(){
        return resultado;
    }

    private void resuelveJuego(){

        int ganadorPA; int ganadorPB; int ganador;
        ganadorPA = quienTieneMejorJuego(pA.getJ1().getPuntos(), pA.getJ2().getPuntos());
        ganadorPB = quienTieneMejorJuego(pB.getJ1().getPuntos(), pB.getJ2().getPuntos());

        if (ganadorPA == 8 && ganadorPB != 8){
            if(ganadorPB > 0){
                ganador = -1;
                piedrasJugadores[0] = 0; piedrasJugadores[1] = 2; piedrasJugadores[2] = 0; piedrasJugadores[3] = 0;
                saberGanador(ganador, 2);
                return;
            }else{
                ganador = -1;
                piedrasJugadores[0] = 0; piedrasJugadores[1] = 0; piedrasJugadores[2] = 0; piedrasJugadores[3] = 2;
                saberGanador(ganador, 2);
                return;
            }
        }else if (ganadorPB == 8 && ganadorPA != 8){
            if(ganadorPA > 0){
                ganador = 1;
                piedrasJugadores[0] = 2; piedrasJugadores[1] = 0; piedrasJugadores[2] = 0; piedrasJugadores[3] = 0;
                saberGanador(ganador, 2);
                return;
            }else{
                ganador = 1;
                piedrasJugadores[0] = 0; piedrasJugadores[1] = 0; piedrasJugadores[2] = 2; piedrasJugadores[3] = 0;
                saberGanador(ganador, 2);
                return;
            }
        }

        if (ganadorPA >= 0 && ganadorPA != 8 && ganadorPB >= 0 && ganadorPB != 8){
            ganador = quienTieneMejorJuego(pA.getJ1().getPuntos(), pB.getJ1().getPuntos());
            if (ganador > 0){
                piedrasJugadores[0] = 2; piedrasJugadores[1] = 0; piedrasJugadores[2] = 0; piedrasJugadores[3] = 0;
            }else if (ganador < 0){
                piedrasJugadores[0] = 0; piedrasJugadores[1] = 2; piedrasJugadores[2] = 0; piedrasJugadores[3] = 0;
            }else {
                ganador = quienTieneMejorJuego(pA.getJ2().getPuntos(), pB.getJ2().getPuntos());
                if (ganador > 0 && ganador != 8){
                    piedrasJugadores[0] = 0; piedrasJugadores[1] = 0; piedrasJugadores[2] = 2; piedrasJugadores[3] = 0;
                }else if (ganador < 0){
                    piedrasJugadores[0] = 0; piedrasJugadores[1] = 0; piedrasJugadores[2] = 0; piedrasJugadores[3] = 2;
                }else ganador = 0;
            }
        }else if (ganadorPA >= 0 && ganadorPA != 8 && ganadorPB < 0){
            ganador = quienTieneMejorJuego(pA.getJ1().getPuntos(), pB.getJ2().getPuntos());
            if (ganador > 0){
                piedrasJugadores[0] = 2; piedrasJugadores[1] = 0; piedrasJugadores[2] = 0; piedrasJugadores[3] = 0;
            }else if (ganador < 0){
                piedrasJugadores[0] = 0; piedrasJugadores[1] = 0; piedrasJugadores[2] = 0; piedrasJugadores[3] = 2;
            }else {
                ganador = quienTieneMejorJuego(pA.getJ2().getPuntos(), pB.getJ1().getPuntos());
                if (ganador > 0 && ganador != 8){
                    piedrasJugadores[0] = 0; piedrasJugadores[1] = 0; piedrasJugadores[2] = 2; piedrasJugadores[3] = 0;
                }else if (ganador < 0){
                    piedrasJugadores[0] = 0; piedrasJugadores[1] = 2; piedrasJugadores[2] = 0; piedrasJugadores[3] = 0;
                }else ganador = 0;
            }
        }else if (ganadorPA < 0 && ganadorPB >= 0 && ganadorPB != 8){
            ganador = quienTieneMejorJuego(pA.getJ2().getPuntos(), pB.getJ1().getPuntos());
            if (ganador > 0){
                piedrasJugadores[0] = 0; piedrasJugadores[1] = 0; piedrasJugadores[2] = 2; piedrasJugadores[3] = 0;
            }else if (ganador < 0){
                piedrasJugadores[0] = 0; piedrasJugadores[1] = 2; piedrasJugadores[2] = 0; piedrasJugadores[3] = 0;
            }else{
                ganador = quienTieneMejorJuego(pA.getJ1().getPuntos(), pB.getJ2().getPuntos());
                if (ganador > 0 && ganador != 8){
                    piedrasJugadores[0] = 2; piedrasJugadores[1] = 0; piedrasJugadores[2] = 0; piedrasJugadores[3] = 0;
                }else if (ganador < 0){
                    piedrasJugadores[0] = 0; piedrasJugadores[1] = 0; piedrasJugadores[2] = 0; piedrasJugadores[3] = 2;
                }else ganador = 0;
            }
        }else if(ganadorPA < 0 && ganadorPB < 0 ){
            ganador = quienTieneMejorJuego(pA.getJ2().getPuntos(), pB.getJ2().getPuntos());
            if (ganador > 0){
                piedrasJugadores[0] = 0; piedrasJugadores[1] = 0; piedrasJugadores[2] = 2; piedrasJugadores[3] = 0;
            }else if (ganador < 0){
                piedrasJugadores[0] = 0; piedrasJugadores[1] = 0; piedrasJugadores[2] = 0; piedrasJugadores[3] = 2;
            }else {
                ganador = quienTieneMejorJuego(pA.getJ1().getPuntos(), pB.getJ1().getPuntos());
                if (ganador > 0 && ganador != 8){
                    piedrasJugadores[0] = 2; piedrasJugadores[1] = 0; piedrasJugadores[2] = 0; piedrasJugadores[3] = 0;
                }else if (ganador < 0){
                    piedrasJugadores[0] = 0; piedrasJugadores[1] = 2; piedrasJugadores[2] = 0; piedrasJugadores[3] = 0;
                }else ganador = 0;
            }
        }else {
            jugarAlPunto();
            return;
        }

        saberGanador(ganador, 2);

    }

    private int quienTieneMejorJuego(int puntos1, int puntos2){ //Devuelve 1 si gana J1, 0 si empatan y -1 si gana J2 devuelve 8 si ninguno tiene juego
        
        if (orden.contains(puntos1) && orden.contains(puntos2)){

            if(orden.indexOf(puntos1) < orden.indexOf(puntos2)){
                return 1;
            }else if(orden.indexOf(puntos1) == orden.indexOf(puntos2)){
                return 0;
            }else return -1;

        }else if(orden.contains(puntos1) && !orden.contains(puntos2)){
            return 1;
        }else if(!orden.contains(puntos1) && orden.contains(puntos2)){
            return -1;
        }else return 8;

    }

    private void jugarAlPunto(){

        int ganadorA; int ganadorB; int ganador;
        ganadorA = mayorPuntuacion(pA.getJ1().getPuntos(), pA.getJ2().getPuntos());
        ganadorB = mayorPuntuacion(pB.getJ1().getPuntos(), pB.getJ2().getPuntos());

        if(ganadorA >= 0 && ganadorB >= 0){
            ganador = mayorPuntuacion(pA.getJ1().getPuntos(), pB.getJ1().getPuntos());
            if (ganador > 0){
                piedrasJugadores[0] = 1; piedrasJugadores[1] = 0; piedrasJugadores[2] = 0; piedrasJugadores[3] = 0;
            }else if (ganador < 0){
                piedrasJugadores[0] = 0; piedrasJugadores[1] = 1; piedrasJugadores[2] = 0; piedrasJugadores[3] = 0;
            }else {
                ganador = mayorPuntuacion(pA.getJ2().getPuntos(), pB.getJ2().getPuntos());
                if (ganador > 0){
                    piedrasJugadores[0] = 0; piedrasJugadores[1] = 0; piedrasJugadores[2] = 1; piedrasJugadores[3] = 0;
                }else if (ganador < 0){
                    piedrasJugadores[0] = 0; piedrasJugadores[1] = 0; piedrasJugadores[2] = 0; piedrasJugadores[3] = 1;
                }
            }
        }else if (ganadorA >= 0 && ganadorB < 0){
            ganador = mayorPuntuacion(pA.getJ1().getPuntos(), pB.getJ2().getPuntos());
            if (ganador > 0){
                piedrasJugadores[0] = 1; piedrasJugadores[1] = 0; piedrasJugadores[2] = 0; piedrasJugadores[3] = 0;
            }else if (ganador < 0){
                piedrasJugadores[0] = 0; piedrasJugadores[1] = 0; piedrasJugadores[2] = 0; piedrasJugadores[3] = 1;
            }else {
                ganador = mayorPuntuacion(pA.getJ2().getPuntos(), pB.getJ1().getPuntos());
                if (ganador > 0){
                    piedrasJugadores[0] = 0; piedrasJugadores[1] = 0; piedrasJugadores[2] = 1; piedrasJugadores[3] = 0;
                }else if (ganador < 0){
                    piedrasJugadores[0] = 0; piedrasJugadores[1] = 1; piedrasJugadores[2] = 0; piedrasJugadores[3] = 0;
                }
            }
        }else if (ganadorA < 0 && ganadorB >= 0){
            ganador = mayorPuntuacion(pA.getJ2().getPuntos(), pB.getJ1().getPuntos());
            if (ganador > 0){
                piedrasJugadores[0] = 0; piedrasJugadores[1] = 0; piedrasJugadores[2] = 1; piedrasJugadores[3] = 0;
            }else if (ganador < 0){
                piedrasJugadores[0] = 0; piedrasJugadores[1] = 1; piedrasJugadores[2] = 0; piedrasJugadores[3] = 0;
            }else{
                ganador = mayorPuntuacion(pA.getJ1().getPuntos(), pB.getJ2().getPuntos());
                if (ganador > 0){
                    piedrasJugadores[0] = 1; piedrasJugadores[1] = 0; piedrasJugadores[2] = 0; piedrasJugadores[3] = 0;
                }else if (ganador < 0){
                    piedrasJugadores[0] = 0; piedrasJugadores[1] = 0; piedrasJugadores[2] = 0; piedrasJugadores[3] = 1;
                }
            }
        }else{
             ganador = mayorPuntuacion(pA.getJ2().getPuntos(), pB.getJ2().getPuntos());
             if (ganador > 0){
                piedrasJugadores[0] = 0; piedrasJugadores[1] = 0; piedrasJugadores[2] = 1; piedrasJugadores[3] = 0;
            }else if (ganador < 0){
                piedrasJugadores[0] = 0; piedrasJugadores[1] = 0; piedrasJugadores[2] = 0; piedrasJugadores[3] = 1;
            }else {
                ganador = mayorPuntuacion(pA.getJ1().getPuntos(), pB.getJ1().getPuntos());
                if (ganador > 0){
                    piedrasJugadores[0] = 1; piedrasJugadores[1] = 0; piedrasJugadores[2] = 0; piedrasJugadores[3] = 0;
                }else if (ganador < 0){
                    piedrasJugadores[0] = 0; piedrasJugadores[1] = 1; piedrasJugadores[2] = 0; piedrasJugadores[3] = 0;
                }
            }
        }

        saberGanador(ganador, 1);
            
    }

    private int mayorPuntuacion(int puntos1, int puntos2){ //Devuelve un 1 si gana J1, 0 si empatan  y un -1 si gana J2

        if(puntos1 > puntos2){
            return 1;
        }else if(puntos1 < puntos2){
            return -1;
        }else return 0;

    }

    private void saberGanador(int ganador, int piedras){

        int resultadoA = 0, resultadoB = 0, puntosJuego = 0;

        if( ganador > 0 ){
            pA.setPiedras(piedras);
            resultadoA += piedras;
        }else if (ganador == 0 && (pA.getJ1().getMano() || pA.getJ2().getMano())){
            pA.setPiedras(piedras);
            resultadoA += piedras;
            if (pA.getJ1().getMano()){
                piedrasJugadores[0] = piedras; piedrasJugadores[1] = 0; piedrasJugadores[2] = 0; piedrasJugadores[3] = 0;
            }else {
                piedrasJugadores[0] = 0; piedrasJugadores[1] = 0; piedrasJugadores[2] = piedras; piedrasJugadores[3] = 0;
            }
        }else if( ganador < 0 ){
            pB.setPiedras(piedras);
            resultadoB += piedras;
        }else if (ganador == 0 && (pB.getJ1().getMano() || pB.getJ2().getMano())){
            pB.setPiedras(piedras);
            resultadoB += piedras;
            if (pB.getJ1().getMano()){
                piedrasJugadores[0] = 0; piedrasJugadores[1] = piedras; piedrasJugadores[2] = 0; piedrasJugadores[3] = 0;
            }else {
                piedrasJugadores[0] = 0; piedrasJugadores[1] = 0; piedrasJugadores[2] = 0; piedrasJugadores[3] = piedras;
            }
        }

        puntosJuego = puntosJuego(pA.getJ1().getPuntos()) + puntosJuego(pA.getJ2().getPuntos());
        piedrasJugadores[0] += puntosJuego(pA.getJ1().getPuntos());
        piedrasJugadores[2] += puntosJuego(pA.getJ2().getPuntos());
        //puntosJuego = piedrasJugadores[0] + piedrasJugadores[2];
        pA.setPiedras(puntosJuego);
        resultadoA += puntosJuego;
        
        puntosJuego = puntosJuego(pB.getJ1().getPuntos()) + puntosJuego(pB.getJ2().getPuntos());
        piedrasJugadores[1] += puntosJuego(pB.getJ1().getPuntos());
        piedrasJugadores[3] += puntosJuego(pB.getJ2().getPuntos());
        //puntosJuego = piedrasJugadores[1] + piedrasJugadores[3];
        pB.setPiedras(puntosJuego);
        resultadoB += puntosJuego;

        resultado = "Juego " + Integer.toString(resultadoA) + " " + Integer.toString(resultadoB);

    }

    private int puntosJuego(int puntos){

        if (puntos == 31){
            return 3;
        }else if(puntos > 31){
            return 2;
        }else return 0;

    }

}