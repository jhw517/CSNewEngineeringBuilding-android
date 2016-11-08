package com.csnewengineeringbuilding;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import at.markushi.ui.CircleButton;
import butterknife.Bind;
import butterknife.ButterKnife;
import me.drakeet.uiview.UIButton;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.search_class_button)
    CircleButton searchClassButton;
    @Bind(R.id.borrow_class_button)
    UIButton borrowClassButton;
    @Bind(R.id.state_class_button)
    UIButton stateClassButton;
    @Bind(R.id.manage_lostitem_button)
    UIButton manageLostItemButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initToolbar();
        initListener();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
    }
    private void initListener() {
        searchClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "1", Toast.LENGTH_SHORT).show();
            }
        });

        stateClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewEngineeringBuildingUI.class);
                startActivity(intent);
            }
        });

        borrowClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "3", Toast.LENGTH_SHORT).show();
            }
        });

        manageLostItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LostItemActivity.class);
                startActivity(intent);
            }
        });
    }

}
