package com.csnewengineeringbuilding;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.csnewengineeringbuilding.util.SFCallback;
import com.csnewengineeringbuilding.util.userDTO;
import com.pnikosis.materialishprogress.ProgressWheel;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button _login;
    private EditText _id, _pw;
    ProgressWheel progressWheel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _login = (Button) findViewById(R.id.btn_login);
        _id = (EditText) findViewById(R.id.et_login_id);
        _pw = (EditText) findViewById(R.id.et_login_password);
        progressWheel = (ProgressWheel) findViewById(R.id.progress_wheel);
        progressWheel.stopSpinning();
        _login.setOnClickListener(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                signIn();
                break;
            default:
                break;
        }
    }

    public void signIn() {
        _id.setError(null);
        _pw.setError(null);
        if (_id.getText().length() != 10) {
            _id.setError("유효한 정보를 입력하세요");
            return;
        }
        if (_pw.getText().length() < 5) {
            _pw.setError("유효한 패스워드를 입력하세요");
            return;
        }
//        final ProgressDialog pdial = new ProgressDialog(this, R.style.AppTheme);
//        pdial.setMessage("계정 정보를 불러오는 중...");
//        pdial.setCancelable(false);
//        pdial.show();

        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        progressWheel.spin();
        userDTO session = new userDTO(new SFCallback() {
            public void callback() {
//                pdial.dismiss();
                Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                SharedPreferences sharedPreferences = getSharedPreferences("CSNewEngineeringBuilding", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLogin", true);
                editor.commit();
                progressWheel.stopSpinning();
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        }, new SFCallback() {
            public void callback() {
//                pdial.dismiss();
                _login.setEnabled(true);
                progressWheel.stopSpinning();
                Toast.makeText(LoginActivity.this, "인터넷에 연결할 수 없습니다.", Toast.LENGTH_SHORT).show();
            }
        }, new SFCallback() {
            public void callback() {
//                pdial.dismiss();
                _login.setEnabled(true);
                progressWheel.stopSpinning();
                Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
            }
        }, _id.getText().toString(), _pw.getText().toString());
        session.execute();
    }
}
