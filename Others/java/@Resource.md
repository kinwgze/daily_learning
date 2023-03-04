## @Resource注入失败案例
工作中遇到的注入失败bug，之前没有留意过@Resource的使用注意项，解决后记录下

有两个类，@Service("aaBbHandler") @Service("bbHandler")，其中
aaBbaHandler是bbHandler的重构增强版，历史原因导致bbHandler并没有删除。

在某次注入时，使用了以下方式注入
'''
@Resource
private AaBbHandler bbHandler
'''
发生了注入失败

查资料后发现

使用 @Resource 注解注入一个 bean 实例时，默认情况下，Spring 会根据变量名或者属性名与 bean 的名称匹配来自动完成注入。也就是说，如果你的注入的变量名与对应的 bean 的名称一致，那么 Spring 会自动进行匹配并完成注入，不需要显式指定 bean 的名称或者别名。

当然，如果你想要使用别名来注入某个 bean，可以在 @Resource 注解中指定 bean 的名称或者别名，例如：

'''
@Resource(name = "myService")
private MyService myService;
'''
这样就会将名为 myService 的 bean 实例注入到 myService 变量中。


当使用 @Resource 注解进行注入时，变量名和 bean 名称之间的匹配是基于变量名和属性名的，默认情况下，它们应该是一致的，以避免出现歧义。

如果你希望将注入的 bean 实例赋值给不同的变量名，可以使用 @Resource 注解的 name 属性来指定要注入的 bean 名称，例如：
'''
@Resource(name = "myService")
private MyService service;
'''

在上面的例子中，@Resource 注解的 name 属性指定要注入的 bean 名称为 myService，注入后的 bean 实例将赋值给 service 变量。

需要注意的是，当使用 @Resource 注解时，如果没有指定 name 属性，则默认将变量名作为 bean 名称进行匹配，因此如果要指定不同的变量名，必须显式指定 name 属性。

