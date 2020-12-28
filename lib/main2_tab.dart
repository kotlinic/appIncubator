import 'package:flutter/material.dart';
import 'package:zm_flutter_app/pages/Fourth.dart';
import './pages/FirstTab.dart';
import './pages/SecondTab.dart';
import './pages/ThirdTab.dart';

void main(){
  runApp(
      new MaterialApp(
          title:'tabdemo',
          home:new HomePage()
      )
  );
}

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() =>new _HomePageState();
}

class _HomePageState extends State<HomePage> with SingleTickerProviderStateMixin{

  //Tab页的控制器，可以用来定义Tab标签和内容页的坐标
  TabController tabcontroller;


  //生命周期方法插入渲染树时调用，只调用一次
  @override
  void initState() {
    super.initState();
    tabcontroller = new TabController(
        length: 4,   //Tab页的个数
        vsync: this //动画效果的异步处理，默认格式
    );
  }

  //生命周期方法构建Widget时调用
  @override
  Widget build(BuildContext context) {
    return new Scaffold(
        body: new TabBarView(
          controller: tabcontroller,
          children: <Widget>[
            //创建之前写好的三个页面，万物皆是Widget
            new FirstTab(),
            new SecondTab(),
            new ThirdTab(),
            new Fourth(),
          ],
        ),
        bottomNavigationBar: new Material(
          //底部栏整体的颜色
          color: Colors.blueAccent,
          child: new TabBar(
            controller: tabcontroller,
            tabs: <Tab>[
              new Tab(text: "第一个",icon: new Icon(Icons.android)),
              new Tab(text: "第二个",icon: new Icon(Icons.home)),
              new Tab(text: "第三个",icon: new Icon(Icons.accessibility)),
              new Tab(text: "第四个",icon: new Icon(Icons.home)),
            ],
            //tab被选中时的颜色，设置之后选中的时候，icon和text都会变色
            labelColor: Colors.amber,
            //tab未被选中时的颜色，设置之后选中的时候，icon和text都会变色
            unselectedLabelColor: Colors.black,
          ),
        )
    );
  }

  //组件即将销毁时调用
  @override
  void dispose() {
    //释放内存，节省开销
    tabcontroller.dispose();
    super.dispose();
  }
}