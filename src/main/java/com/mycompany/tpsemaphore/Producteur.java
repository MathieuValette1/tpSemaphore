/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tpsemaphore;


import static java.lang.Thread.sleep;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mathi
 */
public class Producteur extends Thread{
    
    Semaphore nbProduits;
    Semaphore placesRestantes;
    
    int maxSize;
    int i = 0;
    int attente;
    
    Random random = new Random();
    List<String> produits ;
    
    public Producteur(List<String> produits, Semaphore nbProduits, Semaphore placesRestantes){
        
        this.produits = produits;
        this.nbProduits = nbProduits;
        this.placesRestantes = placesRestantes;
    }
    
    @Override
    public void run(){
        while (true){
            attente = random.nextInt(5001);
            if (produits.size() != maxSize){
               
            
                produits.add("numéro " + i);
                i+=1;
                System.out.println(getName()+" a ajouté le produit " + i);
                nbProduits.release();//On UP le sémaphore correspondant aux nombres de produits
                try {
                    placesRestantes.acquire(); // On DOWN le sémaphore correspondant aux nombre de place restantes
                } catch (InterruptedException ex) {
                    Logger.getLogger(Producteur.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                System.out.println("On attend " + attente + " ms.");
                try {sleep(attente); } catch (InterruptedException e) {};
                System.out.println("Taille de la liste: " + produits.size());
            }
            else{
                System.out.println("La liste est complète.");
                try {sleep(attente); } catch (InterruptedException e) {};
            }
         } 
        
    }
}
