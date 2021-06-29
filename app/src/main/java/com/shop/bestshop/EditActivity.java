package com.shop.bestshop;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.bumptech.glide.Glide;
import com.shop.bestshop.data.EntityMhs;
import com.shop.bestshop.data.MhsParcel;

import static com.shop.bestshop.ShopApp.mhsDatabase;

public class EditActivity extends AppCompatActivity {

    EditText etNama;
    EditText edtAlamat;
    EditText edtHp;
    EditText edtImageUrl;
    Button btnSimpan;
    ImageView imgIcon;

    MhsParcel mhsParcel;
    boolean isNew;
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        mhsParcel = getIntent().getParcelableExtra("mhs");
        index = getIntent().getIntExtra("index", 0);
        isNew = (mhsParcel == null);

        initViews();
        initData();
        initActions();
    }

    void initData(){
        if (mhsParcel != null){
            etNama.setText(mhsParcel.getNama());
            edtAlamat.setText(mhsParcel.getAlamat());
            edtHp.setText(mhsParcel.getHp());
            edtImageUrl.setText(mhsParcel.getPhoto());
            if (!mhsParcel.getPhoto().isEmpty()){
                Glide.with(this).load(mhsParcel.getPhoto()).into(imgIcon);
            }
        }
    }

    void initActions(){
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                doSave();

                Intent intent = getIntent();
                intent.putExtra("index", index);
                intent.putExtra("mhs", mhsParcel);
                intent.putExtra("isNew", isNew);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        edtImageUrl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty()){
                    Glide.with(EditActivity.this).load(s.toString()).into(imgIcon);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    void initViews(){
        edtAlamat = findViewById(R.id.edtAlamat);
        etNama = findViewById(R.id.edtNama);
        edtHp = findViewById(R.id.edtHp);
        edtImageUrl = findViewById(R.id.edtImageUrl);
        btnSimpan = findViewById(R.id.btnSimpan);
        imgIcon = findViewById(R.id.imgIcon);
    }

    void doSave(){
        if (isNew){
            EntityMhs entityMhs = new EntityMhs();
            entityMhs.setNama(etNama.getText().toString());
            entityMhs.setAlamat(edtAlamat.getText().toString());
            entityMhs.setHp(edtHp.getText().toString());
            entityMhs.setPhoto(edtImageUrl.getText().toString());
            mhsDatabase.getMhsDao().insert(entityMhs);
            mhsParcel = entityMhs.toParcel();
        } else {

            mhsParcel.setNama(etNama.getText().toString());
            mhsParcel.setAlamat(edtAlamat.getText().toString());
            mhsParcel.setHp(edtHp.getText().toString());
            mhsParcel.setPhoto(edtImageUrl.getText().toString());

            mhsDatabase.getMhsDao().update(mhsParcel.getNama(), mhsParcel.getAlamat(),
                    mhsParcel.getHp(), mhsParcel.getId());
        }
    }
}