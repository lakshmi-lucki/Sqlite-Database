package com.pd.databaseui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.BatchUpdateException;

public class ShowSingleRecordActivity extends AppCompatActivity {
    String SQLiteDataBaseQueryHolder;

    SQLiteDatabase sqLiteDatabaseObj;    //to open db
    SQLiteHelper sqLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    String IdHoldertoViewRow;
    Cursor cursor;
    TextView tvshow_id, tvshow_name, tvshow_lname;
    Button btn_del, btn_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_show_single_record );

        tvshow_id = (TextView) findViewById( R.id.tvshow_id );
        tvshow_name = (TextView) findViewById( R.id.tvshow_name );
        tvshow_lname = (TextView) findViewById( R.id.tvshow_lname );

        btn_del = (Button) findViewById( R.id.btn_del );
        btn_edit = (Button) findViewById( R.id.btn_edit );

        sqLiteHelper = new SQLiteHelper( this ); // impt

        // FOR DELETING THE ROW DATA
        btn_del.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OpenSQLiteDataBase(); // opening the db with this function

                SQLiteDataBaseQueryHolder = "DELETE FROM " + SQLiteHelper.TABLE_NAME + " where id= " + IdHoldertoViewRow + "";

                sqLiteDatabase.execSQL( SQLiteDataBaseQueryHolder );
                sqLiteDatabase.close();
                finish();
            }
        } );

        // FOR EDITING ON ROW DATA
        btn_edit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( getApplicationContext(), EditSingleRecordActivity.class );
                // while invoking it should pass data as well
                intent.putExtra( "EditId", IdHoldertoViewRow );
                startActivity( intent );
            }
        } );
    }

    @Override
    protected void onResume() {
        //listview show

        SingleShowRecordInTextView();
        super.onResume();
    }

    public void SingleShowRecordInTextView() {

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        IdHoldertoViewRow = getIntent().getStringExtra( "ListViewClickedItemValue" ); // key name

        cursor = sqLiteDatabase.rawQuery( "SELECT * FROM " + SQLiteHelper.TABLE_NAME + " where id=" + IdHoldertoViewRow + "", null );
        if (cursor.moveToNext()) {

            tvshow_id.setText( cursor.getString( cursor.getColumnIndex( SQLiteHelper.Table_Column_ID ) ) );
            tvshow_name.setText( cursor.getString( cursor.getColumnIndex( SQLiteHelper.Table_Column_1_Name ) ) );
            tvshow_lname.setText( cursor.getString( cursor.getColumnIndex( SQLiteHelper.Table_Column_2_PhoneNumber ) ) );
        }
        while (cursor.moveToNext()) ; // ????
        cursor.close();

    }

    public void OpenSQLiteDataBase() {
        sqLiteDatabaseObj = openOrCreateDatabase( SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null );
    }
}