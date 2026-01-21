package com.core.test.utils;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.FixtureMonkeyBuilder;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;
import com.navercorp.fixturemonkey.jakarta.validation.plugin.JakartaValidationPlugin;

public abstract class FixtureMonkeyUtils {
    public static final FixtureMonkey SUT;

    static {
        FixtureMonkeyBuilder fixtureMonkeyBuilder = FixtureMonkey.builder()
                .plugin(new JakartaValidationPlugin())
                .objectIntrospector(ConstructorPropertiesArbitraryIntrospector.INSTANCE); // 생성자 기반 생성

        /**
         * builder.register로 타입 별로 기본 생성 조건을 설정할 수 있음.
         * 가급적이면 필드별로 Bean Validation을 우선 사용하는게 좋을듯
         */
        // setStringType(fixtureMonkeyBuilder);

        SUT = fixtureMonkeyBuilder.build();
    }
    private FixtureMonkeyUtils() {
        // 인스턴스 방지
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
    private static void setStringType(FixtureMonkeyBuilder fixtureMonkeyBuilder) {
        fixtureMonkeyBuilder.register(String.class, fm ->
                fm.giveMeBuilder(String.class)
                        .set("$", "Default String")
        );
    }
}
