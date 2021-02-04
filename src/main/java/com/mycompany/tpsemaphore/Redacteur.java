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
public class Redacteur extends Thread {

    Semaphore s;

    public Redacteur(Semaphore s) {
        this.s = s;
    }

    @Override
    public void run() {
        while (true) {
            try {
                s.acquire();
                System.out.println(getName() + " Rédacteur " + getName() + " entre en écriture");
                // section d'écriture
                // un seul rédacteur et aucun lecteur
                s.release();
                System.out.println(getName() + " Rédacteur " + getName() + " sort de écriture");
                sleep(1000);
            } catch (InterruptedException ex) {
            }
        }
    }
}
