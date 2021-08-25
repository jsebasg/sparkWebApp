
package edu.escuelaing.arep.sparkherokulive;

/**
 *
<<<<<<< HEAD
 * @author Juan sebastian Garcia
=======
 * @author Juan sebastian Garcia 
>>>>>>> 74911f388541e3eb533870b74bc4246d87a8c080
 */
public class IEXAPIHttpStockService extends HttpStockService {
    String stock= "fb";
    @Override
    public String getURL() {
        return "https://cloud.iexapis.com/stable/tops?token=pk_3a53b8c609ff4c1c974353f00a090669&symbols="+ stock;
    }

    @Override
    public void setStock(String stock) {
        this.stock=stock;
    }
    
}
