package es.dprimenko.redsocial;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import es.dprimenko.redsocial.models.User;

/**
 * Created by dprimenko on 3/02/17.
 */

public class AddFriendActivity extends AppCompatActivity {

    private EditText edtUsernameFriend, edtEmailFriend;
    private Button btnInsertFriend;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        edtUsernameFriend = (EditText) findViewById(R.id.edt_username_friend);
        edtEmailFriend = (EditText) findViewById(R.id.edt_email_friend);
        btnInsertFriend = (Button) findViewById(R.id.btn_insert_friend);

        btnInsertFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (verifyData()) {
                    if (!(Repository.getInstance().friendExists(edtEmailFriend.getText().toString()))) {
                        User userSelected = Repository.getInstance().getUserSelected();

                        Repository.getInstance().addFriendToUser(new User(
                                edtUsernameFriend.getText().toString(),
                                "test",
                                edtEmailFriend.getText().toString(),
                                new ArrayList<User>(),
                                userSelected.getmAccount()));

                        Intent intent = new Intent(AddFriendActivity.this, ListFriendsActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        showAlert(R.string.frien_exists);
                    }
                } else {
                    showAlert(R.string.data_empty);
                }
            }
        });

    }

    private boolean verifyData() {
        boolean result = true;

        if (TextUtils.isEmpty(edtUsernameFriend.getText().toString()) || TextUtils.isEmpty(edtEmailFriend.getText().toString())) {
            result = false;
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
