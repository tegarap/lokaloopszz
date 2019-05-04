package com.tegarap.lokaloops.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.tegarap.lokaloops.R
import com.tegarap.lokaloops.models.UserResponse
import com.tegarap.lokaloops.rest.EndPoint
import com.tegarap.lokaloops.rest.InterfacePoint
import com.tegarap.lokaloops.utils.PrefsUtil
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {


    private val prefs by lazy { PrefsUtil(this) }
    private lateinit var iPoint: InterfacePoint
    private lateinit var user: UserResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

//        setupToolbar()

        iPoint = EndPoint.client.create(InterfacePoint::class.java)
        btn_login.setOnClickListener {
            login()
        }
    }

//    private fun setupToolbar() {
//        val actionBar = supportActionBar
//        actionBar!!.hide()
//    }

    private fun login() {
        iPoint.login(et_email.text.toString(), et_password.text.toString()).enqueue(object : Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    user = response.body()!!
                    prefs.saveUser(user)
                    startActivity(MainActivity.getIntent(this@LoginActivity))
                    finish()
                } else {

                }

            }

        })
    }


}
