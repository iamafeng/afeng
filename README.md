# 5201314

#### 介绍
welcome 2 枫之阁

#### 软件架构
软件架构说明


#### 安装教程

1. xxxx
2. xxxx
3. xxxx

#### 使用说明
##### Swagger2 
```
http://localhost:8899/swagger-ui.html

@ApiOperation：用在方法上，说明方法的作用
    value: 表示接口名称
    notes: 表示接口详细描述 
    
@ApiImplicitParams：用在方法上包含一组参数说明
@ApiImplicitParam：用在@ApiImplicitParams注解中，指定一个请求参数的各个方面
paramType：参数位置
header 对应注解：@RequestHeader
query 对应注解：@RequestParam
path  对应注解: @PathVariable
body 对应注解: @RequestBody
name：参数名
dataType：参数类型
required：参数是否必须传
value：参数的描述
defaultValue：参数的默认值
 
@ApiResponses：用于表示一组响应

@ApiResponse：用在@ApiResponses中，一般用于表达一个错误的响应信息

code：状态码
message：返回自定义信息
response：抛出异常的类
@ApiIgnore: 表示该接口函数不对swagger2开放展示   
```
2. xxxx
3. xxxx

#### 参与贡献

1. Fork 本仓库
2. 新建 Feat_xxx 分支
3. 提交代码
4. 新建 Pull Request


#### 码云特技

1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. 码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5. 码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6. 码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)