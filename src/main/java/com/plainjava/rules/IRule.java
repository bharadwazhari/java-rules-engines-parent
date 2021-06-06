package com.plainjava.rules;

interface IRule<I, O> {
    boolean matches(I input);
    O process(I input);
}
