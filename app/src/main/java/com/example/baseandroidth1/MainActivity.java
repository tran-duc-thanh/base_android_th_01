package com.example.baseandroidth1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baseandroidth1.model.Item;
import com.example.baseandroidth1.model.ItemAdapter;
import com.example.baseandroidth1.utils.DataUtils;
import com.example.baseandroidth1.utils.DateUtils;

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
    private ImageButton btnCalendar;
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
        btnCalendar.setOnClickListener(view -> createDatePickerDialog().show());
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
        btnCalendar = findViewById(R.id.btnDate);
        etSearch = findViewById(R.id.search);
        listItem = findViewById(R.id.listItem);
        itemAdapter = new ItemAdapter(this);
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
        Item item = new Item(etName.getText().toString(), etContent.getText().toString(), etDate.getText().toString(), rbMale.isChecked());
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
        if (checkAllFields()) {
            if (tvType.getText().toString().equals(getResources().getString(R.string.FORM_ADD))) {
                itemAdapter.addItem(getDataInForm());
                Toast.makeText(this, getResources().getString(R.string.message_add_success), Toast.LENGTH_SHORT).show();
            } else {
                itemAdapter.setItem(getDataInForm(), getPosition());
                Toast.makeText(this, getResources().getString(R.string.message_update_success), Toast.LENGTH_LONG).show();
            }
            resetForm();
        }
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

    private DatePickerDialog createDatePickerDialog() {
        return new DatePickerDialog(this,
                (datePicker, year, month, day) -> etDate.setText(String.format("%d/%d/%d", day, month, year)),
                DateUtils.getYear(), DateUtils.getMonth(), DateUtils.getDay());
    }

    private boolean checkAllFields () {
        if (DataUtils.isNullOrEmptyOrBlank(etName.getText().toString())) {
            etName.setError(getResources().getString(R.string.message_error_empty));
            return false;
        }
        if (DataUtils.isNullOrEmptyOrBlank(etDate.getText().toString())) {
            etDate.setError(getResources().getString(R.string.message_error_empty));
            return false;
        }
        if (!DateUtils.isValidate(etDate.getText().toString())) {
            etDate.setError(getResources().getString(R.string.message_error_format_date));
            return false;
        }
        return true;
    }
}