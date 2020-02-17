package com.den.bockreder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.den.bockreder.databese.DatabaseAccess;
import com.den.bockreder.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

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


        databaseAccess.open();
        List<String> categories = databaseAccess.getQuotes("categories", 2);
        List<String> categoriesEntryId = databaseAccess.getQuotes("categories", 0);
        List<String> selectedBok = databaseAccess.getDataByEntryId(String.valueOf(categoriesEntryId), 0);
        databaseAccess.close();

        for (int index = 0; index < categories.size() - 1; index++) {
            TextView textView = new TextView(this);
            textView.setText(categories.get(index));
            binding.listOfWordsLinearLayout.addView(textView);
        }

        //TODO abd to batons
    }


    private void selecteAllWords(DatabaseAccess databaseAccess) {
        databaseAccess.open();
        List<String> categories = databaseAccess.getQuotes("categories", 2);
        List<String> categoriesEntryId = databaseAccess.getQuotes("categories", 1);
        List<String> selectedBok = databaseAccess.getDataByEntryId(String.valueOf(categoriesEntryId), 0);
        databaseAccess.close();

        for (int index = 0; index < categories.size() - 1; index++) {
            TextView textView = new TextView(this);
            textView.setText(categories.get(index));
            binding.listOfWordsLinearLayout.addView(textView);
        }

    }

    private void selectedBok(DatabaseAccess databaseAccess, String entryId) {
        databaseAccess.open();
        List<String> selectedBokTitle = databaseAccess.getDataByEntryId(entryId, 3);
        List<String> selectedBokText = databaseAccess.getDataByEntryId(entryId, 4);

        databaseAccess.close();

    }
}
