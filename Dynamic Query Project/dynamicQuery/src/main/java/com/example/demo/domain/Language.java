package com.example.demo.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "language", schema = "dynamicQuery")
@Getter
@Setter
public class Language implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "language_id", unique = true, nullable = false)
    private int languageId;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update", nullable = false, length = 29)
    private Date lastUpdate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "language")
    private Set<Film> films = new HashSet<>(0);
}