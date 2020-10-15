package com.example.receipt;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ViewHolder> {

    private ArrayList<ImageData> imageList;
    private Activity activity;
    private Context context;
    final int EDIT_IMAGE_REQUEST = 2;

    public ImageListAdapter(ArrayList<ImageData> orders, Activity context) {
        this.imageList = orders;
        this.activity = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public final View mview;

        ImageView imageThumbnail;
        TextView textImageName ;
        LinearLayout layoutMain;
        public ViewHolder(View view) {
            super(view);
            mview = view;
            imageThumbnail= view.findViewById(R.id.image_thumbnail);
            textImageName = view.findViewById(R.id.text_image_name);
            layoutMain= view.findViewById(R.id.layout_main);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)  {
        context = parent.getContext();
        View imageView = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_list_view, parent, false);

        return new ViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(ImageListAdapter.ViewHolder holder, int position) {
        ImageData data = imageList.get(position);

        holder.imageThumbnail.setImageURI(data.getFullPhotoUri());
        holder.textImageName.setText(data.getName());
        holder.layoutMain.setTag(position);
        holder.layoutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailsActivity.class );
                intent.putExtra("object",imageList);
                activity.startActivityForResult(intent,EDIT_IMAGE_REQUEST);

            }
        });


    }




    @Override
    public int getItemCount() {
        return imageList.size();
    }


}