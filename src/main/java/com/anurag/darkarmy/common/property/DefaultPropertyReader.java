package com.anurag.darkarmy.common.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Qualifier("default_reader")
public class DefaultPropertyReader extends AbstractPropertyReader {

    public DefaultPropertyReader(@Autowired(required = false) @Qualifier("none") PropertyReader next) {
        super(next);
    }

    @Override
    public Optional<String> read(Property prop) {
        return Optional.ofNullable(prop.getValue());
    }
}
