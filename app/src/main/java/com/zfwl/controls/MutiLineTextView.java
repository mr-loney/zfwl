package com.zfwl.controls;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zfwl.R;

public class MutiLineTextView extends LinearLayout {

    private View mRootView,lineT,lineC,lineE;
    private TextView mtv,mtv2;
    private EditText met;
    private boolean   bt = false,bc = false,be = false,bMore = false;
    private String strTitle = "";

    public MutiLineTextView(Context context) {
        super(context);
        initView();
        setEditing(false);
    }

    public MutiLineTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        setEditing(false);
        readAttrs(attrs);
    }

    public void setTitle(String t) {
        mtv.setText(t);
    }

    public void setEditing(boolean b) {
        mtv2.setVisibility(b? View.GONE:VISIBLE);
        met.setVisibility(b? View.VISIBLE:GONE);
    }

    public void setDetail(String t) {
        mtv2.setText(t);
        met.setText(t);
    }
    public String getDetail() {
        return met.getText().toString();
    }

    private void initView() {
        mRootView = LayoutInflater.from(getContext()).inflate(R.layout.widget_mutiline_text_view, this);
        mtv = (TextView) mRootView.findViewById(R.id.txt1);
        met = (EditText) mRootView.findViewById(R.id.et1);
        mtv2 = (TextView) mRootView.findViewById(R.id.txt2);
        lineT = mRootView.findViewById(R.id.lineT);
        lineC = mRootView.findViewById(R.id.lineC);
        lineE = mRootView.findViewById(R.id.lineE);
    }

    private void readAttrs(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.MutiLineTextView);
        bt = ta.getBoolean(R.styleable.MutiLineTextView_mutilinet,false);
        bc = ta.getBoolean(R.styleable.MutiLineTextView_mutilinec,false);
        be = ta.getBoolean(R.styleable.MutiLineTextView_mutilinee,false);
        strTitle = ta.getString(R.styleable.MutiLineTextView_mutinormaltitle);
        bMore = ta.getBoolean(R.styleable.MutiLineTextView_mutishowmore,false);
        ta.recycle();

        setTitle(strTitle);
        lineT.setVisibility(bt? View.VISIBLE:GONE);
        lineC.setVisibility(bc? View.VISIBLE:GONE);
        lineE.setVisibility(be? View.VISIBLE:GONE);
    }
}
