package net.northking.iacmp.ams.web.utils;


import net.northking.iacmp.common.bean.domain.ams.AmsProcessInfo;
import net.northking.iacmp.constant.EmailContants;
import net.northking.iacmp.framework.util.SysConfigInitParamsUtils;
import net.northking.iacmp.system.domain.SysUser;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xbill.DNS.*;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;

public class SendMailUtil {
    protected static final Logger logger = LoggerFactory.getLogger(SendMailUtil.class);

    /**
     * 邮箱账号
     */
    private static String myEmailAccount;
    /**
     * 邮箱授权码
     */
    private static String myEmailPassword;
    /**
     * 发送邮箱服务器地址（这个地址是网易企业邮箱的地址）
     */
    private static String myEmailSMTPHost;
    private static String receiveEmailAccount;
    /**
     * 邮件标题
     */
    private static String title;
    /**
     * 审批路径
     */
    private static String url;
    /**
     * 邮件正文
     */
    private static String context;

    static {
        myEmailAccount = SysConfigInitParamsUtils.getConfig("emailAccount");
        myEmailPassword = SysConfigInitParamsUtils.getConfig("emailPassword");
        myEmailSMTPHost = SysConfigInitParamsUtils.getConfig("emailSMTPHost");
//		receiveEmailAccount = SysConfigInitParamsUtils.getConfig("receiveEmailAccount");
    }

    public String sendMail(SysUser nextSysuer, AmsProcessInfo amsProcessInfo) {
        String email;
        //借阅人姓名
        String appName = amsProcessInfo.getAppOpName();
        //借阅档案名
        String arcName = amsProcessInfo.getArcName();
        //借阅人所属部门
        String orgName = amsProcessInfo.getAppOrgName();
        //审批人姓名
        String userName = nextSysuer.getUserName();
        receiveEmailAccount = nextSysuer.getEmail();
        //验证邮箱是否可送达
        Boolean flag = valid(receiveEmailAccount, myEmailSMTPHost);
        if (flag) {
            String id = amsProcessInfo.getId();
            url = SysConfigInitParamsUtils.getConfig("processUrl");
            url = url + id;
            email = receiveEmailAccount;
            //拼接title
            title = "《" + arcName + "》借阅审批流程";
            //拼接邮件正文
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = sdf.format(date);
            context = "    <p>" + userName + "领导您好：</p>\n" +
                    "    <p>&nbsp;&nbsp;&nbsp;&nbsp;中信百信银行【档案管理系统】提示您有一个档案借阅流程审批，审批连接如下：</p>\n" +
                    "    <p style=\"display:inline\">" + orgName + "  " + appName + "需要借阅 《" + arcName + "》,</p>\n" +
                    "	 <a  href=\"" + url + "\">点击链接进行审批</a><br/>" +
                    "    <p>如已审批请忽略次此邮件</p>\n" +
                    "    <br/><br/><br/><br/><br/>\n" +
                    "    <hr style=\"width:1000px\">\n" +
                    "    <p>中信百信银行综合档案管理系统<p/>\n" +
                    "    <p>" + dateStr + "</p>";
            Properties props = new Properties();
            props.setProperty(EmailContants.MAIL_SMTP_AUTH, Boolean.TRUE.toString());
            props.setProperty(EmailContants.MAIL_TRANSPORT_PROTOCOL, "smtp");
            // smtp服务器地址
            props.put(EmailContants.MAIL_SMTP_HOST, myEmailSMTPHost);

            Session session = Session.getInstance(props);
            session.setDebug(true);

            Message msg = new MimeMessage(session);
            try {
                msg.setSubject(title);
                msg.setContent(context, EmailContants.CONTEXT);
                //发件人邮箱(我的163邮箱)
                msg.setFrom(new InternetAddress(myEmailAccount));
                msg.setRecipient(Message.RecipientType.TO,
                        //收件人邮箱(我的QQ邮箱)
                        new InternetAddress(email));
                msg.saveChanges();

                Transport transport = session.getTransport();
                //发件人邮箱,授权码(可以在邮箱设置中获取到授权码的信息)
                transport.connect(myEmailAccount, myEmailPassword);

                transport.sendMessage(msg, msg.getAllRecipients());
                logger.info("邮件发送成功...");
                transport.close();
                return "发送成功";
            } catch (Exception e) {
                logger.error(e.getMessage());
                logger.info("邮箱发送失败！");
                return "发送失败";
            }
        } else {
            return "发送失败";
        }


    }


