package model;

import java.time.LocalDate;

public class EvidenceModel {
    private String fromWho;
    private LocalDate date;
    private String type;
    private String desc;

    public EvidenceModel(String fromWho, LocalDate date, String type, String desc) {
        this.fromWho = fromWho;
        this.date = date;
        this.type = type;
        this.desc = desc;
    }

    public String getFromWho() {
        return fromWho;
    }

    public void setFromWho(String fromWho) {
        this.fromWho = fromWho;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
