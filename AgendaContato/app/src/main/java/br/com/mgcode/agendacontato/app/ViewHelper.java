package br.com.mgcode.agendacontato.app;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by MarcoGorak on 26/12/15.
 */
public class ViewHelper {

    public static ArrayAdapter<String> createArrayAdapter (Context ctx, Spinner spinner) {

        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);

        return arrayAdapter;
    }
}
