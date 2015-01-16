package com.nickpiscopio.numbercrunch;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * The fragment for selecting operators.
 *
 * Created by Nick Piscopio on January 16, 2015.
 */
public class OperatorFragment extends DialogFragment
{
    private final int ID_MULTIPLY = R.id.operator_multiply;
    private final int ID_DIVIDE = R.id.operator_divide;
    private final int ID_ADD = R.id.operator_add;
    private final int ID_SUBTRACT = R.id.operator_subtract;

    public static final String OPERATOR_INDEX = "operator_index";
    public static final String OPERATOR_ID = "operator_id";

    private int operatorIndex;
    private int operatorId;

    public interface DialogListener
    {
        public void onOperatorSelected(int operatorIndex, int operatorId, Operator operator);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.activity_operator, container);

        // set the custom dialog components - text, image and button
        Button multiply = (Button) view.findViewById(ID_MULTIPLY);
        Button divide = (Button) view.findViewById(ID_DIVIDE);
        Button add = (Button) view.findViewById(ID_ADD);
        Button subtract = (Button) view.findViewById(ID_SUBTRACT);

        multiply.setOnClickListener(dialogListener);
        divide.setOnClickListener(dialogListener);
        add.setOnClickListener(dialogListener);
        subtract.setOnClickListener(dialogListener);

        Bundle bundle = getArguments();

        operatorIndex = bundle.getInt(OPERATOR_INDEX);
        operatorId = bundle.getInt(OPERATOR_ID);

        return view;
    }

    private View.OnClickListener dialogListener = new View.OnClickListener()
    {
        @Override public void onClick(View v)
        {
            DialogListener activity = (DialogListener) getActivity();

            switch (v.getId())
            {
                case ID_MULTIPLY:

                    activity.onOperatorSelected(operatorIndex, operatorId, Operator.MULTIPLY);

                    break;

                case ID_DIVIDE:

                    activity.onOperatorSelected(operatorIndex, operatorId, Operator.DIVIDE);

                    break;

                case ID_ADD:

                    activity.onOperatorSelected(operatorIndex, operatorId, Operator.ADD);

                    break;

                case ID_SUBTRACT:

                    activity.onOperatorSelected(operatorIndex, operatorId, Operator.SUBTRACT);

                    break;

                default:
                    break;
            }

            OperatorFragment.this.dismiss();
        }
    };
}