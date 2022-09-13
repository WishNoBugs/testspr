package com.example.testspr.service.impl;


import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.example.testspr.dto.MailFileDetail;
import javax.mail.internet.MimeMessage;
import com.example.testspr.enums.EmailTemp;
import com.example.testspr.properties.EmailProperties;
import com.example.testspr.service.EmailService;
import com.example.testspr.util.TextUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileUrlResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.boot.autoconfigure.mail.MailProperties;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 邮件服务
 *
 * @author lihm
 */
@Slf4j
@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;

    private final MailProperties mailProperties;

    @Override
    public void send(String to) {
        log.info("开始发送邮件");
        String subject = "主题";
        String content = "邮件内容测试";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("淘金者");
        message.setTo(StrUtil.splitToArray(to, ","));
        message.setSubject(subject);
        message.setText(content);
        Date now = new Date();
        message.setSentDate(now);


        try {
            javaMailSender.send(message);
            log.info("开始发送邮件");
        } catch (Exception e) {
            log.error("邮件发送失败!", e);
        }
    }


//    @Override
//    public void sendIncludeFile(String to, EmailTemp emailTemp, Map<String, String> tempData, List<MailFileDetail> filePaths) {
//        log.info("开始发送附件邮件");
//        EmailProperties.EmailTemp temp = getEmailTemp(emailTemp);
//        String subject = TextUtil.replacePlaceholders(temp.getSubject(), tempData);
//        String content = TextUtil.replacePlaceholders(temp.getContent(), tempData);
//
//        MimeMessage message = javaMailSender.createMimeMessage();
//        MimeMessageHelper helper = null;
//        String from = mailProperties.getUsername();
//        try {
//
//            helper = new MimeMessageHelper(message, true);
//            helper.setFrom(emailProperties.getNickName() + '<' + from + '>');
//            helper.setTo(StrUtil.splitToArray(to, ","));
//            helper.setSubject(subject);
//            helper.setText(content, false);
//            for (MailFileDetail mailFileDetail : filePaths) {
//                //是本地文件，还是网络文件
//                Integer pathType = mailFileDetail.getPathType();
//                String fileName = mailFileDetail.getFileName();
//                String path = mailFileDetail.getPath();
//                //添加本地附件
//                if (pathType == 1) {
//                    FileSystemResource file = new FileSystemResource(path);
//                    if (StrUtil.isBlank(fileName)) {
//                        helper.addAttachment(file.getFilename(), file);
//                    } else {
//                        helper.addAttachment(fileName, file);
//                    }
//                } else {
//                    //添加网络附件
//                    FileUrlResource resource = new FileUrlResource(new URL(path));
//                    if (StrUtil.isBlank(fileName)) {
//                        helper.addAttachment(resource.getFilename(), resource);
//                    } else {
//                        helper.addAttachment(fileName, resource);
//                    }
//                }
//            }
//            Date now = new Date();
//            message.setSentDate(now);
//
//            javaMailSender.send(message);
//            log.info("发送附件邮件结束");
//
//        } catch (Exception e) {
//            log.error("附件邮件发送失败");
//            log.error(e.getMessage(), e);
//        }
//    }
//
//
//    private EmailProperties.EmailTemp getEmailTemp(EmailTemp emailTemp) {
//        Map<String, EmailProperties.EmailTemp> emailTempMap = emailProperties.getEmailTempMap();
//        EmailProperties.EmailTemp emailDto = Optional.ofNullable(emailTempMap)
//                .map((map) -> map.get(emailTemp.name()))
//                .orElse(null);
//        Assert.notNull(emailDto, "邮件模板'" + emailTemp.name() + "'未配置邮件文案,请检查配置文件!");
//        return emailDto;
//    }
}
