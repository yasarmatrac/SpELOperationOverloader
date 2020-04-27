package com.yasarmatrac.SpELOperationOverloader;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.util.Assert;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@SpringBootTest
class SpElOperationOverloaderApplicationTests {


    @Test
    void testOverloadContext() {
        var expressionParser = new SpelExpressionParser();
        var evaluationContext = StandardEvaluationContextFactory.createContext();
        var date1 = LocalDateTime.now();
        var date2 = date1.plusMinutes(30);
        evaluationContext.setVariable("date1", date1);
        evaluationContext.setVariable("date2", date2);
        var result = expressionParser.parseExpression("#date2 - #date1").getValue(evaluationContext);
        Assert.isTrue(Objects.equals(result, Duration.ofMinutes(30)));

    }

}
