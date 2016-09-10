package io.github.aoemerson.riapidevchallenge.view.detail;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import aoemerson.github.io.riapidevchallenge.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RibotPropertyView extends RelativeLayout {

    @BindView(R.id.icon) ImageView iconView;
    @BindView(R.id.text_detail) TextView textView;
    @BindView(R.id.separator) View separatorView;

    public RibotPropertyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_ribot_property_view, this);
        ButterKnife.bind(this);
    }

    public RibotPropertyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RibotPropertyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);

    }

    public RibotPropertyView(Context context) {
        super(context);
        init(context);
    }

    public void setText(CharSequence text) {
        textView.setText(text);
    }

    public void setIconResource(int resId) {
        iconView.setImageResource(resId);
    }

    public void setSeparatorVisibility(boolean visible) {
        separatorView.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
}
