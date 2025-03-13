package me.arsnotfound.mycontacts.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import me.arsnotfound.mycontacts.databinding.ItemInfoContactBinding;

public class ContactInfoItem extends ConstraintLayout {
    ItemInfoContactBinding binding;

    public ContactInfoItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        binding = ItemInfoContactBinding.inflate(LayoutInflater.from(context), this, false);
        addView(binding.getRoot());
    }
}
