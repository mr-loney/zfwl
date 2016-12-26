package com.zfwl.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager.BadTokenException;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.zfwl.controls.LightAlertDialog;
import com.zfwl.controls.LightPopDialog;
import com.zfwl.controls.LightPopDialog.PopDialogListener;
import com.zfwl.R;

import java.lang.reflect.Field;

public class ViewHub {

    private static final String TAG = ViewHub.class.getSimpleName();

    /**
     * @description TextView 着色
     * @created 2015-5-1 下午7:15:26
     * @author ZZB
     */
    public static void highlightTextView(Context context, TextView tv, String text, int colorResId, int start, int end) {
        SpannableStringBuilder strBuilder = new SpannableStringBuilder(text);
        strBuilder.setSpan(new ForegroundColorSpan(context.getResources().getColor(colorResId)), start,
                end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(strBuilder);
    }
    /**
     * @description 抖动View
     * @created 2015-3-5 上午11:19:21
     * @author ZZB
     */
    public static void shakeView(View v) {
        TranslateAnimation anim = new TranslateAnimation(0, 10, 0, 0);
        anim.setDuration(100);
        anim.setRepeatCount(3);
        v.startAnimation(anim);
    }

    /**
     * @description TextText的弹窗
     */
    public static void showTextDialog(final Context context, String title,
                                      String msg, final EditDialogListener listener) {

        Builder builder = LightAlertDialog.Builder.create(context);
        // final TextView et = new TextView(context);
        // et.setText(msg);
        builder.setTitle(title).setMessage(msg).setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) {
                            listener.onOkClick(null);
                        }
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * @description TextText的弹窗
     */
    public static void showTextDialog(final Context context, String title,
                                      String msg,String okBtnText,String failBtnText, final EditDialogListener listener) {

        Builder builder = LightAlertDialog.Builder.create(context);
        // final TextView et = new TextView(context);
        // et.setText(msg);
        builder.setTitle(title).setMessage(msg).setNegativeButton("取消", null)
                .setNegativeButton(failBtnText, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (listener != null) {
                                    listener.onNegativecClick();
                                }
                            }
                        })
                .setPositiveButton(okBtnText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) {
                            listener.onOkClick(new EditText(context));
                        }
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private static void addCreditView(Context context, ViewGroup layout, int resId, int count) {
        for (int i = 0; i < count; i++) {
            ImageView iv = new ImageView(context);
            iv.setImageResource(resId);
            layout.addView(iv);
        }
    }

    /**
     * @description 直接中转到微信聊天列表
     * @created 2014-11-26 下午5:19:38
     * @author ZZB
     */
    public static void toWeChatTalkList(Context context) {
        try {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri uri = Uri.parse("weixin://");
            intent.setData(uri);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            showLongToast(context, "该功能需要安装微信才能使用");
        }

    }

    /**
     * @description edit text 获取焦点显示键盘
     * @created 2014-11-11 下午8:36:35
     * @author ZZB
     */
    public static void editTextRequestKeyboard(Context context, EditText et) {
        et.requestFocus();
        et.setSelection(et.getText().toString().length());
        InputMethodManager inputManager = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(et, 0);
    }

