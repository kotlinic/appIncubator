import 'package:flutter/material.dart';

class Fourth extends StatefulWidget {
  @override
  _FourthState createState() => _FourthState(
        items: new List<String>.generate(
            10000, (i) => "Flutter实践中遇到的部分问题[持续更新]$i"),
      );
}

class _FourthState extends State<Fourth> {
  final List<String> items;

  _FourthState({Key key, @required this.items});

  @override
  Widget build(BuildContext context) {
    final title = 'Long List';

    return new MaterialApp(
      title: title,
      home: new Scaffold(
        appBar: new AppBar(
          title: new Text(title),
        ),
        body: new ListView.builder(
          itemCount: items.length,
          itemBuilder: (context, index) {
            return new ListTile(
              title: new Text('${items[index]}'),
            );
          },
        ),
      ),
    );
  }
}
