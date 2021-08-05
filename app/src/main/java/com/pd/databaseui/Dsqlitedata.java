
package com.pd.databaseui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Dsqlitedata extends AppCompatActivity {

    SQLiteHelper sqLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    CoustomListAdapter listAdapter;
    ListView LISTVIEW;

    ArrayList<String> ID_Array;
    ArrayList<String> NAME_Array;
    ArrayList<String> PHONE_NUMBER_Array;

    ArrayList<String> ListViewClickItemArray = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_dsqlitedata );

        LISTVIEW = (ListView) findViewById( R.id.listView1 );

        ID_Array = new ArrayList<String>();

        NAME_Array = new ArrayList<String>();

        PHONE_NUMBER_Array = new ArrayList<String>();

        sqLiteHelper = new SQLiteHelper( this );

        // for singlerow view---
        LISTVIEW.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // TODO Auto-generated method stub

                Intent intent = new Intent( getApplicationContext(), ShowSingleRecordActivity.class );
                intent.putExtra( "ListViewClickedItemValue", ListViewClickItemArray.get( position ).toString() );
                startActivity( intent );
            }
        } );
    }

    @Override
    protected void onResume() {

        ShowSQLiteDBdata();

        super.onResume();
    }

    private void ShowSQLiteDBdata() {

        // to write inside the table created

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        //cursor is used wrt to position so *frpm or id[1] ..

        cursor = sqLiteDatabase.rawQuery( "SELECT * FROM " + SQLiteHelper.TABLE_NAME + "", null );

        // why ??
        ID_Array.clear();
        NAME_Array.clear();
        PHONE_NUMBER_Array.clear();


        if (cursor.moveToFirst()) {
            do {
                //  ??? columnindes - position & from the which table

                Toast.makeText( Dsqlitedata.this, "DISPLAYYY", Toast.LENGTH_SHORT ).show();

                ID_Array.add( cursor.getString( cursor.getColumnIndex( SQLiteHelper.Table_Column_ID ) ) );

                //Inserting Column ID into Array to Use at ListView Click Listener Method.

                ListViewClickItemArray.add( cursor.getString( cursor.getColumnIndex( SQLiteHelper.Table_Column_ID ) ) );

                NAME_Array.add( cursor.getString( cursor.getColumnIndex( SQLiteHelper.Table_Column_1_Name ) ) );

                PHONE_NUMBER_Array.add( cursor.getString( cursor.getColumnIndex( SQLiteHelper.Table_Column_2_PhoneNumber ) ) );


            } while (cursor.moveToNext());
        }

        listAdapter = new CoustomListAdapter( Dsqlitedata.this, ID_Array, NAME_Array, PHONE_NUMBER_Array );
        LISTVIEW.setAdapter( listAdapter );

        cursor.close();
    }
}