package com.vidyakalkendra.petsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PetActivity extends AppCompatActivity {

    FloatingActionButton addPet;
    RecyclerView petRV;
    PetDatabseHandler petDatabseHandler;
    Toolbar toolbar;
    PetAdapter petAdapter;
    ArrayList<Pet> petArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        petDatabseHandler = new PetDatabseHandler(PetActivity.this);
        setAdapter();

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Pets List");
        setSupportActionBar(toolbar);

        addPet = findViewById(R.id.newPet);
        addPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent petAdd = new Intent(PetActivity.this,AddPetActivity.class);
                startActivity(petAdd);
            }
        });


    }

    private void setAdapter() {

        petArrayList = petDatabseHandler.getPetList();

        petRV = findViewById(R.id.petsList);
        petAdapter = new PetAdapter(PetActivity.this, petArrayList);
//        petAdapter.notifyDataSetChanged();

        petRV.setAdapter(petAdapter);
        petRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuAddPet = menu.findItem(R.id.menuAddPet);
        MenuItem menuDeletePet = menu.findItem(R.id.menuDeletePet);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menuAddPet){
            Intent petAdd = new Intent(PetActivity.this,AddPetActivity.class);
            startActivity(petAdd);
        }

        if(item.getItemId()==R.id.menuDeletePet){
            petDatabseHandler.deleteAllPets();
            setAdapter();
            petAdapter.notifyDataSetChanged();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setAdapter();
        petAdapter.notifyDataSetChanged();
    }
}