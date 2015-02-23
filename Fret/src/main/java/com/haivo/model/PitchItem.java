package com.haivo.model;

import com.haivo.R;
import com.haivo.hailibrary.util.ResHelper;

/**
 * Created by hvo on 2/22/15.
 */
public enum PitchItem {

    FIRST_E(R.string.FIRST_E, R.drawable.n40, 40),
    FIRST_F(R.string.FIRST_F, R.drawable.n41, 41),
    FIRST_F_SHARP(R.string.FIRST_F_SHARP, R.drawable.n42, 42),
    FIRST_G(R.string.FIRST_G, R.drawable.n43, 43),
    FIRST_G_SHARP(R.string.FIRST_G_SHARP, R.drawable.n44, 44),
    FIRST_A(R.string.FIRST_A, R.drawable.n45, 45),
    FIRST_A_SHARP(R.string.FIRST_A_SHARP, R.drawable.n46, 46),
    FIRST_B(R.string.FIRST_B, R.drawable.n47, 47),
    FIRST_C(R.string.FIRST_C, R.drawable.n48, 48),
    FIRST_C_SHARP(R.string.FIRST_C_SHARP, R.drawable.n49, 49),
    FIRST_D(R.string.FIRST_D, R.drawable.n50, 50),
    FIRST_D_SHARP(R.string.FIRST_D_SHARP, R.drawable.n51, 51),
    SECOND_E(R.string.SECOND_E, R.drawable.n52, 52),
    SECOND_F(R.string.SECOND_F, R.drawable.n53, 53),
    SECOND_F_SHARP(R.string.SECOND_F_SHARP, R.drawable.n54, 54),
    SECOND_G(R.string.SECOND_G, R.drawable.n55, 55),
    SECOND_G_SHARP(R.string.SECOND_G_SHARP, R.drawable.n56, 56),
    SECOND_A(R.string.SECOND_A, R.drawable.n57, 57),
    SECOND_A_SHARP(R.string.SECOND_A_SHARP, R.drawable.n58, 58),
    SECOND_B(R.string.SECOND_B, R.drawable.n59, 59),
    SECOND_C(R.string.SECOND_C, R.drawable.n60, 60),
    SECOND_C_SHARP(R.string.SECOND_C_SHARP, R.drawable.n61, 61),
    SECOND_D(R.string.SECOND_D, R.drawable.n62, 62),
    SECOND_D_SHARP(R.string.SECOND_D_SHARP, R.drawable.n63, 63),
    THIRD_E(R.string.THIRD_E, R.drawable.n64, 64),
    THIRD_F(R.string.THIRD_F, R.drawable.n65, 65),
    THIRD_F_SHARP(R.string.THIRD_F_SHARP, R.drawable.n66, 66),
    THIRD_G(R.string.THIRD_G, R.drawable.n67, 67),
    THIRD_G_SHARP(R.string.THIRD_G_SHARP, R.drawable.n68, 68);

    public String mTitle;
    public int mFrequency;
    public int mImageResId;


    PitchItem(int titleResId, int drawableIconResId, int frequency) {
        mTitle = ResHelper.getStringByResId(titleResId);
        this.mFrequency = frequency;
        this.mImageResId = drawableIconResId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public int getFrequency() {
        return mFrequency;
    }

    public void setImageResId(int imageResId) {
        this.mImageResId = imageResId;
    }

    public int getImageResId() {
        return mImageResId;
    }

    public void setFrequency(int frequency) {
        this.mFrequency = frequency;
    }
}
