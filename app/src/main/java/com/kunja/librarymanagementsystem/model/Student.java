package com.kunja.librarymanagementsystem.model;

public class Student {
    private  int studentId;
    private  String studentName;
    private  String studentPhoneNumber;
    private  String studentAddress;
    private  String studentEmail;
    private  String studentAvailability;




    public Student(int studentId, String studentName, String studentPhoneNumber, String studentAddress, String studentEmail) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentPhoneNumber = studentPhoneNumber;
        this.studentAddress = studentAddress;
        this.studentEmail = studentEmail;
    }

//    public Student(String studentName, String studentPhoneNumber, String studentAddress, String studentEmail) {
//        this.studentName = studentName;
//        this.studentPhoneNumber = studentPhoneNumber;
//        this.studentAddress = studentAddress;
//        this.studentEmail = studentEmail;
//    }

    public Student() {

    }

    public Student(int studentId) {
        this.studentId = studentId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentPhoneNumber() {
        return studentPhoneNumber;
    }

    public void setStudentPhoneNumber(String studentPhoneNumber) {
        this.studentPhoneNumber = studentPhoneNumber;
    }

    public void setStudentAvailability(String studentAvailability) {
        this.studentAvailability = studentAvailability;
    }

    public String getStudentAvailability() {
        return studentAvailability;
    }
    public String getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    @Override
    public String toString() {
        return studentId +". "+studentName;
    }
}

