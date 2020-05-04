package net.northking.iacmp.elasticsearch.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.northking.iacmp.core.domain.BaseEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Document(indexName = "client_info_index", type = "client_info_type")
@Data
public class ClientInfoType implements Serializable {
    /**
     * 主键id
     */
    @Id
    private Long id;
    /**
     * 客户号
     */
    private String customerCode;
    /**
     * 客户姓名
     */
    private String customerName;
    /**
     * 客户证件号
     */
    private String customerIdCard;
    /**
     * 证件类型
     */
    private String idCardType;
    /**
     * 流水号
     */
    private String busino;
    /**
     * 其他预留数据
     */
    private Object metadata;


}
