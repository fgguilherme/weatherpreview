package com.bdiggital.forecaster

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.fragment_facebook_login.*

class FacebookLoginFragment : Fragment(), FacebookCallback<LoginResult> {

    private val callbackManager = CallbackManager.Factory.create()

    override fun onSuccess(result: LoginResult?) {
        Toast.makeText(context,R.string.login_ok,Toast.LENGTH_LONG).show()
    }

    override fun onCancel() {
        Toast.makeText(context,R.string.user_cancel,Toast.LENGTH_LONG).show()
    }

    override fun onError(error: FacebookException?) {
        Toast.makeText(context,R.string.login_error,Toast.LENGTH_LONG).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_facebook_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login_button.setReadPermissions("email")
        login_button.fragment = this
        login_button.registerCallback(callbackManager,this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}
