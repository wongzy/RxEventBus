package site.gemus.processors;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.util.Iterator;
import java.util.List;

import java.util.Set;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import site.gemus.rxeventbusannotation.Subscribe;


/**
 * @author Jackdow
 * @version 1.0
 *          RxEventBus
 */

public class ConcreteJavaCodeBuilder extends AbstractJavaCodeBuilder {
    @Override
    protected JavaFile createFile(Element element, TypeSpec typeSpec) {
        return JavaFile.builder(element.getEnclosingElement().getEnclosingElement().toString(), typeSpec)
                .build();
    }

    @Override
    protected MethodSpec createMethod(Set<? extends Element> elements) {
        ClassName eventMethodMessage = ClassName.get("site.gemus.rxeventbusannotation","EventMethodMessage");
        ClassName proxyMessageMethod = ClassName.get("site.gemus.rxeventbusannotation","ProxyMessageMethod");
        ClassName threadModeName = ClassName.get("site.gemus.rxeventbusannotation","ThreadMode");
        MethodSpec.Builder methodSpecBuilder = MethodSpec.methodBuilder("addEventMethodMessage")
                .addParameter(List.class, "eventMethodMessages")
                .addStatement("$T import1", eventMethodMessage)
                .addStatement("$T import2", proxyMessageMethod)
                .addStatement("$T import3", threadModeName);
        Iterator<? extends Element> iterator = elements.iterator();
        while (iterator.hasNext()) {
            Element element = iterator.next();
            if (null != element) {
                String methodName = element.getSimpleName().toString();
                String threadMode = ThreadCastString.getString(element.getAnnotation(Subscribe.class).threadMode());
                String className = element.getEnclosingElement().getSimpleName().toString();
                String paraName = ((ExecutableElement) element).getParameters().get(0).asType().toString();
                methodSpecBuilder.addCode("((List<EventMethodMessage>) eventMethodMessages).add(new EventMethodMessage(" + "" +
                        threadMode + ",\"" + className + "\",\"" + paraName + "\",\n" + " new ProxyMessageMethod() {\n" +
                        "            @Override\n" +
                        "            public void doMethod(Object object, Object message) {\n" +
                        "((" + className + ")" + "object)" + "." + methodName + "((" + paraName + ")message);\n" +
                        " }}));\n");
            }
        }
        return methodSpecBuilder.build();
    }

    @Override
    protected TypeSpec createClass(MethodSpec methodSpec) {
        return TypeSpec.classBuilder("ProduceEventMethodMessageImpl")
                .addModifiers(Modifier.PUBLIC)
                .addMethod(methodSpec)
                .build();
    }
}
