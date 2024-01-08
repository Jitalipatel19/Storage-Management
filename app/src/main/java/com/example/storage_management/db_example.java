package com.example.storage_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class db_example extends AppCompatActivity implements View.OnClickListener {
    EditText edtname,edtadd;

    Button btnsave,btnview;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_example);


        if(Build.VERSION.SDK_INT>=23)
        {
            if(checkPermission())
            {
                Toast.makeText(getApplicationContext(), "Persmission Already Granted", Toast.LENGTH_SHORT).show();
            }else {
                requestPermissions();
            }
        }

        edtname = (EditText) findViewById(R.id.editText_name);
        edtadd = (EditText) findViewById(R.id.editText_add);

        btnsave = (Button) findViewById(R.id.button_save);
        btnview = (Button) findViewById(R.id.button_view);

        btnsave.setOnClickListener(this);
        btnview.setOnClickListener(this);
    }

    private void requestPermissions() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(db_example.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE))
        {
            Toast.makeText(getApplicationContext(), "Write External Storage Permission Granted", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this,new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1)
        {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
                createDatabase();
            }else {
                Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(db_example.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(result == PackageManager.PERMISSION_GRANTED)
        {
            createDatabase();
            return true;
        }else {
            return false;
        }
    }

    private void createDatabase() {
        db = openOrCreateDatabase(Environment.getExternalStorageDirectory().getAbsolutePath()+"/PERSONDB", Context.MODE_PRIVATE,null);
        db.execSQL("create table if not exists person_detail (id integer primary key autoincrement,p_name varchar,address varchar);");
    }

    @Override
    public void onClick(View view) {
        if(view == btnsave)
        {
            String name = edtname.getText().toString().trim();
            String add = edtadd.getText().toString().trim();

            if(name.equals("") || add.equals(""))
            {
                Toast.makeText(getApplicationContext(), "Please fill of fields", Toast.LENGTH_SHORT).show();
            }else{
                try {

                    String query = "insert into person_detail(p_name,address) values('"+name+"','"+add+"');";
                    db.execSQL(query);
                    Toast.makeText(getApplicationContext(), "Record Saved", Toast.LENGTH_SHORT).show();
                    edtname.setText("");
                    edtadd.setText("");

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Error Insertion"+e.getClass(), Toast.LENGTH_SHORT).show();
                }
            }
        }

        if(view == btnview)
        {
            Intent i = new Intent(db_example.this, view_example.class);
            startActivity(i);
        }
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