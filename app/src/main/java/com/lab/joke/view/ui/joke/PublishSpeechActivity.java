package com.lab.joke.view.ui.joke;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.lab.joke.R;
import com.lab.joke.connection.annotation.WLayout;
import com.lab.joke.environment.AppBundle;
import com.lab.joke.util.AssetsUtil;
import com.lab.joke.util.AudioMergeUtil;
import com.lab.joke.util.SDCardUtil;
import com.lab.joke.view.base.BaseActivity;
import com.lab.joke.view.custom.TitleView;

import java.io.IOException;

import butterknife.InjectView;

/**
 * Created by rokevin on 15/9/25.
 * <p/>
 * 发布语音界面
 */
@WLayout(layoutId = R.layout.activity_publish_speech)
public class PublishSpeechActivity extends BaseActivity {

    @InjectView(R.id.tv_title)
    TitleView tvTitle;

    @InjectView(R.id.btn_player1)
    Button btnPlayer1;

    @InjectView(R.id.btn_player2)
    Button btnPlayer2;

    @InjectView(R.id.btn_player3)
    Button btnPlayer3;

    private String mBundleTitle;

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBundleTitle = getIntent().getStringExtra(AppBundle.TITLE);

        if (TextUtils.isEmpty(mBundleTitle)) {
            mBundleTitle = "";
        }

        tvTitle.setTitle(mBundleTitle);

        tvTitle.setOnFunctionClickListener(new TitleView.OnFunctionClickListener() {
            @Override
            public void onFunctionClick() {

                Intent intent = new Intent(mContext, PublishConfirmActivity.class);
                intent.putExtra(AppBundle.TITLE, mBundleTitle);
                startActivity(intent);
            }
        });

        try {
            AssetsUtil.copyBigDataToSD(mContext, SDCardUtil.getAmrPath("test1"), "test1.amr");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            AssetsUtil.copyBigDataToSD(mContext, SDCardUtil.getAmrPath("test2"), "test2.amr");
        } catch (IOException e) {
            e.printStackTrace();
        }

        btnPlayer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }

                mediaPlayer = new MediaPlayer();

                btnPlayer1.setText("播放中...");

                try {
                    //模拟器里播放传url，真机播放传getAmrPath()
                    mediaPlayer.setDataSource(SDCardUtil.getAmrPath("test1"));
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    //设置播放结束时监听
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            btnPlayer1.setText("播放1");
                        }
                    });
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        btnPlayer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }

                mediaPlayer = new MediaPlayer();

                btnPlayer2.setText("播放中...");

                try {
                    //模拟器里播放传url，真机播放传getAmrPath()
                    //mediaPlayer.setDataSource(SDCardUtil.getAmrPath("test2"));
                    Uri uri = Uri.parse("http://175.43.20.36/ws.cdn.baidupcs.com/file/47c89116911271074232683d01a74cff?bkt=p3-140047c89116911271074232683d01a74cff7e9098ac000000003ef4&xcode=b70d4c6872b083e1d88aa68dc374ad922c275fabad4fca718bb5ee938cac2427&fid=1696687010-250528-965279971868975&time=1461143189&sign=FDTAXGERLBH-DCb740ccc5511e5e8fedcff06b081203-%2BfWmyspUzuhwrfo7jPQVMj6qGeU%3D&to=lc&fm=Nin,B,U,nc&sta_dx=0&sta_cs=13&sta_ft=amr&sta_ct=0&fm2=Ningbo,B,U,nc&newver=1&newfm=1&secfm=1&flow_ver=3&pkey=140047c89116911271074232683d01a74cff7e9098ac000000003ef4&sl=78053455&expires=8h&rt=sh&r=300947301&mlogid=2567743295170035894&vuk=1696687010&vbdid=3581649033&fin=mao.amr&slt=pm&uta=0&rtype=1&iv=0&isw=0&dp-logid=2567743295170035894&dp-callid=0.1.1&wshc_tag=0&wsts_tag=57174695&wsid_tag=72fe9083&wsiphost=ipdbm");
                    mediaPlayer.setDataSource(mContext, uri);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    //设置播放结束时监听
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            btnPlayer2.setText("播放2");
                        }
                    });
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        btnPlayer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }

                mediaPlayer = new MediaPlayer();

                btnPlayer3.setText("播放中...");

                try {
                    //模拟器里播放传url，真机播放传getAmrPath()

                    String[] paths = {SDCardUtil.getAmrPath("test1"), SDCardUtil.getAmrPath("test2")};

                    AudioMergeUtil.uniteAMRFile(paths, SDCardUtil.getAmrPath("test3"));

                    mediaPlayer.setDataSource(SDCardUtil.getAmrPath("test3"));
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    //设置播放结束时监听
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            btnPlayer3.setText("合并播放");
                        }
                    });
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }

}
