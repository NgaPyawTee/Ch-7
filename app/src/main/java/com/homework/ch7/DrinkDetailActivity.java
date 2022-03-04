package com.homework.ch7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DrinkDetailActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "drink_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_detail);

        int drinkID = (int) getIntent().getExtras().get(EXTRA_ID);
        Drink drink = Drink.drinks[drinkID];

        ImageView imageView = findViewById(R.id.image_view);
        TextView textView1 = findViewById(R.id.text_view1);
        TextView textView2 = findViewById(R.id.text_view2);

        imageView.setImageResource(drink.getImageResourceID());
        textView1.setText(drink.getName());
        textView2.setText(drink.getDescription());
    }
}