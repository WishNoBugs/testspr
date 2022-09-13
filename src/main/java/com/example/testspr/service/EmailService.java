package com.example.testspr.service;



import com.example.testspr.dto.MailFileDetail;
import com.example.testspr.enums.EmailTemp;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 邮件服务
 *
 * @author lihm
 */
public interface EmailService {

    /**
     * 发送纯文本邮件
     *
     * @param to        接收者， 如果是多个，以 ,  分隔
     */
    void send(String to);


    /**
     * 发送有附件的邮件
     *
     * @param to        接收者， 如果是多个，以 ,  分隔
     * @param emailTemp
     * @param tempData
     * @param filePaths
     */
//    void sendIncludeFile(String to, EmailTemp emailTemp, Map<String, String> tempData, List<MailFileDetail> filePaths);



}
