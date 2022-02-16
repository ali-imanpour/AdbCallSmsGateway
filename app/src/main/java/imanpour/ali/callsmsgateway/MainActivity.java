package imanpour.ali.callsmsgateway;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.webkit.PermissionRequest;
import android.widget.Toast;

import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.RequestCallback;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getIntent().hasExtra("cmd")){
            String cmd = getIntent().getStringExtra("cmd");

            if(cmd.equals("sendSMS")){
                String phone = getIntent().getStringExtra("phone");
                String message = getIntent().getStringExtra("message");
                sendSMS(phone, message);
            }else if(cmd.equals("call")){
                String phone = getIntent().getStringExtra("phone");
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + phone));
                startActivity(intent);
            }

            Toast.makeText(this, getIntent().getStringExtra("cmd"), Toast.LENGTH_LONG).show();
            finish();
        }

        PermissionX.init(this)
                .permissions(Manifest.permission.SEND_SMS, Manifest.permission.CALL_PHONE)
                .request((allGranted, grantedList, deniedList) -> {
                    if (allGranted) {
                        Toast.makeText(this, "All permissions are granted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "These permissions are denied: $deniedList", Toast.LENGTH_LONG).show();
                    }
                });


    }

    private void sendSMS(String phoneNo, String msg) {
        try {
            SmsManager smsManager = SmsManager.getDefault();

            if(phoneNo.contains("_")){
                String[] nums = phoneNo.split("_");
                for (int i = 0; i < nums.length; i++) {
                    smsManager.sendTextMessage(nums[i], null, msg, null, null);
                    Thread.sleep(500);
                }
            }else {
                smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            }

            Toast.makeText(getApplicationContext(), "Message Sent",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }
}