package com.example.medicalcenterapp.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.medicalcenterapp.R
import com.example.medicalcenterapp.utilities.RetrofitClient
import kotlinx.android.synthetic.main.activity_chatlogin.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ChatLoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatlogin)
        supportActionBar!!.title = "Chat Login"
        supportActionBar!!.setDefaultDisplayHomeAsUpEnabled(true)

        loginBtn.setOnClickListener {
            if (chatUsername.text.isNotEmpty()) {
                createNewUser(chatUsername.text.toString())
            } else {
                Toast.makeText(this@ChatLoginActivity,"Please enter a username", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun createNewUser(userName: String) {
        val jsonObject = JSONObject()
        jsonObject.put("username", userName)

        RetrofitClient().getClient().createUser(userName).enqueue(object: Callback<String> {
            override fun onFailure(call: Call<String>?, t: Throwable?) {
                Log.d("TAG", t.toString())
            }

            override fun onResponse(call: Call<String>?, response: Response<String>?) {
                if (response!!.code() == 200){
                    startActivity(Intent(this@ChatLoginActivity, RoomsListActivity::class.java)
                        .putExtra("extra",userName))
                }
            }
        })
    }
}
