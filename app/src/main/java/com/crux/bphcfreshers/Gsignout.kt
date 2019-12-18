package com.crux.bphcfreshers

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.crux.bphcfreshers.Gsignout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener

class Gsignout : AppCompatActivity() {
    var mAuth: FirebaseAuth? = null
    var mAuthListner: AuthStateListener? = null
    override fun onStart() {
        super.onStart()
        mAuth!!.addAuthStateListener(mAuthListner!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gsignout)
        mAuth = FirebaseAuth.getInstance()
        val signout = findViewById<Button>(R.id.signout)
        signout.setOnClickListener { (mAuth ?: return@setOnClickListener).signOut() }
        mAuthListner = AuthStateListener { firebaseAuth ->
            if (firebaseAuth.currentUser == null) {
                Toast.makeText(this@Gsignout, "Sign Out Successful", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@Gsignout, MainActivity::class.java))
            }
        }
    }
}