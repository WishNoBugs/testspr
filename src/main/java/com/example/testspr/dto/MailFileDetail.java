package com.example.testspr.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lihaim
 */
@Data
public class MailFileDetail implements Serializable {
    /**
     * 文件路径
     */
    private String path;
    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 1是本地文件路径  2是网络路径
     */
    private Integer pathType;

}
