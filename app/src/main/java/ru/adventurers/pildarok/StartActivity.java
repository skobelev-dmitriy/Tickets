package ru.adventurers.pildarok;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import ru.adventurers.pildarok.Utils.DateUtils;
import ru.adventurers.pildarok.Utils.DialogUtils;
import ru.adventurers.pildarok.base.BaseActivity;
import ru.adventurers.pildarok.data.DataManager;
import ru.adventurers.pildarok.models.Ticket;
import ru.adventurers.pildarok.models.TicketResponse;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class StartActivity extends BaseActivity {

    Button button_scan;
    Button button_choose;
    @Inject
    DataManager dataManager;

    private Realm realm;
    Subscription getTickets;
    Dialog progressDialog;
    List<Ticket> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityComponent().inject(this);
        setContentView(R.layout.activity_login);
        initViews();
    }
    @Override
    protected void onStart() {
        super.onStart();
        realm = Realm.getDefaultInstance();
    }
    public void initViews(){
        list=new ArrayList<>();
        button_scan=(Button)findViewById(R.id.scan_button);
        button_choose=(Button)findViewById(R.id.choose_button);
        if(dataManager.getPreferencesHelper().getAtmUpdateDate()==null) {
            getTickets=getTicketsoffline();
            progressDialog=DialogUtils.showProgressDialog(this);
            progressDialog.show();
        }
        button_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new IntentIntegrator(StartActivity.this)
                        .setCaptureActivity(AnyOrientationCaptureActivity.class)
                        .initiateScan();
            }
        });
        button_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(StartActivity.this, TicketsListActivity.class);
                startActivity(intent);
            }
        });
    }
    private Subscription getTicketsoffline(){
        Observable<TicketResponse> atmObservableJson=dataManager.getTicketsFromJSon(StartActivity.this)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Subscription atmSubscription=atmObservableJson.subscribe(new Subscriber<TicketResponse>() {
            @Override
            public void onCompleted() {

                progressDialog.cancel();
                dataManager.getPreferencesHelper().setAtmUpdateDate(DateUtils.getDateUpdate());
            }

            @Override
            public void onError(Throwable e) {
                progressDialog.cancel();
            }

            @Override
            public void onNext(TicketResponse response) {
                List<Ticket> list=response.getTickets();
                realm.beginTransaction();
                realm.copyToRealmOrUpdate(list);
                realm.commitTransaction();
                Log.d("StartActivity","Сделано "+list.size()+" записей");
                realm.close();

            }
        });
        return atmSubscription;

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Log.d("TicketsListActivity", "Cancelled scan");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();

            } else {
                Log.d("TicketsListActivity", "Scanned");
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                openGood(result.getContents());
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    private void openGood(String code){
        Intent intent=new Intent(this,TicketCardActivity.class);
        intent.putExtra("code",code);
        startActivity(intent);
    }

}
