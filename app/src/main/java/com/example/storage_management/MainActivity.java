package com.example.storage_management;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;



public class MainActivity extends AppCompatActivity {
 Toolbar toolbar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
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