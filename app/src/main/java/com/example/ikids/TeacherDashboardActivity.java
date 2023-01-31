package com.example.ikids;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ikids.auth.TeacherLoginActivity;
import com.example.ikids.databinding.ActivityTeacherDashboardBinding;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class TeacherDashboardActivity extends AppCompatActivity  {

    TextView name, email;

    private FirebaseAuth mAuth;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityTeacherDashboardBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTeacherDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //set nav bar
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
//        NavigationView navigationView = findViewById(R.id.navigation_view);
//        View headerLayout =  navigationView.inflateHeaderView(R.layout.nav_header);
//
//        name = headerLayout.findViewById(R.id.name);
//        email = headerLayout.findViewById(R.id.email);
//
//        mAuth = FirebaseAuth.getInstance();
//        String getemail = mAuth.getCurrentUser().getEmail();
//
//        email.setText(getemail);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
               R.id.nav_dashboard, R.id.nav_user, R.id.nav_level,R.id.nav_settings,R.id.nav_logout)
                .setOpenableLayout(binding.drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_container);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(binding.navigationView, navController);

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_container);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}