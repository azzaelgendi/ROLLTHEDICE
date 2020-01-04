package com.azzaelgendy.rollthedice;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //hold text result
    TextView rollResult;
    //hold button
    Button rollButton;
    //hold the score
    int score;
    //hold random number
    Random myRandomNumber;
    //hold the Dice values
    int firstDie;
    int secondDie;
    int thirdDie;
    //score text
    TextView scoreText;
    //array list for all die value
    ArrayList<Integer> dice;
    //arraylist dice images
    ArrayList<ImageView>diceImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//Use of the original button
                rollDice(view);
            }
        });

        //initialize score
        score=0;
        rollResult=findViewById(R.id.rollResult);
        rollButton=findViewById(R.id.btnRoll);
        scoreText=findViewById(R.id.scoreText);

        //initialize the random number
            myRandomNumber = new Random();
        //initialize arrylist container

        dice=new ArrayList<Integer>();

        //initialize the dice images
        ImageView dice1Image =(ImageView) findViewById(R.id.die1Image);
        ImageView dice2Image =(ImageView) findViewById(R.id.die2Image);
        ImageView dice3Image =(ImageView) findViewById(R.id.die3Image);

        // Build ArrayList with dice ImageView instances
        diceImage = new ArrayList<ImageView>();
        diceImage.add(dice1Image);
        diceImage.add(dice2Image);
        diceImage.add(dice3Image);

        //Create Greeting
        Toast.makeText(getApplicationContext(),"Welcome and Roll",Toast.LENGTH_SHORT).show();

    }
    public void rollDice(View myView){
        rollResult.setText("Clicked");
        //Roll Dice
        firstDie=myRandomNumber.nextInt(6)+1;
        secondDie=myRandomNumber.nextInt(6)+1;
        thirdDie=myRandomNumber.nextInt(6)+1;
        // set dice to arraylist
        //clear the list first
        dice.clear();
        dice.add(firstDie);
        dice.add(secondDie);
        dice.add(thirdDie);
        for (int dieNumber=0; dieNumber < 3; dieNumber++){
            String imageName= "die_"+dice.get(dieNumber)+".png";
           try{
               InputStream stream =getAssets().open(imageName);
               Drawable draw=Drawable.createFromStream(stream,null);
               diceImage.get(dieNumber).setImageDrawable(draw);
           }catch (IOException e)
          {
               e.printStackTrace();
          }
        }

        //message result
        String message;
        //check for same number on the three dice
        if(firstDie==secondDie&&firstDie==thirdDie)
        {
            int scoreInt=firstDie*100;
            message="You Rolled A Triple Congrats" + "You Scored "+scoreInt;
            score+=scoreInt;
        }else if(firstDie==secondDie||firstDie==thirdDie||secondDie==thirdDie)
        {
            int scoreInt=50;
            message="You Rolled A Double Congrats" + "You Scored "+scoreInt;
            score+=scoreInt;
        }else{
            message="TRY AGAIN";
        }
        //Show the message
        rollResult.setText(message);
        //update the app score
        scoreText.setText("Score: "+score);
        Toast.makeText(getApplicationContext(),message, Toast.LENGTH_SHORT).show();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
