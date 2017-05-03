package pe.edu.upc.petnet2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import pe.edu.upc.petnet2.PetnetApp;
import pe.edu.upc.petnet2.R;
import pe.edu.upc.petnet2.models.Pet;
import pe.edu.upc.petnet2.models.Post;
import pe.edu.upc.petnet2.network.PetnetApi;

public class NewPetActivity extends AppCompatActivity {
    EditText petName, petAnimal, petBreed;
    Pet pet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        petName = (EditText) findViewById(R.id.petName);
        petAnimal = (EditText) findViewById(R.id.petAnimal);
        petBreed = (EditText) findViewById(R.id.petBreed);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.savePet);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPet();
                Snackbar.make(view, "New Pet added", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(NewPetActivity.this, PetsActivity.class);
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void newPet(){
        AndroidNetworking.post(PetnetApi.PETS_URL)
                .addQueryParameter("name", petName.getText().toString())
                .addQueryParameter("animal", petAnimal.getText().toString())
                .addQueryParameter("breed", petBreed.getText().toString())
                .addHeaders("Authorization", PetnetApp.getInstance().getCurrentUser().getKey())
                .setPriority(Priority.LOW)
                .setTag("HOLA")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        pet = Pet.build(response);
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

}
