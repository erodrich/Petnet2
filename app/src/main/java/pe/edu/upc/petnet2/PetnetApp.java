package pe.edu.upc.petnet2;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

import pe.edu.upc.petnet2.models.Pet;
import pe.edu.upc.petnet2.models.Post;
import pe.edu.upc.petnet2.models.Result;
import pe.edu.upc.petnet2.models.User;
import pe.edu.upc.petnet2.network.PetnetApi;

/**
 * Created by Eric on 26/04/17.
 */

public class PetnetApp extends Application{
    // Singleton Pattern Implementation
    private static PetnetApp instance;
    PetnetApi petnetApi = new PetnetApi();

    public PetnetApp() {
        super();
        instance = this;
    }

    public static PetnetApp getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }

    public void setCurrentPet(Pet pet){
        petnetApi.setCurrentPet(pet);
    }
    public Pet getCurrentPet(){
        return petnetApi.getCurrentPet();
    }
    public void setCurrentUser(User user){
        petnetApi.setCurrentUser(user);
    }
    public User getCurrentUser(){ return petnetApi.getCurrentUser(); }
    public void setCurrentPost(Post post) { petnetApi.setCurrentPost(post);}
    public Post getCurrentPost(){ return petnetApi.getCurrentPost();}
    public void setCurrentResult(Result result){ petnetApi.setCurrentResult(result);}
    public Result getCurrentResult() { return petnetApi.getCurrentResult(); }
}
