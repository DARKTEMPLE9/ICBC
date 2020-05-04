package net.northking.iacmp.imp.domain;

import lombok.Data;

import java.util.Date;

/**
 * DB WorkerID Assigner for UID Generator表 m_worker_node
 *
 * @author qingyu.yan
 * @date 2019-10-09
 */
@Data
public class MWorkerNode {
    private static final long serialVersionUID = 1L;

    /**
     * auto increment id
     */
    private Long id;
    /**
     * host name
     */
    private String hostName;
    /**
     * port
     */
    private String port;
    /**
     * node type: ACTUAL or CONTAINER
     */
    private Integer type;
    /**
     * launch date
     */
    private Date launchDate;
    /**
     * modified time
     */
    private Date modified;
    /**
     * created time
     */
    private Date created;

}
