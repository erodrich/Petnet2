package pe.edu.upc.petnet2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pe.edu.upc.petnet2.PetnetApp;
import pe.edu.upc.petnet2.R;
import pe.edu.upc.petnet2.models.User;
import pe.edu.upc.petnet2.network.PetnetApi;

import static android.R.attr.key;

public class SignUpActivity extends AppCompatActivity {
    Button registerButton;
    EditText nameEditText, emailEditText, passEditText, passConfirmEditText;
    User user;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        registerButton = (Button) findViewById(R.id.registerButton);
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passEditText = (EditText) findViewById(R.id.passEditText);
        passConfirmEditText = (EditText) findViewById(R.id.passConfirmEditText);
        user = new User();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                key = signUpRequest();
                if(key != null){
                    PetnetApp.getInstance().setCurrentUser(user);
                    Toast.makeText(getApplicationContext(),"Sign Up Success", Toast.LENGTH_LONG);
                    Intent intent = new Intent(SignUpActivity.this, PetsActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Sign Up failed", Toast.LENGTH_LONG);
                }
            }
        });



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public String signUpRequest(){
        user.setName(nameEditText.getText().toString());
        user.setEmail(emailEditText.getText().toString());
        AndroidNetworking.post(PetnetApi.SIGNUP_URL)
                .addQueryParameter("name", nameEditText.getText().toString())
                .addQueryParameter("email", emailEditText.getText().toString())
                .addQueryParameter("password", passEditText.getText().toString())
                .addQueryParameter("password_confirm", passConfirmEditText.getText().toString())
                .setPriority(Priority.LOW)
                .setTag("Petnet")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            user.setKey(response.getString("auth_token"));
                            user.setName(response.getJSONObject("user").getString("name"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
        return user.getKey();
    }

}
