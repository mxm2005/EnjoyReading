package com.example.mxm.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.mxm.myapplication.novel.model.NovelInfo;

import java.util.ArrayList;
import java.util.List;

public class db_dao {
    private static final String TAG = "NovelDao";

    // 列定义
    private final String[] NOVEL_COLUMNS = new String[] {"id", "url","title","img","intro","author","category","updateTime","novelId"};


    private Context context;
    private db_helper dbHelper;

    public db_dao(Context context) {
        this.context = context;
        dbHelper = new db_helper(context);
    }

    /**
     * 判断表中是否有数据
     */
    public boolean isDataExist(){
        int count = 0;

        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = dbHelper.getReadableDatabase();
            // select count(Id) from Orders
            cursor = db.query(db_helper.TABLE_NAME, new String[]{"COUNT(Id)"}, null, null, null, null, null);

            if (cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
            if (count > 0) return true;
        }
        catch (Exception e) {
            Log.e(TAG, "", e);
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }
        return false;
    }

    /**
     * 初始化数据
     */
    public void initTable(){
        SQLiteDatabase db = null;

        try {
            db = dbHelper.getWritableDatabase();
            db.beginTransaction();
            //{"id", "url","title","img","intro","author","category","updateTime","novelId"}
            db.execSQL("insert into " + db_helper.TABLE_NAME + " (id, url, title, img,intro,author,category,updateTime,novelId) values (1, '', '测试标题1', 'img/20191010/001.jpg', '简介', 'mack', '历史', '2019-10-10 12:15:26', 1)");
            db.execSQL("insert into " + db_helper.TABLE_NAME + " (id, url, title, img,intro,author,category,updateTime,novelId) values (2, '', '测试标题2', 'img/20191010/001.jpg', '简介', 'mack', '历史', '2019-10-10 12:15:26', 2)");
            db.execSQL("insert into " + db_helper.TABLE_NAME + " (id, url, title, img,intro,author,category,updateTime,novelId) values (3, '', '测试标题3', 'img/20191010/001.jpg', '简介', 'mack', '历史', '2019-10-10 12:15:26', 3)");
            db.execSQL("insert into " + db_helper.TABLE_NAME + " (id, url, title, img,intro,author,category,updateTime,novelId) values (4, '', '测试标题4', 'img/20191010/001.jpg', '简介', 'mack', '历史', '2019-10-10 12:15:26', 4)");
            db.execSQL("insert into " + db_helper.TABLE_NAME + " (id, url, title, img,intro,author,category,updateTime,novelId) values (5, '', '测试标题5', 'img/20191010/001.jpg', '简介', 'mack', '历史', '2019-10-10 12:15:26', 5)");
            db.execSQL("insert into " + db_helper.TABLE_NAME + " (id, url, title, img,intro,author,category,updateTime,novelId) values (6, '', '测试标题6', 'img/20191010/001.jpg', '简介', 'mack', '历史', '2019-10-10 12:15:26', 6)");

            db.setTransactionSuccessful();
        }catch (Exception e){
            Log.e(TAG, "", e);
        }finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
    }

    /**
     * 执行自定义SQL语句
     */
    /*public void execSQL(String sql) {
        SQLiteDatabase db = null;

        try {
            if (sql.contains("select")){
                Toast.makeText(context, R.string.strUnableSql, Toast.LENGTH_SHORT).show();
            }else if (sql.contains("insert") || sql.contains("update") || sql.contains("delete")){
                db = dbHelper.getWritableDatabase();
                db.beginTransaction();
                db.execSQL(sql);
                db.setTransactionSuccessful();
                Toast.makeText(context, R.string.strSuccessSql, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(context, R.string.strErrorSql, Toast.LENGTH_SHORT).show();
            Log.e(TAG, "", e);
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
    }*/

    /**
     * 查询数据库中所有数据
     */
    public List<NovelInfo> getAllDate(){
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = dbHelper.getReadableDatabase();
            // select * from Orders
            cursor = db.query(db_helper.TABLE_NAME, NOVEL_COLUMNS, null, null, null, null, null);

            if (cursor.getCount() > 0) {
                List<NovelInfo> orderList = new ArrayList<>(cursor.getCount());
                while (cursor.moveToNext()) {
                    orderList.add(parseOrder(cursor));
                }
                return orderList;
            }
        }
        catch (Exception e) {
            Log.e(TAG, "", e);
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }

