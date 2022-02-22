package com.vidyakalkendra.petsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PetDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText nameDetail, breedDetail, weightDetail;
    Spinner typeDetail, genderDetail;
    Button saveDetail, deletePet;
    Intent intent;
    PetDatabseHandler petDatabseHandler;
    int id;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pet_details);
        intent = getIntent();

        petDatabseHandler = new PetDatabseHandler(this);

        id = intent.getIntExtra("id", -1);

        nameDetail = findViewById(R.id.petDetailNameBox);
        nameDetail.setText(intent.getStringExtra("name"));

        breedDetail = findViewById(R.id.petDetailBreedBox);
        breedDetail.setText(intent.getStringExtra("breed"));

        double weight = intent.getDoubleExtra("weight",0.0);
        weightDetail = findViewById(R.id.petDetailWeightBox);
        weightDetail.setText(String.valueOf(weight));

        /*typeDetail = findViewById(R.id.typeDetailSpinner);
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(this,R.array.Type, android.R.layout.simple_list_item_1);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeDetail.setAdapter(typeAdapter);
        typeDetail.setOnItemSelectedListener(this);*/

        /*genderDetail = findViewById(R.id.genderDetailSpinner);
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this,R.array.Gender, android.R.layout.simple_list_item_1);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderDetail.setAdapter(genderAdapter);
        genderDetail.setOnItemSelectedListener(this);*/

        saveDetail = findViewById(R.id.saveDetails);
        saveDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pet pet = new Pet(id, nameDetail.getText().toString().trim(), breedDetail.getText().toString().trim(), Double.parseDouble(weightDetail.getText().toString()));
                petDatabseHandler.updatePet(pet);
                finish();
            }
        });

        deletePet = findViewById(R.id.deletePet);
        deletePet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                petDatabseHandler.deletePet(id);
                finish();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String type = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
