package com.example.backstack_bottomnavigation.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.com.getin.Callbacks.CallBack
import com.example.backstack_bottomnavigation.R
import com.example.backstack_bottomnavigation.adapters.Adapter


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class MidFragment : Fragment(), CallBack {
    override fun ItemClicked(pos: Int) {
        childFragmentManager.beginTransaction().add(R.id.mid_container,DetailFragment()).addToBackStack(null).commit()
    }

    lateinit var mrootview:View
    lateinit var recyler_view:RecyclerView
    lateinit var linearLayoutManager:LinearLayoutManager
    lateinit var adapter:Adapter
    lateinit var arrayList:ArrayList<String>
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
        arrayList=ArrayList<String>()
        arrayList.addAll(resources.getStringArray(R.array.hero_names))
        recyler_view=mrootview.findViewById(R.id.recyler_view)
        linearLayoutManager= LinearLayoutManager(this.activity, RecyclerView.VERTICAL,false)
        recyler_view.layoutManager=linearLayoutManager
        adapter= Adapter(activity!!,arrayList,this)
        recyler_view.adapter=adapter

    }

    fun getchildFragmentManager(): FragmentManager {
        return childFragmentManager

    }


}
