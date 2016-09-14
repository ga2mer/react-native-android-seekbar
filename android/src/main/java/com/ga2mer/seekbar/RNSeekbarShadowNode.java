package com.ga2mer.seekbar;

import javax.annotation.Nullable;

import java.util.HashSet;
import java.util.Set;

import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.facebook.csslayout.CSSMeasureMode;
import com.facebook.csslayout.CSSNodeAPI;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.csslayout.MeasureOutput;
import com.facebook.react.uimanager.LayoutShadowNode;

/**
 * Node responsible for holding the style of the ProgressBar, see under
 * {@link android.R.attr.progressBarStyle} for possible styles. ReactProgressBarViewManager
 * manages how this style is applied to the ProgressBar.
 */
public class RNSeekbarShadowNode extends LayoutShadowNode implements CSSNodeAPI.MeasureFunction {
        private String mStyle = "Normal";

        private final SparseIntArray mHeight = new SparseIntArray();
        private final SparseIntArray mWidth = new SparseIntArray();
        private final Set<Integer> mMeasured = new HashSet<>();


        public RNSeekbarShadowNode() {
                setMeasureFunction(this);
        }
        public @Nullable String getStyle() {
                return mStyle;
        }
        @Override
        public void measure(CSSNodeAPI node, float width, CSSMeasureMode widthMode, float height, CSSMeasureMode heightMode, MeasureOutput measureOutput) {
                final int style = RNSeekbarViewManager.getStyleFromString(getStyle());
                if (!mMeasured.contains(style)) {
                        SeekBar seekBar = RNSeekbarViewManager.createSeekBar(getThemedContext(), style);
                        final int spec = View.MeasureSpec.makeMeasureSpec(
                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                View.MeasureSpec.UNSPECIFIED);
                        seekBar.measure(spec, spec);
                        mHeight.put(style, seekBar.getMeasuredHeight());
                        mWidth.put(style, seekBar.getMeasuredWidth());
                        mMeasured.add(style);
                }

                measureOutput.height = mHeight.get(style);
                measureOutput.width = mWidth.get(style);
        }
}
