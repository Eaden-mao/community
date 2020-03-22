###安定区

####学习哔哩哔哩up主码匠社区

###资料
thymeleaf文档页面https://spring.io/guides/gs/serving-web-content/
github app文档https://developer.github.com/apps/building-github-apps/creating-a-github-app/
springboot mybatis整合https://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/
flyway migration官网https://flywaydb.org/getstarted/firststeps/maven
##脚本
```sql
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account_id` varchar(100) ,
  `name` varchar(50) ,
  `token` char(36) ,
  `gmt_create` bigint ,
  `gmt_modified` bigint ,
  PRIMARY KEY (`id`)
) 
```