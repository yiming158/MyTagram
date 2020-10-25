package com.example;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.mytagram.R;

public class PostActivity extends AppCompatActivity {
    static final int CAPTURE_IMAGE=0;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_post);
         img = (ImageView) findViewById(R.id.imageView);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(intent, CAPTURE_IMAGE);
                }
            }
        });
        final EditText txtMessage = (EditText) findViewById(R.id.txtMessage);
        ImageButton btnOk = (ImageButton ) findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putCharSequence("msg", txtMessage.getText());
                bundle.putParcelable("bitmap", ((BitmapDrawable) img.getDrawable()).getBitmap());
                intent.putExtras(bundle);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        ImageButton btnCancel = (ImageButton) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED );
                finish();
            }
        });


    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAPTURE_IMAGE && resultCode == RESULT_OK ){
            Bundle bundle = data.getExtras();
            Bitmap image = (Bitmap) bundle.get("data");
            img.setImageBitmap(image);
    }
}}