package com.vidyakalkendra.petsapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddPetActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText name, breed, weight;
    Button saveBtn;
    Spinner genderSpinner, typeSpinner;
//    String gender[], type[];
    PetDatabseHandler petDatabaseHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_pet);

        name = findViewById(R.id.petNameBox);
        breed = findViewById(R.id.petBreedBox);
        weight = findViewById(R.id.petWeightBox);
        petDatabaseHandler = new PetDatabseHandler(AddPetActivity.this);

        typeSpinner = findViewById(R.id.typeSpinner);
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(this,R.array.Type, android.R.layout.simple_list_item_1);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);
        typeSpinner.setOnItemSelectedListener(this);


        genderSpinner = findViewById(R.id.genderSpinner);
        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(this,R.array.Gender, android.R.layout.simple_list_item_1);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(genderAdapter);
        genderSpinner.setOnItemSelectedListener(this);


        saveBtn = findViewById(R.id.savePet);
        saveBtn.setOnClickListener(view -> {
            String petName = name.getText().toString();
            String breedType = breed.getText().toString();
            double petWeight = Double.parseDouble(weight.getText().toString());
            String type = typeSpinner.getSelectedItem().toString();
            String gender = genderSpinner.getSelectedItem().toString();
            Pet pet = new Pet(petName,breedType,petWeight,type,gender);
            petDatabaseHandler.addPet(pet);
            this.finish();
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String type = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
