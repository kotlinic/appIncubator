import 'package:flutter/material.dart';
import 'package:meta/meta.dart';

void main() {
  runApp(new MyApp( items: new List<String>.generate(10000, (i) => "Item $i"),));
}

/**
 *大量的item 比如上万个
 */
class MyApp extends StatelessWidget {
  final List<String> items;

  MyApp({Key key, @required this.items}) : super(key: key);

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