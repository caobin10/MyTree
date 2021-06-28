package com.demo.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.demo.test.pojo.Unit;
import com.demo.test.ui.UnitTextView;

public class MainActivity extends AppCompatActivity
{
    private UnitTextView unitTv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unitTv= (UnitTextView) findViewById(R.id.tv_unit);
        unitTv.setFinishCallBack(new UnitTextView.UnitCodeCallBack<Unit>() {
            @Override
            public void setCallBack(Unit unit) {
                unitTv.setText(unit.getUnitName());
                unitTv.setTag(unit);
            }
        });
    }
}
