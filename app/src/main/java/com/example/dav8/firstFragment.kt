package com.example.dav8

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [firstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class firstFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var addbutton : Button
    private lateinit var nameInput : EditText
    private lateinit var emailInput : EditText
    private lateinit var numInput : EditText
    private lateinit var locInput : EditText

    lateinit var utils : Utils
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment firstFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            firstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addbutton  = requireView().findViewById(R.id.addbutton)
        utils = Utils()
        nameInput = requireView().findViewById(R.id.itemName)
        emailInput = requireView().findViewById(R.id.itemMail)
        numInput = requireView().findViewById(R.id.itemNum)
        locInput = requireView().findViewById(R.id.itemLoc)

        addbutton.setOnClickListener {
            if(nameInput.text.toString()!=""&&emailInput.text.toString()!=""&&numInput.text.toString()!=""&&locInput.text.toString()!=""){
                (activity as MainActivity).addables = utils.addNewItem((activity as MainActivity).addables, nameInput.text.toString(),emailInput.text.toString(),numInput.text.toString(),locInput.text.toString())
                nameInput.setText("")
                emailInput.setText("")
                numInput.setText("")
                locInput.setText("")
            }

        }
    }
}