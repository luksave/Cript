/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package criptografia;

/**
 *
 * @author lucas
 */
public class TwoKeysStore {
    
    private String cPublica;
    private String cPrivada;
    
    public TwoKeysStore(String chavePublica, String chavePrivada){
        
        this.cPublica = chavePublica;
        this.cPrivada = chavePrivada;
          
    }
    
    public String getChavePublica() {
        return cPublica;
        
    }

    public String getChavePrivada() {
        return cPrivada;
        
    }

    
    
}
