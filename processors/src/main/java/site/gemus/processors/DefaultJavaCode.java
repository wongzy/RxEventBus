package site.gemus.processors;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import site.gemus.annotation.Subscribe;
import site.gemus.annotation.ThreadMode;

/**
 * @author Jackdow
 * @version 1.0
 *          RxEventBus
 */

class DefaultJavaCode extends GenerateJavaCode {
    private static final String PACKAGE = "package ";
    private static final String[] IMPORTS = {
            "import io.reactivex.android.schedulers.AndroidSchedulers;\n",
            "import io.reactivex.schedulers.Schedulers;\n",
            "import site.gemus.rxeventbus.EventMethodMessage;\n",
            "import site.gemus.rxeventbus.IProduceEventMethodMessage;\n",
            "import site.gemus.rxeventbus.ProxyMessageMethod;\n"
    };
    private static final String CLASSHEAD = "public synchronized class ProduceEventMethodMessageImpl implements IProduceEventMethodMessage {\n";
    private static final String OVRRIDE = "@Override\n";
    private static final String METHODHEAD = "public void addEventMethodMessage(List<EventMethodMessage> eventMethodMessages) {\n";
    private static final String ADDMETHODHEAD = "eventMethodMessages.add(new EventMethodMessage(";
    private static final String COMMA = ",";
    private static final String POINT = ".";
    private static final String IMESSAGEHEAD = "new ProxyMessageMethod() {\n" +
            "            @Override\n" +
            "            public void doMethod(Object object, Object message) {\n";
    private static final String OBJECT = "object";
    private static final String MESSAGE = "message";

    @Override
    String addPackage(String packageName) {
        return PACKAGE + packageName;
    }

    @Override
    String addImport() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String importString : IMPORTS) {
            stringBuilder.append(importString);
        }
        return stringBuilder.toString();
    }

    @Override
    String addClassName() {
        return CLASSHEAD;
    }

    @Override
    String addMethodNameAndStart() {
        return OVRRIDE + METHODHEAD;
    }

    @Override
    String addMethodBody(Element element) {
        TypeElement typeElement = (TypeElement) element;
        ThreadMode threadMode = typeElement.getAnnotation(Subscribe.class).threadMode();
        StringBuilder stringBuilder = new StringBuilder();
        //向List中添加元素，依次分别为Schedulers、className、paraName
        stringBuilder.append(ADDMETHODHEAD).append(ThreadCastString.getString(threadMode))
                .append(COMMA)
                .append(typeElement.getQualifiedName().toString())
                .append(COMMA)
                .append(typeElement.getTypeParameters().get(0).toString())
                .append(COMMA);
        //最后添加ProxyMessageMethod接口的实现
        StringBuilder stringBuilder1 = new StringBuilder(IMESSAGEHEAD);
        stringBuilder1.append(typeElement.getQualifiedName().toString())
                .append(OBJECT)
                .append(POINT)
                .append(((VariableElement) element).getSimpleName().toString())
                .append("((")
                .append(typeElement.getTypeParameters().get(0).toString())
                .append(")")
                .append(MESSAGE)
                .append(";")
                .append("}}));");
        stringBuilder.append(stringBuilder1);
        return stringBuilder.toString();
    }

    @Override
    String addMethodEnd() {
        return "} \n } \n";
    }
}
