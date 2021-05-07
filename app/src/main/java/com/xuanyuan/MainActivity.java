package com.xuanyuan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.xuanyuan.bean.Student;
import com.xuanyuan.popspinner.PopSpinner;
import com.xuanyuan.popspinner.PopSpinnerItemClickListener;
import com.xuanyuan.popspinner.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author LUOFAXIN
 */
public class MainActivity extends AppCompatActivity {
    PopSpinner<String> sp1;
    PopSpinner<Student> sp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp1 = findViewById(R.id.pop1);
        sp2 = findViewById(R.id.pop2);

        initData1();
        initData2();
    }

    void initData1() {
        String[] names = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17"};
        sp1.setData(Arrays.asList(names));
        sp1.setListener(new PopSpinnerItemClickListener<String>() {
            @Override
            public void onItemSelected(int position, @NonNull String s, @NonNull String value) {
                Toast.makeText(MainActivity.this, "你点击的是:" + value, Toast.LENGTH_SHORT).show();
                sp1.dismiss();
            }

            @NonNull
            @Override
            public String setContent(@NonNull String s) {
                return s;
            }
        });
        sp1.setText("8");
    }

    void initData2() {

        ArrayList<Student> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setAge(10 + i);
            student.setName("学生 " + i);
            list.add(student);
        }

        sp2.setData(list);
        sp2.setListener(new PopSpinnerItemClickListener<Student>() {
            @Override
            public void onItemSelected(int position, @NonNull Student s, @NonNull String value) {
                Toast.makeText(MainActivity.this, "你点击的是:" + value, Toast.LENGTH_SHORT).show();
                sp2.dismiss();
            }

            @NonNull
            @Override
            public String setContent(@NonNull Student s) {
                return s.getName();
            }
        });
        sp2.setText("学生 7");
    }
}