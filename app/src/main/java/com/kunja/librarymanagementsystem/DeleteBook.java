package com.kunja.librarymanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.kunja.librarymanagementsystem.data.DBHandler;
import com.kunja.librarymanagementsystem.model.Book;

public class DeleteBook extends AppCompatActivity {
    public static final String EXTRA_DELETE_ID = "book_Id";
    int book_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_book);

    }

    public void onYes(View view) {
        book_id = (Integer) getIntent().getExtras().get(EXTRA_DELETE_ID);
        DBHandler db = new DBHandler(this);
        System.out.println("Your delete book ID is "+book_id);
        db.deleteBook(book_id);
        Toast.makeText(this, "Book Has Deleted", Toast.LENGTH_SHORT).show();
        finishActivity();

    }

    public void onNo(View view) {
        finish();
    }
    private void finishActivity() {
        Intent redirect = new Intent(DeleteBook.this, Container.class);
        startActivity(redirect);
    }
}