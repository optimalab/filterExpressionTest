package com.example.test;

import com.example.test.model.Opinion;
import com.example.test.model.OpinionAnswer;
import com.example.test.repository.StatsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private StatsRepository statsRepository;

    @Before
    public void setUp() {
        statsRepository.deleteAll();
        Opinion opinion = new Opinion();
        opinion.setSurvey( "survey-1" );
        opinion.setUser( "demo" );
        opinion.setAnswerList( Arrays.asList( new OpinionAnswer( 0, 0 ), new OpinionAnswer( 1, 2 ) ) );
        statsRepository.save( opinion );

        Opinion opinion2 = new Opinion();
        opinion2.setSurvey( "survey-1" );
        opinion2.setUser( "demo2" );
        opinion2.setAnswerList( Arrays.asList( new OpinionAnswer( 0, 1 ), new OpinionAnswer( 1, 0 ) ) );
        statsRepository.save( opinion2 );
    }

    @Test
    public void testAggregation() {
        statsRepository.getOpinionStatForSurveyAndProfileQuestion( "survey-1", 1 );
    }

}
