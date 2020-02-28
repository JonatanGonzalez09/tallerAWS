/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClienteFromAWStoHeroku;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 * Cliente de AWS a Heroku que tiene el link quemado.
 */
public class ClienteFromAWSToHeroku extends Thread {

  public URL url;

  public ClienteFromAWSToHeroku(URL url) {
    this.url = url;
  }

  /**
   * @return the url
   */
  public URL getUrl() {
    return url;
  }

  @Override
  public void run() {
    System.out.println("Realizando peticion " + url.toString());
  }

  /**
   * Programa principal para ejecutar el cliente.
   * @param args parametros que necesite el programa principal.
   * @throws Exception En caso de que no se pueda ejecutar.
   */
  public static void main(String[] args) throws Exception {
    ArrayList<ClienteFromAWSToHeroku> listaClientes = new ArrayList<ClienteFromAWSToHeroku>();
    ClienteFromAWSToHeroku cl1 = new ClienteFromAWSToHeroku(new URL("https://dry-ravine-53382.herokuapp.com/"));
    ClienteFromAWSToHeroku cl2 = new ClienteFromAWSToHeroku(new URL("https://dry-ravine-53382.herokuapp.com/multiplicacion.html"));
    ClienteFromAWSToHeroku cl3 = new ClienteFromAWSToHeroku(new URL("https://dry-ravine-53382.herokuapp.com/cat.png"));
    ClienteFromAWSToHeroku cl4 = new ClienteFromAWSToHeroku(new URL("https://dry-ravine-53382.herokuapp.com/ironMan.png"));
    ClienteFromAWSToHeroku cl5 = new ClienteFromAWSToHeroku(new URL("https://dry-ravine-53382.herokuapp.com/Operacion/multiplicacion?num1=5&num2=6"));
    listaClientes.add(cl1);
    listaClientes.add(cl2);
    listaClientes.add(cl3);
    listaClientes.add(cl4);
    listaClientes.add(cl5);
    for (int i = 0; i<listaClientes.size();i++){
      listaClientes.get(i).start();
      try (BufferedReader reader = new BufferedReader(
      new InputStreamReader(listaClientes.get(i).getUrl().openStream()))) { 
        String inputLine = null; 
        while ((inputLine = reader.readLine()) != null){
          System.out.println(inputLine); 
        }
      }catch (IOException x) {
        System.err.println(x);
      }
    }
    for (int i = 0; i<listaClientes.size();i++){
      listaClientes.get(i).join();
    }
  }
}