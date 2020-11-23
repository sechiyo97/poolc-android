package com.example.onelinediary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_diary.layoutManager = LinearLayoutManager(this)

        val adapter = DiaryAdapter()
        recycler_diary.adapter = adapter

        adapter.diaryList = listOf(
            Diary(Date(), "new")
        )
        adapter.notifyDataSetChanged()
    }
}