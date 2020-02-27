package edu.escuelaing.arep.app.threads;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.reflect.Method;
import edu.escuelaing.arep.app.annotation.Web;

/**
 * Clase que resuelve peticiones concurrentemente.
 */
public class ThreadPool extends Thread {

    private Socket clientSocket;

    /**
     * Constructor de la clase ThreadPool.
     * @param clientSocket socket del cliente.
     */
    public ThreadPool(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    /**
     * Realiza cuando el hilo se esta ejecutando
     */
    @Override
    public void run() {
        String contenido;
        try {
            PrintWriter texto = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader leer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String lineas = leer.readLine();
            if (lineas != null && lineas.contains("GET")) {
                String url = lineas.split(" ")[1];      
                try {
                    if(url.contains("html")){
                        System.out.println("html...");
                        contenido =ThreadPool.respuestaHtml(url);
                        texto.println(
                            "HTTP/1.1 200 OK \r\n"
                            + "Content-Type: text/html; charset=UTF-8\r\n" 
                            + "\r\n\r\n"
                            + contenido);
                    }
                    else if (url.contains("png")) {
                        byte[] imagen = ThreadPool.respuestaImagen(url);
                        DataOutputStream binaryOut = new DataOutputStream(clientSocket.getOutputStream());
                        binaryOut.writeBytes("HTTP/1.1 200 OK \r\n"
                        + "Content-Type: image/png \r\n"
                        + "Content-Length: " + imagen.length
                        + "\r\n\r\n");
                        binaryOut.write(imagen);
                        binaryOut.close();
                    } else if (url.contains("num")) {
                        String[] recursos = url.split("\\W+");
                        contenido = ThreadPool.htmlConParametros(recursos[1], recursos[2], recursos[4], recursos[6]);
                        texto.println(
                            "HTTP/1.1 200 OK \r\n"
                            + "Content-Type: text/html; charset=UTF-8\r\n" 
                            + "\r\n\r\n"
                            + contenido);
                    } else {
                        contenido =ThreadPool.respuestaHtml("index.html");
                        texto.println(
                            "HTTP/1.1 200 OK \r\n"
                            + "Content-Type: text/html; charset=UTF-8\r\n" 
                            + "\r\n\r\n"
                            + contenido);
                    }
                } catch (IOException e) {
                    texto.println(
                            "HTTP/1.1 200 OK \r\n"
                            + "Content-Type: text/html; charset=UTF-8\r\n" 
                            + "\r\n\r\n"
                            + " 404 Pagina no encontrada");
                } catch (Exception ex) {
                    Logger.getLogger(ThreadPool.class.getName()).log(Level.SEVERE, null, ex);
                    texto.println(
                            "HTTP/1.1 200 OK \r\n"
                            + "Content-Type: text/html; charset=UTF-8\r\n" 
                            + "\r\n\r\n"
                            + " 404 Pagina no encontrada");
                }
            }
            texto.close();
            leer.close();
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ThreadPool.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Muestra la respuesta para cuando la peticion es un html.
     * @param ruta path de la peticion del usuario. 
     * @return Retorna la respuesta que se mostrara al usuario.
     * @throws FileNotFoundException En caso de que no encuentre el recurso.
     * @throws IOException En caso de que la entrada este mal estructurada.
     */
    public static String respuestaHtml(String ruta) throws FileNotFoundException, IOException {
        FileReader archivo = new FileReader("./resources/" + ruta);
        BufferedReader buffered = new BufferedReader(archivo);
        String line = buffered.readLine();
        String respuesta = "";
        while (line != null) {
            respuesta += line;
            line = buffered.readLine();
        }
        buffered.close();
        return respuesta;
    }

    /**
     * Muestra la respuesta para cuando la peticion es un png.
     * @param ruta path de la peticion del usuario. 
     * @return Retorna la respuesta que se mostrara al usuario.
     * @throws IOException En caso de que la entrada este mal estructurada.
     */
    public static byte[] respuestaImagen(String ruta) throws IOException {
        File imagen = new File("resources/" + ruta);
        FileInputStream inputImage = new FileInputStream(imagen.getPath());
        byte[] pagina = new byte[(int) imagen.length()];
        inputImage.read(pagina);
        return pagina;
    }

    /**
     * Muestra la respuesta para cuando la peticion se hace de manera dinamica (multiplicacion).
     * @param nombClase clase a la cual va ha recurrir para dar respuesta.
     * @param metodo el metodo que va ha utilizar para dar respuesta.
     * @param num1 primer numero a procesar en la peticion (multiplicar).
     * @param num2 segundo numero a procesar en la peticion (multiplicar).
     * @return Retorna la respuesta que se mostrara al usuario con la operacion entre los dos numeros. 
     * @throws Exception
     */
    public static String htmlConParametros(String nombClase, String metodo,String num1, String num2) throws Exception {
        String solucion = "";
        Class clase = Class.forName("edu.escuelaing.arep.app.operacion."+ nombClase);
        for (Method met : clase.getMethods()) {
            if (met.isAnnotationPresent(Web.class)) {
                    Object respuesta = met.invoke(metodo,num1,num2);
                    solucion = respuesta.toString();
            }
        }
        return solucion;
    }
}
