package com.adrian.automat.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;

import com.adrian.automat.R;
import com.adrian.automat.tools.CommUtil;

/**
 * 音量调节对话框
 * Created by adrian on 17-6-14.
 */

public class VolumnDialog extends Dialog implements View.OnClickListener {

    private Context context;

    private ImageButton mCloseIB;
    private SeekBar mVolumnSB;
    private Button mCancelBtn;
    private Button mConfirmBtn;

    private int cur;
    private int max;

    private AudioManager audioManager;

    public VolumnDialog(@NonNull Context context) {
        super(context, R.style.Dialog);
        this.context = context;
    }

    public VolumnDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    protected VolumnDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_volumn_dialog);

        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        max = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        cur = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        CommUtil.logE("VOLUMN", "max:" + max + " cur:" + cur);

        mCloseIB = (ImageButton) findViewById(R.id.ib_close);
        mVolumnSB = (SeekBar) findViewById(R.id.sb_volumn);
        mCancelBtn = (Button) findViewById(R.id.btn_cancel);
        mConfirmBtn = (Button) findViewById(R.id.btn_confirm);

        mCancelBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        mCloseIB.setOnClickListener(this);
        mVolumnSB.setMax(max);
        mVolumnSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setVolumn(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        mVolumnSB.setProgress(cur);
    }

    @Override
    public void setOnShowListener(@Nullable OnShowListener listener) {
        super.setOnShowListener(listener);
        CommUtil.logE("VOLUMN", "show");
        cur = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
//        mVolumnSB.setMax(max);
        mVolumnSB.setProgress(cur);
    }

    /**
     * 设置音量值
     *
     * @param value
     */
    private void setVolumn(int value) {
        CommUtil.logE("VOLUMN", "value:" + value + " cur:" + audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, value, AudioManager.FLAG_PLAY_SOUND);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_close:
                dismiss();
                break;
            case R.id.btn_cancel:
                setVolumn(cur);
                dismiss();
                break;
            case R.id.btn_confirm:
                dismiss();
                break;
        }
    }
}
