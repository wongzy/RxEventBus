package site.gemus.rxeventbus;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import site.gemus.rxeventbusannotation.ThreadMode;


/**
 * @author Jackdow
 * @version 1.0
 *          RxEventBus
 */

public final class ThreadModeCastToSchedulersUtil {

    private ThreadModeCastToSchedulersUtil() {}

    public static Scheduler cast(ThreadMode threadMode) {
        Scheduler scheduler = null;
        switch (threadMode) {
            case COMPUTATION:
                scheduler = Schedulers.computation();
                break;
            case NEWTHREAD:
                scheduler = Schedulers.newThread();
                break;
            case MAIN:
                scheduler = AndroidSchedulers.mainThread();
                break;
            case IO:
                scheduler = Schedulers.io();
                break;
            default:
                scheduler = AndroidSchedulers.mainThread();
                break;
        }
        return scheduler;
    }
}
