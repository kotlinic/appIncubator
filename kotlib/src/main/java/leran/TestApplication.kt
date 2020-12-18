package leran

import leran.basis.*
import leran.cls.TypealiasDemo
import leran.cls.classDemo.*
import leran.coroutines.Basis
import leran.design.adapter.TestAdapter
import leran.design.factory.TestFactory
import leran.design.observer.TestObserver
import leran.design.policy.TestPolicy
import leran.design.policy.demo.IBuyTicket
import leran.function.BasisFunctionDemo
import leran.function.InfixFunDemo
import leran.function.SeniorFunctionDemo
import leran.function.lambda.LambdaDemo
import leran.other.CollectionDemo
import leran.other.OperaCollectionDemo

/**
 * Desc           :  程序入口类
 * Author         :  Jetictors
 * Time           :  2019/9/16 14:28
 * Email          :  zhengxcfutures@gmail.com
 * Version        :  v-1.0.1
 */
fun main(args: Array<String>) {

     // 测试 HelloWorld
     HelloWorld().test()

     // 测试 变量
     VariableAndConstantDemo().test()

     // 测试 常量
     Constant().test()

     // 测试 注释
     NoteDemo().test()

     // 测试 数据类型
     DataTypeDemo().test()

     // 测试 控制语句（if、for、while...）
     ControlStatementDemo().test()

     // 测试 空安全
     NullAndNullCheckDemo().test()

     // 测试 操作符
     OperatorStringDemo().test()

     // 测试 函数基础
     BasisFunctionDemo().test()

     // 测试 类的声明
     ClassDemo1().test()

     // 测试 属性与字段
     FieldDemo().test()

     // 测试 抽象类
     AbstarctClassDemo().test()

     // 测试 数据类
     DataClassDemo().test()

     // 测试 枚举类
     EnumDemo().test()

     // 测试 内部类
     InnerClassDemo().test()

     // 测试 密封类
     SealedClassDemo().test()

     // 测试 继承
     InheritClsDemo().test()

     // 测试 接口
     InterfaeDemo().test()

     // 测试 类型别名
     TypealiasDemo().test()

     // 测试 Lambda语法
     LambdaDemo().test()

     // 测试 中缀函数
     val infixDemo = InfixFunDemo()
     infixDemo testInFix("测试中缀函数")
     infixDemo.test()

     // 测试高阶函数
     SeniorFunctionDemo().test()

     // 测试 集合初始化
     CollectionDemo().test()

     // 测试 集合的各种操作
     OperaCollectionDemo().test()

     // 测试协程
     // testCoroutine()

    // 测试工厂模式
    TestFactory().test()

    // 测试 适配器模式
    TestAdapter().test()

    // 测试代理模式
     TestObserver().test()

    // 测试策略模式
    TestPolicy().test(IBuyTicket.TYPE_PLANE)

    // 测试观察者模式
    TestObserver().test()

}

/**
 * 测试协程
 */
private suspend fun testCoroutine(){
    Basis().test()
}

/*
fun main（args：Array< String>）{
     println("我是枚举常量 BLACK ")
}*/
