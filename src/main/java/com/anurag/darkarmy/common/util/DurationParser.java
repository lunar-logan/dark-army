package com.anurag.darkarmy.common.util;


import java.util.concurrent.TimeUnit;

public interface DurationParser {
    long parse(String text, TimeUnit unit);

    default long parse(String text) {
        return parse(text, TimeUnit.MILLISECONDS);
    }

    static DurationParser getSimpleInstance() {
        return SimpleDurationParserImpl.getInstance();
    }
}
