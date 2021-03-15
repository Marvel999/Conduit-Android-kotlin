package com.example.blogoapp.auth

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
import androidx.navigation.fragment.findNavController
import com.example.api.model.entity.UserCred
import com.example.api.model.requests.UserRequests
import com.example.blogoapp.R

class SignupFragment : Fragment() {

    private lateinit var edUserName:EditText
    private lateinit var edEmailId:EditText
    private lateinit var edPassword:EditText
    private lateinit var signupBtn:Button
    private lateinit var tvAlreadyAccount:TextView
    companion object {
        fun newInstance() = SignupFragment()
    }

    private lateinit var viewModel: SignupViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root=inflater.inflate(R.layout.signup_fragment, container, false)

        return root
    }

    override fun onViewCreated(root: View, savedInstanceState: Bundle?) {
        super.onViewCreated(root, savedInstanceState)
        edEmailId=root.findViewById(R.id.edEmailId)
        edPassword=root.findViewById(R.id.edPassword)
        edUserName=root.findViewById(R.id.edUserName)
        signupBtn=root.findViewById(R.id.signUpBtn)
        tvAlreadyAccount=root.findViewById(R.id.tvAlreadyAccount)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignupViewModel::class.java)

        signupBtn.setOnClickListener {
            if (validationCheck()) {
                viewModel.addUser(
                    UserRequests(
                        UserCred(
                            email = edEmailId.text.toString(),
                            password = edPassword.text.toString(),
                            username = edUserName.text.toString()
                        )
                    )
                )
            }
        }
        tvAlreadyAccount.setOnClickListener {
            findNavController().navigate(R.id.gotoLoginFragment)
        }

        viewModel.userResponse.observe({lifecycle},{

            if (!it.isEmpty()) {
                Toast.makeText(context, "User ${it}", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.gotoLoginFragment)


            }else
                Toast.makeText(context, "User ${it}", Toast.LENGTH_LONG).show()

        })


    }

    fun validationCheck():Boolean{
        if (edEmailId.text.isEmpty())
            return false;
        else if (edPassword.text.isEmpty())
            return false;
        else if (edUserName.text.isEmpty())
            return false;
        else
            return true
    }

}