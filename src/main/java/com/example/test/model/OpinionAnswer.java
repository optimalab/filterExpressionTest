package com.example.test.model;

import java.io.Serializable;
import java.util.Objects;

public class OpinionAnswer implements Serializable {

    private static final long serialVersionUID = -1903228435614977002L;

    public OpinionAnswer() {
    }

    public OpinionAnswer( Integer question, Integer answer ) {

        this.question = question;
        this.answer = answer;
    }

    private Integer question;
    private Integer answer;

    public Integer getQuestion() {
        return question;
    }

    public void setQuestion( Integer question ) {
        this.question = question;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer( Integer answer ) {
        this.answer = answer;
    }

    @Override
    public boolean equals( Object o ) {
        if( this == o ) return true;
        if( o == null || getClass() != o.getClass() ) return false;
        OpinionAnswer that = (OpinionAnswer)o;
        return Objects.equals( question, that.question ) &&
            Objects.equals( answer, that.answer );
    }

    @Override
    public int hashCode() {
        return Objects.hash( question, answer );
    }

    @Override
    public String toString() {
        return "OpinionAnswer{" + "question=" + question +
            ", answer=" + answer +
            '}';
    }
}
