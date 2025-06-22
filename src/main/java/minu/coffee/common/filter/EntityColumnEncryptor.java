package minu.coffee.common.filter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import minu.coffee.common.util.EncUtil;

@Converter
public class EntityColumnEncryptor implements AttributeConverter<String, String> {

    /**
     * 저장 될 데이터베이스 칼럼 값으로 암호화
     */
    @Override
    public String convertToDatabaseColumn(String attribute) {
        return EncUtil.encrypt(attribute);
    }

    /**
     * 저장 된 데이터베이스 칼럼 값을 복호화
     */
    @Override
    public String convertToEntityAttribute(String dbData) {
        return EncUtil.decrypt(dbData);
    }

}
