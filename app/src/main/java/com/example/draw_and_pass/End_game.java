package com.example.draw_and_pass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Icon;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

//import com.squareup.picasso.Picasso;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;

public class End_game extends Activity {
    String TAG = "thomas";

    private RecyclerView recyclerView_summary;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter_userLine;
    private ArrayList<Event> events = new ArrayList<>();
    private User thomas;
    private static Game game;
    private static User user;
    private static Transition transition;

    private int nrb=0;
    ///////////////////////////////////////////////////////////////
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
                        .setPositiveButton("Oh MINCE !!!", new DialogInterface.OnClickListener() {
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

    ///////////////////////////////////////////////////////////////////////////////

    public void restart(){
        Intent start = new Intent(this,Transition.class);
        Game newGame= new Game(game.getId()+1,game.getNbrevent(),new ArrayList<Event>(),null);ImageView image = new ImageView(this);
        image.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.robot));
        newGame.addEvent(new Event(new User(0,"Ordinateur", image),MainActivity.generatePhrase()));
        ArrayList<User> users = new ArrayList<User>();
        for(int i=2;i<game.getEvents().size();i++){
            users.add(game.getEvents().get(i).getUser());
        }
        users.add(game.getEvents().get(1).getUser());
        Log.d(TAG,""+users.size());
        Transition.setGame(newGame);
        Transition.setUsers(users);
        startActivity(start);
    }

    public static void setGame(Game game) {
        End_game.game = game;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        events=game.getEvents();
        Log.d(TAG, "Success : Start onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);
        recyclerView_summary = findViewById(R.id.listview_summary);
        layoutManager = new LinearLayoutManager(this);
        recyclerView_summary.setLayoutManager(layoutManager);
        recyclerView_summary.setHasFixedSize(true);
        adapter_userLine = new SummaryAdapter();
        recyclerView_summary.setAdapter(adapter_userLine);
    }


    public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.SummaryViewHolder> {
        @NonNull
        @Override
        public SummaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            return new SummaryViewHolder(inflater.inflate(R.layout.fragment_end_game, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull SummaryViewHolder holder, int position) {
            holder.setSummary(events.get(position),nrb);
            nrb++;
        }

        @Override
        public int getItemCount() {

            return events.size();
        }

        public class SummaryViewHolder extends RecyclerView.ViewHolder {

            private ImageView imageView_icon_person;
            private TextView textView_name_person;
            private ImageView drawing;
            private TextView phrase;

            public SummaryViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView_icon_person = itemView.findViewById(R.id.icon_person);
                textView_name_person = itemView.findViewById(R.id.name_person);
                drawing = itemView.findViewById(R.id.drawing);
                phrase = itemView.findViewById(R.id.phrase);
            }

            public void setSummary(Event event,int nbr) {
                drawing.setImageBitmap(event.getImage());
                phrase.setText(event.getPhrase());
                textView_name_person.setText(event.getUser().getName());
                if (event.getUser().getIcon()!= null) {
                    BitmapDrawable drawable = (BitmapDrawable) event.getUser().getIcon().getDrawable();
                    Bitmap bitmap = drawable.getBitmap();
                    imageView_icon_person.setImageBitmap(bitmap);
                }else{
                    String ImageURL = ( "https://i.pinimg.com/originals/7c/c7/a6/7cc7a630624d20f7797cb4c8e93c09c1.png" );
                    Picasso.get().load(ImageURL).into(imageView_icon_person);
                }
            }
        }
    }

    public void ClickOnHome(View view) {
        Transition.setUsers(null);
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }

    public void ClickOnShare(View view) {

        ArrayList<Bitmap> lstImage= new ArrayList<Bitmap>();
        for (int nb=0;nb<events.size();nb++){
            if (game.getEvents().get(nb).getImage() != null) {
                Bitmap image =game.getEvents().get(nb).getImage();
                lstImage.add(image);

                saveToInternalStorage(image,game.getEvents().get(nb).getUser().getName()+"_"+game.getEvents().get(nb-1).getPhrase());
            }
        }
        Toast.makeText(this,"Dessins enregistrés" , Toast.LENGTH_SHORT).show();
    }


    public void ClickOnRefresh(View view) {
        restart();
    }


    private String saveToInternalStorage(Bitmap bitmapImage,String name){
        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File mypath=new File(directory,name+".jpg");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }
}