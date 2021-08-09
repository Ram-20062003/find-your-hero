package com.example.findyourhero;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RecyclerView_Adapter extends RecyclerView.Adapter<RecyclerView_Adapter.RecyclerView_ViewHolder> {
   List<String> name;
   List<String>image_url;
   List<Integer>id;
    public static int pos;

    public RecyclerView_Adapter(List<String> name, List<Integer> id, List<String> image_url) {
        this.name = name;
        this.id = id;
        this.image_url = image_url;
        notifyDataSetChanged();

    }

    @Override
    public RecyclerView_ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_hero_card,parent,false);
        RecyclerView_ViewHolder recyclerView_viewHolder=new RecyclerView_ViewHolder(view);
        return recyclerView_viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView_ViewHolder holder, int position) {
        holder.t_name.setText(name.get(position));
        holder.t_id.setText(String.valueOf(id.get(position)));
        Picasso.get().load(image_url.get(position)).resize(300,232).into(holder.image_hero);
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class RecyclerView_ViewHolder extends RecyclerView.ViewHolder {
        TextView t_id,t_name;
        ImageView image_hero;
        public RecyclerView_ViewHolder(View itemView) {
            super(itemView);
            t_id=itemView.findViewById(R.id.hero_id);
            t_name=itemView.findViewById(R.id.hero_name);
            image_hero=itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pos=getAdapterPosition();
                    //Toast.makeText(v.getContext(), "Hi I am "+list_name.get(getAdapterPosition()),Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(v.getContext(),hero_stats.class);
                    intent.putExtra("name",t_name.getText());
                    ActivityOptionsCompat optionsCompat=ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) itemView.getContext(),image_hero, "get_details_image");
                    itemView.getContext().startActivity(intent,optionsCompat.toBundle());
                }
            });
        }
    }
}
