package ru.vlistoff.drugstore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Purchases {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "requests_id")
    private Requests requests;
    @ManyToOne
    @JoinColumn(name = "drug_id")
    private Drug drug;
    @Column(nullable = false)
    private LocalDate releaseDate;
    @Column(nullable = false)
    private LocalDate expirationDate;
    @Column(nullable = false)
    private int quantityRequested;
    @Column(nullable = false)
    private int quantityReleased;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Purchases purchases = (Purchases) o;
        return getId() != null && Objects.equals(getId(), purchases.getId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}
