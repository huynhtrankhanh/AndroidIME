package com.example.androidime;

import android.graphics.Color;
import android.inputmethodservice.InputMethodService;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import java.util.Locale;

public class UppercaseImeService extends InputMethodService {
    private static final int IME_HEIGHT_DP = 220;

    @Override
    public View onCreateInputView() {
        FrameLayout root = new FrameLayout(this);
        int heightPx = (int) (IME_HEIGHT_DP * getResources().getDisplayMetrics().density);
        root.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                heightPx
        ));
        root.setBackgroundColor(Color.TRANSPARENT);
        root.addView(new RedCircleView(this), new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
        ));
        return root;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (getCurrentInputConnection() == null) {
            return super.onKeyDown(keyCode, event);
        }

        if (keyCode == KeyEvent.KEYCODE_DEL) {
            getCurrentInputConnection().deleteSurroundingText(1, 0);
            return true;
        }

        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            getCurrentInputConnection().commitText("\n", 1);
            return true;
        }

        int unicodeChar = event.getUnicodeChar();
        if (unicodeChar != 0 && !Character.isISOControl(unicodeChar)) {
            String typed = new String(Character.toChars(unicodeChar));
            getCurrentInputConnection().commitText(typed.toUpperCase(Locale.getDefault()), 1);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
