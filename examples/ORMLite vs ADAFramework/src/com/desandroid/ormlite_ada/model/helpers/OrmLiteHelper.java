package com.desandroid.ormlite_ada.model.helpers;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.desandroid.ormlite_ada.model.entities.OrmLiteEntity;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class OrmLiteHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "OrmLiteDatabase.db";
    private static final int DATABASE_VERSION = 1;
    
    private static OrmLiteHelper instance;
    
    public static OrmLiteHelper getInstance(Context ctx) throws SQLException {
        if (instance == null) {
            instance = OpenHelperManager.getHelper(ctx, OrmLiteHelper.class);
            instance.ormEntityDao = instance.getDao(OrmLiteEntity.class);
        }
        return instance;
    }

    public OrmLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase pDatabase, ConnectionSource pConnectionSource) {
        try {
            TableUtils.createTable(pConnectionSource, OrmLiteEntity.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase pDatabase, ConnectionSource pConnectionSource, int arg2, int arg3) {
        try {

            TableUtils.dropTable(pConnectionSource, OrmLiteEntity.class, true);
            onCreate(pDatabase, pConnectionSource);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Dao<OrmLiteEntity, Long> ormEntityDao;

    public void add(OrmLiteEntity pEntity) throws SQLException {
        ormEntityDao.create(pEntity);
    }
}