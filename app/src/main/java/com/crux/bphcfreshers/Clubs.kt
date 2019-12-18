package com.crux.bphcfreshers

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.crux.bphcfreshers.Clubs

class Clubs : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clubs)
        val crux = findViewById<Button>(R.id.Bcrux)
        crux.setOnClickListener {
            val mbuilder = AlertDialog.Builder(this@Clubs)
            val mview = layoutInflater.inflate(R.layout.crux, null)
            mbuilder.setView(mview)
            val dialog = mbuilder.create()
            dialog.show()
        }
        //        Button Belas=findViewById(R.id.Belas);
//        Belas.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder mbuilder = new AlertDialog.Builder(Clubs.this);
//                View mview=getLayoutInflater().inflate(R.layout.elas,null);
//                mbuilder.setView(mview);
//                AlertDialog dialog=mbuilder.create();
//                dialog.show();
//
//            }
//
//        });
        val bcooking = findViewById<Button>(R.id.bcooking)
        bcooking.setOnClickListener {
            val mbuilder = AlertDialog.Builder(this@Clubs)
            val mview = layoutInflater.inflate(R.layout.cooking, null)
            mbuilder.setView(mview)
            val dialog = mbuilder.create()
            dialog.show()
        }
        val bshades = findViewById<Button>(R.id.bshades)
        bshades.setOnClickListener {
            val mbuilder = AlertDialog.Builder(this@Clubs)
            val mview = layoutInflater.inflate(R.layout.shades, null)
            mbuilder.setView(mview)
            val dialog = mbuilder.create()
            dialog.show()
        }
        val bvfx = findViewById<Button>(R.id.bvfx)
        bvfx.setOnClickListener {
            val mbuilder = AlertDialog.Builder(this@Clubs)
            val mview = layoutInflater.inflate(R.layout.vfx, null)
            mbuilder.setView(mview)
            val dialog = mbuilder.create()
            dialog.show()
        }
        val bhindi = findViewById<Button>(R.id.bhindi)
        bhindi.setOnClickListener {
            val mbuilder = AlertDialog.Builder(this@Clubs)
            val mview = layoutInflater.inflate(R.layout.hindi, null)
            mbuilder.setView(mview)
            val dialog = mbuilder.create()
            dialog.show()
        }
        val bquiz = findViewById<Button>(R.id.bquiz)
        bquiz.setOnClickListener {
            val mbuilder = AlertDialog.Builder(this@Clubs)
            val mview = layoutInflater.inflate(R.layout.quiz, null)
            mbuilder.setView(mview)
            val dialog = mbuilder.create()
            dialog.show()
        }
        val bdc = findViewById<Button>(R.id.bdc)
        bdc.setOnClickListener {
            val mbuilder = AlertDialog.Builder(this@Clubs)
            val mview = layoutInflater.inflate(R.layout.dc, null)
            mbuilder.setView(mview)
            val dialog = mbuilder.create()
            dialog.show()
        }
        val bdrama = findViewById<Button>(R.id.bdrama)
        bdrama.setOnClickListener {
            val mbuilder = AlertDialog.Builder(this@Clubs)
            val mview = layoutInflater.inflate(R.layout.dramatic, null)
            mbuilder.setView(mview)
            val dialog = mbuilder.create()
            dialog.show()
        }
        val bmovie = findViewById<Button>(R.id.bmovie)
        bmovie.setOnClickListener {
            val mbuilder = AlertDialog.Builder(this@Clubs)
            val mview = layoutInflater.inflate(R.layout.movie, null)
            mbuilder.setView(mview)
            val dialog = mbuilder.create()
            dialog.show()
        }
        val bphotog = findViewById<Button>(R.id.bphotog)
        bphotog.setOnClickListener {
            val mbuilder = AlertDialog.Builder(this@Clubs)
            val mview = layoutInflater.inflate(R.layout.photog, null)
            mbuilder.setView(mview)
            val dialog = mbuilder.create()
            dialog.show()
        }
        val bda = findViewById<Button>(R.id.bda)
        bda.setOnClickListener {
            val mbuilder = AlertDialog.Builder(this@Clubs)
            val mview = layoutInflater.inflate(R.layout.da, null)
            mbuilder.setView(mview)
            val dialog = mbuilder.create()
            dialog.show()
        }
        val bsafl = findViewById<Button>(R.id.bsafl)
        bsafl.setOnClickListener {
            val mbuilder = AlertDialog.Builder(this@Clubs)
            val mview = layoutInflater.inflate(R.layout.safl, null)
            mbuilder.setView(mview)
            val dialog = mbuilder.create()
            dialog.show()
        }
        val bjournal = findViewById<Button>(R.id.bjournal)
        bjournal.setOnClickListener {
            val mbuilder = AlertDialog.Builder(this@Clubs)
            val mview = layoutInflater.inflate(R.layout.journal, null)
            mbuilder.setView(mview)
            val dialog = mbuilder.create()
            dialog.show()
        }
        //        Button bembryo=findViewById(R.id.bembryo);
//        bembryo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder mbuilder = new AlertDialog.Builder(Clubs.this);
//                View mview=getLayoutInflater().inflate(R.layout.embryo,null);
//                mbuilder.setView(mview);
//                AlertDialog dialog=mbuilder.create();
//                dialog.show();
//
//            }
//
//        });
        val bmun = findViewById<Button>(R.id.bmun)
        bmun.setOnClickListener {
            val mbuilder = AlertDialog.Builder(this@Clubs)
            val mview = layoutInflater.inflate(R.layout.bitsmun, null)
            mbuilder.setView(mview)
            val dialog = mbuilder.create()
            dialog.show()
        }
        val bted = findViewById<Button>(R.id.bted)
        bted.setOnClickListener {
            val mbuilder = AlertDialog.Builder(this@Clubs)
            val mview = layoutInflater.inflate(R.layout.ted, null)
            mbuilder.setView(mview)
            val dialog = mbuilder.create()
            dialog.show()
        }
    }
}