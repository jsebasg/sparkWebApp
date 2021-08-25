package edu.escuelaing.arep.sparkherokulive;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static spark.Spark.*;
import spark.Request;
import spark.Response;


/**/

public class App 
{
    public static void main(String[] args) {
        port(getPort());
        staticFiles.location("/public");

        get("/inputdata", (req, res) -> inputDataPage(req, res));
        get("/results", (req, res) -> resultsPage(req, res));
        get("/facadealpha", "application/json",(req, res) -> facadeAlpha(req,res));
        get("/index" , (req, res) -> askingPage(req, res));
    }
    private static String askingPage(Request req, Response res) {
        String pageContent 
                                = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "    <head>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "        <title>AREP</title>\n" +
                "        <!-- <link rel=\"stylesheet\" href=\"./css/index.css\">\n" +
                "        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We\" crossorigin=\"anonymous\"> --> \n" +
                "    <script type = \"text/javascript\">  \n" +
                "    function myfunction() {   \n" +
                "        window.open(\"../facadealpha?st=\" + document.getElementById(\"userInput\").value ,\"_self\") \n" +
                "             }  \n" +
                "    </script> \n" +
                "    </head>\n" +
                "      \n" +
                "    <body>\n" +
                "\n" +
                "        <main class=\"main-container\">\n" +
                "            <h1 class=\"title\">Mercado De Valores</h1>\n" +
                "    \n" +
                "            <section class=\"mdv-list\">\n" +
                "                <form id=\"form\" onsubmit=\"return false;\">\n" +
                "                    <input style=\"position:absolute; top:80%; left:5%; width:40%;\" type=\"text\" id=\"userInput\" />\n" +
                "                    <input style=\"position:absolute; top:50%; left:5%; width:40%;\" type=\"submit\" onclick=\"myfunction();\" />\n" +
                "                </form>\n" +
                "            </section>\n" +
                "        </main>\n" +
                "        <script src=\"./js/app.js\"></script>\n" +
                "    \n" +
                "    </body>\n" +
                "</html>\n"; 
        return pageContent; 
    }

    private static String inputDataPage(Request req, Response res) {
        String pageContent
                = "<!DOCTYPE html>"
                + "<html>"
                + "<body>"
                + "<h2>HTML Forms</h2>"
                + "<form action=\"/results\">"
                + "  First name:<br>"
                + "  <input type=\"text\" name=\"firstname\" value=\"Mickey\">"
                + "  <br>"
                + "  Last name:<br>"
                + "  <input type=\"text\" name=\"lastname\" value=\"Mouse\">"
                + "  <br><br>"
                + "  <input type=\"submit\" value=\"Submit\">"
                + "</form>"
                + "<p>If you click the \"Submit\" button, the form-data will be sent to a page called \"/results\".</p>"
                + "</body>"
                + "</html>";
        return pageContent;
    }

    private static String resultsPage(Request req, Response res) {
        return req.queryParams("firstname") + " " +
                req.queryParams("lastname");
    }

    /**
     * This method reads the default port as specified by the PORT variable in
     * the environment.
     *
     * Heroku provides the port automatically so you need this to run the
     * project on Heroku.
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set (i.e. on localhost)
    }
    
    private static String facadeAlpha(Request req, Response res) {
        res.type("application/json");
        String stock = req.queryParams("st");
        System.out.println("Stock str:" + stock);
        String response = "None";
        HttpStockService stockService = CurrentServiceInstance.getInstance().getService();
        if (stock != null && stock != ""){
            System.out.println("Stting stock to :" + stock);
            stockService.setStock(stock);
        }
        try {
            response= stockService.TimeSeriesDaily();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
}

