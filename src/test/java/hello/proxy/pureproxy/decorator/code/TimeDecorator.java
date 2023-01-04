package hello.proxy.pureproxy.decorator.code;


import lombok.extern.slf4j.Slf4j;
import org.mockito.internal.creation.SuspendMethod;

import javax.swing.*;
import java.sql.Time;

@Slf4j
public class TimeDecorator implements Component{
    private Component component;

    public TimeDecorator(Component component) {
        this.component = component;
    }

    @Override
    public String operation() {
        log.info("TimeDecoration 실행");
        long startTime = System.currentTimeMillis();
        String result = component.operation();
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("TimeDecorator 종료 resultTime={}ms", resultTime);
        return result;
    }
}
