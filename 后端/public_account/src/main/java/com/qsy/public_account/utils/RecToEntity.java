package com.qsy.public_account.utils;

import com.qsy.public_account.entity.FirmInfo;
import com.qsy.public_account.entity.FirmRecive;

import java.lang.reflect.Field;

public class RecToEntity {

    public static void firmTransfer(FirmRecive recive, FirmInfo info) {
        Class<?> reciveClass = recive.getClass();
        Class<?> infoClass = info.getClass();

        Field[] reciveFields = reciveClass.getDeclaredFields();
        Field[] infoFields = infoClass.getDeclaredFields();

        for (Field reciveField : reciveFields) {
            try {
                Field infoField = infoClass.getDeclaredField(reciveField.getName());
                reciveField.setAccessible(true);
                infoField.setAccessible(true);

                Object value = reciveField.get(recive);
                infoField.set(info, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
