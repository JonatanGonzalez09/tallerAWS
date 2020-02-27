package edu.escuelaing.arep.app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.escuelaing.arep.app.threads.ThreadPool;

/**
 * Servidor que ejecutara la respuesta.
 */
public class Server {
    
    ExecutorService executor = Executors.newFixedThreadPool(10);

    /**
     * Constructor de la clase servidor.
     */
    public Server() {
    }

    /**
     * Metodo principal para ejecutar el programa.
     * @param args parametros que necesite el main.
     */
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);
        while (true) {
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(getPort());
            } catch (IOException e) {
                System.err.println("Could not listen on port: " + getPort());
                System.exit(1);
            }
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ... ");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            ThreadPool ar = new ThreadPool(clientSocket);
            es.execute(ar);
            try {
                serverSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /** 
     * Devuelve el puerto por el que se correra la aplicacion.
     * @return
     */
    public static int getPort() {
        if (System.getenv("PORT") != null) {
            return new Integer(System.getenv("PORT"));
        }
        return 4567;
    }
}