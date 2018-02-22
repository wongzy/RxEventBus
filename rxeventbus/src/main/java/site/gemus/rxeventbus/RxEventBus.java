package site.gemus.rxeventbus;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


import io.reactivex.functions.Consumer;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
import site.gemus.rxeventbusannotation.EventMethodMessage;


/**
 * @author Jackdow
 * @version 1.0
 *          RxEventBus
 */

public final class RxEventBus {

    private final FlowableProcessor<Object> mbus;
    private Set<Object> mSet = new HashSet<>();
    private final AbstractEventMethodMessageFactory eventMethodMessageFactory;

    private RxEventBus() {
        mbus = PublishProcessor.create().toSerialized();
        eventMethodMessageFactory = new EventMethodMessageFactory();
    }

    /**
     * 获得单例
     * @return 取得默认的事件总线
     */
    public static RxEventBus getDefault() {
        return Holder.INSTANCE;
    }

    /**
     * 静态内部类
     */
    private static class Holder {
        static final RxEventBus INSTANCE = new RxEventBus();
    }

    /**
     * 发送事件
     * @param object 需要发送的事件
     */
    public void post(@NonNull Object object) {
        mbus.onNext(object);
        Iterator<EventMethodMessage> eventMethodMessageIterator =
                eventMethodMessageFactory.getEventMethodMessageList().iterator();
        Iterator<Object> objectIterator = mSet.iterator();
        while (eventMethodMessageIterator.hasNext()) {
            final EventMethodMessage eventMethodMessage = eventMethodMessageIterator.next();
            Log.i("RxEventBus", "look for next one " + "object :" + object.getClass().getName()+ " paraName:" + eventMethodMessage.getParaName() );
            if (eventMethodMessage.getParaName().equals(object.getClass().getName())) {
                while (objectIterator.hasNext()) {
                    Log.i("RxEvent", "look for next two");
                    final Object o = objectIterator.next();
                    if (eventMethodMessage.getClassName().equals(o.getClass().getSimpleName())) {
                        mbus.observeOn(ThreadModeCastToSchedulersUtil.cast(eventMethodMessage.getSchedulers()))
                                .subscribe(new Consumer<Object>() {
                                    @Override
                                    public void accept(Object object) throws Exception {
                                        eventMethodMessage.doMethod(o, object);
                                    }
                                });
                    }
                }
            }
        }
    }

    /**
     * 注册观察者
     * @param subscriber 观察者对象
     */
    public void register(Object subscriber) {
        mSet.add(subscriber);
    }

    /**
     * 注销观察者
     * @param subscriber 观察者对象
     */
    public void unregister(Object subscriber) {
        mSet.remove(subscriber);
    }

}
