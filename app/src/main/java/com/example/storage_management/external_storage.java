package com.example.storage_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class external_storage extends AppCompatActivity {
    EditText edtfname,edtdata;
    Button btnsave,btnread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);
        edtfname =(EditText) findViewById(R.id.editText_fname);
        edtdata=(EditText) findViewById(R.id.editText_data);

        btnsave=(Button) findViewById(R.id.button_save);
        btnread=(Button) findViewById(R.id.button_read);



        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), Environment.getExternalStorageDirectory().getAbsolutePath().toString(), Toast.LENGTH_SHORT).show();
                String fname= edtfname.getText().toString().trim();
                String data= edtdata.getText().toString().trim();
                File myfile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+fname);
                try {
                    FileOutputStream fos = new FileOutputStream(myfile);
                    OutputStreamWriter osw = new OutputStreamWriter(fos);
                    osw.append(data);
                    osw.close();
                    fos.close();
                    Toast.makeText(getApplicationContext(), "File Saved Successfully", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                   e.printStackTrace();
                }

            }
        });
        btnread.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String fname=edtfname.getText().toString().trim();

                String temp ="";
                StringBuffer sb= new StringBuffer();

                File myfile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+fname);

                try {
                    FileInputStream fis = new FileInputStream(myfile);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    while ((temp=br.readLine())!=null)
                    {
                        sb.append(temp+"\n");
                    }
                    br.close();
                    fis.close();
                    Toast.makeText(getApplicationContext(),sb.toString(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(external_storage.this, "read", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                  e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
        });

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