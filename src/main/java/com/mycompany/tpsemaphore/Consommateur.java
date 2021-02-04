/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tpsemaphore;


import static java.lang.Thread.sleep;
import java.util.*;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author mathi
 */
public class Consommateur extends Thread {
    
    Semaphore nbProduits;
    Semaphore placesRestantes;
    
    int size;
    List<String> produits ;
    
    int attente;
    Random random = new Random();
    
    public Consommateur (List<String> produits, Semaphore nbProduits, Semaphore placesRestantes) {
        
        this.produits= produits ; 
        this.nbProduits = nbProduits;
        this.placesRestantes = placesRestantes;
    }
    public int getSize(){
        this.size = produits.size();
        return size;
    }
    @Override
    public void run (){
        try {sleep(500); } catch (InterruptedException e) {};
            while (true){   
                if (produits.isEmpty()) {

                    try {
                        nbProduits.acquire(); //On DOWN le sémaphore correspondant aux nombres de produits
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Consommateur.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else {
                    ///PARTIE 1
                    int last = produits.size() -1 ;
                    System.out.println(getName()+"tente de lire le produit : " + last ); 
                    String produit = produits.get(last); 
                    System.out.println(getName()+"a lu le produit :  " + produit );
                    
                    ///PARTIE 2 
                    System.out.println(getName()+"tente d'enlever le produit : " + produit ); 
                    produits.remove(last);
                    System.out.println(getName()+"a enlever le produit : " + produit ); 
                    attente = random.nextInt(5001);

                    placesRestantes.release(); // On UP le sémaphore correspondant aux nombre de place restantes

                    System.out.println("On attend " + attente + " ms.");
                    try {sleep(attente); } catch (InterruptedException e) {}; 
                    System.out.println("Taille de la liste: " + produits.size());
                }
                }
            }
        //System.out.println(getName()+"a fini" ); 

        }
    

