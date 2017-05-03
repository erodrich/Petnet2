package pe.edu.upc.petnet2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import pe.edu.upc.petnet2.PetnetApp;
import pe.edu.upc.petnet2.R;
import pe.edu.upc.petnet2.models.Pet;
import pe.edu.upc.petnet2.models.Post;
import pe.edu.upc.petnet2.network.PetnetApi;

public class AboutPetPostActivity extends AppCompatActivity {
    TextView aboutPostTitle, aboutPostContent;
    Post post;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_pet_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        aboutPostTitle = (TextView) findViewById(R.id.aboutPostTitle);
        aboutPostContent = (TextView) findViewById(R.id.aboutPostContent);
        post = new Post();
        updatePost();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.newPost);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutPetPostActivity.this, NewPostActivity.class);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void updatePost(){
        AndroidNetworking.get(PetnetApi.PETS_POST_URL)
                .addPathParameter("pet_id", PetnetApp.getInstance().getCurrentPet().getId())
                .addHeaders("Authorization", PetnetApp.getInstance().getCurrentUser().getKey())
                .setPriority(Priority.LOW)
                .setTag("HOLA")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        post = Post.build(response);
                        if(post == null) {
                            Toast toast = Toast.makeText(getApplicationContext(), "This pet has no post", Toast.LENGTH_LONG);
                            toast.show();
                        }else{

                            aboutPostTitle.setText(post.getTitle());
                            aboutPostContent.setText(post.getContent());
                        }

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

}
