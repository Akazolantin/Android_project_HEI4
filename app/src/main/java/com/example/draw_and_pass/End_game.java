package com.example.draw_and_pass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.squareup.picasso.Picasso;

import com.squareup.picasso.Picasso;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class End_game extends Activity {
    String TAG = "thomas";

    private RecyclerView recyclerView_summary;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter_userLine;
    private ArrayList<Event> events = new ArrayList<>();
    private User thomas;
    private static Game game;

    private int nrb=0;

    public void restart(){
        Intent start = new Intent(this,Transition.class);
        Game newGame= new Game(game.getId()+1,game.getNbrevent(),new ArrayList<Event>(),null);
        game.addEvent(new Event(game.getEvents().get(0).getUser(),"Thomas"));
        ArrayList<User> users = new ArrayList<User>();
        for(Event event : game.getEvents()){
            users.add(event.getUser());
        }
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
                String ImageURL = ( "https://i.pinimg.com/originals/7c/c7/a6/7cc7a630624d20f7797cb4c8e93c09c1.png" );
                Picasso.get().load(ImageURL).into(imageView_icon_person);
            }

        }


    }
    public void ClickOnHome(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }

    public void ClickOnShare(View view) {



        try {
            ArrayList<Bitmap> lstImage= new ArrayList<Bitmap>();
            for (int nb=0;nb<events.size();nb++){
                lstImage.add(game.getEvents().get(nb).getImage());
                if (game.getEvents().get(nb).getImage() != null) {
                    FileOutputStream fileOutputStream = this.openFileOutput(game.getEvents().get(nb).getPhrase() + "_dessiné_par_" + game.getEvents().get(nb).getUser(), this.MODE_PRIVATE);
                    game.getEvents().get(nb).getImage().compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                    fileOutputStream.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void ClickOnRefresh(View view) {
        restart();

    }
}