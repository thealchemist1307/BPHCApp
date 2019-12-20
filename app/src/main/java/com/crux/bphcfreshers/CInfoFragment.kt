package com.crux.bphcfreshers

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class CInfoFragment : Fragment(), View.OnClickListener {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cinfo, null)
        //        return super.onCreateView(inflater, container, savedInstanceState);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val Bhistory = view.findViewById<Button>(R.id.button5)
        val Bc_d = view.findViewById<Button>(R.id.button6)
        val Bnearby = view.findViewById<Button>(R.id.button7)
        val Blogo=view.findViewById<ImageView>(R.id.blogo);
        val cinftxt=view.findViewById<TextView>(R.id.textView3);
        val logoanim= AnimationUtils.loadAnimation(context,R.anim.logoanim);
        val bttnanim=AnimationUtils.loadAnimation(context,R.anim.bttnanim);
        Blogo.startAnimation(logoanim);
        cinftxt.startAnimation(logoanim);
        Bhistory.startAnimation(bttnanim);
        Bc_d.startAnimation(bttnanim);
        Bnearby.startAnimation(bttnanim);

        Bhistory.setOnClickListener(this)
        Bc_d.setOnClickListener(this)
        Bnearby.setOnClickListener(this)
        Bhistory.setOnClickListener(this)
        Bc_d.setOnClickListener(this)
        Bnearby.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button5 -> openHistory()
            R.id.button6 -> openClub()
            R.id.button7 -> openFests()
        }
    }

    fun openHistory() {
        val intent = Intent(this@CInfoFragment.activity, History::class.java)
        startActivity(intent)
    }

    fun openClub() {
        val intent = Intent(this@CInfoFragment.activity, clubdep::class.java)
        startActivity(intent)
    }

    fun openFests() {
        val intent = Intent(this@CInfoFragment.activity, Fests::class.java)
        startActivity(intent)
    }
}