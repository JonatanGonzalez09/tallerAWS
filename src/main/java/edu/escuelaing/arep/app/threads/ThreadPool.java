package edu.escuelaing.arep.app.threads;

public class ThreadPool extends Thread{
    @Override
    public void run(){
        System.out.println("Hello from a thread !");
    }
}