package b4a.weathermonitor.designerscripts;
import anywheresoftware.b4a.objects.TextViewWrapper;
import anywheresoftware.b4a.objects.ImageViewWrapper;
import anywheresoftware.b4a.BA;


public class LS_main{

public static void LS_general(java.util.LinkedHashMap<String, anywheresoftware.b4a.keywords.LayoutBuilder.ViewWrapperAndAnchor> views, int width, int height, float scale) {
anywheresoftware.b4a.keywords.LayoutBuilder.setScaleRate(0.3);
String _height="";
//BA.debugLineNum = 2;BA.debugLine="AutoScaleAll"[main/General script]
anywheresoftware.b4a.keywords.LayoutBuilder.scaleAll(views);
//BA.debugLineNum = 3;BA.debugLine="height = 7%y"[main/General script]
_height = BA.NumberToString((7d / 100 * height));
//BA.debugLineNum = 4;BA.debugLine="server_lbl.Top = 0%y"[main/General script]
views.get("server_lbl").vw.setTop((int)((0d / 100 * height)));
//BA.debugLineNum = 5;BA.debugLine="server_lbl.Left = 2%x"[main/General script]
views.get("server_lbl").vw.setLeft((int)((2d / 100 * width)));
//BA.debugLineNum = 6;BA.debugLine="server_lbl.Height = height"[main/General script]
views.get("server_lbl").vw.setHeight((int)(Double.parseDouble(_height)));
//BA.debugLineNum = 7;BA.debugLine="server_lbl.Width = 50%x - 2%x"[main/General script]
views.get("server_lbl").vw.setWidth((int)((50d / 100 * width)-(2d / 100 * width)));
//BA.debugLineNum = 9;BA.debugLine="server_add.Top = 0%y"[main/General script]
views.get("server_add").vw.setTop((int)((0d / 100 * height)));
//BA.debugLineNum = 10;BA.debugLine="server_add.Left = server_lbl.Right"[main/General script]
views.get("server_add").vw.setLeft((int)((views.get("server_lbl").vw.getLeft() + views.get("server_lbl").vw.getWidth())));
//BA.debugLineNum = 11;BA.debugLine="server_add.Height = height"[main/General script]
views.get("server_add").vw.setHeight((int)(Double.parseDouble(_height)));
//BA.debugLineNum = 12;BA.debugLine="server_add.Width = 50%x - 2%x"[main/General script]
views.get("server_add").vw.setWidth((int)((50d / 100 * width)-(2d / 100 * width)));
//BA.debugLineNum = 15;BA.debugLine="port_lbl.Top = server_lbl.Bottom"[main/General script]
views.get("port_lbl").vw.setTop((int)((views.get("server_lbl").vw.getTop() + views.get("server_lbl").vw.getHeight())));
//BA.debugLineNum = 16;BA.debugLine="port_lbl.Left = 2%x"[main/General script]
views.get("port_lbl").vw.setLeft((int)((2d / 100 * width)));
//BA.debugLineNum = 17;BA.debugLine="port_lbl.Height = height"[main/General script]
views.get("port_lbl").vw.setHeight((int)(Double.parseDouble(_height)));
//BA.debugLineNum = 18;BA.debugLine="port_lbl.Width = 50%x - 2%x"[main/General script]
views.get("port_lbl").vw.setWidth((int)((50d / 100 * width)-(2d / 100 * width)));
//BA.debugLineNum = 20;BA.debugLine="port_number.Top = server_lbl.Bottom"[main/General script]
views.get("port_number").vw.setTop((int)((views.get("server_lbl").vw.getTop() + views.get("server_lbl").vw.getHeight())));
//BA.debugLineNum = 21;BA.debugLine="port_number.Left = port_lbl.Right"[main/General script]
views.get("port_number").vw.setLeft((int)((views.get("port_lbl").vw.getLeft() + views.get("port_lbl").vw.getWidth())));
//BA.debugLineNum = 22;BA.debugLine="port_number.Height = height"[main/General script]
views.get("port_number").vw.setHeight((int)(Double.parseDouble(_height)));
//BA.debugLineNum = 23;BA.debugLine="port_number.Width = 50%x - 2%x"[main/General script]
views.get("port_number").vw.setWidth((int)((50d / 100 * width)-(2d / 100 * width)));
//BA.debugLineNum = 25;BA.debugLine="username_lbl.Top = port_lbl.Bottom"[main/General script]
views.get("username_lbl").vw.setTop((int)((views.get("port_lbl").vw.getTop() + views.get("port_lbl").vw.getHeight())));
//BA.debugLineNum = 26;BA.debugLine="username_lbl.Left = 2%x"[main/General script]
views.get("username_lbl").vw.setLeft((int)((2d / 100 * width)));
//BA.debugLineNum = 27;BA.debugLine="username_lbl.Height = height"[main/General script]
views.get("username_lbl").vw.setHeight((int)(Double.parseDouble(_height)));
//BA.debugLineNum = 28;BA.debugLine="username_lbl.Width = 50%x - 2%x"[main/General script]
views.get("username_lbl").vw.setWidth((int)((50d / 100 * width)-(2d / 100 * width)));
//BA.debugLineNum = 30;BA.debugLine="username.Top = port_lbl.Bottom"[main/General script]
views.get("username").vw.setTop((int)((views.get("port_lbl").vw.getTop() + views.get("port_lbl").vw.getHeight())));
//BA.debugLineNum = 31;BA.debugLine="username.Left = username_lbl.Right"[main/General script]
views.get("username").vw.setLeft((int)((views.get("username_lbl").vw.getLeft() + views.get("username_lbl").vw.getWidth())));
//BA.debugLineNum = 32;BA.debugLine="username.Height = height"[main/General script]
views.get("username").vw.setHeight((int)(Double.parseDouble(_height)));
//BA.debugLineNum = 33;BA.debugLine="username.Width = 50%x - 2%x"[main/General script]
views.get("username").vw.setWidth((int)((50d / 100 * width)-(2d / 100 * width)));
//BA.debugLineNum = 35;BA.debugLine="password_lbl.Top = username_lbl.Bottom"[main/General script]
views.get("password_lbl").vw.setTop((int)((views.get("username_lbl").vw.getTop() + views.get("username_lbl").vw.getHeight())));
//BA.debugLineNum = 36;BA.debugLine="password_lbl.Left = 2%x"[main/General script]
views.get("password_lbl").vw.setLeft((int)((2d / 100 * width)));
//BA.debugLineNum = 37;BA.debugLine="password_lbl.Height = height"[main/General script]
views.get("password_lbl").vw.setHeight((int)(Double.parseDouble(_height)));
//BA.debugLineNum = 38;BA.debugLine="password_lbl.Width = 50%x - 2%x"[main/General script]
views.get("password_lbl").vw.setWidth((int)((50d / 100 * width)-(2d / 100 * width)));
//BA.debugLineNum = 40;BA.debugLine="password.Top = username_lbl.Bottom"[main/General script]
views.get("password").vw.setTop((int)((views.get("username_lbl").vw.getTop() + views.get("username_lbl").vw.getHeight())));
//BA.debugLineNum = 41;BA.debugLine="password.Left = password_lbl.Right"[main/General script]
views.get("password").vw.setLeft((int)((views.get("password_lbl").vw.getLeft() + views.get("password_lbl").vw.getWidth())));
//BA.debugLineNum = 42;BA.debugLine="password.Height = height"[main/General script]
views.get("password").vw.setHeight((int)(Double.parseDouble(_height)));
//BA.debugLineNum = 43;BA.debugLine="password.Width = 50%x - 2%x"[main/General script]
views.get("password").vw.setWidth((int)((50d / 100 * width)-(2d / 100 * width)));
//BA.debugLineNum = 45;BA.debugLine="connect.Top = password.Bottom"[main/General script]
views.get("connect").vw.setTop((int)((views.get("password").vw.getTop() + views.get("password").vw.getHeight())));
//BA.debugLineNum = 46;BA.debugLine="connect.Left = 2%x"[main/General script]
views.get("connect").vw.setLeft((int)((2d / 100 * width)));
//BA.debugLineNum = 47;BA.debugLine="connect.Height = height"[main/General script]
views.get("connect").vw.setHeight((int)(Double.parseDouble(_height)));
//BA.debugLineNum = 48;BA.debugLine="connect.Width = 50%x - 2%x"[main/General script]
views.get("connect").vw.setWidth((int)((50d / 100 * width)-(2d / 100 * width)));
//BA.debugLineNum = 50;BA.debugLine="disconnect.Top = password.Bottom"[main/General script]
views.get("disconnect").vw.setTop((int)((views.get("password").vw.getTop() + views.get("password").vw.getHeight())));
//BA.debugLineNum = 51;BA.debugLine="disconnect.Left = connect.Right"[main/General script]
views.get("disconnect").vw.setLeft((int)((views.get("connect").vw.getLeft() + views.get("connect").vw.getWidth())));
//BA.debugLineNum = 52;BA.debugLine="disconnect.Height = height"[main/General script]
views.get("disconnect").vw.setHeight((int)(Double.parseDouble(_height)));
//BA.debugLineNum = 53;BA.debugLine="disconnect.Width = 50%x - 2%x"[main/General script]
views.get("disconnect").vw.setWidth((int)((50d / 100 * width)-(2d / 100 * width)));
//BA.debugLineNum = 55;BA.debugLine="temp_lbl.Top = connect.Bottom + 10%x"[main/General script]
views.get("temp_lbl").vw.setTop((int)((views.get("connect").vw.getTop() + views.get("connect").vw.getHeight())+(10d / 100 * width)));
//BA.debugLineNum = 56;BA.debugLine="temp_lbl.Left = 2%x"[main/General script]
views.get("temp_lbl").vw.setLeft((int)((2d / 100 * width)));
//BA.debugLineNum = 57;BA.debugLine="temp_lbl.Height = height"[main/General script]
views.get("temp_lbl").vw.setHeight((int)(Double.parseDouble(_height)));
//BA.debugLineNum = 58;BA.debugLine="temp_lbl.Width = 25%x"[main/General script]
views.get("temp_lbl").vw.setWidth((int)((25d / 100 * width)));
//BA.debugLineNum = 60;BA.debugLine="temp.Top = temp_lbl.Bottom"[main/General script]
views.get("temp").vw.setTop((int)((views.get("temp_lbl").vw.getTop() + views.get("temp_lbl").vw.getHeight())));
//BA.debugLineNum = 61;BA.debugLine="temp.Left = 2%x"[main/General script]
views.get("temp").vw.setLeft((int)((2d / 100 * width)));
//BA.debugLineNum = 62;BA.debugLine="temp.Height = height"[main/General script]
views.get("temp").vw.setHeight((int)(Double.parseDouble(_height)));
//BA.debugLineNum = 63;BA.debugLine="temp.Width = 18%x"[main/General script]
views.get("temp").vw.setWidth((int)((18d / 100 * width)));
//BA.debugLineNum = 65;BA.debugLine="c_lbl.Top = temp_lbl.Bottom"[main/General script]
views.get("c_lbl").vw.setTop((int)((views.get("temp_lbl").vw.getTop() + views.get("temp_lbl").vw.getHeight())));
//BA.debugLineNum = 66;BA.debugLine="c_lbl.Left = temp.Right + 2%x"[main/General script]
views.get("c_lbl").vw.setLeft((int)((views.get("temp").vw.getLeft() + views.get("temp").vw.getWidth())+(2d / 100 * width)));
//BA.debugLineNum = 67;BA.debugLine="c_lbl.Height = height"[main/General script]
views.get("c_lbl").vw.setHeight((int)(Double.parseDouble(_height)));
//BA.debugLineNum = 68;BA.debugLine="c_lbl.Width = 5%x"[main/General script]
views.get("c_lbl").vw.setWidth((int)((5d / 100 * width)));
//BA.debugLineNum = 70;BA.debugLine="humid_lbl.Top = temp_lbl.Top"[main/General script]
views.get("humid_lbl").vw.setTop((int)((views.get("temp_lbl").vw.getTop())));
//BA.debugLineNum = 71;BA.debugLine="humid_lbl.Left = temp_lbl.Right + 10%x"[main/General script]
views.get("humid_lbl").vw.setLeft((int)((views.get("temp_lbl").vw.getLeft() + views.get("temp_lbl").vw.getWidth())+(10d / 100 * width)));
//BA.debugLineNum = 72;BA.debugLine="humid_lbl.Height = height"[main/General script]
views.get("humid_lbl").vw.setHeight((int)(Double.parseDouble(_height)));
//BA.debugLineNum = 73;BA.debugLine="humid_lbl.Width = 25%x"[main/General script]
views.get("humid_lbl").vw.setWidth((int)((25d / 100 * width)));
//BA.debugLineNum = 75;BA.debugLine="humidity.Top = temp.Top"[main/General script]
views.get("humidity").vw.setTop((int)((views.get("temp").vw.getTop())));
//BA.debugLineNum = 76;BA.debugLine="humidity.Left = c_lbl.Right + 10%x"[main/General script]
views.get("humidity").vw.setLeft((int)((views.get("c_lbl").vw.getLeft() + views.get("c_lbl").vw.getWidth())+(10d / 100 * width)));
//BA.debugLineNum = 77;BA.debugLine="humidity.Height = height"[main/General script]
views.get("humidity").vw.setHeight((int)(Double.parseDouble(_height)));
//BA.debugLineNum = 78;BA.debugLine="humidity.Width = 18%x"[main/General script]
views.get("humidity").vw.setWidth((int)((18d / 100 * width)));
//BA.debugLineNum = 80;BA.debugLine="percebt_lbl.Top = humidity.Top"[main/General script]
views.get("percebt_lbl").vw.setTop((int)((views.get("humidity").vw.getTop())));
//BA.debugLineNum = 81;BA.debugLine="percebt_lbl.Left = humidity.Right + 2%x"[main/General script]
views.get("percebt_lbl").vw.setLeft((int)((views.get("humidity").vw.getLeft() + views.get("humidity").vw.getWidth())+(2d / 100 * width)));
//BA.debugLineNum = 82;BA.debugLine="percebt_lbl.Height = height"[main/General script]
views.get("percebt_lbl").vw.setHeight((int)(Double.parseDouble(_height)));
//BA.debugLineNum = 83;BA.debugLine="percebt_lbl.Width = 5%x"[main/General script]
views.get("percebt_lbl").vw.setWidth((int)((5d / 100 * width)));
//BA.debugLineNum = 85;BA.debugLine="led_on_off.Top = humid_lbl.Top"[main/General script]
views.get("led_on_off").vw.setTop((int)((views.get("humid_lbl").vw.getTop())));
//BA.debugLineNum = 86;BA.debugLine="led_on_off.Left = humid_lbl.Right + 10%x"[main/General script]
views.get("led_on_off").vw.setLeft((int)((views.get("humid_lbl").vw.getLeft() + views.get("humid_lbl").vw.getWidth())+(10d / 100 * width)));
//BA.debugLineNum = 87;BA.debugLine="led_on_off.Height = 2*height"[main/General script]
views.get("led_on_off").vw.setHeight((int)(2d*Double.parseDouble(_height)));
//BA.debugLineNum = 88;BA.debugLine="led_on_off.Width = 25%x"[main/General script]
views.get("led_on_off").vw.setWidth((int)((25d / 100 * width)));
//BA.debugLineNum = 90;BA.debugLine="load1.Top = temp.Bottom + 5%y"[main/General script]
views.get("load1").vw.setTop((int)((views.get("temp").vw.getTop() + views.get("temp").vw.getHeight())+(5d / 100 * height)));
//BA.debugLineNum = 91;BA.debugLine="load1.Left = 2%x"[main/General script]
views.get("load1").vw.setLeft((int)((2d / 100 * width)));
//BA.debugLineNum = 92;BA.debugLine="load1.Height = height*2"[main/General script]
views.get("load1").vw.setHeight((int)(Double.parseDouble(_height)*2d));
//BA.debugLineNum = 93;BA.debugLine="load1.Width = 40%x"[main/General script]
views.get("load1").vw.setWidth((int)((40d / 100 * width)));
//BA.debugLineNum = 95;BA.debugLine="load2.Top = led_on_off.Bottom + 5%y"[main/General script]
views.get("load2").vw.setTop((int)((views.get("led_on_off").vw.getTop() + views.get("led_on_off").vw.getHeight())+(5d / 100 * height)));
//BA.debugLineNum = 96;BA.debugLine="load2.Width = 40%x"[main/General script]
views.get("load2").vw.setWidth((int)((40d / 100 * width)));
//BA.debugLineNum = 97;BA.debugLine="load2.Height = height*2"[main/General script]
views.get("load2").vw.setHeight((int)(Double.parseDouble(_height)*2d));
//BA.debugLineNum = 98;BA.debugLine="load2.Right = led_on_off.Right"[main/General script]
views.get("load2").vw.setLeft((int)((views.get("led_on_off").vw.getLeft() + views.get("led_on_off").vw.getWidth()) - (views.get("load2").vw.getWidth())));
//BA.debugLineNum = 100;BA.debugLine="Load3.Top = load1.Bottom + 5%y"[main/General script]
views.get("load3").vw.setTop((int)((views.get("load1").vw.getTop() + views.get("load1").vw.getHeight())+(5d / 100 * height)));
//BA.debugLineNum = 101;BA.debugLine="Load3.Left = 2%x"[main/General script]
views.get("load3").vw.setLeft((int)((2d / 100 * width)));
//BA.debugLineNum = 102;BA.debugLine="Load3.Height = height*2"[main/General script]
views.get("load3").vw.setHeight((int)(Double.parseDouble(_height)*2d));
//BA.debugLineNum = 103;BA.debugLine="Load3.Width = 40%x"[main/General script]
views.get("load3").vw.setWidth((int)((40d / 100 * width)));
//BA.debugLineNum = 105;BA.debugLine="Load4.Top = load2.Bottom + 5%y"[main/General script]
views.get("load4").vw.setTop((int)((views.get("load2").vw.getTop() + views.get("load2").vw.getHeight())+(5d / 100 * height)));
//BA.debugLineNum = 106;BA.debugLine="Load4.Height = height*2"[main/General script]
views.get("load4").vw.setHeight((int)(Double.parseDouble(_height)*2d));
//BA.debugLineNum = 107;BA.debugLine="Load4.Width = 40%x"[main/General script]
views.get("load4").vw.setWidth((int)((40d / 100 * width)));
//BA.debugLineNum = 108;BA.debugLine="Load4.Right = led_on_off.Right"[main/General script]
views.get("load4").vw.setLeft((int)((views.get("led_on_off").vw.getLeft() + views.get("led_on_off").vw.getWidth()) - (views.get("load4").vw.getWidth())));

}
}