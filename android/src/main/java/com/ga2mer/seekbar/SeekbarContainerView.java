package com.ga2mer.seekbar;

import javax.annotation.Nullable;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;

/**
 * Controls an enclosing ProgressBar. Exists so that the ProgressBar can be recreated if
 * the style would change.
 */
/* package */ class SeekbarContainerView extends FrameLayout {
    private RCTEventEmitter mEventEmitter;

    private @Nullable Integer mBackgroundColor;
    private @Nullable Integer mColor;
    private @Nullable Integer mSecondaryColor;
    private @Nullable Integer mThumbColor;
    //private boolean mIndeterminate = true;
    private boolean mAnimating = true;
    private boolean mPressed = false;
    private int mProgress;
    private int mSecondaryProgress;
    private int mMaxProgress;
    private @Nullable SeekBar mSeekBar;

    public SeekbarContainerView(Context context) {
        super(context);
        mEventEmitter = ((ReactContext) context).getJSModule(RCTEventEmitter.class);
    }

    public void setStyle(@Nullable String styleName) {
        int style = RNSeekbarViewManager.getStyleFromString(styleName);
        mSeekBar = RNSeekbarViewManager.createSeekBar(getContext(), style);
        mSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override public void onStartTrackingTouch(SeekBar seekBar) {
                mPressed = true;
                WritableMap event = Arguments.createMap();
                event.putBoolean("pressed", true);
                mEventEmitter.receiveEvent(
                    getId(),
                    "trackingTouch",
                    event);
            }

            @Override public void onStopTrackingTouch(SeekBar seekBar) {
                mPressed = false;
                WritableMap event = Arguments.createMap();
                event.putBoolean("pressed", false);
                mEventEmitter.receiveEvent(
                    getId(),
                    "trackingTouch",
                    event);
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    WritableMap event = Arguments.createMap();
                    event.putInt("progress", progress);
                    //event.putBoolean("fromUser", fromUser);
                    mEventEmitter.receiveEvent(
                        getId(),
                        "topChange",
                        event);
                }
            }
        });
        removeAllViews();
        addView(
            mSeekBar,
            new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
    }
    public void setBGColor(@Nullable Integer color) {
        this.mBackgroundColor = color;
    }
    public void setColor(@Nullable Integer color) {
        this.mColor = color;
    }
    public void setSecondaryColor(@Nullable Integer color) {
        this.mSecondaryColor = color;
    }
    public void setThumbColor(@Nullable Integer color) {
        this.mThumbColor = color;
    }

    public void setProgress(int progress) {
        mProgress = progress;
    }
    public void setSecondaryProgress(int progress) {
        mSecondaryProgress = progress;
    }

    public void setMax(int max) {
        mMaxProgress = max;
    }

    public void setAnimating(boolean animating) {
        mAnimating = animating;
    }

    public void apply() {
        if (mSeekBar == null) {
            throw new JSApplicationIllegalArgumentException("setStyle() not called");
        }

        setBGColor(mSeekBar);
        setColor(mSeekBar);
        setSecondaryColor(mSeekBar);
        setThumbColor(mSeekBar);
        if (!mPressed) {
            mSeekBar.setProgress(mProgress);
        }
        mSeekBar.setSecondaryProgress(mSecondaryProgress);
        mSeekBar.setMax(mMaxProgress);
    }
    private void setBGColor(SeekBar mSeekBar) {
        Drawable drawable = ((LayerDrawable) mSeekBar.getProgressDrawable()).getDrawable(0);
        if (drawable == null) {
            return;
        }

        if (mBackgroundColor != null) {
            drawable.setColorFilter(mBackgroundColor, PorterDuff.Mode.SRC_IN);
        } else {
            drawable.clearColorFilter();
        }
    }
    private void setColor(SeekBar mSeekBar) {
        Drawable drawable = ((LayerDrawable) mSeekBar.getProgressDrawable()).getDrawable(2);

        if (drawable == null) {
            return;
        }

        if (mColor != null) {
            drawable.setColorFilter(mColor, PorterDuff.Mode.SRC_IN);
        } else {
            drawable.clearColorFilter();
        }
    }
    private void setSecondaryColor(SeekBar mSeekBar) {
        Drawable drawable = ((LayerDrawable) mSeekBar.getProgressDrawable()).getDrawable(1);

        if (drawable == null) {
            return;
        }

        if (mSecondaryColor != null) {
            drawable.setColorFilter(mSecondaryColor, PorterDuff.Mode.SRC_IN);
        } else {
            drawable.clearColorFilter();
        }
    }
    private void setThumbColor(SeekBar mSeekBar) {
        Drawable drawable = mSeekBar.getThumb();
        if (drawable == null) {
            return;
        }

        if (mThumbColor != null) {
            drawable.setColorFilter(mThumbColor, PorterDuff.Mode.SRC_IN);
        } else {
            drawable.clearColorFilter();
        }
    }
}
