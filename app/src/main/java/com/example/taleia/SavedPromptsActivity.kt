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
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.delay


class SavedPromptsActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel

    var recyclerView: RecyclerView? = null
    var recyclerViewAdapter: RecyclerViewAdapter? = null
    var rowsArrayList: ArrayList<String?> = ArrayList()
    var isLoading = false
    var collectionId: String = "617d0845ca7be" //Escena Id

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_prompts)
        recyclerView = findViewById(R.id.recyclerView)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        populateData()
        initAdapter()
        initScrollListener()
    }

    private fun populateData() {
        //HERE ADD FIRST PAGE
        var offset = 0
        mainViewModel.create(this)

        val cardList = mainViewModel.listDocuments(this,collectionId,offset)
        for (row in cardList) {
            rowsArrayList.add(row)
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

            // HERE LOAD FROM APPWRITE SAVED FILES, SHOULD paginate and think about
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