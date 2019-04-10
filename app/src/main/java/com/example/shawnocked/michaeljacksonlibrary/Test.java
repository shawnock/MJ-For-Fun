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

public class Test{

    // TODO make this class send the whole json data

//    public AsyncResponse response = null;
//    ArrayList<String> images = new ArrayList<>();
//
//    @Override
//    protected ArrayList<String> doInBackground(Void... voids) {
//        try{
//            String data;
//
//            // standard urlconnection without third library involved
//            URL url = new URL("https://itunes.apple.com/search?term=Michael+jackson");
//            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//            InputStream inputStream = httpURLConnection.getInputStream();
//            BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader(inputStream));
//            StringBuffer stringBuffer = new StringBuffer();
//            String line;
//            while((line = bufferedInputStream.readLine()) != null){
//                // read each line of the Json file
//                stringBuffer.append(line);
//            }
//            data = stringBuffer.toString();
//
//            // Deal with JSON and get what we want
//            JSONObject obj = new JSONObject(data);
//            JSONArray results = obj.getJSONArray("results");
//            Log.i("json_results", results.toString());
//
//            for(int i = 0; i < results.length(); i++){
//                JSONObject jObj = results.getJSONObject(i);
//                String image = jObj.getString("artworkUrl100");
//                Log.i("images_url", image);
//                images.add(image);
//            }
//            return images;
//        }
//        catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return images;
//    }
//
//    @Override
//    protected void onPostExecute(ArrayList<String> result) {
//        super.onPostExecute(result);
//        response.processFinish(result); // return the result
//    }
}