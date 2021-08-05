package com.pd.databaseui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditSingleRecordActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    SQLiteDatabase sqLiteDataBaseObj;
    SQLiteHelper sqLiteHelper;
    String IdHoldertoViewRow;
    Cursor cursor;
    String SQLiteDataBaseQueryHolder;

    EditText editname, editlname;
    Button btn_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_edit_single_record );

        editname = (EditText) findViewById( R.id.editname );
        editlname = (EditText) findViewById( R.id.editlname );
        btn_update = (Button) findViewById( R.id.btn_update );

        sqLiteHelper = new SQLiteHelper( this );

        btn_update.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Getname = editname.getText().toString();
                String Getlname = editlname.getText().toString();

                OpenSQLiteDataBase();

                SQLiteDataBaseQueryHolder = " UPDATE " + SQLiteHelper.TABLE_NAME + " SET " + SQLiteHelper.Table_Column_1_Name + " = '" + Getname + "', " + SQLiteHelper.Table_Column_2_PhoneNumber + " = '" + Getlname + "' WHERE id = " + IdHoldertoViewRow + "";
                sqLiteDataBaseObj.execSQL( SQLiteDataBaseQueryHolder );
                sqLiteDatabase.close();
                Toast.makeText( EditSingleRecordActivity.this, "Data updtaed successfully", Toast.LENGTH_SHORT ).show();
            }
        } );
    }

    @Override
    protected void onResume() {
        ShowSRecordInEditText();
        super.onResume();
    }

    public void ShowSRecordInEditText() {

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        IdHoldertoViewRow = getIntent().getStringExtra( "EditId" );
        cursor = sqLiteDatabase.rawQuery( "SELECT * FROM " + SQLiteHelper.TABLE_NAME + " WHERE id = " + IdHoldertoViewRow + " ", null );

        if (cursor.moveToFirst()) {

            do {
                editname.setText( cursor.getString( cursor.getColumnIndex( SQLiteHelper.Table_Column_1_Name ) ) );
                editlname.setText( cursor.getString( cursor.getColumnIndex( SQLiteHelper.Table_Column_2_PhoneNumber ) ) );
            }
            while (cursor.moveToNext());
            cursor.close();
        }
    }

    public void OpenSQLiteDataBase() {
        sqLiteDataBaseObj = openOrCreateDatabase( SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null );
    }
}