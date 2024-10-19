package edu.iastate.netid.pocketcalculator;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    /**
     * The instance of the calculator model for use by this controller.
     */
    private final CalculationStream mCalculationStream = new CalculationStream();

    /*
     * The instance of the calculator display TextView. You can use this to update the calculator display.
     */
    private TextView mCalculatorDisplay;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* TODO - uncomment the below line after you make your layout. This line locates
           the instance of the calculator display's TextView.  You need to create this TextView
           and set its ID to CalculatorDisplay in your layout resource file.
         */
        mCalculatorDisplay = findViewById(R.id.CalculatorDisplay);


          }

    /* TODO - add event listeners for your calculator's buttons. See the model's API to figure out
       what methods should be called. The equals button event listener has been done for you. Once
       you've created the layout, you'll need to add these methods as the onClick() listeners
       for the corresponding buttons in the XML layout. */

    public void onDigitClicked(View view) {
    try {
        switch (view.getId()) {
            case R.id.buttonClear:
                mCalculationStream.clear();
                break;
            case R.id.button1:
                mCalculationStream.inputDigit(CalculationStream.Digit.ONE);
                break;
            case R.id.button0:
                mCalculationStream.inputDigit(CalculationStream.Digit.ZERO);
                break;
            case R.id.button2:
                mCalculationStream.inputDigit(CalculationStream.Digit.TWO);
                break;
            case R.id.button3:
                mCalculationStream.inputDigit(CalculationStream.Digit.THREE);
                break;
            case R.id.button4:
                mCalculationStream.inputDigit(CalculationStream.Digit.FOUR);
                break;
            case R.id.button5:
                mCalculationStream.inputDigit(CalculationStream.Digit.FIVE);
                break;
            case R.id.button6:
                mCalculationStream.inputDigit(CalculationStream.Digit.SIX);
                break;
            case R.id.button7:
                mCalculationStream.inputDigit(CalculationStream.Digit.SEVEN);
                break;
            case R.id.button8:
                mCalculationStream.inputDigit(CalculationStream.Digit.EIGHT);
                break;
            case R.id.button9:
                mCalculationStream.inputDigit(CalculationStream.Digit.NINE);
                break;
            case R.id.buttonDot:
                mCalculationStream.inputDigit(CalculationStream.Digit.DECIMAL);
                break;
        }
    }
        finally {
            updateCalculatorDisplay();
        }
    }
    public void onOperandClicked(View view){
        try{
            switch(view.getId()){
                case R.id.buttonDivide:
                    mCalculationStream.inputOperation(CalculationStream.Operation.DIVIDE);
                    break;
                case R.id.buttonMinus:
                    mCalculationStream.inputOperation(CalculationStream.Operation.SUBTRACT);
                    break;
                case R.id.buttonPlus:
                    mCalculationStream.inputOperation(CalculationStream.Operation.ADD);
                    break;
                case R.id.buttonStar:
                    mCalculationStream.inputOperation(CalculationStream.Operation.MULTIPLY);

            }
        }
        finally{
        updateCalculatorDisplay();
        }
    }

    public void onEqualClicked(View view) {
        try {
            mCalculationStream.calculateResult();
        } finally {
            updateCalculatorDisplay();
        }
    }

    /**
     * Call this method after every button press to update the text display of your calculator.
     */
    public void updateCalculatorDisplay() {
        String value = getString(R.string.empty);
        try {
            value = Double.toString(mCalculationStream.getCurrentOperand());
        } catch(NumberFormatException e) {
            value = getString(R.string.error);
        } finally {
            if (value.length() >= 13){
                String sub = value.substring(0,13);
                mCalculatorDisplay.setText(sub);
            }
            else {
                mCalculatorDisplay.setText(value);
            }
        }
    }
}
