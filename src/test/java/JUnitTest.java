import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.junit.jupiter.api.*;

public class JUnitTest {

    @DisplayName("1+2는 뭘까~요")
    @Test
    public void junitTest() {
        int a = 1;
        int b = 2;
        int sum = 3;

        System.out.println("1+2=3이다");

        Assertions.assertEquals(sum,  a+b);
    }
    @DisplayName("1+3은 주현해에요.")
    @Test
    public void junitFailTest() {
        int a = 1;
        int b = 3;
        int sum = 3;

        System.out.println("1+2=3이 아니라 주현해이다");

        Assertions.assertEquals(sum,  a+b);
    }

    @BeforeEach
    public void prepare(){
        System.out.println("테스트 주현해");
    }

    @AfterEach
    public void clean(){
        System.out.println("테스트 주현해 설거지");
    }

    @AfterAll
    public static void prepareAll(){
        System.out.println("테스트 최종 주현해");
    }

    @AfterAll
    public static void cleanAll(){
        System.out.println("진짜 최종 주현해");
    }

}

