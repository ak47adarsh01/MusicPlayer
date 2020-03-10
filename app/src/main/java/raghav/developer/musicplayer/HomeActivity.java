package raghav.developer.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

  private final String JSON_URL = "https://intern.mandin.in/songs.json";
  private JsonArrayRequest request;
  private RequestQueue requestQueue;
  List<model> modelList;
  RecyclerView recyclerView;
  ImageView img;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    //getSupportActionBar().hide();

    modelList = new ArrayList<>();
    recyclerView = findViewById(R.id.recycler_view);
    jsonRequest();

  }

  private void jsonRequest() {

    {

      request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {

          JSONObject jsonObject = null;
          //Log.i("Items in JSON=",Integer.toString(response.length()));

          for (int i = 0; i < response.length(); i++) {


            try {
              jsonObject = response.getJSONObject(i);
              model m = new model();
              m.setSongName(jsonObject.getString("song"));
              m.setArtist(jsonObject.getString("artists"));
              m.setSongURL(jsonObject.getString("url"));
              m.setCoverURL(jsonObject.getString("cover_image"));

              //Log.i("Item"+i,"Title="+m.getTitle());
              modelList.add(m);

            } catch (JSONException e) {
              e.printStackTrace();
            }


          }

          setuprecyclerview(modelList);

        }
      }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
          //Log.i("Error","Volley Error encountered");
        }
      });


      requestQueue = Volley.newRequestQueue(HomeActivity.this);
      requestQueue.add(request);


    }

  }

  private void setuprecyclerview(List<model> modelList) {


    RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this, modelList);
    recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    recyclerView.setAdapter(myadapter);

  }

}

