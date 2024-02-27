package utils;

import datadictionary.Description;

import java.math.BigDecimal;

public interface NumberMode extends Description {

    /**
     * 转化因子，例如%是100
     * @return 因子
     */
    int getFactor();

    /**
     * 保留的小数位，例如2，保留2位小数
     * @return 小数位
     */
    int getScale();

    /**
     * 符号，例如百分比%
     * @return 符号
     */
    String getSymbol();

}
