package com.crux.bphcfreshers;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class clubdep extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubdep);
        Button Bclub=(Button) findViewById(R.id.Bclub);
        Bclub.setOnClickListener(this);
        Button Bdep=(Button)findViewById(R.id.Bdep);
        Bdep.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        { case R.id.Bdep:
            openDep();
            break;
            case R.id.Bclub:
                openClub();
                break;

        }
    }

   public void openClub()
    {
        Intent intent =new Intent(clubdep.this,Clubs.class);
        startActivity(intent);
    }
public void openDep()
{ Intent intent =new Intent(clubdep.this,Deps.class);
    startActivity(intent);

}
}
