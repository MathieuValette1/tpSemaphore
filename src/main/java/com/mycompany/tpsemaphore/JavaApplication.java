package com.mycompany.tpsemaphore;

import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;
import java.util.*;
import java.util.stream.IntStream;

///POUR LECTEUR/REDACTEUR
public class JavaApplication{
    public static void main(String[] args) throws Exception{
        Semaphore s1 = new Semaphore(1);
        Semaphore s2 = new Semaphore(1);
        int nbLecteur = 0;
        IntStream.range(0,10).forEach(i->{
            Lecteur lec = new Lecteur(s1,s2);
            Redacteur red = new Redacteur(s2);
            lec.start();
            red.start();
            
        });
    }
}
/** POUR RENDEZ VOUS
public class JavaApplication{
    public static void main(String[] args) throws Exception {
        Semaphore s = new Semaphore(0);
        IntStream.range(0,10).forEach(i->{
            RendezVous rdv = new RendezVous(10,s);
            rdv.start();
        });
    }
}
*/
/** POUR CONSOMMATEUR/PRODUCTEUR
public class JavaApplication {
    public static void main(String[] args) throws Exception {
        List<String> produits = new ArrayList();
        
    int maxSize = 10;
    
    Semaphore nbProduits = new Semaphore(0);
    Semaphore placesRestantes = new Semaphore(maxSize);
    
    Producteur prod = new Producteur(produits, nbProduits, placesRestantes);
    Producteur prod1 = new Producteur(produits, nbProduits, placesRestantes);
    
    prod.maxSize = maxSize;
    prod1.maxSize = maxSize;
    
               
    Consommateur cons0 = new Consommateur(produits,nbProduits, placesRestantes);
    Consommateur cons1 = new Consommateur(produits,nbProduits, placesRestantes);

    prod.start();
    prod1.start();
    cons0.start();   
    cons1.start();

    }
}*/