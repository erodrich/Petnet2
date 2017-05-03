package pe.edu.upc.petnet2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import pe.edu.upc.petnet2.PetnetApp;
import pe.edu.upc.petnet2.R;
import pe.edu.upc.petnet2.models.User;
import pe.edu.upc.petnet2.network.PetnetApi;

public class MainActivity extends AppCompatActivity {

    Button signInButton, signUpButton;
    EditText emailEditText, passEditText;
    String key;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user = new User();
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passEditText = (EditText) findViewById(R.id.passEditText);
        signInButton = (Button) findViewById(R.id.loginButton);
        signUpButton = (Button) findViewById(R.id.signUpButton);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                key = loginRequest();
                if(key != null){
                    PetnetApp.getInstance().setCurrentUser(user);
                    Toast.makeText(getApplicationContext(),"Login Success", Toast.LENGTH_LONG);
                    Intent intent = new Intent(MainActivity.this, PetsActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Login failed", Toast.LENGTH_LONG);
                }

            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    public String loginRequest(){
        user.setEmail(emailEditText.getText().toString());
        user.setPassword(passEditText.getText().toString());
        AndroidNetworking.post(PetnetApi.LOGIN_URL)
                .addQueryParameter("email", emailEditText.getText().toString())
                .addQueryParameter("password", passEditText.getText().toString())
                .setPriority(Priority.LOW)
                .setTag("HOLA")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            user.setKey(response.getString("auth_token"));
                            user.setName(response.getJSONObject("user").getString("name"));
                            //PetnetApp.getInstance().setCurrentUser(user);
                            //key = response.getString("auth_token");
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
/*
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    */
}
