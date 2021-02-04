package com.mycompany.tpsemaphore;


public class monThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            // la méthode getName() renvoie le nom du thread
            System.out.println("coucou de la part du thread " + getName());
        }
        System.out.println("Je suis le thread "+getName()+" et j'ai fini");
    }
 }

