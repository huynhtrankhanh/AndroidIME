package com.example.androidime

import android.graphics.Color
import android.inputmethodservice.InputMethodService
import android.view.KeyEvent
import android.view.View
import android.widget.FrameLayout
import java.util.Locale

class UppercaseImeService : InputMethodService() {

    override fun onCreateInputView(): View {
        val root = FrameLayout(this)
        val heightPx = (220 * resources.displayMetrics.density).toInt()
        root.layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            heightPx
        )
        root.setBackgroundColor(Color.TRANSPARENT)
        root.addView(
            RedCircleView(this),
            FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
        )
        return root
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        val inputConnection = currentInputConnection ?: return super.onKeyDown(keyCode, event)

        when (keyCode) {
            KeyEvent.KEYCODE_DEL -> {
                inputConnection.deleteSurroundingText(1, 0)
                return true
            }

            KeyEvent.KEYCODE_ENTER -> {
                inputConnection.commitText("\n", 1)
                return true
            }
        }

        val unicodeChar = event.unicodeChar
        if (unicodeChar != 0 && !Character.isISOControl(unicodeChar)) {
            val typed = String(Character.toChars(unicodeChar))
            inputConnection.commitText(typed.uppercase(Locale.getDefault()), 1)
            return true
        }

        return super.onKeyDown(keyCode, event)
    }
}
