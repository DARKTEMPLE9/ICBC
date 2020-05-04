package net.northking.iacmp.server.service;

import java.util.Map;

import net.northking.iacmp.common.bean.domain.ams.ImBatch;
import net.northking.iacmp.common.bean.domain.ams.ImBill;
import net.northking.iacmp.common.bean.domain.ams.ImFile;
import net.northking.iacmp.common.bean.domain.ams.ImImage;
import net.northking.iacmp.common.bean.domain.ams.AmsBatch;
import net.sf.json.JSONObject;

import javax.management.OperationsException;

/**
 * 综合档案著录接口
 *
 * @author zhy
 * @date 2019-09-09
 */
public interface IOutInterfaceService {

    public Map<String, String> saveFiles(JSONObject json);

    public ImImage saveImage(String filename, String start, AmsBatch batch, String transfilepath, ImBill imbill, String remark, String createuser) throws OperationsException;

    public ImFile saveFile(String filename, int start, AmsBatch batch, ImBill imbill, String remark, String transfilepath, String createUser) throws OperationsException;
}
