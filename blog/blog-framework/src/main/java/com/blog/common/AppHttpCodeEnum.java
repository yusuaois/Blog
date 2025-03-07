package com.blog.common;

public enum AppHttpCodeEnum {
    // 成功
    SUCCESS(200, "操作成功"),
    // 登录
    NEED_LOGIN(401, "需要登录后操作"),
    NO_OPERATOR_AUTH(403, "无权限操作"),
    SYSTEM_ERROR(500, "出现错误"),
    USERNAME_EXIST(501, "用户名已存在"),
    PHONENUMBER_EXIST(502, "手机号已存在"), 
    EMAIL_EXIST(503, "邮箱已存在"),
    REQUIRE_USERNAME(504, "必需填写用户名"),
    LOGIN_ERROR(505, "用户名或密码错误"),
    CONTENT_NOT_NULL(506, "评论内容不能为空"),
    FILL_TYPE_ERROR(507, "上传格式错误"),
    USERNAME_NOT_NULL(508, "用户名不能为空"),
    NICKNAME_NOT_NULL(509, "昵称不能为空"),
    PASSWORD_NOT_NULL(510, "密码不能为空"),
    EMAIL_NOT_NULL(511, "邮箱不能为空"),
    NICKNAME_EXIST(512, "昵称已存在"),
    TAG_NAME_NOT_NULL(513, "标签名不能为空"),
    //不能删除当前操作用户
    CAN_NOT_DELETE_YOURSELF(514, "不能删除当前操作用户"), 
    CATEGORY_NAME_NOT_NULL(515, "分类名不能为空"), 
    CATEGORY_NAME_EXIST(516, "分类名已存在"), 
    LINK_NAME_NOT_NULL(517, "友链名不能为空"),
    LINK_NAME_EXIST(518, "友链名已存在"), 
    INPUT_FORMAT_ERROR(519, "输入格式错误"), 
    ARTICLE_NOT_EXIST(520, "文章不存在"), 
    ROOT_COMMENT_NOT_EXIST(521, "根评论不存在"), 
    TO_COMMENT_USER_NOT_EXIST(522, "目标评论用户不存在"), 
    TO_COMMENT_NOT_EXIST(523, "目标评论不存在"), 
    TO_COMMENT_USER_NOT_MATCH(524, "目标评论用户不匹配"),
    COMMENT_ERROR(525, "评论失败"),
    ;

    int code;
    String msg;

    AppHttpCodeEnum(int code, String errorMessage) {
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
