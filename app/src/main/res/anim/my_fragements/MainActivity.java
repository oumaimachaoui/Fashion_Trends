package com.example.oumaima.my_fragements;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button b=(Button)findViewById(R.id.b);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent i = new Intent(com.example.oumaima.my_fragements.MainActivity.this, com.example.oumaima.my_fragements.BeforHome.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
/*
        //toolbar for
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("oumaima fashion");
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.veryminilogo);
*/
        ImageView iv = findViewById(R.id.image);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        Animation animation2 = AnimationUtils.loadAnimation(com.example.oumaima.my_fragements.MainActivity.this, R.anim.slide_in_right);
        b.startAnimation(animation2);
        iv.startAnimation(animation);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // startActivity(i);
                    // finish();

                }
            }
        };
        timer.start();

    }


}

/*
    Intent intentRegister = new Intent(MainActivity.this,loginActivity.class);
       // intentRegister.putExtra("Name", editName.getText().toString().trim());
        startActivity(intentRegister);
    }

}
            */