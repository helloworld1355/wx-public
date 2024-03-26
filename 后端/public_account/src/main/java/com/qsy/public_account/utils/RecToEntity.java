package com.qsy.public_account.utils;

import com.qsy.public_account.entity.*;

import java.lang.reflect.Field;

/**
 * @desc 为了几个实体类可以补充，写几个方法来实现
 * */
public class RecToEntity {

    /**
     * @desc FirmRecive 转 FirmInfo；从前端收到的数据，转成后端需要的数据
     * @auth qsy
     * */
    public static   void firmTransfer(FirmRecive recive, FirmInfo info) {
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
//                e.printStackTrace();
            }
        }
    }

    /**
     * @desc FirmInfo 转 FirmShow；即详情实体转为展示实体
     * */
    public static  void firmTransfer(FirmInfo recive, FirmShow info) {
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
//                e.printStackTrace();
            }
        }
    }

    /**
     * @desc 将FirmRecive 转 FirmPurchase；即接收实体转为求购实体
     * */
    public static   void firmTransfer(FimrRecivePurchase recive, FirmPurchase info) {
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
//                e.printStackTrace();
            }
        }
    }


    /**
     * @desc FirmPurchase 转为 FirmShow；即公司求购实体转为展示实体
     * */
    public static  void firmTransfer(FirmPurchase recive, FirmShowPurchase info) {
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
//                e.printStackTrace();
            }
        }
    }

}
