package com.example.baseandroidth1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.baseandroidth1.model.Item;
import com.example.baseandroidth1.model.ItemAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ItemAdapter.ItemListener {

    private static final String EMPTY = "";

    private TextView tvType;
    private EditText etName;
    private EditText etDate;
    private EditText etContent;
    private RadioButton rbMale;
    private RadioButton rbFemale;
    private Button btnCancel;
    private Button btnSave;
    private EditText etSearch;
    private RecyclerView listItem;

    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        btnCancel.setOnClickListener(view -> resetForm());
        btnSave.setOnClickListener(view -> saveItem());
    }

    private void initView () {
        tvType = findViewById(R.id.tvId);
        etName = findViewById(R.id.etObjectName);
        etDate = findViewById(R.id.etObjectDate);
        etContent = findViewById(R.id.etObjectContent);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);
        etSearch = findViewById(R.id.search);
        listItem = findViewById(R.id.listItem);
        itemAdapter = new ItemAdapter(new ArrayList<>());
        itemAdapter.setItemListener(this);
        listItem.setAdapter(itemAdapter);
        listItem.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
    }

    private void resetForm () {
        tvType.setText(getResources().getString(R.string.FORM_ADD));
        etName.setText(EMPTY);
        etDate.setText(EMPTY);
        etContent.setText(EMPTY);
        rbMale.setChecked(true);
    }

    private Item getDataInForm () {
        Item item = new Item(etName.getText().toString(), etDate.getText().toString(),
                etContent.getText().toString(), rbMale.isChecked());
        return item;
    }

    private void setDataInForm (Item item) {
        tvType.setText(String.format("%s: %d", getResources().getString(R.string.FORM_UPDATE), item.getPosition()));
        etName.setText(item.getName());
        etDate.setText(item.getDate());
        etContent.setText(item.getContent());
        if (item.getGender()) rbMale.setChecked(true);
        else rbFemale.setChecked(true);
    }

    private void saveItem () {
        if (tvType.getText().toString().equals(getResources().getString(R.string.FORM_ADD)))
            itemAdapter.addItem(getDataInForm());
        else itemAdapter.setItem(getDataInForm(), getPosition());
        resetForm();
    }

    private int getPosition () {
        String[] arr = tvType.getText().toString().split("\\s");
        return Integer.parseInt(arr[arr.length - 1]);
    }

    @Override
    public void onClickItem(View view, int position) {
        Item item = itemAdapter.getItem(position);
        item.setPosition(position);
        setDataInForm(item);
    }
}