##项目的自述文件，用于存储项目的自述，github上会在项目下面显示该文件文本内容

##网站编码设计思路
先完成独立的单个网页，网页与网页间的跳转，再完成单个网页中的异步请求

##idea快捷键
    ctrl+tab切换idea最新使用的两个文件
    
##资料

idea使用terminal操作git使用的命令为linux命令

add：git add .

commit：git commit (描述信息)-m "描述"   必写描述

commit追加少量代码,不修改版本：git commit --amend --no-edit

git status:查看当前代码修改状态

idea查看单个class在git中的历史版本右击查看的class  ->git  -> show history

github push的时候，远程仓库和本地不一致，导致拒绝提交时解决
    1、git pull 把远端拉回本地
    2、esc键退出然后:x退出当前pull状态
    3、继续push
    
删除blogdata下的数据库表 rm ~/blogdata.*      windows需要将idea的terminal的运行方式改为git的git-bash才可以运行

执行db->migration下的sql脚本  flyway插件执行flyway脚本的mvn命令  mvn flyway:migrate

使用maven插件mybatis generator逆向生成数据库表对应对象mapper和xml    mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate