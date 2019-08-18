package com.gigabytedevs.apps.midclan.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;

import com.gigabytedevs.apps.midclan.R;

public class SearchActivity extends AppCompatActivity {
    private AppCompatImageButton back, clearSearch;
    private AppCompatEditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        back = findViewById(R.id.search_back);
        back.setOnClickListener(v -> {
            onBackPressed();
        });
        search = findViewById(R.id.search_edit_text);
        search.addTextChangedListener(textWatcher);
        clearSearch = findViewById(R.id.search_clear);
        clearSearch.setOnClickListener(v->{
            search.setText("");
        });
    }
    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(s.toString().trim().length() == 0){
                clearSearch.setVisibility(View.GONE);
            }else {
                clearSearch.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}
