package halla.icsw.mobileproject_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login1 extends AppCompatActivity {
    dbHelper helper;
    SQLiteDatabase db;
    EditText id,pw;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        Button login = findViewById(R.id.login);
        id = findViewById(R.id.l_id);
        pw = findViewById(R.id.l_pw);
        helper = new dbHelper(this);
        db = helper.getWritableDatabase();
        b1 = findViewById(R.id.login);


    }
    public void login(View v) {
        if (id.length() > 0 && pw.length() > 0){
        String a = id.getText().toString();
        String b = pw.getText().toString();
            Cursor cursor = db.rawQuery("SELECT _id,password FROM 회원 WHERE _id = '" + a + "' AND password = '" + b + "';", null);
            if (cursor.getCount() > 0) {
                Toast.makeText(this, "로그인되었습니다.", Toast.LENGTH_LONG).show();
            }
            if(cursor.getCount() == 0){
                Toast.makeText(this, "회원이 아닙니다.", Toast.LENGTH_LONG).show();
            }
        }
        else Toast.makeText(this, "회원정보를 입력해주세요", Toast.LENGTH_LONG).show();
        }


    public void member(View v){
        Intent intent = new Intent(login1.this,MainActivity.class);
        startActivity(intent);
    }

    public void member2(View v){
        Intent intent = new Intent(login1.this,bookw.class);
        startActivity(intent);
    }

}
