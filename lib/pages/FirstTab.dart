import 'package:flutter/material.dart';

class FirstTab extends StatefulWidget {
  @override
  _FirstTabState createState() => _FirstTabState();
}

class _FirstTabState extends State<FirstTab> {
  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(
        title: new Text('第一个界面'),
      ),
      body: new Center(
        child: new Text('first'),
      ),
    );
  }
}