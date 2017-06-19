package com.example.rxbro.test2.SingleUser;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.net.URL;
import java.util.HashMap;
import java.util.IllegalFormatCodePointException;

/**
 * Created by rxbro on 6/19/2017.
 */

public class ContactProvider extends ContentProvider {
    private static final String PROVIDER_NAME = "com.example.rxbro.test2";
    private static final String URL = "content://" + PROVIDER_NAME + "/contacts";
    private static final Uri CONTENT_URI = Uri.parse(URL);
    private static final String _ID = "_id";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String EMAIL = "email";
    private SQLiteDatabase db;
    private static final String DATABASE_NAME = "Contacts";
    private static final String TABLE_NAME = "Contacts";
    private static final int DATABASE_VERSION = 1;
    private static final int CONTACTS = 1;
    private static final int CONTACTS_ID = 2;
    private static final HashMap<String, String> CONTACTS_PROJECTION_MAP;
    private static final UriMatcher uriMatcher;
    private static final String CREATE_DB_TABLE = "CREATE TABLE " + TABLE_NAME + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " name TEXT NOT NULL, " + "address TEXT NOT NULL, " + "email TEXT NOT NULL):";

    // Helper class that manages the provider's data repository
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DB_TABLE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }

    }

    static {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "contacts", CONTACTS);
        uriMatcher.addURI(PROVIDER_NAME, "contacts/#", CONTACTS_ID);
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        return (db == null) ? false : true;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowID = db.insert(TABLE_NAME, "", values);
        if (rowID > 0) {
            uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
            getContext().getContentResolver().notifyChange(uri, null);
            return uri;

        }
        throw new SQLException("Failed to add a record to " + uri);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(TABLE_NAME);
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        switch (uriMatcher.match(uri)) {
            case CONTACTS:
                qb.setProjectionMap(CONTACTS_PROJECTION_MAP);
                break;
            case CONTACTS_ID:
                qb.appendWhere(_ID + "=" + uri.getPathSegments().get(1));
                break;
            default:
                break;
        }
        if (sortOrder == null || sortOrder == "") {
            sortOrder = NAME;
        }
        Cursor cursor = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case CONTACTS:
                count = db.delete(TABLE_NAME, selection, selectionArgs);
                break;
            case CONTACTS_ID:
                String id = uri.getPathSegments().get(1);
                count = db.delete(TABLE_NAME, _ID + "=" + id + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;

    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case CONTACTS:
                count = db.update(TABLE_NAME, contentValues, selection, selectionArgs);
                break;
            case CONTACTS_ID:
                count = db.update(TABLE_NAME, contentValues, _ID + "=" + uri.getPathSegments().get(1) + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case CONTACTS:
                return "vnd.android.cursor.dir/vnd.example.contacts";
            case CONTACTS_ID:
                return "vnd.android.cursor.item/vnd.example.contacts";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }
}