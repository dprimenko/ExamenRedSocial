package es.dprimenko.redsocial;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by dprimenko on 3/02/17.
 */

public class LoginActivity extends AppCompatActivity {

    private TextView txvTitle;
    private EditText edtUsername, edtPassword;
    private Button btnLogin;
    private String selectedAcc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txvTitle = (TextView) findViewById(R.id.title_login);
        edtUsername = (EditText) findViewById(R.id.edt_username);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        btnLogin = (Button) findViewById(R.id.btn_login);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "gloriahallelujah.ttf");
        txvTitle.setTypeface(typeface);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verifyData()) {
                    if (Repository.getInstance().login(selectedAcc, edtUsername.getText().toString(), edtPassword.getText().toString())) {
                        Intent intent = new Intent(LoginActivity.this, ListFriendsActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        showAlert(R.string.login_failed);
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_account) {
            dialogAccount();
        }

        return true;
    }



    private void dialogAccount() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final String[] cuentas = new String[] {"Facebook", "Gmail", "Twitter"};

        builder.setTitle(getString(R.string.app_name));
        builder.setItems(cuentas, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedAcc = cuentas[which].toLowerCase();
            }
        });
        builder.show();
    }

    private boolean verifyData() {
        boolean result = false;

        if (TextUtils.isEmpty(selectedAcc)) {
            showAlert(R.string.account_noselected);
        } else if (TextUtils.isEmpty(edtUsername.getText().toString())) {
            showAlert(R.string.username_empty);
        } else if (TextUtils.isEmpty(edtPassword.getText().toString())) {
            showAlert(R.string.password_empty);
        } else {
            result = true;
        }

        return result;
    }

    public void showAlert(int message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle(getString(R.string.alert));
        builder.setMessage(message);
        builder.setNeutralButton(getString(R.string.accept), null);
        builder.show();
    }
}
