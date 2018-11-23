/*
 *    Copyright 2018 Fabien Boismoreau
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.bwaim.navigationcomponent_loginflow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

  private NavController navController;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    setupNavigation();
  }

  private void setupNavigation() {
    Set<Integer> starts = new HashSet<>(Arrays.asList(R.id.startFragment, R.id.loginFragment));
    // We define the screen where we don't want the back button on the action bar
    AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(starts).build();
    navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
      switch (item.getItemId()) {
          // We call explicitely the action because NavigationUI.onNavDestinationSelected don't take
          // in consideration the popup configuration
          case R.id.action_startFragment_to_loginFragment:
              navController.navigate(R.id.action_startFragment_to_loginFragment);
              return true;
      }

    return NavigationUI.onNavDestinationSelected(item, navController)
        || super.onOptionsItemSelected(item);
  }
}
