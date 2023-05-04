package com.example.boot.entities.otherModel;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String createdDate=null;
    String lastModifiedDate=null;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractEntity that = (AbstractEntity) o;

        return id.equals(that.id);
    }
    @PrePersist
    public void beforeSave(){
        System.out.println("сохранили обьект в бд");
        LocalDateTime logDateTime = new Timestamp(System.currentTimeMillis()).toLocalDateTime();
        String time = logDateTime.getDayOfMonth()+"/"+
                logDateTime.getMonthValue()+"/"+
                logDateTime.getYear()+" "+
                logDateTime.getHour()+":"+
                logDateTime.getMinute()+" "+
                logDateTime.getSecond();
        this.setCreatedDate(time);
    }

    @PreUpdate
    public void PreUpdate(){
        System.out.println("обновили обьект в бд");
        LocalDateTime logDateTime = new Timestamp(System.currentTimeMillis()).toLocalDateTime();
        String time = logDateTime.getDayOfMonth()+"/"+
                logDateTime.getMonthValue()+"/"+
                logDateTime.getYear()+" "+
                logDateTime.getHour()+":"+
                logDateTime.getMinute()+" "+
                logDateTime.getSecond();
        this.setLastModifiedDate(time);
    }
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
