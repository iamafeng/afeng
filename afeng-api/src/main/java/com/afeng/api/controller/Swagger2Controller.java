package com.afeng.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.io.Serializable;
import java.sql.Date;

/**
 * @Description:Swagger2 示例
 * @Author hugf
 * @Date 2019/3/4
 * @Time 10:19
 *
 * @ApiOperation：用在方法上，说明方法的作用
 *
 *     value: 表示接口名称
 *     notes: 表示接口详细描述
 * @ApiImplicitParams：用在方法上包含一组参数说明
 *
 * @ApiImplicitParam：用在@ApiImplicitParams注解中，指定一个请求参数的各个方面
 *
 * paramType：参数位置
 * header 对应注解：@RequestHeader
 * query 对应注解：@RequestParam
 * path  对应注解: @PathVariable
 * body 对应注解: @RequestBody
 * name：参数名
 * dataType：参数类型
 * required：参数是否必须传
 * value：参数的描述
 * defaultValue：参数的默认值
 * @ApiResponses：用于表示一组响应
 *
 * @ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息
 *
 * code：状态码
 * message：返回自定义信息
 * response：抛出异常的类
 * @ApiIgnore: 表示该接口函数不对swagger2开放展示
 */
@Api(description = "index-api")
@RestController
@RequestMapping(value = "/api/v1/index")
public class Swagger2Controller {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = {"/hello","/hi"})
    @ApiOperation(value = "index hello")
    public String hello() {
        logger.info("hello world!!!");
        logger.debug("hello world!!!");
        logger.error("hello world!!!");
        return "hahaha";
    }
    /**
     * 以下函数的注释,不增加注解了,将在下面统一做描述
     */
    @ApiOperation(value = "测试post请求",notes="注意事项")
    @ApiImplicitParam(dataType = "User",name = "user",value = "用户信息",required = true)
    @RequestMapping(value = "/testPost",method = RequestMethod.POST)
    public String testPost(@RequestBody User user){
        return "success";
    }


    @ApiOperation(value = "测试get请求",notes="注意事项")
    @ApiImplicitParam(name = "id",value = "用户id",dataType = "String",paramType = "path")
    @RequestMapping(value = "/testGet/{id}",method = RequestMethod.GET)
    public String testGet(@PathVariable String id){
        return id;
    }

    @ApiOperation(value = "测试组合注解",notes="注意事项")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "User",name = "user",value = "用户信息",required = true,paramType = "body"),
            @ApiImplicitParam(dataType = "string",name = "id",value = "用户id",required = true,paramType = "path")
    })
    @RequestMapping(value = "/joinAnnotation/{id}",method = RequestMethod.POST)
    public User joinAnnotation(@PathVariable String id,@RequestBody User user){
        return user;
    }

    @ApiIgnore
    public String testIgnore(){
        return "success";
    }



    class User implements Serializable {
        private Integer id;
        private String name;
        private Integer gender;
        private Date birthday;

        public User(Integer id, String name, Integer gender, Date birthday) {
            this.id = id;
            this.name = name;
            this.gender = gender;
            this.birthday = birthday;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getGender() {
            return gender;
        }

        public void setGender(Integer gender) {
            this.gender = gender;
        }

        public Date getBirthday() {
            return birthday;
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }
    }
}
