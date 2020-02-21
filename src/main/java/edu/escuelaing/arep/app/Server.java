package edu.escuelaing.arep.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.escuelaing.arep.app.threads.ThreadPool;

public class Server {
	
	private int puerto=getPort();
	private Socket clientSocket;
	private static ServerSocket servSocket;
    private String serverMessage;

    ExecutorService executor = Executors.newFixedThreadPool(10);

    /**
     * Class constructor. Set attributes values.
     */
    public Server() {
    }

    /**
     * This method starts the server
     */
    public void startServer(){
        while (true) {
            try {
                System.out.println("Estableciendo la conexión...");
                this.clientSocket = this.servSocket.accept();

                System.out.println("Cliente establecido...");

                BufferedReader entrada = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));

                String path = this.getRequest(entrada);

                OutputStream os = clientSocket.getOutputStream();
                boolean running = true;

                while(running){
                    this.clientSocket = this.servSocket.accept();
                    executor.execute(RequesHandler(this.clientSocket));
                }
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public String getRequest(BufferedReader entrada) {

        boolean notExit = true;
        String path = null;
        try {
            while ((this.serverMessage = entrada.readLine()) != null && notExit) {

                if (this.serverMessage.contains("GET")) {
                    String[] dir = this.serverMessage.split(" ");
                    path = dir[1];
                    notExit = false;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return path;
    }

    public static int getPort() {
        if (System.getenv("PORT") != null) {
            return new Integer(System.getenv("PORT"));
        }
        return 4567;
    }
}