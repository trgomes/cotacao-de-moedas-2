package br.com.cotacaoDeMoedas.model;

import org.json.JSONArray;
import org.json.JSONException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.io.InputStreamReader;
import java.util.LinkedList;
import org.json.JSONObject;

public class Conection {

    public List<Moeda> getData(String s) throws JSONException {

        final StringBuilder result = new StringBuilder();

        URL url;
        
        HttpURLConnection urlConnection = null;
        
        try {
            url = new URL(s);

            urlConnection = (HttpURLConnection) url
                    .openConnection();

            InputStream in = urlConnection.getInputStream();

            InputStreamReader isw = new InputStreamReader(in);



            int data = isw.read();
            while (data != -1) {
                char current = (char) data;
                data = isw.read();
                result.append(current);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                urlConnection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // System.out.println(result);
        
        JSONObject obj = new JSONObject(result.toString());
        
        //System.out.println(obj.getJSONObject("valores").length());
        
        //System.out.println(teste.getJSONObject("valores").getJSONObject("USD").get("valor"));
        
        //System.out.println(obj.getJSONObject("valores"));        
        
        List<Moeda> finalResult = generateJSON(obj.getJSONObject("valores"));            
        
        
        return finalResult;
    }


    public List<Moeda> generateJSON(JSONObject json) throws JSONException{
    	
        List<Moeda> found = new LinkedList<Moeda>();  
        
        for (int i = 0; i < json.length(); i++) {
			
			String key = null;
		
			switch (i) {
				case 0:
					key = "USD"; break;
				case 1:
					key = "EUR"; break;
				case 2:
					key = "ARS"; break;
				case 3:
					key = "GBP"; break;
				case 4:
					key = "BTC"; break;					
				default:
					break;
			}
			
			
			
			found.add(new Moeda(json.getJSONObject(key).getString("nome"),
					            json.getJSONObject(key).getDouble("valor"),
					            json.getJSONObject(key).getDouble("ultima_consulta"),
					            json.getJSONObject(key).getString("fonte")));
		    
		}
                
        return found;

    }
}
