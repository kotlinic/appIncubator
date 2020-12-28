import 'package:flutter/material.dart';
import 'package:meta/meta.dart';

void main() {
  runApp(new MyApp());
}

/**
 * 垂直listView
 */
class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final title = 'list';
    return new MaterialApp(
      title: title,
      home: new Scaffold(
        appBar: new AppBar(
          title: new Text(title),
        ),
        body: new Center(
          child: new ListView(
            //控制方向 默认是垂直的
//           scrollDirection: Axis.horizontal,
            children: <Widget>[
              _getContainer('Maps', Icons.map),
              _getContainer('phone', Icons.phone),
              _getContainer('Maps', Icons.map),
            ],
          ),
        ),
      ),
    );
  }

  /**
   * 抽取item项
   */
  Widget _getContainer(String test, IconData icon) {
    return new Container(
      width: 160.0,
//      ListTile
      child: new ListTile(
//       显示在title之前
        leading: new Icon(icon),
//        显示在title之后
        trailing: new Icon(icon),
        title: new Text(test),
        subtitle:new Text("我是subtitle") ,
      ),
    );
  }
}