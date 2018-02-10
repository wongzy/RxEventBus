package site.gemus.processors;

import java.util.Set;

import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;

/**
 * @author Jackdow
 * @version 1.0
 *          RxEventBus
 */

abstract class GenerateJavaCode {
    /**
     * 生成Java文件
     * @param elements 注解元素
     */
    final String generate(Set<? extends Element> elements) {
        StringBuilder stringBuilder = new StringBuilder();
        PackageElement packageElement = (PackageElement) elements.iterator().next();
        stringBuilder.append(addPackage(packageElement.getQualifiedName().toString()));
        stringBuilder.append(addImport());
        stringBuilder.append(addClassName());
        stringBuilder.append(addMethodNameAndStart());
        for (Element element : elements) {
            stringBuilder.append(addMethodBody(element));
        }
        stringBuilder.append(addMethodEnd());
        return stringBuilder.toString();
    }

    /**
     * 添加包名
     * @param packageName 包名
     * @return 包名信息
     */
    abstract String addPackage(String packageName);

    /**
     * 添加依赖
     * @return 依赖信息
     */
    abstract String addImport();

    /**
     * 添加类
     * @return 类信息
     */
    abstract String addClassName();

    /**
     * 添加函数
     * @return 函数名信息
     */
    abstract String addMethodNameAndStart();

    /**
     * 添加函数体
     * @param element 元素
     * @return 函数体信息
     */
    abstract String addMethodBody(Element element);

    /**
     * 添加函数体尾部信息及类末尾
     * @return 函数体尾部信息及类末尾
     */
    abstract String addMethodEnd();
}

