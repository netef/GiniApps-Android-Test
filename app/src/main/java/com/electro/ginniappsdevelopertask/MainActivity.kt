package com.electro.ginniappsdevelopertask

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private val TAG = javaClass.simpleName
    private val arrayOfIntegers = arrayListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val queue = Volley.newRequestQueue(this)
        val url = "https://pastebin.com/raw/8wJzytQX"
        val arrayRequest = StringRequest(url,
            Response.Listener<String> { response ->
                try {
                    val obj = JSONObject(response)
                    val array = obj.getJSONArray("numbers")
                    for (i in 0 until array.length())
                        arrayOfIntegers.add(array.getJSONObject(i).getInt("number"))
                    arrayOfIntegers.sort()
                    recycler_view.setHasFixedSize(true)
                    recycler_view.adapter = RecyclerViewAdapter(arrayOfIntegers.toIntArray())
                } catch (e: Exception) {
                    Log.e(TAG, "Response.Listener: ", e)
                }
            }, Response.ErrorListener {
                Log.e(TAG, "Error: ", it)
            })
        queue.add(arrayRequest)
    }
}