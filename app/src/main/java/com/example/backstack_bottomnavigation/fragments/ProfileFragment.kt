package com.example.backstack_bottomnavigation.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.example.backstack_bottomnavigation.R


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ProfileFragment : Fragment() {

    lateinit var more_about_me:TextView
    lateinit var mrootView: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mrootView= inflater.inflate(R.layout.fragment_profile, container, false)
   init()
    return mrootView
    }

    private fun init() {

        more_about_me=mrootView.findViewById(R.id.more_about_me)
        more_about_me.setOnClickListener {
            more_about_me()
        }
    }

    fun more_about_me(){

        childFragmentManager.beginTransaction().add(R.id.profile_conainer,MyInfoFragment()).addToBackStack(null).commit()

    }

    fun getchildFragmentManager(): FragmentManager {
        return childFragmentManager

    }

}
