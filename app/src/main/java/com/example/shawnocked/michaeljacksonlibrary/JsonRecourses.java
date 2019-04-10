package com.example.shawnocked.michaeljacksonlibrary;

import android.os.AsyncTask;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/*
 Class to for getting Json data and forming a list of image urls.
 */

public class JsonRecourses extends AsyncTask<Void, Void, String> {

    public AsyncResponse response = null;
    String data; // json data

    @Override
    protected String doInBackground(Void... voids) {
        try{

            // standard urlconnection without third library involved
            URL url = new URL("https://itunes.apple.com/search?term=Michael+jackson");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while((line = bufferedInputStream.readLine()) != null){
                // read each line of the Json file
                stringBuffer.append(line);
            }
            data = stringBuffer.toString();
            return data;

        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        response.processFinish(result); // return the result
    }
}
