package com.daylermorais.android.mynotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;

import com.daylermorais.android.mynotes.data.MyNotesProvider;

import java.sql.SQLException;

public class Add_subject extends Activity implements View.OnClickListener{
    EditText et;
    Button add_bt;
    MyNotesProvider dbcon;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_subject);
        et = (EditText) findViewById(R.id.subject_et_id);
        add_bt = (Button) findViewById(R.id.add_bt_id);

        dbcon = new MyNotesProvider(this);
        try {
            dbcon.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        add_bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch ((v.getId())){
            case R.id.add_bt_id:
                String subject = et.getText().toString();
                dbcon.insertSubject(subject);
                Intent main = new Intent(Add_subject.this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);

            default:
                break;
        }
    }

}
