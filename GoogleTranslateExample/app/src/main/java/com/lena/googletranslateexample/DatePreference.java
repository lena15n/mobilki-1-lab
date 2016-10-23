package com.lena.googletranslateexample;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DatePreference extends DialogPreference {
    private final static String delimiter = "-";
    private int lastDate = 0;
    private int lastMonth = 0;
    private int lastYear = 0;
    private String dateValue;
    private CharSequence mSummary;
    private DatePicker picker = null;

    public DatePreference(Context context, AttributeSet attrs) {
        super(context, attrs);

        setPositiveButtonText(R.string.ok_button_text);
        setNegativeButtonText(R.string.no_button_text);
    }

    @Override
    protected View onCreateDialogView() {
        picker = new DatePicker(getContext());
        picker.setCalendarViewShown(false);

        return (picker);
    }

    @Override
    protected void onBindDialogView(View v) {
        super.onBindDialogView(v);

        picker.updateDate(lastYear, lastMonth + 1, lastDate);
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);

        if (positiveResult) {
            lastYear = picker.getYear();
            lastMonth = picker.getMonth();
            lastDate = picker.getDayOfMonth();

            String date = String.valueOf(lastYear) + delimiter
                    + String.valueOf(lastMonth) + delimiter
                    + String.valueOf(lastDate);

            if (callChangeListener(date)) {
                persistString(date);
            }
        }
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return (a.getString(index));
    }

    @Override
    protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
        dateValue = null;

        if (restoreValue) {
            if (defaultValue == null) {
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy" + delimiter + "MM" + delimiter + "dd", Locale.US);
                String formatted = format1.format(cal.getTime());
                dateValue = getPersistedString(formatted);
            } else {
                dateValue = getPersistedString(defaultValue.toString());
            }
        } else {
            dateValue = defaultValue.toString();
        }

        lastYear = getYear(dateValue);
        lastMonth = getMonth(dateValue);
        lastDate = getDate(dateValue);
    }

    public void setText(String text) {
        final boolean wasBlocking = shouldDisableDependents();

        dateValue = text;

        persistString(text);

        final boolean isBlocking = shouldDisableDependents();
        if (isBlocking != wasBlocking) {
            notifyDependencyChange(isBlocking);
        }
    }

    public String getText() {
        return dateValue;
    }

    public CharSequence getSummary() {
        return mSummary;
    }

    public void setSummary(CharSequence summary) {
        if (summary == null && mSummary != null || summary != null
                && !summary.equals(mSummary)) {
            mSummary = summary;
            notifyChanged();
        }
    }

    private static int getYear(String date) {
        String[] pieces = date.split(delimiter);
        return (Integer.parseInt(pieces[0]));
    }

    private static int getMonth(String date) {
        String[] pieces = date.split(delimiter);
        return (Integer.parseInt(pieces[1]));
    }

    private static int getDate(String date) {
        String[] pieces = date.split(delimiter);
        return (Integer.parseInt(pieces[2]));
    }
}
