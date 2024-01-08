package com.example.storage_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class view_example extends AppCompatActivity {
    EditText edtid,edtname,edtaddress;
    Button btnprev,btnnext,btnupdate,btndel;
    Cursor c;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_example);
        openDatabase();

        edtid = (EditText) findViewById(R.id.editText_ID);
        edtname= (EditText) findViewById(R.id.editText_name);
        edtaddress = (EditText) findViewById(R.id.editText_address);

        btnprev = (Button) findViewById(R.id.button_prev);
        btnnext = (Button) findViewById(R.id.button_next);
        btnupdate = (Button) findViewById(R.id.button_update);
        btndel = (Button) findViewById(R.id.button_delete);
        

        c = db.rawQuery("select * from person_detail",null);
        c.moveToFirst();
        showData();

        btnprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!c.isFirst())
                {
                    c.moveToPrevious();
                }
                showData();

            }
        });

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!c.isLast())
                {
                    c.moveToNext();
                }
                showData();

            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id  = edtid.getText().toString().trim();
                String name = edtname.getText().toString().trim();
                String add = edtaddress.getText().toString().trim();

                if(id.equals("") ||name.equals("") || add.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please fill of fields", Toast.LENGTH_SHORT).show();
                }else{
                    try {

                        String query = "update   person_detail  set p_name = '"+name+"',address = '"+add+"'  where id = "+id+";";
                        db.execSQL(query);
                        Toast.makeText(getApplicationContext(), "Record Updated", Toast.LENGTH_SHORT).show();
                        c = db.rawQuery("select * from person_detail",null);


                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Error Updation"+e.getClass(), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id  = edtid.getText().toString().trim();


                if(id.equals("") )
                {
                    Toast.makeText(getApplicationContext(), "Please fill of fields", Toast.LENGTH_SHORT).show();
                }else{
                    try {

                        String query = "delete from  person_detail    where id = "+id;
                        db.execSQL(query);
                        Toast.makeText(getApplicationContext(), "Record Deleted", Toast.LENGTH_SHORT).show();
                        c = db.rawQuery("select * from person_detail",null);



                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Error Deletion"+e.getClass(), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    private void showData() {
        String id = c.getString(0);
        String name= c.getString(1);
        String add = c.getString(2);

        edtid.setText(id);
        edtname.setText(name);
        edtaddress.setText(add);
    }

    private void openDatabase() {
        db = openOrCreateDatabase(Environment.getExternalStorageDirectory().getAbsolutePath()+"/PERSONDB", Context.MODE_PRIVATE,null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent i;
        if(item.getItemId() == R.id.external_storage_example){
            i= new Intent(getApplicationContext(),external_storage.class);
            startActivity(i);
            return true;
        } else if (item.getItemId() == R.id.media_player_example) {
            i= new Intent(getApplicationContext(),media_player.class);
            startActivity(i);
            return true;
        } else if (item.getItemId() == R.id.media_record_example) {
            i= new Intent(getApplicationContext(), media_recored.class);
            startActivity(i);
            return true;
        } else if (item.getItemId() == R.id.video_example) {
            i= new Intent(getApplicationContext(), video_example.class);
            startActivity(i);
            return true;
        } else if (item.getItemId() == R.id.db_example) {
            i= new Intent(getApplicationContext(), db_example.class);
            startActivity(i);
            return true;

        }else {
            Toast.makeText(this, "Invalid Selection", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}