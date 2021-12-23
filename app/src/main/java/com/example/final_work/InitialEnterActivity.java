package com.example.final_work;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.final_work.databinding.ActivityInitialEnterBinding;

public class InitialEnterActivity extends AppCompatActivity {
    private ActivityInitialEnterBinding binding;
    private CheckBox rememberPass;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private EditText account;
    private EditText password;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView =this.getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        binding=ActivityInitialEnterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        account=binding.usernameFragment;
        password=binding.password;
        login=binding.loginFragment;
        rememberPass=binding.rememberPass;

        preferences= PreferenceManager.getDefaultSharedPreferences(InitialEnterActivity.this);

        boolean isRemember=preferences.getBoolean("remember_password",false);
        if(isRemember){
            String accountString=preferences.getString("account","");
            String passwordString=preferences.getString("password","");
            account.setText(accountString);
            password.setText(passwordString);
            rememberPass.setChecked(true);
        }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String accountString=account.getText().toString();
                String passwordString=password.getText().toString();

                if(accountString.equals("2020155026")&&passwordString.equals("12051534")){
                    editor=preferences.edit();
                    if(rememberPass.isChecked()){
                        editor.putBoolean("remember_password",true);
                        editor.putString("account",accountString);
                        editor.putString("password",passwordString);
                    }
                    else {
                        //editor.putBoolean("remember_password",false);
                        editor.clear();
                    }

                    editor.apply();

                    Intent intent=new Intent(InitialEnterActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();

                }
                else {
                    Toast.makeText(InitialEnterActivity.this,"账号或者密码错误",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}