package b4a.weathermonitor;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = true;
    public static WeakReference<Activity> previousOne;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (processBA == null) {
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.weathermonitor", "b4a.weathermonitor.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
				p.finish();
			}
		}
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		mostCurrent = this;
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "b4a.weathermonitor", "b4a.weathermonitor.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.weathermonitor.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return main.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null) //workaround for emulator bug (Issue 2423)
            return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        processBA.setActivityPaused(true);
        mostCurrent = null;
        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
			if (mostCurrent == null || mostCurrent != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
		    processBA.raiseEvent(mostCurrent._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }

public anywheresoftware.b4a.keywords.Common __c = null;
public static String _server_add_glob = "";
public static String _port_num_glob = "";
public static String _username_glob = "";
public static String _password_glob = "";
public static anywheresoftware.b4j.objects.MqttAsyncClientWrapper _client = null;
public static boolean _connected = false;
public static anywheresoftware.b4a.objects.collections.Map _setting = null;
public anywheresoftware.b4a.objects.EditTextWrapper _server_add = null;
public anywheresoftware.b4a.objects.EditTextWrapper _username = null;
public anywheresoftware.b4a.objects.EditTextWrapper _password = null;
public anywheresoftware.b4a.objects.EditTextWrapper _port_number = null;
public anywheresoftware.b4a.objects.ButtonWrapper _connect = null;
public anywheresoftware.b4a.objects.ButtonWrapper _disconnect = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.ToggleButtonWrapper _led_on_off = null;
public anywheresoftware.b4a.objects.LabelWrapper _temp = null;
public anywheresoftware.b4a.objects.LabelWrapper _humidity = null;
public anywheresoftware.b4a.objects.IME _ime = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.ToggleButtonWrapper _load1 = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.ToggleButtonWrapper _load2 = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.ToggleButtonWrapper _load3 = null;
public anywheresoftware.b4a.objects.CompoundButtonWrapper.ToggleButtonWrapper _load4 = null;
public b4a.weathermonitor.starter _starter = null;

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
return vis;}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 50;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 52;BA.debugLine="Activity.LoadLayout(\"main\")";
mostCurrent._activity.LoadLayout("main",mostCurrent.activityBA);
 //BA.debugLineNum = 62;BA.debugLine="If File.Exists(File.DirInternal, \"setting.txt\") T";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"setting.txt")) { 
 //BA.debugLineNum = 63;BA.debugLine="setting = File.ReadMap(File.DirInternal, \"settin";
_setting = anywheresoftware.b4a.keywords.Common.File.ReadMap(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"setting.txt");
 }else {
 //BA.debugLineNum = 65;BA.debugLine="setting.Initialize";
_setting.Initialize();
 //BA.debugLineNum = 66;BA.debugLine="setting.Put(\"Server_Address\", \"m14.cloudmqtt.com";
_setting.Put((Object)("Server_Address"),(Object)("m14.cloudmqtt.com"));
 //BA.debugLineNum = 67;BA.debugLine="setting.Put(\"Port_Number\", \"18410\")";
_setting.Put((Object)("Port_Number"),(Object)("18410"));
 //BA.debugLineNum = 68;BA.debugLine="setting.Put(\"Username\", \"setsmjwc\")";
_setting.Put((Object)("Username"),(Object)("setsmjwc"));
 //BA.debugLineNum = 69;BA.debugLine="setting.Put(\"Password\", \"apDnKqHRgAjA\")";
_setting.Put((Object)("Password"),(Object)("apDnKqHRgAjA"));
 //BA.debugLineNum = 70;BA.debugLine="File.WriteMap(File.DirInternal, \"setting.txt\", s";
anywheresoftware.b4a.keywords.Common.File.WriteMap(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"setting.txt",_setting);
 };
 //BA.debugLineNum = 72;BA.debugLine="server_add_glob = setting.Get(\"Server_Address\")";
