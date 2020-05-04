package net.northking.iacmp.elasticsearch.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "transaction_code_index", type = "transaction_code_type")
@Data
@EqualsAndHashCode(callSuper = false)
public class TransactionCodeType {
    /**
     * 主键id
     */
    @Id
    private Long id;
    /**
     * 全局流水号
     */
    private String transactionCode;
    /**
     * 流水号
     */
    private String busino;

}
