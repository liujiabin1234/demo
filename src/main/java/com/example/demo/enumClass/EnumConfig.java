package com.example.demo.enumClass;

/**
 * 枚举类
 */
public enum EnumConfig {

    BASE_ID_CARD_NATION(110,"0002020100","front","身份证正面"),
    BASE_ID_CARD_PORTRAIT(111,"0002020101","back","身份证反面"),
    BASE_BAREHEADED_PHOTO(112,"0002020200","people","头像");

    private EnumConfig(Integer code, String value, String name, String certificateName) {
        this.code = code;
        this.value = value;
        this.name = name;
        this.certificateName = certificateName;
    }

    private Integer code;
    private String value;
    private String name;
    private String certificateName;

    public Integer getCode() {
        return code;
    }
    public String getValue () {
        return value;
    }
    public String getName() {
        return name;
    }
    public String getCertificateName(){
        return certificateName;
    }

    /**
     * @Description: 根据name获取value
     * @param name
     */
    public static String getValueByName(String name){
        if (name != null && !"".equals(name)) {
            for(EnumConfig sexEnum : EnumConfig.values()){
                if(name.equals(sexEnum.getName())){
                    return sexEnum.getValue();
                }
            }
        }
        return "";
    }

    /**
     * @Description: 根据value获取name
     */
    public static String getNameByValue(String value){
        for(EnumConfig sexEnum : EnumConfig.values()){
            if(value == sexEnum.getValue()){
                return sexEnum.getName();
            }
        }
        return "";
    }

    /**
     * @Description: 根据value获取枚举
     */
    public static EnumConfig getEnumByValue(String value){
        for(EnumConfig sexEnum : EnumConfig.values()){
            if(value.equals(sexEnum.getValue())){
                return sexEnum;
            }
        }
        return null;
    }

    public static void main(String []args){
        EnumConfig enumConfig =  EnumConfig.getEnumByValue("0002010100");
        System.out.println("enumConfig---->>>"+enumConfig);

        String value = EnumConfig.getNameByValue("0002010100");
        System.out.println("value---->>>"+value);

        String name = EnumConfig.getValueByName("baseBusinesSlicense");
        System.out.println("name---->>>"+name);
    }
}
