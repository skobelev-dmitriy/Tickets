package ru.adventurers.pildarok;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import ru.adventurers.pildarok.base.BasePresenter;
import ru.adventurers.pildarok.data.DataManager;
import ru.adventurers.pildarok.models.Error;
import ru.adventurers.pildarok.models.Ticket;
import rx.Subscriber;
import rx.Subscription;


public class TicketPresenter extends BasePresenter<TicketCardMVPView> {
    private final DataManager dataManager;
    private Subscription subscription;
    final Realm realm = Realm.getDefaultInstance();

    @Inject
    public TicketPresenter(DataManager dataManager){this.dataManager=dataManager;
    }
    @Override
    public void attachView(TicketCardMVPView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if(subscription!=null) subscription.unsubscribe();
    }

    public void getTicket(String code) {
        checkViewAttached();

         Ticket ticket=dataManager.getTicketInfo(code);
        getMvpView().onSuccess(ticket);


    }
    public void setTicket(Ticket ticket) {
        checkViewAttached();

        dataManager.setTicketInfo(ticket);
        getMvpView().onSuccess(ticket);


    }

}

