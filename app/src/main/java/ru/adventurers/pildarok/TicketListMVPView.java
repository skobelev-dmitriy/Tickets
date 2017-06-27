package ru.adventurers.pildarok;


import java.util.List;

import ru.adventurers.pildarok.base.MVPView;
import ru.adventurers.pildarok.models.Ticket;

public interface TicketListMVPView     extends MVPView {
        void onSuccess(List<Ticket> list);

    void onSuccessQuery(List<Ticket> list);
}
