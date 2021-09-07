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
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Container extends AppCompatActivity {
    private ShareActionProvider shareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);


    }
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you want to Logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
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


    public void onMoveStudent(View view) {
        Intent redirect =new Intent(Container.this,AddStudent.class);
        startActivity(redirect);
    }

    public void onMoveBook(View view) {
        Intent redirect =new Intent(Container.this,AddBook.class);
        startActivity(redirect);
    }

    public void onIssueBook(View view) {
        Intent redirect =new Intent(Container.this, AddIssueBook.class);
        startActivity(redirect);
    }

    public void onReturnBook(View view) {
        Intent redirect =new Intent(Container.this,ReturnBook.class);
        startActivity(redirect);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.view_student:
                Intent student = new Intent(this, ViewStudent.class);
                startActivity(student);
                return true;
            case R.id.view_book:
                Intent book = new Intent(this, ViewBook.class);
                startActivity(book);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        Toast.makeText(getApplicationContext(), "LMS Is Clear",Toast.LENGTH_SHORT).show();
        Log.i("msg", "onDestroy called");
    }


}