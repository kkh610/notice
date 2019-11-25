package halla.icsw.mobileproject_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

class dbHelper extends SQLiteOpenHelper{


    public dbHelper(Context context){
        super(context,"login.db",null,1);

    }

    public void onCreate(SQLiteDatabase db){
        String a = "CREATE TABLE 회원 (_id text PRIMARY KEY,"+
                "password text,name text,bri text,gender int,genre text);";
        String b = "CREATE TABLE 독후감 (gtitle text,btite text,write text,contect text,genre text);";
        db.execSQL(a);
        db.execSQL(b);
        db.execSQL("INSERT INTO 독후감 VALUES('A','A','A','A','A')");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS contact");
        onCreate(db);
    }
}


public class MainActivity extends AppCompatActivity {
    dbHelper helper;
    SQLiteDatabase db;
    EditText id,password,password_1,bir,name;
    RadioGroup rg;
    RadioButton men,women;
    int gender;
    CheckBox c1,c2,c3,c4,c5,c6,c7,c8;
    CheckBox[] checkBoxes;
    String genre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new dbHelper(MainActivity.this);
        db = helper.getWritableDatabase();
        id = findViewById(R.id.ide);
        password = findViewById(R.id.password_1);
        password_1 = findViewById(R.id.password_02);
        name = findViewById(R.id.name_1);
        bir = findViewById(R.id.bir_1);
        men = findViewById(R.id.men);
        women = findViewById(R.id.women);
        c1 = findViewById(R.id.checkBox);c2 = findViewById(R.id.checkBox2);c3 = findViewById(R.id.checkBox3);c4 = findViewById(R.id.checkBox4);
        c5 = findViewById(R.id.checkBox5);c6 = findViewById(R.id.checkBox6);c7 = findViewById(R.id.checkBox7);c8 = findViewById(R.id.checkBox8);

        checkBoxes = new CheckBox[]{c1,c2,c3,c4,c5,c6,c7,c8};
    }

    public void member(View view) {
        if(id.length() > 0 && password.length() > 0 && password_1.length() > 0 && name.length() > 0){
        String id1 = id.getText().toString();
        String password1 = password.getText().toString();
        String password2 = password_1.getText().toString();
        String name1 = name.getText().toString();
        String bir1 = bir.getText().toString();
        if (men.isChecked())
            gender = 1;
        if (women.isChecked())
            gender = 2;
        for (int i = 0; i < 8; i++) {
            if (checkBoxes[i].isChecked()) {
                if (i == 7)
                    genre += checkBoxes[i].getText().toString();
                else
                    genre += checkBoxes[i].getText().toString() + ",";
            }
        }
            if (!password1.equals(password2)) {
                Toast.makeText(this, "비밀번호를 다시 확인해주세요", Toast.LENGTH_LONG).show();
            } else {
                db.execSQL("INSERT INTO 회원 VALUES ('" + id1 + "','" + password1 + "','" + name1 + "','" + bir1 + "','" + gender + "','" + genre + "')");
                Toast.makeText(this, "가입되었습니다.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, login1.class);
                startActivity(intent);
            }
        }
        else {
            Toast.makeText(this, "필수사항을 모두 입력해주세요", Toast.LENGTH_LONG).show();
        }
    }

    public void cancel(View v){
        Intent intent = new Intent(MainActivity.this,login1.class);
        startActivity(intent);
    }
}
