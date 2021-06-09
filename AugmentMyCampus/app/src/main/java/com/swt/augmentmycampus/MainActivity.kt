package com.swt.augmentmycampus

import android.os.Bundle
import android.os.StrictMode
import android.view.MenuInflater
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.swt.augmentmycampus.ui.camera.CameraFragment
import com.swt.augmentmycampus.ui.data.DataFragment
import com.swt.augmentmycampus.ui.search.SearchFragment
import com.swt.augmentmycampus.ui.settings.SettingsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var navController: NavController;

    val cameraFragment : CameraFragment = CameraFragment()
    val dataFragment : DataFragment = DataFragment()
    val settingsFragment : SettingsFragment = SettingsFragment()
    val searchFragment : SearchFragment = SearchFragment()
    val fragmentManager : FragmentManager = supportFragmentManager;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.main_activity_main)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navHostFragment = fragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        navView.setupWithNavController(navController)
    }

    fun registerButtonOnClick(view: View) {
        val registerButton: Button = view.findViewById(R.id.registerButton)
        if (registerButton.text == "Register") {
            registerButton.text = "Unregister"
        } else {
            registerButton.text = "Register"
        }
    }

    fun flashButtonOnClick(view: View)
    {
        var nav: NavHostFragment =(fragmentManager.findFragmentById(R.id.nav_host_fragment)) as NavHostFragment
        var frag: CameraFragment = nav.childFragmentManager.primaryNavigationFragment as CameraFragment
        frag.toggleFlash(view)
    }

    var isPastEventsHidden : Boolean = false

    fun showHideButtonOnClick(v : View) {
        val popup = PopupMenu(this, v)
        val inflater : MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.menu_expandable_list_view_dates, popup.menu)

        // We need this text logic because we always build the popup menu from scratch based on the xml file.
        if (isPastEventsHidden)
            popup.menu.findItem(R.id.action_show_hide_past_events).setTitle(R.string.action_show_past_events)
        else
            popup.menu.findItem(R.id.action_show_hide_past_events).setTitle(R.string.action_hide_past_events)

        popup.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId) {
                R.id.action_show_hide_past_events-> {
                    if (isPastEventsHidden) {
                        // TODO: Show past events
                        isPastEventsHidden = false
                    }
                    else {
                        // TODO: Hide past events
                        isPastEventsHidden = true
                    }
                }
            }
            true
        }

        popup.show()
    }
}