package com.atilsamancioglu.mynewdatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Musicians", MODE_PRIVATE,null);

            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS musicians");

            sqLiteDatabase.execSQL("CREATE TABLE musicians (uniqueid INTEGER PRIMARY KEY AUTOINCREMENT ,name VARCHAR, age INT(2))");

            sqLiteDatabase.execSQL("INSERT INTO musicians (name, age) VALUES ('Lars', 60)");

            sqLiteDatabase.execSQL("INSERT INTO musicians (name, age) VALUES ('James', 50)");

            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM musicians", null );

            int nameIx = cursor.getColumnIndex("name");
            int ageIx = cursor.getColumnIndex("age");
            int idIx = cursor.getColumnIndex("uniqueid");

            while ( cursor.moveToNext()) {

                System.out.println("Name: " + cursor.getString(nameIx));
                System.out.println("Age: " + cursor.getInt(ageIx));
                System.out.println("ID: " + cursor.getInt(idIx));
            }

            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
