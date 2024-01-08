package com.example.storage_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class media_player extends AppCompatActivity {
    Button btnplay,btnstop,btnpause;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);

        mp = new MediaPlayer();
        try{
            mp.setDataSource(Environment.getExternalStorageDirectory().getAbsolutePath()+"/x1.mp3");
            mp.prepare();

        } catch (Exception e) {
            e.printStackTrace();
        }
        btnplay = (Button) findViewById(R.id.button_play);
        btnstop = (Button)findViewById(R.id.button_stop);
        btnpause = (Button)findViewById(R.id.button_pause);

    //play button
    btnplay.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mp.start();
        }
    });

    //stop button
    btnstop.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mp.stop();
            mp.prepareAsync();
            mp.seekTo(0);
        }
    });

    //pause button
    btnpause.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mp.pause();
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