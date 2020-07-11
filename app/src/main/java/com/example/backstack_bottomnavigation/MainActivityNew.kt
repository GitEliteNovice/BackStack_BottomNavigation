package com.example.backstack_bottomnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.backstack_bottomnavigation.fragments.HomeFragment
import com.example.backstack_bottomnavigation.fragments.MidFragment
import com.example.backstack_bottomnavigation.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivityNew : AppCompatActivity() {
  private lateinit var bottomNavigation:BottomNavigationView
     private lateinit var fragmentStackNew: com.aryan.bottomnavlibrary.FragmentStackNew
     private lateinit var bundle:Bundle
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         init(savedInstanceState)
    }

     private fun init(savedInstanceState: Bundle?) {
         bundle= Bundle()
         bottomNavigation=findViewById(R.id.btm_nav)

         if (savedInstanceState==null){

             fragmentStackNew= com.aryan.bottomnavlibrary.FragmentStackNew.Builder()
                              .bottomMaxCount(3)
                              .conatiner_id(R.id.frag_container).
                               lastFragmentToStay(HomeFragment::class.java)
                                .build(this)

             bundle.putString("Name","Aryan")
             bundle.putInt("Age",27)

             fragmentStackNew.updateFrag(HomeFragment::class.java,bundle)
         }else{



    fragmentStackNew= com.aryan.bottomnavlibrary.FragmentStackNew.Builder()
        .bottomMaxCount(3)
        .conatiner_id(R.id.frag_container).
            lastFragmentToStay(HomeFragment::class.java)
        .build(this)
             
             fragmentStackNew.savedInstanceState(savedInstanceState)

         }

         bottomNavigation.setOnNavigationItemSelectedListener(object :BottomNavigationView.OnNavigationItemSelectedListener{
             override fun onNavigationItemSelected(item: MenuItem): Boolean {
                 when(item.itemId){
                     R.id.action_home->{

                         fragmentStackNew.updateFrag(HomeFragment::class.java,bundle)

                     return true
                     }
                     R.id.action_mid->{
                         fragmentStackNew.updateFrag(MidFragment::class.java,null)

                     return true
                     }
                     R.id.action_profile->{
                         fragmentStackNew.updateFrag(ProfileFragment::class.java,null)

            return true
                     }

                 }
return false

             }

         })

     }



     override fun onBackPressed() {
         var returnedtag=fragmentStackNew.backHandling()
         if (returnedtag.equals(com.aryan.bottomnavlibrary.FragmentStackNew.CHILDTAG)) {
         return
         }
         if (returnedtag.equals(com.aryan.bottomnavlibrary.FragmentStackNew.DOBACKSTACK)) {
             super.onBackPressed()
         }else if (returnedtag.equals(HomeFragment::class.java.canonicalName)){
             bottomNavigation.selectedItemId=R.id.action_home
         }else if (returnedtag.equals(MidFragment::class.java.canonicalName)){
             bottomNavigation.selectedItemId=R.id.action_mid
         }else if (returnedtag.equals(ProfileFragment::class.java.canonicalName)){
             bottomNavigation.selectedItemId=R.id.action_profile
         }


     }

     override fun onSaveInstanceState(outState: Bundle) {
         var outStatenew=  fragmentStackNew.setOutstatebundle(outState)
         super.onSaveInstanceState(outStatenew)
     }
}
