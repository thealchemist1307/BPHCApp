package com.crux.bphcfreshers

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.crux.bphcfreshers.GSigni
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.GoogleAuthProvider

class GSigni : AppCompatActivity(), View.OnClickListener {
    var mAuth: FirebaseAuth? = null
    var mAuthListner: AuthStateListener? = null
    var user: String? = null
    var email: String? = null
    var mGoogleSignInClient: GoogleSignInClient? = null
    override fun onStart() {
        super.onStart()
        mAuth!!.addAuthStateListener(mAuthListner!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gsigni)
        mAuth = FirebaseAuth.getInstance()
        val SignButton = findViewById<View>(R.id.sign_in_button) as SignInButton
        SignButton.setOnClickListener(this)
        mAuthListner = AuthStateListener { firebaseAuth ->
            if (firebaseAuth.currentUser != null) {
                startActivity(Intent(this@GSigni, MainActivity::class.java))
                Toast.makeText(this@GSigni, "Sign In Successful", Toast.LENGTH_SHORT).show()
            }
        }
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient!!.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try { // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                val googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
                if (googleSignInResult.isSuccess) { // Signed in successfully.
                    val acct = googleSignInResult.signInAccount
                    user = account!!.displayName
                    email = acct!!.email
                    val split = email!!.split("@").toTypedArray()
                    val domain = split[1] //This Will Give You The Domain After '@'
                    if (domain == "hyderabad.bits-pilani.ac.in") {
                        firebaseAuthWithGoogle(account)
                    } else {
                        Toast.makeText(applicationContext, "please login with your BITS ID", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: ApiException) { // Google Sign In failed, update UI appropriately
                Log.w("TAG", "Google sign in failed", e)
                // ...
            }
        }
    }

    private fun firebaseAuthWithGoogle(account: GoogleSignInAccount?) {
        val credential = GoogleAuthProvider.getCredential(account!!.idToken, null)
        mAuth!!.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) { // Sign in success, update UI with the signed-in user's information
                        Log.d("TAG", "signInWithCredential:success")
                        val user = mAuth!!.currentUser
                        // updateUI(user);
                    } else { // If sign in fails, display a message to the user.
                        Log.w("TAG", "signInWithCredential:failure", task.exception)
                        Toast.makeText(this@GSigni, "Sign In Failed", Toast.LENGTH_SHORT).show()
                        //  updateUI(null);
                    }
                    // ...
                }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.sign_in_button -> signIn()
        }
    }

    companion object {
        private const val RC_SIGN_IN = 101
    }
}