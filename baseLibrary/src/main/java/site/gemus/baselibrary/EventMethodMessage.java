package site.gemus.baselibrary;

/**
 * @author Jackdow
 * @version 1.0
 *          RxEventBus
 */

public class EventMethodMessage {
    private final ThreadMode mSchedulers;
    private final String className;
    private final String paraName;
    private final ProxyMessageMethod mProxyMessageMethod;

    public EventMethodMessage(ThreadMode mScheduler, String className, String paraName, ProxyMessageMethod messageMethod){
        this.mSchedulers = mScheduler;
        this.mProxyMessageMethod = messageMethod;
        this.paraName = paraName;
        this.className = className;
    }

    public ThreadMode getSchedulers() {
        return mSchedulers;
    }

    public String getParaName() {
        return paraName;
    }

    public String getClassName() {
        return className;
    }

    /**
     * 调用函数
     * @param object 观察者
     * @param message 需要传递的消息类型
     */
    public void doMethod(Object object, Object message) {
        mProxyMessageMethod.doMethod(object, message);
    }
}
