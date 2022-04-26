package calc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

class Test_my_Calculator {
    public my_Calculator calc;
    @BeforeAll
    static void initBeforeAll() {
        System.out.println("Before all called");
    }

    @BeforeEach
    void init() {
        System.out.println("before each called");
        calc = new my_Calculator();
    }

    @Test
    @DisplayName("test for sum")
    void testSum() {
        //GIVEN
        double first = 4;
        double second = 5;
        String operation = "+";
        Double expectedResult = 9.0;

        //WHEN
        Double actualResult = calc.performOperation(first, second, operation);

        //THEN
        assertEquals(expectedResult, actualResult);
        //MatcherAssert.assertThat(actualResult, Matchers.is(expectedResult));
    }

    @Test
    void testDivision() {
        //GIVEN
        int first = 1;
        int second = 6;
        String operation = "/";
        Double expectedResult = 0.16666666666666666;
        //WHEN
        Double actualResult = calc.performOperation(first, second, operation);
        //THEN
        assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest

    @ValueSource(ints = {1,3,Integer.MAX_VALUE})
    void testSum_param(double first) {
        //GIVEN
        double second = 6;
        String operation = "+";
        Double expectedResult = prepareExpectedResult(first, second);

        //WHEN
        Double actualResult = calc.performOperation(first, second, operation);
        //THEN
        assertEquals(expectedResult, actualResult);
    }

    private double prepareExpectedResult(double first, double second) {
        double expectedResultDouble = first + second;
        double expectedResult =  expectedResultDouble;
        return expectedResult;
    }

   @ParameterizedTest
    @Disabled
    @CsvSource(value = {"+:12.00", "/:1.00"}, delimiter = ':')
    @CsvFileSource(resources = "/testData.csv")
    void testSum_paramCsv(String operation, String expectedResult) {
        //GIVEN
        int second = 6;
        int first = 6;

        //WHEN
        Double actualResult = calc.performOperation(first, second, operation);
        //THEN
        assertEquals(expectedResult, actualResult);
    }

    @ParameterizedTest
    @MethodSource("provideValidData")
    void testSum_paramMethod(double first, double second, String operation, Double expectedResult) {
        //WHEN
        Double actualResult = calc.performOperation(first, second, operation);
        //THEN
        assertEquals(expectedResult, actualResult);
    }

    private static Stream<Arguments> provideValidData() {
        return Stream.of(
                //GIVEN
                Arguments.of(10, 2, "+", 12.0),
                Arguments.of(12, 2.5, "-", 9.5),
                Arguments.of(2, 2, "/", 1.0),
                Arguments.of(2, 2, "*", 4.0)
        );
    }


    @TestFactory
    Iterable<DynamicTest> dynamicTestsWithIterable() {
        return Arrays.asList(
                DynamicTest.dynamicTest("Sum test",
                        () -> assertEquals(2.0, calc.performOperation(1, 1, "+"))),
                DynamicTest.dynamicTest("Divide Test",
                        () -> assertEquals(4.0, calc.performOperation(8, 2, "/"))),
                DynamicTest.dynamicTest("Multiplication test",
                        () -> assertEquals(150.0, calc.performOperation(15, 10, "*"))),
                DynamicTest.dynamicTest("Subtraction Test",
                        () -> assertEquals(5.5, calc.performOperation(8, 2.5, "-"))));
    }
}

