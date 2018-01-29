package site.gemus.processors;
import com.google.auto.service.AutoService;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

import site.gemus.annotation.Subscribe;


/**
 * 注解处理器
 */
@AutoService(Processor.class)
@SupportedAnnotationTypes("site.gemus.annotation.Subscribe")
public class processor extends AbstractProcessor{
    private Elements mElementUtils; //基于元素进行操作的工具方法
    private Filer mFileCreator;     //代码创建者
    private Messager mMessager;     //日志，提示者，提示错误、警

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mElementUtils = processingEnvironment.getElementUtils();
        mFileCreator = processingEnvironment.getFiler();
        mMessager = processingEnvironment.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        mMessager.printMessage(Diagnostic.Kind.NOTE, "processing..");
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(Subscribe.class);
        Iterator<? extends Element> iterator = elements.iterator();
        if (iterator.hasNext()) {
            TypeElement typeElement = (TypeElement) iterator.next();
            if (typeElement.getKind() != ElementKind.METHOD) {
                mMessager.printMessage(Diagnostic.Kind.ERROR, "Subscribe annotation can only be used on method");
                return false;
            }

        }
        return  true;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
