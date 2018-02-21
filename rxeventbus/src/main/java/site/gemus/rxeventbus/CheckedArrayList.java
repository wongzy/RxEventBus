package site.gemus.rxeventbus;

import java.util.ArrayList;

/**
 * @author Jackdow
 * @version 1.0
 *          RxEventBus
 * @param <T> 泛型类型
 */
class CheckedArrayList<T> extends ArrayList<T> {
    private ArrayList<T> mArrayList = null;
    private boolean isChecked = false;
    CheckedArrayList(ArrayList<T> arrayList) {
        this.mArrayList = arrayList;
    }

    void setChecked(boolean checked) {
        isChecked = checked;
    }

    boolean isChecked() {
        return isChecked;
    }
}
