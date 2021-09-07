package com.kunja.librarymanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kunja.librarymanagementsystem.data.DBHandler;
import com.kunja.librarymanagementsystem.model.Book;

public class DetailBook extends AppCompatActivity {
    public static final String EXTRA_BOOKID = "bookId";
    int bookId;
    TextView book_name,book_author,book_availability,book_taken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_book);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bookId = (Integer)getIntent().getExtras().get(EXTRA_BOOKID);
        DBHandler db = new DBHandler(DetailBook.this);
        String b = db.getStudentName(bookId);
        String c = db.getIssuesBookDate(bookId);
        Book book = db.getBookDetail(bookId);

        book_name = (TextView)findViewById(R.id.book_name);
        book_name.setText(book.getBookName());

        book_author = (TextView)findViewById(R.id.book_author);
        book_author.setText(book.getBookAuthor());

        book_availability = (TextView)findViewById(R.id.book_availability);
        book_availability.setText(book.getBookAvailability());

        book_taken = (TextView)findViewById(R.id.book_taken);
        if(book.getBookAvailability().equals("No")) {
            book_taken.setText(b +" on "+c);
        }
        else{
            book_taken.setText("None");
        }
    }

    public void onUpdate(View view) {
        bookId = (Integer)getIntent().getExtras().get(EXTRA_BOOKID);
        DBHandler db = new DBHandler(DetailBook.this);
        Book book = db.getBookDetail(bookId);
        if(book.getBookAvailability().equals("Yes")) {
            Intent redirect = new Intent(this, UpdateBook.class);
            redirect.putExtra(UpdateBook.EXTRA_BOOK_ID, bookId);
            System.out.println("Your book ID is "+bookId);
            startActivity(redirect);
        }
        else{
            Toast.makeText(getApplicationContext(), "Right now book aren't be updated",Toast.LENGTH_SHORT).show();
        }
    }

    public void onDelete(View view) {
        bookId = (Integer)getIntent().getExtras().get(EXTRA_BOOKID);
        DBHandler db = new DBHandler(DetailBook.this);
        Book book = db.getBookDetail(bookId);
        if(book.getBookAvailability().equals("Yes")) {
            Intent redirect = new Intent(this, DeleteBook.class);
            redirect.putExtra(DeleteBook.EXTRA_DELETE_ID, bookId);
            System.out.println("Your Delete book ID is "+bookId);
            startActivity(redirect);
        }
        else{
            Toast.makeText(getApplicationContext(), "Right now book aren't be deleted",Toast.LENGTH_SHORT).show();
        }
    }
}