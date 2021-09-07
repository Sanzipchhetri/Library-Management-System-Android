package com.kunja.librarymanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.kunja.librarymanagementsystem.data.DBHandler;
import com.kunja.librarymanagementsystem.model.Book;

import java.util.List;

public class ViewBook extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);

        DBHandler db = new DBHandler(ViewBook.this);
        final List<Book> books = db.getAllBook();

        ArrayAdapter<Book> listAdapter = new ArrayAdapter<>(ViewBook.this, android.R.layout.simple_list_item_1, books);
        ListView listBooks = findViewById(R.id.list_books);
        listBooks.setAdapter(listAdapter);

        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> listBooks,
                                            View itemView,
                                            int position,
                                            long id) {
                        Intent intent = new Intent(ViewBook.this,
                                DetailBook.class);
                        intent.putExtra(DetailBook.EXTRA_BOOKID, books.get((int)id).getBookId());
                        startActivity(intent);
                    }
                };
        listBooks.setOnItemClickListener(itemClickListener);

    }

}