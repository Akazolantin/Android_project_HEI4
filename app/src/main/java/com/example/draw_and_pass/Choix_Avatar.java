package com.example.draw_and_pass;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Choix_Avatar extends Activity {
  /*private RecyclerView recyclerView;
  private RecyclerView.LayoutManager layoutManager;
  private RecyclerView.Adapter adapterLine;
  private ArrayList<Avatar> avatars = new ArrayList<Avatar>();
  private static User user;
private static Avatar avatare;
  private int nb=0;


  public static void setUser(User user) {
    Choix_Avatar.user = user;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    avatars=avatare.getAvatar();

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_choix_avatar);
    recyclerView = findViewById(R.id.listview_avatars);
    layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setHasFixedSize(true);
    adapterLine = new Choix_Avatar.AvatarAdapter();
    recyclerView.setAdapter(adapterLine);

  }


  public class AvatarAdapter extends RecyclerView.Adapter<Choix_Avatar.AvatarAdapter.AvatarViewHolder> {
    @NonNull
    @Override
    public Choix_Avatar.AvatarAdapter.AvatarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      LayoutInflater inflater = LayoutInflater.from(parent.getContext());
      return new Choix_Avatar.AvatarAdapter.AvatarViewHolder(inflater.inflate(R.layout.fragment_avatar, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Choix_Avatar.AvatarAdapter.AvatarViewHolder holder, int position) {
      holder.setAvatar(avatars.get(position),nb);
      nb++;

    }

    @Override
    public int getItemCount() {

      return avatars.size();
    }

    public class AvatarViewHolder extends RecyclerView.ViewHolder {

      private ImageView imageView_avatar_person;


      public AvatarViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView_avatar_person = itemView.findViewById(R.id.imageview_avatar_choix);
      }

      public void setAvatar(Avatar avatar,int nbr) {
        String ImageURL = ( "https://i.pinimg.com/originals/7c/c7/a6/7cc7a630624d20f7797cb4c8e93c09c1.png" );
        Picasso.get().load(ImageURL).into(imageView_avatar_person);
      }

    }



  }*/
}

