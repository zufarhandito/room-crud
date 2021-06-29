package com.shop.bestshop;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.Glide;
import com.shop.bestshop.data.MhsParcel;

import static com.shop.bestshop.ShopApp.mhsDatabase;

public class ViewMhsItem extends RelativeLayout {

    Context context;
    TextView txtNama;
    TextView txtAlamat;
    TextView txtHp;
    ImageView imgPhoto;
    Button btnEdit;
    Button btnDel;

    MhsParcel parcel;
    OnItemsAction onItemsAction;
    ViewMhsItem viewMhsItem;

    public ViewMhsItem(Context context, OnItemsAction onItemsAction) {
        super(context);
        this.onItemsAction = onItemsAction;
        inflate(context);
    }

    public ViewMhsItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context);
    }

    public ViewMhsItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context);
    }

    public MhsParcel getParcel() {
        return parcel;
    }

    public void setParcel(MhsParcel parcel) {
        this.parcel = parcel;
        txtNama.setText(parcel.getNama());
        txtAlamat.setText(parcel.getAlamat());
        txtHp.setText("Stok : "+parcel.getHp());
        Glide.with(context).load(parcel.getPhoto()).into(imgPhoto);
    }

    void inflate(Context context) {
        this.context = context;
        this.viewMhsItem = this;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.layout_mhs_item, this);
        txtNama = findViewById(R.id.txtNama);
        txtAlamat = findViewById(R.id.txtAlamat);
        txtHp = findViewById(R.id.txtHp);
        btnEdit = findViewById(R.id.btnEdit);
        btnDel = findViewById(R.id.btnDel);
        imgPhoto = findViewById(R.id.imgPhoto);


        btnEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = ((LinearLayout)getParent()).indexOfChild(viewMhsItem);
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("index", index);
                intent.putExtra("mhs", parcel);
                intent.putExtra("isNew", false);
                ((Activity) context).startActivityForResult(intent, 112);
            }
        });

        btnDel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(context)
                        .setTitle("Hapus "+parcel.getNama()+" ?")
                        .setMessage("Anda yakin akan menghapus "+parcel.getNama()+" ?")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                                mhsDatabase.getMhsDao().delete(parcel.getId());
                                onItemsAction.onItemDeleted(viewMhsItem);
                                //onDeleted.onSucceded(true);
                            }
                        })

                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();


            }
        });
    }

    public void startAnim(){
        int highlightColor = ContextCompat.getColor(getContext(), R.color.purple_200);

        ObjectAnimator highlightNewInsert = ObjectAnimator
                .ofInt(this, "backgroundColor", highlightColor, Color.WHITE)
                .setDuration(5000);
        highlightNewInsert.setEvaluator(new ArgbEvaluator());
        highlightNewInsert.start();
    }

    public interface OnItemsAction{
        void onItemDeleted(ViewMhsItem viewMhsItem);
    }
}
