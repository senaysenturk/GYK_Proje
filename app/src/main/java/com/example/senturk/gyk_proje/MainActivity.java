package com.example.senturk.gyk_proje;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView ates;
    private ImageView su;
    private ViewGroup rootLayout;
    private int xDelta;
    private int yDelta;
    private Button yukle;


    private DatabaseReference mDatabaseRef;
    private List<Upload> imgList;
    private ListView lv;
    private ImageListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.lv_elements);

        yukle=(Button)findViewById(R.id.btYukle);
        yukle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, KsyitActivity.class));
            }
        });

        mDatabaseRef = FirebaseDatabase.getInstance().getReference(Constants.DATABASE_PATH_UPLOADS);

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Fetch image data from firebase database
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //ImageUpload class require default constructor
                    Upload img = snapshot.getValue(Upload.class);
                    imgList.add(img);
                }


                //Init adapter
                adapter = new ImageListAdapter(MainActivity.this, R.layout.line_layout, imgList);
                adapter.sort(new Comparator<Upload>() {
                    @Override
                    public int compare(Upload upload, Upload t1) {
                        return upload.name.compareTo(t1.name);
                    }
                });
                //Set adapter for listview
                lv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}