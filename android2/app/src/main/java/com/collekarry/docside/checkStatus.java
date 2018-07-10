package com.collekarry.docside;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class checkStatus extends Fragment
{
    EditText morContent,noonContent,eveContent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.check_status, container, false);

        morContent = (EditText) rootView.findViewById(R.id.morngContent);
        noonContent = (EditText) rootView.findViewById(R.id.afternoonContent);
        eveContent = (EditText) rootView.findViewById(R.id.evenContent);

        return rootView;
    }
}
