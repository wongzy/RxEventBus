package site.gemus.rxeventbus;

import android.util.Log;

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
                String string = null;
                Class<?> aclass = Class.forName("site.gemus.rxeventbus.AssistTool");
                Method method1 = aclass.getMethod("getPackageName");
                string = (String) method1.invoke(aclass.newInstance());
               // Log.e("String =", string);
                tClass = Class.forName(string);
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
