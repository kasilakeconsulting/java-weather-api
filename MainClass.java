/*
 * Sample code for fetching forecast weather data from the National Weather Service using their API.
 * It parses the results as JSON and outputs some forecast information.

 * References:
 * https://weather-gov.github.io/api/general-faqs
 *
 * https://github.com/stleary/JSON-java
 *   Their README has a link to the latest jar file.
 *   When testing my I code I used json-20210307.jar.
 */

/**
 *
 * @author Kasi Lake Consulting LLC
 */

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
How I got NetBeans to accept the org.json import (macOS):

- Under NetBeansProjects\weatherAPI\src\main\java I created libs and added the json-20210307.jar jar there.
- The IDE refreshed the project to include this folder and file.
- This page (https://stackoverflow.com/questions/17693040/adding-external-jar-to-maven-project-in-netbeans) shows how to add a dependency.
- I used the groupid, artifactid, and version provided in the pom.xml file provided by this jar (use IDE to show jar contents, open META-INF.maven.org.json.json subfolder).
*/
import org.json.*;

public class MainClass {
    
    private static class checkConnection {
        private int status = 0;
        private HttpURLConnection http = null;

        public checkConnection(URL url) {
            try {
                http = (HttpURLConnection)url.openConnection();
                status = http.getResponseCode();
            } catch (IOException ex) {
                Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void main(String[] args) {

        // Retrieve the metadata for your location with:  https://api.weather.gov/points/{lat},{lon}

        String latLon = "38.8894,-77.0352";
        
        URL requestURL = null;
        String inputLine = "", dataRaw = "";
        JSONObject dataJSON  = null, propertiesJSON = null;
        
        try {
            requestURL = new URL("https://api.weather.gov/points/" + latLon);

            System.out.println("Requesting metadata... " + requestURL.toString());

            int statusCode = new checkConnection(requestURL).status;
            System.out.println("\nResponse code: " + String.valueOf(statusCode));
            
            if (statusCode != 200) {
                System.out.println("API call failed");
            } else {
                URLConnection uc = requestURL.openConnection();

                BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));

                System.out.println("\nResponse text:");
                
                while ((inputLine = in.readLine()) != null) {
                    System.out.println(inputLine);
                    dataRaw = dataRaw.concat(inputLine);
                }

                in.close();

                // Parse the data into JSON format.
                dataJSON = new JSONObject(dataRaw);
                
                System.out.println("\nJSON text:\n\n" + dataJSON.toString());
                
                propertiesJSON = dataJSON.getJSONObject("properties"); 
                requestURL = new URL(propertiesJSON.getString("forecast"));

                System.out.println("\nForecast URL: " + requestURL.toString());
                
                System.out.println("\nRequesting forecast data...");
                
                
            }


        } catch (MalformedURLException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        


    }

}
