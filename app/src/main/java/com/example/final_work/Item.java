package com.example.final_work;

import android.widget.ImageView;
import android.widget.TextView;

import org.litepal.crud.LitePalSupport;


public class Item  {

    private int imageId;
    private String typeMaterialId;
    private String typeClassId;

    void setImageId(int imageId) {
        this.imageId = imageId;
    }

    void setTypeMaterialId(String typeMaterialId) {
        this.typeMaterialId = typeMaterialId;
    }

    void setTypeClassId(String typeClassId) {
        this.typeClassId = typeClassId;
    }

    int getImageId() {
        return this.imageId;
    }

    String getTypeMaterialId() {
        return this.typeMaterialId;
    }

    String getTypeClassId() {
        return this.typeClassId;
    }

}
