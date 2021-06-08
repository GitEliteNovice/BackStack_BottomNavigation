# Bottomnavlibrary v1.01

 [![API](https://img.shields.io/badge/API-15%2B-red.svg)](https://android-arsenal.com/api?level=15) [![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-BottomNavigation-green.svg?style=flat )]( https://android-arsenal.com/details/1/7815 ) [![Twitter URL](https://img.shields.io/twitter/url/https/twitter.com/fold_left.svg?style=social&label=Follow%20%40elite_novice)](https://twitter.com/elite_novice) [![Maintenance](https://img.shields.io/badge/Maintained%3F-yes-green.svg)](https://GitHub.com/Naereen/StrapDown.js/graphs/commit-activity) [![Ask Me Anything !](https://img.shields.io/badge/Ask%20me-anything-1abc9c.svg)](https://GitHub.com/Naereen/ama) [![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](http://makeapullrequest.com) [![ForTheBadge built-with-love](http://ForTheBadge.com/images/badges/built-with-love.svg)](https://GitHub.com/Naereen/)[![](https://jitpack.io/v/GitEliteNovice/BackStack_BottomNavigation.svg)](https://jitpack.io/#GitEliteNovice/BackStack_BottomNavigation)

## How this is helpful for you?
With the help of this library , handling fragment will be Piece of cake for you.
This library helps you to keeps history of previous tabs so that when the current tab's stack is exhausted the system back button will navigate to the previously selected tab.
If you want to switch between fragments with botttomnavigation view, without losing fragments state and along with if you want to maintain backstack of fragments. Then this library  is for you.


**Note:-This example handles backstack and even app restores when after killed by Android System, all backstacks are preserved. 


If you want to say thanks or want to share how much you like my efforts, then you can share it with me with [![Say Thanks!](https://img.shields.io/badge/Say%20Thanks-!-1EAEDB.svg)](https://saythanks.io/to/GitEliteNovice) 



![Webp net-resizeimage](https://user-images.githubusercontent.com/15318984/62406658-e6b3a700-b5cc-11e9-9d5e-d1938099aa12.png) ![Webp net-resizeimage (1)](https://user-images.githubusercontent.com/15318984/62406685-224e7100-b5cd-11e9-80d9-0984a98b9455.png) ![Webp net-resizeimage (2)](https://user-images.githubusercontent.com/15318984/62406707-8d984300-b5cd-11e9-98d9-175e87ee3122.png)




## Includle this library in your project 
 ### For Gradle
  
  Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	         implementation 'com.github.GitEliteNovice:BackStack_BottomNavigation:v1.01'
	}

### For Maven
           <repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
	
Step 2. Add the dependency

	<dependency>
	   <groupId>com.github.GitEliteNovice</groupId>
	    <artifactId>BackStack_BottomNavigation</artifactId>
	    <version>v1.01</version>
	</dependency>

### For implementation

Make instance of Library like this

     fragmentStackNew= com.aryan.bottomnavlibrary.FragmentStackNew.Builder()
                         .bottomMaxCount(3)                      // bottom tabs you have 
                         .conatiner_id(R.id.frag_container)     // container id when you want add fragments	  	         
			 .lastFragmentToStay(HomeFragment::class.java).   // last fragment to reach when all fragment removed from backstack
                         .build(this)            

     fragmentStackNew.updateFrag(HomeFragment::class.java,null).       // add fragment to show 
	     
	     
	     
If you want to send extra data to fragment, you can do something like this
 
     bundle= Bundle()
     bundle.putString("Name","Aryan")
     bundle.putInt("Age",27)
	   	    
     fragmentStackNew.updateFrag(HomeFragment::class.java,bundle).    // send extra data to fragment
	     
If you want to handle state even when the activity kills by andoid os

     override fun onSaveInstanceState(outState: Bundle) {
         var outStatenew=  fragmentStackNew.setOutstatebundle(outState).    // add oustate bundle to library instance
          super.onSaveInstanceState(outStatenew)
       }
	   
	   
Inside onCreate method, do something like this
	   
    if (savedInstanceState==null){

     fragmentStackNew= com.aryan.bottomnavlibrary.FragmentStackNew.Builder()
                           .bottomMaxCount(3)
                           .conatiner_id(R.id.frag_container).
                            lastFragmentToStay(HomeFragment::class.java)
                             .build(this)

           bundle.putString("Name","Aryan")
           bundle.putInt("Age",27)
      
       fragmentStackNew.updateFrag(HomeFragment::class.java,bundle)
      
       }
    else
      {

    fragmentStackNew= com.aryan.bottomnavlibrary.FragmentStackNew.Builder()
        .bottomMaxCount(3)
        .conatiner_id(R.id.frag_container).
        lastFragmentToStay(HomeFragment::class.java)
        .build(this)
             
	fragmentStackNew.savedInstanceState(savedInstanceState)

       }

### This example includes following things 
* BottomNavLibrary
* BottomNavigationView


 <p align="center">
<br>
<b>Android library to keeps a history of previous tabs </b> 
</p>
 <p align="center">
 Built with ❤︎ by <a href="https://medium.com/@EliteNovice">Aryan Dhankar</a>.  
 </p>



Connect With Me
-----------

Aryan Dhankar (Elite Novice)
I love making new friends, please feel free to connect with me.

<a href="https://plus.google.com/u/0/+AryanDhankar">
  <img alt="Connect me on Google+" src="/art/gplus.png" />
</a>
<a href="https://www.facebook.com/aryan.dhankar.3">
  <img alt="Connect me on Facebook" src="/art/fb.png" width="64" height="64" />
</a>
<a href="https://www.linkedin.com/in/aryan-dhankar-961b50117/">
  <img alt="Connect me on LinkedIn" src="/art/linkedin.png" />
</a>


Question / Contact Me
---------------------
Please feel free to ping me at **aryandhankar11@gmail.com**.
