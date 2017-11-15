package com.example.pleum.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class MainActivity extends AppCompatActivity implements AwesomeDialogFragment.OnDialogListener{

    private static String TAG_DIALOG = "dialog";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager(); //open fragment
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (item.getItemId()) {
                case R.id.action_home:
                    transaction.replace(R.id.content,new HomeFragment()).commit();
                    return true;
                case R.id.action_catagories:
                    transaction.replace(R.id.content,new CataFragment()).commit();
                    return true;
                case R.id.action_account:
                    transaction.replace(R.id.content,new AccountFragment()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentManager fragmentManager = getSupportFragmentManager(); //on run start at home fragment
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content,new HomeFragment()).commit();


        AwesomeDialogFragment fragment = new AwesomeDialogFragment.Builder()
                .setMessage(R.string.dialogtext)
                .setPosition(R.string.ok)
                .setNegative(R.string.cancle)
                .build();
        fragment.show(getSupportFragmentManager(), TAG_DIALOG);

    }






    @Override
    public void onPositiveButtonClick() {
        Intent intent = new Intent(MainActivity.this,Login.class);
        startActivity(intent);
        //Toast.makeText(this, R.string.sample_dialog_confirm, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNegativeButtonClick() {
        //Toast.makeText(this, R.string.sample_dialog_cancel, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
