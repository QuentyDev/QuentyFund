package com.quenty.quentyfund.dal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.quenty.quentyfund.entity.User;

/**
 * Created by DavorLimachi on 7/21/15.
 */
public class DALUser {

    private SQLiteDatabase db;
    public static final String TABLA = "User";
    public static final String COLUMNAS = "id,firstName,lastName,email";
    public static final String CREATE_TABLA = "CREATE TABLE " + TABLA + " (id integer,firstName text,lastName text,email text)";

    public DALUser(SQLiteDatabase sqLiteDatabase) {
        db = sqLiteDatabase;
    }

    /**
     *
     * @param user object User with data
     * @return 1 if succesful and -1 if error
     */
    public long insert(User user) {

        db.delete(TABLA, null, null);
        ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("firstName", user.getFirstName());
        nuevoRegistro.put("lastName", user.getLastName());
        nuevoRegistro.put("email", user.getEmail());
        long res = db.insert(TABLA, null, nuevoRegistro);
        Log.i("insert:", res + "|" + user.toString());
        return res;
    }

    /**
     *
     * @param user object User
     * @return 1 if succesful and -1 if error
     */
    public long update(User user) {
        ContentValues nr = new ContentValues();
        ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("firstName", user.getFirstName());
        nuevoRegistro.put("lastName", user.getLastName());
        nuevoRegistro.put("email", user.getEmail());
        long res = db.update(TABLA, nr, null, null);
        Log.i("update:", res + "|" + user.toString());
        return res;
    }

    /**
     * Get User
     * @return the current user registrered, it will have some special information
     */
    public User get() {

        User n = new User();
        Cursor c = db.query(TABLA, null, null, null, null, null, null);

        if (c.moveToFirst()) {
            n.setFirstName(c.getString(c.getColumnIndex("firstName")));
            n.setLastName(c.getString(c.getColumnIndex("lastName")));
            n.setEmail(c.getString(c.getColumnIndex("email")));

        }
        return n;
    }


}