    /**
     * @description 显示退出dialog
     * @created 2014-9-25 下午5:07:09
     * @author ZZB
     */
    public static void showExitDialog(final Context context) {
        Builder builder = new Builder(context, AlertDialog.THEME_HOLO_LIGHT);
        // Builder builder = LightAlertDialog.Builder.create(context);
        builder.setTitle("提示").setMessage(context.getString(R.string.main_exit_confirm)).setNegativeButton("否", null)
                .setPositiveButton("退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 不要在这里清空用户数据，不然要重新登录
                        // UserInfoProvider.clearAllUserInfo(context);
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        System.exit(0);
                    }
                });
        builder.show();
    }

    /**
     * @description EditText的弹窗
     * @created 2014-12-15 上午10:28:26
     * @author ZZB
     */
    public static void showEditDialog(final Context context, String title, String editMsg,
            final EditDialogListener listener) {

        // Builder builder = LightAlertDialog.Builder.create(context);
        Builder builder = new Builder(context, AlertDialog.THEME_HOLO_LIGHT);

        final EditText et = new EditText(context);
        et.setBackgroundResource(R.drawable.single_bg);
        et.setText(editMsg);
        builder.setTitle(title).setView(et).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ViewHub.setDialogDismissable(dialog, true);
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (listener != null) {
                    listener.onOkClick(dialog, et);
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                ViewHub.editTextRequestKeyboard(context, et);
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (context instanceof Activity) {
                    hideKeyboard((Activity)context);
                }
            }
        });
        dialog.show();
    }

    public static void showOkDialog(Context context, String title, String msg, String positiveText,
            String negativeText, final OkDialogListener listener) {
        try {
         // Builder builder = LightAlertDialog.Builder.create(context);
            Builder builder = new Builder(context, AlertDialog.THEME_HOLO_LIGHT);

            builder.setTitle(title).setMessage(msg).setNegativeButton(negativeText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    listener.onNegativeClick(dialog, which);
                }
            }).setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    listener.onPositiveClick(dialog, which);
                }
            });
            builder.show();
        } catch (BadTokenException e) {
            e.printStackTrace();
        }

    }

    public static void showOkDialog(Context context, String title, String msg, String positiveText,
            String negativeText, DialogInterface.OnClickListener positiveListener) {
        try {
            Builder builder = new Builder(context, AlertDialog.THEME_HOLO_LIGHT);

            builder.setTitle(title).setMessage(msg).setNegativeButton(negativeText, null)
                    .setPositiveButton(positiveText, positiveListener);
            builder.show();
        } catch (BadTokenException e) {
            e.printStackTrace();
        }
        
    }

    /**
     * @description 显示只有一个按钮的dialog，只做提示信息用
     * @created 2014-9-17 下午2:04:40
     * @author ZZB
     */
    public static void showOkDialog(Context context, String title, String msg, String btnText,
            DialogInterface.OnClickListener listener) {
        try {
          Builder builder = LightAlertDialog.Builder.create(context);

//            android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);
//            builder.setTitle(title).setMessage(msg).setPositiveButton(btnText, listener).create().show();
//            Builder builder = new AlertDialog.Builder(context, AlertDialog.THEME_HOLO_LIGHT);
//
            builder.setTitle(title).setMessage(msg).setPositiveButton(btnText, listener);
            builder.show();
        } catch (BadTokenException e) {
            e.printStackTrace();
        }
        
    }

    /**
     * @description 显示只有一个按钮的dialog，只做提示信息用
     * @created 2014-9-12 上午10:53:28
     * @author ZZB
     */
    public static void showOkDialog(Context context, String title, String msg, String btnText) {
        showOkDialog(context, title, msg, btnText, null);
    }

    /**
     * Description:设置listview gridview的empty view 2014-7-16下午12:01:52
     */
    public static void setEmptyView(Context context, AbsListView view, String emptyText) {
        if (view.getEmptyView() != null) {
            Log.d(TAG, "empty view 已经存在");
            return;
        }
        ViewGroup parentView = (ViewGroup)view.getParent();
        TextView tv = new TextView(context);
        tv.setGravity(Gravity.CENTER);
        tv.setText(emptyText);
        tv.setTextAppearance(context, R.style.myTextAppearance_hint_small);
        if (parentView instanceof RelativeLayout) {
            RelativeLayout.LayoutParams pars = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            pars.addRule(RelativeLayout.CENTER_IN_PARENT);
            parentView.addView(tv, pars);
        } else if (parentView instanceof FrameLayout) {
            FrameLayout.LayoutParams pars = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT);
            pars.gravity = Gravity.CENTER;
            parentView.addView(tv, pars);
        } else if (parentView instanceof LinearLayout) {
            LinearLayout.LayoutParams pars = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            pars.gravity = Gravity.CENTER;
            parentView.addView(tv, pars);
        }
        view.setEmptyView(tv);
    }

    /**
     * 2014-7-28 下午5:25:26
     * 
     * @author ZZB
     */
    public static void showLongToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    /**
     * 2014-7-28 下午5:25:24
     * 
     * @author ZZB
     */
    public static void showShortToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * @description 设置dialog是否可消失
     * @created 2015-3-12 下午6:07:26
     * @author ZZB
     */
    public static void setDialogDismissable(DialogInterface dialog, boolean dismissable) {
        try {
            Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
            field.setAccessible(true);
            field.set(dialog, dismissable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @description 设置EditText的错误信息，黑色背景
     * @time 2014-7-31 下午4:29:22
     * @author ZZB
     */
    public static void setEditError(EditText et, String error) {
        et.setError(Html.fromHtml("<font color='black'>" + error + "</font>"));
        et.requestFocus();
    }

    /**
     * @description 显示键盘
     * @created 2015-1-8 上午10:42:44
     * @author ZZB
     */
    public static void showKeyboard(Activity activity, View view) {
        if (view != null) {
            ((InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(view, 0);;
        }

    }

    /**
     * @description 关闭键盘
     * @created 2014-8-18 上午10:00:07
     * @author ZZB
     */
    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputManager = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        // check if no view has focus:
        View view = activity.getCurrentFocus();
        if (view == null) {
            return;
        }
        inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static void showLightPopDialog(Activity activity, CharSequence title, CharSequence messgae,
                                          CharSequence negative, CharSequence positive, PopDialogListener positiveListener) {
        try {
            LightPopDialog dialog = new LightPopDialog(activity);
            dialog.setTitle(title).setMessage(messgae).setNegative(negative, null).setPositive(positive, positiveListener)
                    .show();
        } catch (BadTokenException e) {
            e.printStackTrace();
        }

    }

    public static interface EditDialogListener {

        public void onOkClick(DialogInterface dialog, EditText editText);

        public void onOkClick(EditText editText);
        public void onNegativecClick();
    }


    public static interface OkDialogListener {
        public void onPositiveClick(DialogInterface dialog, int which);

        public void onNegativeClick(DialogInterface dialog, int which);
    }

    /**
     * @description 自定义退出dialog
     * @created 2015年4月17日 上午10:49:13
     * @author JorsonWong
     */
    public static void showExitLightPopDialog(final Activity activity) {
        try {
            LightPopDialog dialog = new LightPopDialog(activity);
            dialog.setTitle("提示").setMessage(R.string.main_exit_confirm).setNegative("否", null)
                    .setPositive("退出", new PopDialogListener() {
                        @Override
                        public void onPopDialogButtonClick(int which) {
                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            activity.startActivity(intent);
                            System.exit(0);
                        }
                    }).show();
        } catch (BadTokenException e) {
            e.printStackTrace();
        }
        
    }
}
