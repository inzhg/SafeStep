package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.kakao.vectormap.KakaoMap;
import com.kakao.vectormap.KakaoMapReadyCallback;
import com.kakao.vectormap.KakaoMapSdk;
import com.kakao.vectormap.LatLng;
import com.kakao.vectormap.MapLifeCycleCallback;
import com.kakao.vectormap.MapView;
import com.kakao.vectormap.RoadViewRequest;

public class MainActivity extends AppCompatActivity {

    private MapView mapView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        KakaoMapSdk.init(this, "7WCvq6KpDl1GFGfenbRcSa75PfY=");


        mapView = findViewById(R.id.map_view);
        mapView.start(new MapLifeCycleCallback() {
            @Override
            public void onMapDestroy() {
                // 지도 API가 정상적으로 종료될 때 호출됨
            }

            @Override
            public void onMapError(Exception error) {
                // 인증 실패 및 지도 사용 중 에러가 발생할 때 호출됨
                error.printStackTrace();
            }
        }, new KakaoMapReadyCallback() {
            @Override
            public void onMapReady(KakaoMap kakaoMap) {
                // 인증 후 API가 정상적으로 실행될 때 호출됨
                kakaoMap.moveCamera(LatLng.from(37.5665, 126.9780), 10f);
                addMarker(kakaoMap, LatLng.from(37.5665, 126.9780), "서울 시청");
            }
        });
    }

    private void addMarker(KakaoMap kakaoMap, LatLng position, String title) {
        Marker marker = new Marker();
        marker.setPosition(position);
        marker.setText(title);
        kakaoMap.addMarker(marker);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.destroy();
    }
}