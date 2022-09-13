package com.example.testspr.dto;

import com.example.testspr.enums.EmailTemp;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * 邮件推送实体
 *
 * @author xiongmw@wbpharma.com
 * @version 1.0
 */
@Data
@ToString
@NoArgsConstructor
public class MailNoticeEventDTO {

    /**
     * 接收人
     */
    private String toMail;

    /**
     * 发送人
     */
    private String fromMail;

    /**
     * 附件路径
     */
    private String filePath;

    /**
     * 邮件模板编码
     **/
    private EmailTemp emailTemp;

    /**
     * 邮件标题、内容
     **/
    Map<String, String> tempData;

    /**
     * 邮件附件
     **/
    List<MailFileDetail> filePaths;

    /**
     * 是否有附件，1有，0没有
     */
    private Integer isFile;

}
