package com.lena.googletranslateexample;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatePreference extends DialogPreference {
    private final static String delimiter = ".";
    private int lastDay;
    private int lastMonth;
    private int lastYear;
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

        return picker;
    }

    @Override
    protected void onBindDialogView(View v) {
        super.onBindDialogView(v);

        picker.updateDate(lastYear, lastMonth - 1, lastDay);
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);

        if (positiveResult) {
            lastDay = picker.getDayOfMonth();
            lastMonth = picker.getMonth() + 1;
            lastYear = picker.getYear();

            String date = String.valueOf(lastDay) + delimiter + String.valueOf(lastMonth) + delimiter + String.valueOf(lastYear);

            if (!mSummary.equals(date)) {
                persistString(date);
                setSummary(date);
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
            dateValue = getPersistedString((String) defaultValue);
        } else {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat("dd" + delimiter + "MM" + delimiter + "yyyy");
            Date today = calendar.getTime();
            dateValue = format.format(today);
            //get from my_prefs.xml default value
            //dateValue = defaultValue.toString();

        }

        lastDay = getDay(dateValue);
        lastMonth = getMonth(dateValue);
        lastYear = getYear(dateValue);

        String date = String.valueOf(lastDay) + delimiter + String.valueOf(lastMonth) + delimiter + String.valueOf(lastYear);

        setSummary(date);
    }

    /*public void setText(String text) {
        final boolean wasBlocking = shouldDisableDependents();

        dateValue = text;

        persistString(text);

        final boolean isBlocking = shouldDisableDependents();
        if (isBlocking != wasBlocking) {
            notifyDependencyChange(isBlocking);
        }
    }*/

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

    private static int getDay(String date) {
        String[] pieces = date.split("\\" + delimiter);
        return (Integer.parseInt(pieces[0]));
    }

    private static int getMonth(String date) {
        String[] pieces = date.split("\\" + delimiter);
        return (Integer.parseInt(pieces[1]));
    }

    private static int getYear(String date) {
        String[] pieces = date.split("\\" + delimiter);
        return (Integer.parseInt(pieces[2]));
    }
}
