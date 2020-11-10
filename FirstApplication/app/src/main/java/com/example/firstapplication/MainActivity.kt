package com.example.firstapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // intent에서 이름을 가져와서 화면에 표시해 줌.
        //val userName = intent.getStringExtra("user_name")
        //greeting_text.text = "안녕하세요, ${userName}님."

        // sharedPreferences에서 이름을 가져와서 화면에 표시해 줌.
        setNameData()

        name_edit_button.setOnClickListener {
            goToNameActivity()
        }

        submit_button.setOnClickListener{
            if (edit_text.text.isBlank()) Toast.makeText(this, "값이 없어요!", Toast.LENGTH_LONG).show()
            else clickFunction()
        }

        edit_text.requestFocus() // 에딧텍스트에 포커스를 준다 (Manifest 안에 softInput이 설정되어 있으면 키보드도 나타남)
    }

    fun clickFunction(){
        //println("SOMETHING")
        Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_LONG).show()

        val timeString = SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault()).format(Date())

        val sharedPreferences = getSharedPreferences("post_info", MODE_PRIVATE)
        sharedPreferences
            .edit()
            .putString("post_time", timeString)
            .putString("post_input", edit_text.text.toString())
            .apply()
        setResult(RESULT_OK)
        finish()

        /*
        val timeString = SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.getDefault()).format(Date())
        time_text.text = timeString
        input_text.text = edit_text.text.toString() // 입력값

        edit_text.text.clear()

        // hide keyboard
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(edit_text.windowToken, 0)
        */

        // 새로운 입력값으로 바꾸고 싶다면......
        // edit_text.setText("새로운 입력값") // 내용 변경
        // edit_text.setSelection(edit_text.text.length) // 맨 끝으로 커서 이동
    }

    fun goToNameActivity(){
        val intent = Intent(this, NameActivity::class.java)
        startActivityForResult(intent, 2000) // 이름액티비티 실행
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2000 && resultCode == RESULT_OK){
           setNameData()
        }
    }
    fun setNameData(){
        val sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE)
        val userName = sharedPreferences.getString("user_name", "이름 없음")
        greeting_text.text = "안녕하세요, ${userName}님."
    }
}