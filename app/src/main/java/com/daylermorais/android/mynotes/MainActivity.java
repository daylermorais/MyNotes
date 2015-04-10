package com.daylermorais.android.mynotes;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.daylermorais.android.mynotes.data.MyNotesContract;
import com.daylermorais.android.mynotes.data.MyNotesProvider;

import java.sql.SQLException;

public class MainActivity extends Activity {

    Button addSubject_bt;
    ListView lv;
    MyNotesProvider dbcon;
    TextView subjectID_tv, subjectDescription_tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbcon = new MyNotesProvider(this);
        try {
            dbcon.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        addSubject_bt = (Button) findViewById(R.id.addsubject_bt_id);
        lv = (ListView) findViewById(R.id.subjectList_id);

        // onClickListner for addsubject Button
        addSubject_bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent add_subject = new Intent(MainActivity.this, Add_subject.class);
                startActivity(add_subject);
            }
        });

        // Attach The Data From DataBase Into ListView Using Cursor Adapter
        Cursor cursor = dbcon.readData();
        String[] from = new String[]{
                MyNotesContract.SubjectEntry._ID,
                MyNotesContract.SubjectEntry.COLUMN_SUBJECT_DESCRIPTION
        };
        int[] to = new int[]{
                R.id.subject_id,
                R.id.subject_description
        };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                MainActivity.this, R.layout.view_subject_entry, cursor, from, to
        );

        adapter.notifyDataSetChanged();
        lv.setAdapter(adapter);

        // OnClickListner For List Items
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                subjectID_tv = (TextView) view.findViewById(R.id.subject_id);
                subjectDescription_tv = (TextView) view.findViewById(R.id.subject_description);

                String subjectID_val = subjectID_tv.getText().toString();
                String subjectDescription_val = subjectDescription_tv.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(),
                        Modify_subject.class);
                modify_intent.putExtra("subjectDescription", subjectDescription_val);
                modify_intent.putExtra("subjectID", subjectID_val);
                startActivity(modify_intent);
            }
        });
    }
}
