package pe.edu.upc.petnet2.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.petnet2.PetnetApp;
import pe.edu.upc.petnet2.R;
import pe.edu.upc.petnet2.adapters.PetsAdapter;
import pe.edu.upc.petnet2.models.Pet;
import pe.edu.upc.petnet2.network.PetnetApi;

import static pe.edu.upc.petnet2.R.id.petsRecyclerView;

public class SearchActivity extends AppCompatActivity {
    EditText animalChoiceEditText;
    Button  searchButton;
    RecyclerView petsSearchRecyclerView;
    PetsAdapter petsAdapter;
    RecyclerView.LayoutManager  petsLayoutManager;
    List<Pet> pets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        animalChoiceEditText = (EditText) findViewById(R.id.animalChoiceEditText);
        searchButton = (Button) findViewById(R.id.searchButton);
        petsSearchRecyclerView = (RecyclerView) findViewById(R.id.petsSearchRecyclerView);
        petsLayoutManager = new LinearLayoutManager(this);
        petsAdapter = new PetsAdapter();
        pets = new ArrayList<>();
        petsAdapter.setPets(pets);
        petsSearchRecyclerView.setLayoutManager(petsLayoutManager);
        petsSearchRecyclerView.setAdapter(petsAdapter);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void search(){
        AndroidNetworking.get(PetnetApi.SEARCH_URL)
                .addQueryParameter("animal", animalChoiceEditText.getText().toString())
                .addHeaders("Authorization", PetnetApp.getInstance().getCurrentUser().getKey())
                .setPriority(Priority.LOW)
                .setTag("PetNet")
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {

                        pets = Pet.build(response);
                        petsAdapter.setPets(pets);
                        petsAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

}
