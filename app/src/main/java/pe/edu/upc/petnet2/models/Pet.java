package pe.edu.upc.petnet2.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eric on 26/04/17.
 */

public class Pet {
    private String id;
    private String name;
    private String animal;
    private String breed;


    public String getName() {
        return name;
    }

    public Pet setName(String name) {
        this.name = name;
        return this;
    }

    public String getAnimal() {
        return animal;
    }

    public Pet setAnimal(String animal) {
        this.animal = animal;
        return this;
    }

    public String getBreed() {
        return breed;
    }

    public Pet setBreed(String breed) {
        this.breed = breed;
        return this;
    }

    public static Pet build(JSONObject jsonPet){
        Pet pet = new Pet();
        try {
            pet.setName(jsonPet.getString("name"))
                    .setAnimal(jsonPet.getString("animal"))
                    .setBreed(jsonPet.getString("breed"))
                    .setId(jsonPet.getString("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pet;
    }

    public static List<Pet> build(JSONArray jsonArray){
        List<Pet> pets = new ArrayList<>();
        int length = jsonArray.length();
        for(int i = 0;i < length; i++){
            try {
                pets.add(Pet.build(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return pets;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
