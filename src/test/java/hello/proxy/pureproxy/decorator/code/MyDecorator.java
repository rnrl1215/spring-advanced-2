package hello.proxy.pureproxy.decorator.code;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyDecorator implements Component{
    private Component component;

    public MyDecorator(Component component) {
        this.component = component;
    }

    @Override
    public String operation() {
        log.info("테스트");
        component.operation();
        return "test";
    }
}
