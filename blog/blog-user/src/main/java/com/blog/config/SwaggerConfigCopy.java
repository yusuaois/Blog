package com.blog.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableSwagger2
public class SwaggerConfigCopy implements WebMvcConfigurer {
    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.blog.controller"))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("李政武博客项目", "http://www.lzw.com", "15872727512@163.com");
        return new ApiInfoBuilder()
                .title("个人博客项目")
                .description("## 客户端功能\n" +
                        "\n" +
                        "### 文章浏览\n" +
                        "- **多方式查看**：按分类、标签、热门排序等多种方式查看文章。\n" +
                        "- **分页加载**：支持分页加载，浏览量动态更新。\n" +
                        "- **详情查看**：提供文章详情，包括分类、标签、评论等信息。\n" +
                        "\n" +
                        "### 评论互动\n" +
                        "- **发表评论**：用户登录后可对文章发表评论或回复他人评论。\n" +
                        "- **嵌套展示**：支持嵌套评论展示（根评论和子评论），分页加载评论列表。\n" +
                        "- **实时互动**：评论内容实时发布，与文章作者及其他读者互动。\n" +
                        "\n" +
                        "### 分类与标签筛选\n" +
                        "- **分类筛选**：按分类或标签筛选文章，快速找到感兴趣的内容。\n" +
                        "- **投稿选择**：投稿或编辑文章时选择分类和添加标签，增强文章内容的可发现性。\n" +
                        "\n" +
                        "### 文章投稿\n" +
                        "- **支持投稿**：用户可上传新文章并设置分类和标签。\n" +
                        "- **管理投稿**：用户可管理自己的投稿内容（扩展功能）。\n" +
                        "\n" +
                        "### 友链展示\n" +
                        "- **快速访问**：展示审核通过的友情链接，用户可快速访问合作站点。")

                .contact(contact)   // 联系方式
                .version("3.0.1")  // 版本
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}