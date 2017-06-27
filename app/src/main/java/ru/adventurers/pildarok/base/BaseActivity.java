package ru.adventurers.pildarok.base;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import ru.adventurers.pildarok.PilApplication;
import ru.adventurers.pildarok.injection.component.ActivityComponent;
import ru.adventurers.pildarok.injection.component.ApplicationComponent;
import ru.adventurers.pildarok.injection.component.DaggerActivityComponent;
import ru.adventurers.pildarok.injection.module.ActivityModule;

public  class BaseActivity extends AppCompatActivity {

    private ActivityComponent mActivityComponent;
    public ActivityComponent activityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(PilApplication.get(this).getComponent())
                    .build();
        }
        return mActivityComponent;
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                FragmentManager fm = getFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                } else {
                    finish();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected ApplicationComponent applicationComponent() {
        return PilApplication.get(this).getComponent();
    }


}
