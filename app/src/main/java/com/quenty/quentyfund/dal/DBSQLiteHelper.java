package com.quenty.quentyfund.dal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DavorLimachi on 7/21/15.
 */
public class DBSQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "QuentyFundDB";
    private static final int DATABASE_VERSION = 1;

    public DBSQLiteHelper(Context contexto,
                          SQLiteDatabase.CursorFactory factory) {
        super(contexto, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(DALUser.CREATE_TABLA);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {

        db.execSQL("DROP TABLE IF EXISTS "+ DALUser.TABLA);
        db.execSQL(DALUser.CREATE_TABLA);
    }

    private SQLiteDatabase db;

    /**
     * Open Database and start the transaction
     * @return SQLiteDatabase
     */
    public SQLiteDatabase beginTransaction(){
        db = this.getWritableDatabase();
        db.beginTransaction();
        return db;

    }

    /**
     * Set Transaction Successful
     */
    public void commit() {
        db.setTransactionSuccessful();
    }

    /**
     * End Transaction if commited all is saved but id not commited you lose your data
     */
    public void endTransaction() {
        db.endTransaction();
        db.close();
    }

}
