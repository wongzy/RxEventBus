package site.gemus.processors;


import site.gemus.rxeventbusannotation.ThreadMode;

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
                stringBuilder.append("ThreadMode.IO");
                break;
            case MAIN:
                stringBuilder.append("ThreadMode.MAIN");
                break;
            case NEWTHREAD:
                stringBuilder.append("ThreadMode.NEWTHREAD");
                break;
            case COMPUTATION:
                stringBuilder.append("ThreadMode.COMPUTATION");
            default:
                break;
        }
        return stringBuilder.toString();
    }
}
