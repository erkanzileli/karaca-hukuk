package entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Log")
public class Log {

    @Id
    @Column(name = "idLog")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLog;

    @Column(name = "createDate")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "description")
    private String description;

    public Log() {
    }

    public Log(Date date, String description) {
        this.date = date;
        this.description = description;
    }

    public int getIdLog() {
        return idLog;
    }

    public void setIdLog(int idLog) {
        this.idLog = idLog;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
