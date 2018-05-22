package com.example.darthvader.talkpidginv2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends Activity {
    StorageReference reference;
    Button button;
    public static String a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reference = FirebaseStorage.getInstance().getReference();
        button = (Button) findViewById(R.id.btn_success);
        Log.i("Example",Uri.encode("Hello World"));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                promptspeech();
            }
        });
    }

    private void promptspeech() {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Say Something");
        startActivityForResult(intent,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode)
        {
            case 100:
                if(resultCode == RESULT_OK && data!=null)
                {
                    ArrayList<String> strings = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    a=strings.get(0);
                    Log.i("Blah",a);
                }
        }
        Intent intent = new Intent(MainActivity.this,AfterActivity.class);
        startActivity(intent);

    }
}
