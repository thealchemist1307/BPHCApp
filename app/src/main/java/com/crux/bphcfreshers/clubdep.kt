package com.crux.bphcfreshers

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.crux.bphcfreshers.clubdep

class clubdep : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clubdep)
        val Bclub = findViewById<View>(R.id.Bclub) as Button
        Bclub.setOnClickListener(this)
        val Bdep = findViewById<View>(R.id.Bdep) as Button
        Bdep.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.Bdep -> openDep()
            R.id.Bclub -> openClub()
        }
    }

    fun openClub() {
        val intent = Intent(this@clubdep, Clubs::class.java)
        startActivity(intent)
    }

    fun openDep() {
        val intent = Intent(this@clubdep, Deps::class.java)
        startActivity(intent)
    }
}