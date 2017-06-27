package ru.adventurers.pildarok.injection.component;

import dagger.Component;
import ru.adventurers.pildarok.StartActivity;
import ru.adventurers.pildarok.TicketCardActivity;
import ru.adventurers.pildarok.TicketsListActivity;
import ru.adventurers.pildarok.injection.PerActivity;
import ru.adventurers.pildarok.injection.module.ActivityModule;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {


    void inject(StartActivity startActivity);
    void inject(TicketsListActivity ticketsListActivity);
    void inject(TicketCardActivity ticketCardActivity);


    //void injectFragment(StatementFragment statementFragment);


}

