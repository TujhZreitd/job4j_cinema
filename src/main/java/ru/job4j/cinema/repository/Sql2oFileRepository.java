package ru.job4j.cinema.repository;

import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.File;

import java.util.Locale;
import java.util.Optional;


@Repository
public class Sql2oFileRepository implements FileRepository {
    private final Sql2o sql2o;

    public Sql2oFileRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Optional<File> findById(int id) {
        try (Connection connection = sql2o.open()) {
            Query query = connection.createQuery("SELECT * FROM files WHERE id = :id")
                    .addParameter("id", id);
            File file = query.executeAndFetchFirst(File.class);
            return Optional.ofNullable(file);
        }
    }
/*
    @Override
    public File save(File file) {
        try (Connection connection = sql2o.open()) {
            String sql = """
                    INSERT INTO files(name, path)
                    VALUES(:name, :path)
                    """;
            Query query = connection.createQuery(sql, true)
                    .addParameter("name", file.getName())
                    .addParameter("path", file.getPath());
            int generatedId = query.executeUpdate().getKey(Integer.class);
            file.setId(generatedId);
            return file;
        }
    }*/
/*
    @Override
    public boolean deleteById(int id) {
        try (Connection connection = sql2o.open()) {
            Query query = connection.createQuery("DELETE FROM files WHERE id = :id")
                    .addParameter("id", id);
            return query.executeUpdate().getResult() > 0;
        }
    }*/
}
