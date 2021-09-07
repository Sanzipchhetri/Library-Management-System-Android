package com.kunja.librarymanagementsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kunja.librarymanagementsystem.data.DBHandler;
import com.kunja.librarymanagementsystem.model.Book;

public class AddBook extends AppCompatActivity {
    EditText name, author;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        name=findViewById(R.id.editTextBookName);
        author=findViewById(R.id.editTextBookAuthor);
    }

    public void onAddBook(View view) {
        DBHandler db = new DBHandler(AddBook.this);
        Book b = new Book();
        String bookName = name.getText().toString();
        String bookAuthor = author.getText().toString();
        String bookAvailability = "Yes";
        if (bookName.isEmpty() && bookAuthor.isEmpty()){
            Toast.makeText(AddBook.this, "Book name and author are empty", Toast.LENGTH_SHORT).show();
        }
        else if (bookName.isEmpty()){
            Toast.makeText(AddBook.this, "Book name is empty", Toast.LENGTH_SHORT).show();
        }
        else if (bookAuthor.isEmpty()){
            Toast.makeText(AddBook.this, "Book author is empty", Toast.LENGTH_SHORT).show();
        }
        else{
            b.setBookName(bookName);
            b.setBookAuthor(bookAuthor);
            b.setBookAvailability(bookAvailability);
            db.addBook(b);
            Toast.makeText(AddBook.this, "New Book Added", Toast.LENGTH_SHORT).show();
            Log.d("msg", "Book Name:"+b.getBookName());
            Log.d("msg", "Book Author:"+b.getBookAuthor());
            Log.d("msg", "Book Availability:"+b.getBookAvailability());
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setMessage("Do you want to add Book again?");
            builder.setPositiveButton("Back", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.setNegativeButton("Add",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    name.setText("");
                    author.setText("");
                    dialog.cancel();

                }
            });
            AlertDialog alert=builder.create();
            alert.show();
        }

    }


}