_server_add_glob = BA.ObjectToString(_setting.Get((Object)("Server_Address")));
 //BA.debugLineNum = 73;BA.debugLine="port_num_glob = setting.Get(\"Port_Number\")";
_port_num_glob = BA.ObjectToString(_setting.Get((Object)("Port_Number")));
 //BA.debugLineNum = 74;BA.debugLine="username_glob = setting.Get(\"Username\")";
_username_glob = BA.ObjectToString(_setting.Get((Object)("Username")));
 //BA.debugLineNum = 75;BA.debugLine="password_glob = setting.Get(\"Password\")";
_password_glob = BA.ObjectToString(_setting.Get((Object)("Password")));
 //BA.debugLineNum = 100;BA.debugLine="server_add.Text = server_add_glob";
mostCurrent._server_add.setText(BA.ObjectToCharSequence(_server_add_glob));
 //BA.debugLineNum = 101;BA.debugLine="port_number.Text = port_num_glob";
mostCurrent._port_number.setText(BA.ObjectToCharSequence(_port_num_glob));
 //BA.debugLineNum = 102;BA.debugLine="username.Text = username_glob";
mostCurrent._username.setText(BA.ObjectToCharSequence(_username_glob));
 //BA.debugLineNum = 103;BA.debugLine="password.Text =password_glob";
mostCurrent._password.setText(BA.ObjectToCharSequence(_password_glob));
 //BA.debugLineNum = 106;BA.debugLine="server_add.Enabled = True";
mostCurrent._server_add.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 107;BA.debugLine="port_number.Enabled = True";
mostCurrent._port_number.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 108;BA.debugLine="username.Enabled = True";
mostCurrent._username.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 109;BA.debugLine="password.Enabled = True";
mostCurrent._password.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 110;BA.debugLine="connect.Enabled = True";
mostCurrent._connect.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 111;BA.debugLine="disconnect.Enabled = False";
mostCurrent._disconnect.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 112;BA.debugLine="led_on_off.Enabled = False";
mostCurrent._led_on_off.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 113;BA.debugLine="load1.Enabled = False";
mostCurrent._load1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 114;BA.debugLine="load2.Enabled = False";
mostCurrent._load2.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 115;BA.debugLine="Load3.Enabled = False";
mostCurrent._load3.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 116;BA.debugLine="Load4.Enabled = False";
mostCurrent._load4.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 118;BA.debugLine="IME.Initialize(\"IME\")";
mostCurrent._ime.Initialize("IME");
 //BA.debugLineNum = 119;BA.debugLine="IME.AddHeightChangedEvent";
mostCurrent._ime.AddHeightChangedEvent(mostCurrent.activityBA);
 //BA.debugLineNum = 121;BA.debugLine="IME.SetCustomFilter(port_number, port_number.INPU";
mostCurrent._ime.SetCustomFilter((android.widget.EditText)(mostCurrent._port_number.getObject()),mostCurrent._port_number.INPUT_TYPE_NUMBERS,"0123456789");
 //BA.debugLineNum = 125;BA.debugLine="server_add.InputType = Bit.Or(server_add.InputTyp";
mostCurrent._server_add.setInputType(anywheresoftware.b4a.keywords.Common.Bit.Or(mostCurrent._server_add.getInputType(),(int) (524288)));
 //BA.debugLineNum = 126;BA.debugLine="username.InputType = Bit.Or(username.InputType,52";
mostCurrent._username.setInputType(anywheresoftware.b4a.keywords.Common.Bit.Or(mostCurrent._username.getInputType(),(int) (524288)));
 //BA.debugLineNum = 127;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 139;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 141;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 129;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 132;BA.debugLine="End Sub";
