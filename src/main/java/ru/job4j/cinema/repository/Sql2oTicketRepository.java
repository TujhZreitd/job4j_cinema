package ru.job4j.cinema.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import ru.job4j.cinema.model.Ticket;

import java.util.Collection;
import java.util.Optional;

@Repository
public class Sql2oTicketRepository implements TicketRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(Sql2oTicketRepository.class);
    private final Sql2o sql2o;
    public Sql2oTicketRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Collection<Ticket> findAll() {
        try (Connection connection = sql2o.open()) {
            Query query = connection.createQuery("SELECT * FROM tickets");
            return query.setColumnMappings(Ticket.COLUMN_MAPPING).executeAndFetch(Ticket.class);
        }
    }

    @Override
    public Optional<Ticket> save(Ticket ticket) {
        try (Connection connection = sql2o.open()) {
            String sql = """
                    INSERT INTO tickets(session_id, row_number, place_number, user_id)
                    VALUES(:sessionId, :rowNumber, :placeNumber, :userId)
                    """;
            Query query = connection.createQuery(sql, true)
                    .addParameter("sessionId", ticket.getSessionId())
                    .addParameter("rowNumber", ticket.getRowNumber())
                    .addParameter("placeNumber", ticket.getPlaceNumber())
                    .addParameter("userId", ticket.getUserId());
            int generatedId = query.executeUpdate().getKey(Integer.class);
            ticket.setId(generatedId);
            return Optional.of(ticket);
        } catch (Sql2oException e) {
            LOGGER.info("Вернись сюда, может тут проблема с покупкой билетов", e);
            throw e;
        }
        /*return Optional.empty();*/
    }

    @Override
    public boolean deleteById(int id) {
        try (Connection connection = sql2o.open()) {
            Query query = connection.createQuery("DELETE FROM tickets WHERE id = :id")
                    .addParameter("id", id);
            return query.executeUpdate().getResult() > 0;
        }
    }
}
