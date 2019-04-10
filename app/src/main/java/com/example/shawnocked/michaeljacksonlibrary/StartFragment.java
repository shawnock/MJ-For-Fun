package com.example.shawnocked.michaeljacksonlibrary;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/*
 * a fragment of main activity which receive data back from imageResources asyncTask
 * put asyncTask in a fragment because fragment's life cycle go with the activity but
 * asyncTask itself isn't.
 */

public class StartFragment extends Fragment implements AsyncResponse{

    JsonRecourses recourses;
    String data;
    ArrayList<String> imagesStore = new ArrayList<>();   // list contains image urls
    ArrayList<String> previewURLList = new ArrayList<>(); // list contains prev urls
    ArrayList<String> trackNameList = new ArrayList<>(); // list contains track names
    ArrayList<String> collectionPriceList = new ArrayList<>(); // list contains collection prices
    ArrayList<String> releaseDateList = new ArrayList<>(); // list contains release dates

    TextView title;
    ImageView background;
    Button start;
    Button about;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_start, container, false);

        title = (TextView) rootview.findViewById(R.id.appTitle);
        background = (ImageView) rootview.findViewById(R.id.background);
        start = (Button) rootview.findViewById(R.id.startApp);
        about = (Button) rootview.findViewById(R.id.aboutApp);
        recourses = new JsonRecourses();

        // set response back to this class
        recourses.response = this;
        // execute the async task
        recourses.execute();

        // once user clicks on start, we move on to RecyclerViewActivity to show images
        // use intent to send data
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RecyclerViewActivity.class);
                intent.putExtra("IMAGE", imagesStore);
                intent.putExtra("TRACKNAME",trackNameList);
                intent.putExtra("PREVIEWLIST", previewURLList);
                intent.putExtra("COLLECTIONLIST", collectionPriceList);
                intent.putExtra("DATELIST",releaseDateList);
                getActivity().startActivity(intent);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "This is a simple app to show you some Michael Jackson songs -- SpinningShawnny & ideaTree",
                        Toast.LENGTH_LONG).show();
            }
        });

        return rootview;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retain this fragment across configuration changes.
        setRetainInstance(true);
    }

    @Override
    public void onPause() {
        super.onPause();

        // if the activity is finished, we cancel the background tasks
        // since async's life cycle doesn't tie to activity
        if ((recourses != null) && (getActivity().isFinishing())){
            recourses.cancel(false);
        }

    }

    @Override
    public void processFinish(String output) {
        // Here we will receive the result fired from async class
        // of onPostExecute() method.
        data = output;
        Log.i("json_output", data);
        try {
            JSONObject obj = new JSONObject(data);
            JSONArray results = obj.getJSONArray("results");
            Log.i("json_results", results.toString());

            for(int i = 0; i < results.length(); i++){
                // address json here
                JSONObject jObj = results.getJSONObject(i);
                String image = jObj.getString("artworkUrl100");
                String trackName = jObj.getString("trackName");
                String preview = jObj.getString("previewUrl");
                String collectionPrice = jObj.getString("collectionPrice");
                String releaseDate = jObj.getString("releaseDate");

                imagesStore.add(image);
                trackNameList.add(trackName);
                previewURLList.add(preview);
                collectionPriceList.add(collectionPrice);
                releaseDateList.add(releaseDate);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
