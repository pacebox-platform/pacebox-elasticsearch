package tech.mhuang.pacebox.elasticsearch.model.query;

import tech.mhuang.pacebox.elasticsearch.server.query.OrderType;


public class ESOrder {

    private String fieldName;

    private OrderType orderType;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public ESOrder() {
    }

    public ESOrder(String fieldName, OrderType orderType) {
        this.fieldName = fieldName;
        this.orderType = orderType;
    }
}