package com.example.mycode.Pagination.View

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.mycode.Pagination.adapter.PagingAdapter
import com.example.mycode.Pagination.model.DataModles
import com.example.mycode.databinding.ActivityPaginBinding
import org.json.JSONArray


class PaginActivity : AppCompatActivity() {
    lateinit var binding: ActivityPaginBinding
    var page = 0
    var limit = 2
    lateinit var model: ArrayList<DataModles>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getApiFrom(page,limit)

        binding.pagingNested.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            // on scroll change we are checking when users scroll as bottom.
            if (scrollY == v.getChildAt(0).measuredHeight - v.measuredHeight) {
                // in this method we are incrementing page number,
                // making progress bar visible and calling get data method.
                page++
                binding.progressLoader.visibility = View.VISIBLE
                getApiFrom(page, limit)
            }
        })

    }


    private fun getData(){

    }

    private fun getApiFrom(page: Int, limit: Int) {

        if (page > limit) {
            Toast.makeText(this, "Thats all the data..", Toast.LENGTH_SHORT).show()
            binding.progressLoader.visibility = View.GONE
            return
        }

        val url = "https://reqres.in/api/users?page=$page"
        val queue: RequestQueue = Volley.newRequestQueue(applicationContext)
        val request = JsonObjectRequest(Request.Method.GET, url, null, { response ->

            try {

                val dataArray: JSONArray = response.getJSONArray("data")
                for (i in 0 until dataArray.length()) {
                    val jsonObject = dataArray.getJSONObject(i)

                    // on below line we are extracting data from our json object.
                    model.add(
                        DataModles(
                            jsonObject.getString("first_name"),
                            jsonObject.getString("last_name"),
                            jsonObject.getString("email"),
                            jsonObject.getString("avatar")
                        )
                    )

                    // passing array list to our adapter class.
                    val adapter = PagingAdapter(this@PaginActivity)
                    // setting layout manager to our recycler view.
                    binding.pagingRecycler.layoutManager = LinearLayoutManager(this@PaginActivity)

                    // setting adapter to our recycler view.
                    binding.pagingRecycler.adapter = adapter
                }

            } catch (e: Exception) {
                // on below line we are
                // handling our exception.
                e.printStackTrace()
            }

        }, { error ->

        })

        queue.add(request)
        Log.i(TAG, "getApiFrom"+queue.add(request).toString())
    }

}