package com.shinian.pay.util;

import android.content.Context;
import android.widget.Toast;

/** Central Toast gate for the user-controlled do-not-disturb mode. */
public final class AppToast {
    private static final String PREFS = "ui_settings";
    private static final String QUIET_MODE = "quiet_mode_enabled";

    private AppToast() {
    }

    public static Toast makeText(Context context, CharSequence text, int duration) {
        if (context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .getBoolean(QUIET_MODE, false)) {
            return new SilentToast(context.getApplicationContext());
        }
        return Toast.makeText(context, text, duration);
    }

    public static Toast makeText(Context context, int textResourceId, int duration) {
        return makeText(context, context.getText(textResourceId), duration);
    }

    private static final class SilentToast extends Toast {
        SilentToast(Context context) {
            super(context);
        }

        @Override
        public void show() {
            // Intentionally suppressed while do-not-disturb mode is enabled.
        }
    }
}
