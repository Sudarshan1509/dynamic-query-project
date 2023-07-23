package com.example.demo.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "film_category", schema = "dynamicQuery")
@Getter
@Setter
public class FilmCategory implements Serializable {

    private static final long serialVersionUID = -774398076922529150L;

    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "filmId", column = @Column(name = "film_id", nullable = false)),
        @AttributeOverride(name = "categoryId", column = @Column(name = "category_id", nullable = false))
    })
    private FilmCategoryId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id", nullable = false, insertable = false, updatable = false)
    private Film film;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false, insertable = false, updatable = false)
    private Category category;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update", nullable = false, length = 29)
    private Date lastUpdate;
}