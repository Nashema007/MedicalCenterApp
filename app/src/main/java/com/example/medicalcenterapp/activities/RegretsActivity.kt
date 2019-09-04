package com.example.medicalcenterapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.medicalcenterapp.R
import kotlinx.android.synthetic.main.activity_regrets.*

class RegretsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regrets)
        supportActionBar!!.setDefaultDisplayHomeAsUpEnabled(true)

        goChat.setOnClickListener {
            startActivity(Intent(this, ChatLoginActivity::class.java)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK))
        }
          goPharmacy.setOnClickListener {
            startActivity(Intent(this, PharmacyActivity::class.java)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK))
          }


    }
}
