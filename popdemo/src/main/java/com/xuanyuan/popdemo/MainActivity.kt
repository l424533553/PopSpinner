package com.xuanyuan.popdemo

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.xuanyuan.popspinner.PopSpinner
import com.xuanyuan.popspinner.PopSpinnerItemClickListener
import java.util.*

class MainActivity : AppCompatActivity() {

    var sp1: PopSpinner<String>? = null
    var sp2: PopSpinner<Student>? = null

    private lateinit var menuPopupWindow: PopupWindow


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sp1 = findViewById(R.id.pop1)
        sp2 = findViewById(R.id.pop2)

        val btnTest = findViewById<View>(R.id.btnTest)

        btnTest.setOnClickListener {
            if (menuPopupWindow.isShowing) {
                menuPopupWindow.dismiss()
            } else {

                // Gravity.CENTER  布局中部 ;Gravity.BOTTOM 布局底部
                menuPopupWindow.   showAtLocation(btnTest, Gravity.BOTTOM, 0, 0)
//                menuPopupWindow.   showAtLocation(btnTest, 0, 0, Gravity.CENTER)
            }
        }


        initData1()
        initData2()

        initMenuPop()

    }

    private fun initMenuPop() {
        /* menu */
        val menuView = View.inflate(this, R.layout.main_menu, null)
        val addLocalFile = menuView.findViewById(R.id.add_local_file) as LinearLayout
        val about = menuView.findViewById(R.id.about) as LinearLayout
        val feedback = menuView.findViewById(R.id.feedback) as LinearLayout
        addLocalFile.setOnClickListener {
            Toast.makeText(this, "本地文件", Toast.LENGTH_SHORT).show()
        }

        menuPopupWindow = PopupWindow(
            menuView,
            CoordinatorLayout.LayoutParams.MATCH_PARENT,
            CoordinatorLayout.LayoutParams.WRAP_CONTENT
        )
        menuPopupWindow.isFocusable = true
        menuPopupWindow.setBackgroundDrawable(ColorDrawable(Color.argb(200, 81, 86, 88)))
        menuPopupWindow.animationStyle = R.style.PopupAnimation


    }


    fun initData1() {
        val names = arrayOf(
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11",
            "12",
            "13",
            "14",
            "15",
            "16",
            "17"
        )
        sp1!!.setData(Arrays.asList(*names))
        sp1!!.listener = object : PopSpinnerItemClickListener<String> {
            override fun onItemSelected(position: Int, s: String, value: String) {
                Toast.makeText(this@MainActivity, "你点击的是:$value", Toast.LENGTH_SHORT).show()
                sp1!!.dismiss()
            }

            override fun setContent(s: String): String {
                return s
            }
        }
        sp1!!.setText("8")
    }

    fun initData2() {
        val list: ArrayList<Student> = ArrayList<Student>()
        for (i in 0..9) {
            val student = Student()
            student.setAge(10 + i)
            student.setName("学生 $i")
            list.add(student)
        }
        sp2!!.setData(list)
        sp2!!.listener = object : PopSpinnerItemClickListener<Student> {
            override fun onItemSelected(position: Int, s: Student, value: String) {
                Toast.makeText(this@MainActivity, "你点击的是:$value", Toast.LENGTH_SHORT).show()
                sp2!!.dismiss()
            }

            override fun setContent(s: Student): String {
                return s.getName()
            }
        }
        sp2!!.setText("学生 7")
    }
}