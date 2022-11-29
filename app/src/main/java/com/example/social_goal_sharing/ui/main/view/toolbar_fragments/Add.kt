package com.example.social_goal_sharing.ui.main.view.toolbar_fragments

//import android.app.DatePickerDialog

import android.content.Intent
import android.icu.util.Calendar
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.social_goal_sharing.R
import com.example.social_goal_sharing.R.layout.fragment_add
import com.example.social_goal_sharing.databinding.FragmentAddBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
/**
 * A simple [Fragment] subclass.
 * Use the [Add.newInstance] factory method to
 * create an instance of this fragment.
 */
 class Add : Fragment(fragment_add) {
    private var imageUri: Uri? = null
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private lateinit var imgAdd : ImageView
   // private lateinit var dateEv : TextView
    private lateinit var calendar: Calendar



    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v=inflater.inflate(fragment_add, container, false)
        imgAdd= v.findViewById(R.id.imgAdd)

        //dateEv= v.findViewById(R.id.dateEv)
       calendar = Calendar.getInstance()
        imgAdd.setOnClickListener {

            startActivityForResult(
                Intent(
                    Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI
                ), 100
            )
        }


        return v

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddBinding.bind(view)
        binding.apply {
        dateEvStart.setOnClickListener {
            val datePickerFragment = DatePickerFrag()
            val supportFragmentManager = requireActivity().supportFragmentManager
            supportFragmentManager.setFragmentResultListener(
                "REQUEST_KEY",
                viewLifecycleOwner
            ) { resultKey, bundle ->
                if (resultKey == "REQUEST_KEY") {
                    val date = bundle.getString("SELECTED_DATE")

                        dateEvStart.setText(date)
                }
            }
            datePickerFragment.show(supportFragmentManager, "DatePickerFrag")
                }
            dateEvEnd.setOnClickListener {
                val datePickerFragment = DatePickerFrag()
                val supportFragmentManager = requireActivity().supportFragmentManager
                supportFragmentManager.setFragmentResultListener(
                    "REQUEST_KEY",
                    viewLifecycleOwner
                ) { resultKey, bundle ->
                    if (resultKey == "REQUEST_KEY") {
                        val date = bundle.getString("SELECTED_DATE")

                        dateEvEnd.setText(date)
                    }
                }
                datePickerFragment.show(supportFragmentManager, "DatePickerFrag")
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == 100) {
            imageUri = data?.data
            imgAdd.setImageURI(imageUri)
        }
    }



}