package pe.edu.upc.petnet2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.OkHttpResponseListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Response;
import pe.edu.upc.petnet2.PetnetApp;
import pe.edu.upc.petnet2.R;
import pe.edu.upc.petnet2.models.Post;
import pe.edu.upc.petnet2.network.PetnetApi;

import static pe.edu.upc.petnet2.R.id.savePost;

public class NewPostActivity extends AppCompatActivity {
    EditText postTitle;
    EditText postContent;
    CheckBox postActive;
    String active;
    Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        post = new Post();

        postTitle = (EditText) findViewById(R.id.postTitle);
        postContent = (EditText) findViewById(R.id.postContent);
        postActive = (CheckBox) findViewById(R.id.postActive);



        FloatingActionButton fab = (FloatingActionButton) findViewById(savePost);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPostRequest();
                Intent intent = new Intent(NewPostActivity.this, AboutPetPostActivity.class);
                startActivity(intent);

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void newPostRequest(){
        if(postActive.isChecked()){
            active = "true";
        }else{
            active = "false";
        }
        AndroidNetworking.post(PetnetApi.NEW_POST_URL)
                .addPathParameter("pet_id", PetnetApp.getInstance().getCurrentPet().getId())
                .addQueryParameter("title", postTitle.getText().toString())
                .addQueryParameter("content", postContent.getText().toString())
                .addQueryParameter("active", active)
                .addHeaders("Authorization", PetnetApp.getInstance().getCurrentUser().getKey())
                .setPriority(Priority.LOW)
                .setTag("HOLA")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        post = Post.build(response);
                        PetnetApp.getInstance().setCurrentPost(post);
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });

    }


}
