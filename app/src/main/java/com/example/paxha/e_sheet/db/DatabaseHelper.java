package com.example.paxha.e_sheet.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.paxha.e_sheet.calculation.CalculationModel;
import com.example.paxha.e_sheet.project.ProjectModel;
import com.example.paxha.e_sheet.sheet.SheetModel;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "esheet";

    // Table Names
    private static final String TABLE_PROJECT = "projects";
    private static final String TABLE_SHEET = "sheets";
    private static final String TABLE_CALCULATION = "calculations";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";
    private static final String KEY_UPDATED_AT = "updated_at";

    // PROJECTS Table - column names
    private static final String KEY_PROJECT_NAME = "name";

    // SHEETS Table - column names
    private static final String KEY_PROJECT_ID = "project_id";
    private static final String KEY_SHEET_NAME = "name";

    // NOTE_TAGS Table - column names
    private static final String KEY_SHEET_ID = "sheet_id";
    private static final String KEY_TYPE = "type";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_WIDTH_FEET = "width_feet";
    private static final String KEY_WIDTH_INCHES = "width_inches";
    private static final String KEY_HEIGHT_FEET = "height_feet";
    private static final String KEY_HEIGHT_INCHES = "height_inches";
    private static final String KEY_QUANTITY = "quantity";
    private static final String KEY_TOTAL_FEET = "total_feet";
    private static final String KEY_TOTAL_INCHES = "total_inches";

    // Table Create Statements
    // PROJECT table create statement
    private static final String CREATE_TABLE_PROJECT = "CREATE TABLE "
            + TABLE_PROJECT + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_PROJECT_NAME + " TEXT,"
            + KEY_UPDATED_AT + " DATETIME,"
            + KEY_CREATED_AT + " DATETIME"
            + ")";

    // SHEET table create statement mailbox_id INTEGER REFERENCES mailboxes ON DELETE CASCADE
    private static final String CREATE_TABLE_SHEET = "CREATE TABLE "
            + TABLE_SHEET + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_PROJECT_ID + " INTEGER REFERENCES " + TABLE_PROJECT + " ON DELETE CASCADE,"
            + KEY_SHEET_NAME + " TEXT,"
            + KEY_CREATED_AT + " DATETIME,"
            + KEY_UPDATED_AT + " DATETIME"
            + ")";

    // CALCULATION table create statement
    private static final String CREATE_TABLE_CALCULATION = "CREATE TABLE "
            + TABLE_CALCULATION + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_SHEET_ID + " INTEGER REFERENCES " + TABLE_SHEET + " ON DELETE CASCADE,"
            + KEY_TYPE + " TEXT DEFAULT('Add'),"
            + KEY_DESCRIPTION + " TEXT,"
            + KEY_HEIGHT_FEET + " INTEGER DEFAULT(0),"
            + KEY_HEIGHT_INCHES + " INTEGER DEFAULT(0),"
            + KEY_WIDTH_FEET + " INTEGER DEFAULT(0),"
            + KEY_WIDTH_INCHES + " INTEGER DEFAULT(0),"
            + KEY_QUANTITY + " INTEGER DEFAULT(1),"
            + KEY_TOTAL_FEET + " INTEGER DEFAULT(0),"
            + KEY_TOTAL_INCHES + " INTEGER DEFAULT(0),"
            + KEY_CREATED_AT + " DATETIME,"
            + KEY_UPDATED_AT + " DATETIME"
            + ")";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_PROJECT);
        sqLiteDatabase.execSQL(CREATE_TABLE_SHEET);
        sqLiteDatabase.execSQL(CREATE_TABLE_CALCULATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJECT);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_SHEET);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CALCULATION);

        onCreate(sqLiteDatabase);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys=ON");
    }

    public int createProject(ProjectModel projectModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PROJECT_NAME, projectModel.getName());
        values.put(KEY_CREATED_AT, "CURRENT_TIMESTAMP");
        values.put(KEY_UPDATED_AT, "CURRENT_TIMESTAMP");

        return (int) sqLiteDatabase.insert(TABLE_PROJECT, null, values);
    }

    public ProjectModel getProject(int id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_PROJECT + " WHERE " + KEY_ID + " = " + id;
        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        if (cursor != null)
            cursor.moveToFirst();

        ProjectModel projectModel = new ProjectModel();
        assert cursor != null;
        projectModel.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
        projectModel.setName(cursor.getString(cursor.getColumnIndex(KEY_PROJECT_NAME)));
        projectModel.setCreatedAt(cursor.getString(cursor.getColumnIndex(KEY_CREATED_AT)));
        projectModel.setUpdatedAt(cursor.getString(cursor.getColumnIndex(KEY_UPDATED_AT)));

        return projectModel;
    }

    public ArrayList<ProjectModel> getAllProject() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<ProjectModel> projectModels = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_PROJECT;

        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        if (cursor.moveToFirst())
            do {
                ProjectModel projectModel = new ProjectModel();
                projectModel.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                projectModel.setName(cursor.getString(cursor.getColumnIndex(KEY_PROJECT_NAME)));
                projectModel.setCreatedAt(cursor.getString(cursor.getColumnIndex(KEY_CREATED_AT)));
                projectModel.setUpdatedAt(cursor.getString(cursor.getColumnIndex(KEY_UPDATED_AT)));

                projectModels.add(projectModel);
            } while (cursor.moveToNext());

        return projectModels;
    }

    public int updateProject(ProjectModel projectModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PROJECT_NAME, projectModel.getName());
        values.put(KEY_UPDATED_AT, "CURRENT_TIMESTAMP");
        return sqLiteDatabase.update(TABLE_PROJECT, values, KEY_ID + " = ?", new String[]{String.valueOf(projectModel.getId())});
    }

    public void deleteProject(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_PROJECT, KEY_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public int createSheet(SheetModel sheetModel, int project_id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PROJECT_ID, project_id);
        values.put(KEY_SHEET_NAME, sheetModel.getName());
        values.put(KEY_CREATED_AT, "CURRENT_TIMESTAMP");
        values.put(KEY_UPDATED_AT, "CURRENT_TIMESTAMP");

        return (int) sqLiteDatabase.insert(TABLE_SHEET, null, values);
    }

    public SheetModel getSheet(int id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_SHEET + " WHERE " + KEY_ID + " = " + id;
        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        if (cursor != null)
            cursor.moveToFirst();

        SheetModel sheetModel = new SheetModel();
        assert cursor != null;
        sheetModel.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
        sheetModel.setName(cursor.getString(cursor.getColumnIndex(KEY_SHEET_NAME)));
        sheetModel.setCreatedAt(cursor.getString(cursor.getColumnIndex(KEY_CREATED_AT)));
        sheetModel.setUpdatedAt(cursor.getString(cursor.getColumnIndex(KEY_UPDATED_AT)));

        return sheetModel;
    }

    public ArrayList<SheetModel> getAllSheets(int projectId) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<SheetModel> sheetModels = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_SHEET + " WHERE " + KEY_PROJECT_ID + " = " + projectId;

        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        if (cursor.moveToFirst())
            do {
                SheetModel sheetModel = new SheetModel();
                sheetModel.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                sheetModel.setProjectId(cursor.getInt(cursor.getColumnIndex(KEY_PROJECT_ID)));
                sheetModel.setName(cursor.getString(cursor.getColumnIndex(KEY_SHEET_NAME)));
                sheetModel.setCreatedAt(cursor.getString(cursor.getColumnIndex(KEY_CREATED_AT)));
                sheetModel.setUpdatedAt(cursor.getString(cursor.getColumnIndex(KEY_UPDATED_AT)));

                sheetModels.add(sheetModel);
            } while (cursor.moveToNext());

        return sheetModels;
    }

    public int updateSheet(SheetModel sheetModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SHEET_NAME, sheetModel.getName());
        values.put(KEY_UPDATED_AT, "CURRENT_TIMESTAMP");

        return sqLiteDatabase.update(TABLE_SHEET, values, KEY_ID + " = ?", new String[]{String.valueOf(sheetModel.getId())});
    }

    public void deleteSheet(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_SHEET, KEY_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public int createCalculation(CalculationModel calculationModel, int sheetId) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SHEET_ID, sheetId);
        values.put(KEY_TYPE, calculationModel.getType());
        values.put(KEY_DESCRIPTION, calculationModel.getDescription());
        values.put(KEY_WIDTH_FEET, calculationModel.getWidthFeet());
        values.put(KEY_WIDTH_INCHES, calculationModel.getWidthInches());
        values.put(KEY_HEIGHT_FEET, calculationModel.getHeightFeet());
        values.put(KEY_HEIGHT_INCHES, calculationModel.getWidthInches());
        values.put(KEY_QUANTITY, calculationModel.getQuantity());
        values.put(KEY_TOTAL_FEET, calculationModel.getTotalFeet());
        values.put(KEY_TOTAL_INCHES, calculationModel.getTotalInches());
        values.put(KEY_CREATED_AT, "CURRENT_TIMESTAMP");
        values.put(KEY_UPDATED_AT, "CURRENT_TIMESTAMP");

        return (int) sqLiteDatabase.insert(TABLE_CALCULATION, null, values);
    }

    public CalculationModel getCalculation(int id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_CALCULATION + " WHERE " + KEY_ID + " = " + id;
        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        if (cursor != null)
            cursor.moveToFirst();

        CalculationModel calculationModel = new CalculationModel();
        assert cursor != null;
        calculationModel.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
        calculationModel.setSheet_id(cursor.getInt(cursor.getColumnIndex(KEY_SHEET_ID)));
        calculationModel.setType(cursor.getString(cursor.getColumnIndex(KEY_TYPE)));
        calculationModel.setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));
        calculationModel.setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));
        calculationModel.setWidthFeet(cursor.getInt(cursor.getColumnIndex(KEY_WIDTH_FEET)));
        calculationModel.setWidthInches(cursor.getInt(cursor.getColumnIndex(KEY_WIDTH_INCHES)));
        calculationModel.setHeightFeet(cursor.getInt(cursor.getColumnIndex(KEY_HEIGHT_FEET)));
        calculationModel.setHeightInches(cursor.getInt(cursor.getColumnIndex(KEY_HEIGHT_INCHES)));
        calculationModel.setQuantity(cursor.getInt(cursor.getColumnIndex(KEY_QUANTITY)));
        calculationModel.setTotalFeet(cursor.getInt(cursor.getColumnIndex(KEY_TOTAL_FEET)));
        calculationModel.setTotalInches(cursor.getInt(cursor.getColumnIndex(KEY_TOTAL_INCHES)));
        calculationModel.setCreatedAt(cursor.getString(cursor.getColumnIndex(KEY_CREATED_AT)));
        calculationModel.setUpdatedAt(cursor.getString(cursor.getColumnIndex(KEY_UPDATED_AT)));

        return calculationModel;
    }

    public ArrayList<CalculationModel> getAllCalculations(int sheetId) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<CalculationModel> calculationModels = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_CALCULATION + " WHERE " + KEY_SHEET_ID + " = " + sheetId;

        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        if (cursor.moveToFirst())
            do {
                CalculationModel calculationModel = new CalculationModel();
                calculationModel.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                calculationModel.setSheet_id(cursor.getInt(cursor.getColumnIndex(KEY_SHEET_ID)));
                calculationModel.setType(cursor.getString(cursor.getColumnIndex(KEY_TYPE)));
                calculationModel.setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));
                calculationModel.setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));
                calculationModel.setWidthFeet(cursor.getInt(cursor.getColumnIndex(KEY_WIDTH_FEET)));
                calculationModel.setWidthInches(cursor.getInt(cursor.getColumnIndex(KEY_WIDTH_INCHES)));
                calculationModel.setHeightFeet(cursor.getInt(cursor.getColumnIndex(KEY_HEIGHT_FEET)));
                calculationModel.setHeightInches(cursor.getInt(cursor.getColumnIndex(KEY_HEIGHT_INCHES)));
                calculationModel.setQuantity(cursor.getInt(cursor.getColumnIndex(KEY_QUANTITY)));
                calculationModel.setTotalFeet(cursor.getInt(cursor.getColumnIndex(KEY_TOTAL_FEET)));
                calculationModel.setTotalInches(cursor.getInt(cursor.getColumnIndex(KEY_TOTAL_INCHES)));
                calculationModel.setCreatedAt(cursor.getString(cursor.getColumnIndex(KEY_CREATED_AT)));
                calculationModel.setUpdatedAt(cursor.getString(cursor.getColumnIndex(KEY_UPDATED_AT)));

                calculationModels.add(calculationModel);
            } while (cursor.moveToNext());

        return calculationModels;
    }

    public int updateCalculation(CalculationModel calculationModel) {
        Log.e("my log", "updating calculation on id = " + calculationModel.getId());
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TYPE, calculationModel.getType());
        values.put(KEY_DESCRIPTION, calculationModel.getDescription());
        values.put(KEY_WIDTH_FEET, calculationModel.getWidthFeet());
        values.put(KEY_WIDTH_INCHES, calculationModel.getWidthInches());
        values.put(KEY_HEIGHT_FEET, calculationModel.getHeightFeet());
        values.put(KEY_HEIGHT_INCHES, calculationModel.getWidthInches());
        values.put(KEY_QUANTITY, calculationModel.getQuantity());
        values.put(KEY_TOTAL_FEET, calculationModel.getTotalFeet());
        values.put(KEY_TOTAL_INCHES, calculationModel.getTotalInches());
        values.put(KEY_UPDATED_AT, "CURRENT_TIMESTAMP");

        return sqLiteDatabase.update(TABLE_CALCULATION, values, KEY_ID + " = ?", new String[]{String.valueOf(calculationModel.getId())});
    }

    public void deleteCalculation(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_CALCULATION, KEY_ID + " = ?", new String[]{String.valueOf(id)});
    }

}
