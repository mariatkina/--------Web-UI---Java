package Lesson4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertJExamplesTest {

    @Test
    void assertJTest(){
        List<String>stringList = Arrays.asList("test1", "test2", "test3");
        Assertions.assertAll(
                () -> assertThat(new Functions().isPalindrome("236")).isFalse(),
                () -> assertThat(5).isGreaterThan(4).isLessThan(6),
                () -> assertThat(stringList).containsAnyOf("test")
        );
    }
}
