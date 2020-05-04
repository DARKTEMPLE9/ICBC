package net.northking.iacmp.cms.web.sso.service;

/*import cn.com.agree.bxbank.sso.domain.TokenAuthAPI;
import cn.com.agree.bxbank.sso.domain.TokenAuthRequest;
import cn.com.agree.bxbank.sso.domain.TokenAuthResponse;
import com.baidu.ub.msoa.container.support.governance.annotation.BundleService;
import com.baidu.ub.msoa.rpc.RPCProtocol;*/

import net.northking.iacmp.annotation.DataSource;
import net.northking.iacmp.enums.DataSourceType;
import org.springframework.stereotype.Service;

/**
 * 令牌接口服务
 *
 * @author duxianchao
 */
@Service
@DataSource(value = DataSourceType.SLAVE)
public class TokenAuthService {
	
	/*@BundleService(provider = "${msoa.sso.provider}", service = TokenAuthAPI.SERVICE_NAME, version = "${msoa.sso.version}",protocol = RPCProtocol.NAVI2JSON, interfaceType = TokenAuthAPI.class)
	private TokenAuthAPI tokenAuthAPI;

	*//**
     * 获取token令牌
     * @param tokenString
     * @param systemName
     * @param clientIP
     * @param encode
     * @param encrypt
     * @return
     *//*
	public TokenAuthResponse getToken(String tokenString,String systemName,String clientIP,boolean encode,boolean encrypt){
		TokenAuthRequest req = new TokenAuthRequest();
		req.setClientIp(clientIP);
		req.setEncode(encode);
		req.setEncrypt(encrypt);
		req.setSystemName(systemName);
		req.setTokenString(tokenString);
		return tokenAuthAPI.TokenTouch(req);
	}
	
	*//**
     * 令牌校验
     * @param tokenString
     * @param systemName
     * @param clientIP
     * @param encode
     * @param encrypt
     * @return
     *//*
	public TokenAuthResponse verifyToken(String userID,String tokenString,String systemName,String clientIP,boolean encode,boolean encrypt){
		TokenAuthRequest req = new TokenAuthRequest();
		req.setClientIp(clientIP);
		req.setEncode(encode);
		req.setUserID(userID);
		req.setEncrypt(encrypt);
		req.setSystemName(systemName);
		req.setTokenString(tokenString);
		return tokenAuthAPI.TokenCheck(req);
	}*/

}
