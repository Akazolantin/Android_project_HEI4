package com.example.draw_and_pass;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.CookieHandler;
import java.util.ArrayList;


public class Transition extends Activity {
    private EditText mPseudo;
    private Button mNextButton;
    private Button mButtonUp;
    private Button mButtonDown;
    private ImageView mImageProfil;
    private static Game game;
   private final String TAG = "toto";
   private static ArrayList<User> users;
   private int positionAvatar;
   private TextView mTextADeviner;
   private ImageView mImageADeviner;
   private Image iconUser;

///////////////////////////////////////////////////////////////////////////////////////////////////
    private boolean back_answer = false;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (game.getCounter()>0) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                boolean debugState = false;
                if (debugState) {
                    Toast.makeText(this, "BACK key press", Toast.LENGTH_SHORT).show();
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Vous ne pouvez pas tricher !")
                        .setCancelable(false)
                        .setPositiveButton("Oh MINCE !", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                back_answer = true;
                            }
                        });

                AlertDialog alert = builder.create();
                alert.show();
            }

        }
        return back_answer;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////


    public static void setUsers(ArrayList<User> userArrayList) {
        Transition.users = userArrayList;
        Log.d("toto", "salut: "+userArrayList);
    }

    public static void setGame(Game game) {
        Transition.game = game;
        Log.d("toto", "salut: "+game);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        positionAvatar=0;
        if(users ==null) {
            setContentView(R.layout.activity_transition);
            mImageProfil=(ImageView) findViewById(R.id.activity_transition_profil);
            mPseudo = (EditText) findViewById(R.id.activity_transition_pseudo);
            mButtonUp= (Button) findViewById(R.id.transition_button_up);
            mButtonDown=(Button) findViewById(R.id.transition_button_down);
            mNextButton = (Button) findViewById(R.id.activity_transition_next_activity);
            mNextButton.setEnabled(false);
            mTextADeviner=(TextView) findViewById(R.id.texte_trouve_avant);
            mImageADeviner=(ImageView) findViewById(R.id.image_dessine_avant);

            mPseudo = (EditText) findViewById(R.id.activity_transition_pseudo);
            mNextButton = (Button) findViewById(R.id.activity_transition_next_activity);

            mNextButton.setEnabled(false);



            ArrayList<Integer> avatars = new ArrayList<>();
            avatars.add(R.drawable.boy);
            avatars.add(R.drawable.designer);
            avatars.add(R.drawable.girl);
            avatars.add(R.drawable.hacker);
            avatars.add(R.drawable.man);
            avatars.add(R.drawable.man1);
            avatars.add(R.drawable.man2);
            avatars.add(R.drawable.boy1);
            avatars.add(R.drawable.reporter);


            mImageProfil.setImageResource(avatars.get(positionAvatar));
            mButtonUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if(positionAvatar<(avatars.size()-1)){
                        positionAvatar++;
                        mImageProfil.setImageResource(avatars.get(positionAvatar));

                    }
                    else{
                        positionAvatar =0;
                        mImageProfil.setImageResource(avatars.get(positionAvatar));
                    }
                }
            });
            mButtonDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(positionAvatar==0){
                        positionAvatar=8;
                        mImageProfil.setImageResource(avatars.get(positionAvatar));
                    }
                    else{
                        positionAvatar--;
                        mImageProfil.setImageResource(avatars.get(positionAvatar));
                    }
                }
            });

            if (game.getEvents().get(game.getEvents().size()-1).getImage() !=null){
                mImageADeviner.setImageBitmap(game.getEvents().get(game.getEvents().size()-1).getImage());
            }
            else{
                mTextADeviner.setText(game.getEvents().get(game.getEvents().size()-1).getPhrase());
            }

        mPseudo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mNextButton.setEnabled(s.toString().length() >1);
            }

            @Override
            public void afterTextChanged(Editable s) {

                }
            });
        }
        else{
            setContentView(R.layout.activity_transition_alt);
            mNextButton = (Button) findViewById(R.id.activity_transition_next_activity);
            mImageProfil = (ImageView) findViewById(R.id.activity_transition_profil);
            //mButtonProfil.setImage(users.get(game.getCounter()).getIcon());
            TextView pseudo = (TextView) findViewById(R.id.activity_transition_pseudo);
            pseudo.setText(users.get(game.getCounter()+1).getName());
        }



        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                User user;

                if(users==null){
                user = new User(0, mPseudo.getText().toString(), null);}
                else{
                    user = users.get(game.getCounter()+1);
                }


                Event event = new Event(user);
                game.addEvent(event);
                // retourner le nombre de tours de start game

                    if (game.getCounter() % 2 == 0) {
                        Log.d(TAG, "1");
                        Intent drawingActivity = new Intent(Transition.this, Drawing.class);
                        game.setCounter();
                        Drawing.setGame(game);
                        startActivity(drawingActivity);

                        Log.d(TAG, "2");
                    } else {
                        Intent guessActivity = new Intent(Transition.this, Guess_the_word.class);
                        Guess_the_word.setGame(game);
                        startActivity(guessActivity);
                        Log.d(TAG, "3");
                        game.setCounter();
                    }

            }
        });



    }

}