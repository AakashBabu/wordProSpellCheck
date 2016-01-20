package com.example.ravi.wordprospellcheck;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class TextSpeech extends AppCompatActivity implements TextToSpeech.OnInitListener{

    private TextToSpeech voice;
    private EditText input;
    private Button click;
    public String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_speech);
        voice = new TextToSpeech(this, this);
        input = (EditText)findViewById(R.id.editText);
        Button button = (Button)findViewById(R.id.button2);
        text = input.getText().toString();
        click = (Button)findViewById(R.id.button);
        click.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pronounce();
                    }
                }
        );
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , webview.class);
                intent.putExtra("value",text);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onDestroy() {
        if(voice != null){
            voice.stop();
            voice.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {
        if(status == TextToSpeech.SUCCESS){
            int result = voice.setLanguage(Locale.US);
            if(result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS", "Language is not Supported");
            }else {
                click.setEnabled(true);
                pronounce();
            }
        }else {
            Log.e("TTS", "Initilation Failed");
        }
    }

    private void pronounce() {
        voice.speak(text , TextToSpeech.QUEUE_FLUSH, null);
    }

}
