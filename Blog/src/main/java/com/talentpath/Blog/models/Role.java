package com.talentpath.Blog.models;

import javax.annotation.processing.Generated;
import javax.persistence.*;

@Entity //tells Hibernate that this is a class to pay attention to at all
@Table(name="roles") //tells Hibernate which table to look at
public class Role {

    @Id //this tells Hibernate this field is involved in the primary key
    @GeneratedValue( strategy= GenerationType.IDENTITY) //tell Hibernate to create this column as a serial
    private Integer id;

    @Enumerated( EnumType.STRING )  //tell Hibernate that the set of allowable values should be constrained
    @Column( length = 20 ) //tell Hibernate to set up the column to be 20 characters wide
    private RoleName name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (id != null ? !id.equals(role.id) : role.id != null) return false;
        return name == role.name;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public enum RoleName {
        ROLE_USER,
        ROLE_ADMIN,
        ROLE_AUTHOR
    }
}
