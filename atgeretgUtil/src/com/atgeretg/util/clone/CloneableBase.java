package com.atgeretg.util.clone;

import java.lang.reflect.Array;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;


public class CloneableBase implements ICloneable
{
    private static ConcurrentMap<Class<?>, BeanCopier> beanCopiers = new ConcurrentHashMap<Class<?>, BeanCopier>();
 
    @Override
    public Object clone()
    {
        try
        {
            Object clone = this.getClass().newInstance();
 
            BeanCopier copier = _createCopier(this.getClass());
 
            copier.copy(this, clone, new Converter()
            {
                @Override
                public Object convert(Object pojo, @SuppressWarnings("rawtypes") Class fieldType, Object fieldName)
                {
                    return _clone(pojo);
                }
            });
            return clone;
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
 
    private static Object _clone(Object bean)
    {
        if (bean == null)
        {
            return null;
        }
        else if (bean instanceof ICloneable)
        {
            return ((ICloneable) bean).clone();
        }
        else
        {
 
                    if (bean.getClass().isArray() && !bean.getClass().getComponentType().equals(byte.class))
                    {
                        int length = Array.getLength(bean);
                        Object clone = Array.newInstance(bean.getClass().getComponentType(), length);
                        for (int i = 0; i < length; i++)
                        {
                            Array.set(clone, i, _clone(Array.get(bean, i)));
                        }
                        return clone;
                    }
                    else
                    {
                        return bean;
                    }
            }
        }
    
 
    private static BeanCopier _createCopier(Class<?> clz)
    {
        if (beanCopiers.containsKey(clz))
            return beanCopiers.get(clz);
        beanCopiers.putIfAbsent(clz, BeanCopier.create(clz, clz, true));
        return beanCopiers.get(clz);
 
    }
}