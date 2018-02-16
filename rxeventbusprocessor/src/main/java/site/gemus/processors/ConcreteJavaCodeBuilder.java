package site.gemus.processors;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.util.Iterator;
import java.util.List;

import java.util.Set;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

import site.gemus.rxeventbusannotation.EventMethodMessage;
import site.gemus.rxeventbusannotation.ProxyMessageMethod;
import site.gemus.rxeventbusannotation.Subscribe;
import site.gemus.rxeventbusannotation.ThreadMode;


/**
 * @author Jackdow
 * @version 1.0
 *          RxEventBus
 */

public class ConcreteJavaCodeBuilder extends AbstractJavaCodeBuilder {
    @Override
    protected JavaFile createFile(PackageElement packageElement, TypeSpec typeSpec) {
        return JavaFile.builder(packageElement.getQualifiedName().toString(), typeSpec)
                .addStaticImport(java.util.List.class)
                .addStaticImport(ThreadMode.class)
                .addStaticImport(EventMethodMessage.class)
                .addStaticImport(ProxyMessageMethod.class)
                .build();
    }

    @Override
    protected MethodSpec createMethod(Set<? extends Element> elements) {
        MethodSpec.Builder methodSpecBuilder = MethodSpec.methodBuilder("addEventMethodMessage")
                .addParameter(List.class, "eventMethodMessages");
        Iterator<? extends Element> iterator = elements.iterator();
        while (iterator.hasNext()) {
            Element element = iterator.next();
            TypeElement typeElement = (TypeElement) element;
            String className = typeElement.getQualifiedName().toString();
            String threadMode = ThreadCastString.getString(typeElement.getAnnotation(Subscribe.class).threadMode());
            VariableElement variableElement = (VariableElement) element;
            String methodName = variableElement.getEnclosingElement().getSimpleName().toString();
            String paraName = ((ExecutableElement) element).getParameters().get(0).getSimpleName().toString();
            methodSpecBuilder.addCode("((List<EventMethodMessage>) eventMethodMessages).add(new EventMethodMessage(" + "" +
                    threadMode + "," + className + "," + paraName + ",\n" + " new ProxyMessageMethod() {\n" +
                    "            @Override\n" +
                    "            public void doMethod(Object object, Object message) {\n" +
                    "((" + className + ")" + "object)" + "." + methodName + "((" + paraName + ")message);\n" +
                    " }}));");
        }
        return methodSpecBuilder.build();
    }

    @Override
    protected TypeSpec createClass(MethodSpec methodSpec) {
        return TypeSpec.classBuilder("ProduceEventMethodMessageImpl")
                .addMethod(methodSpec)
                .build();
    }
}
