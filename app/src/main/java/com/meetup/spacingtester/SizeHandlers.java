package com.meetup.spacingtester;

import android.view.View;

public interface SizeHandlers {
    void plusTextSize(View v);

    void minusTextSize(View v);

    void plusSpacingMultiplier(View v);

    void minusSpacingMultiplier(View v);

    void plusSpacingExtra(View v);

    void minusSpacingExtra(View v);
}
