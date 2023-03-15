/**
 * Esta clase es la clase principal, donde incluimos el main, que asigna el modo de juego segun sea la entrada por linea de comandos.
 */

public class MiniMus{
    
    public static void main(String[] args) {

        Modo1 modo1;
        Modo2 modo2;
        Modo3 modo3;
        Partida partida;
        Ficheros f;
        int i = args.length;

        if (i == 0) {
            
            modo1 = new Modo1("salida.txt");
            partida = new Partida(modo1.getListaParejas().get(0), modo1.getListaParejas().get(1), modo1.getFichero());
            //no existen los ficheros, creamos nosotros los jugadores. Estamos en Modo1 siempre.
        }

      //  if (i == 0) { //(?????????????????????????????????????????????????????????????????)
        //    f = new Ficheros("comandos.txt", "salida.txt");
        //    modo3 = new Modo3(f);
            //partida = new Partida(modo1.getListaParejas().get(0), modo1.getListaParejas().get(1), modo1.getFichero());
            //no existen los ficheros, creamos nosotros los jugadores. Estamos en Modo1 siempre.
   //     }
    
        if(i == 2){
            //tenemos el fichero de entrada pero no el de salida. Según el guión que tengamos en el args de 0, sabemos qué modo es
            
            if (args[0].equals("-p")){
                
                f = new Ficheros(args[1], "salida.txt");
                modo1 = new Modo1(f);
                partida = new Partida(modo1.getListaParejas().get(0), modo1.getListaParejas().get(1), f);
                
            }else if(args[0].equals("-o")){

                modo1 = new Modo1(args[1]);
                partida = new Partida(modo1.getListaParejas().get(0), modo1.getListaParejas().get(1), modo1.getFichero());

            }else if (args[0].equals("-j")){

                f = new Ficheros(args[1], "salida.txt");
                modo2 = new Modo2(f);
                partida = new Partida(f, modo2);
                
            }else if (args[0].equals("-c")){

                f = new Ficheros(args[1], "salida.txt");
                modo3 = new Modo3(f);
                
            }
        
            
        }else if(args.length == 4){
            //tenemos ambos ficheros, entrada y salida.
            
            if ((args[0].equals("-p"))&&(args[2].equals("-o"))){

                f = new Ficheros(args[1], args[3]);
                modo1 = new Modo1(f);
                partida = new Partida(modo1.getListaParejas().get(0), modo1.getListaParejas().get(1), f);

                
            }else if ((args[0].equals("-j"))&&(args[2].equals("-o"))){
                f = new Ficheros(args[1], args[3]);
                modo2 = new Modo2(f);
                partida = new Partida(f, modo2);
                
            }else if ((args[0].equals("-c"))&&(args[2].equals("-o"))){
                f = new Ficheros(args[1], args[3]);
                modo3 = new Modo3(f);
                
            }
        }else if(args.length == 6){
            f = new Ficheros(args[1], args[5]);
            modo2 = new Modo2(f, args[3]);
            partida = new Partida(f, modo2);
        }
        
    } 
  
    
}
