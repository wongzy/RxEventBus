package site.gemus.rxeventbus;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import site.gemus.rxeventbusannotation.EventMethodMessage;

/**
 * @author Jackdow
 * @version 1.0
 *          RxEventBus
 */

abstract class AbstractEventMethodMessageFactory {
    /**
     *
     */
     volatile CheckedArrayList<EventMethodMessage> mEventMethodMessages
            = new CheckedArrayList<>(new LinkedList<EventMethodMessage>());
    abstract List<EventMethodMessage> getEventMethodMessageList();
}
