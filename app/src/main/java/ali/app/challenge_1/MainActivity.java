package ali.app.challenge_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private EditText edtTextName, edtTextEmail, edtTextPassword, edtTextPassRepeat;
    private Button btnPickImage, btnRegister;
    private TextView textWarnName, textWarnEmail, textWarnPassword, textWarnPassRepeat;
    private Spinner countriesSpinner;
    private RadioGroup rgGender;
    private CheckBox agreementCheck;
    private ConstraintLayout parent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        btnPickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Picking image", Toast.LENGTH_SHORT).show();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initRegister();
            }
        });
    }

    private void initRegister() {
        Log.d(TAG, "initRegister: Started");

        if(validateData()) {
            if(agreementCheck.isChecked()) {
                showSnackBar();
            }
            else {
                Toast.makeText(this, "You need to agree to the license agreement to continue", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showSnackBar() {
        Log.d(TAG, "showSnackBar: Started");
        textWarnName.setVisibility(View.GONE);
        textWarnEmail.setVisibility(View.GONE);
        textWarnPassword.setVisibility(View.GONE);
        textWarnPassRepeat.setVisibility(View.GONE);

        String name = edtTextName.getText().toString();
        String email = edtTextEmail.getText().toString();
        String country = countriesSpinner.getSelectedItem().toString();
        String gender = "";
        switch (rgGender.getCheckedRadioButtonId()) {
            case R.id.rbMale:
                gender = "Male";
                break;
            case R.id.rbFemale:
                gender = "Female";
                break;
            case R.id.rbOther:
                gender = "Other";
                break;
            default:
                gender = "Unknown";
                break;
        }

        String snackText = "Name: " + name + "\n" +
                "Email: " + email + "\n" +
                "Gender: " + gender + "\n" +
                "Country: " + country;

        Log.d(TAG, "showSnackBar: Snack Bar Text: " + snackText);

        Snackbar.make(parent, snackText, Snackbar.LENGTH_INDEFINITE)
                .setAction("Dismiss", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        edtTextName.setText("");
                        edtTextEmail.setText("");
                        edtTextPassword.setText("");
                        edtTextPassRepeat.setText("");
                    }
                }).show();
    }

    private boolean validateData() {
        Log.d(TAG, "validateData: Started");
        if(edtTextName.getText().toString().equals("")) {
            textWarnName.setVisibility(View.VISIBLE);
            textWarnName.setText("Enter your name");
            return false;
        }

        if(edtTextEmail.getText().toString().equals("")) {
            textWarnEmail.setVisibility(View.VISIBLE);
            textWarnEmail.setText("Enter your email");
            return false;
        }

        if(edtTextPassword.getText().toString().equals("")) {
            textWarnPassword.setVisibility(View.VISIBLE);
            textWarnPassword.setText("Enter your password");
            return false;
        }

        if(edtTextPassRepeat.getText().toString().equals("")) {
            textWarnPassRepeat.setVisibility(View.VISIBLE);
            textWarnPassRepeat.setText("Confirm your password");
            return false;
        }

        if(!edtTextPassword.getText().toString().equals(edtTextPassRepeat.getText().toString())) {
            textWarnPassRepeat.setVisibility(View.VISIBLE);
            textWarnPassRepeat.setText("Password does not match");
            return false;
        }

        return true;
    }

    private void initViews() {
        Log.d(TAG, "initViews: Started");
        edtTextName = findViewById(R.id.edtTextName);
        edtTextEmail = findViewById(R.id.edtTextEmail);
        edtTextPassword = findViewById(R.id.edtTextPassword);
        edtTextPassRepeat = findViewById(R.id.edtTextPassRepeat);

        btnPickImage = findViewById(R.id.btnPickImage);
        btnRegister = findViewById(R.id.btnRegister);

        textWarnName = findViewById(R.id.txtWarnName);
        textWarnEmail = findViewById(R.id.txtWarnEmail);
        textWarnPassword = findViewById(R.id.txtWarnPassword);
        textWarnPassRepeat = findViewById(R.id.txtWarnPassRepeat);

        countriesSpinner = findViewById(R.id.spinnerCountry);
        rgGender = findViewById(R.id.rgGender);
        agreementCheck = findViewById(R.id.agreementCheck);
        parent = findViewById(R.id.parent);
    }
}