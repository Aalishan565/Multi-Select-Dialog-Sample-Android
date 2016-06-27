package com.multiselectdialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by aalishan on 27/1/16.
 */
public class CustomDialogFragment extends DialogFragment {
    String[] fruits;
    ArrayList<String> list;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String[] fruitsItems = getResources().getStringArray(R.array.fruit_list);
        list = new ArrayList<String>();
        fruits = getResources().getStringArray(R.array.fruit_list);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.select_fruits);

        builder.setMultiChoiceItems(fruits, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {

                    list.add(fruitsItems[which]);
                } else if (list.contains(fruitsItems[which])) {
                    list.remove(fruitsItems[which]);
                }
            }
        });
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String selection = "";
                for (String multiselectedItem : list) {
                    selection = selection + "\n" + multiselectedItem;
                }
                Toast.makeText(getActivity(), "Selected fruits:" + selection, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        return builder.create();
    }
}

