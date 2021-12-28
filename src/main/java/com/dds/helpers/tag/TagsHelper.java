package com.dds.helpers.tag;

import java.beans.PropertyDescriptor;
import org.apache.commons.beanutils.PropertyUtils;

public class TagsHelper {
    public static boolean hasProperty(Object o, String propertyName) {
        if (o == null || propertyName == null) {
            return false;
        }
        try
        {
            return PropertyUtils.getPropertyDescriptor(o, propertyName) != null;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}
