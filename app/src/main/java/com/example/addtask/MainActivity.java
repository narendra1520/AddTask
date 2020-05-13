package com.example.addtask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    @BindView(R.id.edt_mobile)
    EditText edt_mobile;

    @BindView(R.id.edt_name)
    EditText edt_name;

    @BindView(R.id.ly_name)
    TextInputLayout ly_name;

    @BindView(R.id.ly_mobile)
    TextInputLayout ly_mobile;

    @BindView(R.id.spinner_country)
    Spinner spinner_country;

    @BindView(R.id.btn_add)
    Button btnadd;

    @BindView(R.id.btn_delete)
    Button btndelete;

    @BindView(R.id.btn_save)
    Button btnsave;

    @BindView(R.id.recy_note)
    RecyclerView recyclerView;

    String name, mobile, country;
    private String[] ar_country;

    List<Note> listofnote = new ArrayList<>();
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ar_country = getResources().getStringArray(R.array.country);
        country = ar_country[0];

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, ar_country);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_country.setAdapter(dataAdapter);
        spinner_country.setSelection(0);
        spinner_country.setOnItemSelectedListener(this);

        btnadd.setOnClickListener(this);
        btndelete.setOnClickListener(this);
        btnsave.setOnClickListener(this);

        db = new DBHelper(MainActivity.this);
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_add){
            if(validateForm()) {
                db.insertNote(name,mobile,country);
            }
        }else if(v.getId() == R.id.btn_save){
            //db.getAllNotes();
            startActivity(new Intent(MainActivity.this,SavedNoted.class));
        }else {
            //dbManager.deleteNote();
        }
    }

    private boolean validateForm() {
        ly_name.setError(null);
        ly_mobile.setError(null);

        name = edt_name.getText().toString();
        mobile = edt_mobile.getText().toString();

        String er_name = Function.checkName(name);
        String er_mobile = Function.checkMobile(mobile);

        if(er_name == null && er_mobile == null){
            return true;
        }else {
            if (er_name!=null)
                ly_name.setError(er_name);

            if (er_mobile!=null)
                ly_mobile.setError(er_mobile);
            return false;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        country = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
