package com.example.baseandroidth1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baseandroidth1.dbFake.TblItem;
import com.example.baseandroidth1.model.Item;
import com.example.baseandroidth1.model.ItemAdapter;
import com.example.baseandroidth1.utils.DataUtils;
import com.example.baseandroidth1.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

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
    private ImageButton btnAdd;
    private EditText etSearch;
    private RecyclerView listItem;
    private ConstraintLayout form;

    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        btnCancel.setOnClickListener(view -> {
            resetForm();
            hideForm();
        });
        btnSave.setOnClickListener(view -> saveItem());
        btnCalendar.setOnClickListener(view -> createDatePickerDialog().show());
        btnAdd.setOnClickListener(view -> showForm());
        etSearch.addTextChangedListener(doSearch());
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
        btnAdd = findViewById(R.id.btnAdd);
        etSearch = findViewById(R.id.search);
        listItem = findViewById(R.id.listItem);
        form = findViewById(R.id.form);
        hideForm();
        itemAdapter = new ItemAdapter(this);
        itemAdapter.setItemListener(this);
        listItem.setAdapter(itemAdapter);
        listItem.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        tvType.setVisibility(View.GONE);
    }

    private void createAdapter (ItemAdapter adapter, List<Item> items) {
        adapter.setItems(items);
        adapter.notifyDataSetChanged();
    }

    private void resetForm () {
        tvType.setText(EMPTY);
        etName.setText(EMPTY);
        etDate.setText(EMPTY);
        etContent.setText(EMPTY);
        rbMale.setChecked(true);
    }

    private void hideForm () {
        form.setVisibility(View.GONE);
    }

    private void showForm () {
        form.setVisibility(View.VISIBLE);
    }

    private Item getDataInForm () {
        Item item = new Item(etName.getText().toString(), etContent.getText().toString(), etDate.getText().toString(), rbMale.isChecked());
        if (!DataUtils.isNullOrEmpty(tvType.getText().toString())) {
            item.setId(Integer.parseInt(tvType.getText().toString()));
        }
        return item;
    }

    private void setDataInForm (Item item) {
        tvType.setText(String.valueOf(item.getId()));
        etName.setText(item.getName());
        etDate.setText(item.getDate());
        etContent.setText(item.getContent());
        if (item.getGender()) rbMale.setChecked(true);
        else rbFemale.setChecked(true);
    }

    private void saveItem () {
        if (checkAllFields()) {
            Item item = getDataInForm();
            if (DataUtils.isNullOrEmpty(item.getId())) {
                item.setId(++Item.count);
                itemAdapter.addItem(item);
                createAdapter(itemAdapter, TblItem.getData());
                Toast.makeText(this, getResources().getString(R.string.message_add_success), Toast.LENGTH_SHORT).show();
            } else {
                itemAdapter.setItem(item);
                createAdapter(itemAdapter, TblItem.getData());
                Toast.makeText(this, getResources().getString(R.string.message_update_success), Toast.LENGTH_LONG).show();
                hideForm();
            }
            resetForm();
        }
    }

    @Override
    public void onClickItem(View view, int position) {
        Item item = itemAdapter.getItem(position);
        setDataInForm(item);
        showForm();
    }

    private DatePickerDialog createDatePickerDialog() {
        return new DatePickerDialog(this,
                (datePicker, year, month, day) -> etDate.setText(String.format("%d/%d/%d", day, month, year)),
                DateUtils.getYear(), DateUtils.getMonth(), DateUtils.getDay());
    }

    private List<Item> doSearch (String keySearch) {
        List<Item> itemsSearch = new ArrayList<>();
        if (DataUtils.isNullOrEmptyOrBlank(keySearch) || DataUtils.isNullOrEmpty(itemAdapter.getItems()))
            return TblItem.getData();
        itemAdapter.getItems().forEach(item -> {
            if (item.getName().toLowerCase().contains(keySearch.toLowerCase()))
                itemsSearch.add(item);
        });
        return itemsSearch;
    }

    private TextWatcher doSearch () {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                createAdapter(itemAdapter, doSearch(etSearch.getText().toString()));
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        };
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