package com.example.relifemedicare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.relifemedicare.R;
import com.example.relifemedicare.model.Hit;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RelifemedicareApiAdapter extends RecyclerView.Adapter<RelifemedicareApiAdapter.MyViewHolder> {


    Context mcontext;
    List<Hit>  hits;
    public RelifemedicareApiAdapter(Context context, List<Hit> hit) {
        this.mcontext=context;
        this.hits=hit;
    }

    @NonNull
    @Override
    public RelifemedicareApiAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_layout, parent, false);

        RelifemedicareApiAdapter.MyViewHolder viewHolder = new RelifemedicareApiAdapter.MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RelifemedicareApiAdapter.MyViewHolder holder, int position) {
//            for (int i=0 ;i<hits.size();i++){

        holder.texttitle.setText(hits.get(position).getTags());

        holder.texttype.setText(hits.get(position).getType());

            holder.like.setText(String.valueOf(hits.get(position).getLikes()));

       Integer views=hits.get(position).getViews();
        holder.views.setText(String.valueOf(views));
        holder.comments.setText(String.valueOf(hits.get(position).getComments()));
        holder.collections.setText(String.valueOf(hits.get(position).getCollections()));
        holder.downloads.setText(String.valueOf(hits.get(position).getDownloads()));

                Glide.with(mcontext)
                        .load( hits.get(position).getPreviewURL())
                        .placeholder(R.drawable.star)
                        .override(hits.get(position).getPreviewWidth(), hits.get(position).getPreviewHeight())
                        .into(holder.img_pageURL)
                ;
//            }
    }

    @Override
    public int getItemCount() {
        return hits.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img_pageURL;
        TextView texttitle,texttype,like,views,comments,collections,downloads;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img_pageURL=itemView.findViewById(R.id.img_pageURL);
            texttitle=itemView.findViewById(R.id.texttitle);
            texttype=itemView.findViewById(R.id.texttype);
            like=itemView.findViewById(R.id.like);
            views=itemView.findViewById(R.id.views);
            comments=itemView.findViewById(R.id.comments);
            collections=itemView.findViewById(R.id.collections);
            downloads=itemView.findViewById(R.id.downloads);
        }
    }
}
