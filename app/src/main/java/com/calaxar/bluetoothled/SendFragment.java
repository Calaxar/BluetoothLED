package com.calaxar.bluetoothled;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.Color;

/**
 * Created by Calum on 23/12/2017.
 */

public class SendFragment extends Fragment {
    private static final String TAG = "SendFragment";

    private TextView colorView;
    private Button hexButton;
    private Button rgbButton;
    private Button sendButton;
    private LinearLayout hexLayout;
    private LinearLayout rgbLayout;
    private EditText hexEditText;
    private EditText redEditText;
    private EditText greenEditText;
    private EditText blueEditText;
    private int red = 0;
    private int green = 0;
    private int blue = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //inflate view for fragment
        View myFragmentView = inflater.inflate(R.layout.fragment_send, container, false);
        return myFragmentView;
    }

    @Override
    public void onStart() {
        colorView = getView().findViewById(R.id.send_colour_preview);
        hexButton = getView().findViewById(R.id.hex_button);
        rgbButton = getView().findViewById(R.id.rgb_button);
        sendButton = getView().findViewById(R.id.send_button);
        hexLayout = getView().findViewById(R.id.hex_input_view);
        rgbLayout = getView().findViewById(R.id.rgb_input_layout);
        hexEditText = getView().findViewById(R.id.hex_input);
        redEditText = getView().findViewById(R.id.red_input);
        greenEditText = getView().findViewById(R.id.green_input);
        blueEditText = getView().findViewById(R.id.blue_input);

        hexButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rgbButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                rgbButton.setEnabled(true);
                rgbLayout.setVisibility(View.INVISIBLE);
                hexButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                hexButton.setEnabled(false);
                hexLayout.setVisibility(View.VISIBLE);
            }
        });

        rgbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hexButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                hexButton.setEnabled(true);
                hexLayout.setVisibility(View.INVISIBLE);
                rgbButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                rgbButton.setEnabled(false);
                rgbLayout.setVisibility(View.VISIBLE);
            }
        });

        hexEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 6) {
                    int colours[] = getRGB(charSequence.toString());
                    red = colours[0];
                    green = colours[1];
                    blue = colours[2];
                    updatePreviewColour(red, green, blue);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        redEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    red = Integer.parseInt(charSequence.toString());
                    if (red > 255) red = 255;
                    updatePreviewColour(red, green, blue);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        greenEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    green = Integer.parseInt(charSequence.toString());
                    if (green > 255) green = 255;
                    updatePreviewColour(red, green, blue);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        blueEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    blue = Integer.parseInt(charSequence.toString());
                    if (blue > 255) blue = 255;
                    updatePreviewColour(red, green, blue);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.sendColor(red, green, blue);
            }
        });

        super.onStart();
    }

    private int[] getRGB(final String rgb)
    {
        int r = Integer.parseInt(rgb.substring(0, 2), 16); // 16 for hex
        int g = Integer.parseInt(rgb.substring(2, 4), 16); // 16 for hex
        int b = Integer.parseInt(rgb.substring(4, 6), 16); // 16 for hex
        return new int[] {r, g, b};
    }

    private void updatePreviewColour(int r, int g, int b) {
        int color = Color.rgb(r, g, b);
        colorView.setBackgroundColor(color);
    }
}
