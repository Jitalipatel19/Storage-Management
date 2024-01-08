package com.example.storage_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class media_recored extends AppCompatActivity {
    Button btnstart,btnstop;
    String audiofilepath;
    MediaRecorder recorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_recored);
        btnstart = (Button) findViewById(R.id.button_startrec);
        btnstop = (Button) findViewById(R.id.button_stoprec);


        //Start Button
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnstop.setEnabled(true);
                btnstart.setEnabled(false);
                audiofilepath= Environment.getExternalStorageDirectory().getAbsolutePath()+"/myaudio.3gp";
                try {
                    recorder = new MediaRecorder();
                    recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    recorder.setOutputFile(audiofilepath);
                    recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    recorder.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                recorder.start();

            }
        });

        //Stop Button
        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnstart.setEnabled(true);
                btnstop.setEnabled(false);
                recorder.stop();
                recorder.release();
                recorder = null;

                addRecordingToMediaLibrary();
            }
        });
    }

    private void addRecordingToMediaLibrary() {
        ContentValues cv = new ContentValues(4);
        cv.put(MediaStore.Audio.Media.TITLE,"audio"+"myaudio.3gp");
        long cureentMillisecond = System.currentTimeMillis();

        cv.put(MediaStore.Audio.Media.DATE_ADDED,(int)(cureentMillisecond/1000));
        cv.put(MediaStore.Audio.Media.MIME_TYPE,"audio/3gpp");
        cv.put(MediaStore.Audio.Media.DATA,audiofilepath);

        ContentResolver cr = getContentResolver();


        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Uri newuri = cr.insert(uri,cv);

        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,newuri));
        Toast.makeText(getApplicationContext(), "Audio File:"+newuri, Toast.LENGTH_SHORT).show();
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