package com.example.myddd;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List al;
    private ArrayAdapter<String>arrayAdapter;
    SwipeFlingAdapterView flingContainer;
    @SuppressLint("WrongViewCast")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //add the view via xml or programmatically
        flingContainer = findViewById(R.id. frame);

        al = new ArrayList();
        al.add("aaa"); /*改照片~123~~~*/
        al.add("c");
        al.add("bbb");
        al.add("ddd");

        //choose your favorite adapter
        arrayAdapter = new ArrayAdapter<>(this, R.layout.item, R.id.helloText, al );

        //set the listener and the adapter

        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {

            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                al.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            public void onLeftCardExit(Object O) {

                Toast.makeText(MainActivity.this, "Left!", Toast.LENGTH_SHORT).show();
            }


            public void onRightCardExit(Object o) {
                Toast.makeText(MainActivity.this, "Right!", Toast.LENGTH_SHORT).show();
            }


            public void onAdapterAboutToEmpty(int i) {
                al.add("XML ".concat(String.valueOf(i)));/*改執行*/
                arrayAdapter.notifyDataSetChanged();
                 i++;
            }



        public void onScroll(float scrollProgressPercent) {

            View view = flingContainer.getSelectedView();
            view.findViewById(R.id.item_swipe_left_indicator)
                    .setAlpha(scrollProgressPercent <0 ?-scrollProgressPercent:0);
            view.findViewById(R.id.item_swipe_right_indicator)
                    .setAlpha(scrollProgressPercent <0 ?-scrollProgressPercent:0);
        }
        });

        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {

            public void onItemClicked(int itemPosition, Object dataObject) {
               Toast.makeText( MainActivity.this, "Tiklandi",Toast.LENGTH_SHORT).show();
            }
        });
    }
//
//    public void left(View view){flingContainer.getTopCardListener().selectLeft();}
//    public void right(View view){flingContainer.getTopCardListener().selectRight(); }
}
