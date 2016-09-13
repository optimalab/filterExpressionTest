package com.example.test.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Document(collection = "opinion")
public class Opinion implements Serializable {

    private static final long serialVersionUID = 656414171115349216L;

    @Id
    private String id;

    @Field("survey_id")
    private String survey;

    @Field("user_login")
    private String user;

    @Field("answer_list")
    private List<OpinionAnswer> answerList;

    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public String getSurvey() {
        return survey;
    }

    public void setSurvey( String survey ) {
        this.survey = survey;
    }

    public String getUser() {
        return user;
    }

    public void setUser( String user ) {
        this.user = user;
    }

    public List<OpinionAnswer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList( List<OpinionAnswer> answerList ) {
        this.answerList = answerList;
    }

    @Override
    public boolean equals( Object o ) {
        if( this == o ) return true;
        if( o == null || getClass() != o.getClass() ) return false;
        Opinion opinion = (Opinion)o;
        return Objects.equals( id, opinion.id );
    }

    @Override
    public int hashCode() {
        return Objects.hash( id );
    }

    @Override
    public String toString() {
        return "Opinion{" + "id='" + id + '\'' +
            ", survey=" + survey +
            ", user='" + user + '\'' +
            ", answerList='" + answerList + '\'' +
            '}';
    }
}
