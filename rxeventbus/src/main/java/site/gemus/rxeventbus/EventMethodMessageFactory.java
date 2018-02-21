package site.gemus.rxeventbus;

import java.util.List;

import site.gemus.rxeventbusannotation.EventMethodMessage;


/**
 * @author Jackdow
 * @version 1.0
 *          RxEventBus
 */

class EventMethodMessageFactory extends AbstractEventMethodMessageFactory {

    /**
     * 获得信息列表
     * @return 信息列表
     */
    @Override
    List<EventMethodMessage> getEventMethodMessageList() {
        if (mEventMethodMessages.isChecked()) {
            return mEventMethodMessages;
        } else {

            //getEventMethodUtil.addEventMethod(mEventMethodMessages);
            mEventMethodMessages.setChecked(true);
            return mEventMethodMessages;
        }
    }
}
