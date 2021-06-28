package com.demo.test.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.demo.test.manager.AppConfig;
import com.demo.test.pojo.Unit;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

/**
 * Created by Administrator on 15-10-27.
 */
public class UnitDao extends AbstractDao<Unit, String> {
    //加载实际的地区表  测试加载UnitTest 正式的加载Unit
    public static final String TABLENAME = AppConfig.DEBUG?"UnitTest":"Unit";;

    public UnitDao(DaoConfig config, AbstractDaoSession daoSession) {
        super(config, daoSession);
    }

    public static class Properties {
        public final static Property UnitCode = new Property(0, String.class, "unitCode", false, "unitCode");
        public final static Property UnitName = new Property(1, String.class, "unitName", false, "unitName");
        public final static Property ParentCode = new Property(2, String.class, "parentCode", false, "parentCode");
        public final static Property Remark = new Property(3, String.class, "remark", false, "remark");
    };

    @Override
    protected Unit readEntity(Cursor cursor, int offset) {
        return new Unit(cursor.isNull(offset)?null:cursor.getString(offset),
                cursor.getString(offset+1),
                cursor.getString(offset+2),
                cursor.getString(offset+3)
        );
    }
    @Override
    protected String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset) ? null : cursor.getString(offset);
    }
    @Override
    protected void readEntity(Cursor cursor, Unit entity, int offset) {
        entity.setUnitCode(cursor.isNull(offset) ? null : cursor.getString(offset));
        entity.setUnitName(cursor.getString(offset + 1));
        entity.setParentCode(cursor.getString(offset + 2));
        entity.setRemark(cursor.getString(offset + 3));
    }
    @Override
    protected void bindValues(SQLiteStatement stmt, Unit entity) {
        stmt.clearBindings();
        String unitCode = entity.getUnitCode();
        if (unitCode != null) {
            stmt.bindString(1, unitCode);
        }
        stmt.bindString(2, entity.getUnitName());
        stmt.bindString(3, entity.getParentCode());
        stmt.bindString(4, entity.getRemark());
    }
    @Override
    protected String updateKeyAfterInsert(Unit entity, long rowId) {
        return entity.getUnitCode();
    }
    @Override
    protected String getKey(Unit entity) {
        return entity==null?null:entity.getUnitCode();
    }
    @Override
    protected boolean isEntityUpdateable() {
        return false;
    }
}
