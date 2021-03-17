package com.example.blogoapp.auth

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.api.model.entity.User
import com.example.blogoapp.AppHomeActivity
import com.example.blogoapp.R
import org.w3c.dom.Text

class LoginFragment : Fragment() {

    private lateinit var tvNoAccount: TextView
    private lateinit var edEmailId: EditText
    private lateinit var edPassword: EditText
    private lateinit var signInBtn: Button

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvNoAccount = view.findViewById(R.id.tvNoAccount)
        edEmailId = view.findViewById(R.id.edEmailId)
        edPassword = view.findViewById(R.id.edPassword)
        signInBtn = view.findViewById(R.id.signInBtn)


    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        signInBtn.setOnClickListener {
            viewModel.userLoginRequest(
                edEmailId.text.toString().trim(),
                edPassword.text.toString().trim()
            )
        }

        viewModel.userInfo.observe({lifecycle}){
            when(it){
                is User ->{
                    Toast.makeText(requireContext(),"Logged in"+it.token,Toast.LENGTH_LONG).show()
                    val intent =Intent(activity,AppHomeActivity::class.java)
                    startActivity(intent)
                    activity?.finish()

                }
                else ->{
                    Toast.makeText(requireContext(),"Login request is fail",Toast.LENGTH_LONG).show()

                }
            }
        }


    }




}