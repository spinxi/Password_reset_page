package com.example.giorgi_kurdadze_group_1

import android.os.Bundle
import android.text.Editable
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var phone: EditText;
    private lateinit var code: EditText;
    private lateinit var newPassword: EditText;
    private lateinit var newPasswordRepeat: EditText;
    private lateinit var resetButton: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Get Fields and buttons with ID
        phone = findViewById(R.id.editTextPhone);
        code = findViewById(R.id.editTextSms);
        newPassword = findViewById(R.id.editTextNewPassword);
        newPasswordRepeat = findViewById(R.id.editTextNewPasswordRepeat);
        resetButton = findViewById(R.id.buttonReset);

        //Set Click Listener
        resetButton.setOnClickListener{

            //Get Texts from Fields and convert into STRING
            val phoneText = phone.text.toString();
            val codeText = code.text.toString();
            val newPasswordText = newPassword.text.toString();
            val newPasswordRepeatText = newPasswordRepeat.text.toString();


            //Validate each component
            val phoneValidate = validadePhone(phoneText);
            val codeValidate = validateCode(codeText);
            val passwordsValidate = validatePasswords(newPasswordText, newPasswordRepeatText);

            //Check validations and display its Toasts
            if (phoneValidate && codeValidate && passwordsValidate) {
                //Clear fields
                emptyEditTextFields(phone, code, newPassword, newPasswordRepeat);
                //Call TOAST
                Toast.makeText(this@MainActivity, "ახალ პაროლს მიიღებთ SMS - ით", Toast.LENGTH_SHORT).show();

            } else {
                var errorText:String = "";
                if (!phoneValidate){
                    errorText = "ტელეფონი არასწორია!";
                }else if(!codeValidate){
                    errorText = "კოდი არასწორია!";
                }else if(!passwordsValidate){
                    errorText = "პაროლი არასწორია!";
                }
                val errorMessage = "შეცდომა: " + errorText;

                //Call TOAST
                Toast.makeText(this@MainActivity, errorMessage, Toast.LENGTH_SHORT).show()
            }





        }



    }

    private fun emptyEditTextFields(vararg editTexts: EditText) {
        for (editText in editTexts) {
            editText.text.clear()
        }
    }

    private fun validadePhone(phone: String?): Boolean {
        return Patterns.PHONE.matcher(phone).matches();
    }

    private fun validateCode(code: String?): Boolean {
        if (code != null && code.length == 4) {
            return code.toIntOrNull() != null;
        }else {
            return false;
        }
    }



    private fun validatePasswords(password1: String?,password2: String?): Boolean {
        return checkForPasswordValidations(password1) && checkForPasswordValidations(password2) && password1 == password2;
    }
    private fun checkForPasswordValidations(password: String?): Boolean{
        return password != null && password.length >= 8 && password.any { it.isDigit() };
    }



}