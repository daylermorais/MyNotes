package com.daylermorais.android.mynotes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.daylermorais.android.mynotes.data.MyNotesProvider;

import java.sql.SQLException;

public class Modify_subject extends Activity implements View.OnClickListener {

    EditText et;
    Button edit_bt, delete_bt;
    long member_id;

    MyNotesProvider dbcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_subject);

        dbcon = new MyNotesProvider(this);
        try {
            dbcon.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        et = (EditText) findViewById(R.id.edit_subject_id);
        edit_bt = (Button) findViewById(R.id.update_bt_id);
        delete_bt = (Button) findViewById(R.id.delete_bt_id);

        Intent i = getIntent();
        String memberID = i.getStringExtra("subjectID");
        String memberName = i.getStringExtra("subjectDescription");

        member_id = Long.parseLong(memberID);

        et.setText(memberName);

        edit_bt.setOnClickListener(this);
        delete_bt.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.update_bt_id:
                String memName_upd = et.getText().toString();
                dbcon.updateData(member_id, memName_upd);
                this.returnHome();
                break;

            case R.id.delete_bt_id:
                dbcon.deleteData(member_id);
                this.returnHome();
                break;
        }
    }

    public void returnHome() {

        Intent home_intent = new Intent(getApplicationContext(),
                MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(home_intent);
    }

}
