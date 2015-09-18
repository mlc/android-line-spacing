package com.meetup.spacingtester;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.OnRebindCallback;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.meetup.spacingtester.databinding.FontItemBinding;
import com.scopely.fontain.Fontain;
import com.scopely.fontain.interfaces.Font;
import com.scopely.fontain.interfaces.FontFamily;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class FontAdapter extends ArrayAdapter<Font> {
    SizeInfo sizes;

    public FontAdapter(Context context, SizeInfo sizes) {
        super(context, 0, collectAllFonts());
        this.sizes = sizes;
    }

    private static List<Font> collectAllFonts() {
        ArrayList<Font> fonts = new ArrayList<>();
        for (FontFamily family : Fontain.getFontManager().getAllFontFamilies()) {
            fonts.addAll(family.getFonts());
        }
        final Collator familyCompare = Collator.getInstance(Locale.getDefault());
        Collections.sort(fonts, new Comparator<Font>() {
            @Override
            public int compare(Font lhs, Font rhs) {
                int result = familyCompare.compare(lhs.getFamily().getName(), rhs.getFamily().getName());
                if (result == 0)
                    result = Integer.compare(lhs.getWeight(), rhs.getWeight());
                if (result == 0)
                    result = Boolean.compare(lhs.getSlope(), rhs.getSlope());
                return result;
            }
        });
        return fonts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final FontItemBinding binding;
        final View view;
        if (convertView == null) {
            binding = FontItemBinding.inflate(LayoutInflater.from(getContext()), parent, false);
            view = binding.getRoot();
            binding.addOnRebindCallback(new OnRebindCallback<FontItemBinding>() {
                @Override
                public void onBound(FontItemBinding b) {
                    b.metrics.setText(fontMetricsToString(b.swatch.getPaint().getFontMetrics()));
                }
            });
        } else {
            binding = DataBindingUtil.getBinding(convertView);
            view = convertView;
        }
        binding.setSizes(sizes);
        binding.setFont(getItem(position));
        return view;
    }

    static String fontMetricsToString(Paint.FontMetrics fm) {
        return "top:\u00a0" + fm.top +
                ", ascent:\u00a0" + fm.ascent +
                ", descent:\u00a0" + fm.descent +
                ", bottom:\u00a0" + fm.bottom +
                ", leading:\u00a0" + fm.leading;
    }

    @BindingAdapter("bind:font")
    public static void setFont(TextView view, Font font) {
        Fontain.applyFontToViewHierarchy(view, font);
    }
}
