package pe.edu.upc.petnet2.network;

import pe.edu.upc.petnet2.models.Pet;
import pe.edu.upc.petnet2.models.Post;
import pe.edu.upc.petnet2.models.Result;
import pe.edu.upc.petnet2.models.User;

/**
 * Created by Eric on 26/04/17.
 */

public class PetnetApi {
    public static String LOGIN_URL = "http://evening-bastion-58711.herokuapp.com/auth/login";
    public static String SIGNUP_URL = "http://evening-bastion-58711.herokuapp.com/signup";
    public static String PETS_URL = "http://evening-bastion-58711.herokuapp.com/pets";
    public static String PETS_ID_URL = "http://evening-bastion-58711.herokuapp.com/pets/{pet_id}";
    public static String PETS_POST_URL = "http://evening-bastion-58711.herokuapp.com/pets/{pet_id}/posts";
    public static String NEW_POST_URL = "http://evening-bastion-58711.herokuapp.com/pets/{pet_id}/posts";
    public static String SEARCH_URL = "http://evening-bastion-58711.herokuapp.com/search";

    private Pet currentPet;
    private User currentUser;
    private Post currentPost;
    private Result currentResult;


    public Pet getCurrentPet() {
        return currentPet;
    }

    public void setCurrentPet(Pet currentPet) {
        this.currentPet = currentPet;
    }

    public User getCurrentUser() { return currentUser; }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Post getCurrentPost() { return currentPost; }

    public void setCurrentPost(Post currentPost) { this.currentPost = currentPost; }

    public Result getCurrentResult() {
        return currentResult;
    }

    public void setCurrentResult(Result currentResult) {
        this.currentResult = currentResult;
    }
}
