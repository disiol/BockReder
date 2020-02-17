package com.den.bockreder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private void getCategories(DatabaseAccess databaseAccess) {
        databaseAccess.open();
        List<String> categories = databaseAccess.getQuotes("categories", 2);
        List<String> categoriesEntryId = databaseAccess.getQuotes("categories", 1);

        List<String> selectedBok = databaseAccess.getDataByEntryId("8", 2);
        databaseAccess.close();
    }

    private void selectedBok(DatabaseAccess databaseAccess, String entryId) {
        databaseAccess.open();
        List<String> selectedBokTitle = databaseAccess.getDataByEntryId(entryId, 3);
        List<String> selectedBokText = databaseAccess.getDataByEntryId(entryId, 4);
        databaseAccess.close();

    }
}
