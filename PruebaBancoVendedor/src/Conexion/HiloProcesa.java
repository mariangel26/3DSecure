/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oswalm
 */
public class HiloProcesa extends Thread {
    
    Socket clientSocket;
    
    public HiloProcesa(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
    
    public void run(){
        try {
            PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(),true);
            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.println("user '" + br.readLine() + "' is now connected to the server");
            while(true) pw.println(br.readLine() + " echo");
        } catch (IOException ex) {
            Logger.getLogger(HiloProcesa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
