package pe.edu.upc.petnet2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.petnet2.PetnetApp;
import pe.edu.upc.petnet2.adapters.PetsAdapter;
import pe.edu.upc.petnet2.R;
import pe.edu.upc.petnet2.models.Pet;
import pe.edu.upc.petnet2.models.User;
import pe.edu.upc.petnet2.network.PetnetApi;

public class PetsActivity extends AppCompatActivity {
    TextView userNameTextView;
    RecyclerView petsRecyclerView;
    PetsAdapter petsAdapter;
    RecyclerView.LayoutManager  petsLayoutManager;
    List<Pet> pets;
    Pet pet;
    int spanCount;
    private static String TAG = "Petnet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userNameTextView = (TextView) findViewById(R.id.userNameTextView);
        petsRecyclerView = (RecyclerView) findViewById(R.id.petsRecyclerView);
        petsLayoutManager = new LinearLayoutManager(this);
        petsAdapter = new PetsAdapter();
        pets = new ArrayList<>();
        petsAdapter.setPets(pets);
        petsRecyclerView.setLayoutManager(petsLayoutManager);
        petsRecyclerView.setAdapter(petsAdapter);
        userNameTextView.setText(PetnetApp.getInstance().getCurrentUser().getName());
        updatePets();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.newPet);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PetsActivity.this, NewPetActivity.class);
                startActivity(intent);
            }
        });
    }

    private void updatePets(){
        AndroidNetworking.get(PetnetApi.PETS_URL)
                .addHeaders("Authorization", PetnetApp.getInstance().getCurrentUser().getKey())
                .setPriority(Priority.LOW)
                .setTag(TAG)
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {
            Intent intent = new Intent(PetsActivity.this, SearchActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
