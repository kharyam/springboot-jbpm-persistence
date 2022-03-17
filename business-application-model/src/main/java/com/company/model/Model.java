package com.company.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "cs_model")

public class Model implements Serializable {
    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "MODEL_ID_GENERATOR", strategy = javax.persistence.GenerationType.AUTO)
    @SequenceGenerator(name = "MODEL_ID_GENERATOR", sequenceName = "MODEL_ID_SEQ", allocationSize = 1)
    protected Long id;
    
    private String field1;
    private String field2;
    private String field3;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getField1() {
        return field1;
    }
    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }
    public void setField2(String field2) {
        this.field2 = field2;
    }

    public String getField3() {
        return field3;
    }
    public void setField3(String field3) {
        this.field3 = field3;
    }

    @Override
    public String toString() {
        return "Field1: \"" + field1 + "\" Field2: \"" + field2 + "\" Field3: \"" + field3 +'\"';
    }

}