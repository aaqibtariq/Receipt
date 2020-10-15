package com.example.receipt;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import android.view.View;

import android.widget.Button;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView listView;
    private ArrayList<ImageData> imageList;
    private LinearLayoutManager layoutManager;
    private Context context;
    private NewAdapter listAdapter;


    Button getImage;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageList = new ArrayList<>();
        context = this;
        getImage = findViewById(R.id.get_image);
        listView = findViewById(R.id.listView);

        getImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, 0);
                }
            }


        });

        Log.i("Activity_State_Log_Data", imageList.toString());

        listAdapter = new NewAdapter(imageList, this);
        listView.setAdapter(listAdapter);
        listView.setLayoutManager(new LinearLayoutManager(context));


    }

    private void refreshListView () {
        listAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("TESTING", "Image Fetched");
        if (requestCode == 0 && resultCode == RESULT_OK) {
            Log.e("TESTING", "Image Ok");
            Uri fullPhotoUri = data.getData();
            Log.e("TESTING", fullPhotoUri.toString());
            if (fullPhotoUri != null) {
                imageList.add(new ImageData(fullPhotoUri));
                Log.e("TESTING", "List: "+imageList.size());
                refreshListView();
//                listAdapter = new NewAdapter(imageList, this);
//                listView.setAdapter(listAdapter);

            }

        }
        else {
            Log.e("TESTING", "Error");
        }
//        else if (requestCode == 2 && resultCode == RESULT_OK) {
//            ImageData returnedData = (ImageData) data.getSerializableExtra("object");
//            for (int i = 0; i<imageList.size(); i++) {
//                if (imageList.get(i).getUri().equals(returnedData.getUri())) {
//                    imageList.set(i, returnedData);
//                }
//            }
//        }
//        refreshListView();
    }

}



