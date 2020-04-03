package com.city.blog.blog.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Cheng
 * Date: 2020/3/16
 * Time: 23:08
 * Description: No Description
 */
//前端分页条元素信息,弱化js，控制前端分页显示结果
@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;//所欲发布的信息
    private boolean showPrevious;//上一页
    private boolean showFirstPage;//首页
    private boolean showNext;//下一页
    private boolean showEndPage;//尾页
    private Integer page;//当前页
    private List<Integer> pages=new ArrayList<>();//页面总数
    private Integer totalPage;
    public void setPagination(Integer count, Integer page, Integer size) {
        //页数
        if (count%size==0){
            totalPage=count/size;
        }else {
            totalPage=count/size+1;
        }

        if (page<1){
            page=1;
        }
        if (page>totalPage){
            page=totalPage;
        }

        this.page=page;
        //pages内容
        pages.add(page);
        for (int i=1;i<=3;i++){
            if(page-i>0){
                pages.add(0,page-i);
            }
            if (page+i<=totalPage){
                pages.add(page+i);
            }
        }


        //上下叶是否展示
        if (page==1){
            showPrevious=false;
        }else{
            showPrevious=true;
        }
        if (page.equals(totalPage)){
            showNext=false;
        }else {
            showNext=true;
        }
        if (pages.contains(1)){//包含
            showFirstPage=false;
        }else {
            showFirstPage=true;
        }

        if (pages.contains(totalPage)){
            showEndPage=false;
        }else{
            showEndPage=true;
        }
    }
}