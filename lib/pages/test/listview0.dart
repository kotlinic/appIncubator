/**
 * ListTile
 */
import 'package:flutter/material.dart';

void main() {
  runApp(new MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return new MaterialApp(
      title: "ListTile azm",
      home: new Scaffold(
        appBar: new AppBar(
          title: new Text("ListTile  zm"),
        ),
        body: new MyCard(),
      ),
    );
  }
}

class MyCard extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new MyCardState();
  }
}

class MyCardState extends State<MyCard> {
  var _throwShotAway = false;

  @override
  Widget build(BuildContext context) {
    return new Card(
      child: new ListTile(
          title: new Text("duo_shine"),
          subtitle: new Text("duo_shine@163.com"),
          //之前显示icon
          leading: new Icon(Icons.email, color: Colors.blueAccent),
          //之后显示checkBox
          trailing: new Checkbox(
              value: _throwShotAway,
              onChanged: (bool newValue) {
                setState(() {
                  _throwShotAway = newValue;
                });
              })),
    );
  }
}