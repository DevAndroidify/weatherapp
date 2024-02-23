package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.weatherapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchdata("Delhi")
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        var city:String
        binding.searchView2.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
fetchdata(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // This method is called when the user changes the search query text.
                // You can perform real-time filtering or other operations here if needed.
                return true
            }
        })


    }

    private fun fetchdata(query: String) {
        val api = retrofithelper.getInstance().create(api::class.java)

        val weat = api.getdat(query, "58261eebbee003dd7bcfb64101c36382")
        weat.enqueue(object : Callback<weather> {
            override fun onResponse(call: Call<weather>, response: Response<weather>) {
                val a = response.body()
                binding.textView5.text = a?.main?.temp.toString()
                binding.textView3.text = a?.name.toString()
                binding.textView6.text = "Min:" + a?.main?.temp_min.toString()
                binding.textView7.text = "Max:" + a?.main?.temp_max.toString()
                binding.sunset.text = "sunset:" + a?.sys?.sunset.toString()
                binding.sunrise.text = "sunrise:" + a?.sys?.sunrise.toString()
                binding.humidity.text = "humidity:" + a?.main?.humidity.toString()
                binding.sea.text = "sea:" + 100
                binding.weather.text = a?.weather?.firstOrNull()?.main.toString()
                binding.speed.text = "speed:" + a?.wind?.speed.toString()
                binding.condition.text = "condition:" + a?.weather?.firstOrNull()?. main.toString()
                val condition=a?.weather?.firstOrNull()?.main.toString()
                when(condition){
                    "Haze","Mist","Clouds"->binding.background.setBackgroundResource(R.drawable.colud_background)
                    "Sunny"->binding.background.setBackgroundResource(R.drawable.sunny_background)
                    "Drizzle"->binding.background.setBackgroundResource(R.drawable.rain_background)
                }

            }

            override fun onFailure(call: Call<weather>, t: Throwable) {
                Toast.makeText(applicationContext, "No response got", Toast.LENGTH_SHORT).show()
            }

        })

    }
}



