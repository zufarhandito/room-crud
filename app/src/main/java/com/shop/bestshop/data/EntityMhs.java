package com.shop.bestshop.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class EntityMhs {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "nama")
    private String nama;
    @ColumnInfo(name = "alamat")
    private String alamat;
    @ColumnInfo(name = "hp")
    private String hp;
    @ColumnInfo(name = "photo")
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public MhsParcel toParcel() {
        MhsParcel mhsParcel = new MhsParcel();
        mhsParcel.setAlamat(alamat);
        mhsParcel.setNama(nama);
        mhsParcel.setId(id);
        mhsParcel.setHp(hp);
        mhsParcel.setPhoto(photo);
        return mhsParcel;
    }
}
