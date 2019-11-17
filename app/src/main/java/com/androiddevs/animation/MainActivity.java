package com.androiddevs.animation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout parent = findViewById(R.id.linearLayout);
        Button btnShuffle = findViewById(R.id.btnShuffle);

        final List<String> texts = new ArrayList<>(Arrays.asList(
                "Instagram", "YouTube", "Twitter", "Facebook", "Pinterest", "PornHub", "LinkedIn"
        ));

        addViewsToLayout(this, parent, texts);
        btnShuffle.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                TransitionManager.beginDelayedTransition(parent, new ChangeBounds());
                Collections.shuffle(texts);
                addViewsToLayout(MainActivity.this, parent, texts);
            }
        });
    }

    private void addViewsToLayout(Context context, ViewGroup parent, List<String> texts) {
        parent.removeAllViews();
        for (String text : texts) {
            TextView tvItem = new TextView(context);
            tvItem.setText(text);
            tvItem.setGravity(Gravity.CENTER);
            tvItem.setTextSize(24f);
            tvItem.setTextColor(Color.WHITE);
            ViewGroup.MarginLayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 32, 0, 0);
            tvItem.setLayoutParams(params);
            com.transitionseverywhere.TransitionManager.setTransitionName(tvItem, text);
            parent.addView(tvItem);
        }
    }

}
