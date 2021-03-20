package com.example.blogoapp.ui.profile

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.api.ConduitClient
import com.example.blogoapp.R
import com.example.blogoapp.data.UserSharedpreferences
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
     private lateinit var fab:FloatingActionButton
     private lateinit var userName:TextView
     private lateinit var editBtn:ImageView
     private lateinit var userBio:TextView
     private lateinit var profileUserName:TextView
     private lateinit var tvbio:TextView
     private lateinit var emailId:TextView
     private lateinit var profileImage:ImageView
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        profileViewModel =
                ViewModelProvider(this).get(ProfileViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_profile, container, false)

        return root
    }






    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab=view.findViewById(R.id.fab)
        userName=view.findViewById(R.id.userName)
        editBtn=view.findViewById(R.id.editBtn)
        userBio=view.findViewById(R.id.userBio)
        profileUserName=view.findViewById(R.id.profileUserName)
        tvbio=view.findViewById(R.id.tvbio)
        emailId=view.findViewById(R.id.emailId)
        profileImage=view.findViewById(R.id.profileImage)
        ConduitClient.authToken=UserSharedpreferences(requireContext()).token

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



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



        fab.setOnClickListener {
            findNavController().navigate(R.id.updateUserInfo)
        }
        editBtn.setOnClickListener {
            findNavController().navigate(R.id.updateUserInfo)
        }
        profileViewModel.getCurrentUserInfo()
        profileViewModel.userRes.observe({lifecycle}){
            progressDialog.dismiss()
            userName.text=it.user.username
            profileUserName.text=it.user.username
            userBio.text=it.user.bio
            tvbio.text=it.user.bio
            emailId.text=it.user.email
            Glide.with(this).load(it.user.image).circleCrop().into(profileImage)
        }
    }
}