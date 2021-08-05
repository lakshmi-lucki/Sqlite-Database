package com.pd.databaseui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDataBaseObj;
    EditText edtFirstName, edtLastName;
    String NameHolder, NumberHolder, SQLiteDataBaseQueryHolder;     // variables for data
    Button btnEnterData, btnDisplayData;
    Boolean EditTextEmptyHold;          //whether data is available

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        btnEnterData = (Button) findViewById( R.id.btn_insert );
        btnDisplayData = (Button) findViewById( R.id.btn_display );

        edtFirstName = (EditText) findViewById( R.id.edt_firstname );
        edtLastName = (EditText) findViewById( R.id.edt_lastname );

        // ON THE CLICK OF INSERT BTN FOLLOWING FUNCTIONS WILL BE CALLED
        btnEnterData.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 5 functions are called in main method

                SQLiteDataBaseBuild();  //create DB

                SQLiteTableBuild();     //create table

                CheckEditTextStatus();      //checking whether data present at edt

                InsertDataSQLiteDataBase();     // For inserting data into table at db

                EmptyEditTextAfterDataInsert();     //clearing data or refresh
            }
        } );

        btnDisplayData.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText( MainActivity.this, "SUCCESSFUL", Toast.LENGTH_SHORT ).show();
                Intent intent = new Intent( MainActivity.this, Dsqlitedata.class );
                startActivity( intent );
            }
        } );
    }

    public void SQLiteDataBaseBuild() {
        sqLiteDataBaseObj = openOrCreateDatabase( "AndroidJSONDataBase", MODE_PRIVATE, null );
    }

    public void SQLiteTableBuild() {

        sqLiteDataBaseObj.execSQL( " CREATE TABLE IF NOT EXISTS AndroidJSONTable(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR, phone_number VARCHAR);" );
    }

    // to put data inside the table
    public void CheckEditTextStatus() {
        NameHolder = edtFirstName.getText().toString();
        NumberHolder = edtLastName.getText().toString();

        if (TextUtils.isEmpty( NameHolder ) || TextUtils.isEmpty( NumberHolder )) {
            EditTextEmptyHold = false;
        } else {
            EditTextEmptyHold = true;
        }
    }

    public void InsertDataSQLiteDataBase() {

        if (EditTextEmptyHold == true) {                                                              //dynamically inserting the data so syntax is:
            // a string to store then execute in next step                                                   // id is filled automatically
            SQLiteDataBaseQueryHolder = "INSERT INTO AndroidJSONTable(name, phone_number) VALUES ('" + NameHolder + "', '" + NumberHolder + "');";
            sqLiteDataBaseObj.execSQL( SQLiteDataBaseQueryHolder );

            Toast.makeText( this, "Data Inserted Successfully.", Toast.LENGTH_SHORT ).show();
        } else {
            Toast.makeText( this, "Please fill all the required fields.", Toast.LENGTH_SHORT ).show();
        }
    }

    public void EmptyEditTextAfterDataInsert() {
        edtFirstName.getText().clear();
        edtLastName.getText().clear();
    }


}