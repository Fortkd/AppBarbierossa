package com.example.fortu.appbarbierossa;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class activity_registrazione extends AppCompatActivity {

    private LoginActivity.UserLoginTask mAuthTask = null;
    private EditText rNomeView;
    private EditText rCognomeView;
    private EditText rCellulareView;
    private EditText rMailView;
    private EditText rPasswordView;
    private EditText rConfPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);

        rNomeView = (EditText) findViewById(R.id.nome_reg);
        rCognomeView = (EditText) findViewById(R.id.cognome_reg);
        rCellulareView = (EditText) findViewById(R.id.cel_reg);
        rMailView = (EditText) findViewById(R.id.mail_reg);
        rPasswordView = (EditText) findViewById(R.id.pax_reg);
        rConfPasswordView = (EditText) findViewById(R.id.paxConf_reg);
        rPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptSignIn();
                    return true;
                }
                return false;
            }
        });

        Button rSignInButton = (Button) findViewById(R.id.signin_button);
        rSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptSignIn();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

    }

    private void attemptSignIn() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        rNomeView.setError(null);
        rCognomeView.setError(null);
        rCellulareView.setError(null);
        rMailView.setError(null);
        rPasswordView.setError(null);
        rConfPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String name = rNomeView.getText().toString();
        String surname = rCognomeView.getText().toString();
        String cell =rCellulareView.getText().toString();
        String email = rMailView.getText().toString();
        String password = rPasswordView.getText().toString();
        String confpax = rConfPasswordView.getText().toString();
        boolean pax = false;
        boolean user = false;
        boolean checkName = false;
        boolean checkSurname = false;
        boolean checkPhone = false;
        boolean checkconfPax = false;
        boolean cancel = false;
        View focusView = null;

        // Check for a valid conferma password.
        if (TextUtils.isEmpty(confpax)) {
            rConfPasswordView.setError("Campo obbligatorio");
            focusView = rConfPasswordView;
            cancel = true;
        } else if (!password.equals(confpax)) {
            rConfPasswordView.setError(getString(R.string.error_wrong_password));
            focusView = rConfPasswordView;
            cancel = true;
        } else checkconfPax = true;

        // Check for a valid password.
        if (TextUtils.isEmpty(password)) {
            rPasswordView.setError("Campo obbligatorio");
            focusView = rPasswordView;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            rPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = rPasswordView;
            cancel = true;
        } else pax = true;

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            rMailView.setError(getString(R.string.error_field_required));
            focusView = rMailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            rMailView.setError(getString(R.string.error_invalid_email));
            focusView = rMailView;
            cancel = true;
        }  else user=true;

        // Check for a valid cellulare.
        if (TextUtils.isEmpty(cell)) {
            rCellulareView.setError("Campo obbligatorio");
            focusView = rCellulareView;
            cancel = true;
        } else if (cell.length() < 9) {
            rCellulareView.setError(getString(R.string.error_invalid_phone));
            focusView = rCellulareView;
            cancel = true;
        } else checkPhone = true;

        // Check for a valid surname.
        if (TextUtils.isEmpty(surname)) {
            rCognomeView.setError("Campo obbligatorio");
            focusView = rCognomeView;
            cancel = true;
        }else checkSurname = true;

        // Check for a valid name.
        if (TextUtils.isEmpty(name)) {
            rNomeView.setError("Campo obbligatorio");
            focusView = rNomeView;
            cancel = true;
        }else checkName = true;

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        }

        if(user && pax && checkName && checkSurname && checkPhone && checkconfPax){
            Intent openMain=new Intent(activity_registrazione.this,MainActivity.class); //Definisce l'intenzione di aprire il mainActivity
            startActivity(openMain); //Apre il main
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 5;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

}
