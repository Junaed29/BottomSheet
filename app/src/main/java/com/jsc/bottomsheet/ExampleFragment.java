package com.jsc.bottomsheet;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class ExampleFragment extends Fragment  {

    ImageView arrowImageView;
    TextView statusTextView;
    Button changeStatusButton;
    BottomSheetBehavior bottomSheetBehavior;

    public ExampleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_example, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button = view.findViewById(R.id.btnProcessPayment);
        arrowImageView = view.findViewById(R.id.imgDownArrow);
        statusTextView = view.findViewById(R.id.textViewId);
        changeStatusButton = view.findViewById(R.id.buttonId);

        View view1 = view.findViewById(R.id.bottomSheet);
        bottomSheetBehavior = bottomSheetBehavior.from(view1);



        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED){
            changeStatusButton.setText("COLLAPSED");
            statusTextView.setText("Bottom Sheet is EXPANDED");
            arrowImageView.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
        }else {
            changeStatusButton.setText("EXPANDED");
            statusTextView.setText("Bottom Sheet is COLLAPSED");
            arrowImageView.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
        }


        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState){
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        statusTextView.setText("Bottom Sheet is COLLAPSED");
                        changeStatusButton.setText("EXPAND");
                        arrowImageView.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        statusTextView.setText("Bottom Sheet is EXPANDED");
                        changeStatusButton.setText("COLLAPSE");
                        arrowImageView.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        statusTextView.setText("Bottom Sheet is DRAGGING...");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        statusTextView.setText("Bottom Sheet is SETTLING...");
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                statusTextView.setText("Bottom Sheet is SLIDING...");
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Payment Processing", Toast.LENGTH_SHORT).show();
            }
        });

        changeStatusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }else {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        });

    }


}