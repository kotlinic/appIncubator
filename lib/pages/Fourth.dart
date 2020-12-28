import 'package:flutter/material.dart';
class Fourth extends StatefulWidget {
  @override
  _FourthState createState() => _FourthState();
}

class _FourthState extends State<Fourth> {
    @override
    Widget build(BuildContext context) {
      return new Scaffold(
        appBar: new AppBar(
          title: new Text('第四个界面'),
        ),
        body: new Center(
          child: new Text('Fourth'),
        ),
      );;
    }
}
