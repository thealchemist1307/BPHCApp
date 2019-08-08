package com.crux.bphcfreshers;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Clubs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubs);
        Button crux=findViewById(R.id.Bcrux);
        crux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(Clubs.this);
                View mview=getLayoutInflater().inflate(R.layout.crux,null);
           mbuilder.setView(mview);
           AlertDialog dialog=mbuilder.create();
           dialog.show();

            }

        });
        Button Belas=findViewById(R.id.Belas);
        Belas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(Clubs.this);
                View mview=getLayoutInflater().inflate(R.layout.elas,null);
                mbuilder.setView(mview);
                AlertDialog dialog=mbuilder.create();
                dialog.show();

            }

        });
        Button bcooking=findViewById(R.id.bcooking);
        bcooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(Clubs.this);
                View mview=getLayoutInflater().inflate(R.layout.cooking,null);
                mbuilder.setView(mview);
                AlertDialog dialog=mbuilder.create();
                dialog.show();

            }

        });
        Button bshades=findViewById(R.id.bshades);
        bshades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(Clubs.this);
                View mview=getLayoutInflater().inflate(R.layout.shades,null);
                mbuilder.setView(mview);
                AlertDialog dialog=mbuilder.create();
                dialog.show();

            }

        });
        Button bvfx=findViewById(R.id.bvfx);
        bvfx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(Clubs.this);
                View mview=getLayoutInflater().inflate(R.layout.vfx,null);
                mbuilder.setView(mview);
                AlertDialog dialog=mbuilder.create();
                dialog.show();

            }

        });
        Button bhindi=findViewById(R.id.bhindi);
        bhindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(Clubs.this);
                View mview=getLayoutInflater().inflate(R.layout.hindi,null);
                mbuilder.setView(mview);
                AlertDialog dialog=mbuilder.create();
                dialog.show();

            }

        });
        Button bquiz=findViewById(R.id.bquiz);
        bquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(Clubs.this);
                View mview=getLayoutInflater().inflate(R.layout.quiz,null);
                mbuilder.setView(mview);
                AlertDialog dialog=mbuilder.create();
                dialog.show();

            }

        });
        Button bdc=findViewById(R.id.bdc);
        bdc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(Clubs.this);
                View mview=getLayoutInflater().inflate(R.layout.dc,null);
                mbuilder.setView(mview);
                AlertDialog dialog=mbuilder.create();
                dialog.show();

            }

        });
        Button bdrama=findViewById(R.id.bdrama);
        bdrama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(Clubs.this);
                View mview=getLayoutInflater().inflate(R.layout.dramatic,null);
                mbuilder.setView(mview);
                AlertDialog dialog=mbuilder.create();
                dialog.show();

            }

        });
        Button bmovie=findViewById(R.id.bmovie);
        bmovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(Clubs.this);
                View mview=getLayoutInflater().inflate(R.layout.movie,null);
                mbuilder.setView(mview);
                AlertDialog dialog=mbuilder.create();
                dialog.show();

            }

        });
        Button bphotog=findViewById(R.id.bphotog);
        bphotog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(Clubs.this);
                View mview=getLayoutInflater().inflate(R.layout.photog,null);
                mbuilder.setView(mview);
                AlertDialog dialog=mbuilder.create();
                dialog.show();

            }

        });
        Button bda=findViewById(R.id.bda);
        bda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(Clubs.this);
                View mview=getLayoutInflater().inflate(R.layout.da,null);
                mbuilder.setView(mview);
                AlertDialog dialog=mbuilder.create();
                dialog.show();

            }

        });
        Button bsafl=findViewById(R.id.bsafl);
        bsafl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(Clubs.this);
                View mview=getLayoutInflater().inflate(R.layout.safl,null);
                mbuilder.setView(mview);
                AlertDialog dialog=mbuilder.create();
                dialog.show();

            }

        });
        Button bjournal=findViewById(R.id.bjournal);
        bjournal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(Clubs.this);
                View mview=getLayoutInflater().inflate(R.layout.journal,null);
                mbuilder.setView(mview);
                AlertDialog dialog=mbuilder.create();
                dialog.show();

            }

        });
        Button bembryo=findViewById(R.id.bembryo);
        bembryo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(Clubs.this);
                View mview=getLayoutInflater().inflate(R.layout.embryo,null);
                mbuilder.setView(mview);
                AlertDialog dialog=mbuilder.create();
                dialog.show();

            }

        });
        Button bmun=findViewById(R.id.bmun);
        bmun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(Clubs.this);
                View mview=getLayoutInflater().inflate(R.layout.bitsmun,null);
                mbuilder.setView(mview);
                AlertDialog dialog=mbuilder.create();
                dialog.show();

            }

        });
        Button bted=findViewById(R.id.bted);
        bted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(Clubs.this);
                View mview=getLayoutInflater().inflate(R.layout.ted,null);
                mbuilder.setView(mview);
                AlertDialog dialog=mbuilder.create();
                dialog.show();

            }

        });
    }
}
