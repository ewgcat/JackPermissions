package com.lishuaihua.jackpermissions

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.lishuaihua.permissions.JackPermissions
import com.lishuaihua.permissions.OnPermission

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initPermissions(permissions)
    }


    internal var permissions = arrayOf(
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.CALL_PHONE,
        Manifest.permission.CAMERA,
        Manifest.permission.READ_CALENDAR,
        Manifest.permission.WRITE_CALENDAR,
        Manifest.permission.READ_CONTACTS,
        Manifest.permission.MANAGE_EXTERNAL_STORAGE,
        Manifest.permission.CHANGE_NETWORK_STATE,
        Manifest.permission.ACCESS_NETWORK_STATE,
        Manifest.permission.ACCESS_WIFI_STATE,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.READ_SMS
    )

    /**
     * 运行时请求权限
     */
    private fun initPermissions(permissions: Array<String>) {
        JackPermissions.with(this) // 申请安装包权限
            .permission(permissions) // 申请多个权限
            .request(object : OnPermission {
                override fun onGranted(granted: List<String>, all: Boolean) {
                    if (!all) {
                        initPermissions(permissions)
                    }else {
                        Toast.makeText(this@MainActivity,"已全部获取全部权限",Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onDenied(denied: List<String>, never: Boolean) {
                    initPermissions(permissions)
                }
            })
    }

}