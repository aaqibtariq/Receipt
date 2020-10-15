package com.example.receipt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    private ImageView imageview;
    private TextView text_name ;
    private EditText edit_name ;
    private TextView text_uri;
    private  TextView text_description ;
    private  EditText edit_description ;

    private ImageData data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        text_name = findViewById(R.id.text_name);
        edit_name = findViewById(R.id.edit_name);
        text_uri = findViewById(R.id.text_uri);
        text_description = findViewById(R.id.text_description);
        edit_description = findViewById(R.id.edit_description);
        final Intent intent = new Intent();

        data = (ImageData) intent.getParcelableExtra("object") ;
        final Context context = this;

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        imageview = findViewById(R.id.image);
        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,FullscreenActivity.class);
                intent.putExtra("image", data.getFullPhotoUri());
                startActivity(intent);
            }
        });



        imageview.setImageURI(data.getFullPhotoUri());
        //        image.setImageDrawable(getResources().getDrawable(android.R.drawable.btn_star));
        text_name.setText(data.getName());
        edit_name.setText(data.getName());
        text_uri.setText(data.getUri().toString());


        text_description.setText(data.getDescription());
        edit_description.setText(data.getDescription());

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        data.setName(edit_name.getText().toString()) ;
        data.setDescription(edit_description.getText().toString());

        Intent resultIntent = new Intent();
        resultIntent.putExtra("object", (Parcelable) data);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }


}







