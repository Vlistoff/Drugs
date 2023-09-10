package ru.vlistoff.drugstore.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private Manufacturer manufacturer;
    @Column(nullable = false)
    private String tradeName;
    @Column(nullable = false)
    private String internationalName;
    @Column(nullable = false)
    private String dose;
    @Column(nullable = false)
    private String form;
    @ManyToOne
    @JoinColumn(name = "measure_units_id", nullable = false)
    private MeasureUnits measureUnits;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Drug drug = (Drug) o;
        return getId() != null && Objects.equals(getId(), drug.getId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}
