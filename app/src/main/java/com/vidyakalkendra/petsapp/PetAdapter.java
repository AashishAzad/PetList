package com.vidyakalkendra.petsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.ViewHolder> {

    public Context context;
    public ArrayList<Pet> petArrayList;

    public PetAdapter(Context context, ArrayList<Pet> petArrayList) {
        this.context = context;
        this.petArrayList = petArrayList;
    }


    @NonNull
    @Override
    public PetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pets_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetAdapter.ViewHolder holder, int position) {
        Pet pet = petArrayList.get(position);
        holder.name.setText(pet.getName());
        holder.breed.setText(pet.getBreed());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent petDetail = new Intent(context,PetDetails.class);
                petDetail.putExtra("id",pet.getId());
                petDetail.putExtra("name",pet.getName());
                petDetail.putExtra("breed",pet.getBreed());
                petDetail.putExtra("weight",pet.getWeight());
                petDetail.putExtra("type",pet.getType());
                petDetail.putExtra("gender",pet.getGender());
                context.startActivity(petDetail);
            }
        });
    }

    @Override
    public int getItemCount() {

        return petArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name, breed;
        TextView detailsName, detailsBreed, detailsWeight;
        Spinner detailsType, detailsGender;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.petName);
            breed = itemView.findViewById(R.id.petBreed);

        }

    }
}
