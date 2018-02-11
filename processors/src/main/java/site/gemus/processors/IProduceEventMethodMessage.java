package site.gemus.processors;

import java.util.List;

import site.gemus.processors.EventMethodMessage;

/**
 * @author Jackdow
 * @version 1.0
 *          RxEventBus
 */

public interface IProduceEventMethodMessage {
    void addEventMethodMessage(List<EventMethodMessage> eventMethodMessages);
}
