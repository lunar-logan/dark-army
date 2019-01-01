package com.anurag.darkarmy.common.property;

import java.util.Optional;

public interface PropertyReader {
    Optional<String> read(Property prop);
}
