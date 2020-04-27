package com.yasarmatrac.SpELOperationOverloader;

import org.springframework.expression.spel.support.StandardEvaluationContext;

public class StandardEvaluationContextFactory {

    public static StandardEvaluationContext createContext() {
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setOperatorOverloader(new TemporalOperatorOverloader());
        return context;
    }
}
