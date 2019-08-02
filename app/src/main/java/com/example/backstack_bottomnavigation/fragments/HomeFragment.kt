package com.example.backstack_bottomnavigation.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
class HomeFragment : Fragment() {
lateinit var about_me:TextView
    lateinit var mrootView: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment doWork()
        mrootView= inflater.inflate(R.layout.fragment_home, container, false)
    init()
        return mrootView
    }

    private fun init() {

        about_me=mrootView.findViewById(R.id.about_me)
        about_me.setOnClickListener {
            about_me()
        }
    }

    fun about_me(){
    childFragmentManager.beginTransaction().add(R.id.home_container,AboutmeFragment()).addToBackStack(null).commit()
}

    fun getchildFragmentManager(): FragmentManager {
        return childFragmentManager

    }

}
