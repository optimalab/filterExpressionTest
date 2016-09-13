package org.springframework.data.mongodb.core.aggregation;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class FilterExpression implements AggregationExpression {

    private String input;
    private String as;
    private BasicDBObject cond;

    public FilterExpression( String input, String as, BasicDBObject cond ) {
        this.input = input;
        this.as = as;
        this.cond = cond;
    }

    @Override
    public DBObject toDbObject( AggregationOperationContext context ) {
        DBObject filterExpression = new BasicDBObject();
        filterExpression.put("input", input);
        filterExpression.put("as", as);
        filterExpression.put("cond", cond);
        return new BasicDBObject("$filter", filterExpression);
    }

}
