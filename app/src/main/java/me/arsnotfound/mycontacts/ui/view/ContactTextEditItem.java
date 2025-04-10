package me.arsnotfound.mycontacts.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import me.arsnotfound.mycontacts.R;
import me.arsnotfound.mycontacts.databinding.ItemTextEditContactBinding;

public class ContactTextEditItem extends ConstraintLayout {
    private String title;
    private String hint;

    private final ItemTextEditContactBinding binding;

    public ContactTextEditItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        binding = ItemTextEditContactBinding.inflate(LayoutInflater.from(context), this, false);
        addView(binding.getRoot());

        try (TypedArray a = context
                .getTheme()
                .obtainStyledAttributes(attrs, R.styleable.ContactTextEditItem, 0, 0)
        ) {
            title = a.getString(R.styleable.ContactTextEditItem_title);
            hint = a.getString(R.styleable.ContactTextEditItem_hint);
        }

        binding.label.setText(title);
        binding.value.setHint(hint);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        binding.label.setText(title);
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
        binding.value.setText(hint);
    }

    public String getValue() {
        return binding.value.getText().toString();
    }

    public void setValue(String value) {
        binding.value.setText(value);
    }
}
