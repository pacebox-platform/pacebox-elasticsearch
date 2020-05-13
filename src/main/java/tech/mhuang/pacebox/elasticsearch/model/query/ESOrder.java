package tech.mhuang.pacebox.elasticsearch.model.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.mhuang.pacebox.elasticsearch.server.query.OrderType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ESOrder {

    private String fieldName;

    private OrderType orderType;
}