package elieomatuku.workingapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.content.Intent;

public class SecondSreen extends AppCompatActivity {




    TextView title;
    TextView releaseDate;
    TextView characters;
    TextView plot;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_sreen);


        title = (TextView)findViewById(R.id.textView2);
        releaseDate = (TextView)findViewById(R.id.textView3);
        characters = (TextView)findViewById(R.id.textView5);
        plot = (TextView)findViewById(R.id.textView6);



        Bundle incoming=this.getIntent().getExtras();
        title.setText(incoming.getString("title"));
        title.setTextColor(Color.BLACK);

        releaseDate.setText(incoming.getString("Release"));
        plot.setText(incoming.getString("plot"));
        characters.setText(incoming.getString("character"));





    }

}
