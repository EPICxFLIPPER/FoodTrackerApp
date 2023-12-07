package conversions;


import exception.InvalidConversionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UnitConverterTest {

    private UnitConverter theConverter;

    @BeforeEach
    void setup() {
        theConverter = UnitConverter.getInstance();
    }

    @Test
    void testConvertFactorNoExceptionExpected() {

        try {
            assertEquals(1,theConverter.convertFactor("Clove","clove"));
            assertEquals(.001,theConverter.convertFactor("G","kg"));
        } catch (InvalidConversionException e) {
            fail("Should not have thrown exception");
        }

    }

    @Test
    void testConvertFactorExceptionExpected() {

        try {
            assertEquals(null,theConverter.convertFactor("unit","clove"));
            assertEquals(null,theConverter.convertFactor("blue","clove"));
            fail("Should have thrown exception");
        } catch (InvalidConversionException e) {

        }

    }


}
