    package ru.vlistoff.drugstore.repository;

    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;
    import ru.vlistoff.drugstore.model.Requests;

    @Repository
    public interface RequestsRepository extends JpaRepository<Requests, Long> {
    }
