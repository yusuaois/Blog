package com.blog.constants;

public class SystemConstants
{
    /**
     *  文章是草稿
     */
    public static final int ARTICLE_STATUS_DRAFT = 1;
    /**
     *  文章是正常分布状态
     */
    public static final int ARTICLE_STATUS_NORMAL = 0;

    //状态正常
    public static final String STATUS_NORMAL = "0";

    //友链状态正常
    public static final String LINK_STATUS_NORMAL = "0";

    //评论类型为文章评论
    public static final String ARTICLE_COMMENT = "0";

    //评论类型为友链评论
    public static final String LINK_COMMENT = "1";

    //文章浏览量
    public static final String ARTICLE_VIEW_COUNT = "article:ViewCount";

    public static final String BUTTON = "F";

    public static final String MENU = "C";

    public static final String TAG_STATUS_NORMAL = "0";

    public static final String ADMIN = "1";

    public static final String NORMAL = "0";

    //文章点赞量
    public static final String ARTICLE_LIKE_COUNT = "article:LikeCount";

    //根评论
    public static final Long ROOT_COMMENT = -1L;
}