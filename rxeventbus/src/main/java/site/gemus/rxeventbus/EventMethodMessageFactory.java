package site.gemus.rxeventbus;

import java.lang.reflect.Method;
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
            Class<?> tClass = null;
            Method method = null;
            try {
                tClass = Class.forName("site.gemus.app.ProduceEventMethodMessageImpl");
                method = tClass.getMethod("addEventMethodMessage", List.class);
                method.invoke(tClass.newInstance(), mEventMethodMessages);
            }catch (Exception e) {
                e.printStackTrace();
            }
            mEventMethodMessages.setChecked(true);
            return mEventMethodMessages;
        }
    }
}
