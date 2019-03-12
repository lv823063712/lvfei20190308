package soexample.bigfly.com.lvfei20190308.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import soexample.bigfly.com.lvfei20190308.R;

public class Main4Activity extends AppCompatActivity {
    private String[] lists = {"手机", "电脑", "IG", "RNG"};
    private ArrayList<String> datas = new ArrayList<>();
    private ArrayList<String> mHistory = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
    }
}
