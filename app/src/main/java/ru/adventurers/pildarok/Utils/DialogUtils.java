package ru.adventurers.pildarok.Utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import ru.adventurers.pildarok.R;
import ru.adventurers.pildarok.listeners.OnDialogOk;
import ru.adventurers.pildarok.listeners.OnDialogOkCancel;


/**
 * Created by Дмитрий on 27.11.2015.
 */
public class DialogUtils {
    private static final String LOG_TAG = DialogUtils.class.getSimpleName();

    public static Dialog showProgressDialog(@NonNull Context context) {
        Dialog dialog = new Dialog(context);

            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.CENTER);
        ProgressBar progressBar = new ProgressBar(context);
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.progress_dialog, null);
        dialog.setCancelable(false);
        dialog.addContentView(view, new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        dialog.show();
        return dialog;
    }


    /**
     * Диалог ок
     * @param context
     * @param message
     * @param listener
     * @return
     */
    public static AlertDialog showAlertDialog(@NonNull Context context, @Nullable String message,@Nullable final OnDialogOk listener) {

        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(R.string.app_name)
                .setMessage(message)
                .setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if(listener!=null) listener.onOk();
                    }
                })
                .create();
        dialog.show();
        return dialog;
    }

    /**
     * Диалог ок-отмена
     * @param context
     * @param message
     * @param listener
     * @return
     */
    public static AlertDialog showAlertDialog2(@NonNull Context context, @Nullable String message,@Nullable final OnDialogOkCancel listener) {

        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(R.string.app_name)
                .setMessage(message)
                .setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (listener != null) listener.onOk();
                    }
                })
                .setNegativeButton(R.string.dialog_no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (listener != null) listener.onCancel();
                    }
                })
                .create();
        dialog.show();
        return dialog;
    }


}
