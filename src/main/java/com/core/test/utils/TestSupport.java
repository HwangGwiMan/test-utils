package com.core.test.utils;

import com.navercorp.fixturemonkey.ArbitraryBuilder;
import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.expression.JavaGetterMethodReference;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static com.navercorp.fixturemonkey.api.expression.JavaGetterMethodPropertySelector.javaGetter;

public class TestSupport<T>  {
    public final FixtureMonkey sut = FixtureMonkeyUtils.SUT;
    private final Class<T> clazz;
    public TestSupport(Class<T> clazz) {
        this.clazz = clazz;
    }
    public T monkey() {
        return sut.giveMeOne(clazz);
    }
    public T monkey(Consumer<T> builder) {
        T t = sut.giveMeOne(clazz);
        builder.accept(t);
        return t;
    }
    public <V> T monkey(JavaGetterMethodReference<T, V> getter, List<V> candidates) {
        return getBuilder()
                .set(javaGetter(getter), Arbitraries.of(candidates))
                .sample();
    }
    public <R> T monkey(Map<JavaGetterMethodReference<T, R>, Arbitrary<R>> selectorMap) {
        ArbitraryBuilder<T> builder = getBuilder();
        selectorMap.forEach((javaGetterMethodReference, arbitrary) -> {
            builder.set(javaGetter(javaGetterMethodReference), arbitrary);
        });
        return builder.sample();
    }
    public List<T> monkeyList(int size) {
        return sut.giveMe(clazz, size);
    }
    public List<T> monkeyList(Consumer<T> builder, int size) {
        List<T> ts = this.monkeyList(size);
        ts.forEach(builder);
        return ts;
    }
    public <V> List<T> monkeyList(JavaGetterMethodReference<T, V> getter, List<V> candidates, int size) {
        return getBuilder()
                .set(javaGetter(getter), Arbitraries.of(candidates))
                .sampleList(size);
    }
    public <V> List<T> monkeyList(Map<JavaGetterMethodReference<T, V>, Arbitrary<V>> selectorMap, int size) {
        ArbitraryBuilder<T> builder = getBuilder();
        selectorMap.forEach((javaGetterMethodReference, candidates) -> {
            builder.set(javaGetter(javaGetterMethodReference), candidates);
        });
        return builder.sampleList(size);
    }
    public ArbitraryBuilder<T> getBuilder() {
        return sut.giveMeBuilder(clazz);
    }
}
