package Lesson4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class FunctionsTest {
    private static Logger logger = LoggerFactory.getLogger(FunctionsTest.class);
    @BeforeAll
    static void beforeAll(){
        System.out.println("first test befoe all tests");
        logger.info("first infolog");
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    void beforeEach(){
        logger.info("infolog");
        System.out.println("before ech test")
        ;
    }

    @Test
    @DisplayName("Проверка метода isPalindrome со словом-палиндромом")
    void isPalindromeTest(){
       boolean result = new  Functions().isPalindrome("321123");
        Assertions.assertEquals(true, result);
    }
    @ParameterizedTest
    @ValueSource(strings = {"123321", "1234321"})
    @DisplayName("Проверка метода isPalindrome со словом-палиндромом с нечетным кол симв")
    void isPalindromeTest1(String testWord){
        boolean result = new  Functions().isPalindrome(testWord);
        Assertions.assertEquals(true, result);
    }

    @ParameterizedTest
    @CsvSource({"234, false", "147741, true"})
    void isPalindrome(String testWord, boolean expectedResult){
        Assertions.assertEquals(expectedResult, new Functions().isPalindrome(testWord));

    }
    @ParameterizedTest
    @MethodSource("catAndAgeDataProvider")
    void catAndAgeTest(Cat cat, Integer age){
        Assertions.assertEquals(cat.getAge(), age);
    }
    private static List<Arguments>catAndAgeDataProvider() {
        return Arrays.asList(
                Arguments.of(new Cat ("test1", 10), 0),
                Arguments.of(new Cat ("test2", 10), 10) );


    }

    @AfterEach
    void afterEach(){
        System.out.println("After each test");
    }
    @AfterAll
    static void afterAll(){
        System.out.println("After all tests");
    }
}
