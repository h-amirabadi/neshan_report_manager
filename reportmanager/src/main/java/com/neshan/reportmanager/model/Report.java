package com.neshan.reportmanager.model;


import javax.persistence.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
public class Report {
    @Id
    @SequenceGenerator(
            name = "report_sequence",
            sequenceName = "report_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "report_sequence"
    )
    @Column(nullable = false, updatable = false)
    private Long id;
    private short type;
    private String x;
    private String y;

    @Column(
            name = "created_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITH TIME ZONE"
    )
    private Timestamp createdAt;

    @Column(
            name = "active_until",
            nullable = false,
            columnDefinition = "TIMESTAMP WITH TIME ZONE"
    )
    private Timestamp activeUntil;

    @ManyToOne
    @JoinColumn(
            name = "account_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "fk_report_account"
            )
    )
    private Account account;

    public Report() {
    }

    public Report(short type, String x, String y, Timestamp createdAt, Timestamp activeUntil, Account account) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.createdAt = createdAt;
        this.activeUntil = activeUntil;
        this.account = account;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getActiveUntil() {
        return activeUntil;
    }

    public void setActiveUntil(Timestamp activeUntil) {
        this.activeUntil = activeUntil;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", type=" + type +
                ", x='" + x + '\'' +
                ", y='" + y + '\'' +
                ", createdAt=" + createdAt +
                ", activeUntil=" + activeUntil +
                ", account=" + account +
                '}';
    }


    public enum TYPE {
        ACCIDENT((short) 1), TRAFFIC((short) 2), POLICE((short) 3);
        private static Map<Short, TYPE> map = new HashMap<>();

        static {
            for (TYPE item : values()) {
                map.put(item.getID(), item);
            }
        }

        private short ID;

        TYPE(short ID) {
            this.ID = ID;
        }

        public static Map<Short, TYPE> getMap() {
            return map;
        }

        public static TYPE valueOf(short ID) {
            return map.get(ID);
        }

        public short getID() {
            return this.ID;
        }
    }

}
