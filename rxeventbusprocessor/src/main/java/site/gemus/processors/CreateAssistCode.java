package site.gemus.processors;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;

/**
 * @author Jackdow
 * @version 1.0
 *          RxEventBus
 */

public  class CreateAssistCode {
    public void buildAssistCode(Element element, Filer filer) {
        MethodSpec methodSpec = MethodSpec.methodBuilder("getPackageName")
                .addModifiers(Modifier.PUBLIC)
                .returns(String.class)
                .addCode("return \"" + element.getEnclosingElement().getEnclosingElement().toString() + ".ProduceEventMethodMessageImpl\";")
                .build();
        TypeSpec typeSpec = TypeSpec.classBuilder("AssistTool")
                .addModifiers(Modifier.PUBLIC)
                .addMethod(methodSpec)
                .build();
        JavaFile javaFile = JavaFile.builder("site.gemus.rxeventbus", typeSpec)
                .build();
        try {
            javaFile.writeTo(filer);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
