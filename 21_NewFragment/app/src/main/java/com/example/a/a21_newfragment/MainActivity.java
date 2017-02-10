package com.example.a.a21_newfragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnClick(View v){
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.frame); //fragment가 올라갈 곳의 id를 적어준다. (hosting하는 곳의 id)

        switch (v.getId()){
            case R.id.add:
                if (fragment == null){
                    fragment = new BlankFragment();
                    FragmentTransaction tr = fm.beginTransaction();
                    tr.add(R.id.frame,fragment,"counter");
                    tr.addToBackStack(null);  //backstack를 추가한다.
                    tr.commit();
                }
                break;
            case R.id.remove:
                if (fragment != null){
                    FragmentTransaction tr = fm.beginTransaction();
                    tr.remove(fragment);
                    tr.commit();

                    fm.popBackStack(); //backstact을 빼준다
                }
                break;
            case R.id.replace:
                if (fragment != null){
                    FragmentTransaction tr = fm.beginTransaction();
                    if (fragment.getTag().equals("counter")){
                        TextFragment textFragment = new TextFragment();
                        tr.replace(R.id.frame,textFragment,"text");
                    }else{
                        BlankFragment blankFragment = new BlankFragment();
                        tr.replace(R.id.frame,blankFragment,"counter");
                    }
                    tr.addToBackStack(null);  //backstack를 추가한다.
                    tr.commit();
                }
                break;
            case R.id.hide:
                if (fragment != null){
                    FragmentTransaction tr = fm.beginTransaction();
                    if (fragment.isHidden()){
                        tr.show(fragment);
                    }else{
                        tr.hide(fragment);
                    }
                    tr.addToBackStack(null);  //backstack를 추가한다. //해야 할지, 안해야 할지는 고민해야함
                                                //back키에 대한 고민은 해야 함.

                    tr.commit();
                }
                break;
        }





    }
}
