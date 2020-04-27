package com.yasarmatrac.SpELOperationOverloader;


import org.springframework.expression.EvaluationException;
import org.springframework.expression.Operation;
import org.springframework.expression.OperatorOverloader;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

public class CustomOperatorOverloader implements OperatorOverloader {

    @Override
    public boolean overridesOperation(Operation operation, Object leftOperand, Object rightOperand) throws EvaluationException {
        if (leftOperand instanceof Temporal && rightOperand instanceof Temporal && operation == Operation.SUBTRACT) {
            return true;
        } else if (leftOperand instanceof Temporal && rightOperand instanceof Duration
                && (operation == Operation.SUBTRACT || operation == Operation.ADD)) {
            return true;
        }
        return false;
    }

    @Override
    public Object operate(Operation operation, Object leftOperand, Object rightOperand) throws EvaluationException {
        if (leftOperand instanceof Temporal && rightOperand instanceof Temporal && operation == Operation.SUBTRACT) {
            Temporal left = (Temporal) leftOperand;
            Temporal right = (Temporal) rightOperand;
            var seconds = right.until(left, ChronoUnit.SECONDS);
            return Duration.ofSeconds(seconds);
        } else if (leftOperand instanceof Temporal && rightOperand instanceof Duration
                && (operation == Operation.SUBTRACT || operation == Operation.ADD)) {
            Temporal left = (Temporal) leftOperand;
            Duration right = (Duration) rightOperand;
            var duration = operation == Operation.ADD ? right : right.negated();
            return left.plus(duration);
        } else {
            throw new EvaluationException("No operation overloaded by default");
        }
    }
}