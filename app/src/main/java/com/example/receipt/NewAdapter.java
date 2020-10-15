package com.example.receipt;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.OrderViewHolder> {

    private List<ImageData> orders;
    private Context context;

    public NewAdapter (List<ImageData> orders, Context context) {
        this.orders = orders;
        this.context = context;
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {

        ImageView imageThumbnail;
        TextView textImageName;
        LinearLayout layoutMain;

        public OrderViewHolder (View view) {
            super(view);
            imageThumbnail = view.findViewById(R.id.image_thumbnail);
            textImageName = view.findViewById(R.id.text_image_name);
            layoutMain = view.findViewById(R.id.layout_main);
        }
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View layout = inflater.inflate(R.layout.image_list_view, parent, false);

        // Return a new holder instance
        OrderViewHolder viewHolder = new OrderViewHolder(layout);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder (@NonNull OrderViewHolder holder, final int position) {

        ImageData data = orders.get(position);

        holder.imageThumbnail.setImageURI(data.getFullPhotoUri());
        holder.textImageName.setText(data.getName());
        holder.layoutMain.setTag(position);
        holder.layoutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent intent = new Intent(v.getContext(), DetailsActivity.class);
                intent.putExtra("object", (Parcelable) orders.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount () {
        return orders.size();
    }
}