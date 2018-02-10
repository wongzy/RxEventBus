package site.gemus.processors;

import site.gemus.annotation.ThreadMode;

/**
 * @author Jackdow
 * @version 1.0
 *          RxEventBus
 */

final class ThreadCastString {
     static String getString(ThreadMode threadMode) {
        StringBuilder stringBuilder = new StringBuilder();
        switch (threadMode) {
            case IO:
                stringBuilder.append("Schedulers.io()");
                break;
            case MAIN:
                stringBuilder.append("AndroidSchedulers.mainThread()");
                break;
            case NEWTHREAD:
                stringBuilder.append("Schedulers.newThread()");
                break;
            case COMPUTATION:
                stringBuilder.append("Schedulers.computation()");
            default:
                break;
        }
        return stringBuilder.toString();
    }
}
