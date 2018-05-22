package com.example.darthvader.talkpidginv2;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.VideoView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by darthvader on 21/1/18.
 */

public class AfterActivity extends AppCompatActivity {
    VideoView view;
    StorageReference reference;
    String sentence;
    int index=1;
    ArrayList<String> list = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after);
        sentence = MainActivity.a.toLowerCase();
        Log.i("lol", sentence);
        Pattern pattern = Pattern.compile("[a-zA-z]+");
        Matcher matcher = pattern.matcher(sentence);
        reference = FirebaseStorage.getInstance().getReference();
        view = (VideoView) findViewById(R.id.videoView);
        String link = "https://firebasestorage.googleapis.com/v0/b/talkpidginv2.appspot.com/o/Videos%2F" + Uri.encode(sentence) + ".mp4?alt=media";
        Log.i("link", link);
        while (matcher.find()) {
            list.add(matcher.group());
        }
        view.setVisibility(View.VISIBLE);
        view.setVideoPath(link);
        view.start();
        new CountDownTimer(5000,1000)
        {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                if(!view.isPlaying()) {
                    view.setVideoPath("https://firebasestorage.googleapis.com/v0/b/talkpidginv2.appspot.com/o/Videos%2F" + Uri.encode(list.get(0)) + ".mp4?alt=media");
                    view.start();
                    view.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            if (index < list.size()) {
                                mediaPlayer.stop();
                                view.setVideoPath("https://firebasestorage.googleapis.com/v0/b/talkpidginv2.appspot.com/o/Videos%2F" + Uri.encode(list.get(index)) + ".mp4?alt=media");
                                view.start();
                                index++;
                            }
                        }
                    });
                }

            }
        }.start();



    }
    }


