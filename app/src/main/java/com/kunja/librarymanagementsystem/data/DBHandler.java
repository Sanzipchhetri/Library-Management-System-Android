package com.kunja.librarymanagementsystem.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.kunja.librarymanagementsystem.model.Book;
import com.kunja.librarymanagementsystem.model.IssueBook;
import com.kunja.librarymanagementsystem.model.Student;

import java.util.ArrayList;
import java.util.List;


public class DBHandler extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "lms";
//    public static final String TABLE_Book = "book";
//    public static final String TABLE_Student = "student";
//    public static final String TABLE_Issue_Book = "issue_book";


    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createBook = "CREATE TABLE book(book_id INTEGER primary key AUTOINCREMENT, book_name TEXT NOT NULL, book_author TEXT NOT NULL, book_availability TEXT NOT NULL)";
        String createStudent = "CREATE TABLE student(stud_id INTEGER primary key AUTOINCREMENT, stud_name TEXT NOT NULL, stud_number TEXT NOT NULL, stud_email TEXT NOT NULL,stud_address TEXT NOT NULL, student_show TEXT)";
        String createIssueBook = "CREATE TABLE issue_book(issue_id INTEGER primary key AUTOINCREMENT,book_id INTEGER,stud_id INTEGER,issue_date TEXT NOT NULL,return_date TEXT, book_show TEXT , FOREIGN KEY(book_id) REFERENCES book(book_id), FOREIGN KEY(stud_id) REFERENCES student(stud_id) )";

        db.execSQL(createBook);
        Log.d("msg", "Successfully : " + createBook);
        db.execSQL(createStudent);
        Log.d("msg", "Query being run is : " + createStudent);
        db.execSQL(createIssueBook);
        Log.d("msg", "Query being run is : " + createIssueBook);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists book");
        db.execSQL("drop Table if exists student");
        db.execSQL("drop Table if exists issue_book");
        onCreate(db);
        Log.d("msg", "Database being upgraded");
    }

    public void addBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("book_name", book.getBookName());
        values.put("book_author", book.getBookAuthor());
        values.put("book_availability", book.getBookAvailability());
        db.insert("book", null, values);
        Log.d("msg", "Successfully Inserted Book");
        db.close();
    }
    public void updateBook(int id,String name,String author) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("book_name", name);
        values.put("book_author", author);
        db.update("book", values, "book_id = ?", new String[]{id+""});
        Log.d("msg", "Successfully Updated Book");
        db.close();
    }
    public void deleteBook(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("book",  "book_id = ?", new String[]{id+""});
        Log.d("msg", "Successfully Delete Book");
        db.close();
    }


    public void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("stud_name", student.getStudentName());
        values.put("stud_number", student.getStudentPhoneNumber());
        values.put("stud_email", student.getStudentEmail());
        values.put("stud_address", student.getStudentAddress());
        values.put("student_show", student.getStudentAvailability());
        db.insert("student", null, values);
        Log.d("msg", "Successfully added Student");
        db.close();
    }

    public void addIssueBook(IssueBook issue) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("book_id", issue.getBookId());
        values.put("stud_id", issue.getStudentId());
        values.put("issue_date", issue.getIssueDate());
        values.put("return_date", issue.getReturnDate());
        values.put("book_show", issue.getBookAvailability());
        db.insert("issue_book", null, values);
        Log.d("msg", "Successfully Issue Book");
        db.close();
    }

    public void returnIssueBook(int id,String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("return_date", date);
        db.update("issue_book", values, "book_id = ?", new String[]{id+""});
        db.close();
}

    public void updateBookAvailabilityToNo(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("book_availability", "No");
        Log.d("msg", "Successfully To No: "+id);
        db.update("book", values, "book_id = ?", new String[]{id+""});
        db.close();
    }

    public void updateBookAvailabilityToYes(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("book_availability", "Yes");
        Log.d("msg", "Successfully To Yes: "+id);
        db.update("book", values, "book_id = ?", new String[]{id+""});
        db.close();
    }

    public String getBookAvailability(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select book_availability from book where book_availability = 'Yes' and book_id = ?",new String[]{id+""});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String a = c.getString(c.getColumnIndex("book_availability"));
            buffer.append(a);
        }
        db.close();
        return buffer.toString();
    }

    public String getCheckIssueBook(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select book_show from issue_book where book_show = 'Yes' and book_id = ?",new String[]{id+""});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String a = c.getString(c.getColumnIndex("book_show"));
            buffer.append(a);
        }
        db.close();
        return buffer.toString();
    }

    public void updateBookShowToNo(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("book_show", "No");
        Log.d("msg", "Successfully To No: "+id);
        db.update("issue_book", values, "book_id = ?", new String[]{id+""});
        db.close();
    }

    public String getCheckStudent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select student_show from student where student_show = 'Yes' and stud_id = ?",new String[]{id+""});
        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String a = c.getString(c.getColumnIndex("student_show"));
            buffer.append(a);
        }
        db.close();
        return buffer.toString();
    }

    public String getStudentName(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("select student.stud_name from student INNER JOIN issue_book on issue_book.stud_id =student.stud_id INNER JOIN book on book.book_id= issue_book.book_id where issue_book.book_show='Yes' AND book.book_id= ?",new String[]{id+""});

        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String a = c.getString(c.getColumnIndex("stud_name"));
            buffer.append(a);
        }
        db.close();
        return buffer.toString();
    }

    public String getStudentBookList(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("select book.book_name from book INNER JOIN issue_book on issue_book.book_id = book.book_id INNER JOIN student on student.stud_id= issue_book.stud_id where issue_book.book_show = 'Yes' and student.stud_id=?",new String[]{id+""});

        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String a = c.getString(c.getColumnIndex("book_name"));
            buffer.append(a);
            buffer.append(", ");
        }
        db.close();
        return buffer.toString();
    }

    public String getIssuesBookDate(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("select issue_book.issue_date from issue_book INNER JOIN book on book.book_id = issue_book.book_id INNER JOIN student on student.stud_id = issue_book.stud_id where issue_book.book_show= 'Yes' AND book.book_id=?",new String[]{id+""});

        StringBuffer buffer = new StringBuffer();
        while (c.moveToNext()) {
            String a = c.getString(c.getColumnIndex("issue_date"));
            buffer.append(a);
        }
        db.close();
        return buffer.toString();
    }

    public List<Book> getAllBook(){
        List<Book> books = new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM book",null);
        if(c.moveToFirst()) {
            do {
                Book b = new Book();
                b.setBookId(c.getInt(0));
                b.setBookName(c.getString(1));
                b.setBookAuthor(c.getString(2));
                b.setBookAvailability(c.getString(3));
                books.add(b);
            } while (c.moveToNext());
        }

        db.close();
        return books;
    }
    public Book getBookDetail(int id){
        Book b=new Book();
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM book WHERE book_id="+id,null);
        if(c.moveToFirst()) {
            b.setBookId(c.getInt(0));
            b.setBookName(c.getString(1));
            b.setBookAuthor(c.getString(2));
            b.setBookAvailability(c.getString(3));
        }
        db.close();
        return  b;
    }

    public List<Student> getAllStudent(){
        List<Student> students = new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM student",null);
        if(c.moveToFirst()) {
            do {
                Student s = new Student();
                s.setStudentId(c.getInt(0));
                s.setStudentName(c.getString(1));
                s.setStudentPhoneNumber(c.getString(2));
                s.setStudentEmail(c.getString(3));
                s.setStudentAddress(c.getString(4));
                s.setStudentAvailability(c.getString(5));
                students.add(s);
            } while (c.moveToNext());
        }

        db.close();
        return students;
    }
    public Student getStudentDetail(int id){
        Student s=new Student();
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM student WHERE stud_id="+id,null);
        if(c.moveToFirst()) {
            s.setStudentId(c.getInt(0));
            s.setStudentName(c.getString(1));
            s.setStudentPhoneNumber(c.getString(2));
            s.setStudentEmail(c.getString(3));
            s.setStudentAddress(c.getString(4));
            s.setStudentAvailability(c.getString(5));

        }
        db.close();
        return  s;
    }


}
