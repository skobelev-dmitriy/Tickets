package ru.adventurers.pildarok;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmQuery;
import ru.adventurers.pildarok.base.BasePresenter;
import ru.adventurers.pildarok.data.DataManager;
import ru.adventurers.pildarok.models.Ticket;
import rx.Subscriber;
import rx.Subscription;
import ru.adventurers.pildarok.models.Error;



public class TicketListPresenter extends BasePresenter<TicketListMVPView> {
    private final DataManager dataManager;
    private Subscription subscription;
    final Realm realm = Realm.getDefaultInstance();

    @Inject
    public TicketListPresenter(DataManager dataManager){this.dataManager=dataManager;
    }
    @Override
    public void attachView(TicketListMVPView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if(subscription!=null) subscription.unsubscribe();
    }
    public void getTickets() {
        checkViewAttached();
        subscription=realm.where(Ticket.class).findAllAsync().asObservable()
                .subscribe(new Subscriber<List<Ticket>>() {
                    @Override
                    public void onCompleted() {
                        Log.d("ATMPresenter","onComplited");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("ATMPresenter","onError "+e.getMessage());
                        Error error=new  Error("network",e.getMessage());
                        getMvpView().onError(error);

                    }

                    @Override
                    public void onNext(List<Ticket> response) {
                        Log.d("ATMPresenter","onNext");
                        getMvpView().onSuccess(realm.copyFromRealm(response));
                    }
                });
        Log.d("ATMPresenter","onComplited");
    }
    public void getTickets(String search) {
        checkViewAttached();
        subscription=realm.where(Ticket.class).contains("name", search).findAllAsync().asObservable()
                .subscribe(new Subscriber<List<Ticket>>() {
                    @Override
                    public void onCompleted() {
                        Log.d("ATMPresenter","onComplited");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("ATMPresenter","onError "+e.getMessage());
                        Error error=new  Error("network",e.getMessage());
                        getMvpView().onError(error);

                    }

                    @Override
                    public void onNext(List<Ticket> response) {
                        Log.d("ATMPresenter","onNext");
                        getMvpView().onSuccess(realm.copyFromRealm(response));
                    }
                });
        Log.d("ATMPresenter","onComplited");
    }

}

