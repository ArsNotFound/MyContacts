package me.arsnotfound.mycontacts.ui.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import me.arsnotfound.mycontacts.R;
import me.arsnotfound.mycontacts.databinding.ItemInfoContactBinding;

public class ContactInfoItem extends ConstraintLayout {
    private String title;
    private String value;

    private ItemInfoContactBinding binding;

    public ContactInfoItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        binding = ItemInfoContactBinding.inflate(LayoutInflater.from(context), this, false);
        addView(binding.getRoot());

        try (TypedArray a = context
                .getTheme()
                .obtainStyledAttributes(attrs, R.styleable.ContactInfoItem, 0, 0)
        ) {
            title = a.getString(R.styleable.ContactInfoItem_title);
            value = a.getString(R.styleable.ContactInfoItem_value);
        }

        binding.label.setText(title);
        binding.value.setText(value);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        binding.label.setText(title);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        binding.value.setText(value);
    }

}
