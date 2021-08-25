/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arep.sparkherokulive;
public class CurrentServiceInstance {
    private static CurrentServiceInstance _instance = new  CurrentServiceInstance();
    private HttpStockService service;
    private CurrentServiceInstance(){
        service =new AlphaHttpStockService();
    }

    public static CurrentServiceInstance getInstance(){
        return _instance;
    }
    
    public HttpStockService getService(){
        return service;
    }
    
}
