package com.kunja.librarymanagementsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.core.view.MenuItemCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kunja.librarymanagementsystem.data.DBHandler;
import com.kunja.librarymanagementsystem.model.Book;

public class MainActivity extends AppCompatActivity {
    private ShareActionProvider shareActionProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHandler db = new DBHandler(MainActivity.this);
        Log.d("msg", "Query being ");
    }

    public void onAdminLogin(View view) {
        Intent redirect =new Intent(MainActivity.this,Container.class);
        EditText ed1 = findViewById(R.id.editTextTextUsername);
        EditText ed2 = findViewById(R.id.editTextTextPassword);
        String msg=ed1.getText().toString();
        if(ed1.getText().toString().equals("kunja") && ed2.getText().toString().equals("kunja") || ed1.getText().toString().equals("lms") && ed2.getText().toString().equals("lms")) {
                    redirect.putExtra("msg",msg);
                    startActivity(redirect);
            Toast.makeText(getApplicationContext(), "Logging...",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Username and Password Incorrect!",Toast.LENGTH_SHORT).show();
        }

    }

    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();

            }
        });
        builder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        setShareActionIntent("Download this app");
        return super.onCreateOptionsMenu(menu);
    }
    private void setShareActionIntent(String text) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        shareActionProvider.setShareIntent(intent);
    }
    @Override
    protected void onStop() {
        super.onStop();

        Log.i("msg", "onStop called");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("msg", "onStart called");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "LMS Is Clear",Toast.LENGTH_SHORT).show();

        Log.i("msg", "onDestroy called");
    }

    @Override
    protected void onPause() {

        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("msg", "onResume called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("msg", "onRestart called");
    }

}