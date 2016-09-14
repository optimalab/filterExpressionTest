package com.example.test.repository;

import com.example.test.model.Opinion;
import com.example.test.model.Stat;
import com.mongodb.BasicDBObject;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.mongodb.core.aggregation.FilterExpression;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class StatsRepositoryImpl implements StatsRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public void deleteAll() {
        mongoTemplate.remove( new Query(), Opinion.class );
    }

    public Opinion save( Opinion opinion ) {
        mongoTemplate.save( opinion );
        return opinion;
    }


    /*
    db.getCollection('opinion').aggregate([
            //{ "$lookup": { "from": "jhi_user", "localField": "user_login", "foreignField": "login", "as": "profile" } },
            { "$match" : { "survey_id" : ObjectId("5785144c7d844dad0a3dde6f") } },
            { "$project": { 'answer_list': 1, 'profile': { $filter : { input: '$answer_list', as: 'answer', cond: { $eq: [ '$$answer.question', 2 ] } } } } },
            { "$unwind" : "$profile"},
            { "$unwind" : "$answer_list"},
            { "$group" : { "_id" : { "question" : "$answer_list.question", "answer" : "$answer_list.answer", "criteria" : "$profile.answer"}, "count" : { "$sum" : 1 } } },
            { "$sort" : { "_id.question" : 1, "_id.answer" : 1 } }
    ]);
    */
    @Override
    public List<Stat> getOpinionStatForSurveyAndProfileQuestion( String surveyId, int profileAnswer ) {

        FilterExpression expression = new FilterExpression( "$answer_list", "answer", new BasicDBObject( "$eq", Arrays.<Object>asList( "$$answer.question", profileAnswer ) ) );
        System.out.println( "expression.toDbObject(  ) = " + ((BasicDBObject)expression.toDbObject( DEFAULT_CONTEXT ) ).toJson() );
        Aggregation aggregation = newAggregation(
            match( Criteria.where( "survey_id" ).is( surveyId ) ),
            project().andInclude( Fields.from(Fields.field("answers", "$answerList"))).and(expression).as("profile"),
            unwind( "$answers" ),
            unwind( "$profile" ),
            group( Fields.from( Fields.field("question","answers.question"), Fields.field("answer","answers.answer"), Fields.field("profile","profile.answer") ) ).count().as( "count" ),
            sort( Sort.Direction.ASC, "_id.question", "_id.answer", "_id.criteria" )
        );

        AggregationResults<Stat> list = mongoTemplate.aggregate( aggregation, Opinion.class, Stat.class );
        List<Stat> result = list.getMappedResults();

        System.out.println( "result = " + result );
        Assertions.assertThat(list).isNotEmpty();

        return result;

    }

}
