package com.example.sunshine;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView, leftImg, rightImg;
    private TextView textView;
    private LinearLayout infoContainer, imagesContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(getResources().getString(R.string.app_name));
        imageView = findViewById(R.id.accessories_image);
        leftImg = findViewById(R.id.left_image);
        rightImg = findViewById(R.id.right_image);
        textView = findViewById(R.id.description_text_view);
        imagesContainer = findViewById(R.id.images_container);
        infoContainer = findViewById(R.id.info_container);
        String highlightedName = "  Sunshine";
        String others = " shop provides all the accessories that men need in their daily life.";
        textView.setText(highlightedName + others, TextView.BufferType.SPANNABLE);
        Spannable sp = (Spannable)textView.getText();
        sp.setSpan(new ForegroundColorSpan(0xFFFB8C00), 0, highlightedName.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        sp.setSpan(new AbsoluteSizeSpan(22, true), 0, highlightedName.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            imagesContainer.setX(imageView.getX());
            imagesContainer.getLayoutParams().width = imageView.getWidth();
            imagesContainer.requestLayout();
            leftImg.getLayoutParams().width = imageView.getWidth()/2 - 10;
            leftImg.requestLayout();
            rightImg.setX(leftImg.getX() + leftImg.getWidth() + 20);
            rightImg.getLayoutParams().width = imageView.getWidth()/2 - 10;
            rightImg.requestLayout();
            infoContainer.setX(imageView.getX());
            infoContainer.getLayoutParams().width = imageView.getWidth();
            infoContainer.requestLayout();
        }
    }

    private void setTitle(String title){
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView textView = new TextView(this);
        textView.setText(title);
        textView.setTextSize(20);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(getResources().getColor(R.color.colorAccent));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(textView);
    }
}