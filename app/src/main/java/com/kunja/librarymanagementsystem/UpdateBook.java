package com.kunja.librarymanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kunja.librarymanagementsystem.data.DBHandler;
import com.kunja.librarymanagementsystem.model.Book;

public class UpdateBook extends AppCompatActivity {
    EditText name, author;
    public static final String EXTRA_BOOK_ID = "book_Id";
    int book_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);
        DBHandler db = new DBHandler(this);
        book_id = (Integer) getIntent().getExtras().get(EXTRA_BOOK_ID);
        Book book = db.getBookDetail(book_id);

        name=(EditText) findViewById(R.id.editTextBookName);
        name.setText(book.getBookName());

        author=(EditText) findViewById(R.id.editTextBookAuthor);
        author.setText(book.getBookAuthor());
    }

    public void onUpdateBook(View view) {
        book_id = (Integer) getIntent().getExtras().get(EXTRA_BOOK_ID);
        DBHandler db = new DBHandler(this);
        String bookName = name.getText().toString();
        String bookAuthor = author.getText().toString();
        if (bookName.isEmpty()){
            Toast.makeText(this, "Book name is empty", Toast.LENGTH_SHORT).show();
        }
        else if (bookAuthor.isEmpty()){
            Toast.makeText(this, "Book author is empty", Toast.LENGTH_SHORT).show();
        }
        else{
            db.updateBook(book_id,bookName,bookAuthor);
            Toast.makeText(this, "Book Updated", Toast.LENGTH_SHORT).show();
            finishActivity();
        }
    }

    private void finishActivity() {
        Intent redirect = new Intent(UpdateBook.this, Container.class);
        startActivity(redirect);
    }
}