package tech.mhuang.pacebox.elasticsearch.server.query;

import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;

/**
 * 查询封装类
 *
 * @author zhangxh
 * @since 1.0.0
 */
@Slf4j
public abstract class AbstractESQueryAware implements ESQueryAware {


    protected void checkValue(int length, OperatorContext context) {
        if (context.getValues().length < length) {
            throw new IllegalArgumentException(context.getFieldName().concat(" values argument length less than ").concat(length + ""));
        }
        for (Object value : context.getValues()) {
            if (value == null) {
                throw new IllegalArgumentException(context.getFieldName().concat(" exists argument value  is null "));
            }
        }
    }


    protected String getCalcMessage(OperatorContext second) {
        MessageFormat format = new MessageFormat(second.getOperateType().getMessage());
        Object[] messages = new Object[second.getValues().length + 1];
        messages[0] = second.getFieldName();
        System.arraycopy(second.getValues(), 0, messages, 1, second.getValues().length);
        return format.format(messages);
    }

}
