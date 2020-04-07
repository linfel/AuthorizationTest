package com.gosha.authorizationtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

const val LOGIN = "com.gosha.authorizationtest.LOGIN"
const val PASSWORD = "com.gosha.authorizationtest.PASSWORD"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }



    fun onLogin(view: View) {
        val login = login_filed.text.toString()
        val password = password_field.text.toString()

        if (login == "login12345" && password == "password12345") {
            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra(LOGIN, login)
                putExtra(PASSWORD, password)
            }
            startActivity(intent)
        }
        else {
            Toast.makeText(this, "Wrong credentials", Toast.LENGTH_LONG).show()
        }




    }
}
