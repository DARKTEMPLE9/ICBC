package net.northking.iacmp.ams.web.sso.service;

/*import cn.com.agree.bxbank.sso.domain.UserInfoAPI;
import cn.com.agree.bxbank.sso.domain.UserInfoRequest;
import cn.com.agree.bxbank.sso.domain.UserInfoResponse;
import cn.com.agree.bxbank.sso.domain.UsersInfoResponse;
import com.baidu.ub.msoa.container.support.governance.annotation.BundleService;
import com.baidu.ub.msoa.rpc.RPCProtocol;*/

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.enums.DataSourceType;
import org.springframework.stereotype.Service;

/**
 * 用户信息接口服务
 *
 * @author duxianchao
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class UserInfoService {
	/*@BundleService(provider = "${msoa.sso.provider}", service = UserInfoAPI.SERVICE_NAME, version = "${msoa.sso.version}",protocol = RPCProtocol.NAVI2JSON, interfaceType = UserInfoAPI.class)
	private UserInfoAPI userInfoAPI;
	
	*//**
     * 获取用户信息(按系统)
     * @param userID-非必填
     * @param systemID-必填
     * @return
     *//*
	public UsersInfoResponse queryUsersInfo(String userID, String systemID){
		UserInfoRequest req = new UserInfoRequest();
		req.setSystemID(systemID);
		req.setUserID(userID);
		return userInfoAPI.getUsersInfo(req);
	}
	
	*//**
     * 获取用户信息(按用户)
     * @param userID-必填
     * @param systemID-非必填
     * @return
     *//*
	public UserInfoResponse queryUserInfo(String userID, String systemID){
		UserInfoRequest req = new UserInfoRequest();
		req.setSystemID(systemID);
		req.setUserID(userID);
		return userInfoAPI.getUserInfo(req);
	}*/
}
