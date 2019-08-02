package com.example.backstack_bottomnavigation.fragments


import android.os.Bundle
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
class MidFragment : Fragment() {
lateinit var detail:TextView
    lateinit var mrootview:View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mrootview= inflater.inflate(R.layout.fragment_mid, container, false)
   init()
        return  mrootview

    }

    private fun init() {

       detail= mrootview.findViewById(R.id.detail)
        detail.setOnClickListener {
            detail()
        }
    }

    fun detail(){
        childFragmentManager.beginTransaction().add(R.id.mid_container,DetailFragment()).addToBackStack(null).commit()
    }

    fun getchildFragmentManager(): FragmentManager {
        return childFragmentManager

    }


}
