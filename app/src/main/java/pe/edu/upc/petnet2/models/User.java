package pe.edu.upc.petnet2.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Eric on 26/04/17.
 */

public class User {
    private String key;
    private String email;
    private String password;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static User build(JSONObject jsonUser){
        User user = new User();
        try {
            user.setName(jsonUser.getString("name"));
            user.setEmail(jsonUser.getString("email"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }


}
