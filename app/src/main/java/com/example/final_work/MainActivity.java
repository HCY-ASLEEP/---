package com.example.final_work;

import static android.view.KeyEvent.KEYCODE_BACK;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;


public class MainActivity extends BasicActivity {
    private NetWorkChangeReceiver networkChangeReceiver;
    private WebView webView;
    private boolean mChange;
    private int containerId;
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = this.getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_main);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetWorkChangeReceiver(this);
        registerReceiver(networkChangeReceiver, intentFilter);


        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView welcome = findViewById(R.id.welcome);
        welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Welcome to my waste sorting app!", Toast.LENGTH_SHORT).show();
                //replaceFragment(new VideoFragment(),R.id.land_container);
            }
        });

        TextView backMain = findViewById(R.id.toolbar_back_main);

        TextView nav_search = findViewById(R.id.nav_search);
        TextView nav_picture = findViewById(R.id.nav_picture);
        TextView nav_news = findViewById(R.id.nav_news);
        TextView nav_class = findViewById(R.id.nav_class);
        TextView nav_home=findViewById(R.id.nav_home);
        TextView nav_video=findViewById(R.id.nav_video);


        if (this.findViewById(R.id.drawer_layout_right) != null) {
            drawerLayout = findViewById(R.id.drawerLayout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();


//            LinearLayout layout=findViewById(R.id.UUU);
//            layout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    drawerLayout.closeDrawer(Gravity.LEFT);
//                }
//            });
            containerId = R.id.drawer_layout_right;
            nav_class.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    replaceFragment(new HorizonTabLayoutFragment(), containerId);

                    drawerLayout.closeDrawer(Gravity.LEFT);
                }
            });

        } else {
            containerId = R.id.land_container;
            nav_class.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    replaceFragment(new VerticalTabLayoutFragment(), containerId);
                }
            });
        }


        replaceFragment(new HomeFragment(),containerId);




        backMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new HomeFragment(), containerId);
            }
        });

        nav_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new HomeFragment(),containerId);
                if (MainActivity.this.findViewById(R.id.drawer_layout_right) != null){
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }
            }
        });


        nav_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new SearchFragment(), containerId);
                if (MainActivity.this.findViewById(R.id.drawer_layout_right) != null){
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }
            }
        });

        nav_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new UploadFragment(), containerId);
                if (MainActivity.this.findViewById(R.id.drawer_layout_right) != null){
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }
            }
        });

        nav_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new WebViewFragment(), containerId);
                if (MainActivity.this.findViewById(R.id.drawer_layout_right) != null){
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }
            }
        });

        nav_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new VideoFragment(),containerId);
                if (MainActivity.this.findViewById(R.id.drawer_layout_right) != null){
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }
            }
        });

        TextView menu = findViewById(R.id.menu_in_main_activity);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopMenu(menu);
            }
        });
    }


    private void showPopMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.menu_main_activity, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int menuId=menuItem.getItemId();
                if(menuId==R.id.app_settings){
                    replaceFragment(new SettingFragment(),containerId);
                }
                if(menuId==R.id.app_feedback){
                    AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                    View temp=View.inflate(MainActivity.this,R.layout.feedback,null);
                    builder.setView(temp);
                    final AlertDialog dia =builder.show();
                    Button button=temp.findViewById(R.id.submit);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dia.dismiss();
                            AlertDialog.Builder thanks=new AlertDialog.Builder(MainActivity.this);
                            thanks.setMessage("感谢您的反馈！");
                            thanks.show();
                        }
                    });
                }
                if (menuId==R.id.app_about){
                   AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                   View temp=View.inflate(MainActivity.this,R.layout.about,null);
                   builder.setView(temp);
                   builder.show();
                }
                Toast.makeText(getApplicationContext(), menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        popupMenu.show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void setWebView(WebView webView) {
        this.webView = webView;
    }


    private void replaceFragment(Fragment fragment, final int layoutId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(layoutId, fragment);
        fragmentTransaction.commit();
    }


    //Important!
    @Override
    protected void onSaveInstanceState(Bundle outBundle) {
        super.onSaveInstanceState(outBundle);
        outBundle.putBoolean("RoadChange", mChange);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mChange = savedInstanceState.getBoolean("RoadChange");
    }




}