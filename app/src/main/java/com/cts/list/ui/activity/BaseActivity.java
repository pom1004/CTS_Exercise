package com.cts.list.ui.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.cts.list.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;


public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";
    private AlertDialog alertDialog = null;
    private AlertDialog.Builder dialogBuilde = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }


    public void setDrawble(TextView txtvw, int drawableId) {
        Spannable span = new SpannableString("  " + txtvw.getText());  // or set your text manually
        Drawable drawable;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawable = ContextCompat.getDrawable(getBaseContext(), drawableId);
        } else {
            drawable = AppCompatResources.getDrawable(getBaseContext(), drawableId);
        }

        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }
        ImageSpan imageSpan = new ImageSpan(drawable);
        span.setSpan(imageSpan, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        txtvw.setText(span);
    }


    private void initProgressDialog() {
        if (dialogBuilde == null || alertDialog == null) {
            dialogBuilde = new AlertDialog.Builder(BaseActivity.this);
            LayoutInflater inflater = LayoutInflater.from(BaseActivity.this);
            View dialogView = inflater.inflate(R.layout.dialog_progress, null);


            dialogBuilde.setView(dialogView);
            alertDialog = dialogBuilde.create();
            alertDialog.setCanceledOnTouchOutside(false);

            alertDialog.setOnKeyListener(new Dialog.OnKeyListener() {

                @Override
                public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent event) {
                    return true;
                }
            });
        }
    }

    public void showProgress() {

        if (alertDialog != null) {
            if (!alertDialog.isShowing()) {
                initProgressDialog();
                alertDialog.show();
            }
        } else {
            initProgressDialog();
            alertDialog.show();
        }

    }

    public void hideProgress() {
        initProgressDialog();
        alertDialog.dismiss();
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            if (alertDialog != null) {
                alertDialog.dismiss();
            }
        } catch (Exception e) {
            Log.e(TAG, Log.getStackTraceString(e));
        }

        dialogBuilde = null;
        alertDialog = null;
    }


}
