package com.kr.lg.common.enums.convert;

import com.kr.lg.common.enums.EnumEntry;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeConverter;
import java.util.EnumSet;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class AbstractEnumAttributeConverter<E extends Enum<E> & EnumEntry>
    implements AttributeConverter<E, Integer> {

  /** 대상 클래스 */
  private Class<E> enumClass;

  public AbstractEnumAttributeConverter(Class<E> enumClass) {
    this.enumClass = enumClass;
  }

  @Override
  public Integer convertToDatabaseColumn(E attr) {
    return Optional.of(attr).map(EnumEntry::getCode).orElse(null); // null safe
  }

  @Override
  public E convertToEntityAttribute(Integer column) {
    return EnumSet.allOf(enumClass).stream().filter(it -> it.getCode() == column).findFirst().orElse(null);
  }
}
