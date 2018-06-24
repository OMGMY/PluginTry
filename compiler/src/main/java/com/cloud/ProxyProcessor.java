package com.cloud;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.cloud.model.ProxyMethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * Created by hanzhang on 2017/12/4.
 */
@AutoService(Processor.class)
public class ProxyProcessor  extends AbstractProcessor {


    private static final ClassName CONTEXT = ClassName.get("android.content", "Context");

    private static final ClassName ASSERT_MANAGER = ClassName.get("android.content.res", "AssetManager");

    private static final ClassName METHOD = ClassName.get("java.lang.reflect", "Method");

    private static final ClassName CLASS =  ClassName.get("java.lang", "Class");

    private static final ClassName PLUGINMANAGEER = ClassName.get("plugin.cloud.utils", "PluginManager");
    /**
     * 文件相关的辅助类
     */
    private Filer mFiler;
    /**
     * 元素相关的辅助类
     */
    private Elements mElementUtils;
    /**
     * 日志相关的辅助类
     */
    private Messager mMessager;
    /**
     * 解析的目标注解集合
     */
    private Map<String, List<ProxyMethod>> mProxyMap = new HashMap<>();

    private Set<String> path = new HashSet<>();

    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        // 返回该注解处理器支持的注解集合
        types.add(UseProxy.class.getCanonicalName());
        types.add(ResourcePath.class.getCanonicalName());
        types.add(Paramters.class.getCanonicalName());
        types.add(UseProxyMethod.class.getCanonicalName());
        return types;
    }

    /**
     * 指定支持的 java 版本，通常返回 SourceVersion.latestSupported()
     * @return java 版本
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    /**
     * init方法是在Processor创建时被javac调用并执行初始化操作。
     * @param processingEnvironment 提供一系列的注解处理工具。
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        mFiler = processingEnvironment.getFiler();
        mElementUtils = processingEnvironment.getElementUtils();
        mMessager = processingEnvironment.getMessager();
    }

    /**
     * 注解处理器要处理的注解类型,值为完全限定名（就是带所在包名和路径的类全名）
     */
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        mProxyMap.clear();
        set.clear();
        for (Element element : roundEnvironment.getElementsAnnotatedWith(UseProxy.class)) {
            if(element.getAnnotation(UseProxy.class).value()){
                getPathInfo(roundEnvironment);
                createNewResouce(roundEnvironment);
                createProxyMethod(roundEnvironment);
                addParamtersInfo(roundEnvironment);
                generateMethodCode();
                break;
            }
        }
        return true;
    }

    private void generateMethodCode() {
        for(String className:mProxyMap.keySet()){
            MethodSpec.Builder methodBuilder = null;
            int index = className.lastIndexOf(".");
            TypeSpec.Builder builder = TypeSpec.classBuilder("Plugin" +className.substring(index+1))
                                                .addModifiers(Modifier.PUBLIC);
            for(ProxyMethod method:mProxyMap.get(className)){
                methodBuilder = MethodSpec.methodBuilder(method.methodName)
                        .addModifiers(Modifier.PUBLIC)
                        .addModifiers(Modifier.STATIC)
                        .addException(Exception.class)
                        .returns(String[].class);
                String[] a = new String[9];
                methodBuilder.addStatement("$T<?> pluginClass = $T.getInstance().getClassLoader().loadClass($S)",CLASS,PLUGINMANAGEER,className+"plugin");
                if(method.parameters != null){
                    methodBuilder.addStatement("String[] paramters = new String["+method.parameters.length+"]");
                    for (int i = 0;i<method.parameters.length;i++){
                        methodBuilder.addStatement("paramters["+i+"] = $S",method.parameters[i]);
                    }
                }else {
                    methodBuilder.addStatement("String[] paramters = null");
                }
                methodBuilder.addStatement("$T $N = pluginClass.getDeclaredMethod($S,new Class[]{String[].class}); ",METHOD,method.methodName,method.methodName);
                methodBuilder.addStatement("$N.setAccessible(true)",method.methodName);
                methodBuilder.addStatement("return (String[])$N.invoke(null,new Object[] { $N })",method.methodName,"paramters");
                builder.addMethod(methodBuilder.build());
            }
            TypeSpec mSpec = builder.build();

            try {
                JavaFile.builder("com.cloud.module", mSpec).build().writeTo(mFiler);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

   /* Method setProxy = pluginClass.getMethod("setProxy",
            new Class[]{PluginProxyActivity.class});
            setProxy.setAccessible(true);
            setProxy.invoke(pluginInstance, new Object[] { this });*/

    private void addParamtersInfo(RoundEnvironment roundEnvironment) {

        Set<? extends Element> elementsAnnotatedWithUseProxyMethod = roundEnvironment.getElementsAnnotatedWith(Paramters.class);
        for (Element element : elementsAnnotatedWithUseProxyMethod) {
            ExecutableElement element1 = (ExecutableElement) element;
            TypeElement classElement = (TypeElement) element1.getEnclosingElement();
            String qualifiedName = classElement.getQualifiedName().toString();
            if(mProxyMap.get(qualifiedName) != null){
                List<ProxyMethod> methods =  mProxyMap.get(qualifiedName);
                for(ProxyMethod method:methods){
                    if(method.methodName.equals(element1.getSimpleName().toString())){
                        method.parameters = element.getAnnotation(Paramters.class).value();
                    }
                }
            }
        }
    }

    private void createProxyMethod(RoundEnvironment roundEnvironment) {

      //  mMessager.printMessage(Diagnostic.Kind.ERROR, "createProxyMethod start");
        Set<? extends Element> elementsAnnotatedWithUseProxyMethod = roundEnvironment.getElementsAnnotatedWith(UseProxyMethod.class);
        for (Element element : elementsAnnotatedWithUseProxyMethod) {
            ExecutableElement element1 = (ExecutableElement) element;
            TypeElement classElement = (TypeElement) element1.getEnclosingElement();
            String qualifiedName = classElement.getQualifiedName().toString();
         //   mMessager.printMessage(Diagnostic.Kind.ERROR, "createProxyMethod start2");
            if(mProxyMap.get(qualifiedName) == null){
                ArrayList<ProxyMethod> proxyMethods = new ArrayList<>();
                ProxyMethod method = new ProxyMethod(classElement, mElementUtils);
                method.methodName = element1.getSimpleName().toString();
                proxyMethods.add(method);
                mProxyMap.put(qualifiedName,proxyMethods);

            }else {
                List<ProxyMethod> methods = mProxyMap.get(qualifiedName);
                ProxyMethod method = new ProxyMethod(classElement, mElementUtils);
                method.methodName = element1.getSimpleName().toString();
                methods.add(method);
            }
        }
    }

    private void createNewResouce(RoundEnvironment roundEnvironment) {
        for (Element element : roundEnvironment.getElementsAnnotatedWith(UseProxy.class)) {
            if(element.getAnnotation(UseProxy.class).value()){
                generateCode();
            }
        }
    }

    private void generateCode() {
        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("createAssetManager")
                .addModifiers(Modifier.PUBLIC)
                .addModifiers(Modifier.STATIC)
                .addParameter(CONTEXT, "mContext")
                .returns(ASSERT_MANAGER);
        methodBuilder.addStatement("try { \n" +
                "final AssetManager assetManager = AssetManager.class.newInstance();\n" +
                "final Class<?> assetManagerClazz = Class.forName(\"android.content.res.AssetManager\");\n" +
                "final $T addAssetPathMethod = assetManagerClazz.getDeclaredMethod(\"addAssetPath\", String.class);\n" +
                "addAssetPathMethod.setAccessible(true)",METHOD);
        for(String line:path){
            methodBuilder.addStatement(" addAssetPathMethod.invoke(assetManager, $S);",
                    line);
        }
        methodBuilder.addStatement(
                "          return assetManager;\n" +
                "        } catch (Exception e) {\n" +
                "            e.printStackTrace();\n" +
                "        }\n" +
                "        return null");
        TypeSpec mSpec = TypeSpec.classBuilder("ResourceRegister")
                .addModifiers(Modifier.PUBLIC)
                .addMethod(methodBuilder.build())
                .build();
        try {
            JavaFile.builder("com.cloud.module", mSpec).build().writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getPathInfo(RoundEnvironment roundEnvironment) {
        for (Element element : roundEnvironment.getElementsAnnotatedWith(ResourcePath.class)) {
            path.add(element.getAnnotation(ResourcePath.class).value());
        }
    }
}
