package ru.adventurers.pildarok;


import java.util.List;

import ru.adventurers.pildarok.base.MVPView;
import ru.adventurers.pildarok.models.Ticket;

public interface TicketCardMVPView extends MVPView {

    void onSuccess(Ticket ticket);
    void onSuccessQuery(List<Ticket> list);
}
