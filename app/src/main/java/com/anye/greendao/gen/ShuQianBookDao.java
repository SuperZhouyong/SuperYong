package com.anye.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.sxzx.GreenDao.ShuQianBook;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SHU_QIAN_BOOK".
*/
public class ShuQianBookDao extends AbstractDao<ShuQianBook, Long> {

    public static final String TABLENAME = "SHU_QIAN_BOOK";

    /**
     * Properties of entity ShuQianBook.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property BookId = new Property(1, String.class, "BookId", false, "BOOK_ID");
        public final static Property BookName = new Property(2, String.class, "bookName", false, "BOOK_NAME");
        public final static Property PraNum = new Property(3, String.class, "praNum", false, "PRA_NUM");
        public final static Property Content = new Property(4, String.class, "content", false, "CONTENT");
        public final static Property CharIndex = new Property(5, String.class, "charIndex", false, "CHAR_INDEX");
        public final static Property ElementIndex = new Property(6, String.class, "elementIndex", false, "ELEMENT_INDEX");
    };


    public ShuQianBookDao(DaoConfig config) {
        super(config);
    }
    
    public ShuQianBookDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SHU_QIAN_BOOK\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"BOOK_ID\" TEXT NOT NULL ," + // 1: BookId
                "\"BOOK_NAME\" TEXT," + // 2: bookName
                "\"PRA_NUM\" TEXT," + // 3: praNum
                "\"CONTENT\" TEXT," + // 4: content
                "\"CHAR_INDEX\" TEXT," + // 5: charIndex
                "\"ELEMENT_INDEX\" TEXT);"); // 6: elementIndex
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SHU_QIAN_BOOK\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ShuQianBook entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getBookId());
 
        String bookName = entity.getBookName();
        if (bookName != null) {
            stmt.bindString(3, bookName);
        }
 
        String praNum = entity.getPraNum();
        if (praNum != null) {
            stmt.bindString(4, praNum);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(5, content);
        }
 
        String charIndex = entity.getCharIndex();
        if (charIndex != null) {
            stmt.bindString(6, charIndex);
        }
 
        String elementIndex = entity.getElementIndex();
        if (elementIndex != null) {
            stmt.bindString(7, elementIndex);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ShuQianBook entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getBookId());
 
        String bookName = entity.getBookName();
        if (bookName != null) {
            stmt.bindString(3, bookName);
        }
 
        String praNum = entity.getPraNum();
        if (praNum != null) {
            stmt.bindString(4, praNum);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(5, content);
        }
 
        String charIndex = entity.getCharIndex();
        if (charIndex != null) {
            stmt.bindString(6, charIndex);
        }
 
        String elementIndex = entity.getElementIndex();
        if (elementIndex != null) {
            stmt.bindString(7, elementIndex);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ShuQianBook readEntity(Cursor cursor, int offset) {
        ShuQianBook entity = new ShuQianBook( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // BookId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // bookName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // praNum
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // content
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // charIndex
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6) // elementIndex
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ShuQianBook entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setBookId(cursor.getString(offset + 1));
        entity.setBookName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPraNum(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setContent(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setCharIndex(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setElementIndex(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ShuQianBook entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ShuQianBook entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
