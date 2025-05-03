package org.example.domain.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Id;


import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public class Entity<ID> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

    public void setId(ID id){
        this.id = id;
    }

    public ID getId(){
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity<?> entity = (Entity<?>) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
