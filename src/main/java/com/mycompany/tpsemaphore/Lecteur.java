/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tpsemaphore;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mathi
 */
public class Lecteur extends Thread {

    Semaphore s;
    Semaphore mutex;
    static int compteur = 0;

    public Lecteur(Semaphore mutex, Semaphore s) {
        this.mutex = mutex;
        this.s = s;
    }
    
    
    // section de lecture
    // ok plusieurs lecteurs, mais pas de rédacteurs
    @Override
    public void run() {
        while (true) {
            try {
                mutex.acquire();
                System.out.println(getName() + " Lecteur " + getName() + " entre en lecture ");
                compteur++;

                if (compteur == 1) {
                    s.acquire();
                    
                }
                mutex.release();
                sleep(1000);
            } catch (InterruptedException ex) {
            }
            
            try {
                mutex.acquire();
                System.out.println(getName() + " Lecteur " + getName() + " sort de lecture");
                compteur--;
                if (compteur == 0) {
                    s.release();
                }
                mutex.release();
                sleep(1000);
            } catch (InterruptedException ex) {
            }
        }
    }
}