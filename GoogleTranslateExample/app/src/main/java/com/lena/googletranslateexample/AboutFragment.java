package com.lena.googletranslateexample;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

public class AboutFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        StyleAlertDialog adb = new StyleAlertDialog(getActivity(), R.style.AlertDialogCustom);
        adb.setView(getActivity().getLayoutInflater().inflate(R.layout.fragment_about, null));
        adb.setTitle("Aboutt");
        adb.setButton(AlertDialog.BUTTON_POSITIVE, "Okk", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });
                //adb.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return new android.support.v7.app.AlertDialog.Builder(getActivity(), R.style.AlertDialogCustom).show();
    }

    private class StyleAlertDialog extends AlertDialog {
        StyleAlertDialog(Context context, int theme) {
            super(context, theme);
        }
    }
}