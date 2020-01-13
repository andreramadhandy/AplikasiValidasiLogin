package id.ac.poliban.mi.andre.aplikasivalidasilogin;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

class RegisterActivity extends AppCompatActivity {
    EditText editUsername, editPassword, editEmail, editNama, editAsalSekolah, editAlamat;
    Button btnSimpan;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_register);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setTitle("Register");

        editAlamat = findViewById(R.id.editAlamat);
        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
        editAsalSekolah = findViewById(R.id.editAsalSekolah);
        editEmail = findViewById(R.id.editEmail);
        editNama = findViewById(R.id.editNamaLengkap);
        btnSimpan = findViewById(R.id.btnSimpan);

        btnSimpan.setOnClickListener(v -> {
            if(isValidation()){
                simpanFileData();
            } else {
                Toast.makeText(this, "Mohon Lengkapi Seluruh Data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void simpanFileData() {
        String isiFile = editUsername.getText().toString()+";"+
                editPassword.getText().toString() + ";" +
                editEmail.getText().toString() + ";" +
                editNama.getText().toString() + ";" +
                editAsalSekolah.getText().toString() + ";" +
                editAlamat.getText().toString();
        File file = new File(getFilesDir(), editUsername.getText().toString());

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    private boolean isValidation() {
        if(editUsername.getText().toString().equals("") ||
        editNama.getText().toString().equals("") ||
                editPassword.getText().toString().equals("") ||
                editEmail.getText().toString().equals("") ||
                editAsalSekolah.getText().toString().equals("") ||
                editAlamat.getText().toString().equals("")) {
                    return false;
        } else {
            return true;
        }
    }

}