return "";
}
public static String  _client_connected(boolean _success) throws Exception{
 //BA.debugLineNum = 191;BA.debugLine="Sub client_Connected (Success As Boolean)";
 //BA.debugLineNum = 192;BA.debugLine="connected = Success";
_connected = _success;
 //BA.debugLineNum = 193;BA.debugLine="If Success Then";
if (_success) { 
 //BA.debugLineNum = 194;BA.debugLine="Log( \"Connected with MQTT Server\")";
anywheresoftware.b4a.keywords.Common.Log("Connected with MQTT Server");
 //BA.debugLineNum = 195;BA.debugLine="ToastMessageShow(\"Connected!\", True)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Connected!"),anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 199;BA.debugLine="client.Subscribe(\"home/#\", 0)";
_client.Subscribe("home/#",(int) (0));
 //BA.debugLineNum = 201;BA.debugLine="server_add.Enabled = False";
mostCurrent._server_add.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 202;BA.debugLine="port_number.Enabled = False";
mostCurrent._port_number.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 203;BA.debugLine="username.Enabled = False";
mostCurrent._username.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 204;BA.debugLine="password.Enabled = False";
mostCurrent._password.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 205;BA.debugLine="connect.Enabled = False";
mostCurrent._connect.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 206;BA.debugLine="disconnect.Enabled = True";
mostCurrent._disconnect.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 207;BA.debugLine="led_on_off.Enabled = True";
mostCurrent._led_on_off.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 208;BA.debugLine="load1.Enabled = True";
mostCurrent._load1.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 209;BA.debugLine="load2.Enabled = True";
mostCurrent._load2.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 210;BA.debugLine="Load3.Enabled = True";
mostCurrent._load3.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 211;BA.debugLine="Load4.Enabled = True";
mostCurrent._load4.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 213;BA.debugLine="Log(LastException)";
anywheresoftware.b4a.keywords.Common.Log(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.LastException(mostCurrent.activityBA)));
 };
 //BA.debugLineNum = 215;BA.debugLine="End Sub";
