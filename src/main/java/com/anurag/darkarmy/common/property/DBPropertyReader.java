package com.anurag.darkarmy.common.property;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Qualifier("db_reader")
public class DBPropertyReader extends AbstractPropertyReader {

    public DBPropertyReader(@Qualifier("default_reader") PropertyReader next) {
        super(next);
    }

    @Override
    public Optional<String> read(Property prop) {
        return super.read(prop);
    }
}
