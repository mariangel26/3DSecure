/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

/**
 *
 * @author oswalm
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Cliente.pintarCandadoAbierto();
        Cliente.pintarCandadoConLlave();
        Cliente.pintarCandadoCerrado();
    }
    
    public static void pintarCandadoConLlave(){
        System.out.println( "──▄▀▀▀▄───────────────\n" +
                            "──█───█───────────────\n" +
                            "─███████─────────▄▀▀▄─\n" +
                            "░██───██░░█▀█▀▀▀▀█░░█░\n" +
                            "░███▄███░░▀░▀░░░░░▀▀░░\n");
    }
    
    public static void pintarCandadoCerrado(){
        System.out.println( "────▄▀▀▀▄────\n" +
                            "────█───█────\n" +
                            "───███████───\n" +
                            "──░██───██░──\n" +
                            "──░███▄███░──\n");
    }
    /*
    public static void pintarCandadoAbierto(){
        System.out.println( "────▄▀▀▀─────\n" +
                            "────█────────\n" +
                            "───███████───\n" +
                            "──░██───██░──\n" +
                            "──░███▄███░──");
    }*/
    
    public static void pintarCandadoAbierto(){
        System.out.println( "─▄▀▀▀▄────────\n" +
                            "─█───█────────\n" +
                            "────███████───\n" +
                            "───░██───██░──\n" +
                            "───░███▄███░──\n");
    }
    
    
    
}
