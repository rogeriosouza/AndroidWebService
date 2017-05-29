package service.com.br.androidwebservice;

import android.util.Log;



import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by ROGERIO on 23/05/2017.
 */

public class JSONfunctions {

    public static JSONObject getJSONfromURL(String url){
        String result = "";
        JSONObject jArray = null;
        HttpURLConnection urlConnection = null;

        try {
            URL urlObj = new URL(url);
            urlConnection = (HttpURLConnection) urlObj.openConnection();
            urlConnection.setReadTimeout(3000);
            urlConnection.setConnectTimeout(3000);

            int responseCode = urlConnection.getResponseCode();
            if (responseCode != HttpsURLConnection.HTTP_OK) {
                throw new IOException("HTTP error code: " + responseCode);
            }

            InputStream is = urlConnection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
            // resposta to string
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
            jArray = new JSONObject(result);

            /* final String[] test = new String[]{ "All fall gala hall\"," +
                "this\\is/a%test\\t_~!@#$%^&*()dude", "v=abc%C3%A4def"};


            /*for ( String s : test ) {
                s = result;
                *//*System.out.println( java.net.URLDecoder.decode(test[i],"UTF-8") );
                System.out.println( java.net.URLDecoder.decode(test[i],"ISO-8859-1") );*//*
                String dec = URLEncoder.encode (result ,"UTF-8");
                jArray = new JSONObject(URLDecoder.decode(result,"UTF-8"));
                //jArray = new JSONObject(URLDecoder.decode(test[i],"ISO-8859-1"));
            }*/
            //String dec = URLEncoder.encode (result ,"UTF-8");



        } catch (Exception e) {

            Log.e("log_tag1", "Error in http connection " + e.toString());
            Log.e("log_tag2", "Error converting result " + e.toString());
            Log.e("log_tag3 JsonException", "Error parsing data " + e.toString());

        }finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return jArray;
    }
}
