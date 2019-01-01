package com.anurag.darkarmy.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleDurationParserImpl implements DurationParser {
    private static SimpleDurationParserImpl instance = new SimpleDurationParserImpl();

    private static final Pattern DURATION_PATTERN = Pattern.compile(" *([0-9]+) *(ms|s|m|h|d) *");
    private static final Map<String, TimeUnit> unitMap = new HashMap<>();

    static {
        unitMap.put("ms", TimeUnit.MILLISECONDS);
        unitMap.put("s", TimeUnit.SECONDS);
        unitMap.put("m", TimeUnit.MINUTES);
        unitMap.put("h", TimeUnit.HOURS);
        unitMap.put("d", TimeUnit.DAYS);
    }

    public static SimpleDurationParserImpl getInstance() {
        if(instance == null) {
            synchronized (SimpleDurationParserImpl.class) {
                if(instance == null) {
                    instance = new SimpleDurationParserImpl();
                }
            }
        }

        return instance;
    }

    private SimpleDurationParserImpl() {
    }

    @Override
    public long parse(String text, TimeUnit targetUnit) {
        Matcher matcher = DURATION_PATTERN.matcher(text);
        if (matcher.matches()) {
            String unit = matcher.group(2);
            if (unitMap.containsKey(unit)) {
                return targetUnit.convert(Long.valueOf(matcher.group(1)), unitMap.get(unit));
            }
        }
        throw new RuntimeException("Invalid Format");
    }
}
