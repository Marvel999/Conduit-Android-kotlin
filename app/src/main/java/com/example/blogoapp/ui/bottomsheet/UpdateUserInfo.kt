package com.example.blogoapp.ui.bottomsheet

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.api.ConduitClient
import com.example.api.model.response.ArticleResponse
import com.example.api.model.response.UserResponse
import com.example.blogoapp.R
import com.example.blogoapp.data.UserSharedpreferences
import com.example.blogoapp.ui.profile.ProfileViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class UpdateUserInfo : BottomSheetDialogFragment() {
    companion object {
        fun newInstance() = PostArticleBS()
    }
    private lateinit var edUserName:EditText
    private lateinit var edEmailId:EditText
    private lateinit var edBioId:EditText
    private lateinit var edImageId:EditText
    private lateinit var edPassword:EditText
    private lateinit var updateBtn:Button
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_update_user_info, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.MyBottomSheetDialogTheme)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateBtn=view.findViewById(R.id.updateBtn)
        edUserName=view.findViewById(R.id.edUserName)
        edEmailId=view.findViewById(R.id.edEmailId)
        edBioId=view.findViewById(R.id.edBioId)
        edImageId=view.findViewById(R.id.edImageId)
        edPassword=view.findViewById(R.id.edPassword)
        updateBtn.background=resources.getDrawable(R.drawable.green_circuler_background)
        ConduitClient.authToken=UserSharedpreferences(requireContext()).token

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        viewModel.getCurrentUserInfo()


        val progressDialog = Dialog(requireContext())
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressDialog.setContentView(R.layout.custom_dialog_progress)

/* Custom setting to change TextView text,Color and Text Size according to your Preference*/

        val progressTv = progressDialog.findViewById(R.id.progress_tv) as TextView
        progressTv.text = resources.getString(R.string.loading)
        progressTv.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
        progressTv.textSize = 19F

        progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        progressDialog.setCancelable(false)
        progressDialog.show()




        viewModel.userRes.observe({lifecycle}){
            when(it){
            is UserResponse->{
                edBioId.setText(it.user.bio ?:" ")
            edUserName.setText(it.user.username ?: " ")
            edEmailId.setText(it.user.email ?:" ")
            edImageId.setText(it.user.image ?: " ")
                progressDialog.dismiss()
            }
                else->{
                    Toast.makeText(requireContext(),"Error in server", Toast.LENGTH_LONG).show()
                    progressDialog.dismiss()
                }
            }
        }
        updateBtn.setOnClickListener {
            if (checkValidation()){
                viewModel.PostArticles(
                    bio = edBioId.text.takeIf { it.isNotBlank() }.toString(),
                    userName = edUserName.text.takeIf { it.isNotBlank() }.toString(),
                    image = edImageId.text.toString(),
                    email = edEmailId.text.takeIf { it.isNotBlank() }.toString(),
                    password = edPassword.text.takeIf { it.isNotBlank() }.toString()
                ).let {
                    Toast.makeText(requireContext(),"Please Wait...", Toast.LENGTH_LONG).show()

                }
            }
        }

        viewModel.userResponse.observe({lifecycle}){
            when(it){
                is UserResponse ->{
                    Toast.makeText(requireContext(),"Article is successful", Toast.LENGTH_LONG).show()
                    viewModel.getCurrentUserInfo()
                    dismiss()
                }
                else->{
                    Toast.makeText(requireContext(),"Sorry Some Technical Issue", Toast.LENGTH_LONG).show()
                    dismiss()
                }
            }
        }
    }

    fun checkValidation():Boolean{
        if (edUserName.text.isEmpty())
            return false
        else if (edEmailId.text.isEmpty())
            return false
        else return !edPassword.text.isEmpty()
    }


}


