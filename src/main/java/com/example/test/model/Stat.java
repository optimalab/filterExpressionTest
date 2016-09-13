package com.example.test.model;

import java.io.Serializable;
import java.util.Objects;

public class Stat implements Serializable {

    private String question;
    private String answer;
    private String profile;
    private Long count;

    public String getQuestion() {
        return question;
    }

    public void setQuestion( String question ) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer( String answer ) {
        this.answer = answer;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile( String profile ) {
        this.profile = profile;
    }

    public Long getCount() {
        return count;
    }

    public void setCount( Long count ) {
        this.count = count;
    }

    @Override
    public boolean equals( Object o ) {
        if( this == o ) return true;
        if( o == null || getClass() != o.getClass() ) return false;
        Stat that = (Stat)o;
        return Objects.equals( question, that.question ) &&
            Objects.equals( answer, that.answer ) &&
            Objects.equals( profile, that.profile ) &&
            Objects.equals( count, that.count );
    }

    @Override
    public int hashCode() {
        return Objects.hash( question, answer, profile, count );
    }

    @Override
    public String toString() {
        return "OpinionStat{" + "question='" + question + '\'' +
            ", answer='" + answer + '\'' +
            ", profile='" + profile + '\'' +
            ", count=" + count +
            '}';
    }
}
