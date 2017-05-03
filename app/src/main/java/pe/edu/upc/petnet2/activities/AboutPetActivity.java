package pe.edu.upc.petnet2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;
import org.w3c.dom.Text;

import pe.edu.upc.petnet2.PetnetApp;
import pe.edu.upc.petnet2.R;
import pe.edu.upc.petnet2.models.Pet;
import pe.edu.upc.petnet2.network.PetnetApi;


public class AboutPetActivity extends AppCompatActivity {
    TextView aboutPetNameTextView, aboutPetAnimalTextView, aboutPetBreedTextView;
    Pet pet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_pet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        aboutPetNameTextView = (TextView) findViewById(R.id.aboutPetNameTextView);
        aboutPetAnimalTextView = (TextView) findViewById(R.id.aboutPetAnimalTextView);
        aboutPetBreedTextView = (TextView) findViewById(R.id.aboutPetBreedTextView);
        pet = PetnetApp.getInstance().getCurrentPet();
        aboutPetNameTextView.setText(pet.getName());
        aboutPetAnimalTextView.setText(pet.getAnimal());
        aboutPetBreedTextView.setText(pet.getBreed());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.viewPost);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutPetActivity.this, AboutPetPostActivity.class);
                startActivity(intent);
            }
        });

        FloatingActionButton delete = (FloatingActionButton) findViewById(R.id.deletePet);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePet();
                Snackbar.make(view, "Pet deleted", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                /*Intent intent = new Intent(AboutPetActivity.this, PetsActivity.class);
                startActivity(intent);*/
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void deletePet(){
        AndroidNetworking.delete(PetnetApi.PETS_ID_URL)
                .addPathParameter("pet_id", PetnetApp.getInstance().getCurrentPet().getId())
                .setContentType("*/*")
                .doNotCacheResponse()
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

}
