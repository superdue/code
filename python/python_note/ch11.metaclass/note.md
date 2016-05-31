# 第11章 元类

类型对象负责创建对象实例,控制对象行为(方法)。那么类型对象又由谁来创建呢? -- 元类(metaclass),也就是类型的类型
 
New-Style Class 的默认元类是 type 

demo1.py 

关键字 Class 会被编译成元类创建类型对象指令 

demo2.py 

正因为 class 和 def 一样是指令,我们可以在任何地方创建类型对象 

demo3.py 

现在可以理清几者的关系,以及创建顺序了。 

```
class = metaclass(...)          # 元类创建类型
instance = class(...)           # 类型创建实例

instance.__class__ is class     # 实例的类型
class.__class__ is metaclass     # 类型的类型
```

**__metaclass__**

除了使用默认元类type以外,还可以用`__metaclass__`属性指定自定义元类,以便对类型对象创建过程进行干预。 

demo4.py 

自定义元类通常都从 type 继承,习惯以 Meta 结尾,就像抽象元类 abc.ABCMeta 那样。代码很简单,只需注意 `__new__` 和 `__init__` 方法参数的区别就行了。 

demo5.py 

当解释器创建类型对象时,会按以下顺序查找`__metaclass__`属性。 

`class.__metaclass__ -> bases.__metaclass__ -> module.__metaclass__ -> type` 

这也是为什么在模块中可以用`__metaclass__`为所有类型指定默认元类的缘故。 

虽然惯例将元类写成 type 的派生类,但也可以用函数代替。 

demo6.py 

**magic**

对象行为由类型决定,实例不过存储了状态数据。那么,当我们控制了类型对象的创建,也就意味着可以让对象的实际行为和代码存在极大的差异。 
这是魔法的力量,也是Python的核心开发人员Tim Peters 说出下面这番话的原因(想必你对它的 import this 很熟悉) 
```
Metaclasses are deeper magic than 99% of users should ever worry about. if you wonder whether you need
them, you don't (the people who actually need them know with certainty that they need them, and don't need an
explanation about why). TimePeters (c.l.p post 2002-12-22)
```

试着写两个简单的例子练练手。 

静态类(static class):不允许创建实例,通常作为工具类(Utility)存在。 

demo7.py 

密封类(sealed class): 禁止被继承。 

demo8.py 

