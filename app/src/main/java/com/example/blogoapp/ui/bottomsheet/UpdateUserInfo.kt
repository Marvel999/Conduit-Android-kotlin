package com.example.blogoapp.ui.bottomsheet

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.blogoapp.R
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

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

    }


}