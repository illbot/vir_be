package com.example.kepbongeszo_be.Model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TestEntity")
public class TestEntity {

    @Id
    @GeneratedValue
    private long id;

    private String valami;

    public TestEntity() {
        this.valami = "string";
    }

    public TestEntity(String valami) {
        this.valami = valami;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValami() {
        return valami;
    }

    public void setValami(String valami) {
        this.valami = valami;
    }
}
