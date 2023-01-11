package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0() {
        Hello target = new Hello();

        // 공통로직 1 시작
        log.info("start");
        String result1 = target.callA();
        log.info("result={}", result1);

        // 공통로직 2 시작
        log.info("start");
        String result2 = target.callB();
        log.info("result={}", result1);

    }


    // 리플렉션을 적용하여 동적으로 클래스 메소드를 호출 할 수 있다.
    @Test
    void reflection1() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 클래스 정보
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        //Call
        Method methodCallA = classHello.getMethod("callA");
        Object result1 = methodCallA.invoke(target);
        log.info("result1={}", result1);

        //Call
        Method methodCallB = classHello.getMethod("callB");
        Object result2 = methodCallB.invoke(target);
        log.info("result2={}", result2);
    }

    // 리플렉션은 런타임에 오류가 발생하기 때문에 컴파일 시점에서 오류를 잡을 수 없는 단점이 있다.
    @Test
    void reflection2() throws Exception {
        // 클래스 정보
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();

        //Call
        Method methodCallA = classHello.getMethod("callA");
        dynamicCall(methodCallA, target);

        //Call
        Method methodCallB = classHello.getMethod("callB");
        dynamicCall(methodCallB, target);
    }

    private void dynamicCall(Method method, Object target) throws Exception {
        log.info("start");
        Object result = method.invoke(target);
        log.info("result={}", result);
    }


    @Slf4j
    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }

    }

}