return "";
}
public static String  _client_disconnected() throws Exception{
 //BA.debugLineNum = 218;BA.debugLine="Sub client_Disconnected";
 //BA.debugLineNum = 219;BA.debugLine="connected = False";
_connected = anywheresoftware.b4a.keywords.Common.False;
 //BA.debugLineNum = 220;BA.debugLine="client.Close()";
_client.Close();
 //BA.debugLineNum = 221;BA.debugLine="End Sub";
return "";
}
public static String  _client_messagearrived(String _topic,byte[] _payload) throws Exception{
String _rx_string = "";
 //BA.debugLineNum = 224;BA.debugLine="Sub client_MessageArrived (Topic As String, Payloa";
 //BA.debugLineNum = 226;BA.debugLine="Log(\"Topic: \" & Topic)";
anywheresoftware.b4a.keywords.Common.Log("Topic: "+_topic);
 //BA.debugLineNum = 227;BA.debugLine="Log(\"Message: \" & BytesToString(Payload, 0, Paylo";
anywheresoftware.b4a.keywords.Common.Log("Message: "+anywheresoftware.b4a.keywords.Common.BytesToString(_payload,(int) (0),_payload.length,"UTF8"));
 //BA.debugLineNum = 228;BA.debugLine="Dim Rx_String As String";
_rx_string = "";
 //BA.debugLineNum = 229;BA.debugLine="If Payload.Length = 5 Then";
if (_payload.length==5) { 
 //BA.debugLineNum = 231;BA.debugLine="Rx_String = BytesToString(Payload, 0, 2, \"UTF8\")";
_rx_string = anywheresoftware.b4a.keywords.Common.BytesToString(_payload,(int) (0),(int) (2),"UTF8");
 //BA.debugLineNum = 232;BA.debugLine="update_temperature( Rx_String )";
_update_temperature(_rx_string);
 //BA.debugLineNum = 233;BA.debugLine="Rx_String = BytesToString(Payload, 3, 2, \"UTF8\")";
_rx_string = anywheresoftware.b4a.keywords.Common.BytesToString(_payload,(int) (3),(int) (2),"UTF8");
 //BA.debugLineNum = 234;BA.debugLine="update_humidity( Rx_String )";
_update_humidity(_rx_string);
 };
 //BA.debugLineNum = 236;BA.debugLine="End Sub";
return "";
}
public static String  _connect_click() throws Exception{
 //BA.debugLineNum = 144;BA.debugLine="Sub connect_Click";
 //BA.debugLineNum = 146;BA.debugLine="connect_with_server";
_connect_with_server();
 //BA.debugLineNum = 147;BA.debugLine="End Sub";
return "";
}
public static String  _connect_with_server() throws Exception{
anywheresoftware.b4j.objects.MqttAsyncClientWrapper.MqttConnectOptionsWrapper _mo = null;
String _server_info = "";
 //BA.debugLineNum = 156;BA.debugLine="Public Sub connect_with_server()";
 //BA.debugLineNum = 157;BA.debugLine="Private mo As MqttConnectOptions";
_mo = new anywheresoftware.b4j.objects.MqttAsyncClientWrapper.MqttConnectOptionsWrapper();
 //BA.debugLineNum = 165;BA.debugLine="Dim server_info As String";
_server_info = "";
 //BA.debugLineNum = 166;BA.debugLine="server_info = \"tcp://\" & server_add_glob & \":\" &";
_server_info = "tcp://"+_server_add_glob+":"+_port_num_glob;
 //BA.debugLineNum = 167;BA.debugLine="client.Initialize(\"client\", server_info, \"Android";
_client.Initialize(processBA,"client",_server_info,"Android");
 //BA.debugLineNum = 168;BA.debugLine="mo.Initialize(username_glob, password_glob)";
_mo.Initialize(_username_glob,_password_glob);
 //BA.debugLineNum = 169;BA.debugLine="client.Connect2(mo)";
_client.Connect2((org.eclipse.paho.client.mqttv3.MqttConnectOptions)(_mo.getObject()));
 //BA.debugLineNum = 170;BA.debugLine="Log( \"Trying to Connect with MQTT Server\")";
anywheresoftware.b4a.keywords.Common.Log("Trying to Connect with MQTT Server");
 //BA.debugLineNum = 171;BA.debugLine="End Sub";
return "";
}
public static String  _disconnect_click() throws Exception{
 //BA.debugLineNum = 150;BA.debugLine="Sub disconnect_Click";
 //BA.debugLineNum = 152;BA.debugLine="disconnect_with_server";
_disconnect_with_server();
 //BA.debugLineNum = 153;BA.debugLine="End Sub";
return "";
}
public static String  _disconnect_with_server() throws Exception{
 //BA.debugLineNum = 174;BA.debugLine="Public Sub disconnect_with_server()";
 //BA.debugLineNum = 175;BA.debugLine="client_Disconnected";
_client_disconnected();
 //BA.debugLineNum = 177;BA.debugLine="server_add.Enabled = True";
mostCurrent._server_add.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 178;BA.debugLine="port_number.Enabled = True";
mostCurrent._port_number.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 179;BA.debugLine="username.Enabled = True";
mostCurrent._username.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 180;BA.debugLine="password.Enabled = True";
mostCurrent._password.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 181;BA.debugLine="connect.Enabled = True";
mostCurrent._connect.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 182;BA.debugLine="disconnect.Enabled = False";
mostCurrent._disconnect.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 183;BA.debugLine="led_on_off.Enabled = False";
mostCurrent._led_on_off.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 184;BA.debugLine="load1.Enabled = False";
mostCurrent._load1.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 185;BA.debugLine="load2.Enabled = False";
mostCurrent._load2.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 186;BA.debugLine="Load3.Enabled = False";
mostCurrent._load3.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 187;BA.debugLine="Load4.Enabled = False";
mostCurrent._load4.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 188;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 27;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 33;BA.debugLine="Private server_add As EditText";
mostCurrent._server_add = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 34;BA.debugLine="Private username As EditText";
mostCurrent._username = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 35;BA.debugLine="Private password As EditText";
mostCurrent._password = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Private port_number As EditText";
mostCurrent._port_number = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Private connect As Button";
mostCurrent._connect = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Private disconnect As Button";
mostCurrent._disconnect = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Private led_on_off As ToggleButton";
mostCurrent._led_on_off = new anywheresoftware.b4a.objects.CompoundButtonWrapper.ToggleButtonWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private temp As Label";
mostCurrent._temp = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private humidity As Label";
mostCurrent._humidity = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Dim IME As IME";
mostCurrent._ime = new anywheresoftware.b4a.objects.IME();
 //BA.debugLineNum = 44;BA.debugLine="Private load1 As ToggleButton";
mostCurrent._load1 = new anywheresoftware.b4a.objects.CompoundButtonWrapper.ToggleButtonWrapper();
 //BA.debugLineNum = 45;BA.debugLine="Private load2 As ToggleButton";
mostCurrent._load2 = new anywheresoftware.b4a.objects.CompoundButtonWrapper.ToggleButtonWrapper();
 //BA.debugLineNum = 46;BA.debugLine="Private Load3 As ToggleButton";
mostCurrent._load3 = new anywheresoftware.b4a.objects.CompoundButtonWrapper.ToggleButtonWrapper();
 //BA.debugLineNum = 47;BA.debugLine="Private Load4 As ToggleButton";
mostCurrent._load4 = new anywheresoftware.b4a.objects.CompoundButtonWrapper.ToggleButtonWrapper();
 //BA.debugLineNum = 48;BA.debugLine="End Sub";
return "";
}
public static String  _led_buttonchanged(boolean _state) throws Exception{
byte _b = (byte)0;
 //BA.debugLineNum = 279;BA.debugLine="Public Sub led_ButtonChanged(state As Boolean)";
 //BA.debugLineNum = 280;BA.debugLine="Dim b As Byte";
_b = (byte)0;
 //BA.debugLineNum = 281;BA.debugLine="If state Then";
if (_state) { 
 //BA.debugLineNum = 282;BA.debugLine="b = 0x31";
_b = (byte) (0x31);
 }else {
 //BA.debugLineNum = 284;BA.debugLine="b = 0x30";
_b = (byte) (0x30);
 };
 //BA.debugLineNum = 287;BA.debugLine="client.Publish(\"led\", Array As Byte(b))";
_client.Publish("led",new byte[]{_b});
 //BA.debugLineNum = 288;BA.debugLine="End Sub";
return "";
}
public static String  _led_on_off_checkedchange(boolean _checked) throws Exception{
 //BA.debugLineNum = 239;BA.debugLine="Sub led_on_off_CheckedChange(Checked As Boolean)";
 //BA.debugLineNum = 240;BA.debugLine="led_ButtonChanged(Checked)";
_led_buttonchanged(_checked);
 //BA.debugLineNum = 241;BA.debugLine="End Sub";
return "";
}
public static String  _load1_checkedchange(boolean _checked) throws Exception{
byte _b = (byte)0;
 //BA.debugLineNum = 291;BA.debugLine="Sub load1_CheckedChange(Checked As Boolean)";
 //BA.debugLineNum = 292;BA.debugLine="Dim b As Byte";
_b = (byte)0;
 //BA.debugLineNum = 293;BA.debugLine="If Checked Then";
if (_checked) { 
 //BA.debugLineNum = 294;BA.debugLine="b = 0x31";
_b = (byte) (0x31);
 }else {
 //BA.debugLineNum = 296;BA.debugLine="b = 0x30";
_b = (byte) (0x30);
 };
 //BA.debugLineNum = 299;BA.debugLine="client.Publish(\"load1\", Array As Byte(b))";
_client.Publish("load1",new byte[]{_b});
 //BA.debugLineNum = 300;BA.debugLine="End Sub";
return "";
}
public static String  _load2_checkedchange(boolean _checked) throws Exception{
byte _b = (byte)0;
 //BA.debugLineNum = 302;BA.debugLine="Sub load2_CheckedChange(Checked As Boolean)";
 //BA.debugLineNum = 303;BA.debugLine="Dim b As Byte";
_b = (byte)0;
 //BA.debugLineNum = 304;BA.debugLine="If Checked Then";
if (_checked) { 
 //BA.debugLineNum = 305;BA.debugLine="b = 0x31";
_b = (byte) (0x31);
 }else {
 //BA.debugLineNum = 307;BA.debugLine="b = 0x30";
_b = (byte) (0x30);
 };
 //BA.debugLineNum = 310;BA.debugLine="client.Publish(\"load2\", Array As Byte(b))";
_client.Publish("load2",new byte[]{_b});
 //BA.debugLineNum = 311;BA.debugLine="End Sub";
return "";
}
public static String  _load3_checkedchange(boolean _checked) throws Exception{
byte _b = (byte)0;
 //BA.debugLineNum = 313;BA.debugLine="Sub Load3_CheckedChange(Checked As Boolean)";
 //BA.debugLineNum = 314;BA.debugLine="Dim b As Byte";
_b = (byte)0;
 //BA.debugLineNum = 315;BA.debugLine="If Checked Then";
if (_checked) { 
 //BA.debugLineNum = 316;BA.debugLine="b = 0x31";
_b = (byte) (0x31);
 }else {
 //BA.debugLineNum = 318;BA.debugLine="b = 0x30";
_b = (byte) (0x30);
 };
 //BA.debugLineNum = 321;BA.debugLine="client.Publish(\"load3\", Array As Byte(b))";
_client.Publish("load3",new byte[]{_b});
 //BA.debugLineNum = 322;BA.debugLine="End Sub";
return "";
}
public static String  _load4_checkedchange(boolean _checked) throws Exception{
byte _b = (byte)0;
 //BA.debugLineNum = 324;BA.debugLine="Sub Load4_CheckedChange(Checked As Boolean)";
 //BA.debugLineNum = 325;BA.debugLine="Dim b As Byte";
_b = (byte)0;
 //BA.debugLineNum = 326;BA.debugLine="If Checked Then";
if (_checked) { 
 //BA.debugLineNum = 327;BA.debugLine="b = 0x31";
_b = (byte) (0x31);
 }else {
 //BA.debugLineNum = 329;BA.debugLine="b = 0x30";
_b = (byte) (0x30);
 };
 //BA.debugLineNum = 332;BA.debugLine="client.Publish(\"load4\", Array As Byte(b))";
_client.Publish("load4",new byte[]{_b});
 //BA.debugLineNum = 333;BA.debugLine="End Sub";
return "";
}
public static String  _password_enterpressed() throws Exception{
 //BA.debugLineNum = 272;BA.debugLine="Sub password_EnterPressed";
 //BA.debugLineNum = 273;BA.debugLine="setting.Put(\"Password\", password.Text)";
_setting.Put((Object)("Password"),(Object)(mostCurrent._password.getText()));
 //BA.debugLineNum = 274;BA.debugLine="File.WriteMap(File.DirInternal, \"setting.txt\", se";
anywheresoftware.b4a.keywords.Common.File.WriteMap(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"setting.txt",_setting);
 //BA.debugLineNum = 275;BA.debugLine="password_glob = setting.Get(\"Password\")";
_password_glob = BA.ObjectToString(_setting.Get((Object)("Password")));
 //BA.debugLineNum = 276;BA.debugLine="End Sub";
return "";
}
public static String  _port_number_enterpressed() throws Exception{
 //BA.debugLineNum = 260;BA.debugLine="Sub port_number_EnterPressed";
 //BA.debugLineNum = 261;BA.debugLine="setting.Put(\"Port_Number\", port_number.Text)";
_setting.Put((Object)("Port_Number"),(Object)(mostCurrent._port_number.getText()));
 //BA.debugLineNum = 262;BA.debugLine="File.WriteMap(File.DirInternal, \"setting.txt\", se";
anywheresoftware.b4a.keywords.Common.File.WriteMap(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"setting.txt",_setting);
 //BA.debugLineNum = 263;BA.debugLine="port_num_glob = setting.Get(\"Port_Number\")";
_port_num_glob = BA.ObjectToString(_setting.Get((Object)("Port_Number")));
 //BA.debugLineNum = 264;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main._process_globals();
starter._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 18;BA.debugLine="Public server_add_glob As String";
_server_add_glob = "";
 //BA.debugLineNum = 19;BA.debugLine="Public port_num_glob As String";
_port_num_glob = "";
 //BA.debugLineNum = 20;BA.debugLine="Public username_glob As String";
_username_glob = "";
 //BA.debugLineNum = 21;BA.debugLine="Public password_glob As String";
_password_glob = "";
 //BA.debugLineNum = 22;BA.debugLine="Public client As MqttClient";
_client = new anywheresoftware.b4j.objects.MqttAsyncClientWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Public connected As Boolean";
_connected = false;
 //BA.debugLineNum = 24;BA.debugLine="Public setting As Map";
_setting = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 25;BA.debugLine="End Sub";
return "";
}
public static String  _server_add_enterpressed() throws Exception{
 //BA.debugLineNum = 254;BA.debugLine="Sub server_add_EnterPressed";
 //BA.debugLineNum = 255;BA.debugLine="setting.Put(\"Server_Address\", server_add.Text)";
_setting.Put((Object)("Server_Address"),(Object)(mostCurrent._server_add.getText()));
 //BA.debugLineNum = 256;BA.debugLine="File.WriteMap(File.DirInternal, \"setting.txt\", se";
anywheresoftware.b4a.keywords.Common.File.WriteMap(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"setting.txt",_setting);
 //BA.debugLineNum = 257;BA.debugLine="server_add_glob = setting.Get(\"Server_Address\")";
_server_add_glob = BA.ObjectToString(_setting.Get((Object)("Server_Address")));
 //BA.debugLineNum = 258;BA.debugLine="End Sub";
return "";
}
public static String  _update_humidity(String _humid_value) throws Exception{
 //BA.debugLineNum = 249;BA.debugLine="Public Sub update_humidity(humid_value As String )";
 //BA.debugLineNum = 250;BA.debugLine="humidity.Text = humid_value";
mostCurrent._humidity.setText(BA.ObjectToCharSequence(_humid_value));
 //BA.debugLineNum = 251;BA.debugLine="End Sub";
return "";
}
public static String  _update_temperature(String _temp_value) throws Exception{
 //BA.debugLineNum = 244;BA.debugLine="Public Sub update_temperature(temp_value As String";
 //BA.debugLineNum = 245;BA.debugLine="temp.Text = temp_value";
mostCurrent._temp.setText(BA.ObjectToCharSequence(_temp_value));
 //BA.debugLineNum = 246;BA.debugLine="End Sub";
return "";
}
public static String  _username_enterpressed() throws Exception{
 //BA.debugLineNum = 266;BA.debugLine="Sub username_EnterPressed";
 //BA.debugLineNum = 267;BA.debugLine="setting.Put(\"Username\", username.Text)";
_setting.Put((Object)("Username"),(Object)(mostCurrent._username.getText()));
 //BA.debugLineNum = 268;BA.debugLine="File.WriteMap(File.DirInternal, \"setting.txt\", se";
anywheresoftware.b4a.keywords.Common.File.WriteMap(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"setting.txt",_setting);
 //BA.debugLineNum = 269;BA.debugLine="username_glob = setting.Get(\"Username\")";
_username_glob = BA.ObjectToString(_setting.Get((Object)("Username")));
 //BA.debugLineNum = 270;BA.debugLine="End Sub";
return "";
}
}
