package com.example.tengyu.lifeholder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.appyvet.rangebar.RangeBar;

public class EditActivity extends AppCompatActivity {
    private RangeBar tomato_rangebar;
    private TextView tomato_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit);

        Toolbar toolbar = (Toolbar) findViewById(R.id.tomatotask_edit_toolbar);
        toolbar.setTitle("Task Edit");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tomato_rangebar = (RangeBar) findViewById(R.id.tomatotask_edit_rangebar);
        tomato_index = (TextView) findViewById(R.id.tomatotask_edit_tomatoIndex);
        tomato_rangebar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex,
                                              int rightPinIndex,
                                              String leftPinValue, String rightPinValue) {
                    tomato_index.setText(String.valueOf(rightPinIndex+1));
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
