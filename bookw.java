package halla.icsw.mobileproject_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class bookw extends AppCompatActivity {
    dbHelper helper;
    SQLiteDatabase db;
    Button lib;
    public EditText gtitle,btitle,write,contect;
    RadioGroup rg;
    RadioButton rb1,rb2,rb3,rb4,rb5;
    RadioButton[] buttons;
    String bgenre;
    String ggtitle;
    ListView listView;
    ArrayList<String> items;
    ArrayAdapter<String> adapter;
    ArrayList<String> items2;
    LinearLayout book,li;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookw);

        helper = new dbHelper(this);
        db = helper.getWritableDatabase();
        gtitle = findViewById(R.id.gtitle);
        btitle = findViewById(R.id.btitle);
        write = findViewById(R.id.write);
        contect = findViewById(R.id.contect);
        rb1 = findViewById(R.id.rb1);rb2 = findViewById(R.id.rb2);rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);rb5 = findViewById(R.id.rb5);
        buttons = new RadioButton[]{rb1,rb2,rb3,rb4,rb5};
        book = findViewById(R.id.book);
        li = findViewById(R.id.li);
        lib = findViewById(R.id.lib);

        if(items2 == null) {
            items = new ArrayList<String>();
        }else {
            items = items2;
        }

        adapter = new ArrayAdapter<String>(bookw.this,
                android.R.layout.simple_list_item_single_choice, items);

        // 어댑터 설정
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


    }

    public void save(View v){
        if(gtitle.length() > 0 && btitle.length() > 0 && write.length() > 0 && contect.length() > 0){
            ggtitle = gtitle.getText().toString();
            String bbtitle = btitle.getText().toString();
            String write2 = write.getText().toString();
            String contect2 = contect.getText().toString();
            for(int i = 0; i < 5; i++){
                if(buttons[i].isChecked() == true){
                    bgenre = buttons[i].getText().toString();
                }
            }
            db.execSQL("INSERT INTO 독후감 VALUES ('" + ggtitle + "','" + bbtitle + "','" + write2 + "','" + contect2 + "','" + bgenre +  "')");

            Toast.makeText(this,"독후감이 저장되었습니다.",Toast.LENGTH_LONG).show();
            Cursor cursor = db.rawQuery("SELECT gtitle FROM 독후감;",null );
            for (int i = 0; i < cursor.getCount();i++) {
                cursor.moveToNext();
                String s = cursor.getString(0);
                items.add(i,s);
            }
            adapter.notifyDataSetChanged();
                 book.setVisibility(View.GONE);
            li.setVisibility(View.VISIBLE);
            items2 = items;





            /*Intent intent = new Intent(bookw.this,book_report.class);
            intent.putExtra("aa",ggtitle);
            startActivity(intent);*/

        }
        else {
            Toast.makeText(this,"모두 작성해 주세요.",Toast.LENGTH_LONG).show();
        }
    }

    public void no(View v){
        Intent intent = new Intent(bookw.this,book_report.class);
        startActivity(intent);
    }
    public void lib(View v){
        Intent intent = new Intent(bookw.this,MainActivity.class);
        startActivity(intent);
    }

}
