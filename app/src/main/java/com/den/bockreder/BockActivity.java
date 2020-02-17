package com.den.bockreder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.content.Intent;
import android.os.Bundle;

import com.den.bockreder.databinding.BockAtivityBinding;

public class BockActivity extends AppCompatActivity {
    private static final String SELECTED_BOK_TITLE = "selectedBokTitle";
    private static final String SELECTED_BOK_TEXT = "selectedBokText";
    private BockAtivityBinding binding;
    private String title;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bock_ativity);

        Intent intent = getIntent();
        title = intent.getStringExtra(SELECTED_BOK_TITLE);
        text = intent.getStringExtra(SELECTED_BOK_TEXT);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

         binding.titleTextView.setText(title);
         binding.textView.setText(text);



    }
}
