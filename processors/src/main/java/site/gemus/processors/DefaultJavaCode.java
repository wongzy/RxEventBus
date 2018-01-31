package site.gemus.processors;

import javax.lang.model.element.TypeElement;

/**
 * @author Jackdow
 * @version 1.0
 *          RxEventBus
 */

class DefaultJavaCode extends GenerateJavaCode {

    @Override
    String addPackage() {
        return "package site.gemus.rxeventbus;\n";
    }

    @Override
    String addImport() {
        return null;
    }

    @Override
    String addClassName() {
        return null;
    }

    @Override
    String addMethodNameAndStart() {
        return null;
    }

    @Override
    String addMethodBody(TypeElement element) {
        return null;
    }

    @Override
    String addMethodEnd() {
        return null;
    }
}
