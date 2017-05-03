package pe.edu.upc.petnet2.adapters;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pe.edu.upc.petnet2.PetnetApp;
import pe.edu.upc.petnet2.R;
import pe.edu.upc.petnet2.activities.AboutPetActivity;
import pe.edu.upc.petnet2.models.Pet;

/**
 * Created by Eric on 26/04/17.
 */

public class PetsAdapter extends RecyclerView.Adapter<PetsAdapter.ViewHolder> {
    private List<Pet> pets;
    @Override
    public PetsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_pets, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PetsAdapter.ViewHolder holder, final int position) {
        holder.nameTextView.setText(pets.get(position).getName());
        holder.animalTextView.setText(pets.get(position).getAnimal());
        holder.breedTextView.setText(pets.get(position).getBreed());
        holder.petCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PetnetApp.getInstance().setCurrentPet(pets.get(position));
                v.getContext().startActivity(new Intent(
                        v.getContext(), AboutPetActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return getPets().size();
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView petCardView;
        TextView nameTextView;
        TextView animalTextView;
        TextView breedTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            petCardView = (CardView) itemView.findViewById(R.id.petCardView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            animalTextView = (TextView) itemView.findViewById(R.id.animalTextView);
            breedTextView = (TextView) itemView.findViewById(R.id.breedTextView);

        }
    }
}
