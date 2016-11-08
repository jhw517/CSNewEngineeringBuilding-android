package com.csnewengineeringbuilding;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.csnewengineeringbuilding.floor.FirstFloorFragment;
import com.csnewengineeringbuilding.floor.SecondFloorFragment;
import com.csnewengineeringbuilding.floor.ThirdFloorFragment;
import com.csnewengineeringbuilding.util.Constants;

import me.kaelaela.verticalviewpager.VerticalViewPager;

public class NewEngineeringBuildingUI extends AppCompatActivity {

    private VerticalViewPager verticalViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_engineering_building_ui);
        initVerticalViewPager();
    }

    private void initVerticalViewPager() {
        verticalViewPager = (VerticalViewPager) findViewById(R.id.verticalviewpager);
        verticalViewPager.setAdapter(new FloorFragmentAdapter.Holder(getSupportFragmentManager())
                .add(FirstFloorFragment.newInstance(Constants.FIRST_FLOOR))
                .add(SecondFloorFragment.newInstance(Constants.SECOND_FLOOR))
                .add(ThirdFloorFragment.newInstance(Constants.THIRD_FLOOR))
                .set());
    }
}