        return null;
    }

    /**
     * 新增一条数据
     */
    public boolean insertDate(){
        SQLiteDatabase db = null;

        try {
            db = dbHelper.getWritableDatabase();
            db.beginTransaction();

            // insert into Orders(Id, CustomName, OrderPrice, Country) values (7, "Jne", 700, "China");
            ContentValues contentValues = new ContentValues();
            contentValues.put("Id", 7);
            contentValues.put("CustomName", "Jne");
            contentValues.put("OrderPrice", 700);
            contentValues.put("Country", "China");
            db.insertOrThrow(db_helper.TABLE_NAME, null, contentValues);

            db.setTransactionSuccessful();
            return true;
        }catch (SQLiteConstraintException e){
            Toast.makeText(context, "主键重复", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Log.e(TAG, "", e);
        }finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
        return false;
    }

    /**
     * 删除一条数据  此处删除Id为7的数据
     */
    public boolean deleteOrder() {
        SQLiteDatabase db = null;

        try {
            db = dbHelper.getWritableDatabase();
            db.beginTransaction();

            // delete from Orders where Id = 7
            db.delete(db_helper.TABLE_NAME, "Id = ?", new String[]{String.valueOf(7)});
            db.setTransactionSuccessful();
            return true;
        } catch (Exception e) {
            Log.e(TAG, "", e);
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
        return false;
    }

    /**
     * 修改一条数据  此处将Id为6的数据的OrderPrice修改了800
     */
    public boolean updateOrder(){
        SQLiteDatabase db = null;
        try {
            db = dbHelper.getWritableDatabase();
            db.beginTransaction();

            // update Orders set OrderPrice = 800 where Id = 6
            ContentValues cv = new ContentValues();
            cv.put("OrderPrice", 800);
            db.update(db_helper.TABLE_NAME,
                    cv,
                    "Id = ?",
                    new String[]{String.valueOf(6)});
            db.setTransactionSuccessful();
            return true;
        }
        catch (Exception e) {
            Log.e(TAG, "", e);
        }
        finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }

        return false;
    }

    /**
     * 数据查询  此处将用户名为"Bor"的信息提取出来
     */
    /*public List<Order> getBorOrder(){
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = dbHelper.getReadableDatabase();

            // select * from Orders where CustomName = 'Bor'
            cursor = db.query(db_helper.TABLE_NAME,
                    NOVEL_COLUMNS,
                    "CustomName = ?",
                    new String[] {"Bor"},
                    null, null, null);

            if (cursor.getCount() > 0) {
                List<Order> orderList = new ArrayList<Order>(cursor.getCount());
                while (cursor.moveToNext()) {
                    Order order = parseOrder(cursor);
                    orderList.add(order);
                }
                return orderList;
            }
        }
        catch (Exception e) {
            Log.e(TAG, "", e);
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }

        return null;
    }*/

    /**
     * 统计查询  此处查询Country为China的用户总数
     */
    public int getChinaCount(){
        int count = 0;

        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = dbHelper.getReadableDatabase();
            // select count(Id) from Orders where Country = 'China'
            cursor = db.query(db_helper.TABLE_NAME,
                    new String[]{"COUNT(Id)"},
                    "Country = ?",
                    new String[] {"China"},
                    null, null, null);

            if (cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
        }
        catch (Exception e) {
            Log.e(TAG, "", e);
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }

        return count;
    }

    /**
     * 比较查询  此处查询单笔数据中OrderPrice最高的
     */
    /*public Order getMaxOrderPrice(){
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = dbHelper.getReadableDatabase();
            // select Id, CustomName, Max(OrderPrice) as OrderPrice, Country from Orders
            cursor = db.query(db_helper.TABLE_NAME, new String[]{"Id", "CustomName", "Max(OrderPrice) as OrderPrice", "Country"}, null, null, null, null, null);

            if (cursor.getCount() > 0){
                if (cursor.moveToFirst()) {
                    return parseOrder(cursor);
                }
            }
        }
        catch (Exception e) {
            Log.e(TAG, "", e);
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }

        return null;
    }*/

    /**
     * 将查找到的数据转换成Order类
     */
    private NovelInfo parseOrder(Cursor cursor) {
        NovelInfo info = new NovelInfo();
        info.id = (cursor.getInt(cursor.getColumnIndex("id")));
        info.title = (cursor.getString(cursor.getColumnIndex("title")));
        info.img = (cursor.getString(cursor.getColumnIndex("img")));
        info.updateTime = (cursor.getString(cursor.getColumnIndex("updateTime")));
        info.intro = (cursor.getString(cursor.getColumnIndex("intro")));
        info.author = (cursor.getString(cursor.getColumnIndex("author")));
        return info;
    }
}
