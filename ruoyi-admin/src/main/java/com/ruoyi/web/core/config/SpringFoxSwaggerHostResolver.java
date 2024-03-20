package com.ruoyi.web.core.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import springfox.documentation.oas.web.OpenApiTransformationContext;
import springfox.documentation.oas.web.WebMvcOpenApiTransformationFilter;
import springfox.documentation.spi.DocumentationType;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Component
public class SpringFoxSwaggerHostResolver implements WebMvcOpenApiTransformationFilter {

    private final static String LOCALHOST = "www.zytb.top:80";
    private final static String ZC = "www.zytb.top/xljkwx";
    @Override
    public OpenAPI transform(OpenApiTransformationContext<HttpServletRequest> context) {

        HttpServletRequest request = context.request().get();

        OpenAPI swagger = context.getSpecification();

        String scheme = "http";
        String referer = request.getHeader("Referer");

        if(StringUtils.hasLength(referer)){
            //获取协议
            scheme = referer.split(":")[0];
        }

        List<Server> servers = new ArrayList<>();
        String finalScheme = scheme;
        //重新组装server信息
        swagger.getServers().forEach(item->{

            //替换协议,去掉默认端口
            item.setUrl(clearDefaultPort(item.getUrl().replace("http", finalScheme)));
            servers.add(item);
        });
        swagger.setServers(servers);
        return swagger;
    }

    //替换成线上的域名
    private String clearDefaultPort(String url){
        if (url.contains(LOCALHOST)) {
            return url.replace(LOCALHOST,ZC);
        }
        return url;
    }

    @Override
    public boolean supports(DocumentationType documentationType) {
        return documentationType.equals(DocumentationType.OAS_30);
    }
}