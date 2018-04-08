package com.example.administrator.providertest;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private String newId;//记录id

    Button addData,queryData,updateData,deleteData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         addData =  findViewById(R.id.add_data);//添加
         queryData =  findViewById(R.id.query_data);//查询
         updateData = (Button) findViewById(R.id.update_data);//更新
         deleteData = (Button) findViewById(R.id.delete_data);//删除

        addData.setOnClickListener(this);
        queryData.setOnClickListener(this);
        updateData.setOnClickListener(this);
        deleteData.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_data:
                Uri uri = Uri.parse("content://com.example.administrator.databaseteat.provider/book");
                ContentValues values = new ContentValues();
                values.put("name", "A Clash of Kings");
                values.put("author", "George Martin");
                values.put("pages", 1040);
                values.put("price", 55.55);
                Uri newUri = getContentResolver().insert(uri, values);
                newId = newUri.getPathSegments().get(1);
                break;

            case R.id.query_data:
                Uri uri1 = Uri.parse("content://com.example.administrator.databaseteat.provider/book");
                Cursor cursor = getContentResolver().query(uri1,null,null,null,null);

                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String name = cursor.getString(cursor. getColumnIndex("name"));
                        String author = cursor.getString(cursor. getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex ("pages"));
                        double price = cursor.getDouble(cursor. getColumnIndex("price"));
                        Log.d("TTEST", "book name is " + name);
                        Log.d("TTEST", "book author is " + author);
                        Log.d("TTEST", "book pages is " + pages);
                        Log.d("TTEST", "book price is " + price);
                    }
                    cursor.close();
                }


                break;

            case R.id.update_data:
                Uri uri2 = Uri.parse("content://com.example.administrator.databaseteat.provider/book/" + newId);
                ContentValues values1 = new ContentValues();
                values1.put("name", "A Storm of Swords");
                values1.put("pages", 1216);
                values1.put("price", 24.05);
                getContentResolver().update(uri2, values1, null, null);
                break;
            case R.id.delete_data:
                Uri uri3 = Uri.parse("content://com.example.administrator.databaseteat.provider/book/"+ newId);
                getContentResolver().delete(uri3, null, null);
                break;

             default:
                 break;
        }



    }
}
