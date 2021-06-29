package com.shop.bestshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.shop.bestshop.data.EntityMhs;
import com.shop.bestshop.data.MhsParcel;

import java.util.List;

import static com.shop.bestshop.ShopApp.mhsDatabase;

public class MainActivity extends AppCompatActivity implements ViewMhsItem.OnItemsAction {

    LinearLayout linMain;
    Button btnAdd;
    EditText edtCari;
    Button btnCari;

    List<EntityMhs> mhsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        loadData();
    }

    void initViews() {
        btnCari = findViewById(R.id.btnCari);
        edtCari = findViewById(R.id.edtCari);
        linMain = findViewById(R.id.linMain);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivityForResult(intent, 111);
            }
        });

        btnCari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchMhs(edtCari.getText().toString());
            }
        });
    }


    void searchMhs(String param) {
        mhsList = mhsDatabase.getMhsDao().seacrhMhs("%" + param + "%");
        if (mhsList.size() > 0) {
            linMain.removeAllViews();
            for (int i = 0; i < mhsList.size(); i++) {
                ViewMhsItem viewMhsItem = new ViewMhsItem(this, this);
                viewMhsItem.setParcel(mhsList.get(i).toParcel());
                linMain.addView(viewMhsItem);
            }
        }
    }

    void loadData() {
        mhsList = mhsDatabase.getMhsDao().getMhs();
        for (int i = 0; i < mhsList.size(); i++) {
            ViewMhsItem viewMhsItem = new ViewMhsItem(this, this);
            viewMhsItem.setParcel(mhsList.get(i).toParcel());
            linMain.addView(viewMhsItem);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 111) {
            MhsParcel mhsParcel = data.getParcelableExtra("mhs");
            ViewMhsItem viewMhsItem = new ViewMhsItem(this, this);
            viewMhsItem.setParcel(mhsParcel);
            linMain.addView(viewMhsItem, 0); // sisipkan di index 0 (paling atas)
            viewMhsItem.startAnim();
        } else if (resultCode == RESULT_OK && requestCode == 112) {
            MhsParcel mhsParcel = data.getParcelableExtra("mhs");
            int index = data.getIntExtra("index", 0);

            ((ViewMhsItem) linMain.getChildAt(index)).setParcel(mhsParcel);
            ((ViewMhsItem) linMain.getChildAt(index)).startAnim();

        }
    }

    @Override
    public void onItemDeleted(ViewMhsItem viewMhsItem) {
        linMain.removeView(viewMhsItem);
    }
}