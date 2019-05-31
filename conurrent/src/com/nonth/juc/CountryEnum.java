package com.nonth.juc;

import jdk.nashorn.internal.objects.annotations.Getter;

/**
 * @author shkstart
 * @create 2019-05-30 0:05
 */



public enum CountryEnum {

    ONE(1,"齐"),TWO(2,"楚"),THREE(3,"燕"),FOUR(4,"赵"),FIVE(5,"魏"),SIX(6,"韩");

    private  Integer retCode;
    private  String retMessage;

    public Integer getRetCode() {
        return retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    CountryEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public static CountryEnum get(int index)
    {
        CountryEnum[] enums = CountryEnum.values();
        for (CountryEnum anEnum : enums) {

            if (index == anEnum.getRetCode())
            {
                return anEnum;
            }
        }
        return null;
    }
}
