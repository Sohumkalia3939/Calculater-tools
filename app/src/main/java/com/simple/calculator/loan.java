package com.simple.calculator;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;


public class loan extends AppCompatActivity {

    Button emiCalcBtn;
     TextView mMontlyPaymentResult, mTotalPaymentsResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);

        final EditText P = findViewById(R.id.principal);
        final EditText I = findViewById(R.id.interest);
        final EditText Y = findViewById(R.id.years);
        final EditText TI = findViewById(R.id.interest_total);
        final EditText result = findViewById(R.id.emi);

        mMontlyPaymentResult = (TextView)findViewById(R.id.emi);
        mTotalPaymentsResult = (TextView)findViewById(R.id.interest_total);

        emiCalcBtn = findViewById(R.id.btn_calculate2);

        emiCalcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String st1 = P.getText().toString();
                String st2 = I.getText().toString();
                String st3 = Y.getText().toString();

                if (TextUtils.isEmpty(st1)) {
                    P.setError("Enter Principal Amount");
                    P.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(st2)) {
                    I.setError("Enter Interest Rate");
                    I.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(st3)) {
                    Y.setError("Enter Years");
                    Y.requestFocus();
                    return;
                }












                double loanAmount = Integer.parseInt(P.getText().toString());
                double interestRate = (Integer.parseInt(I.getText().toString()));
                double loanPeriod = Integer.parseInt(Y.getText().toString());
                double r = interestRate / 1200;
                double r1 = Math.pow(r + 1, loanPeriod);



















                double monthlyPayment = (double) ((r + (r / (r1 - 1))) * loanAmount);
                double totalPayment = monthlyPayment * loanPeriod;

                mMontlyPaymentResult.setText(new DecimalFormat("##.##").format(monthlyPayment));
                mTotalPaymentsResult.setText(new DecimalFormat("##.##").format(totalPayment));
            }
        });

    }
}
