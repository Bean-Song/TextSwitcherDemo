package my.demo.TSDemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextSwitcher;

import java.util.ArrayList;
import java.util.List;

import my.demo.TSDemo.adapter.TextSwitcherAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextSwitcher textSwitcher = findViewById(R.id.textSwitcher);
        List<String> list = new ArrayList<>();
        list.add("123aaaa");
        list.add("123aaaa.......");
        list.add("123aaaa.............");

        new TextSwitcherAdapter(this, textSwitcher, list);

    }


}
