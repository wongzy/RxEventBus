package site.gemus.processors;

import com.google.auto.service.AutoService;

import java.util.Iterator;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

import site.gemus.rxeventbusannotation.Subscribe;


/**
 * 注解处理器
 * @author Jackdow
 */
@SupportedAnnotationTypes("site.gemus.rxeventbusannotation.Subscribe")
@AutoService(Processor.class)
public class RxEventBusProcessor extends AbstractProcessor{
    private Messager mMessager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mMessager = processingEnvironment.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        mMessager.printMessage(Diagnostic.Kind.NOTE, "processing..");
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(Subscribe.class);
        if (elements.isEmpty()) {
            mMessager.printMessage(Diagnostic.Kind.WARNING, "Elements is empty!");
            return false;
        }
        Iterator<? extends Element> iterator = elements.iterator();
        if (iterator.hasNext()) {
            Element element = iterator.next();
            if (element.getKind() != ElementKind.METHOD) {
                mMessager.printMessage(Diagnostic.Kind.ERROR, "Subscribe annotation can only be used on method");
                return false;
            }
        }
        AbstractJavaCodeBuilder abstractJavaCodeBuilder = new ConcreteJavaCodeBuilder();
        abstractJavaCodeBuilder.build(elements, processingEnv.getFiler());
        return  true;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_7;
    }
}
