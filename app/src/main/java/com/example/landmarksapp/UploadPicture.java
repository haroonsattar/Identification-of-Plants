package com.example.landmarksapp;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.landmarksapp.ml.Places;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.image.TensorImage;
import org.tensorflow.lite.support.label.Category;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class UploadPicture extends AppCompatActivity {
    ImageView imgGallery;
    Button button;
    TextView result;
    ActivityResultLauncher<String> mGetContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_picture);

        imgGallery = findViewById(R.id.image_view1);
        button = findViewById(R.id.button8);
        result = findViewById(R.id.result1);


        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {

                Bitmap imageBitmp = null;
                try {
                    imageBitmp = UriToBimap(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imgGallery.setImageBitmap(imageBitmp);
                openGenerator(imageBitmp);

                Log.d("TAG_URI", "" + result);
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetContent.launch("image/*");
            }
        });

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/search?=" + result.getText().toString()));
            }
        });

    }

    private void openGenerator(Bitmap imageBitmp)
    {
        try {
            Places model = Places.newInstance(UploadPicture.this);

// Creates inputs for reference.
            TensorImage image = TensorImage.fromBitmap(imageBitmp);

// Runs model inference and gets result.
            Places.Outputs outputs = model.process(image);
            List<Category> probability = outputs.getProbabilityAsCategoryList();

            int index =0;
            float max=probability.get(0).getScore();

            for(int i=0;i<probability.size();i++)
            {
                if(max<probability.get(i).getScore())
                {
                    max=probability.get(i).getScore();
                    index=i;
                }
            }

            Category output=probability.get(index);
            result.setText(output.getLabel());




// Releases model resources if no longer used.
            model.close();
        } catch (IOException e) {

        }
    }

        private Bitmap UriToBimap(Uri result) throws IOException {
            return MediaStore.Images.Media.getBitmap(this.getContentResolver(),result);
        }

    }
