package Lesson4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class BoxTests {
    Box box;

    @Nested
    class BoxIsEmpty{
        @BeforeEach
        void createBox(){
            box = new Box();
        }
        @Test
        void ecxeptionTryToDeleteBall(){
            Assertions.assertThrows(BallsCountException.class, () -> box.deleteBalls());
            assertThatExceptionOfType(BallsCountException.class).isThrownBy(() -> box.deleteBalls());
        }
        @Nested
        class OneBall{
            @BeforeEach
            void addBalls(){
                box.addBalls();

            }
            @Test
            void deleteBallsTest() throws BallsCountException {
                box.deleteBalls();
                assertThat(box.getBallsCount().equals(0));
            }
        }
    }
}
