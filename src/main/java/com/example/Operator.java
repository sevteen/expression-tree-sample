package com.example;

import java.util.Objects;

/**
 * Represents virtually any kind of operator. How operator is treated
 * is up to {@link Visitor}
 *
 * @author beka
 */
public class Operator {

    public static final Operator ADDITION = new Operator("addition");
    public static final Operator SUBTRACTION = new Operator("subtraction");
    public static final Operator MULTIPLICATION = new Operator("multiplication");
    public static final Operator DIVISION = new Operator("division");

    private String name;

    public Operator(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean is(Operator operator) {
        return equals(operator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operator operator = (Operator) o;
        return Objects.equals(name, operator.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
