package com.lena.googletranslateexample;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;

public class ExitFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.exit_dialog_title)
                .setMessage(R.string.exit_dialog_text)
                .setPositiveButton(R.string.no_button_text, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dismiss();
                    }
                })
                .setNegativeButton(R.string.yes_button_text, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getActivity().finish();
                    }
                });

        builder.setInverseBackgroundForced(true);
        AlertDialog dialog = builder.create();

// Make some UI changes for AlertDialog
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialog) {

                // Add or create your own background drawable for AlertDialog window
                Window view = ((AlertDialog) dialog).getWindow();

                // Customize POSITIVE, NEGATIVE and NEUTRAL buttons.
                Button positiveButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE);
                positiveButton.setBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimary));
                //positiveButton.setBackgroundColor(Typeface.DEFAULT_BOLD);
                positiveButton.invalidate();

                Button negativeButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_NEGATIVE);
                negativeButton.setBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimary));
                negativeButton.setTypeface(Typeface.DEFAULT_BOLD);
                negativeButton.invalidate();
            }
        });


        return dialog;
    }
}