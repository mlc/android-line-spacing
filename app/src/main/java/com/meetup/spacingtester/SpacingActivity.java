package com.meetup.spacingtester;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.meetup.spacingtester.databinding.ActivitySpacingBinding;

public class SpacingActivity extends Activity implements SizeHandlers {
    private SizeInfo sizeInfo;
    private ListView list;
    private FontAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySpacingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_spacing);
        if (savedInstanceState != null)
            sizeInfo = savedInstanceState.getParcelable("size_info");
        if (sizeInfo == null)
            sizeInfo = new SizeInfo(this);
        binding.setSizes(sizeInfo);
        binding.setHandlers(this);

        list = (ListView)findViewById(android.R.id.list);
        adapter = new FontAdapter(this, sizeInfo);
        list.setAdapter(adapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("size_info", sizeInfo);
    }

    @Override
    public void plusTextSize(View v) {
        sizeInfo.setTextSize(sizeInfo.getTextSize() + 1.0f);
    }

    @Override
    public void minusTextSize(View v) {
        sizeInfo.setTextSize(sizeInfo.getTextSize() - 1.0f);
    }

    @Override
    public void plusSpacingMultiplier(View v) {
        sizeInfo.setLineSpacingMultiplier(sizeInfo.getLineSpacingMultiplier() + 0.1f);
    }

    @Override
    public void minusSpacingMultiplier(View v) {
        sizeInfo.setLineSpacingMultiplier(sizeInfo.getLineSpacingMultiplier() - 0.1f);
    }

    @Override
    public void plusSpacingExtra(View v) {
        sizeInfo.setLineSpacingExtra(sizeInfo.getLineSpacingExtra() + 1.0f);
    }

    @Override
    public void minusSpacingExtra(View v) {
        sizeInfo.setLineSpacingExtra(sizeInfo.getLineSpacingExtra() - 1.0f);
    }
}
