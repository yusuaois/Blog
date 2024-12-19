
package com.blog.config;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@Configuration
//@EnableWebMvc
//@EnableSwagger2
//public class SwaggerConfigCopy implements WebMvcConfigurer {
//    @Bean
//    public Docket customDocket() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.blog.controller"))
//                .build();
//    }
//
//    private ApiInfo apiInfo() {
//        Contact contact = new Contact("李政武博客项目", "http://www.lzw.com", "15872727512@163.com");
//        return new ApiInfoBuilder()
//                .title("博客后台")
//                .description("## 管理端功能\n" +
//                        "\n" +
//                        "### 用户管理\n" +
//                        "- **用户列表**：分页查询用户信息，支持按条件筛选。\n" +
//                        "- **新增用户**：添加用户并分配角色，支持用户名、邮箱、手机号的唯一性校验。\n" +
//                        "- **修改用户**：更新用户信息和角色绑定。\n" +
//                        "- **删除用户**：支持批量删除用户，禁止删除当前登录用户。\n" +
//                        "- **用户详情**：查看用户详细信息及其角色绑定。\n" +
//                        "\n" +
//                        "### 角色与权限管理\n" +
//                        "- **角色列表**：查询和管理系统中的角色信息。\n" +
//                        "- **新增/修改角色**：创建或更新角色，并分配对应权限。\n" +
//                        "- **权限分配**：基于角色动态加载菜单和功能权限。\n" +
//                        "\n" +
//                        "### 文章管理\n" +
//                        "- **文章列表**：分页查询文章信息，支持按标题、摘要筛选。\n" +
//                        "- **新增文章**：管理员可上传文章并关联分类和标签。\n" +
//                        "- **修改文章**：更新文章内容和关联信息（分类、标签）。\n" +
//                        "- **删除文章**：支持逻辑删除文章，保证数据完整性。\n" +
//                        "- **文章详情**：查看文章详细信息，包括分类、标签和浏览量。\n" +
//                        "\n" +
//                        "### 分类管理\n" +
//                        "- **分类列表**：查询和管理文章分类信息。\n" +
//                        "- **新增/修改分类**：创建或更新分类，支持分类状态管理。\n" +
//                        "- **分类详情**：查看分类详细信息及其关联文章。\n" +
//                        "\n" +
//                        "### 标签管理\n" +
//                        "- **标签列表**：查询和管理文章标签信息。\n" +
//                        "- **新增/修改标签**：创建或更新标签，支持标签状态管理。\n" +
//                        "- **标签详情**：查看标签详细信息及其关联文章。\n" +
//                        "\n" +
//                        "### 友链管理\n" +
//                        "- **友链列表**：查询和管理友链信息。\n" +
//                        "- **新增/修改友链**：创建或更新友链，支持友链状态管理。\n" +
//                        "- **友链详情**：查看友链详细信息及其状态。")
//                .contact(contact)   // 联系方式
//                .version("3.0.1")  // 版本
//                .build();
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
//}