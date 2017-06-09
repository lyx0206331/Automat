package com.adrian.automat.activities.fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.adrian.automat.R;
import com.just.library.AgentWeb;
import com.just.library.ChromeClientCallbackManager;
import com.just.library.WebDefaultSettingsManager;
import com.just.library.WebSettings;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityFragment extends BaseFragment {

    protected AgentWeb mAgentWeb;

    public ActivityFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAgentWeb = AgentWeb.with(this)//
                .setAgentWebParent((ViewGroup) view, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))//
                .setIndicatorColorWithHeight(-1,2)//
                .setWebSettings(getSettings())//
                .setWebViewClient(mWebViewClient)
                .setReceivedTitleCallback(mCallback)
                .setSecurityType(AgentWeb.SecurityType.strict)
                .createAgentWeb()//
                .ready()//
                .go(getUrl());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity, container, false);
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroyView();
    }

    public WebSettings getSettings(){
        return WebDefaultSettingsManager.getInstance();
    }
    public String getUrl(){
        String target="";

//        if(TextUtils.isEmpty(target=this.getArguments().getString(URL_KEY))){
        target = "http://www.baidu.com";
//        }
        return target;
    }
    protected ChromeClientCallbackManager.ReceivedTitleCallback mCallback=new ChromeClientCallbackManager.ReceivedTitleCallback() {
        @Override
        public void onReceivedTitle(WebView view, String title) {
//            if(mTitleTextView!=null&&!TextUtils.isEmpty(title))
//                if(title.length()>10)
//                    title=title.substring(0,10)+"...";
//            mTitleTextView.setText(title);

        }
    };
    protected WebViewClient mWebViewClient = new WebViewClient() {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

            Log.i("Info","shouldOverrideUrlLoading");
            return false;
        }



        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            Log.i("Info","url:"+url+"   target:"+getUrl());
//            if(url.equals(getUrl())){
//                pageNavigator(View.GONE);
//            }else{
//                pageNavigator(View.VISIBLE);
//            }

        }
    };


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        Log.i("Info","onActivityResult result");
        mAgentWeb.uploadFileResult(requestCode,resultCode,data);
    }
}
