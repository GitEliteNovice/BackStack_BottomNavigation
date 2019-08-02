package com.example.backstack_bottomnavigation

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.backstack_bottomnavigation.R
import com.example.backstack_bottomnavigation.fragments.HomeFragment
import com.example.backstack_bottomnavigation.fragments.MidFragment
import com.example.backstack_bottomnavigation.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import java.util.*

 class MainActivity : AppCompatActivity() {
  private lateinit var bottomNavigation:BottomNavigationView
     private var currentTag:String="null"
     private var currentFragment:Fragment?=null
      private lateinit var homeFragment:Fragment
     private lateinit var midFragment:Fragment
     private lateinit var profileFragment:Fragment
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//doWork()
         init()
    }



     fun doWork(){
         val array= arrayOf(2,3,2,3,5)
         val hashmap=HashMap<Int,Int>()
         for (i in 0.. array.size-1){
             if (hashmap.containsKey(array[i])){
                 hashmap.put(array[i],(hashmap.get(array[i]) as Int)+1)
             }else{
                 hashmap.put(array[i],1)
             }
         }
         publishResult(hashmap)

     }

     private fun publishResult(hashmap: HashMap<Int, Int>) {
         var iterator=hashmap.entries.iterator()
         while (iterator.hasNext()){
             var entry =iterator.next()
             Log.d("Aryan","${entry.key}--"+entry.value)
         }

     }

     private fun init() {
         homeFragment= HomeFragment()
         midFragment= MidFragment()
         profileFragment= ProfileFragment()

         bottomNavigation=findViewById(R.id.btm_nav)
         bottomNavigation.setOnNavigationItemSelectedListener(object :BottomNavigationView.OnNavigationItemSelectedListener{
             override fun onNavigationItemSelected(item: MenuItem): Boolean {
                 when(item.itemId){
                     R.id.action_home->{


                         updateFragment(homeFragment,"home")
                     return true
                     }
                     R.id.action_mid->{

                         updateFragment(midFragment,"mid")
                     return true
                     }
                     R.id.action_profile->{
                         updateFragment(profileFragment,"profile")
            return true
                     }

                 }
return false

             }

         })
         updateFragment(homeFragment,"home")
     }

     fun updateFragment(fragment:Fragment, tag:String){

         if (currentTag.equals(tag)){
             return
         }
         var prev_fragment=supportFragmentManager.findFragmentByTag(tag)

         // if fragment is not present in stack
         if (prev_fragment==null){
             if (currentFragment==null){
                 supportFragmentManager.beginTransaction().add(R.id.frag_container,fragment,tag).commit()
             }else{
                 supportFragmentManager.beginTransaction().hide(currentFragment!!).add(R.id.frag_container,fragment,tag).commit()
             }

         }
       // if fragment is already added
         else{
             supportFragmentManager.beginTransaction().hide(currentFragment!!).show(prev_fragment).commit()
         }
         FragmentStack.getInstance(3).push(tag)
         currentFragment=fragment
         currentTag=tag

     }

     override fun onBackPressed() {

         var currentFrag=supportFragmentManager.findFragmentByTag(currentTag)

         var childfragmanager: FragmentManager?=null
         if (currentFrag is ProfileFragment){
             childfragmanager=currentFrag.getchildFragmentManager()
         }else if (currentFrag is MidFragment){

             childfragmanager=currentFrag.getchildFragmentManager()
         }else if (currentFrag is HomeFragment){

             childfragmanager=currentFrag.getchildFragmentManager()
         }
         if (childfragmanager?.backStackEntryCount!!>0){
             childfragmanager.popBackStackImmediate()
         }
         else{

             var toptag  =  FragmentStack.getInstance(3).pop(currentTag)
             if (toptag!=null){
                 var topfrag=  supportFragmentManager.findFragmentByTag(toptag)
                 supportFragmentManager.beginTransaction().hide(currentFragment!!).show(topfrag!!).commit()
                 currentTag=toptag
                 currentFragment=topfrag
                 if (currentTag.equals("home")){
                     bottomNavigation.selectedItemId=R.id.action_home
                 }else if (currentTag.equals("mid")){

                     bottomNavigation.selectedItemId=R.id.action_mid
                 }else if (currentTag.equals("profile")){

                     bottomNavigation.selectedItemId=R.id.action_profile
                 }


             }else{
                 if (currentTag.equals("home")){
                     super.onBackPressed()
                 }else{

                     var newfrag=  supportFragmentManager.findFragmentByTag("home")
                     supportFragmentManager.beginTransaction().hide(currentFragment!!).show(newfrag!!).commit()
                     currentTag="home"
                     currentFragment=newfrag

                     bottomNavigation.selectedItemId=R.id.action_home
                 }

             }

         }




     }
}
