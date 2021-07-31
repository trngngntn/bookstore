package fa.training.dao;

import fa.training.entity.Ticket;
import fa.training.meta.TicketMeta;

public class TicketDAO extends BaseDAO<Ticket>{
    public TicketDAO() {
        super(TicketMeta.class);
    }
}
