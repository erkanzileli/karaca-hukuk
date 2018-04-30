package entity;

import javax.persistence.*;

@Entity
@Table(name = "Question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idQuestion;

    private int idLawsuit;

    private String question;

    private boolean ansver;

    public Question() {
    }

    public Question(int idLawsuit, String question, boolean ansver) {
        this.idLawsuit = idLawsuit;
        this.question = question;
        this.ansver = ansver;
    }

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public int getIdLawsuit() {
        return idLawsuit;
    }

    public void setIdLawsuit(int idLawsuit) {
        this.idLawsuit = idLawsuit;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isAnsver() {
        return ansver;
    }

    public void setAnsver(boolean ansver) {
        this.ansver = ansver;
    }
}
