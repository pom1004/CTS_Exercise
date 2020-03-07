package com.cts.list.ui.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.cts.list.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";
    private AlertDialog alertDialog = null;
    private AlertDialog.Builder dialogBuilde = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
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
