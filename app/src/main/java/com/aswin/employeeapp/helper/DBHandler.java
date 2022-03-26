package com.aswin.employeeapp.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.aswin.employeeapp.model.EmployeeModel;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "EmployeeDB";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "employees";

    private static final String ID = "id";
    private static final String name = "name";
    private static final String username = "username";
    private static final String email = "email";
    private static final String addressStreet = "street";
    private static final String addressSute = "suite";
    private static final String addressCity = "city";
    private static final String addressZipcode = "zipcode";
    private static final String phone = "phone";
    private static final String website = "website";
    private static final String profileImage = "profile_image";
    private static final String companyName = "companyname";
    private static final String companyCatchPhrase = "catchPhrase";
    private static final String companybs = "bs";


    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID + " INTEGER PRIMARY KEY, " //+ ID + " INTEGER PRIMARY KEY AUTOINCREMENT,
                + name + " TEXT,"
                + username + " TEXT,"
                + email + " TEXT,"
                + addressStreet + " TEXT,"
                + addressSute + " TEXT,"
                + addressCity + " TEXT,"
                + addressZipcode + " TEXT,"
                + phone + " TEXT,"
                + website + " TEXT,"
                + profileImage + " TEXT,"
                + companyName + " TEXT,"
                + companyCatchPhrase + " TEXT,"
                + companybs + " TEXT)";

        db.execSQL(query);
    }

    public void addEmployee(EmployeeModel employee) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(name, employee.getName());
        values.put(username, employee.getUsername());
        values.put(email, employee.getEmail());
        values.put(addressStreet, employee.getAddressStreet());
        values.put(addressSute, employee.getAddressSute());
        values.put(addressCity, employee.getAddressCity());
        values.put(addressZipcode, employee.getAddressZipcode());
        values.put(phone, employee.getPhone());
        values.put(website, employee.getWebsite());
        values.put(profileImage, employee.getProfileImage());
        values.put(companyName, employee.getCompanyName());
        values.put(companyCatchPhrase, employee.getCompanyCatchPhrase());
        values.put(companybs, employee.getCompanybs());

        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public int getEmployeeCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
//        cursor.close();

        return cursor.getCount();

    }

    public ArrayList<EmployeeModel> getAllEmployees() {
        ArrayList<EmployeeModel> employeeList = new ArrayList<EmployeeModel>();;

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(1);
                String username = cursor.getString(2);
                String email = cursor.getString(3);
                String addressStreet = cursor.getString(4);
                String addressSute = cursor.getString(5);
                String addressCity = cursor.getString(6);
                String addressZipcode = cursor.getString(7);
                String phone = cursor.getString(8);
                String website = cursor.getString(9);
                String profileImage = cursor.getString(10);
                String companyName = cursor.getString(11);
                String companyCatchPhrase = cursor.getString(12);
                String companybs = cursor.getString(13);

                EmployeeModel employee = new EmployeeModel(name,username,email,addressStreet,addressSute,addressCity,addressZipcode,
                        phone,website,profileImage,companyName,companyCatchPhrase,companybs);
                // Adding contact to list
                employeeList.add(employee);
            } while (cursor.moveToNext());
        }

        // return contact list
        return employeeList;
    }
}