    /**
     * 验证邮箱是否存在
     * <br>
     * 由于要读取IO，会造成线程阻塞
     *
     * @param toMail 要验证的邮箱
     * @param domain 发出验证请求的域名(是当前站点的域名，可以任意指定)
     * @return 邮箱是否可达
     */
    public static boolean valid(String toMail, String domain) {
        if (StringUtils.isBlank(toMail) || StringUtils.isBlank(domain)) {
            return false;
        }
        if (!StringUtils.contains(toMail, '@')) {
            return false;
        }
        String host = toMail.substring(toMail.indexOf('@') + 1);
        if (host.equals(domain)) {
            return false;
        }
        Socket socket = new Socket();
        try {
            // 查找mx记录
            Record[] mxRecords = new Lookup(host, Type.MX).run();
            if (ArrayUtils.isEmpty(mxRecords)) {
                return false;
            }
            // 邮件服务器地址
            String mxHost = ((MXRecord) mxRecords[0]).getTarget().toString();
            if (mxRecords.length > 1) {
                // 优先级排序
                List<Record> arrRecords = new ArrayList<Record>();
                Collections.addAll(arrRecords, mxRecords);
                Collections.sort(arrRecords, (o1, o2) -> new CompareToBuilder().append(((MXRecord) o1).getPriority(), ((MXRecord) o2).getPriority()).toComparison());
                mxHost = ((MXRecord) arrRecords.get(0)).getTarget().toString();
            }
            // 开始smtp
            socket.connect(new InetSocketAddress(mxHost, 25));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(socket.getInputStream())));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            // 超时时间(毫秒)
            long timeout = 6000;
            // 睡眠时间片段(50毫秒)
            int sleepSect = 50;

            // 连接(服务器是否就绪)
            if (getResponseCode(timeout, sleepSect, bufferedReader) != 220) {
                return false;
            }

            // 握手
            bufferedWriter.write("HELO " + domain + "\r\n");
            bufferedWriter.flush();
            if (getResponseCode(timeout, sleepSect, bufferedReader) != 250) {
                return false;
            }
            // 身份
            bufferedWriter.write("MAIL FROM: <check@" + domain + ">\r\n");
            bufferedWriter.flush();
            if (getResponseCode(timeout, sleepSect, bufferedReader) != 250) {
                return false;
            }
            // 验证
            bufferedWriter.write("RCPT TO: <" + toMail + ">\r\n");
            bufferedWriter.flush();
            if (getResponseCode(timeout, sleepSect, bufferedReader) != 250) {
                return false;
            }
            // 断开
            bufferedWriter.write("QUIT\r\n");
            bufferedWriter.flush();
            return true;
        } catch (NumberFormatException e) {
        } catch (TextParseException e) {
        } catch (IOException e) {
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
        return false;
    }


    private static int getResponseCode(long timeout, int sleepSect, BufferedReader bufferedReader) throws InterruptedException, NumberFormatException, IOException {
        int code = 0;
        for (long i = sleepSect; i < timeout; i += sleepSect) {
            Thread.sleep(sleepSect);
            if (bufferedReader.ready()) {
                String outline = bufferedReader.readLine();
                // FIXME 读完……
                code = Integer.parseInt(outline.substring(0, 3));
                break;
            }
        }
        return code;
    }

}
