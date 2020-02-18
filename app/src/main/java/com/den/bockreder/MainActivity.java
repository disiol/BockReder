package com.den.bockreder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.den.bockreder.databese.DatabaseAccess;
import com.den.bockreder.databinding.ActivityMainBinding;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String SELECTED_BOK_TITLE = "selectedBokTitle";
    private static final String SELECTED_BOK_TEXT = "selectedBokText";
    private ListView listView;
    private ActivityMainBinding binding;
    private DatabaseAccess instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        instance = DatabaseAccess.getInstance(this);

        binding.porularWordsButton.setOnClickListener(V -> {
            getCategories(instance);

        });
        binding.allWordsButton.setOnClickListener(V -> {
            selecteAllWords(instance);
        });

    }

    private void getCategories(DatabaseAccess databaseAccess) {
        binding.listOfWordsLinearLayout.removeAllViews();

        databaseAccess.open();
        List<String> categories = databaseAccess.getQuotes("categories", 2);
        List<String> text = databaseAccess.getQuotes("categories", 3);
        databaseAccess.close();

        for (int index = 0; index < categories.size() - 1; index++) {
            addTitesOfBucs(databaseAccess, categories, text, index);
        }

        //TODO abd to batons
    }


    private void selecteAllWords(DatabaseAccess databaseAccess) {
        binding.listOfWordsLinearLayout.removeAllViews();

        databaseAccess.open();
        List<String> categories = databaseAccess.getQuotes("items", 2);
        List<String> text = databaseAccess.getQuotes("items", 3);
        databaseAccess.close();

        for (int index = 0; index < categories.size() - 1; index++) {
            addTitesOfBucs(databaseAccess, categories, text, index);
        }

    }


    private void addTitesOfBucs(DatabaseAccess databaseAccess, List<String> categories, List<String> text, int index) {
        Button button = new Button(this);
        String title = categories.get(index);
        button.setText(title);

        binding.listOfWordsLinearLayout.addView(button);

        button.setOnClickListener(v -> {
            selectedBok(databaseAccess, title, text.get(index));

        });
    }


    private void selectedBok(DatabaseAccess databaseAccess, String title, String text) {

        openBok(title, text);


    }

    private void openBok(String title, String text) {
        Intent intent = new Intent(this, BockActivity.class);
        intent.putExtra(SELECTED_BOK_TITLE,title);
        intent.putExtra(SELECTED_BOK_TEXT, text);
        startActivity(intent);
    }
}
