package com.example.draw_and_pass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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

import java.io.File;
import java.io.IOException;
import com.squareup.picasso.Picasso;

import java.net.CookieHandler;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Transition extends Activity {
    private static final int PHOTO_RESULT = 0;
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

    private File mFichier;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == PHOTO_RESULT && resultCode== RESULT_OK){
            if (data != null){

                Bitmap photoUser = BitmapFactory.decodeFile("mFichier");
            }
        }

    }

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
            mImageProfil=(ImageButton) findViewById(R.id.activity_transition_profil);
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
            avatars.add(R.drawable.photocamera);

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
                        positionAvatar=9;
                        mImageProfil.setImageResource(avatars.get(positionAvatar));
                    }
                    else{
                        positionAvatar--;
                        mImageProfil.setImageResource(avatars.get(positionAvatar));
                    }
                }
            });





                    mImageProfil.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if(positionAvatar==9) {
                                System.out.println("position" + positionAvatar);
                                mFichier = new File(Environment.getExternalStorageDirectory(), "photo.png");
                                Uri fileUri ;
                                fileUri = FileProvider.getUriForFile(
                                        Transition.this,
                                        "com.example.draw_and_pass.fileprovider",
                                        mFichier);
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

                                startActivityForResult(intent, PHOTO_RESULT);
                                //onActivityResult();
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
            mTextADeviner=(TextView) findViewById(R.id.texte_trouve_avant);
            mImageADeviner=(ImageView) findViewById(R.id.image_dessine_avant);
            if (users.get(game.getCounter()).getIcon()!= null) {
                BitmapDrawable drawable = (BitmapDrawable) users.get(game.getCounter()).getIcon().getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                mImageProfil.setImageBitmap(bitmap);
            }else{
                String ImageURL = ( "https://i.pinimg.com/originals/7c/c7/a6/7cc7a630624d20f7797cb4c8e93c09c1.png" );
                Picasso.get().load(ImageURL).into(mImageProfil);
            }
            TextView pseudo = (TextView) findViewById(R.id.activity_transition_pseudo);
            pseudo.setText(users.get(game.getCounter()).getName());
        }
        if (game.getEvents().get(game.getEvents().size()-1).getImage() !=null){
            mImageADeviner.setImageBitmap(game.getEvents().get(game.getEvents().size()-1).getImage());
        }
        else{
            mTextADeviner.setText(game.getEvents().get(game.getEvents().size()-1).getPhrase());
        }


        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ImageView photo;
                User user;
                photo=mImageProfil;
                if(users==null){

                user = new User(0, mPseudo.getText().toString(), photo);

                }
                else{
                    user = users.get(game.getCounter());
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