package site.gemus.processors;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;

/**
 * @author Jackdow
 * @version 1.0
 *          RxEventBus
 */

abstract class AbstractJavaCodeBuilder {
    public final void build(Set<? extends Element> elements) {
        MethodSpec methodSpec = createMethod(elements);
        TypeSpec typeSpec = createClass(methodSpec);
        JavaFile javaFile = createFile((PackageElement) elements.iterator().next(), typeSpec);
        try {
            javaFile.writeTo(System.out);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 顶级java文件构造对象
     * @param packageElement 包元素
     * @param typeSpec 类构造对象
     * @return 顶级java文件构造对象
     */
    protected abstract JavaFile createFile(PackageElement packageElement, TypeSpec typeSpec);

    /**
     * 函数构造对象
     * @param elements 注解元素
     * @return 函数构造对象
     */
    protected abstract MethodSpec createMethod(Set<? extends Element> elements);

    /**
     * 类构造对象
     * @param methodSpec 函数构造对象
     * @return 类构造对象
     */
    protected abstract TypeSpec createClass(MethodSpec methodSpec);
}
