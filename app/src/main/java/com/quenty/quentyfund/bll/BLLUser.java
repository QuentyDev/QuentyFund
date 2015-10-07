package com.quenty.quentyfund.bll;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Looper;

import com.quenty.quentyfund.dal.DALUser;
import com.quenty.quentyfund.dal.DBSQLiteHelper;
import com.quenty.quentyfund.entity.User;


/**
 * Created by DavorLimachi on 7/21/15.
 */
public class BLLUser {

    private DBSQLiteHelper dbsqLiteHelper;

    public BLLUser(Context context) {
        dbsqLiteHelper = new DBSQLiteHelper(context, null);
    }

    /**
     * Insert into database some attribs
     * @param firstName String
     * @param lastName String
     * @param email String
     * @return 1 if all is ok and -1 or negative if error
     */
    public long insert(String firstName, String lastName, String email) {
        long val = -1;
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        SQLiteDatabase sqLiteDatabase = dbsqLiteHelper.beginTransaction();
        DALUser dalUser = new DALUser(sqLiteDatabase);
        val = dalUser.insert(user);
        if (val > 0) {
            dbsqLiteHelper.commit();
        }
        dbsqLiteHelper.endTransaction();
        return val;

    }

    /**
     *
     * @param firstName String
     * @param lastName String
     * @param email String
     * @return 1 if all is ok and -1 or negative if error
     */
    public long update(String firstName, String lastName, String email) {

        long val = -1;
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        SQLiteDatabase sqLiteDatabase = dbsqLiteHelper.beginTransaction();
        DALUser dalUser = new DALUser(sqLiteDatabase);
        val = dalUser.update(user);
        if (val > 0) {
            dbsqLiteHelper.commit();
        }
        dbsqLiteHelper.endTransaction();
        return val;
    }

    /**
     * @return User
     */
    public User get() {

        SQLiteDatabase sqLiteDatabase = dbsqLiteHelper.beginTransaction();
        DALUser dalUser = new DALUser(sqLiteDatabase);
        User user;
        user=dalUser.get();
        dbsqLiteHelper.endTransaction();
        return user;
    }



}
