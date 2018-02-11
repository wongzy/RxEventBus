package site.gemus.processors;

/**
 * @author Jackdow
 * @version 1.0
 *          RxEventBus
 */

public interface ProxyMessageMethod {
    /**
     * 观察者中要执行的函数
     * @param object 观察者对象
     * @param message 需要传递的消息
     */
    void doMethod(Object object, Object message);
}
