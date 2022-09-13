package com.example.testspr.constants;

/**
 * 描述:
 * 公共常量定义类
 *
 * @author wangtao
 * @create 2018-12-17 17:53
 */
    public class CommConstant {

    public static final String CLAIM_KEY_USER_ACCOUNT = "sub";
    public static final String CLAIM_KEY_USER_ROLE = "role";
    public static final String CLAIM_KEY_CREATED = "created";
    public static final String CLAIM_KEY_LOGINRECORD = "loginkey";

    public static final int USER_NOT_EXIST_CODE = 8001;
    public static final String USER_NOT_EXIST_MSG = "用户不存在";


    public static final int PASSWORD_IS_WRONG_CODE = 8002;
    public static final String PASSWORD_IS_WRONG_msg = "密码错误";

    public static final int LOGIN_IN_SUCCEED_CODE = 8003;
    public static final String LOGIN_IN_SUCCEED_msg = "登陆成功";

    public static final int REFRESH_TOKEN_SUCCEED_CODE = 8004;
    public static final String REFRESH_TOKEN_SUCCEED_msg = "token刷新成功";

    public static final int REFRESH_TOKEN_FAILED_CODE = 8005;
    public static final String REFRESH_TOKEN_FAILED_msg = "token刷新失败";

    public static final int USER_IS_DISABLED_CODE = 8006;
    public static final String USER_IS_DISABLED_msg = "账户被禁用";

    public static final int USER_HAS_NO_RIGHT_CODE = 8007;
    public static final String USER_HAS_NO_RIGHT_msg = "暂无资源的访问权限";

    public static final double TOTAL_SHARE = 100;

    /**
     * 服务响应失败码
     */
    public static final int ERROR_500 = 500;
    public static final String USER_EXIST_MSG = "该用户已存在";
    public static final String USER_NOT_ADD_MSG = "该用户无效";
    public static final String SELECT_USERS = "请选择用户";
    public static final String PARENTID_NOT_EXIST_MSG = "该上级组织不存在";
    public static final String COMPANYNAME_EXIST_MSG = "该公司名称已存在，请重新输入";
    public static final String PARENTID_EXIST_MSG = "该组织已存在";
    public static final String ORG_NOT_ADD_MSG = "本级组织不可以添加";
    public static final String ORG_NOT_DEL_MSG = "本级组织不可以删除";
    public static final String ORG_1TH_ID = "1";

    /**
     * 业务类型0
     */
    public static final int BUSINESS_TYPE_ZERO = 0;
    /**
     * 业务类型1
     */
    public static final int BUSINESS_TYPE_ONE = 1;

    /**
     * 业务类型2
     */
    public static final int BUSINESS_TYPE_TWO = 2;

    /**
     * 业务类型3
     */
    public static final int BUSINESS_TYPE_THREE = 3;
    /**
     * 业务类型3
     */
    public static final int BUSINESS_TYPE_FOUR = 4;
    /**
     * 业务类型3
     */
    public static final int BUSINESS_TYPE_FIVE = 5;

    /**
     * 生成数字验证码字符串
     */
    public static final String VERIFICATION_CODE_NUMBER = "0123456789";

    /**
     * pagesize默认值10
     */
    public static final int PAGE_SIZE_DEFAULT = 10;
    /**
     * pagenume默认值1
     */
    public static final int PAGE_NUM_DEFAULT = 1;

    /**
     * 密钥
     */
    public static final String KEY = "SHANGHAIEXPRESSYTO";

    /**
     * url编码方式
     */
    public static final String DEFAULT_URL_ENCODING = "UTF-8";

    /**
     * 加密方式
     */
    public static final String MD5 = "MD5";
    /**
     * 操作数据失败
     */
    public static final int DATA_OPERATE_STATUS_FAIL = 0;
    /**
     * 成功回执
     */
    public static final String RETURN_SUCCESS_INFO = "<?xml version=\"1.0\" encoding='UTF-8'?>" +
            "<Response>" +
            "<success>true</success>" +
            "</Response>";


    public static final String UNAUTHORIZED_ACCESS = "未授权访问";

    /**
     * 总部管理员角色
     */
    public static final int SYS_ROLE_MANAGER = 0;

    /**
     * 省管区角色
     */
    public static final int SYS_ROLE_PROVINCE = 1;

    /**
     * 游客角色
     */
    public static final int SYS_ROLE_LEADER = 2;
    /**
     * 财务角色
     */
    public static final int SYS_ROLE_FINANCE=3;

    public static final String CERT_YES = "Y";
    public static final String CERT_NO = "N";
    public static final String OPERATE_TYPE_ADD = "insert";
    public static final String OPERATE_TYPE_UPDATE = "update";
    public static final String OPERATE_TYPE_DELETE = "delete";


}
