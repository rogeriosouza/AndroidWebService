package service.com.br.androidwebservice;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import java.util.ArrayList;
import java.util.HashMap;



public class MainActivity extends AppCompatActivity {

    JSONObject jsonobject;
    JSONArray jsonarray;

    ListView listview;
    ListViewAdapter adapter;
    ProgressDialog mProgressDialog;
    ArrayList<HashMap<String, String>> arraylist;
    Handler handler;
    static String AUTHOR = "author";
    static String NUMCOMMENTS = "num_comments";
    static String SCORE = "score";
    static String FLAG = "flag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_main);

        // DownloadJSON AsyncTask
        new DownloadJSON().execute();
    }

    private class DownloadJSON extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressDialog = new ProgressDialog(MainActivity.this);

            mProgressDialog.setTitle("NetBee");
            mProgressDialog.setMessage("Aguarde...");
            //mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }


        @Override
        protected Void doInBackground(Void... param) {

            arraylist = new ArrayList<HashMap<String, String>>();
            //url json
            jsonobject = JSONfunctions.getJSONfromURL("https://www.reddit.com/r/Android/new/.json");
            try {
                // Locate       the array name in JSON

                JSONObject data = jsonobject.getJSONObject("data");
                JSONArray hotTopics = data.getJSONArray("children");

                for (int i = 0; i < hotTopics.length(); i++) {
                    JSONObject topic = hotTopics.getJSONObject(i).getJSONObject("data");
                    HashMap<String, String> map = new HashMap<String, String>();

                    // Retrive JSON Objects
                    if (topic.getString("author") !=null ){
                        map.put("author", topic.getString("author"));
                    }else{
                        map.put("author", " ");

                    }
                    if (topic.getString("num_comments") !=null ){
                        map.put("num_comments", topic.getString("num_comments"));
                    }else{
                        map.put("num_comments", "0");

                    }
                    if ((topic.getString("score")) !=null ){
                        map.put("score", topic.getString("score"));
                    }else{
                        map.put("score", "0");

                    }
                        map.put("flag", topic.getString("thumbnail"));


                    // Set the JSON Objects into the array
                    arraylist.add(map);
                }
            } catch (JSONException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void args) {

            listview = (ListView) findViewById(R.id.listview);

            adapter = new ListViewAdapter (MainActivity.this, arraylist);


            listview.setAdapter(adapter);
            mProgressDialog.dismiss();

   /*         new Handler().postDelayed(new Runnable() {
                @Override public void run() {
                    //new myAsync().execute();
                    System.out.print("dsadada");
                }

            }, 10000);*/

        }







    }
}
