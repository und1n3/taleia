package com.example.taleia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.NonNull

import android.widget.ProgressBar

import androidx.recyclerview.widget.RecyclerView

import android.widget.TextView

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import RecyclerViewAdapter
import android.os.Handler


class SavedPromptsActivity : AppCompatActivity() {

    var recyclerView: RecyclerView? = null
    var recyclerViewAdapter: RecyclerViewAdapter? = null
    var rowsArrayList: ArrayList<String?> = ArrayList()
    var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_prompts)
        recyclerView = findViewById(R.id.recyclerView)
        populateData()
        initAdapter()
        initScrollListener()
    }

    private fun populateData() {
        var i = 0
        while (i < 10) {
            rowsArrayList.add("Item $i")
            i++
        }
    }

    private fun initAdapter() {
        recyclerViewAdapter = RecyclerViewAdapter(rowsArrayList)
        recyclerView!!.adapter = recyclerViewAdapter
    }

    private fun initScrollListener() {
        recyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (!isLoading) {
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == rowsArrayList.size- 1) {
                        //bottom of list!
                        loadMore()
                        isLoading = true
                    }
                }
            }
        })
    }

    private fun loadMore() {
        rowsArrayList.add(null)
        recyclerViewAdapter!!.notifyItemInserted(rowsArrayList.size - 1)
        val handler = Handler()
        handler.postDelayed(Runnable {
            rowsArrayList.removeAt(rowsArrayList.size - 1)
            val scrollPosition: Int = rowsArrayList.size
            recyclerViewAdapter!!.notifyItemRemoved(scrollPosition)
            var currentSize = scrollPosition
            val nextLimit = currentSize + 25
            while (currentSize - 1 < nextLimit) {
                rowsArrayList.add("Item $currentSize")
                currentSize++
            }
            recyclerViewAdapter!!.notifyDataSetChanged()
            isLoading = false
        }, 2000)
    }
}