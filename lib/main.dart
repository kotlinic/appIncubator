import 'package:flutter/material.dart';

import 'main2_tab.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Welcome to Flutter',
      home: Scaffold(
        appBar: AppBar(
          title: Text('Welcome to Flutter zm..'),
        ),
        body: Center(
          child: Text('Hello World'),

        ),
      ),
    );
  }

/*  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[
        Row(
          children: <Widget>[
            RaisedButton(
              child:Text('普通按钮'),
              onPressed: (){
                print("这是一个普通按钮");
                *//*Navigator.push(
                    context,
                    MaterialPageRoute(
                        builder: (context) =>
                        new HomePage()));*//*
              },
            ),
          ],
        ),
      ],
    );
  }*/
}