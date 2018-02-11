package site.gemus.processors;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeSpec;

import java.lang.annotation.Annotation;
import java.util.List;

import java.util.Set;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;

import site.gemus.annotation.ThreadMode;


/**
 * @author Jackdow
 * @version 1.0
 *          RxEventBus
 */

class ConcreteJavaCodeBuilder extends AbstractJavaCodeBuilder {
    @Override
    protected JavaFile createFile(PackageElement packageElement, TypeSpec typeSpec) {
        return JavaFile.builder(packageElement.getQualifiedName().toString(), typeSpec)
                .addStaticImport(java.util.List.class)
                .addStaticImport(ThreadMode.class)
                .addStaticImport(EventMethodMessage.class)
                .addStaticImport(ProxyMessageMethod.class)
                .addStaticImport(IProduceEventMethodMessage.class)
                .build();
    }

    @Override
    protected MethodSpec createMethod(Set<? extends Element> elements) {
        MethodSpec methodSpec = MethodSpec.methodBuilder("addEventMethodMessage")
                .addModifiers(Modifier.PUBLIC)
                .addParameter(List.class, "eventMethodMessages")
                .build();
        //
        return null;
    }

    @Override
    protected TypeSpec createClass(MethodSpec methodSpec) {
        return null;
    }
}
