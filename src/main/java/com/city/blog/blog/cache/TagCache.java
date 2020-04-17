package com.city.blog.blog.cache;

import com.city.blog.blog.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/4/16
 * Time: 16:07
 * Description: No Description
 *
 *缓存，不存入数据库的数据，存储在java代码中
 * @author Cheng
 */
public class TagCache {
    public static List<TagDTO> getTag(){
        List<TagDTO> tagDTOS=new ArrayList<>();

        TagDTO tagDTO = new TagDTO();
        tagDTO.setBigTag("编程语言");
        tagDTO.setTag(Arrays.asList("javascript", "php", "css", "html", "html5", "java", "node.js", "python", "c++", "c", "golang", "objective-c", "typescript", "shell", "swift", "c#", "sass", "ruby", "bash", "less", "asp.net", "lua", "scala", "coffeescript", "actionscript", "rust", "erlang", "perl"));
        tagDTOS.add(tagDTO);

        TagDTO tagDTO0 = new TagDTO();
        tagDTO0.setBigTag("平台框架");
        tagDTO0.setTag(Arrays.asList("laravel", "spring", "express", "django", "flask", "yii", "ruby-on-rails", "tornado", "koa", "struts"));
        tagDTOS.add(tagDTO0);

        TagDTO tagDTO1 = new TagDTO();
        tagDTO1.setBigTag("服务器");
        tagDTO1.setTag(Arrays.asList("linux", "nginx", "docker", "apache", "ubuntu", "centos", "缓存 tomcat", "负载均衡", "unix", "hadoop", "windows-server"));
        tagDTOS.add(tagDTO1);

        TagDTO tagDTO2 = new TagDTO();
        tagDTO2.setBigTag("数据库");
        tagDTO2.setTag(Arrays.asList("mysql", "redis", "mongodb", "sql", "oracle", "nosql memcached", "sqlserver", "postgresql", "sqlite"));
        tagDTOS.add(tagDTO2);

        TagDTO tagDTO3 = new TagDTO();
        tagDTO3.setBigTag("开发工具");
        tagDTO3.setTag(Arrays.asList("git", "github", "visual-studio-code", "vim", "sublime-text", "xcode intellij-idea", "eclipse", "maven", "ide", "svn", "visual-studio", "atom emacs", "textmate", "hg"));
        tagDTOS.add(tagDTO3);

        return tagDTOS;
    }
    public static String isValid(String tags){
        String[] split = StringUtils.split(tags, ",");
        List<TagDTO> tagDTOS = getTag();
        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTag().stream()).collect(Collectors.toList());
        String collect = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));
        return collect;
    }
}
