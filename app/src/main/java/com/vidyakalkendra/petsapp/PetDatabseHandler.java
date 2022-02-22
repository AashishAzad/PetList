package com.vidyakalkendra.petsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class PetDatabseHandler extends SQLiteOpenHelper {
    public PetDatabseHandler(Context context) {
        super(context, PetDatabase.DB_TABLE_NAME, null, PetDatabase.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE " + PetDatabase.DB_TABLE_NAME + "(" +
                PetDatabase.PET_ID + " INTEGER PRIMARY KEY ," +
                PetDatabase.PET_NAME + " TEXT," +
                PetDatabase.PET_BREED + " TEXT," +
                PetDatabase.PET_WEIGHT + " INTEGER," +
                PetDatabase.PET_TYPE + " TEXT," +
                PetDatabase.PET_GENDER + " TEXT" + ")";

        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public ArrayList<Pet> getPetList(){
        ArrayList<Pet> petList = new ArrayList<>();
        String query = "SELECT * FROM "+ PetDatabase.DB_TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do {
                Pet pet = new Pet();
                pet.setId(cursor.getInt(cursor.getColumnIndexOrThrow(PetDatabase.PET_ID)));
                pet.setName(cursor.getString(cursor.getColumnIndexOrThrow(PetDatabase.PET_NAME)));
                pet.setBreed(cursor.getString(cursor.getColumnIndexOrThrow(PetDatabase.PET_BREED)));
                pet.setWeight(Double.parseDouble(cursor.getString(cursor.getColumnIndexOrThrow(PetDatabase.PET_WEIGHT))));
                pet.setType(cursor.getString(cursor.getColumnIndexOrThrow(PetDatabase.PET_TYPE)));
                pet.setGender(cursor.getString(cursor.getColumnIndexOrThrow(PetDatabase.PET_GENDER)));

                petList.add(pet);

            }while(cursor.moveToNext());
        }

        return petList;
    }

    public void addPet(Pet pet) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(PetDatabase.PET_NAME, pet.getName());
        values.put(PetDatabase.PET_BREED, pet.getBreed());
        values.put(PetDatabase.PET_WEIGHT, pet.getWeight());
        values.put(PetDatabase.PET_TYPE, pet.getType());
        values.put(PetDatabase.PET_GENDER, pet.getGender());

        sqLiteDatabase.insert(PetDatabase.DB_TABLE_NAME, null, values);
        sqLiteDatabase.close();
    }

    public void updatePet(Pet pet){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PetDatabase.PET_NAME, pet.getName());
        values.put(PetDatabase.PET_BREED, pet.getBreed());
        values.put(PetDatabase.PET_WEIGHT, pet.getWeight());
        //values.put(PetDatabase.PET_TYPE, pet.getType());
        //values.put(PetDatabase.PET_GENDER, pet.getGender());

        sqLiteDatabase.update(PetDatabase.DB_TABLE_NAME,values,PetDatabase.PET_ID + " = " + pet.getId(),null);
    }

    public void deletePet(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(PetDatabase.DB_TABLE_NAME,PetDatabase.PET_ID+"=?",new String[]{String.valueOf(id)});
        sqLiteDatabase.close();
    }

    public void deleteAllPets(){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM "+ PetDatabase.DB_TABLE_NAME;
        db.execSQL(query);
    }

}
