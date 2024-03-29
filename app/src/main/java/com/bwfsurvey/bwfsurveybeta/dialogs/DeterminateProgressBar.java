package com.bwfsurvey.bwfsurveybeta.dialogs;

import android.os.CountDownTimer;

public class DeterminateProgressBar extends CountDownTimer {

    public DeterminateProgressBar(int secs, DeterminateProgressBar.DeterminateProgressBarListener listener) {
        super((long)1000*secs, 1000);
        this.listener = listener;
    }

    @Override
    public void onTick(long millisUntilFinished) {

    }

    @Override
    public void onFinish() {
        listener.onPregressFinished();
    }

    public interface DeterminateProgressBarListener {
        public void onPregressFinished();
    }

    DeterminateProgressBar.DeterminateProgressBarListener listener;

}
