package Lesson4HWTests;

import Lesson4HW.TriangleSidesException;
import Lesson4HW.TriangleArea;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TriangleArTests {
    Logger logger= LoggerFactory.getLogger(TriangleArTests.class);


    @ParameterizedTest
    @CsvSource({"6, 3, 4, 5", "12, 5, 5, 6"})
    @DisplayName("Проверка расчета площади тр-ка")
    void triangleArTest0(Double expValue, int a, int b, int c) throws TriangleSidesException{
        logger.info("Проверка расчета");
        Assertions.assertEquals(expValue, new TriangleArea().triangleArWithExcptn(a, b, c));
    }

    @Test
    @DisplayName("Негативный тест расчета площади тр-ка")
    void triangleArNegativeTest0()throws TriangleSidesException {
        logger.error("Несовпадение результатов");
        Assertions.assertEquals(3, new TriangleArea().triangleArWithExcptn(3, 4, 4));
    }

    @ParameterizedTest
    @CsvSource({"2, 3, 5, false", "14, 17, 11, true"})
    @DisplayName("Проверка верно введенных значений сторон треугольника")
    void triangleArTest1(int a, int b, int c, boolean expectedResult){
        logger.info("Проверка значений");
        Assertions.assertEquals(expectedResult, new TriangleArea().triangleArea(a, b, c));
    }

    @Test
    @DisplayName("Негативный тест верно введенных значений сторон треугольника")
    void triangleArNegativeTest1(){
        logger.error("Несовпадение результатов");
        Assertions.assertEquals(true, new TriangleArea().triangleArea(22,23, -4));
    }

    @Test
    @DisplayName("Проверка работы исключения")
    void triangleArExcptn(){
        logger.info("***");
        Assertions.assertThrows(TriangleSidesException.class,
                () -> new TriangleArea().triangleArWithExcptn(-3, 4, 5));
    }

}
