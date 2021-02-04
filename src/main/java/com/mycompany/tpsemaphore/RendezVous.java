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
public class RendezVous extends Thread{
     int n;
     Semaphore s;
     static int compteur =1;
     
     public RendezVous(int n, Semaphore s) {
        this.n = n;
        this.s = s;
     }
     
     @Override
     public void run() {
     System.out.println(getName()+ "débute sa première partie");
     
     if (compteur<9){
         try {
             compteur+=1;
             s.acquire();             
         } catch (InterruptedException ex) {
             Logger.getLogger(RendezVous.class.getName()).log(Level.SEVERE, null, ex);
         }
         
     }
     // point de rendez-vous
     System.out.println(getName()+ "débute sa seconde partie");
     s.release();
    }

}
