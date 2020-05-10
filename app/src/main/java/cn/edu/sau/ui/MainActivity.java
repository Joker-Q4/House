package cn.edu.sau.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import cn.edu.sau.joker.R;
import cn.edu.sau.ui.basic.BaseFragment;

public class MainActivity extends AppCompatActivity{

    private DrawerLayout drawerLayout;
    private BaseFragment housingInfo,houseOwner,customDemand,housesLease;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化
        init();

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setCheckedItem(R.id.housing_information);
        // 点击事件处理
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                switch (item.getItemId()){
                    // 侧滑栏中各个按钮的点击事件
                    case R.id.housing_information:
                        // 隐藏所有fragment
                        hideAll();
                        // 选择正确的fragment显示
                        transaction.show(housingInfo);
      //                  Toast.makeText(MainActivity.this,"房源信息!",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.houses_owner:
                        hideAll();
                        transaction.show(houseOwner);
      //                  Toast.makeText(MainActivity.this,"房主信息!",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.customer_demand:
                        hideAll();
                        transaction.show(customDemand);
      //                  Toast.makeText(MainActivity.this,"房客需求!",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.houses_lease:
                        hideAll();
                        transaction.show(housesLease);
     //                   Toast.makeText(MainActivity.this,"房屋租赁!",Toast.LENGTH_SHORT).show();
                        break;
                    default:break;
                }
                // 提交事件
                transaction.commit();
                // 关闭侧滑栏
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void init(){
        // 对fragment进行初始化
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // 得到四个基本的fragment
        housingInfo = HousingInfoFragment.newInstance();
        houseOwner = HouseOwnerFragment.newInstance();
        customDemand = CustomerDemandFragment.newInstance();
        housesLease = HousesLeaseFragment.newInstance();
        // 加入transaction
        transaction.add(R.id.fragment, housesLease);
        transaction.add(R.id.fragment, customDemand);
        transaction.add(R.id.fragment, houseOwner);
        transaction.add(R.id.fragment, housingInfo);
        // 事件提交
        transaction.commit();
    }

    private void hideAll(){
        // 隐藏所有的fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(housingInfo)
                .hide(customDemand)
                .hide(housesLease)
                .hide(houseOwner)
                .commit();
    }
}