package com.aryan.bottomnavlibrary;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;


public final class FragmentStackNew {
    private HashMap<String, Fragment> hashMap=new HashMap<>();
    private ArrayList<String> arrayList=new ArrayList<String>();
    private String CURRENT_TAG=null;
    private int bottomMaxCount;
    private int container_id;
    private String lastFragToStay;
    private FragmentManager supportFragmentManager;
    public static final String CHILDTAG="child";
    public static final String DOBACKSTACK="dobackstack";
    private FragmentStackNew(){
    }
    public static class Builder{
         private int bottomMaxCount=3;
         private int container_id=-1;
         private String lastFragToStay;
         private FragmentStackNew fragmentStackNew=null;


        public Builder bottomMaxCount(int bottomMaxCount){
            this.bottomMaxCount=bottomMaxCount;
            return this;
        }

        public  Builder conatiner_id(int container_id){
            this.container_id=container_id;
            return this;
        }


      public Builder lastFragmentToStay(@NonNull Class<?> modelClass){
            this.lastFragToStay=modelClass.getCanonicalName();
            return this;
        }


        public FragmentStackNew build(FragmentActivity context){

            if (fragmentStackNew==null){
                fragmentStackNew=new FragmentStackNew();
            }

            //
            fragmentStackNew.bottomMaxCount=bottomMaxCount;
            fragmentStackNew.container_id=container_id;
            fragmentStackNew.lastFragToStay=lastFragToStay;
            fragmentStackNew.supportFragmentManager=  context.getSupportFragmentManager();

            //return instance of FragmentStackNew Instance
            return fragmentStackNew;
        }

    }


    public void updateFrag(@NonNull Class<?> modelClass, Bundle extradata){

    if (hashMap.get(modelClass.getCanonicalName())==null){

    try {

        Fragment instance= (Fragment) modelClass.newInstance();
        if (extradata!=null){
            instance.setArguments(extradata);
        }
        hashMap.put(modelClass.getCanonicalName(),instance);

    } catch (IllegalAccessException e) {
        e.printStackTrace();
    } catch (InstantiationException e) {
        e.printStackTrace();
    }
    }

        if (CURRENT_TAG!=null&&CURRENT_TAG.equals(modelClass.getCanonicalName())){
            return;
        }

        Fragment prev_fragment=supportFragmentManager.findFragmentByTag(modelClass.getCanonicalName());

        // if fragment is not present in stack
        if (prev_fragment==null){
            if (hashMap.get(CURRENT_TAG)==null){
                supportFragmentManager.beginTransaction().add(container_id,hashMap.get(modelClass.getCanonicalName()),modelClass.getCanonicalName()).commit();
            }else{
                supportFragmentManager.beginTransaction().hide(hashMap.get(CURRENT_TAG)).add(container_id,hashMap.get(modelClass.getCanonicalName()),modelClass.getCanonicalName()).commit();
            }

        }
        // if fragment is already added
        else{

            supportFragmentManager.beginTransaction().hide(hashMap.get(CURRENT_TAG)).show(prev_fragment).commit();
        }
        CURRENT_TAG=modelClass.getCanonicalName();
        push(CURRENT_TAG);
    }


   public String backHandling(){
        Fragment currentFrag=supportFragmentManager.findFragmentByTag(CURRENT_TAG);
        if (currentFrag!=null&&currentFrag.getChildFragmentManager()!=null&&currentFrag.getChildFragmentManager().getBackStackEntryCount()>0){
            currentFrag.getChildFragmentManager().popBackStackImmediate();
            return CHILDTAG;
        }else {
            String TAG  =  pop(CURRENT_TAG);
            if (TAG!=null){
                Fragment fragment=  supportFragmentManager.findFragmentByTag(TAG);
                supportFragmentManager.beginTransaction().hide(hashMap.get(CURRENT_TAG)).show(fragment).commit();
                CURRENT_TAG=TAG;
                return CURRENT_TAG;



            }else{
                if (CURRENT_TAG.equals(lastFragToStay)){
                  return DOBACKSTACK;
                }else{

                    Fragment latFag=  supportFragmentManager.findFragmentByTag(lastFragToStay);
                    supportFragmentManager.beginTransaction().hide(hashMap.get(CURRENT_TAG)).show(latFag).commit();
                    CURRENT_TAG=lastFragToStay;
                    return CURRENT_TAG;

                }

            }
        }
    }


    private void addAll( ArrayList<String> array){
        arrayList.addAll(array);
    }

    private void push(String tag){
        if (bottomMaxCount==arrayList.size()){
            if (remove(tag)){
                arrayList.add(tag);
            }
        }else{
            arrayList.add(tag);
        }
    }

    private String pop(String tag){
        if (remove(tag)){
            if (arrayList.size()==0){
                return null;
            }else{
                return   arrayList.get(arrayList.size()-1);
            }
        }else{
            return null;
        }
    }

   private Boolean remove( String tag){
        try {
            int index_value=arrayList.indexOf(tag);
            arrayList.remove(index_value);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    private boolean set(Object object, String fieldName, Object fieldValue) {
        Class<?> clazz = object.getClass();
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(object, fieldValue);
                return true;
            } catch (NoSuchFieldException e) {
                clazz = clazz.getSuperclass();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
        return false;
    }

    public Bundle setOutstatebundle( Bundle outState){

        outState.putString("currentTag",CURRENT_TAG);
        outState.putStringArrayList("fragStack",arrayList);
        return outState;
    }



    public void savedInstanceState(Bundle savedInstanceState){

        ArrayList<String> stack=savedInstanceState.getStringArrayList("fragStack");

        for(int i=0;i<=stack.size()-1;i++){
          Fragment  fragment=  supportFragmentManager.findFragmentByTag(stack.get(i));

    if (fragment!=null){
     hashMap.put(stack.get(i),fragment);
     supportFragmentManager.beginTransaction().remove(fragment).commitNow();
     updateFrag(fragment.getClass(),null);
  }
        }
    }
}
