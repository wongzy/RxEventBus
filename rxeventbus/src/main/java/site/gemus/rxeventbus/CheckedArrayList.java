package site.gemus.rxeventbus;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Jackdow
 * @version 1.0
 *          RxEventBus
 * @param <T> 泛型类型
 */
class CheckedArrayList<T> extends LinkedList<T> {
    private LinkedList<T> mArrayList = null;
    private boolean isChecked = false;
    CheckedArrayList(LinkedList<T> arrayList) {
        this.mArrayList = arrayList;
    }

    void setChecked(boolean checked) {
        isChecked = checked;
    }

    boolean isChecked() {
        return isChecked;
    }
}
