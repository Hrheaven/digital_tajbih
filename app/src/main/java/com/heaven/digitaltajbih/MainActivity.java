package com.heaven.digitaltajbih;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView display;
    ImageView plus, reset, minus;
    int count = 0; // Declare count as a member variable
    Vibrator vibrator; // Declare a Vibrator object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        display = findViewById(R.id.display);
        plus = findViewById(R.id.plus);
        reset = findViewById(R.id.reset);
        minus = findViewById(R.id.minus);

        // Initialize the Vibrator object
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = count + 1;
                display.setText(String.valueOf(count));
                vibratePhone();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 0;
                display.setText(String.valueOf(count));
                vibratePhone();
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count > 0) {
                    count = count - 1;
                }
                display.setText(String.valueOf(count));
                vibratePhone();
            }
        });
    }

    // Method to vibrate the phone
    private void vibratePhone() {
        if (vibrator != null) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                vibrator.vibrate(100);
            }
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Exit APP!")
                .setMessage("Do you Really want to Exit?")
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // If the user clicked "No", dismiss the dialog and do nothing
                        dialog.dismiss();
                    }
                })

                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // If the user clicked "Yes", close the activity and exit the app
                        dialog.dismiss();
                        finishAndRemoveTask();
                    }
                })
                .show();



    }
}
