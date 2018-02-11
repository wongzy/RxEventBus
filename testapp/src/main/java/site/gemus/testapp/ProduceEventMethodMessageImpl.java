package site.gemus.testapp;

import java.util.List;

import site.gemus.annotation.ThreadMode;
import site.gemus.processors.EventMethodMessage;
import site.gemus.processors.ProxyMessageMethod;
import site.gemus.processors.IProduceEventMethodMessage;


/**
 * @author Jackdow
 * @version 1.0
 *          RxEventBus
 */

public class ProduceEventMethodMessageImpl implements IProduceEventMethodMessage {
    @Override
    public synchronized void addEventMethodMessage(List<EventMethodMessage> eventMethodMessages) {
        eventMethodMessages.add(new EventMethodMessage(ThreadMode.COMPUTATION, "", "",
                new ProxyMessageMethod() {
            @Override
            public void doMethod(Object object, Object message) {

            }}));
    }
}
