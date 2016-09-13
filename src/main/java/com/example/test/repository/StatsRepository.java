package com.example.test.repository;

import com.example.test.model.Opinion;
import com.example.test.model.Stat;

import java.util.List;

public interface StatsRepository {

    void deleteAll();

    Opinion save( Opinion opinion );

    List<Stat> getOpinionStatForSurveyAndProfileQuestion( String surveyId, int profileAnswer );

}
