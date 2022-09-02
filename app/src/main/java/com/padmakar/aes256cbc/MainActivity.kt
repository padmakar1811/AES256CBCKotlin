package com.padmakar.aes256cbc

import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var editMobileNumber: EditText? = null
    var output: TextView? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editMobileNumber = findViewById(R.id.editMobileNumber)
        output = findViewById(R.id.output)

        findViewById<View>(R.id.buttonEncrypt).setOnClickListener {
            if (!TextUtils.isEmpty(editMobileNumber!!.text.toString().trim { it <= ' ' })) {
                var encrypted: String? = null
                encrypted =
                    AESUtil.encrypt(editMobileNumber!!.text.toString().trim { it <= ' ' })
                output!!.text=encrypted
                // encrypted = CryptoHandler.encrypt(editMobileNumber.getText().toString().trim());
                Log.e("out", output!!.text.toString().trim { it <= ' ' })
            } else {
                Toast.makeText(this@MainActivity, "Enter Number", Toast.LENGTH_LONG)
            }
        }
        findViewById<View>(R.id.buttonDecrypt).setOnClickListener {
            if (!TextUtils.isEmpty(editMobileNumber!!.text.toString().trim { it <= ' ' })) {
                try {
                    //String decrypt = decrypt(encrypt(editMobileNumber.getText().toString().getBytes(), secretKey, IV), secretKey, IV);
                    val encrypted =
                        AESUtil.encrypt(editMobileNumber!!.text.toString().trim { it <= ' ' })
                    val decrypted = AESUtil.decrypt(encrypted)
                    output!!.setText(decrypted)
                    Log.e("out", output!!.text.toString().trim { it <= ' ' })
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                Toast.makeText(this@MainActivity, "Enter Number", Toast.LENGTH_LONG)
            }
        }
    }
}