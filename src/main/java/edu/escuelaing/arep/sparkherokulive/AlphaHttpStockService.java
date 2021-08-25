/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arep.sparkherokulive;


public class AlphaHttpStockService extends HttpStockService {
    String stock= "fb";
    @Override
    public String getURL() {
        return "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol="+ stock+"&apikey=XXXXXXXXXXX";
    }

    @Override
    public void setStock(String stock) {
        this.stock=stock;
    }
    
}
