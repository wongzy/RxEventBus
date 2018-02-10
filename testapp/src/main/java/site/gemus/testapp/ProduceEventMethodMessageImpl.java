package site.gemus.testapp;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import site.gemus.rxeventbus.EventMethodMessage;
import site.gemus.rxeventbus.IProduceEventMethodMessage;
import site.gemus.rxeventbus.ProxyMessageMethod;

/**
 * @author Jackdow
 * @version 1.0
 *          RxEventBus
 */

public class ProduceEventMethodMessageImpl implements IProduceEventMethodMessage {
    @Override
    public synchronized void addEventMethodMessage(List<EventMethodMessage> eventMethodMessages) {
        eventMethodMessages.add(new EventMethodMessage(Schedulers.computation(), "", "",
                new ProxyMessageMethod() {
            @Override
            public void doMethod(Object object, Object message) {

            }}));
    }
}
