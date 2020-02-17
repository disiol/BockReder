package com.den.bockreder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.den.bockreder.databese.DatabaseAccess;
import com.den.bockreder.databinding.ActivityMainBinding;

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
        List<String> categoriesEntryId = databaseAccess.getQuotes("categories", 0);
        databaseAccess.close();

        for (int index = 0; index < categories.size() - 1; index++) {
            addTitesOfBucs(databaseAccess, categories, categoriesEntryId, index);
        }

        //TODO abd to batons
    }

    private void addTitesOfBucs(DatabaseAccess databaseAccess, List<String> categories, List<String> categoriesEntryId, int index) {
        Button button = new Button(this);
        button.setText(categories.get(index));
        selectedBok(databaseAccess, categoriesEntryId.toString());
        binding.listOfWordsLinearLayout.addView(button);
    }


    private void selecteAllWords(DatabaseAccess databaseAccess) {
        binding.listOfWordsLinearLayout.removeAllViews();

        databaseAccess.open();
        List<String> categories = databaseAccess.getQuotes("items", 2);
        List<String> categoriesEntryId = databaseAccess.getQuotes("items", 1);
        databaseAccess.close();

        for (int index = 0; index < categories.size() - 1; index++) {
            addTitesOfBucs(databaseAccess, categories, categoriesEntryId, index);
        }

    }

    private void selectedBok(DatabaseAccess databaseAccess, String entryId) {
        databaseAccess.open();
        List<String> selectedBokTitle = databaseAccess.getDataByEntryId(2, entryId);
        List<String> selectedBokText = databaseAccess.getDataByEntryId(3, entryId);


        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(SELECTED_BOK_TITLE, selectedBokTitle.toString());
        intent.putExtra(SELECTED_BOK_TEXT, selectedBokText.toString());
        startActivity(intent);
        finish();

        databaseAccess.close();

    }
}
